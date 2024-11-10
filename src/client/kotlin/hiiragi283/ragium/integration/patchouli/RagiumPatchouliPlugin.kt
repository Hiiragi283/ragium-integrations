package hiiragi283.ragium.integration.patchouli

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isClientEnv
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.common.RagiumContents
import net.minecraft.item.Items
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
            RagiumAPI.id("raw_raginite"),
            RagiumContents.Ores.NETHER_RAGINITE.prefixedTagKey,
            Items.WOODEN_PICKAXE.defaultStack,
            RagiumContents.RawMaterials.RAGINITE
                .asItem()
                .defaultStack,
        )

        HTCustomCraftingPage.register(
            RagiumAPI.id("raginite_dust"),
            RagiumContents.Dusts.CRUDE_RAGINITE,
            Items.CAULDRON.defaultStack,
            RagiumContents.Dusts.RAGINITE
                .asItem()
                .defaultStack,
        )
    }

    @JvmStatic
    private inline fun <reified T : BookPage> addPageType(modId: String, name: String) {
        ClientBookRegistry.INSTANCE.pageTypes[Identifier.of(modId, name)] = T::class.java
    }
}
