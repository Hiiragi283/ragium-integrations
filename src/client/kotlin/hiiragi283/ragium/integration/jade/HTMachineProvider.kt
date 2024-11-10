package hiiragi283.ragium.integration.jade

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import hiiragi283.ragium.api.extension.iterateStacks
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.machine.block.HTMachineBlockEntityBase
import hiiragi283.ragium.api.machine.multiblock.HTMultiblockController
import hiiragi283.ragium.common.init.RagiumTranslationKeys
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import snownee.jade.api.BlockAccessor
import snownee.jade.api.IBlockComponentProvider
import snownee.jade.api.IServerDataProvider
import snownee.jade.api.ITooltip
import snownee.jade.api.config.IPluginConfig
import snownee.jade.api.ui.IElementHelper
import kotlin.jvm.optionals.getOrNull

object HTMachineProvider : IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    @JvmField
    val KEY: MapCodec<HTMachineKey> = HTMachineKey.CODEC.fieldOf("key")

    @JvmField
    val TIER: MapCodec<HTMachineTier> = HTMachineTier.CODEC.fieldOf("tier")

    @JvmField
    val INVENTORY: MapCodec<List<ItemStack>> = ItemStack.OPTIONAL_CODEC.listOf().fieldOf("inventory")

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
        key.appendTooltip(tooltip::add, tier)
        val progress: Int = accessor.readData(TICK).orElse(0)
        val maxProgress: Int = accessor.readData(MAX_TICK).orElse(tier.tickRate)
        val currentProgress: Float = progress.toFloat() / maxProgress.toFloat()
        val helper: IElementHelper = IElementHelper.get()
        accessor.readData(INVENTORY).ifPresent { stacks: List<ItemStack> ->
            if (stacks.isNotEmpty()) {
                key.entry.getOrDefault(RagiumJadePlugin.INVENTORY)(stacks, tooltip, helper, currentProgress)
            }
        }

        val showPreview: Boolean = accessor.readData(PREVIEW).getOrNull() ?: return
        tooltip.add(Text.translatable(RagiumTranslationKeys.MACHINE_SHOW_PREVIEW, showPreview))
    }

    //    IServerDataProvider    //

    override fun appendServerData(nbt: NbtCompound, accessor: BlockAccessor) {
        accessor.machineBlockEntity?.let { machine: HTMachineBlockEntityBase ->
            accessor.writeData(KEY, machine.key)
            accessor.writeData(TIER, machine.tier)
            (machine.asInventory())?.let { accessor.writeData(INVENTORY, it.iterateStacks()) }
            accessor.writeData(TICK, machine.property.get(0))
            accessor.writeData(MAX_TICK, machine.property.get(1))
            if (machine is HTMultiblockController) {
                accessor.writeData(PREVIEW, machine.showPreview)
            }
        }
    }
}
