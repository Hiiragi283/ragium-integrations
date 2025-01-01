package hiiragi283.ragium.integration

import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTMaterialPropertyKeys
import hiiragi283.ragium.api.material.HTMaterialType
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.util.TriConsumer
import hiiragi283.ragium.common.init.RagiumMaterialKeys
import net.minecraft.item.ItemConvertible
import net.minecraft.util.Rarity
import rearth.oritech.init.BlockContent
import rearth.oritech.init.ItemContent

object RagiumOritechPlugin : RagiumPlugin {
    @JvmField
    val ADAMANT: HTMaterialKey = HTMaterialKey.of("adamant")

    @JvmField
    val BIOSTEEL: HTMaterialKey = HTMaterialKey.of("biosteel")

    @JvmField
    val DURATIUM: HTMaterialKey = HTMaterialKey.of("duratium")

    @JvmField
    val ENERGITE: HTMaterialKey = HTMaterialKey.of("energite")

    @JvmField
    val PROMETHEUM: HTMaterialKey = HTMaterialKey.of("prometheum")

    //    RagiumPlugin    //

    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isModLoaded("oritech")

    override fun registerMaterial(helper: RagiumPlugin.MaterialHelper) {
        // alloy
        helper.register(ADAMANT, HTMaterialType.ALLOY, Rarity.RARE)
        helper.register(BIOSTEEL, HTMaterialType.ALLOY, Rarity.UNCOMMON)
        helper.register(DURATIUM, HTMaterialType.ALLOY, Rarity.RARE)
        helper.register(ENERGITE, HTMaterialType.ALLOY, Rarity.RARE)
        helper.register(PROMETHEUM, HTMaterialType.ALLOY, Rarity.EPIC)
    }

    override fun setupMaterialProperties(helper: RagiumPlugin.PropertyHelper<HTMaterialKey>) {
        helper.modify(ADAMANT, DURATIUM, ENERGITE, PROMETHEUM) {
            add(HTMaterialPropertyKeys.DISABLE_BLOCK_CRAFTING)
        }
    }

    override fun bindMaterialToItem(consumer: TriConsumer<HTTagPrefix, HTMaterialKey, ItemConvertible>) {
        consumer.accept(HTTagPrefix.STORAGE_BLOCK, RagiumMaterialKeys.ELECTRUM, BlockContent.ELECTRUM_BLOCK)
        consumer.accept(HTTagPrefix.STORAGE_BLOCK, RagiumMaterialKeys.NICKEL, BlockContent.NICKEL_BLOCK)
        consumer.accept(HTTagPrefix.STORAGE_BLOCK, RagiumMaterialKeys.PLATINUM, BlockContent.PLATINUM_BLOCK)
        consumer.accept(HTTagPrefix.STORAGE_BLOCK, RagiumMaterialKeys.STEEL, BlockContent.STEEL_BLOCK)
        consumer.accept(HTTagPrefix.STORAGE_BLOCK, ADAMANT, BlockContent.ADAMANT_BLOCK)
        consumer.accept(HTTagPrefix.STORAGE_BLOCK, BIOSTEEL, BlockContent.BIOSTEEL_BLOCK)
        consumer.accept(HTTagPrefix.STORAGE_BLOCK, DURATIUM, BlockContent.DURATIUM_BLOCK)
        consumer.accept(HTTagPrefix.STORAGE_BLOCK, ENERGITE, BlockContent.ENERGITE_BLOCK)

        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.COAL, ItemContent.COAL_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.COPPER, ItemContent.COPPER_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.ELECTRUM, ItemContent.ELECTRUM_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.GOLD, ItemContent.GOLD_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.IRON, ItemContent.IRON_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.NICKEL, ItemContent.NICKEL_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.PLATINUM, ItemContent.PLATINUM_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.PLUTONIUM, ItemContent.PLUTONIUM_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.URANIUM, ItemContent.URANIUM_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.STEEL, ItemContent.STEEL_DUST)
        consumer.accept(HTTagPrefix.DUST, ADAMANT, ItemContent.ADAMANT_DUST)
        consumer.accept(HTTagPrefix.DUST, BIOSTEEL, ItemContent.BIOSTEEL_DUST)
        consumer.accept(HTTagPrefix.DUST, DURATIUM, ItemContent.DURATIUM_DUST)
        consumer.accept(HTTagPrefix.DUST, ENERGITE, ItemContent.ENERGITE_DUST)

        consumer.accept(HTTagPrefix.GEM, RagiumMaterialKeys.URANIUM, ItemContent.URANIUM_GEM)

        consumer.accept(HTTagPrefix.INGOT, RagiumMaterialKeys.ELECTRUM, ItemContent.ELECTRUM_INGOT)
        consumer.accept(HTTagPrefix.INGOT, RagiumMaterialKeys.NICKEL, ItemContent.NICKEL_INGOT)
        consumer.accept(HTTagPrefix.INGOT, RagiumMaterialKeys.PLATINUM, ItemContent.PLATINUM_INGOT)
        consumer.accept(HTTagPrefix.INGOT, RagiumMaterialKeys.STEEL, ItemContent.STEEL_INGOT)
        consumer.accept(HTTagPrefix.INGOT, ADAMANT, ItemContent.ADAMANT_INGOT)
        consumer.accept(HTTagPrefix.INGOT, BIOSTEEL, ItemContent.BIOSTEEL_INGOT)
        consumer.accept(HTTagPrefix.INGOT, DURATIUM, ItemContent.DURATIUM_INGOT)
        consumer.accept(HTTagPrefix.INGOT, ENERGITE, ItemContent.ENERGITE_INGOT)
        consumer.accept(HTTagPrefix.INGOT, PROMETHEUM, ItemContent.PROMETHEUM_INGOT)

        consumer.accept(HTTagPrefix.ORE, RagiumMaterialKeys.NICKEL, BlockContent.DEEPSLATE_NICKEL_ORE)
        consumer.accept(HTTagPrefix.ORE, RagiumMaterialKeys.NICKEL, BlockContent.NICKEL_ORE)
        consumer.accept(HTTagPrefix.ORE, RagiumMaterialKeys.PLATINUM, BlockContent.DEEPSLATE_PLATINUM_ORE)
        consumer.accept(HTTagPrefix.ORE, RagiumMaterialKeys.PLATINUM, BlockContent.ENDSTONE_PLATINUM_ORE)
        consumer.accept(HTTagPrefix.ORE, RagiumMaterialKeys.URANIUM, BlockContent.DEEPSLATE_URANIUM_ORE)

        consumer.accept(HTTagPrefix.RAW_MATERIAL, RagiumMaterialKeys.NICKEL, ItemContent.RAW_NICKEL)
        consumer.accept(HTTagPrefix.RAW_MATERIAL, RagiumMaterialKeys.PLATINUM, ItemContent.RAW_PLATINUM)
        consumer.accept(HTTagPrefix.RAW_MATERIAL, RagiumMaterialKeys.URANIUM, ItemContent.RAW_URANIUM)
    }
}
