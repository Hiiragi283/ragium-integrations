package hiiragi283.ragium.integration.patchouli

import com.mojang.blaze3d.systems.RenderSystem
import hiiragi283.ragium.api.recipe.HTItemIngredient
import hiiragi283.ragium.api.recipe.HTItemResult
import net.minecraft.client.gui.DrawContext
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.recipe.input.RecipeInput
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import net.minecraft.world.World
import vazkii.patchouli.client.book.BookContentsBuilder
import vazkii.patchouli.client.book.BookEntry
import vazkii.patchouli.client.book.gui.GuiBook
import vazkii.patchouli.client.book.page.abstr.PageDoubleRecipeRegistry

class HTCustomCraftingPage : PageDoubleRecipeRegistry<HTCustomCraftingPage.DummyRecipe>(object : RecipeType<DummyRecipe> {}) {
    companion object {
        private val registry: MutableMap<Identifier, DummyRecipe> = mutableMapOf()

        @JvmStatic
        fun register(
            id: Identifier,
            input: HTItemIngredient,
            icon: HTItemIngredient,
            output: HTItemResult,
        ) {
            registry[id] = DummyRecipe(input, icon, output)
        }
    }

    //    PageDoubleRecipeRegistry    //

    @Transient
    private var loadedId: Identifier? = null

    override fun drawRecipe(
        graphics: DrawContext,
        recipe: DummyRecipe,
        recipeX: Int,
        recipeY: Int,
        mouseX: Int,
        mouseY: Int,
        second: Boolean,
    ) {
        RenderSystem.enableBlend()
        graphics.drawTexture(book.craftingTexture, recipeX, recipeY, 11f, 71f, 95, 24, 128, 256)
        parent.drawCenteredStringNoShadow(
            graphics,
            getTitle(second).asOrderedText(),
            GuiBook.PAGE_WIDTH / 2,
            recipeY - 10,
            book.headerColor,
        )

        parent.renderIngredient(graphics, recipeX + 4, recipeY + 4, mouseX, mouseY, recipe.input)
        parent.renderIngredient(graphics, recipeX + 40, recipeY + 4, mouseX, mouseY, recipe.icon)
        parent.renderResult(graphics, recipeX + 76, recipeY + 4, mouseX, mouseY, recipe.output)
    }

    override fun loadRecipe(
        level: World?,
        builder: BookContentsBuilder,
        entry: BookEntry,
        res: Identifier?,
    ): DummyRecipe? {
        if (level == null || res == null) return null
        if (loadedId == res) return null
        val recipe: DummyRecipe = registry[res] ?: return null
        entry.addRelevantStack(builder, recipe.getResult(level.registryManager), pageNum)
        loadedId = res
        return recipe
    }

    override fun getRecipeOutput(level: World?, recipe: DummyRecipe?): ItemStack =
        level?.registryManager?.let { recipe?.getResult(it) } ?: ItemStack.EMPTY

    override fun getRecipeHeight(): Int = 45

    //    Recipe    //

    class DummyRecipe(val input: HTItemIngredient, val icon: HTItemIngredient, val output: HTItemResult) : Recipe<RecipeInput> {
        override fun matches(input: RecipeInput, world: World): Boolean = false

        override fun craft(input: RecipeInput, lookup: RegistryWrapper.WrapperLookup): ItemStack = output.stack

        override fun fits(width: Int, height: Int): Boolean = true

        override fun getResult(registriesLookup: RegistryWrapper.WrapperLookup?): ItemStack = output.stack

        override fun getSerializer(): RecipeSerializer<*>? = null

        override fun getType(): RecipeType<*>? = null
    }
}
