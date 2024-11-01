package hiiragi283.ragium.integration.rei

import hiiragi283.ragium.api.machine.HTMachineConvertible
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.recipe.HTIngredient
import hiiragi283.ragium.api.recipe.HTRecipeResult
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.entry.EntryIngredient
import me.shedaniel.rei.api.common.entry.EntryStack
import me.shedaniel.rei.api.common.util.EntryStacks
import me.shedaniel.rei.impl.Internals
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentLevelEntry
import net.minecraft.fluid.Fluid
import net.minecraft.item.EnchantedBookItem
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry

//    Accessors    //

@Suppress("UnstableApiUsage")
val dynamicRegistry: () -> DynamicRegistryManager
    get() = Internals::getRegistryAccess

//    CategoryIdentifier    //

val HTMachineConvertible.categoryId: CategoryIdentifier<HTMachineRecipeDisplay>
    get() = CategoryIdentifier.of(key.id)

//    EntryStack    //

fun HTMachineConvertible.createEntryStack(tier: HTMachineTier): EntryStack<ItemStack> = EntryStacks.of(createItemStack(tier))

fun createEnchantedBook(key: RegistryKey<Enchantment>): EntryStack<ItemStack> = dynamicRegistry()
    .get(RegistryKeys.ENCHANTMENT)
    .getEntry(key)
    .map { EnchantmentLevelEntry(it, 1) }
    .map(EnchantedBookItem::forEnchantment)
    .map(EntryStacks::of)
    .orElse(EntryStacks.of(Items.ENCHANTED_BOOK))

//    Display    //

/*@JvmField
val INPUT_ENTRIES: HTPropertyKey.Defaulted<(HTMachineRecipe) -> List<EntryIngredient>> =
    HTPropertyKey.Defaulted(
        RagiumAPI.id("input_entries"),
        value = { recipe: HTMachineRecipe ->
            buildList {
                add(recipe.getInput(0)?.entryIngredient ?: EntryIngredient.empty())
                add(recipe.getInput(1)?.entryIngredient ?: EntryIngredient.empty())
                add(recipe.getInput(2)?.entryIngredient ?: EntryIngredient.empty())
                add(EntryIngredients.ofIngredient(recipe.catalyst))
            }
        },
    )

@JvmField
val OUTPUT_ENTRIES: HTPropertyKey.Defaulted<(HTMachineRecipe) -> List<EntryIngredient>> =
    HTPropertyKey.Defaulted(
        RagiumAPI.id("output_entries"),
        value = { recipe: HTMachineRecipe ->
            recipe.outputs.map(HTRecipeResult::entryIngredient)
        },
    )*/

// fun HTMachineType.getInputEntries(recipe: HTMachineRecipe): List<EntryIngredient> = getOrDefault(INPUT_ENTRIES)(recipe)

// fun HTMachineType.getOutputEntries(recipe: HTMachineRecipe): List<EntryIngredient> = getOrDefault(OUTPUT_ENTRIES)(recipe)

//    WeightedIngredient    //

val HTIngredient<*, *, *>.entryStacks: List<EntryStack<*>>
    get() = when (this) {
        is HTIngredient.Fluid -> entryMap.map { (fluid: RegistryEntry<Fluid>, amount: Long) ->
            EntryStacks.of(
                fluid.value(),
                amount,
            )
        }

        is HTIngredient.Item -> matchingStacks.map(EntryStacks::of)
    }

val HTIngredient<*, *, *>.entryIngredient: EntryIngredient
    get() = EntryIngredient.of(entryStacks)

//    HTRecipeResult    //

val HTRecipeResult<*, *, *>.entryStack: EntryStack<*>
    get() = when (this) {
        is HTRecipeResult.Fluid -> EntryStacks.of(resourceAmount.resource.fluid, resourceAmount.amount)
        is HTRecipeResult.Item -> EntryStacks.of(stack)
    }

val HTRecipeResult<*, *, *>.entryIngredient: EntryIngredient
    get() = EntryIngredient.of(entryStack)
