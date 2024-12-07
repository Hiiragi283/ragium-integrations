package hiiragi283.ragium.integration.rei.category

import me.shedaniel.math.Point
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.gui.widgets.Slot
import me.shedaniel.rei.api.client.gui.widgets.Widgets
import me.shedaniel.rei.api.client.registry.display.DisplayCategory
import me.shedaniel.rei.api.common.display.Display
import me.shedaniel.rei.api.common.entry.EntryStack

interface HTDisplayCategory<T : Display> : DisplayCategory<T> {
    fun getPoint(bounds: Rectangle, x: Int, y: Int): Point = Point(bounds.x + 5 + x * 18, bounds.y + 5 + y * 18)

    fun getPoint(bounds: Rectangle, x: Double, y: Double): Point = Point(bounds.x + 5 + x * 18, bounds.y + 5 + y * 18)

    fun createSlot(
        bounds: Rectangle,
        x: Int,
        y: Int,
        ingredient: Collection<EntryStack<*>>?,
    ): Slot = Widgets
        .createSlot(getPoint(bounds, x, y))
        .entries(ingredient ?: listOf())

    fun createSlot(
        bounds: Rectangle,
        x: Double,
        y: Double,
        ingredient: Collection<EntryStack<*>>?,
    ): Slot = Widgets
        .createSlot(getPoint(bounds, x, y))
        .entries(ingredient ?: listOf())
}
