package hiiragi283.ragium.integration.jade

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import hiiragi283.ragium.api.block.HTMachineBlockEntityBase
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.machine.multiblock.HTMultiblockProvider
import hiiragi283.ragium.common.init.RagiumTranslationKeys
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import snownee.jade.api.BlockAccessor
import snownee.jade.api.IBlockComponentProvider
import snownee.jade.api.IServerDataProvider
import snownee.jade.api.ITooltip
import snownee.jade.api.config.IPluginConfig
import kotlin.jvm.optionals.getOrNull

object HTMachineProvider : IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    @JvmField
    val KEY: MapCodec<HTMachineKey> = HTMachineKey.CODEC.fieldOf("key")

    @JvmField
    val TIER: MapCodec<HTMachineTier> = HTMachineTier.CODEC.fieldOf("tier")

    @JvmField
    val TICK: MapCodec<Int> = Codec.INT.fieldOf("tick")

    @JvmField
    val MAX_TICK: MapCodec<Int> = Codec.INT.fieldOf("maxTick")

    @JvmField
    val PREVIEW: MapCodec<Boolean> = Codec.BOOL.fieldOf("preview")

    //    IBlockComponentProvider    //

    override fun getUid(): Identifier = RagiumJadeCompat.MACHINE

    override fun appendTooltip(tooltip: ITooltip, accessor: BlockAccessor, config: IPluginConfig) {
        val key: HTMachineKey = accessor
            .readData(KEY)
            .getOrNull()
            ?: return
        val tier: HTMachineTier = accessor
            .readData(TIER)
            .orElse(HTMachineTier.PRIMITIVE)
        key.appendTooltip(tooltip::add, tier, false)
        val showPreview: Boolean = accessor.readData(PREVIEW).getOrNull() ?: return
        tooltip.add(Text.translatable(RagiumTranslationKeys.MACHINE_SHOW_PREVIEW, showPreview))
    }

    //    IServerDataProvider    //

    override fun appendServerData(nbt: NbtCompound, accessor: BlockAccessor) {
        accessor.machineBlockEntity?.let { machine: HTMachineBlockEntityBase ->
            accessor.writeData(KEY, machine.machineKey)
            accessor.writeData(TIER, machine.tier)
            accessor.writeData(TICK, machine.property.get(0))
            accessor.writeData(MAX_TICK, machine.property.get(1))
            if (machine is HTMultiblockProvider) {
                accessor.writeData(PREVIEW, machine.multiblockManager.showPreview)
            }
        }
    }
}
