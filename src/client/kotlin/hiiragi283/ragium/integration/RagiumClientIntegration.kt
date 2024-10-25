package hiiragi283.ragium.integration

import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.common.RagiumContents
import hiiragi283.ragium.integration.accessories.HTOpenBackpackPayload
import hiiragi283.ragium.integration.patchouli.RagiumPatchouliInit
import io.wispforest.accessories.api.AccessoriesCapability
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.MinecraftClient

object RagiumClientIntegration : ClientModInitializer {
    override fun onInitializeClient() {
        RIKeyBinds
        
        if (isModLoaded("accessories")) {
            ClientTickEvents.END_CLIENT_TICK.register { client: MinecraftClient ->
                while (RIKeyBinds.OPEN_BACKPACK.wasPressed()) {
                    val capability: AccessoriesCapability = client.player?.accessoriesCapability() ?: break
                    if (capability.isEquipped(RagiumContents.Misc.BACKPACK.asItem())) {
                        ClientPlayNetworking.send(HTOpenBackpackPayload)
                    }
                }
            }
        }

        if (isModLoaded("patchouli")) {
            RagiumPatchouliInit.init()
        }
    }
}
