package com.example.examplemod.entities;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class AtomicFireballRenderer extends ThrownItemRenderer<AtomicFireballEntity> {
    public AtomicFireballRenderer(EntityRendererProvider.Context context) {
        super(context,1.0F,true);
    }
}
