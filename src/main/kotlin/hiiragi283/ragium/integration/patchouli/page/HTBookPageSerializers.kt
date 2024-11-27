package hiiragi283.ragium.integration.patchouli.page

import com.mojang.datafixers.kinds.App
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import hiiragi283.ragium.api.RagiumAPI
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import vazkii.patchouli.api.PatchouliAPI
import vazkii.patchouli.client.book.BookPage
import vazkii.patchouli.client.book.page.PageImage
import vazkii.patchouli.client.book.page.PageSpotlight
import vazkii.patchouli.client.book.page.PageText
import vazkii.patchouli.client.book.page.abstr.PageDoubleRecipe
import java.util.*
import kotlin.jvm.optionals.getOrNull

object HTBookPageSerializers {
    @JvmField
    val ITEM_LIST_CODEC: Codec<List<RegistryEntry<Item>>> = Codec.STRING.xmap(
        {
            it
                .split(",")
                .map(Identifier::of)
                .map { RegistryKey.of(RegistryKeys.ITEM, it) }
                .map(Registries.ITEM::getEntry)
                .mapNotNull(Optional<RegistryEntry.Reference<Item>>::getOrNull)
        },
        {
            it
                .map(RegistryEntry<Item>::getKey)
                .mapNotNull(Optional<RegistryKey<Item>>::getOrNull)
                .map(RegistryKey<Item>::getValue)
                .joinToString(separator = ",", transform = Identifier::toString)
        },
    )

    @JvmField
    val TEXT: HTBookPageSerializer<PageText, HTTextBookPage> =
        register("text") { instance ->
            instance
                .group(
                    Codec.STRING.fieldOf("text").forGetter(HTTextBookPage::text),
                    Codec.STRING.optionalFieldOf("title", "").forGetter(HTTextBookPage::title),
                ).apply(instance, ::HTTextBookPage)
        }

    @JvmField
    val CRAFTING: HTBookPageSerializer<PageDoubleRecipe<*>, HTRecipeBookPage> = registerRecipe("crafting")

    @JvmField
    val SMELTING: HTBookPageSerializer<PageDoubleRecipe<*>, HTRecipeBookPage> = registerRecipe("smelting")

    @JvmField
    val BLASTING: HTBookPageSerializer<PageDoubleRecipe<*>, HTRecipeBookPage> = registerRecipe("blasting")

    @JvmField
    val SMOKING: HTBookPageSerializer<PageDoubleRecipe<*>, HTRecipeBookPage> = registerRecipe("smoking")

    @JvmField
    val CAMPFIRE: HTBookPageSerializer<PageDoubleRecipe<*>, HTRecipeBookPage> = registerRecipe("campfire")

    @JvmField
    val SMITHING: HTBookPageSerializer<PageDoubleRecipe<*>, HTRecipeBookPage> = registerRecipe("smithing")

    @JvmField
    val STONECUTTING: HTBookPageSerializer<PageDoubleRecipe<*>, HTRecipeBookPage> = registerRecipe("stonecutting")

    @JvmField
    val MACHINE_RECIPE: HTBookPageSerializer<PageDoubleRecipe<*>, HTRecipeBookPage> =
        registerRecipe("machine_recipe", RagiumAPI.MOD_ID)

    @JvmField
    val IMAGE: HTBookPageSerializer<PageImage, HTImageBookPage> =
        register("image") { instance ->
            instance
                .group(
                    Identifier.CODEC
                        .listOf()
                        .fieldOf("images")
                        .forGetter(HTImageBookPage::images),
                    Codec.STRING.optionalFieldOf("title", "").forGetter(HTImageBookPage::title),
                    Codec.BOOL.optionalFieldOf("border", false).forGetter(HTImageBookPage::border),
                ).apply(instance, ::HTImageBookPage)
        }

    @JvmField
    val SPOTLIGHT: HTBookPageSerializer<PageSpotlight, HTSpotlightBookPage> =
        register("spotlight") { instance ->
            instance
                .group(
                    ITEM_LIST_CODEC.fieldOf("item").forGetter(HTSpotlightBookPage::items),
                    Codec.BOOL.optionalFieldOf("link_recipe", false).forGetter(HTSpotlightBookPage::linkRecipe),
                    Codec.STRING.optionalFieldOf("text").forGetter { Optional.ofNullable(it.text) },
                    Codec.STRING.optionalFieldOf("title").forGetter { Optional.ofNullable(it.title) },
                ).apply(instance, ::HTSpotlightBookPage)
        }

    @JvmStatic
    private fun registerRecipe(
        path: String,
        namespace: String = PatchouliAPI.MOD_ID,
    ): HTBookPageSerializer<PageDoubleRecipe<*>, HTRecipeBookPage> = register(path, namespace) { instance ->
        instance
            .group(
                Registries.RECIPE_TYPE.codec
                    .fieldOf("recipe_type")
                    .forGetter(HTRecipeBookPage::recipeType),
                Identifier.CODEC.fieldOf("recipe").forGetter(HTRecipeBookPage::recipe),
                Identifier.CODEC.optionalFieldOf("recipe2").forGetter { Optional.ofNullable(it.recipe2) },
                Codec.STRING.optionalFieldOf("text").forGetter { Optional.ofNullable(it.text) },
                Codec.STRING.optionalFieldOf("title").forGetter { Optional.ofNullable(it.title) },
            ).apply(instance, ::HTRecipeBookPage)
    }

    @JvmStatic
    private fun <T : BookPage, R : HTBookPage<T, R>> register(
        path: String,
        namespace: String = PatchouliAPI.MOD_ID,
        builderAction: (RecordCodecBuilder.Instance<R>) -> (App<RecordCodecBuilder.Mu<R>, R>),
    ): HTBookPageSerializer<T, R> = Registry.register(
        HTBookPageSerializer.REGISTRY,
        Identifier.of(namespace, path),
        HTBookPageSerializer(RecordCodecBuilder.mapCodec<R>(builderAction)),
    )
}
