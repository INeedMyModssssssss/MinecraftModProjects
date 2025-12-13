package com.example.simple_poop.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = net.minecraftforge.api.distmarker.Dist.CLIENT)
public class Keybinds {

    public static KeyMapping POOP_KEY;

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        POOP_KEY = new KeyMapping(
                "key.simple_poop.poop_action",             // translation key
                InputConstants.Type.KEYSYM,
                InputConstants.KEY_P,                      // default key (P)
                "key.categories.simple_poop"               // category in controls menu
        );

        event.register(POOP_KEY);
    }
}
