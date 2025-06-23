package com.example.examplemod.items;

import net.minecraft.world.item.AxeItem;

public class Axe extends AxeItem {
    private static int attackDamage = 7;
    private static float attackSpeed = -2.8F;
    private static Properties axeProperties = new Properties();

    public Axe() {
        super(MySuperTiers.TIER1, attackDamage, attackSpeed, axeProperties);
    }
}
