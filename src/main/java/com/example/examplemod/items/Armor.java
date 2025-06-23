package com.example.examplemod.items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Armor extends ArmorItem {

    private static Properties armorProperties = new Properties();

    public Armor(ArmorItem.Type armorSlot) {
        super(MySuperMaterials.MAT1, armorSlot, armorProperties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slotIndex, boolean isSelected) {
        if(entity instanceof LivingEntity livingEntity
                && slotIndex == getType().getSlot().getIndex()
                && entity.tickCount % 60 == 0) {
            livingEntity.heal(1.0F);
        }
    }
}
