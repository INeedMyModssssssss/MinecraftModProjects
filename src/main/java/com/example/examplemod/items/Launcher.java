package com.example.examplemod.items;

import com.example.examplemod.entities.AtomicFireballEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import com.example.examplemod.init.RegistryHandler;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class Launcher extends CrossbowItem {

    private static Properties launcherProperties = new Properties().durability(400);

    public Launcher() {
        super(launcherProperties);
    }

    private static Predicate<ItemStack> ammo = (checkItem) -> {
        return checkItem.getItem() == RegistryHandler.ATOMIC_FIREBALL.get();
    };

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ammo;
    }

    @Override
    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return ammo;
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(isCharged(itemstack)) {
            if(!world.isClientSide()) {
                AtomicFireballEntity fireball = new AtomicFireballEntity(world, player.getX(), player.getEyeY(), player.getZ());
                float speed = 1.5F;
                float yOffset = 0.0F;
                float inaccuracy = 1.0F;
                fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), yOffset, speed, inaccuracy);
                world.addFreshEntity(fireball);
            }
            setCharged(itemstack, false);
            return InteractionResultHolder.consume(itemstack);
        } else if (!player.getProjectile(itemstack).isEmpty()) {
            if (!isCharged(itemstack)) {
                player.startUsingItem(hand);
            }
            return InteractionResultHolder.consume(itemstack);
        }
        return InteractionResultHolder.fail(itemstack);
    }
}