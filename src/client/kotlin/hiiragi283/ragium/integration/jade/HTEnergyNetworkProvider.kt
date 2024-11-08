package hiiragi283.ragium.integration.jade

import hiiragi283.ragium.api.extension.energyNetwork
import hiiragi283.ragium.api.world.HTEnergyNetwork
import hiiragi283.ragium.integration.RITranslationKeys
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import snownee.jade.api.BlockAccessor
import snownee.jade.api.IBlockComponentProvider
import snownee.jade.api.ITooltip
import snownee.jade.api.config.IPluginConfig

object HTEnergyNetworkProvider : IBlockComponentProvider {
    //    IBlockComponentProvider    //

    override fun getUid(): Identifier = RagiumJadeCompat.NETWORK_INTERFACE

    override fun appendTooltip(tooltip: ITooltip, accessor: BlockAccessor, config: IPluginConfig) {
        MinecraftClient.getInstance().server?.getWorld(accessor.level.registryKey)?.energyNetwork?.let { network: HTEnergyNetwork ->
            tooltip.add(Text.translatable(RITranslationKeys.PROVIDER_JADE_NETWORK_INTERFACE, network.amount))
        }
    }
}
