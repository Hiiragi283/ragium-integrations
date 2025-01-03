package hiiragi283.ragium.data.client

import hiiragi283.ragium.common.init.RagiumBlocks
import hiiragi283.ragium.common.init.RagiumItems
import hiiragi283.ragium.common.init.RagiumTranslationKeys
import hiiragi283.ragium.integration.patchouli.HTBookCategory
import hiiragi283.ragium.integration.patchouli.HTPatchouliCategory
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider
import net.minecraft.data.DataOutput
import net.minecraft.item.ItemConvertible
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class RIPatchouliCategoryProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricCodecDataProvider<HTBookCategory>(
        output,
        registriesFuture,
        DataOutput.OutputType.RESOURCE_PACK,
        "patchouli_books/ragi_wiki/en_us/categories",
        HTBookCategory.CODEC,
    ) {
    override fun getName(): String = "Patchouli/Category"

    override fun configure(consumer: BiConsumer<Identifier, HTBookCategory>, wrapperLookup: RegistryWrapper.WrapperLookup) {
        registerCategory(
            consumer,
            HTPatchouliCategory.TIER_1,
            RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER1,
            RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER10,
            RagiumItems.Ingots.RAGI_ALLOY,
        )
        registerCategory(
            consumer,
            HTPatchouliCategory.TIER_2,
            RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER2,
            RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER20,
            RagiumItems.Ingots.RAGI_STEEL,
        )
        registerCategory(
            consumer,
            HTPatchouliCategory.TIER_3,
            RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER3,
            RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER30,
            RagiumItems.Ingots.REFINED_RAGI_STEEL,
        )
        registerCategory(
            consumer,
            HTPatchouliCategory.TIER_4,
            RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER4,
            RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER40,
            RagiumItems.Gems.RAGIUM,
        )
        registerCategory(
            consumer,
            HTPatchouliCategory.FOOD,
            "Foods",
            "Foods",
            RagiumBlocks.SWEET_BERRIES_CAKE,
        )
        registerCategory(
            consumer,
            HTPatchouliCategory.MACHINES,
            "Machines",
            "Machines",
            RagiumBlocks.Hulls.PRIMITIVE,
        )
        registerCategory(
            consumer,
            HTPatchouliCategory.PETROCHEMISTRY,
            "Petro Chemistry",
            "Petro Chemistry",
            RagiumItems.RESIDUAL_COKE,
        )
    }

    private fun registerCategory(
        consumer: BiConsumer<Identifier, HTBookCategory>,
        category: HTPatchouliCategory,
        name: String,
        description: String,
        icon: ItemConvertible,
    ) {
        consumer.accept(
            category.id,
            HTBookCategory(name, description, Registries.ITEM.getId(icon.asItem()).toString()),
        )
    }
}
