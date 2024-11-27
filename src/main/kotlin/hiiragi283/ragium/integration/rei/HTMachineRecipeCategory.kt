package hiiragi283.ragium.integration.rei

import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineTier
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.gui.Renderer
import me.shedaniel.rei.api.client.gui.widgets.Widget
import me.shedaniel.rei.api.client.gui.widgets.Widgets
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.text.Text

@Environment(EnvType.CLIENT)
class HTMachineRecipeCategory(private val key: HTMachineKey) : HTDisplayCategory<HTMachineRecipeDisplay> {
    override fun getCategoryIdentifier(): CategoryIdentifier<out HTMachineRecipeDisplay> = key.categoryId

    override fun getTitle(): Text = key.text

    override fun getIcon(): Renderer = key.createEntryStack(HTMachineTier.PRIMITIVE)

    override fun setupDisplay(display: HTMachineRecipeDisplay, bounds: Rectangle): List<Widget> = buildList {
        this += Widgets.createRecipeBase(bounds)
        this += Widgets.createArrow(getPoint(bounds, 3.25, 0.0)).animationDurationTicks(200.0)
        // inputs
        this += createSlot(bounds, 0, 0, display.inputEntries.getOrNull(0)).markInput()
        this += createSlot(bounds, 1, 0, display.inputEntries.getOrNull(1)).markInput()
        this += createSlot(bounds, 2, 0, display.inputEntries.getOrNull(2)).markInput()
        this += createSlot(bounds, 0, 1, display.inputEntries.getOrNull(3)).markInput()
        this += createSlot(bounds, 1, 1, display.inputEntries.getOrNull(4)).markInput()
        this += createSlot(bounds, 2, 1, display.inputEntries.getOrNull(5)).markInput()
        // outputs
        this += createSlot(bounds, 5, 0, display.outputEntries.getOrNull(0)).markOutput()
        this += createSlot(bounds, 6, 0, display.outputEntries.getOrNull(1)).markOutput()
        this += createSlot(bounds, 7, 0, display.outputEntries.getOrNull(2)).markOutput()
        this += createSlot(bounds, 5, 1, display.outputEntries.getOrNull(3)).markOutput()
        this += createSlot(bounds, 6, 1, display.outputEntries.getOrNull(4)).markOutput()
        this += createSlot(bounds, 7, 1, display.outputEntries.getOrNull(5)).markOutput()
        // catalyst
        this += createSlot(bounds, 3.5, 1.0, display.catalyst).markInput()
        // info
        this += Widgets
            .createLabel(getPoint(bounds, 0, 2), display.recipe.tier.recipeCostText)
            .color(0x404040, 0xBBBBBB)
            .noShadow()
            .leftAligned()
        this += Widgets
            .createLabel(getPoint(bounds, 0.0, 2.5), display.recipe.tier.tierText)
            .color(0x404040, 0xBBBBBB)
            .noShadow()
            .leftAligned()
    }

    override fun getDisplayHeight(): Int = 18 * 3 + 8

    override fun getDisplayWidth(display: HTMachineRecipeDisplay): Int = 18 * 8 + 8
}
