package hiiragi283.ragium.integration.rei

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.RagiumConfig
import hiiragi283.ragium.api.component.HTExplosionComponent
import hiiragi283.ragium.api.data.HTMachineRecipeJsonBuilder
import hiiragi283.ragium.api.extension.buildItemStack
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineRegistry
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.tags.RagiumFluidTags
import hiiragi283.ragium.api.util.DelegatedLogger
import hiiragi283.ragium.common.init.*
import hiiragi283.ragium.common.recipe.HTMachineRecipe
import hiiragi283.ragium.integration.rei.category.HTMaterialInfoCategory
import hiiragi283.ragium.integration.rei.display.HTMachineRecipeDisplay
import hiiragi283.ragium.integration.rei.display.HTMaterialInfoDisplay
import me.shedaniel.rei.api.client.plugins.REIClientPlugin
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.entry.EntryStack
import me.shedaniel.rei.api.common.plugins.PluginManager
import me.shedaniel.rei.api.common.registry.ReloadStage
import me.shedaniel.rei.api.common.util.EntryIngredients
import me.shedaniel.rei.api.common.util.EntryStacks
import me.shedaniel.rei.plugin.common.DefaultPlugin
import me.shedaniel.rei.plugin.common.displays.DefaultSmithingDisplay
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants
import net.minecraft.block.ComposterBlock
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.tag.TagKey
import org.slf4j.Logger
import java.util.*

@Suppress("UnstableApiUsage")
@Environment(EnvType.CLIENT)
object RagiumREIClient : REIClientPlugin {
    @JvmStatic
    private val logger: Logger by DelegatedLogger()
    
    @JvmField
    val MATERIAL_INFO: CategoryIdentifier<HTMaterialInfoDisplay> = CategoryIdentifier.of(RagiumAPI.id("material_info"))

    //    REIClientPlugin    //

    override fun preStage(manager: PluginManager<REIClientPlugin?>?, stage: ReloadStage?) {
        logger.info("REI Integration enabled!")
    }

    override fun registerCategories(registry: CategoryRegistry) {
        // vanilla
        HTMachineTier.entries.map(RagiumMachineKeys.MULTI_SMELTER::createEntryStack).forEach {
            registry.addWorkstations(DefaultPlugin.SMELTING, it)
        }
        registry.addWorkstations(DefaultPlugin.WAXING, EntryStacks.of(RagiumItems.BEE_WAX))

        // machine
        RagiumAPI
            .getInstance()
            .machineRegistry
            .entryMap
            .forEach { (key: HTMachineKey, entry: HTMachineRegistry.Entry) ->
                registry.add(entry.getOrDefault(REIMachinePropertyKeys.RECIPE_CATEGORY)(key))
                HTMachineTier.entries.map(key::createEntryStack).forEach { stack: EntryStack<ItemStack> ->
                    registry.addWorkstations(key.categoryId, stack)
                }
            }
        addWorkStation(registry, RagiumMachineKeys.COMPRESSOR, RagiumBlocks.MANUAL_FORGE)
        addWorkStation(registry, RagiumMachineKeys.GRINDER, RagiumBlocks.MANUAL_GRINDER)
        addWorkStation(registry, RagiumMachineKeys.MIXER, RagiumBlocks.MANUAL_MIXER)
        // material info
        registry.add(HTMaterialInfoCategory)
        registry.addWorkstations(MATERIAL_INFO, EntryStacks.of(Items.IRON_INGOT))
    }

    @JvmStatic
    private fun addWorkStation(registry: CategoryRegistry, key: HTMachineKey, item: ItemConvertible) {
        registry.addWorkstations(key.categoryId, EntryStacks.of(item))
    }

    override fun registerDisplays(registry: DisplayRegistry) {
        val config: RagiumConfig.Generator = RagiumAPI
            .getInstance()
            .config.machine.generator

        fun registerFuels(fuelTag: TagKey<Fluid>, key: HTMachineKey, amount: Long) {
            Registries.FLUID.iterateEntries(fuelTag).forEach { fluid: RegistryEntry<Fluid> ->
                HTMachineRecipeJsonBuilder
                    .create(key)
                    .fluidInput(fluid.value(), amount)
                    .transform(::HTMachineRecipeDisplay)
                    .let(registry::add)
            }
        }

        // vanilla
        (3..16)
            .map { power: Int ->
                DefaultSmithingDisplay(
                    listOf(
                        EntryIngredients.of(RagiumItems.Dynamites.SIMPLE),
                        EntryIngredients.of(Items.GUNPOWDER, power - 2),
                    ),
                    listOf(
                        EntryIngredients.of(
                            buildItemStack(RagiumItems.Dynamites.SIMPLE) {
                                add(RagiumComponentTypes.DYNAMITE, HTExplosionComponent(power.toFloat(), true))
                            },
                        ),
                    ),
                    null,
                    Optional.empty(),
                )
            }.forEach(registry::add)

        // generator
        registerFuels(RagiumFluidTags.NON_NITRO_FUELS, RagiumMachineKeys.COMBUSTION_GENERATOR, config.nonNitroFuel)
        registerFuels(RagiumFluidTags.NITRO_FUELS, RagiumMachineKeys.COMBUSTION_GENERATOR, config.nitroFuel)
        registerFuels(RagiumFluidTags.THERMAL_FUELS, RagiumMachineKeys.THERMAL_GENERATOR, config.thermalFuel)
        // steam
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.STEAM_GENERATOR)
            .itemInput(RagiumItems.COAL_CHIP)
            .fluidInput(Fluids.WATER, config.steamWater)
            .itemOutput(RagiumItems.Dusts.ASH)
            .transform(::HTMachineRecipeDisplay)
            .let(registry::add)
        // nuclear
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.NUCLEAR_REACTOR)
            .itemInput(RagiumItems.Radioactives.URANIUM_FUEL)
            .fluidInput(RagiumFluidTags.COOLANTS, config.coolant)
            .itemOutput(RagiumItems.Radioactives.NUCLEAR_WASTE)
            .transform(::HTMachineRecipeDisplay)
            .let(registry::add)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.NUCLEAR_REACTOR)
            .itemInput(RagiumItems.Radioactives.PLUTONIUM_FUEL)
            .fluidInput(RagiumFluidTags.COOLANTS, config.coolant)
            .itemOutput(RagiumItems.SLAG)
            .transform(::HTMachineRecipeDisplay)
            .let(registry::add)

        // machine
        registry.registerRecipeFiller(
            HTMachineRecipe::class.java,
            RagiumRecipeTypes.MACHINE,
            ::HTMachineRecipeDisplay,
        )
        // biomass
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.forEach { (item: ItemConvertible, chance: Float) ->
            val fixedAmount: Long = (FluidConstants.BUCKET * chance).toLong()
            HTMachineRecipeJsonBuilder
                .create(RagiumMachineKeys.BIOMASS_FERMENTER)
                .itemInput(item)
                .fluidOutput(RagiumFluids.BIOMASS, fixedAmount)
                .transform(::HTMachineRecipeDisplay)
                .let(registry::add)
        }
        // material info
        RagiumAPI
            .getInstance()
            .materialRegistry
            .keys
            .map(::HTMaterialInfoDisplay)
            .forEach(registry::add)
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
