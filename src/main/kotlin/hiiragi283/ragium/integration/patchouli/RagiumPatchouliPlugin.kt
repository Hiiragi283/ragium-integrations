package hiiragi283.ragium.integration.patchouli

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isClientEnv
import hiiragi283.ragium.api.extension.isModLoaded
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import vazkii.patchouli.api.PatchouliAPI
import vazkii.patchouli.client.book.BookPage
import vazkii.patchouli.client.book.ClientBookRegistry

object RagiumPatchouliPlugin : RagiumPlugin {
    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isClientEnv() && isModLoaded("patchouli")

    override fun afterRagiumInit(instance: RagiumAPI) {
        UseItemCallback.EVENT.register { player: PlayerEntity, world: World, hand: Hand ->
            val stack: ItemStack = player.getStackInHand(hand)
            if (player is ServerPlayerEntity) {
                PatchouliAPI.get().openBookGUI(player, RagiumAPI.id("ragi_wiki"))
            }
            TypedActionResult.success(stack, world.isClient)
        }
    }

    @JvmStatic
    private inline fun <reified T : BookPage> addPageType(modId: String, name: String) {
        ClientBookRegistry.INSTANCE.pageTypes[Identifier.of(modId, name)] = T::class.java
    }
}
