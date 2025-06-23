package com.example.examplemod.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class Ore extends Block {

    public Ore() {
        super(oreProperties);
    }

    private static final Properties oreProperties = Properties.of()
            .destroyTime(3.0F)
            .explosionResistance(3.0F)
            .sound(SoundType.STONE)
            .mapColor(MapColor.STONE)
            .lightLevel(b -> 0)
            .requiresCorrectToolForDrops();


}