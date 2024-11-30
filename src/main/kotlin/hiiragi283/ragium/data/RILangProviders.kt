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
            builder.add(RITranslationKeys.CONFIG_JADE_EXPORTER, "Exporters")
            builder.add(RITranslationKeys.CONFIG_JADE_MACHINE, "Machines")
            builder.add(RITranslationKeys.CONFIG_JADE_NETWORK_INTERFACE, "E.N.I")

            builder.add(RITranslationKeys.PROVIDER_JADE_NETWORK_INTERFACE, "Stored Energy: %s E")
            // Patchouli
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER1, "Tier 1")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER10, "The first stage")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER2, "Tier 2")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER20, "The second stage")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER3, "Tier 3")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER30, "The third stage")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER4, "Tier 4")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER40, "The final stage")

            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE, "Find Crude Raginite")
            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE0, "Found in Overworld, between y = [-48, 48]")
            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE1, "Drops [1, 3] from Crude Raginite Ore")

            builder.add(RITranslationKeys.PATCHOULI_RAGI_ALLOY, "Craft Ragi-Alloy")
            builder.add(RITranslationKeys.PATCHOULI_RAGI_ALLOY0, "Ragi-Alloy is the main metal for tier 1")
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
            builder.add(RITranslationKeys.CONFIG_JADE_EXPORTER, "搬出機")
            builder.add(RITranslationKeys.CONFIG_JADE_MACHINE, "機械")
            builder.add(RITranslationKeys.CONFIG_JADE_NETWORK_INTERFACE, "E.N.I")

            builder.add(RITranslationKeys.PROVIDER_JADE_NETWORK_INTERFACE, "エネルギー量: %s E")
            // Patchouli
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER1, "Tier 1")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER10, "最初のステージ")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER2, "Tier 2")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER20, "次なるステージ")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER3, "Tier 3")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER30, "さらなるステージ")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER4, "Tier 4")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER40, "最後のステージ")

            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE, "粗製ラギナイトを見つけよう")
            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE0, "オーバーワールドのy = [-48, 48]の範囲に生成される")
            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE1, "ラギナイト鉱石から[1, 3]個ドロップする")

            builder.add(RITranslationKeys.PATCHOULI_RAGI_ALLOY, "ラギ合金を作ろう")
            builder.add(RITranslationKeys.PATCHOULI_RAGI_ALLOY0, "ラギ合金はTier 1のメイン金属となる")
            // REI
            builder.add(RITranslationKeys.REI_ENTRY_NO_MATCHING, "次を満たす値がありません: %s")
            builder.add(RITranslationKeys.REI_ENTRY_APPLY_DAMAGE, "実行時に%sだけ耐久値を減らす")

            builder.add(RITranslationKeys.REI_RECIPE_BIOME, "次のバイオームで見つかる: %s")
            builder.add(RITranslationKeys.REI_RECIPE_INFO, "レシピ情報")
        }
    }
}
