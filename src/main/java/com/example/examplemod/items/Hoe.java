package com.example.examplemod.items;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class Hoe extends HoeItem {


    private static int attackDamage = 0;
    private static float attackSpeed = -2.8F;
    private static Properties hoeProperties = new Properties();


    public Hoe() {
        super(MySuperTiers.TIER1,attackDamage,attackSpeed,hoeProperties);
    }
}
