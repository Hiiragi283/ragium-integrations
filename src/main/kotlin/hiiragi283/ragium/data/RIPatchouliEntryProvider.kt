package hiiragi283.ragium.data

import hiiragi283.ragium.integration.RagiumIntegration
import hiiragi283.ragium.integration.patchouli.HTBookEntry
import hiiragi283.ragium.integration.patchouli.HTBookPage
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider
import net.minecraft.data.DataOutput
import net.minecraft.item.ItemConvertible
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

abstract class RIPatchouliEntryProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>,
    locale: String,
) : FabricCodecDataProvider<HTBookEntry>(
        output,
        registriesFuture,
        DataOutput.OutputType.RESOURCE_PACK,
        "patchouli_books/ragi_wiki/$locale/entries",
        HTBookEntry.CODEC,
    ) {
    override fun getName(): String = "Patchouli"

    fun register(
        consumer: BiConsumer<Identifier, HTBookEntry>,
        category: String,
        path: String,
        name: String,
        icon: ItemConvertible,
        vararg pages: HTBookPage<*, *>,
    ) {
        val id: Identifier = RagiumIntegration.id(category)
        val entry: HTBookEntry = HTBookEntry.of(
            name,
            Registries.ITEM.getEntry(icon.asItem()),
            id,
            pages.toList(),
        ) ?: return
        consumer.accept(RagiumIntegration.id(path).withPrefixedPath("$category/"), entry)
    }
}
