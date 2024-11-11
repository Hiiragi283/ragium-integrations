package hiiragi283.ragium.data

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.integration.RITranslationKeys
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

object RILangProviders {
    @JvmStatic
    fun init(pack: FabricDataGenerator.Pack) {
        pack.addProvider(::EnglishLang)
        pack.addProvider(::JapaneseLang)
    }

    //    English    //

    private class EnglishLang(output: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>) :
        FabricLanguageProvider(output, registryLookup) {
        override fun generateTranslations(registryLookup: RegistryWrapper.WrapperLookup, builder: TranslationBuilder) {
            // Key Binds
            builder.add("category.ragium.ragium", RagiumAPI.MOD_NAME)

            builder.add("key.ragium.open_backpack", "Open Backpack")
            // Jade
            builder.add(RITranslationKeys.CONFIG_JADE_MACHINE, "Machines")
            builder.add(RITranslationKeys.CONFIG_JADE_NETWORK_INTERFACE, "E.N.I")

            builder.add(RITranslationKeys.PROVIDER_JADE_NETWORK_INTERFACE, "Stored Energy: %s E")
            // REI
            builder.add(RITranslationKeys.REI_ENTRY_NO_MATCHING, "No matching entry for %s")
            builder.add(RITranslationKeys.REI_ENTRY_APPLY_DAMAGE, "Apply %s damage when processed")

            builder.add(RITranslationKeys.REI_RECIPE_BIOME, "Found in the biome: %s")
            builder.add(RITranslationKeys.REI_RECIPE_INFO, "Recipe Info")
        }
    }

    //    Japanese    //

    private class JapaneseLang(output: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>) :
        FabricLanguageProvider(output, "ja_jp", registryLookup) {
        override fun generateTranslations(registryLookup: RegistryWrapper.WrapperLookup, builder: TranslationBuilder) {
            // Key Binds
            builder.add("category.ragium.ragium", RagiumAPI.MOD_NAME)

            builder.add("key.ragium.open_backpack", "バックパックを開く")
            // Jade
            builder.add(RITranslationKeys.CONFIG_JADE_MACHINE, "機械")
            builder.add(RITranslationKeys.CONFIG_JADE_NETWORK_INTERFACE, "E.N.I")

            builder.add(RITranslationKeys.PROVIDER_JADE_NETWORK_INTERFACE, "エネルギー量: %s E")
            // REI
            builder.add(RITranslationKeys.REI_ENTRY_NO_MATCHING, "次を満たす値がありません: %s")
            builder.add(RITranslationKeys.REI_ENTRY_APPLY_DAMAGE, "実行時に%sだけ耐久値を減らす")

            builder.add(RITranslationKeys.REI_RECIPE_BIOME, "次のバイオームで見つかる: %s")
            builder.add(RITranslationKeys.REI_RECIPE_INFO, "レシピ情報")
        }
    }
}
