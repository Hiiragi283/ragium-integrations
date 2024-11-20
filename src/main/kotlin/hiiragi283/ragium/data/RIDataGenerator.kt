package hiiragi283.ragium.data

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object RIDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()
        // server
        pack.addProvider(::RIRecipeProvider)
        pack.addProvider(::RITagProvider)
        // client
        pack.addProvider(::RIEnglishPatchouliEntryProvider)
        RILangProviders.init(pack)
    }
}
