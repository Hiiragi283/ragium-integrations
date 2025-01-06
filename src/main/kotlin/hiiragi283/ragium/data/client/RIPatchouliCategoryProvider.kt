package hiiragi283.ragium.data.client

import hiiragi283.ragium.common.init.RagiumBlocks
import hiiragi283.ragium.common.init.RagiumItems
import hiiragi283.ragium.integration.patchouli.HTBookCategory
import hiiragi283.ragium.integration.patchouli.HTPatchouliCategory
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider
import net.minecraft.data.DataOutput
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
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
        registerCategory(consumer, HTPatchouliCategory.FOOD, RagiumBlocks.SWEET_BERRIES_CAKE)
        registerCategory(consumer, HTPatchouliCategory.MACHINE, RagiumBlocks.Hulls.PRIMITIVE)
        registerCategory(consumer, HTPatchouliCategory.MATERIAL, Items.IRON_INGOT)
        registerCategory(consumer, HTPatchouliCategory.PETROCHEMISTRY, RagiumItems.RESIDUAL_COKE)
        registerCategory(consumer, HTPatchouliCategory.UTILITY, RagiumItems.TRADER_CATALOG)
    }

    private fun registerCategory(consumer: BiConsumer<Identifier, HTBookCategory>, category: HTPatchouliCategory, icon: ItemConvertible) {
        consumer.accept(
            category.id,
            HTBookCategory(category.translationKey, category.descKey, Registries.ITEM.getId(icon.asItem()).toString()),
        )
    }
}
