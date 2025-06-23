package com.example.examplemod.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class Dirt extends Block {
    private static Properties dirtProperties = Properties.of()
            .mapColor(MapColor.DIRT)
            .strength(0.5F, 0.5F)
            .sound(SoundType.GRAVEL);

    public Dirt() {
        super(dirtProperties);
    }
}
