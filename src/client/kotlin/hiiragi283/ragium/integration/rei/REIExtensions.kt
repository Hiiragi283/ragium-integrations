package hiiragi283.ragium.integration.rei

import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.recipe.HTFluidResult
import hiiragi283.ragium.api.recipe.HTIngredient
import hiiragi283.ragium.api.recipe.HTItemResult
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.entry.EntryIngredient
import me.shedaniel.rei.api.common.entry.EntryStack
import me.shedaniel.rei.api.common.util.EntryStacks
import me.shedaniel.rei.impl.Internals
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

val HTIngredient<*, *>.entryStacks: List<EntryStack<*>>
    get() = when (this) {
        is HTIngredient.Fluid -> valueMap.map { EntryStacks.of(it.key, it.value) }
        is HTIngredient.Item -> matchingStacks.map(EntryStacks::of).onEach { stack: EntryStack<ItemStack> ->
            if (consumeType == HTIngredient.ConsumeType.DAMAGE) {
                stack.tooltip(Text.literal("Apply $amount damage when processed").formatted(Formatting.YELLOW))
            }
        }
    }

val HTIngredient<*, *>.entryIngredient: EntryIngredient
    get() = EntryIngredient.of(entryStacks)

//    HTRecipeResult    //

val HTItemResult.entryStack: EntryStack<*>
    get() = EntryStacks.of(stack)

val HTFluidResult.entryStack: EntryStack<*>
    get() = EntryStacks.of(fluid, amount)

val HTItemResult.entryIngredient: EntryIngredient
    get() = EntryIngredient.of(entryStack)

val HTFluidResult.entryIngredient: EntryIngredient
    get() = EntryIngredient.of(entryStack)
