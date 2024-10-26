package hiiragi283.ragium.integration.data.recipe

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.extension.splitWith
import hiiragi283.ragium.common.RagiumContents
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import reborncore.common.crafting.RebornRecipe
import reborncore.common.crafting.SizedIngredient
import techreborn.init.ModRecipes
import techreborn.recipe.recipes.BlastFurnaceRecipe
import java.util.concurrent.CompletableFuture

@Suppress("UnstableApiUsage")
class TRRecipeProvider(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricRecipeProvider(output, completableFuture) {
    override fun getName(): String = "Recipes/Tech Reborn"

    override fun getRecipeIdentifier(identifier: Identifier): Identifier = RagiumAPI.id(identifier.splitWith('/'))

    override fun generate(exporter: RecipeExporter) {
        exporter.accept(
            id("blast_furnace/ragi_steel_ingot"),
            BlastFurnaceRecipe(
                ModRecipes.BLAST_FURNACE,
                listOf(
                    SizedIngredient(1, Ingredient.fromTag(ConventionalItemTags.IRON_INGOTS)),
                    SizedIngredient(4, Ingredient.ofItems(RagiumContents.Dusts.RAGINITE)),
                ),
                listOf(
                    ItemStack(RagiumContents.Ingots.RAGI_STEEL),
                ),
                128,
                20 * 10,
                1700,
            ),
            null,
        )

        exporter.accept(
            id("alloy_smelter/ragi_alloy_ingot"),
            RebornRecipe.Default(
                ModRecipes.ALLOY_SMELTER,
                listOf(
                    SizedIngredient(1, Ingredient.fromTag(ConventionalItemTags.COPPER_INGOTS)),
                    SizedIngredient(4, Ingredient.ofItems(RagiumContents.Dusts.CRUDE_RAGINITE)),
                ),
                listOf(
                    ItemStack(RagiumContents.Ingots.RAGI_ALLOY),
                ),
                6,
                20 * 10,
            ),
            null,
        )
    }

    private fun id(path: String): Identifier = Identifier.of("techreborn", path)
}
