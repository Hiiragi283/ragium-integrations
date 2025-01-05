package hiiragi283.ragium.integration.rei.category

import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.integration.rei.categoryId
import hiiragi283.ragium.integration.rei.createEntryStack
import hiiragi283.ragium.integration.rei.display.HTMachineRecipeDisplay
import hiiragi283.ragium.integration.rei.getPoint
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.gui.Renderer
import me.shedaniel.rei.api.client.gui.widgets.Widget
import me.shedaniel.rei.api.client.gui.widgets.Widgets
import me.shedaniel.rei.api.client.registry.display.DisplayCategory
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.text.Text
import java.util.function.Consumer

@Environment(EnvType.CLIENT)
abstract class HTMachineCategoryBase(protected val key: HTMachineKey) : DisplayCategory<HTMachineRecipeDisplay> {
    final override fun getCategoryIdentifier(): CategoryIdentifier<out HTMachineRecipeDisplay> = key.categoryId

    final override fun getTitle(): Text = key.text

    final override fun getIcon(): Renderer = key.createEntryStack(HTMachineTier.PRIMITIVE)

    final override fun setupDisplay(display: HTMachineRecipeDisplay, bounds: Rectangle): List<Widget> = buildList {
        this += Widgets.createRecipeBase(bounds)
        this += Widgets.createArrow(getPoint(bounds, 3.25, 0.0)).animationDurationTicks(200.0)
        arrangeSlots(display, bounds, this::add)
        // info
        this += Widgets
            .createLabel(getPoint(bounds, 0, maxSlots), display.tier.recipeCostText)
            .color(0x404040, 0xBBBBBB)
            .noShadow()
            .leftAligned()
        this += Widgets
            .createLabel(getPoint(bounds, 0.0, maxSlots + 0.5), display.tier.tierText)
            .color(0x404040, 0xBBBBBB)
            .noShadow()
            .leftAligned()
    }

    abstract fun arrangeSlots(display: HTMachineRecipeDisplay, bounds: Rectangle, consumer: Consumer<Widget>)

    abstract val maxSlots: Int

    final override fun getDisplayHeight(): Int = 18 * (maxSlots + 1) + 8

    final override fun getDisplayWidth(display: HTMachineRecipeDisplay): Int = 18 * 8 + 8
}
