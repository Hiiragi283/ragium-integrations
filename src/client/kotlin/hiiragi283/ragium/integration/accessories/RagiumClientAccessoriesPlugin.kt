package hiiragi283.ragium.integration.accessories

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isClientEnv
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.common.init.RagiumItems
import hiiragi283.ragium.integration.RIKeyBinds
import io.wispforest.accessories.api.AccessoriesCapability
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.MinecraftClient

@Environment(EnvType.CLIENT)
object RagiumClientAccessoriesPlugin : RagiumPlugin {
    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isClientEnv() && isModLoaded("accessories")

    override fun afterRagiumInit(instance: RagiumAPI) {
        ClientTickEvents.END_CLIENT_TICK.register { client: MinecraftClient ->
            while (RIKeyBinds.OPEN_BACKPACK.wasPressed()) {
                val capability: AccessoriesCapability = client.player?.accessoriesCapability() ?: break
                if (capability.isEquipped(RagiumItems.BACKPACK.get())) {
                    ClientPlayNetworking.send(HTOpenBackpackPayload)
                }
            }
        }
    }
}
