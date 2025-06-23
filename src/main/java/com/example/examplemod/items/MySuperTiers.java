package com.example.examplemod.items;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.init.RegistryHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class MySuperTiers {
    public static final Tier TIER1 = new ForgeTier(4, 2000, 15.0F, 5.0F, 30,
            Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).createTagKey(new ResourceLocation(ExampleMod.MOD_ID)),
            () -> Ingredient.of(RegistryHandler.INGOT.get()));

}
