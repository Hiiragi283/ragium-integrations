package hiiragi283.ragium.data

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.common.RagiumContents
import hiiragi283.ragium.integration.patchouli.HTBookEntry
import hiiragi283.ragium.integration.patchouli.page.HTRecipeBookPage
import hiiragi283.ragium.integration.patchouli.page.HTSpotlightBookPage
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.recipe.RecipeType
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class RIEnglishPatchouliEntryProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    RIPatchouliEntryProvider(output, registriesFuture, "en_us") {
    override fun getName(): String = "Patchouli"

    override fun configure(consumer: BiConsumer<Identifier, HTBookEntry>, wrapperLookup: RegistryWrapper.WrapperLookup) {
        register(
            consumer,
            "tier1",
            "crude_raginite",
            "Crude Raginite",
            RagiumContents.RawMaterials.CRUDE_RAGINITE,
            HTSpotlightBookPage(RagiumContents.Ores.CRUDE_RAGINITE, "Found in Overworld, between y = -48 ~ y = 48"),
            HTSpotlightBookPage(RagiumContents.RawMaterials.CRUDE_RAGINITE, "Drops 1 ~ 3 from Crude Raginite Ore"),
        )
        register(
            consumer,
            "tier1",
            "ragi_alloy",
            "Ragi-Alloy",
            RagiumContents.Ingots.RAGI_ALLOY,
            HTSpotlightBookPage(RagiumContents.Ingots.RAGI_ALLOY, "Tier 1 Main Material"),
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
}
