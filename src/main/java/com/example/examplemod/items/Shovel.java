package com.example.examplemod.items;

import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class Shovel extends ShovelItem {

    private static float attackDamage = 1.5F;
    private static float attackSpeed = -3.0F;
    private static Properties shovelProperties = new Properties();

    public Shovel() {
        super(MySuperTiers.TIER1,attackDamage,attackSpeed,shovelProperties);
    }
}
