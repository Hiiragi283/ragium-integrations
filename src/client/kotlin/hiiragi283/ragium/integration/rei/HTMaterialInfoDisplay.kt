package hiiragi283.ragium.integration.rei

import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.recipe.HTIngredient
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.display.Display
import me.shedaniel.rei.api.common.entry.EntryIngredient

class HTMaterialInfoDisplay(private val key: HTMaterialKey) : Display {
    val ingredientMap: Map<HTTagPrefix, EntryIngredient> =
        HTTagPrefix.entries.associateWith { HTIngredient.ofItem(it.createTag(key)).entryIngredient }

    private val entries: List<EntryIngredient>
        get() = ingredientMap.values.toList()

    override fun getInputEntries(): List<EntryIngredient> = entries

    override fun getOutputEntries(): List<EntryIngredient> = entries

    override fun getCategoryIdentifier(): CategoryIdentifier<*> = RagiumREIClient.MATERIAL_INFO
}
