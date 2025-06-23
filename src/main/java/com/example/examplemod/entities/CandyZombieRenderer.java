package com.example.examplemod.entities;

import com.example.examplemod.ExampleMod;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CandyZombieRenderer extends MobRenderer<CandyZombieEntity, ZombieModel<CandyZombieEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ExampleMod.MOD_ID, "textures/entity/zombie.png");

    public CandyZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)),0.5F);
    }


    @Override
    public ResourceLocation getTextureLocation(CandyZombieEntity entity) {
        return TEXTURE;
    }
}
