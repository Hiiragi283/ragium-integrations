package hiiragi283.ragium.integration

import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isClientEnv
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.common.RagiumContents
import hiiragi283.ragium.integration.accessories.HTOpenBackpackPayload
import io.wispforest.accessories.api.AccessoriesCapability
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.MinecraftClient

object RagiumClientAccessoriesPlugin : RagiumPlugin {
    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isClientEnv() && isModLoaded("accessories")

    override fun afterRagiumInit() {
        ClientTickEvents.END_CLIENT_TICK.register { client: MinecraftClient ->
            while (RIKeyBinds.OPEN_BACKPACK.wasPressed()) {
                val capability: AccessoriesCapability = client.player?.accessoriesCapability() ?: break
                if (capability.isEquipped(RagiumContents.Misc.BACKPACK.asItem())) {
                    ClientPlayNetworking.send(HTOpenBackpackPayload)
                }
            }
        }
    }
}
