package hiiragi283.ragium.integration.jade

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.machine.block.HTMachineBlock
import hiiragi283.ragium.common.block.HTExporterBlock
import hiiragi283.ragium.common.block.HTNetworkInterfaceBlock
import net.minecraft.util.Identifier
import snownee.jade.api.IWailaClientRegistration
import snownee.jade.api.IWailaCommonRegistration
import snownee.jade.api.IWailaPlugin

object RagiumJadeCompat : IWailaPlugin {
    @JvmField
    val EXPORTER: Identifier = RagiumAPI.id("exporter")

    @JvmField
    val MACHINE: Identifier = RagiumAPI.id("machine")

    @JvmField
    val NETWORK_INTERFACE: Identifier = RagiumAPI.id("network_interface")

    override fun register(registration: IWailaCommonRegistration) {
        registration.registerBlockDataProvider(HTExporterProvider, HTExporterBlock::class.java)
        registration.registerBlockDataProvider(HTMachineProvider, HTMachineBlock::class.java)
    }

    override fun registerClient(registration: IWailaClientRegistration) {
        registration.registerBlockComponent(HTExporterProvider, HTExporterBlock::class.java)
        registration.registerBlockComponent(HTMachineProvider, HTMachineBlock::class.java)
        registration.registerBlockComponent(HTEnergyNetworkProvider, HTNetworkInterfaceBlock::class.java)

        RagiumAPI.log { info("Jade integration enabled!") }
    }
}
