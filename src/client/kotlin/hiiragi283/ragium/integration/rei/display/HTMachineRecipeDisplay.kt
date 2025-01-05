package hiiragi283.ragium.integration.rei.display

import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.recipe.*
import hiiragi283.ragium.integration.rei.categoryId
import hiiragi283.ragium.integration.rei.entryIngredient
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.display.Display
import me.shedaniel.rei.api.common.entry.EntryIngredient
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.recipe.RecipeEntry
import net.minecraft.util.Identifier
import java.util.*

@Environment(EnvType.CLIENT)
class HTMachineRecipeDisplay(private val recipe: HTMachineRecipeBase, private val id: Identifier? = null) : Display {
    constructor(entry: RecipeEntry<out HTMachineRecipeBase>) : this(entry.value, entry.id)

    val tier: HTMachineTier = recipe.tier
    val catalyst: EntryIngredient? = recipe.catalyst?.entryIngredient

    fun getItemInput(index: Int): EntryIngredient = recipe.getItemIngredient(index)?.entryIngredient ?: EntryIngredient.empty()

    fun getFluidInput(index: Int): EntryIngredient = recipe.getFluidIngredient(index)?.entryIngredient ?: EntryIngredient.empty()

    fun getItemOutput(index: Int): EntryIngredient = recipe.getItemResult(index)?.entryIngredient ?: EntryIngredient.empty()

    fun getFluidOutput(index: Int): EntryIngredient = recipe.getFluidResult(index)?.entryIngredient ?: EntryIngredient.empty()

    override fun getInputEntries(): List<EntryIngredient> = buildList {
        recipe.itemIngredients.map(HTItemIngredient::entryIngredient).forEach(this::add)
        recipe.fluidIngredients.map(HTFluidIngredient::entryIngredient).forEach(this::add)
        add(recipe.catalyst?.entryIngredient ?: EntryIngredient.empty())
    }

    override fun getOutputEntries(): List<EntryIngredient> = buildList {
        recipe.itemResults.map(HTItemResult::entryIngredient).forEach(this::add)
        recipe.fluidResults.map(HTFluidResult::entryIngredient).forEach(this::add)
    }

    override fun getCategoryIdentifier(): CategoryIdentifier<*> = recipe.key.categoryId

    override fun getDisplayLocation(): Optional<Identifier> = Optional.ofNullable(id)
}
