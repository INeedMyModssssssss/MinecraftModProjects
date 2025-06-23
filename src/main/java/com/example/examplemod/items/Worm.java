package com.example.examplemod.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class Worm extends Item {
    private static MobEffectInstance wormEffect = new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
             600, 2);

    private static FoodProperties wormFood = new FoodProperties.Builder()
            .nutrition(3).saturationMod(1.2f).effect(() -> wormEffect, 1f).alwaysEat().build();

    private static final Properties wormProperties = new Properties().food(wormFood).stacksTo(16);

    public Worm() {
        super(wormProperties);

    }
}