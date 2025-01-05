package hiiragi283.ragium.integration.rei

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.property.HTPropertyKey
import hiiragi283.ragium.integration.rei.category.HTDefaultMachineCategory
import hiiragi283.ragium.integration.rei.category.HTMachineCategoryBase
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

@Environment(EnvType.CLIENT)
object REIMachinePropertyKeys {
    @JvmField
    val RECIPE_CATEGORY: HTPropertyKey.Defaulted<(HTMachineKey) -> HTMachineCategoryBase> =
        HTPropertyKey.ofDefaulted(RagiumAPI.id("recipe_category"), ::HTDefaultMachineCategory)
}
