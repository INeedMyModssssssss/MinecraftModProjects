package com.example.examplemod.items;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;

public class BlockItemGenerator extends BlockItem {

    private static final Properties blockItemProp = new Properties();

    public BlockItemGenerator(Block blockIn) {
        super(blockIn, blockItemProp);
    }
}