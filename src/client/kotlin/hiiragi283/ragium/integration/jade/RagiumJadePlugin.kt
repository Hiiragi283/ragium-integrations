package hiiragi283.ragium.integration.jade

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isClientEnv
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.property.HTPropertyKey
import hiiragi283.ragium.common.init.RagiumMachineKeys
import net.minecraft.inventory.Inventory
import net.minecraft.util.math.Vec2f
import snownee.jade.api.ITooltip
import snownee.jade.api.ui.IElementHelper

object RagiumJadePlugin : RagiumPlugin {
    @JvmField
    val INVENTORY: HTPropertyKey.Defaulted<(Inventory, ITooltip, IElementHelper, Float) -> Unit> =
        HTPropertyKey.ofDefaulted(
            RagiumAPI.id("jade_inventory"),
        ) { inventory: Inventory, tooltip: ITooltip, helper: IElementHelper, progress: Float ->
            tooltip.add(helper.item(inventory.getStack(0)))
            tooltip.append(helper.item(inventory.getStack(1)))
            tooltip.append(helper.spacer(4, 0))
            tooltip.append(helper.progress(progress).translate(Vec2f(-2.0f, 0.0f)))
            tooltip.append(helper.item(inventory.getStack(3)))
            tooltip.append(helper.item(inventory.getStack(4)))
        }

    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isClientEnv() && isModLoaded("jade")

    override fun setupMachineProperties(helper: RagiumPlugin.PropertyHelper<HTMachineKey>) {
        helper.modify(
            RagiumMachineKeys.BLAST_FURNACE,
            RagiumMachineKeys.DISTILLATION_TOWER,
            RagiumMachineKeys.SAW_MILL,
        ) {
            set(INVENTORY) { inventory: Inventory, tooltip: ITooltip, helper: IElementHelper, progress: Float ->
                tooltip.add(helper.item(inventory.getStack(0)))
                tooltip.append(helper.item(inventory.getStack(1)))
                tooltip.append(helper.item(inventory.getStack(2)))
                tooltip.append(helper.spacer(4, 0))
                tooltip.append(helper.progress(progress).translate(Vec2f(-2.0f, 0.0f)))
                tooltip.append(helper.item(inventory.getStack(4)))
                tooltip.append(helper.item(inventory.getStack(5)))
                tooltip.append(helper.item(inventory.getStack(6)))
            }
        }
    }

    override fun afterRagiumInit(instance: RagiumAPI) {}
}
