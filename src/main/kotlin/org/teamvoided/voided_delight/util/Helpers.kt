@file:Suppress("unused")

package org.teamvoided.voided_delight.util

import net.minecraft.registry.Holder
import net.minecraft.registry.Registry
import org.teamvoided.voided_delight.VoidedDelight.MODID


fun <T> isModHolder(holder: Holder<T>) = holder.matches { it.value.namespace == MODID }

fun <T> getModHolders(registry: Registry<T>): List<Holder<T>> = registry.holders()
    .filter(::isModHolder)
    .toList()

fun <T> getModEntries(registry: Registry<T>): List<T> = registry.holders()
    .filter(::isModHolder)
    .map(Holder<T>::value)
    .toList()
