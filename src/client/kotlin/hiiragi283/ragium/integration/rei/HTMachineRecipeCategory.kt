package hiiragi283.ragium.integration.rei

import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.recipe.HTMachineRecipe
import hiiragi283.ragium.integration.RITranslationKeys
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.gui.Renderer
import me.shedaniel.rei.api.client.gui.widgets.Slot
import me.shedaniel.rei.api.client.gui.widgets.Tooltip
import me.shedaniel.rei.api.client.gui.widgets.Widget
import me.shedaniel.rei.api.client.gui.widgets.Widgets
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.entry.EntryStack
import me.shedaniel.rei.api.common.util.EntryStacks
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.component.ComponentChanges
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.text.Text
import net.minecraft.util.Formatting

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
        this += createSlot(bounds, 0, 1, display.inputEntries.getOrNull(4)).markInput()
        this += createSlot(bounds, 1, 1, display.inputEntries.getOrNull(5)).markInput()
        this += createSlot(bounds, 2, 1, display.inputEntries.getOrNull(6)).markInput()
        // outputs
        this += createSlot(bounds, 5, 0, display.outputEntries.getOrNull(0)).markOutput()
        this += createSlot(bounds, 6, 0, display.outputEntries.getOrNull(1)).markOutput()
        this += createSlot(bounds, 7, 0, display.outputEntries.getOrNull(2)).markOutput()
        this += createSlot(bounds, 5, 1, display.outputEntries.getOrNull(4)).markOutput()
        this += createSlot(bounds, 6, 1, display.outputEntries.getOrNull(5)).markOutput()
        this += createSlot(bounds, 7, 1, display.outputEntries.getOrNull(6)).markOutput()
        // catalyst
        this += createSlot(bounds, 3.5, 1.0, display.catalyst).markInput()
        // info
        this += createInfoSlot(bounds, 7, 1, display).markOutput()
    }

    override fun getDisplayHeight(): Int = 18 * 2 + 8

    override fun getDisplayWidth(display: HTMachineRecipeDisplay): Int = 18 * 8 + 8

    //    Utils    //

    fun createInfoSlot(
        bounds: Rectangle,
        x: Int,
        y: Int,
        display: HTMachineRecipeDisplay,
    ): Slot = Widgets
        .createSlot(getPoint(bounds, x, y))
        .entries(listOf(createInfoEntry(display.recipe)))

    protected fun createInfoEntry(recipe: HTMachineRecipe): EntryStack<*> {
        val entry: RegistryEntry<Item> = Registries.ITEM.getEntry(Items.WRITABLE_BOOK)
        val components: ComponentChanges = ComponentChanges
            .builder()
            .add(
                DataComponentTypes.ITEM_NAME,
                Text.translatable(RITranslationKeys.REI_RECIPE_INFO).formatted(Formatting.LIGHT_PURPLE),
            ).build()
        val stack = ItemStack(entry, 1, components)
        val tier: HTMachineTier = recipe.tier
        return EntryStacks.of(stack).tooltipProcessor { _: EntryStack<ItemStack>, tooltip: Tooltip ->
            tooltip.add(tier.tierText)
            tooltip.add(tier.recipeCostText)
            tooltip
        }
    }
}
