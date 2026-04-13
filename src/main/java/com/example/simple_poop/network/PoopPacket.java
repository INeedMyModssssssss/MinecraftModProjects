package com.example.simple_poop.network;

import com.example.simple_poop.init.RegistryHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PoopPacket {
    public PoopPacket() {
    }

    public PoopPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Server side logic
            ServerPlayer player = context.getSender();
            if (player != null) {
                FoodData foodData = player.getFoodData();
                boolean shouldPoop = false;
                
                if (foodData.getSaturationLevel() > 0) {
                    foodData.setSaturation(foodData.getSaturationLevel() - 1);
                    System.out.println(foodData.getSaturationLevel());
                    shouldPoop = true;
                } else if (!foodData.needsFood()) {
                    foodData.setFoodLevel(19);
                    System.out.println(foodData.getFoodLevel());
                    shouldPoop = true;
                }

                if (shouldPoop) {
                    Direction facing = player.getDirection();
                    Direction behind = facing.getOpposite();
                    BlockPos blockPos = player.blockPosition().relative(behind);
                    Level level = player.level();
                    level.setBlock(blockPos, RegistryHandler.POOP.get().defaultBlockState(), 3);
                }
            }
        });
        context.setPacketHandled(true);
    }
}
