package hiiragi283.ragium.integration.patchouli.page

import hiiragi283.ragium.api.extension.registryEntry
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.registry.Registries
import net.minecraft.registry.entry.RegistryEntry
import vazkii.patchouli.client.book.page.PageSpotlight
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Suppress("DEPRECATION")
class HTSpotlightBookPage private constructor(
    val items: List<RegistryEntry<Item>>,
    val linkRecipe: Boolean,
    val title: String? = null,
    val text: String? = null,
) : HTBookPage<PageSpotlight, HTSpotlightBookPage> {
    constructor(item: ItemConvertible, text: String? = null, title: String? = null) : this(
        listOf(Registries.ITEM.getEntry(item.asItem())),
        true,
        title,
        text,
    )

    constructor(items: List<ItemConvertible>, text: String? = null, title: String? = null) : this(
        items.map(ItemConvertible::registryEntry),
        true,
        title,
        text,
    )

    constructor(
        item: List<RegistryEntry<Item>>,
        linkRecipe: Boolean,
        title: Optional<String>,
        text: Optional<String>,
    ) : this(item, linkRecipe, title.getOrNull(), text.getOrNull())

    override val serializer: HTBookPageSerializer<PageSpotlight, HTSpotlightBookPage>
        get() = HTBookPageSerializers.SPOTLIGHT
}
