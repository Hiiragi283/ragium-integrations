package hiiragi283.ragium.integration

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.api.extension.openBackpackScreen
import hiiragi283.ragium.common.init.RagiumComponentTypes
import hiiragi283.ragium.integration.accessories.HTOpenBackpackPayload
import hiiragi283.ragium.integration.accessories.RagiumAccessoriesInit
import io.wispforest.accessories.api.AccessoriesCapability
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.world.World

object RagiumIntegration : ModInitializer {
    override fun onInitialize() {
        HTOpenBackpackPayload

        RagiumAPI.getInstance().registerIntegration(::registerIntegrations)
    }

    private fun registerIntegrations() {
        if (isModLoaded("accessories")) {
            RagiumAccessoriesInit.init()

            ServerPlayNetworking.registerGlobalReceiver(
                HTOpenBackpackPayload.ID,
            ) { _: HTOpenBackpackPayload, context: ServerPlayNetworking.Context ->
                val player: ServerPlayerEntity = context.player()
                val world: World = player.world
                val capability: AccessoriesCapability = player.accessoriesCapability() ?: return@registerGlobalReceiver
                capability
                    .getEquipped { it.contains(RagiumComponentTypes.COLOR) }
                    ?.firstOrNull { it.reference.slotName() == "back" }
                    ?.stack
                    ?.let { stack: ItemStack -> openBackpackScreen(world, player, stack) }
            }
        }
    }
}
