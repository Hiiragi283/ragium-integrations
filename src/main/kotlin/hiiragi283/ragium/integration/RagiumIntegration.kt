package hiiragi283.ragium.integration

import hiiragi283.ragium.integration.patchouli.HTBookPage
import hiiragi283.ragium.integration.patchouli.HTBookPageSerializers
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier

object RagiumIntegration : ModInitializer, ClientModInitializer {
    const val MOD_ID = "ragium_integrations"

    @JvmStatic
    fun id(path: String): Identifier = Identifier.of(MOD_ID, path)

    override fun onInitialize() {
        HTBookPageSerializers
        HTBookPage
    }

    override fun onInitializeClient() {
        RIKeyBinds
    }
}
