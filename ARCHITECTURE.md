# PoopMod Architecture

## Overview
PoopMod is a Minecraft mod built using the **Forge Mod Loader** for **Minecraft 1.20.1** (Forge version 47.2.0). The mod adds a feature where players can press a custom keybind ("P") to physically "poop" within the game world. It includes custom blocks, creative tabs, input handling, and client-server networking to handle the logic.

## Project Structure
The Java source code is located in `src/main/java/com/example/simple_poop/`. The mod separates logic based on physical and networking distributions (Client vs. Common/Server).

### 1. Core Entry Point (`PoopMod.java`)
- **Location**: `com/example/simple_poop/PoopMod.java`
- **Purpose**: The main mod class annotated with `@Mod("simple_poop")`.
- **Functionality**:
  - Initializes all Forge Deferred Registers for items, blocks, entity types, and creative tabs via `RegistryHandler.init()`.
  - Handles common setup on the mod Event Bus.
  - Enqueues work to explicitly register the network handlers (`PacketHandler.register()`) on `FMLCommonSetupEvent`.

### 2. Object Registration (`init/RegistryHandler.java`)
- **Location**: `com/example/simple_poop/init/RegistryHandler.java`
- **Purpose**: Centralized location for initializing Forge objects using `DeferredRegister`.
- **Registered Elements**:
  - **Blocks**: `POOP` (Mapped to the `Poop` class logic).
  - **Items**: `POOP_ITEM` (Generates the item block representation for the inventory utilizing `BlockItemGenerator`).
  - **Creative Tabs**: Registers the `simple_poop` tab, placing the poop item within it before the combat section.
  - **Entities**: Has a placeholder entity register (currently unused).

### 3. Blocks & Items
- **Poop Block** (`blocks/Poop.java`): 
  - Extending `Block`.
  - Incorporates custom voxel shapes composed of stacking smaller bounding boxes.
  - **Behavior**: Features an empty collision shape, meaning players can walk through it. It hooks into `entityInside()` to mimic the effect of a berry bush/cobweb (slowing the entity via `makeStuckInBlock`) and applies two Minecraft Status Effects: `MOVEMENT_SLOWDOWN` (Slowness) and `CONFUSION` (Nausea) if the encasing entity is a `LivingEntity`.
- **BlockItemGenerator** (`items/BlockItemGenerator.java`):
  - Thin wrapper to easily wrap blocks in a corresponding Item for dropping, placing, and viewing in GUIs.

### 4. Client Side (Events & Keybinds)
Stored inside `com.example.simple_poop.client.*`. These classes are restricted strictly to client-side mod environments via `Dist.CLIENT` to prevent server-side crashes during deployment.
- **Keybinds.java**: Registers the mapping for `POOP_KEY` on `KEY_P` under a custom "key.categories.simple_poop" category.
- **ClientEvents.java**: Subscribes to input interactions (`InputEvent.Key`). When the "P" key is pressed, it verifies the mapping and sends a custom `PoopPacket` via the `PacketHandler` channel over to the server.

### 5. Client-Server Networking Model
Stored inside `com.example.simple_poop.network.*`.
- **PacketHandler.java**: Manages the network channels. Sets up a standard `SimpleChannel` pointing to the main mod ID, with a base protocol version ("1") for verification.
- **PoopPacket.java**: Defines the networking contract when a user hits "P", transitioning state changes synchronously over to the server.
  - Runs in `Context.enqueueWork()` to be thread-safe for server mutations.
  - Assesses `getSender()` (ServerPlayer info).
  - Manipulates the server-side `FoodData`. If the player possesses saturation or a food level greater than 19, deduction occurs.
  - Finally, determines where placing occurs: finds the block relative to the inverse direction (`facing.getOpposite()`) directly behind the player's vector, placing a `POOP` BlockState at that location.

## Flow of User Action 
1. **User Action**: The client participant naturally presses the `P` key.
2. **Client Validation**: `ClientEvents.onKeyInput` captures this input map, consumes the click to prevent double executions, and blasts a `PoopPacket`.
3. **Data Link**: The channel passes packet bytes to the server context wrapper.
4. **Server Handler**: The execution switches to `Server Thread`. Code authenticates the packet, pulls the correct player instance, removes feeding values, evaluates direction logic, and subsequently constructs the Block in the user's world level.
5. **Entity Impact**: If the player or an entity steps into the instantiated block, the block's `entityInside` handles applying slowness and nausea locally on the server ticks.
