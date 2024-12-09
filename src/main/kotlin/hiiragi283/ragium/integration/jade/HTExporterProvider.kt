package hiiragi283.ragium.integration.jade

import com.mojang.serialization.MapCodec
import hiiragi283.ragium.api.extension.asText
import hiiragi283.ragium.api.extension.name
import hiiragi283.ragium.common.init.RagiumTranslationKeys
import net.minecraft.fluid.Fluid
import net.minecraft.item.Item
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryCodecs
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntryList
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import snownee.jade.api.BlockAccessor
import snownee.jade.api.IBlockComponentProvider
import snownee.jade.api.IServerDataProvider
import snownee.jade.api.ITooltip
import snownee.jade.api.config.IPluginConfig

object HTExporterProvider : IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    @JvmField
    val FLUID_FILTER: MapCodec<RegistryEntryList<Fluid>> = RegistryCodecs.entryList(RegistryKeys.FLUID).fieldOf("fluid")

    @JvmField
    val ITEM_FILTER: MapCodec<RegistryEntryList<Item>> = RegistryCodecs.entryList(RegistryKeys.ITEM).fieldOf("item")

    //    IBlockComponentProvider    //

    override fun getUid(): Identifier = RagiumJadeCompat.EXPORTER

    override fun appendTooltip(tooltip: ITooltip, accessor: BlockAccessor, config: IPluginConfig) {
        accessor.readData(FLUID_FILTER).ifPresent { fluid: RegistryEntryList<Fluid> ->
            tooltip.add(
                Text.translatable(
                    RagiumTranslationKeys.EXPORTER_FLUID_FILTER,
                    fluid.asText(Fluid::name),
                ),
            )
        }
        accessor.readData(ITEM_FILTER).ifPresent { item: RegistryEntryList<Item> ->
            tooltip.add(
                Text.translatable(
                    RagiumTranslationKeys.EXPORTER_FLUID_FILTER,
                    item.asText(Item::getName),
                ),
            )
        }
    }

    //    IServerDataProvider    //

    override fun appendServerData(nbt: NbtCompound, accessor: BlockAccessor) {
        /*(accessor.blockEntity as? HTExporterBlockEntity)?.let {
            accessor.writeData(FLUID_FILTER, it.fluidFilter)
            accessor.writeData(ITEM_FILTER, it.itemFilter)
        }*/
    }
}
