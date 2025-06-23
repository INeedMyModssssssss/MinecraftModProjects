package com.example.examplemod;

import com.example.examplemod.entities.AtomicFireballRenderer;
import com.example.examplemod.entities.CandyZombieEntity;
import com.example.examplemod.entities.CandyZombieRenderer;
import com.example.examplemod.init.RegistryHandler;
import com.example.examplemod.items.Launcher;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
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
@Mod("mymod")
@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExampleMod {
    // NOTE log4j logger -> slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // Helpful global variables.
    public static final String MOD_ID = "mymod";

    public ExampleMod() {

        RegistryHandler.init();
        // Register my stuff and link to RegistryHandler file init method

    }

    @SubscribeEvent
    public static void onRegisterEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(RegistryHandler.CANDY_ZOMBIE.get(), CandyZombieEntity.createCandyZombieAttributes().build());
    }

    @SubscribeEvent
    public static void onBuildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.SPAWN_EGGS)) {
            event.accept(RegistryHandler.CANDY_ZOMBIE_SPAWN_EGG.get());
        }

        // This event listener is registered automatically because of the @Mod.EventBusSubscriber annotation
//    @SubscribeEvent
//    public static void onBuildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
//
//    }

        // NOTE static inner class to avoid classloading errors on dedicated server
        //// "value = Dist.CLIENT" is EXTREMELY IMPORTANT
        @Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
        class ClientEvents {

            @SubscribeEvent
            public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
                event.registerEntityRenderer(RegistryHandler.ATOMIC_FIREBALL_ENTITY.get(), AtomicFireballRenderer::new);
                event.registerEntityRenderer(RegistryHandler.CANDY_ZOMBIE.get(), CandyZombieRenderer::new);
            }

            // NOTE this method
            @SubscribeEvent
            public static void onClientSetup(FMLClientSetupEvent event) {
            }

            private static void addItemPropertyOverrides() {
                ItemProperties.register(RegistryHandler.LAUNCHER.get(),
                        new ResourceLocation("charged"),
                        (itemStack, world, entity, tintIndex) -> Launcher.isCharged(itemStack) ? 1 : 0);

            }

        }
    }
}

























