package hiiragi283.ragium.integration

import hiiragi283.ragium.integration.patchouli.page.HTBookPage
import hiiragi283.ragium.integration.patchouli.page.HTBookPageSerializers
import net.fabricmc.api.ModInitializer

object RagiumIntegration : ModInitializer {
    override fun onInitialize() {
        HTBookPageSerializers
        HTBookPage
    }
}
