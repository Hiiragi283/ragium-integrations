package hiiragi283.ragium.integration.rei

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.extension.component1
import hiiragi283.ragium.api.extension.component2
import hiiragi283.ragium.api.machine.HTMachineConvertible
import hiiragi283.ragium.api.machine.HTMachinePropertyKeys
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.machine.HTMachineType
import hiiragi283.ragium.api.recipe.HTMachineRecipe
import hiiragi283.ragium.common.init.RagiumBlocks
import hiiragi283.ragium.common.init.RagiumEnchantments
import hiiragi283.ragium.common.init.RagiumMachineTypes
import hiiragi283.ragium.common.init.RagiumRecipeTypes
import hiiragi283.ragium.common.screen.HTLargeProcessorScreenHandler
import hiiragi283.ragium.common.screen.HTProcessorScreenHandlerBase
import hiiragi283.ragium.common.screen.HTSimpleProcessorScreenHandler
import me.shedaniel.rei.api.client.plugins.REIClientPlugin
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler
import me.shedaniel.rei.api.common.entry.EntryStack
import me.shedaniel.rei.api.common.util.EntryStacks
import me.shedaniel.rei.plugin.common.BuiltinPlugin
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.enchantment.Enchantment
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemStack
import net.minecraft.recipe.RecipeEntry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
object RagiumREIClient : REIClientPlugin {
    init {
        RagiumAPI.log { info("REI Integration enabled!") }
    }

    //    REIClientPlugin    //

    override fun registerCategories(registry: CategoryRegistry) {
        // Machines
        RagiumAPI.getInstance().machineTypeRegistry.processors.forEach { type: HTMachineType ->
            registry.add(HTMachineRecipeCategory(type))
            HTMachineTier.entries.map(type::createEntryStack).forEach { stack: EntryStack<ItemStack> ->
                registry.addWorkstations(type.categoryId, stack)
            }
        }
        addWorkStation(registry, RagiumMachineTypes.Processor.METAL_FORMER, RagiumBlocks.MANUAL_FORGE)
        addWorkStation(registry, RagiumMachineTypes.Processor.GRINDER, RagiumBlocks.MANUAL_GRINDER)
        addWorkStation(registry, RagiumMachineTypes.Processor.MIXER, RagiumBlocks.MANUAL_MIXER)
        // Enchantment
        registry.addWorkstations(BuiltinPlugin.SMELTING, createEnchantedBook(RagiumEnchantments.SMELTING))
        addWorkStation(registry, RagiumMachineTypes.Processor.GRINDER, RagiumEnchantments.SLEDGE_HAMMER)
        addWorkStation(registry, RagiumMachineTypes.SAW_MILL, RagiumEnchantments.BUZZ_SAW)
    }

    @JvmStatic
    private fun addWorkStation(registry: CategoryRegistry, type: HTMachineConvertible, item: ItemConvertible) {
        registry.addWorkstations(type.categoryId, EntryStacks.of(item))
    }

    @JvmStatic
    private fun addWorkStation(registry: CategoryRegistry, type: HTMachineConvertible, key: RegistryKey<Enchantment>) {
        registry.addWorkstations(type.categoryId, createEnchantedBook(key))
    }

    override fun registerDisplays(registry: DisplayRegistry) {
        // Machines
        registry.registerRecipeFiller(
            HTMachineRecipe::class.java,
            RagiumRecipeTypes.MACHINE,
        ) { entry: RecipeEntry<HTMachineRecipe> ->
            val (id: Identifier, recipe: HTMachineRecipe) = entry
            when (recipe.sizeType) {
                HTMachineRecipe.SizeType.SIMPLE -> HTMachineRecipeDisplay.Simple(recipe, id)
                HTMachineRecipe.SizeType.LARGE -> HTMachineRecipeDisplay.Large(recipe, id)
            }
        }
    }

    /*override fun registerScreens(registry: ScreenRegistry) {
        // Machines
        registry.registerContainerClickArea(
            Rectangle(5 + 18 * 4, 5 + 18 * 1, 18, 18),
            HTProcessorScreen::class.java,
     *getMachineIds().toTypedArray(),
        )
    }*/

    @Suppress("UnstableApiUsage")
    override fun registerTransferHandlers(registry: TransferHandlerRegistry) {
        // Machines
        RagiumAPI
            .getInstance()
            .machineTypeRegistry
            .processors
            .forEach { type: HTMachineType ->
                val sizeType: HTMachineRecipe.SizeType = type[HTMachinePropertyKeys.RECIPE_SIZE] ?: return@forEach
                val screenClass: Class<out HTProcessorScreenHandlerBase> = when (sizeType) {
                    HTMachineRecipe.SizeType.SIMPLE -> HTSimpleProcessorScreenHandler::class
                    HTMachineRecipe.SizeType.LARGE -> HTLargeProcessorScreenHandler::class
                }.java
                val inputRange: SimpleTransferHandler.IntRange = when (sizeType) {
                    HTMachineRecipe.SizeType.SIMPLE -> SimpleTransferHandler.IntRange(0, 2)
                    HTMachineRecipe.SizeType.LARGE -> SimpleTransferHandler.IntRange(0, 3)
                }
                registry.register(SimpleTransferHandler.create(screenClass, type.categoryId, inputRange))
            }
    }
}
