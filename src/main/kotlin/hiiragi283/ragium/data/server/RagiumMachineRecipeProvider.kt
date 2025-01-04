package hiiragi283.ragium.data.server

import hiiragi283.ragium.api.data.HTMachineRecipeJsonBuilder
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.tags.RagiumItemTags
import hiiragi283.ragium.common.init.*
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.fluid.Fluids
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import java.util.concurrent.CompletableFuture

class RagiumMachineRecipeProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricRecipeProvider(output, registriesFuture) {
    override fun getName(): String = "Recipes/Machine"

    override fun generate(exporter: RecipeExporter) {
        assembler(exporter)
        blastFurnace(exporter)
        compressor(exporter)
        cuttingMachine(exporter)
        grinder(exporter)
        growthChamber(exporter)
        laserTransformer(exporter)
        rockGenerator(exporter)
    }

    //    Assembler    //

    private fun assembler(exporter: RecipeExporter) {
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER, HTMachineTier.ADVANCED)
            .itemInput(RagiumItems.EMPTY_FLUID_CUBE)
            .fluidInput(RagiumFluids.ENRICHED_URANIUM_HEXAFLUORIDE)
            .fluidInput(Fluids.WATER)
            .itemOutput(RagiumItems.Radioactives.URANIUM_FUEL)
            .fluidOutput(RagiumFluids.HYDROGEN_FLUORIDE)
            .offerTo(exporter, RagiumItems.Radioactives.URANIUM_FUEL)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER, HTMachineTier.ADVANCED)
            .itemInput(RagiumItems.EMPTY_FLUID_CUBE)
            .itemInput(RagiumItems.Radioactives.NUCLEAR_WASTE, 64)
            .fluidInput(RagiumFluids.AQUA_REGIA)
            .itemOutput(RagiumItems.Radioactives.PLUTONIUM_FUEL)
            .offerTo(exporter, RagiumItems.Radioactives.PLUTONIUM_FUEL)
        // LED
        // processor
        RagiumItems.Processors.entries.forEach { processor: RagiumItems.Processors ->
            HTMachineRecipeJsonBuilder
                .create(RagiumMachineKeys.ASSEMBLER, HTMachineTier.ADVANCED)
                .itemInput(HTTagPrefix.GEM, processor.material)
                .itemInput(RagiumItems.PROCESSOR_SOCKET)
                .itemOutput(processor)
                .offerTo(exporter, processor)
        }

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER, HTMachineTier.ADVANCED)
            .itemInput(RagiumItems.Gems.RAGI_CRYSTAL, 8)
            .itemInput(RagiumItems.PROCESSOR_SOCKET)
            .itemOutput(RagiumItems.Processors.RAGIUM)
            .offerTo(exporter, RagiumItems.Processors.RAGIUM, "_from_crystal")

        // shortcuts
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER)
            .itemInput(ItemTags.LOGS, 2)
            .registerShortcut(exporter, Items.CHEST)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER)
            .itemInput(ItemTags.PLANKS, 7)
            .registerShortcut(exporter, Items.BARREL)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER)
            .itemInput(ItemTags.LOGS, 7)
            .registerShortcut(exporter, Items.BARREL, 4, "_from_logs")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ASSEMBLER)
            .itemInput(ConventionalItemTags.IRON_INGOTS, 5)
            .itemInput(ItemTags.LOGS, 2)
            .registerShortcut(exporter, Items.HOPPER)
    }

    fun HTMachineRecipeJsonBuilder.registerShortcut(
        exporter: RecipeExporter,
        output: ItemConvertible,
        count: Int = 1,
        suffix: String = "",
    ) {
        catalyst(output)
        itemOutput(output, count)
        offerTo(exporter, output, suffix)
    }

    //    Blast Furnace    //

    private fun blastFurnace(exporter: RecipeExporter) {
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ConventionalItemTags.COPPER_INGOTS)
            .itemInput(RagiumItems.Dusts.CRUDE_RAGINITE, 4)
            .itemOutput(RagiumItems.Ingots.RAGI_ALLOY)
            .offerTo(exporter, RagiumItems.Ingots.RAGI_ALLOY)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ConventionalItemTags.COPPER_INGOTS)
            .itemInput(RagiumItems.Dusts.RAGINITE)
            .itemOutput(RagiumItems.Ingots.RAGI_ALLOY)
            .offerTo(exporter, RagiumItems.Ingots.RAGI_ALLOY, "_alt")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .fluidInput(RagiumFluids.BATTER)
            .itemInput(RagiumItems.BUTTER)
            .itemOutput(RagiumBlocks.SPONGE_CAKE)
            .offerTo(exporter, RagiumBlocks.SPONGE_CAKE)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ConventionalItemTags.IRON_INGOTS)
            .itemInput(RagiumItems.Dusts.RAGINITE, 4)
            .itemOutput(RagiumItems.Ingots.RAGI_STEEL)
            .itemOutput(RagiumItems.SLAG)
            .offerTo(exporter, RagiumItems.Ingots.RAGI_STEEL)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ConventionalItemTags.IRON_INGOTS)
            .itemInput(ItemTags.COALS, 2)
            .itemOutput(RagiumItems.Ingots.STEEL)
            .itemOutput(RagiumItems.SLAG)
            .offerTo(exporter, RagiumItems.Ingots.STEEL)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE, HTMachineTier.BASIC)
            .itemInput(ConventionalItemTags.REDSTONE_DUSTS, 4)
            .itemInput(RagiumItems.Dusts.RAGINITE, 5)
            .itemOutput(RagiumItems.Gems.RAGI_CRYSTAL)
            .offerTo(exporter, RagiumItems.Gems.RAGI_CRYSTAL)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE, HTMachineTier.BASIC)
            .itemInput(RagiumItems.Ingots.STEEL)
            .itemInput(RagiumItems.Dusts.RAGI_CRYSTAL, 4)
            .itemInput(ConventionalItemTags.QUARTZ_GEMS)
            .itemOutput(RagiumItems.Ingots.REFINED_RAGI_STEEL)
            .itemOutput(RagiumItems.SLAG)
            .offerTo(exporter, RagiumItems.Ingots.REFINED_RAGI_STEEL)
        // silicon
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE, HTMachineTier.BASIC)
            .itemInput(RagiumItemTags.SILICON, 2)
            .itemInput(ItemTags.COALS)
            .itemOutput(RagiumItems.SILICON)
            .itemOutput(RagiumItems.SLAG)
            .offerTo(exporter, RagiumItems.SILICON)
        // aluminum
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE, HTMachineTier.BASIC)
            .itemInput(ItemTags.COALS, 4)
            .fluidInput(RagiumFluids.ALUMINA_SOLUTION)
            .itemOutput(RagiumItems.Ingots.ALUMINUM)
            .offerTo(exporter, RagiumItems.Ingots.ALUMINUM, "_with_coal")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE, HTMachineTier.BASIC)
            .itemInput(RagiumItems.Gems.CRYOLITE)
            .fluidInput(RagiumFluids.ALUMINA_SOLUTION, FluidConstants.INGOT * 12)
            .itemOutput(RagiumItems.Ingots.ALUMINUM, 3)
            .itemOutput(RagiumItems.SLAG, 2)
            .offerTo(exporter, RagiumItems.Ingots.ALUMINUM, "_with_cryolite")
        // deep steel
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE, HTMachineTier.BASIC)
            .itemInput(RagiumItems.Ingots.STEEL)
            .itemInput(RagiumItems.DEEPANT, 4)
            .itemOutput(RagiumItems.Ingots.DEEP_STEEL)
            .offerTo(exporter, RagiumItems.Ingots.DEEP_STEEL)
        // glass
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ItemTags.SAND)
            .fluidOutput(RagiumFluids.GLASS)
            .offerTo(exporter, RagiumFluids.GLASS, "_from_sand")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ConventionalItemTags.SANDSTONE_BLOCKS)
            .fluidOutput(RagiumFluids.GLASS, FluidConstants.BUCKET * 4)
            .offerTo(exporter, RagiumFluids.GLASS, "_from_sandstone")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ConventionalItemTags.GLASS_BLOCKS)
            .fluidOutput(RagiumFluids.GLASS)
            .offerTo(exporter, RagiumFluids.GLASS, "_from_glass")
        // advanced glass
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(ConventionalItemTags.OBSIDIANS)
            .itemInput(RagiumBlocks.Glasses.STEEL)
            .itemOutput(RagiumBlocks.Glasses.OBSIDIAN)
            .offerTo(exporter, RagiumBlocks.Glasses.OBSIDIAN)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.BLAST_FURNACE)
            .itemInput(RagiumItems.Gems.RAGIUM)
            .itemInput(RagiumBlocks.Glasses.OBSIDIAN)
            .itemOutput(RagiumBlocks.Glasses.RAGIUM)
            .offerTo(exporter, RagiumBlocks.Glasses.RAGIUM)
    }

    //    Compressor    //

    private fun compressor(exporter: RecipeExporter) {
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(RagiumItems.PULP)
            .catalyst(RagiumItems.PressMolds.PLATE)
            .itemOutput(RagiumItems.Plates.WOOD)
            .offerTo(exporter, RagiumItems.Plates.WOOD)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(HTTagPrefix.DUST, RagiumMaterialKeys.WOOD)
            .catalyst(RagiumItems.PressMolds.ROD)
            .itemOutput(Items.STICK, 4)
            .offerTo(exporter, Items.STICK)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(Items.BLAZE_POWDER, 4)
            .catalyst(RagiumItems.PressMolds.ROD)
            .itemOutput(Items.BLAZE_ROD)
            .offerTo(exporter, Items.BLAZE_ROD)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(Items.WIND_CHARGE, 4)
            .catalyst(RagiumItems.PressMolds.ROD)
            .itemOutput(Items.BREEZE_ROD)
            .offerTo(exporter, Items.BREEZE_ROD)
        // snow, ice
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .fluidInput(Fluids.WATER)
            .itemOutput(Items.SNOW_BLOCK)
            .offerTo(exporter, Items.SNOW_BLOCK)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(Items.SNOW_BLOCK)
            .itemOutput(Items.ICE)
            .offerTo(exporter, Items.ICE)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(Items.SNOWBALL, 4)
            .itemOutput(Items.ICE)
            .offerTo(exporter, Items.ICE, "_from_snowball")
        // polymer
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(RagiumItems.POLYMER_RESIN)
            .catalyst(Items.LEATHER)
            .itemOutput(Items.LEATHER)
            .offerTo(exporter, Items.LEATHER)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(RagiumItems.POLYMER_RESIN)
            .catalyst(Items.STRING)
            .itemOutput(Items.STRING, 4)
            .offerTo(exporter, Items.STRING)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(RagiumItems.POLYMER_RESIN)
            .catalyst(Items.GLASS)
            .itemOutput(Items.GLASS)
            .offerTo(exporter, Items.GLASS)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(RagiumItems.MINCED_MEAT)
            .itemOutput(RagiumItems.MEAT_INGOT)
            .offerTo(exporter, RagiumItems.MEAT_INGOT)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .fluidInput(RagiumFluids.NITROGEN, FluidConstants.BUCKET * 8)
            .fluidOutput(RagiumFluids.LIQUID_NITROGEN)
            .offerTo(exporter, RagiumFluids.LIQUID_NITROGEN)
        // shaft
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(RagiumItems.Ingots.STEEL, 2)
            .itemOutput(RagiumBlocks.SHAFT)
            .catalyst(RagiumBlocks.SHAFT)
            .offerTo(exporter, RagiumBlocks.SHAFT, "_from_steel")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(RagiumItems.Ingots.DEEP_STEEL)
            .itemOutput(RagiumBlocks.SHAFT)
            .catalyst(RagiumBlocks.SHAFT)
            .offerTo(exporter, RagiumBlocks.SHAFT, "_from_deep_steel")
        // carbon electrodes
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(ItemTags.COALS, 8)
            .catalyst(RagiumItems.PressMolds.ROD)
            .itemOutput(RagiumItems.CARBON_ELECTRODE)
            .offerTo(exporter, RagiumItems.CARBON_ELECTRODE)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(RagiumItems.CARBON_ELECTRODE)
            .itemInput(Items.BLAZE_POWDER, 8)
            .catalyst(RagiumItems.PressMolds.ROD)
            .itemOutput(RagiumItems.BLAZING_CARBON_ELECTRODE)
            .offerTo(exporter, RagiumItems.BLAZING_CARBON_ELECTRODE)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.COMPRESSOR)
            .itemInput(RagiumItems.CARBON_ELECTRODE)
            .itemInput(Items.WIND_CHARGE, 8)
            .catalyst(RagiumItems.PressMolds.ROD)
            .itemOutput(RagiumItems.CHARGED_CARBON_ELECTRODE)
            .offerTo(exporter, RagiumItems.CHARGED_CARBON_ELECTRODE)
    }

    //    Cutting Machine    //

    private fun cuttingMachine(exporter: RecipeExporter) {
        registerPlank(exporter, ItemTags.OAK_LOGS, Items.OAK_PLANKS)
        registerPlank(exporter, ItemTags.SPRUCE_LOGS, Items.SPRUCE_PLANKS)
        registerPlank(exporter, ItemTags.BIRCH_LOGS, Items.BIRCH_PLANKS)
        registerPlank(exporter, ItemTags.JUNGLE_LOGS, Items.JUNGLE_PLANKS)
        registerPlank(exporter, ItemTags.ACACIA_LOGS, Items.ACACIA_PLANKS)
        registerPlank(exporter, ItemTags.CHERRY_LOGS, Items.CHERRY_PLANKS)
        registerPlank(exporter, ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_PLANKS)
        registerPlank(exporter, ItemTags.MANGROVE_LOGS, Items.MANGROVE_PLANKS)
        registerPlank(exporter, ItemTags.CRIMSON_STEMS, Items.CRIMSON_PLANKS)
        registerPlank(exporter, ItemTags.WARPED_STEMS, Items.WARPED_PLANKS)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.CUTTING_MACHINE)
            .itemInput(ItemTags.PLANKS)
            .itemOutput(Items.STICK, 4)
            .offerTo(exporter, Items.STICK)
    }

    private fun registerPlank(exporter: RecipeExporter, log: TagKey<Item>, plank: ItemConvertible) {
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.CUTTING_MACHINE)
            .itemInput(log)
            .itemOutput(plank, 6)
            .itemOutput(RagiumItems.PULP)
            .offerTo(exporter, plank)
    }

    //    Grinder    //

    private fun grinder(exporter: RecipeExporter) {
        registerGrinder(exporter, ConventionalItemTags.COBBLESTONES to 1, Items.GRAVEL to 1)
        registerGrinder(exporter, ConventionalItemTags.WHEAT_CROPS to 1, RagiumItems.FLOUR to 1)
        registerGrinder(exporter, Items.ANCIENT_DEBRIS to 1, Items.NETHERITE_SCRAP to 2)
        registerGrinder(exporter, Items.COARSE_DIRT to 1, Items.DIRT to 1)
        registerGrinder(exporter, Items.DEEPSLATE to 1, Items.COBBLED_DEEPSLATE to 1)
        registerGrinder(exporter, Items.STONE to 1, Items.COBBLESTONE to 1)
        registerGrinder(exporter, ItemTags.BOATS to 1, RagiumItems.PULP to 5, suffix = "_from_boat")
        registerGrinder(exporter, ItemTags.FENCE_GATES to 1, RagiumItems.PULP to 4, suffix = "_from_fence_gate")
        registerGrinder(exporter, ItemTags.LOGS to 1, RagiumItems.PULP to 4, suffix = "_from_log")
        registerGrinder(exporter, ItemTags.PLANKS to 1, RagiumItems.PULP to 1, suffix = "_from_plank")
        registerGrinder(exporter, ItemTags.SAPLINGS to 2, RagiumItems.PULP to 1, suffix = "_from_sapling")
        registerGrinder(exporter, ItemTags.WOODEN_BUTTONS to 1, RagiumItems.PULP to 1, suffix = "_from_button")
        registerGrinder(exporter, ItemTags.WOODEN_DOORS to 1, RagiumItems.PULP to 2, suffix = "_from_door")
        registerGrinder(exporter, ItemTags.WOODEN_FENCES to 3, RagiumItems.PULP to 5, suffix = "_from_fence")
        registerGrinder(exporter, ItemTags.WOODEN_PRESSURE_PLATES to 1, RagiumItems.PULP to 2, suffix = "_from_plate")
        registerGrinder(exporter, ItemTags.WOODEN_SLABS to 2, RagiumItems.PULP to 1, suffix = "_from_slab")
        registerGrinder(exporter, ItemTags.WOODEN_STAIRS to 4, RagiumItems.PULP to 6, suffix = "_from_stair")
        registerGrinder(exporter, ItemTags.WOODEN_TRAPDOORS to 1, RagiumItems.PULP to 3, suffix = "_from_trap_door")
        registerGrinder(exporter, ItemTags.WOOL to 1, Items.STRING to 4)
        registerGrinder(exporter, RagiumItems.CINNAMON_STICK to 1, RagiumItems.CINNAMON_POWDER to 2)
        registerGrinder(exporter, RagiumItems.SLAG to 1, RagiumItems.ROCK_WOOL to 1)
        registerGrinder(exporter, RagiumItemTags.PROTEIN_FOODS to 1, RagiumItems.MINCED_MEAT to 1)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.GRINDER)
            .itemInput(Items.GRAVEL)
            .itemOutput(Items.FLINT)
            .catalyst(Items.FLINT)
            .offerTo(exporter, Items.FLINT, "_from_gravel")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.GRINDER)
            .itemInput(Items.GRAVEL, RagiumItems.SLAG)
            .itemOutput(Items.SAND)
            .catalyst(Items.SAND)
            .offerTo(exporter, Items.SAND)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.GRINDER)
            .itemInput(Items.NETHERRACK, 8)
            .itemOutput(RagiumItems.Dusts.BAUXITE, 2)
            .itemOutput(RagiumItems.Dusts.SULFUR)
            .offerTo(exporter, Items.NETHERRACK)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.GRINDER)
            .itemInput(ConventionalItemTags.UNCOLORED_SANDSTONE_BLOCKS)
            .itemOutput(Items.SAND, 4)
            .itemOutput(RagiumItems.Dusts.NITER)
            .offerTo(exporter, Items.SAND, "_from_sandstone")

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.GRINDER)
            .itemInput(ConventionalItemTags.RED_SANDSTONE_BLOCKS)
            .itemOutput(Items.RED_SAND, 4)
            .itemOutput(RagiumItems.Dusts.BAUXITE)
            .offerTo(exporter, Items.RED_SAND)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.GRINDER)
            .itemInput(ConventionalItemTags.SUGAR_CANE_CROPS)
            .itemOutput(Items.SUGAR, 2)
            .itemOutput(RagiumItems.PULP)
            .offerTo(exporter, Items.SUGAR)
    }

    @JvmName("registerGrinderItem")
    private fun registerGrinder(
        exporter: RecipeExporter,
        input: Pair<ItemConvertible, Int>,
        output: Pair<ItemConvertible, Int>,
        suffix: String = "",
    ) {
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.GRINDER)
            .itemInput(input.first, input.second)
            .itemOutput(output.first, output.second)
            .offerTo(exporter, output.first, suffix)
    }

    @JvmName("registerGrinderTag")
    private fun registerGrinder(
        exporter: RecipeExporter,
        input: Pair<TagKey<Item>, Int>,
        output: Pair<ItemConvertible, Int>,
        suffix: String = "",
    ) {
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.GRINDER)
            .itemInput(input.first, input.second)
            .itemOutput(output.first, output.second)
            .offerTo(exporter, output.first, suffix)
    }

    //    Growth Chamber    //

    private fun growthChamber(exporter: RecipeExporter) {
        // crops
        registerCrop(exporter, Items.BAMBOO, Items.BAMBOO)
        registerCrop(exporter, Items.BEETROOT_SEEDS, Items.BEETROOT)
        registerCrop(exporter, Items.BROWN_MUSHROOM, Items.BROWN_MUSHROOM)
        registerCrop(exporter, Items.CACTUS, Items.CACTUS, Items.SAND)
        registerCrop(exporter, Items.CARROT, Items.CARROT)
        registerCrop(exporter, Items.CHORUS_FLOWER, Items.CHORUS_FRUIT, Items.END_STONE)
        registerCrop(exporter, Items.COCOA_BEANS, Items.COCOA_BEANS, Items.JUNGLE_LOG)
        registerCrop(exporter, Items.GLOW_BERRIES, Items.GLOW_BERRIES)
        registerCrop(exporter, Items.MELON_SEEDS, Items.MELON)
        registerCrop(exporter, Items.NETHER_WART, Items.NETHER_WART, Items.SOUL_SAND)
        registerCrop(exporter, Items.PITCHER_POD, Items.PITCHER_PLANT)
        registerCrop(exporter, Items.POTATO, Items.POTATO)
        registerCrop(exporter, Items.PUMPKIN_SEEDS, Items.PUMPKIN)
        registerCrop(exporter, Items.RED_MUSHROOM, Items.RED_MUSHROOM)
        registerCrop(exporter, Items.SUGAR_CANE, Items.SUGAR_CANE, Items.SAND)
        registerCrop(exporter, Items.SWEET_BERRIES, Items.SWEET_BERRIES)
        registerCrop(exporter, Items.TORCHFLOWER_SEEDS, Items.TORCHFLOWER)
        registerCrop(exporter, Items.WHEAT_SEEDS, Items.WHEAT)

        registerCrop(exporter, Items.BROWN_MUSHROOM, Items.WARPED_FUNGUS, RagiumBlocks.MUTATED_SOIL)
        registerCrop(exporter, Items.POTATO, Items.POISONOUS_POTATO, RagiumBlocks.MUTATED_SOIL)
        registerCrop(exporter, Items.PUMPKIN_SEEDS, Items.CARVED_PUMPKIN, RagiumBlocks.MUTATED_SOIL)
        registerCrop(exporter, Items.RED_MUSHROOM, Items.CRIMSON_FUNGUS, RagiumBlocks.MUTATED_SOIL)
        registerCrop(exporter, Items.TORCHFLOWER_SEEDS, Items.TORCH, RagiumBlocks.MUTATED_SOIL)
        // trees
        registerTree(exporter, Items.ACACIA_SAPLING, Items.ACACIA_LOG)
        registerTree(exporter, Items.BIRCH_SAPLING, Items.BIRCH_LOG)
        registerTree(exporter, Items.CHERRY_SAPLING, Items.CHERRY_LOG)
        registerTree(exporter, Items.CRIMSON_FUNGUS, Items.CRIMSON_STEM, Items.NETHERRACK)
        registerTree(exporter, Items.DARK_OAK_SAPLING, Items.DARK_OAK_LOG)
        registerTree(exporter, Items.JUNGLE_SAPLING, Items.JUNGLE_LOG)
        registerTree(exporter, Items.MANGROVE_PROPAGULE, Items.MANGROVE_LOG)
        registerTree(exporter, Items.OAK_SAPLING, Items.OAK_LOG)
        registerTree(exporter, Items.SPRUCE_SAPLING, Items.SPRUCE_LOG)
        registerTree(exporter, Items.WARPED_FUNGUS, Items.WARPED_STEM, Items.NETHERRACK)
        // other
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.GROWTH_CHAMBER, HTMachineTier.BASIC)
            .itemInput(ConventionalItemTags.AMETHYST_GEMS)
            .itemInput(HTTagPrefix.DUST, RagiumMaterialKeys.QUARTZ)
            .fluidInput(Fluids.WATER)
            .itemOutput(Items.AMETHYST_SHARD, 3)
            .offerTo(exporter, Items.AMETHYST_SHARD)
    }

    private fun registerCrop(
        exporter: RecipeExporter,
        seed: ItemConvertible,
        crop: ItemConvertible,
        soil: ItemConvertible = Items.DIRT,
    ) {
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.GROWTH_CHAMBER)
            .itemInput(seed)
            .catalyst(soil)
            .itemOutput(crop)
            .itemOutput(seed)
            .offerTo(exporter, crop)
    }

    private fun registerTree(
        exporter: RecipeExporter,
        sapling: ItemConvertible,
        log: ItemConvertible,
        soil: ItemConvertible = Items.DIRT,
    ) {
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.GROWTH_CHAMBER)
            .itemInput(sapling)
            .catalyst(soil)
            .itemOutput(log, 8)
            .itemOutput(sapling)
            .offerTo(exporter, log)
    }

    //    Laser Transformer    //

    private fun laserTransformer(exporter: RecipeExporter) {
        // primitive
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.LASER_TRANSFORMER)
            .itemInput(ConventionalItemTags.GLASS_BLOCKS_COLORLESS, 8)
            .itemOutput(Items.QUARTZ)
            .offerTo(exporter, Items.QUARTZ)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.LASER_TRANSFORMER)
            .itemInput(Items.ICE)
            .itemOutput(Items.PRISMARINE_CRYSTALS)
            .offerTo(exporter, Items.PRISMARINE_CRYSTALS)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.LASER_TRANSFORMER)
            .itemInput(Items.PACKED_ICE)
            .itemOutput(Items.PRISMARINE_SHARD)
            .offerTo(exporter, Items.PRISMARINE_SHARD)
        // basic
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.LASER_TRANSFORMER, HTMachineTier.BASIC)
            .itemInput(ItemTags.COALS, 64)
            .itemOutput(Items.DIAMOND)
            .offerTo(exporter, Items.DIAMOND)
        // advanced
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.LASER_TRANSFORMER, HTMachineTier.ADVANCED)
            .itemInput(RagiumItems.Gems.RAGI_CRYSTAL, 8)
            .itemOutput(RagiumItems.Gems.RAGIUM)
            .offerTo(exporter, RagiumItems.Gems.RAGIUM)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.LASER_TRANSFORMER, HTMachineTier.ADVANCED)
            .itemInput(Items.BLUE_ICE, 64)
            .itemOutput(Items.HEART_OF_THE_SEA)
            .offerTo(exporter, Items.HEART_OF_THE_SEA)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.LASER_TRANSFORMER, HTMachineTier.ADVANCED)
            .itemInput(ConventionalItemTags.STORAGE_BLOCKS_GOLD, 8)
            .itemInput(Items.APPLE)
            .itemOutput(Items.ENCHANTED_GOLDEN_APPLE)
            .offerTo(exporter, Items.ENCHANTED_GOLDEN_APPLE)
    }

    //    Rock Generator    //

    private fun rockGenerator(exporter: RecipeExporter) {
        registerRock(exporter, Items.STONE, HTMachineTier.PRIMITIVE)
        registerRock(exporter, Items.COBBLESTONE, HTMachineTier.PRIMITIVE)
        registerRock(exporter, Items.GRANITE, HTMachineTier.PRIMITIVE)
        registerRock(exporter, Items.DIORITE, HTMachineTier.PRIMITIVE)
        registerRock(exporter, Items.ANDESITE, HTMachineTier.PRIMITIVE)
        registerRock(exporter, RagiumBlocks.Stones.SLATE, HTMachineTier.PRIMITIVE)

        registerRock(exporter, Items.DEEPSLATE, HTMachineTier.BASIC)
        registerRock(exporter, Items.COBBLED_DEEPSLATE, HTMachineTier.BASIC)
        registerRock(exporter, Items.CALCITE, HTMachineTier.BASIC)
        registerRock(exporter, Items.TUFF, HTMachineTier.BASIC)
        registerRock(exporter, Items.DRIPSTONE_BLOCK, HTMachineTier.BASIC)
        registerRock(exporter, Items.NETHERRACK, HTMachineTier.BASIC)
        registerRock(exporter, Items.BASALT, HTMachineTier.BASIC)
        registerRock(exporter, Items.BLACKSTONE, HTMachineTier.BASIC)
        registerRock(exporter, RagiumBlocks.Stones.ASPHALT, HTMachineTier.BASIC)
        registerRock(exporter, RagiumBlocks.Stones.GYPSUM, HTMachineTier.BASIC)

        registerRock(exporter, Items.END_STONE, HTMachineTier.ADVANCED)

        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ROCK_GENERATOR, HTMachineTier.ADVANCED)
            .fluidInput(Fluids.LAVA)
            .catalyst(Items.OBSIDIAN)
            .itemOutput(Items.OBSIDIAN)
            .offerTo(exporter, Items.OBSIDIAN)
    }

    private fun registerRock(exporter: RecipeExporter, rock: ItemConvertible, tier: HTMachineTier) {
        HTMachineRecipeJsonBuilder
            .create(RagiumMachineKeys.ROCK_GENERATOR, tier)
            .catalyst(rock)
            .itemOutput(rock, 8)
            .offerTo(exporter, rock)
    }
}
