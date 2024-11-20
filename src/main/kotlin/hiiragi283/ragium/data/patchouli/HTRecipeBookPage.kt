package hiiragi283.ragium.data.patchouli

import net.minecraft.recipe.RecipeType
import net.minecraft.util.Identifier
import vazkii.patchouli.client.book.page.abstr.PageDoubleRecipe
import java.util.*
import kotlin.jvm.optionals.getOrNull

data class HTRecipeBookPage(
    val recipeType: RecipeType<*>,
    val recipe: Identifier,
    val recipe2: Identifier? = null,
    val text: String? = null,
    val title: String? = null,
) : HTBookPage<PageDoubleRecipe<*>, HTRecipeBookPage> {
    constructor(
        recipeType: RecipeType<*>,
        recipe: Identifier,
        recipe2: Optional<Identifier>,
        text: Optional<String>,
        title: Optional<String>,
    ) : this(
        recipeType,
        recipe,
        recipe2.getOrNull(),
        text.getOrNull(),
        title.getOrNull(),
    )

    override val serializer: HTBookPageSerializer<PageDoubleRecipe<*>, HTRecipeBookPage>
        get() = when (recipeType) {
            RecipeType.CRAFTING -> HTBookPageSerializers.CRAFTING
            RecipeType.SMELTING -> HTBookPageSerializers.SMELTING
            RecipeType.BLASTING -> HTBookPageSerializers.BLASTING
            RecipeType.SMOKING -> HTBookPageSerializers.SMOKING
            RecipeType.CAMPFIRE_COOKING -> HTBookPageSerializers.CAMPFIRE
            RecipeType.SMITHING -> HTBookPageSerializers.SMITHING
            RecipeType.STONECUTTING -> HTBookPageSerializers.STONECUTTING
            else -> throw IllegalStateException("Unsupported recipe type: $recipeType!")
        }
}
