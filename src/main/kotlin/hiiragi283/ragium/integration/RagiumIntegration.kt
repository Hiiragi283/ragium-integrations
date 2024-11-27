package hiiragi283.ragium.integration

import hiiragi283.ragium.integration.patchouli.page.HTBookPage
import hiiragi283.ragium.integration.patchouli.page.HTBookPageSerializers
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer

object RagiumIntegration : ModInitializer, ClientModInitializer {
    override fun onInitialize() {
        HTBookPageSerializers
        HTBookPage
    }

    override fun onInitializeClient() {
        RIKeyBinds
    }
}
