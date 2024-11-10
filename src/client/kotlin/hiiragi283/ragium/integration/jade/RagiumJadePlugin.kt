package hiiragi283.ragium.integration.jade

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isClientEnv
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.property.HTPropertyKey
import hiiragi283.ragium.common.init.RagiumMachineKeys
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Vec2f
import snownee.jade.api.ITooltip
import snownee.jade.api.ui.IElementHelper

object RagiumJadePlugin : RagiumPlugin {
    @JvmField
    val INVENTORY: HTPropertyKey.Defaulted<(List<ItemStack>, ITooltip, IElementHelper, Float) -> Unit> =
        HTPropertyKey.ofDefaulted(
            RagiumAPI.id("jade_inventory"),
        ) { inventory: List<ItemStack>, tooltip: ITooltip, helper: IElementHelper, progress: Float ->
            tooltip.add(helper.item(inventory[0]))
            tooltip.append(helper.item(inventory[1]))
            tooltip.append(helper.spacer(4, 0))
            tooltip.append(helper.progress(progress).translate(Vec2f(-2.0f, 0.0f)))
            tooltip.append(helper.item(inventory[3]))
            tooltip.append(helper.item(inventory[4]))
        }

    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isClientEnv() && isModLoaded("jade")

    override fun setupMachineProperties(helper: RagiumPlugin.PropertyHelper<HTMachineKey>) {
        helper.modify(
            RagiumMachineKeys.BLAST_FURNACE,
            RagiumMachineKeys.DISTILLATION_TOWER,
            RagiumMachineKeys.SAW_MILL,
        ) {
            set(INVENTORY) { inventory: List<ItemStack>, tooltip: ITooltip, helper: IElementHelper, progress: Float ->
                tooltip.add(helper.item(inventory[0]))
                tooltip.append(helper.item(inventory[1]))
                tooltip.append(helper.item(inventory[2]))
                tooltip.append(helper.spacer(4, 0))
                tooltip.append(helper.progress(progress).translate(Vec2f(-2.0f, 0.0f)))
                tooltip.append(helper.item(inventory[4]))
                tooltip.append(helper.item(inventory[5]))
                tooltip.append(helper.item(inventory[6]))
            }
        }
    }

    override fun afterRagiumInit(instance: RagiumAPI) {}
}
