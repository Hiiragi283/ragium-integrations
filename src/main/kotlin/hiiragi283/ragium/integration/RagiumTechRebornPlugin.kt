package hiiragi283.ragium.integration

import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTMaterialPropertyKeys
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.util.TriConsumer
import hiiragi283.ragium.common.init.RagiumMaterialKeys
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import reborncore.common.misc.TagConvertible
import techreborn.init.TRContent

object RagiumTechRebornPlugin : RagiumPlugin {
    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isModLoaded("techreborn")

    override fun setupMaterialProperties(helper: RagiumPlugin.PropertyHelper<HTMaterialKey>) {
        // mineral
        helper.modify(RagiumMaterialKeys.BAUXITE) {
            set(HTMaterialPropertyKeys.ORE_SUB_PRODUCT, TRContent.SmallDusts.TITANIUM)
        }
    }

    override fun bindMaterialToItem(consumer: TriConsumer<HTTagPrefix, HTMaterialKey, ItemConvertible>) {
        fun <T> registerContents(prefix: HTTagPrefix, entries: List<T>) where T : ItemConvertible, T : TagConvertible<Item> {
            entries.forEach {
                val name: String = it
                    .asTag()
                    .id.path
                    .removePrefix("${prefix.prefix}/")
                if (name.startsWith("raw_")) return@forEach
                consumer.accept(
                    prefix,
                    HTMaterialKey.of(name),
                    it,
                )
            }
        }

        registerContents(HTTagPrefix.DUST, TRContent.Dusts.entries)
        registerContents(HTTagPrefix.GEM, TRContent.Gems.entries)
        registerContents(HTTagPrefix.INGOT, TRContent.Ingots.entries)
        registerContents(HTTagPrefix.NUGGET, TRContent.Nuggets.entries)
        registerContents(HTTagPrefix.ORE, TRContent.Ores.entries)
        registerContents(HTTagPrefix.PLATE, TRContent.Plates.entries)
        registerContents(HTTagPrefix.RAW_MATERIAL, TRContent.RawMetals.entries)
        registerContents(HTTagPrefix.STORAGE_BLOCK, TRContent.StorageBlocks.entries)
    }
}
