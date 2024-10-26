package hiiragi283.ragium.integration

import net.fabricmc.api.ClientModInitializer

object RagiumClientIntegration : ClientModInitializer {
    override fun onInitializeClient() {
        RIKeyBinds
    }
}
