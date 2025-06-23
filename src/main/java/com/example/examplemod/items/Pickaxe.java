package com.example.examplemod.items;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class Pickaxe extends PickaxeItem {

    private static int attackDamage = 1;
    private static float attackSpeed = -2.8F;
    private static Properties pickaxeProperties = new Properties();

    public Pickaxe() {
        super(MySuperTiers.TIER1, attackDamage, attackSpeed, pickaxeProperties);
    }
}
