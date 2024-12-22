package hiiragi283.ragium.integration.accessories

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.accessory.HTAccessoryRegistry
import hiiragi283.ragium.api.accessory.HTAccessorySlotTypes
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.api.extension.openBackpackScreen
import hiiragi283.ragium.common.init.RagiumComponentTypes
import hiiragi283.ragium.common.init.RagiumItemsNew
import io.wispforest.accessories.api.AccessoriesAPI
import io.wispforest.accessories.api.AccessoriesCapability
import io.wispforest.accessories.api.Accessory
import io.wispforest.accessories.api.components.AccessoriesDataComponents
import io.wispforest.accessories.api.components.AccessorySlotValidationComponent
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.component.ComponentMap
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.world.World

object RagiumAccessoriesPlugin : RagiumPlugin {
    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isModLoaded("accessories")

    private val slotCache: MutableMap<Item, Set<String>> = mutableMapOf()

    override fun afterRagiumInit(instance: RagiumAPI) {
        HTAccessoryRegistry.slotTypes.forEach { (item: ItemConvertible, slot: HTAccessorySlotTypes) ->
            registerAccessory(item, HTWrappedAccessory, slot)
        }

        registerAccessory(RagiumItemsNew.BACKPACK, HTEmptyAccessory, HTAccessorySlotTypes.BACK)

        DefaultItemComponentEvents.MODIFY.register { context: DefaultItemComponentEvents.ModifyContext ->
            context.modify(slotCache::containsKey) { builder: ComponentMap.Builder, item: Item ->
                val slots: Set<String> = slotCache.getOrDefault(item, setOf())
                builder.add(
                    AccessoriesDataComponents.SLOT_VALIDATION,
                    AccessorySlotValidationComponent(slots, setOf()),
                )
            }
        }

        HTOpenBackpackPayload

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

        RagiumAPI.log { info("Accessories integration loaded!") }
    }

    @JvmStatic
    private fun registerAccessory(item: ItemConvertible, accessory: Accessory, vararg slots: HTAccessorySlotTypes) {
        AccessoriesAPI.registerAccessory(item.asItem(), accessory)
        slotCache[item.asItem()] = slots.map(HTAccessorySlotTypes::asString).toSet()
    }
}
