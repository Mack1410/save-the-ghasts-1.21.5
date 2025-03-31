package roseherald.savetheghasts.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import roseherald.savetheghasts.entity.ModEntities;
import roseherald.savetheghasts.item.ModItems;

public class SpongeballProjectileEntity extends ThrownItemEntity {

    public SpongeballProjectileEntity(EntityType<? extends SpongeballProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public SpongeballProjectileEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ModEntities.SPONGEBALL, owner, world, stack);
    }

    public SpongeballProjectileEntity(World world, double x, double y, double z, ItemStack stack) {
        super(ModEntities.SPONGEBALL, x, y, z, world, stack);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SPONGEBALL;
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getStack();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.ASH : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for (int i = 0; i < 8; i++) {
                this.getWorld().addParticleClient(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int ghastDamage = entity instanceof GhastEntity ? 100 : 0;

        if (entity.getWorld() instanceof ServerWorld serverWorld) {

            entity.damage(serverWorld, this.getDamageSources().thrown(this, this.getOwner()), ghastDamage);

            if (entity instanceof GhastEntity){
                entity.dropItem(serverWorld, Items.NETHER_STAR); //REPLACE WITH DRIED GHAST
                entity.dropItem(serverWorld, Items.FIRE_CHARGE);
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }
}
