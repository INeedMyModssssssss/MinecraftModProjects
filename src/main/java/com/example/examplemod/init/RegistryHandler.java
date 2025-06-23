package com.example.examplemod.init;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.blocks.Dirt;
import com.example.examplemod.blocks.Ore;
import com.example.examplemod.entities.AtomicFireballEntity;
import com.example.examplemod.entities.CandyZombieEntity;
import com.example.examplemod.items.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryHandler {
    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ExampleMod.MOD_ID);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MOD_ID);

    public static final RegistryObject<Block> DIRT = BLOCKS.register("dirt", () -> new Dirt());

    public static final RegistryObject<Item> DIRT_ITEM = Items.register("dirt", () -> new BlockItemGenerator(DIRT.get()));



    public static final  DeferredRegister<CreativeModeTab>
            CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExampleMod.MOD_ID);

    public static void init() {
        Items.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //items
    public static final RegistryObject<Item> WORM = Items.register("worm", () -> new Worm());
    public static final RegistryObject<Item> INGOT = Items.register("ingot", () -> new Ingot());

    public static final RegistryObject<Item> PICKAXE = Items.register("pickaxe", () -> new Pickaxe());
    public static final RegistryObject<Item> SHOVEL = Items.register("shovel", () -> new Shovel());
    public static final RegistryObject<Item> AXE = Items.register("axe", () -> new Axe());
    public static final RegistryObject<Item> HOE = Items.register("hoe", () -> new Hoe());
    public static final RegistryObject<Item> Sword = Items.register("sword", () -> new SuperSword());
    public static final RegistryObject<Item> TELEPORTER = Items.register("teleporter", () -> new Teleporter());

    public static final RegistryObject<Item> HELMET = Items.register("helmet", () -> new Armor(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> CHESTPLATE = Items.register("chestplate", () -> new Armor(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> LEGGINGS = Items.register("leggings", () -> new Armor(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> BOOTS = Items.register("boots", () -> new Armor(ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> ATOMIC_FIREBALL = Items.register("atomic_fireball", () -> new AtomicFireball());
    public static final RegistryObject<Item> LAUNCHER = Items.register("atomic_launcher", () -> new Launcher());

    public static final RegistryObject<Block> ORE = BLOCKS.register("ore",() -> new Ore());
    public static final RegistryObject<Item> ORE_ITEM = Items.register("ore", () -> new BlockItemGenerator(ORE.get()));

    //Entities
    public static final RegistryObject<EntityType<AtomicFireballEntity>> ATOMIC_FIREBALL_ENTITY = ENTITY_TYPES.register("atomic_fireball", () -> EntityType.Builder.<AtomicFireballEntity>of(AtomicFireballEntity::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(4)
            .updateInterval(10)
            .build("atomic_fireball"));
    public static final RegistryObject<EntityType<CandyZombieEntity>> CANDY_ZOMBIE = ENTITY_TYPES.register("candy_zombie", () -> EntityType.Builder.of(CandyZombieEntity::new, MobCategory.MONSTER)
            .sized(0.6f, 1.95f)
            .build("candy_zombie"));

    //spawn eggs
    public static final RegistryObject<Item> CANDY_ZOMBIE_SPAWN_EGG = Items.register("candy_zombie_spawn_egg", () -> new CandyZombieSpawnEgg());

    public static final RegistryObject<CreativeModeTab> TAB =
            CREATIVE_MODE_TABS.register("mytab", () -> CreativeModeTab.builder()
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .title(Component.translatable(RegistryHandler.TAB.getId().toLanguageKey("tab")))
                    .icon(RegistryHandler.WORM.get()::getDefaultInstance)
                    .displayItems((parameters, output) -> {
                        output.accept(WORM.get().getDefaultInstance());
                        output.accept(INGOT.get().getDefaultInstance());
                        output.accept(DIRT_ITEM.get().getDefaultInstance());
                        output.accept(ORE_ITEM.get().getDefaultInstance());
                        output.accept(PICKAXE.get().getDefaultInstance());
                        output.accept(AXE.get().getDefaultInstance());
                        output.accept(SHOVEL.get().getDefaultInstance());
                        output.accept(HOE.get().getDefaultInstance());
                        output.accept(Sword.get().getDefaultInstance());
                        output.accept(HELMET.get().getDefaultInstance());
                        output.accept(CHESTPLATE.get().getDefaultInstance());
                        output.accept(LEGGINGS.get().getDefaultInstance());
                        output.accept(BOOTS.get().getDefaultInstance());
                        output.accept(LAUNCHER.get().getDefaultInstance());
                        output.accept(ATOMIC_FIREBALL.get().getDefaultInstance());
                        output.accept(TELEPORTER.get().getDefaultInstance());
                        output.accept(CANDY_ZOMBIE_SPAWN_EGG.get().getDefaultInstance());
                    }).build());

}
