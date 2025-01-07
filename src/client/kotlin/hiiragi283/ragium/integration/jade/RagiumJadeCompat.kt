package hiiragi283.ragium.integration.jade

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.block.HTMachineBlock
import hiiragi283.ragium.api.util.DelegatedLogger
import hiiragi283.ragium.common.block.machine.HTNetworkInterfaceBlock
import hiiragi283.ragium.common.block.transfer.HTExporterBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.util.Identifier
import org.slf4j.Logger
import snownee.jade.api.IWailaClientRegistration
import snownee.jade.api.IWailaCommonRegistration
import snownee.jade.api.IWailaPlugin

@Environment(EnvType.CLIENT)
object RagiumJadeCompat : IWailaPlugin {
    @JvmStatic
    private val logger: Logger by DelegatedLogger()

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

        logger.info("Jade integration enabled!")
    }
}
