package hiiragi283.ragium.integration.patchouli

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.item.Item
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import kotlin.jvm.optionals.getOrNull

class HTBookEntry private constructor(
    val name: String,
    val icon: String,
    val category: Identifier,
    val pages: List<HTBookPage<*, *>>,
) {
    companion object {
        @JvmField
        val CODEC: Codec<HTBookEntry> = RecordCodecBuilder.create { instance ->
            instance
                .group(
                    Codec.STRING.fieldOf("name").forGetter(HTBookEntry::name),
                    Codec.STRING.fieldOf("icon").forGetter(HTBookEntry::icon),
                    Identifier.CODEC.fieldOf("category").forGetter(HTBookEntry::category),
                    HTBookPage.CODEC
                        .listOf()
                        .fieldOf("pages")
                        .forGetter(HTBookEntry::pages),
                ).apply(instance, ::HTBookEntry)
        }

        @JvmStatic
        fun of(
            name: String,
            icon: RegistryEntry<Item>,
            category: Identifier,
            pages: List<HTBookPage<*, *>>,
        ): HTBookEntry? {
            val iconId: String = icon.key
                .getOrNull()
                ?.value
                ?.toString() ?: return null
            return HTBookEntry(name, iconId, category, pages)
        }
    }
}
