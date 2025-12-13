package com.example.simple_poop;

import com.example.simple_poop.init.RegistryHandler;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("simple_poop")
@Mod.EventBusSubscriber(modid = PoopMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PoopMod {
    // NOTE log4j logger -> slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // Helpful global variables.
    public static final String MOD_ID = "simple_poop";

    public PoopMod() {

        RegistryHandler.init();
        // Register my stuff and link to RegistryHandler file init method

    }

    @SubscribeEvent
    public static void onRegisterEntityAttributes(EntityAttributeCreationEvent event) {
    }

    @SubscribeEvent
    public static void onBuildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.SPAWN_EGGS)) {

        }

        // This event listener is registered automatically because of the @Mod.EventBusSubscriber annotation
//    @SubscribeEvent
//    public static void onBuildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
//
//    }

        // NOTE static inner class to avoid classloading errors on dedicated server
        //// "value = Dist.CLIENT" is EXTREMELY IMPORTANT
        @Mod.EventBusSubscriber(modid = PoopMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
        class ClientEvents {

            @SubscribeEvent
            public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {

            }

            // NOTE this method
            @SubscribeEvent
            public static void onClientSetup(FMLClientSetupEvent event) {
            }

            private static void addItemPropertyOverrides() {

            }

        }
    }
}

























