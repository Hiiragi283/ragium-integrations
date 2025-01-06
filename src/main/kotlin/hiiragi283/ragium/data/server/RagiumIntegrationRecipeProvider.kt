package hiiragi283.ragium.data.server

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.data.HTMachineRecipeJsonBuilder
import hiiragi283.ragium.api.data.HTShapelessRecipeJsonBuilder
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.recipe.HTItemIngredient
import hiiragi283.ragium.api.tags.RagiumItemTags
import hiiragi283.ragium.common.init.RagiumItems
import hiiragi283.ragium.common.init.RagiumMachineKeys
import hiiragi283.ragium.common.init.RagiumMaterialKeys
import hiiragi283.ragium.integration.RagiumEPPlugin
import me.jddev0.ep.item.EPItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.block.Block
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.item.Items
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import net.minecraft.util.Identifier
import rearth.oritech.init.BlockContent
import rearth.oritech.init.ItemContent
import rearth.oritech.init.TagContent
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
            .offerTo(exporter, EPItems.REDSTONE_ALLOY_INGOT, "_with_ep")
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.STEEL, 3)
            .itemInput(ConventionalItemTags.COPPER_INGOTS, 3)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.TIN, 3)
            .itemOutput(EPItems.ADVANCED_ALLOY_INGOT)
            .offerTo(exporter, EPItems.ADVANCED_ALLOY_INGOT, "_with_ep")
        // cable insulator
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER)
            .itemInput(ItemTags.WOOL)
            .itemInput(Items.SHEARS, consumeType = HTItemIngredient.ConsumeType.DAMAGE)
            .itemOutput(EPItems.CABLE_INSULATOR, 18)
            .offerTo(exporter, EPItems.CABLE_INSULATOR, "_with_ep")
        // circuits
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER)
            .itemInput(RagiumItems.Circuits.PRIMITIVE)
            .itemInput(HTTagPrefix.WIRE, RagiumMaterialKeys.COPPER, 4)
            .itemOutput(EPItems.BASIC_CIRCUIT)
            .offerTo(exporter, EPItems.BASIC_CIRCUIT, "_with_ep")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER, HTMachineTier.BASIC)
            .itemInput(RagiumItems.Circuits.BASIC)
            .itemInput(HTTagPrefix.WIRE, RagiumEPPlugin.ENERGIZED_COPPER, 4)
            .itemOutput(EPItems.ADVANCED_CIRCUIT)
            .offerTo(exporter, EPItems.ADVANCED_CIRCUIT, "_with_ep")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER, HTMachineTier.ADVANCED)
            .itemInput(RagiumItems.Circuits.ADVANCED)
            .itemInput(HTTagPrefix.WIRE, RagiumEPPlugin.ENERGIZED_GOLD, 4)
            .itemOutput(EPItems.PROCESSING_UNIT)
            .offerTo(exporter, EPItems.PROCESSING_UNIT, "_with_ep")
    }

    //    Oritech    //

    private fun generateOT(exporter: RecipeExporter) {
        // alloy
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE, HTMachineTier.BASIC)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.NICKEL)
            .itemInput(HTTagPrefix.GEM, RagiumMaterialKeys.DIAMOND)
            .itemOutput(ItemContent.ADAMANT_INGOT)
            .offerTo(exporter, ItemContent.ADAMANT_INGOT, "_with_oritech")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE, HTMachineTier.ADVANCED)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.PLATINUM)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.NETHERITE)
            .itemOutput(ItemContent.DURATIUM_INGOT)
            .offerTo(exporter, ItemContent.DURATIUM_INGOT, "_with_oritech")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE, HTMachineTier.ADVANCED)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.NICKEL)
            .itemInput(ItemContent.FLUXITE)
            .itemOutput(ItemContent.ENERGITE_INGOT)
            .offerTo(exporter, ItemContent.ENERGITE_INGOT, "_with_oritech")
        // reinforced plating
        mapOf(
            RagiumMaterialKeys.COPPER to BlockContent.MACHINE_PLATING_BLOCK,
            RagiumMaterialKeys.IRON to BlockContent.IRON_PLATING_BLOCK,
            RagiumMaterialKeys.NICKEL to BlockContent.NICKEL_PLATING_BLOCK,
        ).forEach { (material: HTMaterialKey, block: Block) ->
            HTMachineRecipeJsonBuilder
                .create(RagiumMachineKeys.ASSEMBLER)
                .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.STEEL, 2)
                .itemInput(HTTagPrefix.INGOT, material)
                .itemInput(RagiumItems.Plastics.PRIMITIVE.tagKey)
                .itemOutput(block, 8)
                .offerTo(exporter, block, "_with_oritech")
        }
        // industrial glass
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER)
            .itemInput(ConventionalItemTags.GLASS_BLOCKS, 2)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.STEEL, 2)
            .itemInput(TagContent.MACHINE_PLATING)
            .itemOutput(BlockContent.INDUSTRIAL_GLASS_BLOCK, 4)
            .offerTo(exporter, BlockContent.INDUSTRIAL_GLASS_BLOCK, "_with_oritech")
    }
}
