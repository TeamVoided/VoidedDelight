package org.teamvoided.voided_delight.util

import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

fun Item.Settings.noteBlockSound(sound: SoundEvent): Item.Settings = noteBlockSound(sound.id)
fun Item.Settings.noteBlockSound(soundId: Identifier): Item.Settings = component(DataComponentTypes.NOTE_BLOCK_SOUND, soundId)