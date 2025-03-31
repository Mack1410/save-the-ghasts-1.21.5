package roseherald.savetheghasts.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import roseherald.savetheghasts.SaveTheGhasts;
import roseherald.savetheghasts.entity.custom.SpongeballProjectileEntity;

public class ModEntities {

    public static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> type){
        return Registry.register(
                Registries.ENTITY_TYPE,
                RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(SaveTheGhasts.MOD_ID, name)),
                type.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(SaveTheGhasts.MOD_ID, name)))
        );
    }

    public static final EntityType<SpongeballProjectileEntity> SPONGEBALL = register(
            "spongeball",
            EntityType.Builder
                .<SpongeballProjectileEntity>create(SpongeballProjectileEntity::new, SpawnGroup.MISC)
                .dropsNothing()
                .maxTrackingRange(4)
                .trackingTickInterval(10)
                .dimensions(0.25f, 0.25f)
    );
}
