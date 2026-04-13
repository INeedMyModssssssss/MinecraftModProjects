package com.example.simple_poop.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Poop extends Block {


    private static Properties poopProperties = Properties.of()
            .strength(1.5f, 0.5f)
            .mapColor(MapColor.TERRACOTTA_BROWN)
            .sound(SoundType.HONEY_BLOCK);

    private static final VoxelShape SHAPE = Shapes.or(
            // Layer 1: [0,0,0] to [16,2,16]
            Block.box(0, 0, 0, 16, 2, 16),

            // Layer 2: [2,2,2] to [14,4,14]
            Block.box(2, 2, 2, 14, 4, 14),

            // Layer 3: [4,4,4] to [12,6,12]
            Block.box(4, 4, 4, 12, 6, 12),

            // Layer 4: [6,6,6] to [10,8,10]
            Block.box(6, 6, 6, 10, 8, 10)
    );


    public Poop() {
        super(poopProperties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();  // no collision, walk through
    }


    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        // slow movement (like berry bush)
        entity.makeStuckInBlock(state, new Vec3(0.5D, 0.5D, 0.5D)); //first and 3rd are horizontal, 2nd is y level

        if (!level.isClientSide && entity instanceof LivingEntity living) {

            // apply effect when stepping inside
            living.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 150, 0));
        }

        super.entityInside(state, level, pos, entity);
    }


}
