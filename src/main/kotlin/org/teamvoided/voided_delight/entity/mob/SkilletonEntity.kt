package org.teamvoided.voided_delight.entity.mob

import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LightningEntity
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.AbstractSkeletonEntity
import net.minecraft.entity.mob.CreeperEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import org.teamvoided.voided_delight.FDItems
import org.teamvoided.voided_delight.VoidedDelight.config
import org.teamvoided.voided_delight.init.VDItems
import vectorwing.farmersdelight.common.registry.ModSounds

class SkilletonEntity(entityType: EntityType<out SkilletonEntity>, world: World) :
    AbstractSkeletonEntity(entityType, world) {

    override fun getStepSound(): SoundEvent =
        if (isNetherite()) SoundEvents.BLOCK_NETHERITE_BLOCK_STEP else SoundEvents.BLOCK_LANTERN_STEP

    override fun getAmbientSound(): SoundEvent =
        if (isNetherite()) SoundEvents.BLOCK_NETHERITE_BLOCK_HIT else SoundEvents.BLOCK_LANTERN_HIT

    override fun getHurtSound(source: DamageSource?): SoundEvent =
        if (isNetherite()) SoundEvents.BLOCK_NETHERITE_BLOCK_BREAK else ModSounds.ITEM_SKILLET_ATTACK_WEAK.get()

    override fun getDeathSound(): SoundEvent = ModSounds.ITEM_SKILLET_ATTACK_STRONG.get()

    override fun isAffectedByDaylight(): Boolean = false


    override fun initEquipment(random: RandomGenerator, difficulty: LocalDifficulty) {
        super.initEquipment(random, difficulty)
        if ((random.nextInt(10) - (difficulty.clampedLocalDifficulty * 3)) > 1) {
            equipStack(EquipmentSlot.MAINHAND, ItemStack(FDItems.SKILLET.get()))
        }
        if (random.nextInt(25) == 0) {
            equipStack(EquipmentSlot.HEAD, ItemStack(FDItems.SKILLET.get()))
        }
    }

    override fun createArrowProjectile(
        stack: ItemStack, damageModifier: Float, weapon: ItemStack?,
    ): PersistentProjectileEntity {
        val arrow = super.createArrowProjectile(stack, damageModifier, weapon)
        arrow.setOnFireForSeconds(100.0f)
        return arrow
    }

    override fun dropCustomLoot(world: ServerWorld?, source: DamageSource, playerKill: Boolean) {
        super.dropCustomLoot(world, source, playerKill)
        val creeper = source.attacker
        if (creeper is CreeperEntity && creeper.shouldDropHead()) {
            creeper.onHeadDropped()
            dropItem(if (isNetherite()) VDItems.NETHERITE_SKILLETON_SKULL else VDItems.SKILLETON_SKULL)
        }
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(NETHERITE, false)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        if (nbt.contains(N_KEY, NbtElement.BYTE_TYPE.toInt())) {
            setNetherite(nbt.getBoolean(N_KEY))
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        if (isNetherite()) nbt.putBoolean(N_KEY, isNetherite())
    }


    fun isNetherite(): Boolean = dataTracker.get(NETHERITE)
    fun setNetherite(state: Boolean) = dataTracker.set(NETHERITE, state)

    override fun isFireImmune(): Boolean = isNetherite() || super.isFireImmune()
    override fun doesRenderOnFire(): Boolean = !isNetherite() && super.doesRenderOnFire()

    override fun onStruckByLightning(world: ServerWorld?, lightning: LightningEntity?) {
        super.onStruckByLightning(world, lightning)
        setNetherite(true)
    }

    companion object {
        const val N_KEY = "is_netherite"
        val NETHERITE: TrackedData<Boolean> =
            DataTracker.registerData(SkilletonEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

        fun canSpawn(
            type: EntityType<SkilletonEntity>, world: ServerWorldAccess, spawnReason: SpawnReason,
            pos: BlockPos, random: RandomGenerator,
        ): Boolean = config.skilletonCanSpawn && canSpawnInDark(type, world, spawnReason, pos, random)
    }
}