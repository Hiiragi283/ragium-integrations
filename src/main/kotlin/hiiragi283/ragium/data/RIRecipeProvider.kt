package hiiragi283.ragium.data

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.data.HTMachineRecipeJsonBuilder
import hiiragi283.ragium.api.data.HTShapelessRecipeJsonBuilder
import hiiragi283.ragium.api.data.HTStonecuttingRecipeJsonBuilder
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.tags.RagiumItemTags
import hiiragi283.ragium.common.RagiumContents
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
import net.minecraft.util.Identifier
import rearth.oritech.init.ItemContent
import vazkii.patchouli.common.item.ItemModBook
import java.util.concurrent.CompletableFuture

class RIRecipeProvider(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricRecipeProvider(output, completableFuture) {
    override fun getRecipeIdentifier(identifier: Identifier): Identifier = RagiumAPI.id(identifier.path)

    override fun generate(exporter: RecipeExporter) {
        // patchouli
        HTShapelessRecipeJsonBuilder
            .create(ItemModBook.forBook(RagiumAPI.id("ragi_wiki")))
            .input(Items.BOOK)
            .input(RagiumContents.RawMaterials.CRUDE_RAGINITE)
            .input(ConventionalItemTags.IRON_INGOTS)
            .offerTo(withConditions(exporter, ResourceConditions.allModsLoaded("patchouli")))
        // energized power
        val energizedPower: RecipeExporter =
            withConditions(exporter, ResourceConditions.allModsLoaded("energizedpower"))
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ConventionalItemTags.IRON_INGOTS)
            .itemInput(RagiumItemTags.SILICON)
            .itemInput(ConventionalItemTags.COPPER_INGOTS)
            .itemOutput(EPItems.REDSTONE_ALLOY_INGOT)
            .offerTo(energizedPower, EPItems.REDSTONE_ALLOY_INGOT)
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ConventionalItemTags.IRON_INGOTS, 3)
            .itemInput(ConventionalItemTags.COPPER_INGOTS, 3)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.TIN, 3)
            .itemOutput(EPItems.ADVANCED_ALLOY_INGOT)
            .offerTo(energizedPower, EPItems.ADVANCED_ALLOY_INGOT)
        // oritech
        val oritech: RecipeExporter = withConditions(exporter, ResourceConditions.allModsLoaded("oritech"))
        HTStonecuttingRecipeJsonBuilder.registerExchange(
            oritech,
            RagiumItems.PLASTIC_PLATE,
            ItemContent.PLASTIC_SHEET,
        )
        HTStonecuttingRecipeJsonBuilder.registerExchange(
            oritech,
            RagiumItems.POLYMER_RESIN,
            ItemContent.POLYMER_RESIN,
        )
    }
}
