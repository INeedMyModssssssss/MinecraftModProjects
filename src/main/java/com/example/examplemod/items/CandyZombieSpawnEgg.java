package com.example.examplemod.items;

import com.example.examplemod.init.RegistryHandler;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.function.Supplier;

public class CandyZombieSpawnEgg extends ForgeSpawnEggItem {

    private static Properties properties = new Properties();

    public CandyZombieSpawnEgg() {
        super(RegistryHandler.CANDY_ZOMBIE, 0x0000D5, 0x000000, properties);
    }
}
