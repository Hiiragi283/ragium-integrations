package hiiragi283.ragium.data.client

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.common.init.RagiumBlocks
import hiiragi283.ragium.common.init.RagiumItems
import hiiragi283.ragium.common.init.RagiumTranslationKeys
import hiiragi283.ragium.integration.patchouli.HTBookEntry
import hiiragi283.ragium.integration.patchouli.HTPatchouliCategory
import hiiragi283.ragium.integration.patchouli.page.HTBookPage
import hiiragi283.ragium.integration.patchouli.page.HTRecipeBookPage
import hiiragi283.ragium.integration.patchouli.page.HTSpotlightBookPage
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider
import net.minecraft.data.DataOutput
import net.minecraft.item.ItemConvertible
import net.minecraft.recipe.RecipeType
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class RIPatchouliPageProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricCodecDataProvider<HTBookEntry>(
        output,
        registriesFuture,
        DataOutput.OutputType.RESOURCE_PACK,
        "patchouli_books/ragi_wiki/en_us/entries",
        HTBookEntry.CODEC,
    ) {
    override fun getName(): String = "Patchouli/Pages"

    override fun configure(consumer: BiConsumer<Identifier, HTBookEntry>, wrapperLookup: RegistryWrapper.WrapperLookup) {
        registerPage(
            consumer,
            HTPatchouliCategory.TIER_1,
            "crude_raginite",
            RagiumTranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE,
            RagiumItems.RawMaterials.CRUDE_RAGINITE,
            HTSpotlightBookPage(
                RagiumBlocks.Ores.CRUDE_RAGINITE,
                RagiumTranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE0,
            ),
            HTSpotlightBookPage(
                RagiumItems.RawMaterials.CRUDE_RAGINITE,
                RagiumTranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE1,
            ),
        )
        registerPage(
            consumer,
            HTPatchouliCategory.TIER_1,
            "ragi_alloy",
            RagiumTranslationKeys.PATCHOULI_RAGI_ALLOY,
            RagiumItems.Ingots.RAGI_ALLOY,
            HTSpotlightBookPage(
                RagiumItems.Ingots.RAGI_ALLOY,
                RagiumTranslationKeys.PATCHOULI_RAGI_ALLOY0,
            ),
            HTRecipeBookPage(
                RecipeType.CRAFTING,
                RagiumAPI.id("shaped/ragi_alloy_compound"),
                RagiumAPI.id("shaped/ragi_alloy_compound_1"),
            ),
            HTRecipeBookPage(
                RecipeType.SMELTING,
                RagiumAPI.id("smelting/ragi_alloy_ingot"),
            ),
            HTRecipeBookPage(
                RecipeType.BLASTING,
                RagiumAPI.id("blasting/ragi_alloy_ingot"),
            ),
        )
    }

    private fun registerPage(
        consumer: BiConsumer<Identifier, HTBookEntry>,
        category: HTPatchouliCategory,
        path: String,
        name: String,
        icon: ItemConvertible,
        vararg pages: HTBookPage<*, *>,
    ) {
        val entry: HTBookEntry = HTBookEntry.of(
            name,
            Registries.ITEM.getEntry(icon.asItem()),
            category.id,
            pages.toList(),
        ) ?: return
        consumer.accept(RagiumAPI.id(path).withPrefixedPath("${category.asString()}/"), entry)
    }
}
