package hiiragi283.ragium.data

import hiiragi283.ragium.api.RagiumAPI
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object RIDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()
        // server
        pack.addProvider(::RIRecipeProvider)
        RITagProviders.init(pack)
        // client
        pack.addProvider(::RIPatchouliCategoryProvider)
        pack.addProvider(::RIPatchouliPageProvider)
        RILangProviders.init(pack)
    }

    override fun getEffectiveModId(): String = RagiumAPI.MOD_ID
}
