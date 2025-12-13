package com.example.simple_poop.init;

import com.example.simple_poop.PoopMod;
import com.example.simple_poop.blocks.Poop;
import com.example.simple_poop.items.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryHandler {
    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, PoopMod.MOD_ID);

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PoopMod.MOD_ID);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PoopMod.MOD_ID);




    public static final  DeferredRegister<CreativeModeTab>
            CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PoopMod.MOD_ID);

    public static void init() {
        Items.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //items
    //public static final RegistryObject<Item> WORM = Items.register("worm", () -> new Worm());


    //blocks

    public static final RegistryObject<Block> POOP = BLOCKS.register("poop",() -> new Poop());
    public static final RegistryObject<Item> POOP_ITEM = Items.register("poop", () -> new BlockItemGenerator(POOP.get()));



    public static final RegistryObject<CreativeModeTab> TAB =
            CREATIVE_MODE_TABS.register("simple_poop", () -> CreativeModeTab.builder()
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .title(Component.translatable(RegistryHandler.TAB.getId().toLanguageKey("tab")))
                    .icon(RegistryHandler.POOP_ITEM.get()::getDefaultInstance)
                    .displayItems((parameters, output) -> {
                        //output.accept(WORM.get().getDefaultInstance());
                        output.accept(POOP_ITEM.get().getDefaultInstance());

                    }).build());

}
