package org.teamvoided.voided_delight.init

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.SpawnLocationTypes
import net.minecraft.entity.mob.AbstractSkeletonEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.world.Heightmap
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.entity.mob.SkilletonEntity
import org.teamvoided.voided_delight.util.getModEntries
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry.register as registerAttributes
import net.minecraft.entity.SpawnRestriction.register as registerRestriction


object VDEntityTypes {
    val ENTITY_TYPES get() = getModEntries(Registries.ENTITY_TYPE)

    val SKILLETON = register(
        "skilleton", EntityType.Builder.create(::SkilletonEntity, SpawnGroup.MONSTER)
            .setDimensions(0.6f, 1.99f)
            .setEyeHeight(1.74f)
            .vehicleAttachment(-0.7f)
            .maxTrackingRange(8)
    )

    fun init() {
        registerAttributes(SKILLETON, AbstractSkeletonEntity.createAttributes())
        registerRestriction(
            SKILLETON, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SkilletonEntity::canSpawn
        )

    }

    fun <T : Entity> register(id: String, builder: EntityType.Builder<T>): EntityType<T> =
        Registry.register(Registries.ENTITY_TYPE, id(id), builder.build())
}
