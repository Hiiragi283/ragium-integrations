package hiiragi283.ragium.integration

import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.data.recipe.HTMachineRecipeJsonBuilder
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTMaterialRegistry
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.util.TriConsumer
import hiiragi283.ragium.common.init.RagiumMachineKeys
import hiiragi283.ragium.common.init.RagiumMaterialKeys
import me.jddev0.ep.block.EPBlocks
import me.jddev0.ep.item.EPItems
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.item.Item
import net.minecraft.util.Rarity

object RagiumEPPlugin : RagiumPlugin {
    @JvmField
    val ADVANCED_ALLOY: HTMaterialKey = HTMaterialKey.of("advanced_alloy")

    @JvmField
    val REDSTONE_ALLOY: HTMaterialKey = HTMaterialKey.of("redstone_alloy")

    @JvmField
    val ENERGIZED_COPPER: HTMaterialKey = HTMaterialKey.of("energized_copper")

    @JvmField
    val ENERGIZED_GOLD: HTMaterialKey = HTMaterialKey.of("energized_gold")

    //    RagiumPlugin    //

    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isModLoaded("energizedpower")

    override fun registerMaterial(consumer: TriConsumer<HTMaterialKey, HTMaterialKey.Type, Rarity>) {
        consumer.accept(ADVANCED_ALLOY, HTMaterialKey.Type.ALLOY, Rarity.RARE)
        consumer.accept(REDSTONE_ALLOY, HTMaterialKey.Type.ALLOY, Rarity.UNCOMMON)

        consumer.accept(ENERGIZED_COPPER, HTMaterialKey.Type.METAL, Rarity.UNCOMMON)
        consumer.accept(ENERGIZED_GOLD, HTMaterialKey.Type.METAL, Rarity.RARE)
    }

    override fun bindMaterialToItem(consumer: TriConsumer<HTTagPrefix, HTMaterialKey, Item>) {
        consumer.accept(HTTagPrefix.DEEP_ORE, RagiumMaterialKeys.TIN, EPBlocks.DEEPSLATE_TIN_ORE_ITEM)

        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.COPPER, EPItems.COPPER_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.GOLD, EPItems.GOLD_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.IRON, EPItems.IRON_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.TIN, EPItems.TIN_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.WOOD, EPItems.SAWDUST)

        consumer.accept(HTTagPrefix.GEAR, RagiumMaterialKeys.IRON, EPItems.IRON_GEAR)

        consumer.accept(HTTagPrefix.INGOT, RagiumMaterialKeys.SILICON, EPItems.SILICON)
        consumer.accept(HTTagPrefix.INGOT, RagiumMaterialKeys.STEEL, EPItems.STEEL_INGOT)
        consumer.accept(HTTagPrefix.INGOT, RagiumMaterialKeys.TIN, EPItems.TIN_INGOT)
        consumer.accept(HTTagPrefix.INGOT, ADVANCED_ALLOY, EPItems.ADVANCED_ALLOY_INGOT)
        consumer.accept(HTTagPrefix.INGOT, REDSTONE_ALLOY, EPItems.REDSTONE_ALLOY_INGOT)
        consumer.accept(HTTagPrefix.INGOT, ENERGIZED_COPPER, EPItems.ENERGIZED_COPPER_INGOT)
        consumer.accept(HTTagPrefix.INGOT, ENERGIZED_GOLD, EPItems.ENERGIZED_GOLD_INGOT)

        consumer.accept(HTTagPrefix.NUGGET, RagiumMaterialKeys.TIN, EPItems.TIN_NUGGET)

        consumer.accept(HTTagPrefix.ORE, RagiumMaterialKeys.TIN, EPBlocks.TIN_ORE_ITEM)

        consumer.accept(HTTagPrefix.PLATE, RagiumMaterialKeys.COPPER, EPItems.COPPER_PLATE)
        consumer.accept(HTTagPrefix.PLATE, RagiumMaterialKeys.GOLD, EPItems.GOLD_PLATE)
        consumer.accept(HTTagPrefix.PLATE, RagiumMaterialKeys.IRON, EPItems.IRON_PLATE)
        consumer.accept(HTTagPrefix.PLATE, RagiumMaterialKeys.TIN, EPItems.TIN_PLATE)
        consumer.accept(HTTagPrefix.PLATE, ADVANCED_ALLOY, EPItems.ADVANCED_ALLOY_PLATE)
        consumer.accept(HTTagPrefix.PLATE, ENERGIZED_COPPER, EPItems.ENERGIZED_COPPER_PLATE)
        consumer.accept(HTTagPrefix.PLATE, ENERGIZED_GOLD, EPItems.ENERGIZED_GOLD_PLATE)

        consumer.accept(HTTagPrefix.RAW_MATERIAL, RagiumMaterialKeys.TIN, EPItems.RAW_TIN)

        consumer.accept(HTTagPrefix.ROD, RagiumMaterialKeys.IRON, EPItems.IRON_ROD)

        consumer.accept(HTTagPrefix.STORAGE_BLOCK, RagiumMaterialKeys.SILICON, EPBlocks.SILICON_BLOCK_ITEM)
        consumer.accept(HTTagPrefix.STORAGE_BLOCK, RagiumMaterialKeys.TIN, EPBlocks.TIN_BLOCK_ITEM)
    }

    override fun registerRuntimeRecipes(
        exporter: RecipeExporter,
        key: HTMaterialKey,
        entry: HTMaterialRegistry.Entry,
        helper: RagiumPlugin.RecipeHelper,
    ) {
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ConventionalItemTags.IRON_INGOTS)
            .itemInput(EPItems.SILICON)
            .itemInput(ConventionalItemTags.COPPER_INGOTS)
            .itemOutput(EPItems.REDSTONE_ALLOY_INGOT)
            .offerTo(exporter, EPItems.REDSTONE_ALLOY_INGOT)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ConventionalItemTags.IRON_INGOTS, 3)
            .itemInput(ConventionalItemTags.COPPER_INGOTS, 3)
            .itemInput(HTTagPrefix.INGOT, RagiumMaterialKeys.TIN, 3)
            .itemOutput(EPItems.ADVANCED_ALLOY_INGOT)
            .offerTo(exporter, EPItems.ADVANCED_ALLOY_INGOT)
    }
}