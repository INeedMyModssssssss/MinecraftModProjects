package com.example.examplemod.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class SuperSword extends SwordItem {

    private static int attackDamage = 10;
    private static float attackSpeed = -2.0F;
    private static Properties swordProperties = new Properties();

    public SuperSword() {
        super(MySuperTiers.TIER1,attackDamage,attackSpeed,swordProperties);
    }
    @Override

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        ItemStack itemStack = player.getItemInHand(hand);

        if (!world.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            if(!player.isCrouching()) {
                HitResult coord = player.pick(80, 1.0F, false);

                if(coord.getType() == HitResult.Type.BLOCK) {
                    LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
                    lightning.setPos(coord.getLocation());
                    lightning.setVisualOnly(false);
                    world.addFreshEntity(lightning);

                    return InteractionResultHolder.success(itemStack);
                }

            } else {
                Vec3 look = player.getLookAngle();

                double xPos = player.getX();
                double yPos = player.getEyeY();
                double zPos = player.getZ();

                double accel = 1.4;
                double xAccel = look.x * accel;
                double yAccel = look.y * accel;
                double zAccel = look.z * accel;

                SmallFireball fireball = new SmallFireball(world, xPos, yPos, zPos, xAccel, yAccel, zAccel);

                world.addFreshEntity(fireball);

                return InteractionResultHolder.success(itemStack);
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }
    @Override
    public boolean isFoil(ItemStack item) {
        return true;
    }

}
