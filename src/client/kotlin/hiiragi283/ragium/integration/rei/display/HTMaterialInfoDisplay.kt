package hiiragi283.ragium.integration.rei.display

import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.recipe.HTItemIngredient
import hiiragi283.ragium.integration.rei.RagiumREIClient
import hiiragi283.ragium.integration.rei.entryIngredient
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.display.Display
import me.shedaniel.rei.api.common.entry.EntryIngredient

class HTMaterialInfoDisplay(private val key: HTMaterialKey) : Display {
    val ingredientMap: Map<HTTagPrefix, EntryIngredient> =
        HTTagPrefix.entries.associateWith { HTItemIngredient.of(it.createTag(key)).entryIngredient }

    private val entries: List<EntryIngredient>
        get() = ingredientMap.values.toList()

    override fun getInputEntries(): List<EntryIngredient> = entries

    override fun getOutputEntries(): List<EntryIngredient> = entries

    override fun getCategoryIdentifier(): CategoryIdentifier<*> = RagiumREIClient.MATERIAL_INFO
}
