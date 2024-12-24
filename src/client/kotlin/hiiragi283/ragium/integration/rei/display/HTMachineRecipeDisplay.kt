package hiiragi283.ragium.integration.rei.display

import hiiragi283.ragium.api.recipe.HTMachineRecipe
import hiiragi283.ragium.integration.rei.categoryId
import hiiragi283.ragium.integration.rei.entryIngredient
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.display.Display
import me.shedaniel.rei.api.common.entry.EntryIngredient
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.util.Identifier
import java.util.*

@Environment(EnvType.CLIENT)
class HTMachineRecipeDisplay(val recipe: HTMachineRecipe, private val id: Identifier? = null) : Display {
    private fun getItemInput(index: Int): EntryIngredient = recipe.itemInputs.getOrNull(index)?.entryIngredient ?: EntryIngredient.empty()

    private fun getFluidInput(index: Int): EntryIngredient = recipe.fluidInputs.getOrNull(index)?.entryIngredient ?: EntryIngredient.empty()

    private fun getItemOutput(index: Int): EntryIngredient = recipe.itemOutputs.getOrNull(index)?.entryIngredient ?: EntryIngredient.empty()

    private fun getFluidOutput(index: Int): EntryIngredient =
        recipe.fluidOutputs.getOrNull(index)?.entryIngredient ?: EntryIngredient.empty()

    override fun getInputEntries(): List<EntryIngredient> = buildList {
        add(getItemInput(0))
        add(getItemInput(1))
        add(getItemInput(2))
        add(getFluidInput(0))
        add(getFluidInput(1))
        add(getFluidInput(2))
        add(recipe.catalyst?.entryIngredient ?: EntryIngredient.empty())
    }

    override fun getOutputEntries(): List<EntryIngredient> = buildList {
        add(getItemOutput(0))
        add(getItemOutput(1))
        add(getItemOutput(2))
        add(getFluidOutput(0))
        add(getFluidOutput(1))
        add(getFluidOutput(2))
    }

    override fun getCategoryIdentifier(): CategoryIdentifier<*> = recipe.key.categoryId

    override fun getDisplayLocation(): Optional<Identifier> = Optional.ofNullable(id)
}
