package hiiragi283.ragium.integration.rei

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.recipe.HTMachineRecipe
import hiiragi283.ragium.common.init.RagiumBlocks
import hiiragi283.ragium.common.init.RagiumEnchantments
import hiiragi283.ragium.common.init.RagiumMachineKeys
import hiiragi283.ragium.common.init.RagiumRecipeTypes
import me.shedaniel.rei.api.client.plugins.REIClientPlugin
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry
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

@Environment(EnvType.CLIENT)
object RagiumREIClient : REIClientPlugin {
    init {
        RagiumAPI.log { info("REI Integration enabled!") }
    }

    //    REIClientPlugin    //

    override fun registerCategories(registry: CategoryRegistry) {
        // Machines
        RagiumAPI
            .getInstance()
            .machineRegistry
            .keys
            .forEach { key: HTMachineKey ->
                if (key.isProcessor()) {
                    registry.add(HTMachineRecipeCategory(key))
                    HTMachineTier.entries.map(key::createEntryStack).forEach { stack: EntryStack<ItemStack> ->
                        registry.addWorkstations(key.categoryId, stack)
                    }
                }
            }
        addWorkStation(registry, RagiumMachineKeys.METAL_FORMER, RagiumBlocks.MANUAL_FORGE)
        addWorkStation(registry, RagiumMachineKeys.GRINDER, RagiumBlocks.MANUAL_GRINDER)
        addWorkStation(registry, RagiumMachineKeys.MIXER, RagiumBlocks.MANUAL_MIXER)
        // Enchantment
        registry.addWorkstations(BuiltinPlugin.SMELTING, createEnchantedBook(RagiumEnchantments.SMELTING))
        addWorkStation(registry, RagiumMachineKeys.GRINDER, RagiumEnchantments.SLEDGE_HAMMER)
        addWorkStation(registry, RagiumMachineKeys.SAW_MILL, RagiumEnchantments.BUZZ_SAW)
    }

    @JvmStatic
    private fun addWorkStation(registry: CategoryRegistry, key: HTMachineKey, item: ItemConvertible) {
        registry.addWorkstations(key.categoryId, EntryStacks.of(item))
    }

    @JvmStatic
    private fun addWorkStation(registry: CategoryRegistry, key: HTMachineKey, enchantKey: RegistryKey<Enchantment>) {
        registry.addWorkstations(key.categoryId, createEnchantedBook(enchantKey))
    }

    override fun registerDisplays(registry: DisplayRegistry) {
        // Machines
        registry.registerRecipeFiller(
            HTMachineRecipe::class.java,
            RagiumRecipeTypes.MACHINE,
        ) { entry: RecipeEntry<HTMachineRecipe> -> HTMachineRecipeDisplay(entry.value, entry.id) }
    }

    /*override fun registerScreens(registry: ScreenRegistry) {
        // Machines
        registry.registerContainerClickArea(
            Rectangle(5 + 18 * 4, 5 + 18 * 1, 18, 18),
            HTProcessorScreen::class.java,
     *getMachineIds().toTypedArray(),
        )
    }

    @Suppress("UnstableApiUsage")
    override fun registerTransferHandlers(registry: TransferHandlerRegistry) {
        // Machines
        RagiumAPI
            .getInstance()
            .machineRegistry
            .processors
            .forEach { type: HTMachineType ->
                val sizeType: HTMachineType.Size = type[HTMachinePropertyKeys.RECIPE_SIZE] ?: return@forEach
                val screenClass: Class<out HTMachineScreenHandlerBase> = when (sizeType) {
                    HTMachineType.Size.SIMPLE -> HTSimpleMachineScreenHandler::class
                    HTMachineType.Size.LARGE -> HTLargeMachineScreenHandler::class
                }.java
                val inputRange: SimpleTransferHandler.IntRange = when (sizeType) {
                    HTMachineType.Size.SIMPLE -> SimpleTransferHandler.IntRange(0, 2)
                    HTMachineType.Size.LARGE -> SimpleTransferHandler.IntRange(0, 3)
                }
                registry.register(SimpleTransferHandler.create(screenClass, type.categoryId, inputRange))
            }
    }*/
}
