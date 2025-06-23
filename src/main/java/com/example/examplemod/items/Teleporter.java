package com.example.examplemod.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;


public class Teleporter extends Item {

    private static Properties teleporterProperties = new Properties();

    public Teleporter() {
        super(teleporterProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        ItemStack itemStack = player.getItemInHand(hand);

        if (!world.isClientSide() && !player.getCooldowns().isOnCooldown(this)) {

            HitResult coord = player.pick(80,1.0F,false);

            if (coord.getType() == HitResult.Type.BLOCK) {

                player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);

                double x = coord.getLocation().x();
                double y = coord.getLocation().y();
                double z = coord.getLocation().z();
                player.teleportTo(x, y, z);

                player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);

                player.getCooldowns().addCooldown(this, 40);
            }
            return InteractionResultHolder.success(itemStack);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }
}
