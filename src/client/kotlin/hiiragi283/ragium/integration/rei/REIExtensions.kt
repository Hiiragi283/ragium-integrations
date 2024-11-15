package hiiragi283.ragium.integration.rei

import com.mojang.datafixers.util.Either
import hiiragi283.ragium.api.extension.buildItemStack
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.recipe.HTFluidResult
import hiiragi283.ragium.api.recipe.HTIngredient
import hiiragi283.ragium.api.recipe.HTItemResult
import hiiragi283.ragium.integration.RITranslationKeys
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.entry.EntryIngredient
import me.shedaniel.rei.api.common.entry.EntryStack
import me.shedaniel.rei.api.common.util.EntryIngredients
import me.shedaniel.rei.api.common.util.EntryStacks
import me.shedaniel.rei.impl.Internals
import net.minecraft.component.DataComponentTypes
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentLevelEntry
import net.minecraft.fluid.Fluid
import net.minecraft.item.EnchantedBookItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.tag.TagKey
import net.minecraft.text.Text
import net.minecraft.util.Formatting

//    Accessors    //

@Suppress("UnstableApiUsage")
val dynamicRegistry: () -> DynamicRegistryManager
    get() = Internals::getRegistryAccess

//    CategoryIdentifier    //

val HTMachineKey.categoryId: CategoryIdentifier<HTMachineRecipeDisplay>
    get() = CategoryIdentifier.of(id)

//    EntryStack    //

fun HTMachineKey.createEntryStack(tier: HTMachineTier): EntryStack<ItemStack> = EntryStacks.of(createItemStack(tier))

fun createEnchantedBook(key: RegistryKey<Enchantment>): EntryStack<ItemStack> = dynamicRegistry()
    .get(RegistryKeys.ENCHANTMENT)
    .getEntry(key)
    .map { EnchantmentLevelEntry(it, 1) }
    .map(EnchantedBookItem::forEnchantment)
    .map(EntryStacks::of)
    .orElse(EntryStacks.of(Items.ENCHANTED_BOOK))

//    HTIngredient    //

val HTIngredient<*, *>.entryIngredient: EntryIngredient
    get() {
        val dummyIngredient: EntryIngredient = EntryIngredients.of(
            buildItemStack(Items.BARRIER) {
                add(
                    DataComponentTypes.ITEM_NAME,
                    Text
                        .translatable(RITranslationKeys.REI_ENTRY_NO_MATCHING, this@entryIngredient.entryText)
                        .formatted(Formatting.RED),
                )
            },
        )
        return when (this) {
            is HTIngredient.Fluid -> map { storage: Either<TagKey<Fluid>, List<RegistryEntry<Fluid>>>, amount: Long ->
                storage.map(
                    { EntryIngredients.ofFluidTag(it).takeIf(EntryIngredient::isNotEmpty) ?: dummyIngredient },
                    {
                        it
                            .map(RegistryEntry<Fluid>::value)
                            .map { fluid: Fluid -> EntryStacks.of(fluid, amount) }
                            .let(EntryIngredient::of)
                    },
                )
            }

            is HTIngredient.Item -> map { storage: Either<TagKey<Item>, List<RegistryEntry<Item>>>, count: Int ->
                storage
                    .map(
                        { EntryIngredients.ofItemTag(it).takeIf(EntryIngredient::isNotEmpty) ?: dummyIngredient },
                        {
                            it
                                .map(RegistryEntry<Item>::value)
                                .map { item: Item -> EntryStacks.of(item, count) }
                                .let(EntryIngredient::of)
                        },
                    )
            }.onEach { stack: EntryStack<*> ->
                if (consumeType == HTIngredient.ConsumeType.DAMAGE) {
                    stack.tooltip(
                        Text
                            .translatable(RITranslationKeys.REI_ENTRY_APPLY_DAMAGE, amount)
                            .formatted(Formatting.YELLOW),
                    )
                }
            }
        }
    }

//    HTRecipeResult    //

val HTItemResult.entryIngredient: EntryIngredient
    get() = EntryIngredients.of(stack)

val HTFluidResult.entryIngredient: EntryIngredient
    get() = EntryIngredients.of(fluid, amount)
