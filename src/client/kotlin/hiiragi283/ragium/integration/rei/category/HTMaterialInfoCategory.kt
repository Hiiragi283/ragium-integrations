package hiiragi283.ragium.integration.rei.category

import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.integration.rei.RagiumREIClient
import hiiragi283.ragium.integration.rei.display.HTMaterialInfoDisplay
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.gui.Renderer
import me.shedaniel.rei.api.client.gui.widgets.Widget
import me.shedaniel.rei.api.client.gui.widgets.Widgets
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.util.EntryStacks
import net.minecraft.item.Items
import net.minecraft.text.Text

object HTMaterialInfoCategory : HTDisplayCategory<HTMaterialInfoDisplay> {
    override fun getCategoryIdentifier(): CategoryIdentifier<out HTMaterialInfoDisplay> = RagiumREIClient.MATERIAL_INFO

    override fun getTitle(): Text = Text.literal("Material Information")

    override fun getIcon(): Renderer = EntryStacks.of(Items.BOOK)

    override fun setupDisplay(display: HTMaterialInfoDisplay, bounds: Rectangle): List<Widget> = buildList {
        this += Widgets.createRecipeBase(bounds)
        var index = 0
        HTTagPrefix.entries.forEach { prefix: HTTagPrefix ->
            this@buildList += createSlot(bounds, index % 8, index / 8, display.ingredientMap[prefix])
                .markInput()
            index++
        }
    }

    override fun getDisplayHeight(): Int = 18 * 2 + 8

    override fun getDisplayWidth(display: HTMaterialInfoDisplay?): Int = 18 * 8 + 8
}
