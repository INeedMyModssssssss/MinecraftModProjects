package com.example.examplemod.entities;

import com.example.examplemod.init.RegistryHandler;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class AtomicFireballEntity extends ThrowableItemProjectile {

    public AtomicFireballEntity(Level world, LivingEntity thrower) {
        super(RegistryHandler.ATOMIC_FIREBALL_ENTITY.get(), thrower, world);
    }

    public AtomicFireballEntity(EntityType<AtomicFireballEntity> atomicFireballEntity, Level world) {
        super(atomicFireballEntity, world);
    }

    public AtomicFireballEntity(Level world, double x, double y, double z) {
        super(RegistryHandler.ATOMIC_FIREBALL_ENTITY.get(), x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return RegistryHandler.ATOMIC_FIREBALL.get();
    }

    @Override
    protected void onHit(HitResult p_37260_) {
        super.onHit(p_37260_);
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        Entity owner = getOwner();
        DamageSource damageSource = this.level().damageSources().explosion(this, owner);
        hitResult.getEntity().hurt(damageSource, 30.0F);
        entity.setSecondsOnFire(7);
        super.onHitEntity(hitResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        Vec3 pos = hitResult.getLocation();
        float size = 2.0F;
        this.level().explode(this, pos.x(), pos.y(), pos.z(), size, Level.ExplosionInteraction.MOB);
        super.onHitBlock(hitResult);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
