package hiiragi283.ragium.integration.rei

import hiiragi283.ragium.api.extension.buildItemStack
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.recipe.HTFluidIngredient
import hiiragi283.ragium.api.recipe.HTFluidResult
import hiiragi283.ragium.api.recipe.HTItemIngredient
import hiiragi283.ragium.api.recipe.HTItemResult
import hiiragi283.ragium.common.init.RagiumTranslationKeys
import hiiragi283.ragium.integration.rei.display.HTMachineRecipeDisplay
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.entry.EntryIngredient
import me.shedaniel.rei.api.common.entry.EntryStack
import me.shedaniel.rei.api.common.util.EntryIngredients
import me.shedaniel.rei.api.common.util.EntryStacks
import me.shedaniel.rei.impl.Internals
import net.minecraft.component.DataComponentTypes
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentLevelEntry
import net.minecraft.item.EnchantedBookItem
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
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

fun getDummyIngredient(entryText: Text): EntryIngredient = EntryIngredients.of(
    buildItemStack(Items.BARRIER) {
        add(
            DataComponentTypes.ITEM_NAME,
            Text
                .translatable(RagiumTranslationKeys.REI_ENTRY_NO_MATCHING, entryText)
                .formatted(Formatting.RED),
        )
    },
)

val HTItemIngredient.entryIngredient: EntryIngredient
    get() = (
        map(EntryStacks::of)
            .let(EntryIngredient::of)
            .takeIf(EntryIngredient::isNotEmpty)
            ?: getDummyIngredient(text)
    ).onEach { stack: EntryStack<*> ->
        if (consumeType == HTItemIngredient.ConsumeType.DAMAGE) {
            stack.tooltip(
                Text
                    .translatable(RagiumTranslationKeys.REI_ENTRY_APPLY_DAMAGE, count)
                    .formatted(Formatting.YELLOW),
            )
        }
    }

val HTFluidIngredient.entryIngredient: EntryIngredient
    get() = map(EntryStacks::of)
        .let(EntryIngredient::of)
        .takeIf(EntryIngredient::isNotEmpty)
        ?: getDummyIngredient(text)

//    HTRecipeResult    //

val HTItemResult.entryIngredient: EntryIngredient
    get() = EntryIngredients.of(stack)

val HTFluidResult.entryIngredient: EntryIngredient
    get() = EntryIngredients.of(fluid, amount)
