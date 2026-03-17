package org.teamvoided.voided_delight.util.outsource

import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemGroup

//TODO Move to Voidlib
typealias TabBuilder = ItemGroup.ItemStackCollector.(ItemGroup.DisplayParameters) -> Unit

fun ItemGroup.Builder.entries(fn: TabBuilder): ItemGroup.Builder = entries { par, col -> fn.invoke(col, par) }
fun ItemGroup.ItemStackCollector.add(vararg item: ItemConvertible) = item.forEach(::addItem)
