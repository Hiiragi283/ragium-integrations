package hiiragi283.ragium.integration.patchouli

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isClientEnv
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.api.recipe.HTItemIngredient
import hiiragi283.ragium.api.recipe.HTItemResult
import hiiragi283.ragium.common.RagiumContents
import net.minecraft.registry.tag.ItemTags
import net.minecraft.util.Identifier
import vazkii.patchouli.client.book.BookPage
import vazkii.patchouli.client.book.ClientBookRegistry

object RagiumPatchouliPlugin : RagiumPlugin {
    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isClientEnv() && isModLoaded("patchouli")

    override fun afterRagiumInit(instance: RagiumAPI) {
        addPageType<HTMachineRecipePage>(RagiumAPI.MOD_ID, "machine_recipe")
        addPageType<HTCustomCraftingPage>(RagiumAPI.MOD_ID, "custom_recipe")

        HTCustomCraftingPage.register(
            RagiumAPI.id("crude_raginite"),
            HTItemIngredient.of(RagiumContents.Ores.NETHER_RAGINITE),
            HTItemIngredient.of(ItemTags.PICKAXES),
            HTItemResult(RagiumContents.RawMaterials.RAGINITE, 3),
        )
    }

    @JvmStatic
    private inline fun <reified T : BookPage> addPageType(modId: String, name: String) {
        ClientBookRegistry.INSTANCE.pageTypes[Identifier.of(modId, name)] = T::class.java
    }
}
