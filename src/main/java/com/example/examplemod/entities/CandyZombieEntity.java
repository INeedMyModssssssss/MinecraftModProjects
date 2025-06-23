package com.example.examplemod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

import javax.management.Attribute;

public class CandyZombieEntity extends Zombie {

    public CandyZombieEntity(EntityType<? extends Zombie> type, Level worldIn) {
        super(type, worldIn);
    }

    public static AttributeSupplier.Builder createCandyZombieAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.FOLLOW_RANGE,35.0D)
                .add(Attributes.MOVEMENT_SPEED,0.5D)
                .add(Attributes.ATTACK_DAMAGE,5.0D)
                .add(Attributes.ATTACK_KNOCKBACK,5.0D)
                .add(Attributes.ARMOR,3.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE,0.3D);
    }
}
