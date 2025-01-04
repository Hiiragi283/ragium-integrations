package hiiragi283.ragium.data

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.data.client.*
import hiiragi283.ragium.data.server.*
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.ConfiguredFeature

object RagiumDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()
        // server
        pack.addProvider(::RagiumBlockLootProvider)
        pack.addProvider(::RagiumDynamicRegistryProvider)
        pack.addProvider(::RagiumVanillaRecipeProvider)
        pack.addProvider(::RagiumMachineRecipeProvider)
        pack.addProvider(::RagiumChemicalRecipeProvider)
        pack.addProvider(::RagiumIntegrationRecipeProvider)
        RagiumAdvancementProviders.init(pack)
        RagiumTagProviders.init(pack)
        // client
        pack.addProvider(::RagiumEnglishLangProvider)
        pack.addProvider(::RagiumJapaneseLangProvider)
        pack.addProvider(::RagiumModelProvider)
        pack.addProvider(::RIPatchouliCategoryProvider)
        pack.addProvider(::RIPatchouliPageProvider)

        RagiumAPI.LOGGER.info("Ragium data generation is done!")
    }

    override fun getEffectiveModId(): String = RagiumAPI.MOD_ID

    override fun buildRegistry(registryBuilder: RegistryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE) { registerable: Registerable<ConfiguredFeature<*, *>> ->
            RagiumDynamicRegistryProvider.Bootstraps.registerConfigured(registerable::register)
        }
    }
}
