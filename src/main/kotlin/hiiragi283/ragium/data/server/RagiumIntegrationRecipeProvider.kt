package hiiragi283.ragium.data.server

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.data.HTMachineRecipeJsonBuilder
import hiiragi283.ragium.api.data.HTShapelessRecipeJsonBuilder
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.recipe.HTItemIngredient
import hiiragi283.ragium.api.tags.RagiumItemTags
import hiiragi283.ragium.common.init.RagiumItems
import hiiragi283.ragium.common.init.RagiumMachineKeys
import hiiragi283.ragium.common.init.RagiumMaterialKeys
import me.jddev0.ep.item.EPItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.item.Items
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import net.minecraft.util.Identifier
import vazkii.patchouli.common.item.ItemModBook
import java.util.concurrent.CompletableFuture

class RagiumIntegrationRecipeProvider(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricRecipeProvider(output, completableFuture) {
    override fun getRecipeIdentifier(identifier: Identifier): Identifier = RagiumAPI.Companion.id(identifier.path)

    override fun generate(exporter: RecipeExporter) {
        HTShapelessRecipeJsonBuilder
            .create(ItemModBook.forBook(RagiumAPI.id("ragi_wiki")))
            .input(Items.BOOK)
            .input(RagiumItems.RawMaterials.CRUDE_RAGINITE)
            .input(ConventionalItemTags.IRON_INGOTS)
            .unlockedBy(RagiumItems.RawMaterials.CRUDE_RAGINITE)
            .offerTo(withConditions(exporter, ResourceConditions.allModsLoaded("patchouli")))

        generateEP(withConditions(exporter, ResourceConditions.allModsLoaded("energizedpower")))
        generateOT(withConditions(exporter, ResourceConditions.allModsLoaded("oritech")))
    }

    //    Energized Power    //

    private fun generateEP(exporter: RecipeExporter) {
        // alloys
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.TIN)
            .itemInput(RagiumItemTags.SILICON)
            .itemInput(ConventionalItemTags.REDSTONE_DUSTS, 2)
            .itemOutput(EPItems.REDSTONE_ALLOY_INGOT)
            .offerTo(exporter, EPItems.REDSTONE_ALLOY_INGOT)
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.STEEL, 3)
            .itemInput(ConventionalItemTags.COPPER_INGOTS, 3)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.TIN, 3)
            .itemOutput(EPItems.ADVANCED_ALLOY_INGOT)
            .offerTo(exporter, EPItems.ADVANCED_ALLOY_INGOT)
        // cable insulator
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER)
            .itemInput(ItemTags.WOOL)
            .itemInput(Items.SHEARS, consumeType = HTItemIngredient.ConsumeType.DAMAGE)
            .itemOutput(EPItems.CABLE_INSULATOR, 18)
            .offerTo(exporter, EPItems.CABLE_INSULATOR)
    }

    //    Oritech    //

    private fun generateOT(exporter: RecipeExporter) {
    }
}
