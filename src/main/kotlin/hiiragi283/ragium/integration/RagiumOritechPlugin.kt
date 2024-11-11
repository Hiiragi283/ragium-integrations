package hiiragi283.ragium.integration

import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTMaterialPropertyKeys
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.util.TriConsumer
import hiiragi283.ragium.common.init.RagiumMaterialKeys
import net.minecraft.item.ItemConvertible
import net.minecraft.util.Rarity
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

    override fun registerMaterial(consumer: TriConsumer<HTMaterialKey, HTMaterialKey.Type, Rarity>) {
        // alloy
        consumer.accept(ADAMANT, HTMaterialKey.Type.ALLOY, Rarity.RARE)
        consumer.accept(DURATIUM, HTMaterialKey.Type.ALLOY, Rarity.RARE)
        consumer.accept(ENERGITE, HTMaterialKey.Type.ALLOY, Rarity.RARE)
        consumer.accept(PROMETHEUM, HTMaterialKey.Type.ALLOY, Rarity.EPIC)
    }

    override fun setupMaterialProperties(helper: RagiumPlugin.PropertyHelper<HTMaterialKey>) {
        helper.modify(ADAMANT, DURATIUM, ENERGITE, PROMETHEUM) {
            add(HTMaterialPropertyKeys.DISABLE_BLOCK_CRAFTING)
        }
    }

    override fun bindMaterialToItem(consumer: TriConsumer<HTTagPrefix, HTMaterialKey, ItemConvertible>) {
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.COAL, ItemContent.COAL_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.COPPER, ItemContent.COPPER_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.ELECTRUM, ItemContent.ELECTRUM_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.GOLD, ItemContent.GOLD_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.IRON, ItemContent.IRON_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.NICKEL, ItemContent.NICKEL_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.PLATINUM, ItemContent.PLATINUM_DUST)
        consumer.accept(HTTagPrefix.DUST, RagiumMaterialKeys.STEEL, ItemContent.STEEL_DUST)
        consumer.accept(HTTagPrefix.DUST, ADAMANT, ItemContent.ADAMANT_DUST)
        consumer.accept(HTTagPrefix.DUST, BIOSTEEL, ItemContent.BIOSTEEL_DUST)
        consumer.accept(HTTagPrefix.DUST, DURATIUM, ItemContent.DURATIUM_DUST)
        consumer.accept(HTTagPrefix.DUST, ENERGITE, ItemContent.ENERGITE_DUST)

        consumer.accept(HTTagPrefix.INGOT, RagiumMaterialKeys.ELECTRUM, ItemContent.ELECTRUM_INGOT)
        consumer.accept(HTTagPrefix.INGOT, RagiumMaterialKeys.NICKEL, ItemContent.NICKEL_INGOT)
        consumer.accept(HTTagPrefix.INGOT, RagiumMaterialKeys.PLATINUM, ItemContent.PLATINUM_INGOT)
        consumer.accept(HTTagPrefix.INGOT, RagiumMaterialKeys.STEEL, ItemContent.STEEL_INGOT)
        consumer.accept(HTTagPrefix.INGOT, ADAMANT, ItemContent.ADAMANT_INGOT)
        consumer.accept(HTTagPrefix.INGOT, BIOSTEEL, ItemContent.BIOSTEEL_INGOT)
        consumer.accept(HTTagPrefix.INGOT, DURATIUM, ItemContent.DURATIUM_INGOT)
        consumer.accept(HTTagPrefix.INGOT, ENERGITE, ItemContent.ENERGITE_INGOT)
        consumer.accept(HTTagPrefix.INGOT, PROMETHEUM, ItemContent.PROMETHEUM_INGOT)

        consumer.accept(HTTagPrefix.RAW_MATERIAL, RagiumMaterialKeys.NICKEL, ItemContent.RAW_NICKEL)
        consumer.accept(HTTagPrefix.RAW_MATERIAL, RagiumMaterialKeys.PLATINUM, ItemContent.RAW_PLATINUM)
    }
}
