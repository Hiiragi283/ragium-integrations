package hiiragi283.ragium.data.client

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.extension.registryEntry
import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.common.init.RagiumItems
import hiiragi283.ragium.common.init.RagiumMaterialKeys
import hiiragi283.ragium.integration.patchouli.HTBookEntry
import hiiragi283.ragium.integration.patchouli.HTPatchouliCategory
import hiiragi283.ragium.integration.patchouli.page.HTBookPage
import hiiragi283.ragium.integration.patchouli.page.HTSpotlightBookPage
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider
import net.minecraft.data.DataOutput
import net.minecraft.item.ItemConvertible
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

    private fun registerPage(
        consumer: BiConsumer<Identifier, HTBookEntry>,
        category: HTPatchouliCategory,
        path: String,
        name: String,
        icon: ItemConvertible,
        advancementId: Identifier?,
        vararg pages: HTBookPage<*, *>,
    ) {
        val entry: HTBookEntry = HTBookEntry.of(
            name,
            icon.registryEntry,
            category.id,
            pages.toList(),
            advancementId,
        ) ?: return
        consumer.accept(RagiumAPI.id(path).withPrefixedPath("${category.asString()}/"), entry)
    }

    override fun configure(consumer: BiConsumer<Identifier, HTBookEntry>, wrapperLookup: RegistryWrapper.WrapperLookup) {
        registerMaterials(consumer, wrapperLookup)
    }

    //    Food    //

    //    Machine    //

    //    Material    //

    private fun registerMaterials(consumer: BiConsumer<Identifier, HTBookEntry>, wrapperLookup: RegistryWrapper.WrapperLookup) {
        // tier 1
        registerMaterialPage(
            consumer,
            RagiumMaterialKeys.CRUDE_RAGINITE,
            RagiumItems.Dusts.CRUDE_RAGINITE,
            RagiumAPI.id("progress/root"),
            HTSpotlightBookPage(
                RagiumItems.Dusts.CRUDE_RAGINITE,
                "Crude Raginite is the most fundamental material in Ragium",
            ),
        )
        // tier 2
        registerMaterialPage(
            consumer,
            RagiumMaterialKeys.RAGINITE,
            RagiumItems.Dusts.RAGINITE,
            RagiumAPI.id("progress/ragi_steel"),
            HTSpotlightBookPage(
                RagiumItems.Dusts.RAGINITE,
                "Raginite is common material",
            ),
        )
        // tier 3
        registerMaterialPage(
            consumer,
            RagiumMaterialKeys.RAGI_CRYSTAL,
            RagiumItems.Gems.RAGI_CRYSTAL,
            RagiumAPI.id("progress/ragi_crystal"),
            HTSpotlightBookPage(
                RagiumItems.Gems.RAGI_CRYSTAL,
                "Ragi-Crystal is crystallized form of Raginite",
            ),
        )
        registerMaterialPage(
            consumer,
            RagiumMaterialKeys.REFINED_RAGI_STEEL,
            RagiumItems.Ingots.REFINED_RAGI_STEEL,
            RagiumAPI.id("progress/refined_ragi_steel"),
            HTSpotlightBookPage(
                RagiumItems.Ingots.REFINED_RAGI_STEEL,
                "Refined Ragi-Steel is steel material for Tier 3",
            ),
        )
        // tier 4
        registerMaterialPage(
            consumer,
            RagiumMaterialKeys.RAGIUM,
            RagiumItems.Ingots.RAGIUM,
            RagiumAPI.id("progress/ragium"),
            HTSpotlightBookPage(
                RagiumItems.Ingots.RAGIUM,
                "Ragium is perfectly purified material",
            ),
        )
    }

    private fun registerMaterialPage(
        consumer: BiConsumer<Identifier, HTBookEntry>,
        material: HTMaterialKey,
        icon: ItemConvertible,
        advancementId: Identifier?,
        vararg pages: HTBookPage<*, *>,
    ) {
        registerPage(
            consumer,
            HTPatchouliCategory.MATERIAL,
            material.name,
            material.translationKey,
            icon,
            advancementId,
            *pages,
        )
    }

    //    Petrochemistry    //

    //    Utility    //
}
