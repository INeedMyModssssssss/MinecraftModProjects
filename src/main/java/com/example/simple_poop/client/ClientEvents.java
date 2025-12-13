package com.example.simple_poop.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// Listen for client-side events only
@Mod.EventBusSubscriber(modid = "simple_poop", value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        // Avoid rare null-event issues during startup
        if (Keybinds.POOP_KEY != null && Keybinds.POOP_KEY.consumeClick()) {
            System.out.println("Poop key pressed!");
        }
    }
}
