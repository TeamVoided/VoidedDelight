package org.teamvoided.voided_delight.item

import net.minecraft.entity.player.HungerConstants
import net.minecraft.item.FoodComponent
import java.util.*

@Suppress("HasPlatformType", "MagicNumber")
object VDFoodComponents {
    val CANDY_BERRY = foodComponent(2, 0.2f, 0.8f)
    val MARSHMAROW = foodComponent(2, 0.2f, 0.8f)
    val LOLLIPOP = foodComponent(2, 0.2f, 1.6f)
    val CANDY_CORN = foodComponent(2, 0.2f, 0.8f)
    val LITCHORRICE = foodComponent(2, 0.2f, 0.8f)
    val CLOUDY_CANDY = foodComponent(2, 0.2f, 0.8f)
    val SYRUP_APPLE = foodComponent(26, 0.4f, 1.2f)

    val CRYSTAL_CANDY_8 = foodComponent(8, 0.8f, 3f) //full block
    val CRYSTAL_CANDY_6 = foodComponent(6, 0.6f, 2f) //stair and wall block
    val CRYSTAL_CANDY_4 = foodComponent(4, 0.4f, 1.6f) //slab block
    val CRYSTAL_CANDY_2 = foodComponent(2, 0.2f, 0.8f) //shard item

    fun foodComponent(hunger: Int, saturation: Float, eatSeconds: Float): FoodComponent {
        val saturationButComponent: Float = HungerConstants.calculateSaturation(hunger, saturation)
        return FoodComponent(hunger, saturationButComponent, false, eatSeconds, Optional.empty(), listOf());
    }
    //1.6
}