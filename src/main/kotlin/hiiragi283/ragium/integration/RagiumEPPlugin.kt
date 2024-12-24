package hiiragi283.ragium.integration

import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTMaterialType
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.util.TriConsumer
import hiiragi283.ragium.common.init.RagiumMaterialKeys
import me.jddev0.ep.block.EPBlocks
import me.jddev0.ep.item.EPItems
import net.minecraft.item.ItemConvertible
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

    override fun registerMaterial(helper: RagiumPlugin.MaterialHelper) {
        helper.register(ADVANCED_ALLOY, HTMaterialType.ALLOY, Rarity.RARE)
        helper.register(REDSTONE_ALLOY, HTMaterialType.ALLOY, Rarity.UNCOMMON)

        helper.register(ENERGIZED_COPPER, HTMaterialType.METAL, Rarity.UNCOMMON)
        helper.register(ENERGIZED_GOLD, HTMaterialType.METAL, Rarity.RARE)
    }

    override fun bindMaterialToItem(consumer: TriConsumer<HTTagPrefix, HTMaterialKey, ItemConvertible>) {
        consumer.accept(HTTagPrefix.ORE, RagiumMaterialKeys.TIN, EPBlocks.DEEPSLATE_TIN_ORE_ITEM)

        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.COPPER, EPItems.COPPER_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.GOLD, EPItems.GOLD_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.IRON, EPItems.IRON_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.TIN, EPItems.TIN_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.WOOD, EPItems.SAWDUST)

        consumer.accept(HTTagPrefix.GEAR, RagiumMaterialKeys.IRON, EPItems.IRON_GEAR)

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

        consumer.accept(HTTagPrefix.STORAGE_BLOCK, RagiumMaterialKeys.TIN, EPBlocks.TIN_BLOCK_ITEM)
    }
}
