package com.example.simple_poop.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class BlockItemGenerator extends BlockItem {

    private static final Properties blockItemProperties = new Properties();

    public BlockItemGenerator(Block blockIn) {
        super(blockIn, blockItemProperties);
    }
}
