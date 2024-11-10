package hiiragi283.ragium.data

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.extension.splitWith
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeType
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import reborncore.common.crafting.RebornRecipe
import reborncore.common.crafting.SizedIngredient
import techreborn.init.ModRecipes
import techreborn.init.TRContent
import techreborn.recipe.recipes.BlastFurnaceRecipe
import java.util.concurrent.CompletableFuture

typealias Exporter = (String, Recipe<*>) -> Unit

@Suppress("UnstableApiUsage")
class TRRecipeProvider(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricRecipeProvider(output, completableFuture) {
    override fun getName(): String = "Recipes/Tech Reborn"

    override fun getRecipeIdentifier(identifier: Identifier): Identifier = RagiumAPI.id(identifier.splitWith('/'))

    override fun generate(exporter: RecipeExporter) {
        generates { path: String, recipe: Recipe<*> ->
            withConditions(exporter, ResourceConditions.allModsLoaded("techreborn")).accept(id(path), recipe, null)
        }
    }

    private fun id(path: String): Identifier = Identifier.of("techreborn", path)

    private fun generates(exporter: Exporter) {
        TRContent.Ingots.TIN
    }

    private fun simple(
        exporter: Exporter,
        recipeType: RecipeType<RebornRecipe>,
        output: ItemConvertible,
        power: Int,
        time: Int,
        vararg inputs: SizedIngredient,
        path: String = CraftingRecipeJsonBuilder.getItemId(output).path,
    ) {
        exporter(
            "${Registries.RECIPE_TYPE.getId(recipeType)?.path}/$path",
            RebornRecipe.Default(
                recipeType,
                inputs.toList(),
                listOf(ItemStack(output)),
                power,
                20 * time,
            ),
        )
    }

    private fun blastFurnace(
        exporter: Exporter,
        output: ItemConvertible,
        power: Int,
        time: Int,
        heat: Int,
        vararg inputs: SizedIngredient,
        path: String = CraftingRecipeJsonBuilder.getItemId(output).path,
    ) {
        exporter(
            "blast_furnace/$path",
            BlastFurnaceRecipe(
                ModRecipes.BLAST_FURNACE,
                inputs.toList(),
                listOf(ItemStack(output)),
                power,
                20 * time,
                heat,
            ),
        )
    }
}
