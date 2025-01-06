package hiiragi283.ragium.integration.rei

import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.extension.isClientEnv
import hiiragi283.ragium.api.extension.isModLoaded
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.common.init.RagiumMachineKeys
import hiiragi283.ragium.integration.rei.category.HTDistillationTowerCategory
import hiiragi283.ragium.integration.rei.category.HTGeneratorCategory
import hiiragi283.ragium.integration.rei.category.HTLargeChemicalReactorCategory
import hiiragi283.ragium.integration.rei.category.HTRockGeneratorCategory
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

@Environment(EnvType.CLIENT)
object RagiumREIPlugin : RagiumPlugin {
    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isClientEnv() && isModLoaded("roughlyenoughitems")

    override fun setupMachineProperties(helper: RagiumPlugin.PropertyHelper<HTMachineKey>) {
        // consumer
        helper.modify(RagiumMachineKeys.BIOMASS_FERMENTER) {
            set(REIMachinePropertyKeys.RECIPE_CATEGORY, ::HTGeneratorCategory)
        }
        helper.modify(RagiumMachineKeys.ROCK_GENERATOR) {
            set(REIMachinePropertyKeys.RECIPE_CATEGORY, ::HTRockGeneratorCategory)
        }
        // generator
        helper.modify(
            RagiumMachineKeys.COMBUSTION_GENERATOR,
            RagiumMachineKeys.NUCLEAR_REACTOR,
            RagiumMachineKeys.STEAM_GENERATOR,
            RagiumMachineKeys.THERMAL_GENERATOR,
        ) {
            set(REIMachinePropertyKeys.RECIPE_CATEGORY, ::HTGeneratorCategory)
        }
        // processor
        helper.modify(RagiumMachineKeys.DISTILLATION_TOWER) {
            set(REIMachinePropertyKeys.RECIPE_CATEGORY, ::HTDistillationTowerCategory)
        }
        helper.modify(RagiumMachineKeys.LARGE_CHEMICAL_REACTOR) {
            set(REIMachinePropertyKeys.RECIPE_CATEGORY, ::HTLargeChemicalReactorCategory)
        }
    }
}
