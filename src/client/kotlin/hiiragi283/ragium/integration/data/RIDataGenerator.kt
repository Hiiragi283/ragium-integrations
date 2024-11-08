package hiiragi283.ragium.integration.data

import hiiragi283.ragium.data.RIRecipeProvider
import hiiragi283.ragium.data.recipe.TRRecipeProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object RIDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()
        // server
        pack.addProvider(::RIRecipeProvider)
        pack.addProvider(::TRRecipeProvider)
        // client
        RILangProviders.init(pack)
    }
}
