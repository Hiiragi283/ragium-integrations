package hiiragi283.ragium.data.client

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.common.init.RagiumBlocks
import hiiragi283.ragium.common.init.RagiumEntityTypes
import hiiragi283.ragium.common.init.RagiumFluids
import hiiragi283.ragium.common.init.RagiumItemGroup
import hiiragi283.ragium.common.init.RagiumItems
import hiiragi283.ragium.common.init.RagiumMachineKeys
import hiiragi283.ragium.common.init.RagiumMaterialKeys
import hiiragi283.ragium.common.init.RagiumTranslationKeys
import hiiragi283.ragium.integration.patchouli.HTPatchouliCategory
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.world.World
import java.util.concurrent.CompletableFuture

class RagiumEnglishLangProvider(output: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricLanguageProvider(output, registryLookup) {
    override fun generateTranslations(registryLookup: RegistryWrapper.WrapperLookup, builder: TranslationBuilder) {
        RagiumFluids.entries.forEach { fluid: RagiumFluids ->
            builder.add(
                fluid.translationKey,
                fluid.enName,
            )
        }

        builder.add("modmenu.descriptionTranslation.ragium", "A new tech mod for Fabric")
        builder.add("modmenu.nameTranslation.ragium", RagiumAPI.MOD_NAME)
        builder.add("text.autoconfig.ragium.title", RagiumAPI.MOD_NAME)
        builder.add(RagiumTranslationKeys.FOR_INTEGRATION, "Contents for integration with other mods")
        builder.add(RagiumTranslationKeys.PRESS_CTRL, "Press Ctrl to show descriptions")
        // Advancements
        builder.add(RagiumTranslationKeys.ADVANCEMENT_BUJIN, "Tycoon the Racoon")
        builder.add(RagiumTranslationKeys.ADVANCEMENT_STELLA_SUIT, "Synthetically Treated External Lightweight-Layered Augment")
        builder.add(RagiumTranslationKeys.ADVANCEMENT_THIS_CAKE_IS_DIE, "This cake is DIE.")
        // Blocks
        builder.add(RagiumBlocks.Creatives.CRATE, "Creative Crate")
        builder.add(RagiumBlocks.Creatives.DRUM, "Creative Drum")
        builder.add(RagiumBlocks.Creatives.EXPORTER, "Creative Exporter")
        builder.add(RagiumBlocks.Creatives.SOURCE, "Creative Power Source")

        builder.add(RagiumBlocks.MUTATED_SOIL, "Mutated Soil")
        builder.add(RagiumBlocks.POROUS_NETHERRACK, "Porous Netherrack")

        builder.add(RagiumBlocks.Slabs.ASPHALT, "Asphalt Slab")
        builder.add(RagiumBlocks.Slabs.GYPSUM, "Gypsum Slab")
        builder.add(RagiumBlocks.Slabs.POLISHED_ASPHALT, "Polished Asphalt Slab")
        builder.add(RagiumBlocks.Slabs.POLISHED_GYPSUM, "Polished Gypsum Slab")
        builder.add(RagiumBlocks.Slabs.POLISHED_SLATE, "Polished Slate Slab")
        builder.add(RagiumBlocks.Slabs.SLATE, "Slate Slab")
        builder.add(RagiumBlocks.Stairs.ASPHALT, "Asphalt Stairs")
        builder.add(RagiumBlocks.Stairs.GYPSUM, "Gypsum Stairs")
        builder.add(RagiumBlocks.Stairs.POLISHED_ASPHALT, "Polished Asphalt Stairs")
        builder.add(RagiumBlocks.Stairs.POLISHED_GYPSUM, "Polished Gypsum Stairs")
        builder.add(RagiumBlocks.Stairs.POLISHED_SLATE, "Polished Slate Stairs")
        builder.add(RagiumBlocks.Stairs.SLATE, "Slate Stairs")
        builder.add(RagiumBlocks.Stones.ASPHALT, "Asphalt")
        builder.add(RagiumBlocks.Stones.GYPSUM, "Gypsum")
        builder.add(RagiumBlocks.Stones.POLISHED_ASPHALT, "Polished Asphalt")
        builder.add(RagiumBlocks.Stones.POLISHED_GYPSUM, "Polished Gypsum")
        builder.add(RagiumBlocks.Stones.POLISHED_SLATE, "Polished Slate")
        builder.add(RagiumBlocks.Stones.SLATE, "Slate")

        builder.add(RagiumBlocks.WhiteLines.SIMPLE, "White Line")
        builder.add(RagiumBlocks.WhiteLines.T_SHAPED, "White Line (T)")
        builder.add(RagiumBlocks.WhiteLines.CROSS, "White Line (Cross)")

        builder.add(RagiumBlocks.Glasses.STEEL, "Steel Glass")
        builder.add(RagiumBlocks.Glasses.OBSIDIAN, "Obsidian Glass")
        builder.add(RagiumBlocks.Glasses.RAGIUM, "Ragium Glass")

        builder.add(RagiumBlocks.PLASTIC_BLOCK, "Plastic Block")

        builder.add(RagiumBlocks.SPONGE_CAKE, "Sponge Cake")
        builder.add(RagiumBlocks.SWEET_BERRIES_CAKE, "Sweet Berries Cake")

        builder.add(RagiumBlocks.AUTO_ILLUMINATOR, "Auto Illuminator")
        builder.add(RagiumBlocks.BACKPACK_CRATE, "Backpack Crate")
        builder.add(RagiumBlocks.ITEM_DISPLAY, "Item Display")
        builder.add(RagiumBlocks.EXTENDED_PROCESSOR, "Extended Processor")
        builder.add(RagiumBlocks.MANUAL_FORGE, "Ragi-Anvil")
        builder.add(RagiumBlocks.MANUAL_GRINDER, "Ragi-Grinder")
        builder.add(RagiumBlocks.MANUAL_MIXER, "Ragi-Basin")
        builder.add(RagiumBlocks.MACHINE_INTERFACE, "E.M.I.")
        builder.add(RagiumBlocks.NETWORK_INTERFACE, "E.N.I.")
        builder.add(RagiumBlocks.OPEN_CRATE, "Open Crate")
        builder.add(RagiumBlocks.SHAFT, "Shaft")
        builder.add(RagiumBlocks.TELEPORT_ANCHOR, "Teleport Anchor")
        builder.add(RagiumBlocks.VOID_CRATE, "Void Crate")

        builder.add(RagiumBlocks.Pipes.STONE, "Stone Pipe")
        builder.add(RagiumBlocks.Pipes.WOODEN, "Wooden Pipe")
        builder.add(RagiumBlocks.Pipes.IRON, "Iron Pipe")
        builder.add(RagiumBlocks.Pipes.COPPER, "Copper Pipe")
        builder.add(RagiumBlocks.Pipes.UNIVERSAL, "Universal Pipe")

        builder.add(RagiumBlocks.CrossPipes.STEEL, "Steel Pipe")
        builder.add(RagiumBlocks.CrossPipes.GOLD, "Gold Pipe")

        builder.add(RagiumBlocks.PipeStations.ITEM, "Item Pipe Station")
        builder.add(RagiumBlocks.PipeStations.FLUID, "Fluid Pipe Station")

        builder.add(RagiumBlocks.FilteringPipes.ITEM, "Item Filtering Pipe")
        builder.add(RagiumBlocks.FilteringPipes.FLUID, "Fluid Filtering Pipe")

        builder.add(RagiumTranslationKeys.AUTO_ILLUMINATOR, "Place lights in area of %s block radius")
        builder.add(RagiumTranslationKeys.MACHINE_INTERFACE, "Extend the adjacent side for front machine")
        builder.add(RagiumTranslationKeys.LARGE_PROCESSOR, "Extend processor machine inside the multiblock")
        builder.add(RagiumTranslationKeys.MANUAL_GRINDER, "Insert items by hopper or something else")
        builder.add(RagiumTranslationKeys.MUTATED_SOIL, "Used for Growth Chamber")
        builder.add(
            RagiumTranslationKeys.NETWORK_INTERFACE,
            "Connect Wireless Network and energy cables from other mod",
        )
        builder.add(RagiumTranslationKeys.OBSIDIAN_GLASS, "A glass as hard as Obsidian")
        builder.add(RagiumTranslationKeys.OPEN_CRATE, "Drop inserted items below")
        builder.add(RagiumTranslationKeys.PIPE_STATION, "Priority transport to adjacent storage")
        builder.add(RagiumTranslationKeys.POROUS_NETHERRACK, "Absorb around lava like sponge but not reusable")
        builder.add(RagiumTranslationKeys.RAGIUM_GLASS, "A glass as hard as Bedrock")
        builder.add(RagiumTranslationKeys.SPONGE_CAKE, "Decrease falling damage when land on")
        builder.add(RagiumTranslationKeys.STEEL_GLASS, "A glass as hard as Water")
        builder.add(RagiumTranslationKeys.TRASH_BOX, "Remove ALL inserted items or fluids")

        builder.add(RagiumTranslationKeys.CRATE_CAPACITY, "Capacity: %s Items")

        builder.add(RagiumTranslationKeys.TRANSPORTER_FLUID_SPEED, "Fluid Speed: %s Units/s")
        builder.add(RagiumTranslationKeys.TRANSPORTER_ITEM_SPEED, "Item Speed: %s /s")

        builder.add(RagiumTranslationKeys.EXPORTER_FLUID_FILTER, "Current Fluid Filter: %s")
        builder.add(RagiumTranslationKeys.EXPORTER_ITEM_FILTER, "Current Item Filter: %s")
        // Contents
        builder.add(RagiumTranslationKeys.BATTERY, "Battery")
        builder.add(RagiumTranslationKeys.CASING, "Casing")
        builder.add(RagiumTranslationKeys.CIRCUIT, "Circuit")
        builder.add(RagiumTranslationKeys.CIRCUIT_BOARD, "Circuit Board")
        builder.add(RagiumTranslationKeys.COIL, "Coil")
        builder.add(RagiumTranslationKeys.CRATE, "Crate")
        builder.add(RagiumTranslationKeys.DRILL_HEAD, "Drill Head")
        builder.add(RagiumTranslationKeys.DRUM, "Drum")
        builder.add(RagiumTranslationKeys.EXPORTER, "Exporter")
        builder.add(RagiumTranslationKeys.GRATE, "Grate")
        builder.add(RagiumTranslationKeys.HULL, "Hull")
        builder.add(RagiumTranslationKeys.PLASTIC, "Plastic")
        // Entity
        builder.add(RagiumEntityTypes.DYNAMITE, "Dynamite")
        builder.add(RagiumEntityTypes.ANVIL_DYNAMITE, "Anvil Dynamite")
        builder.add(RagiumEntityTypes.BLAZING_DYNAMITE, "Blazing Dynamite")
        builder.add(RagiumEntityTypes.BEDROCK_DYNAMITE, "Bedrock Dynamite")
        builder.add(RagiumEntityTypes.FLATTENING_DYNAMITE, "Flattening Dynamite")
        builder.add(RagiumEntityTypes.FROSTING_DYNAMITE, "Frosting Dynamite")
        // Fluid
        builder.add(RagiumTranslationKeys.FLUID_AMOUNT, "Amount: %s")
        builder.add(RagiumTranslationKeys.FLUID_CAPACITY, "Capacity: %s")
        builder.add(RagiumTranslationKeys.FLUID_TITLE, "Fluid: %s")
        builder.add(RagiumTranslationKeys.FORMATTED_FLUID, "%s B, %s Units")
        // Item
        builder.add(RagiumItems.SteelArmors.HELMET, "Steel Helmet")
        builder.add(RagiumItems.SteelArmors.CHESTPLATE, "Steel Chestplate")
        builder.add(RagiumItems.SteelArmors.LEGGINGS, "Steel Leggings")
        builder.add(RagiumItems.SteelArmors.BOOTS, "Steel Boots")

        builder.add(RagiumItems.DeepSteelArmors.HELMET, "Deep Steel Helmet")
        builder.add(RagiumItems.DeepSteelArmors.CHESTPLATE, "Deep Steel Chestplate")
        builder.add(RagiumItems.DeepSteelArmors.LEGGINGS, "Deep Steel Leggings")
        builder.add(RagiumItems.DeepSteelArmors.BOOTS, "Deep Steel Boots")

        builder.add(RagiumItems.StellaSuits.GOGGLE, "S.T.E.L.L.A. Goggles")
        builder.add(RagiumItems.StellaSuits.JACKET, "S.T.E.L.L.A. Jacket")
        builder.add(RagiumItems.StellaSuits.LEGGINGS, "S.T.E.L.L.A. Leggings")
        builder.add(RagiumItems.StellaSuits.BOOTS, "S.T.E.L.L.A. Boots")

        builder.add(RagiumItems.DRAGONIC_ELYTRA, "Dragonic Elytra")

        builder.add(RagiumItems.SteelTools.AXE, "Steel Axe")
        builder.add(RagiumItems.SteelTools.HOE, "Steel Hoe")
        builder.add(RagiumItems.SteelTools.PICKAXE, "Steel Pickaxe")
        builder.add(RagiumItems.SteelTools.SHOVEL, "Steel Shovel")
        builder.add(RagiumItems.SteelTools.SWORD, "Steel Sword")

        builder.add(RagiumItems.DeepSteelTools.AXE, "Deep Steel Axe")
        builder.add(RagiumItems.DeepSteelTools.HOE, "Deep Steel Hoe")
        builder.add(RagiumItems.DeepSteelTools.PICKAXE, "Deep Steel Pickaxe")
        builder.add(RagiumItems.DeepSteelTools.SHOVEL, "Deep Steel Shovel")
        builder.add(RagiumItems.DeepSteelTools.SWORD, "Deep Steel Sword")

        builder.add(RagiumItems.Dynamites.SIMPLE, "Dynamite")
        builder.add(RagiumItems.Dynamites.ANVIL, "Anvil Dynamite")
        builder.add(RagiumItems.Dynamites.BEDROCK, "Bedrock Dynamite")
        builder.add(RagiumItems.Dynamites.BLAZING, "Blazing Dynamite")
        builder.add(RagiumItems.Dynamites.FLATTENING, "Flattening Dynamite")
        builder.add(RagiumItems.Dynamites.FROSTING, "Frosting Dynamite")

        builder.add(RagiumItems.BACKPACK, "Backpack")
        builder.add(RagiumItems.ECHO_BULLET, "Echo Bullet")
        builder.add(RagiumItems.EMPTY_FLUID_CUBE, "Fluid Cube (Empty)")
        builder.add(RagiumItems.FILLED_FLUID_CUBE, "Fluid Cube (%s)")
        builder.add(RagiumItems.FLUID_FILTER, "Fluid Filter")
        builder.add(RagiumItems.FORGE_HAMMER, "Forge Hammer")
        builder.add(RagiumItems.GIGANT_HAMMER, "Gigant Hammer")
        builder.add(RagiumItems.ITEM_FILTER, "Item Filter")
        builder.add(RagiumItems.RAGI_WRENCH, "Ragi-Wrench")
        builder.add(RagiumItems.RAGIUM_SABER, "Ragium Saber")
        builder.add(RagiumItems.STELLA_SABER, "S.T.E.L.L.A. Saber")
        builder.add(RagiumItems.TRADER_CATALOG, "Trader Catalog")

        builder.add(RagiumItems.SWEET_BERRIES_CAKE_PIECE, "A piece of Sweet Berries Cake")
        builder.add(RagiumItems.MELON_PIE, "Melon Pie")

        builder.add(RagiumItems.BUTTER, "Butter")
        builder.add(RagiumItems.CARAMEL, "Caramel")
        builder.add(RagiumItems.DOUGH, "Dough")
        builder.add(RagiumItems.FLOUR, "Flour")

        builder.add(RagiumItems.CHOCOLATE, "Chocolate")
        builder.add(RagiumItems.CHOCOLATE_APPLE, "Chocolate Apple")
        builder.add(RagiumItems.CHOCOLATE_BREAD, "Chocolate Bread")
        builder.add(RagiumItems.CHOCOLATE_COOKIE, "Chocolate Cookie")

        builder.add(RagiumItems.CINNAMON_STICK, "Cinnamon Stick")
        builder.add(RagiumItems.CINNAMON_POWDER, "Cinnamon Powder")
        builder.add(RagiumItems.CINNAMON_ROLL, "Cinnamon Roll")

        builder.add(RagiumItems.MINCED_MEAT, "Minced Meat")
        builder.add(RagiumItems.MEAT_INGOT, "Meat Ingot")
        builder.add(RagiumItems.COOKED_MEAT_INGOT, "Cooked Meat Ingot")
        builder.add(RagiumItems.CANNED_COOKED_MEAT, "Canned Cooked Meat")

        builder.add(RagiumItems.AMBROSIA, "Ambrosia")

        builder.add(RagiumItems.BEE_WAX, "Bee Wax")
        builder.add(RagiumItems.BLAZING_CARBON_ELECTRODE, "Blazing Carbon Electrode")
        builder.add(RagiumItems.CARBON_ELECTRODE, "Carbon Electrode")
        builder.add(RagiumItems.CHARGED_CARBON_ELECTRODE, "Charged Carbon Electrode")
        builder.add(RagiumItems.COAL_CHIP, "Coal Chip")
        builder.add(RagiumItems.CRIMSON_CRYSTAL, "Crimson Crystal")
        builder.add(RagiumItems.CRUDE_SILICON, "Crude Silicon")
        builder.add(RagiumItems.DEEPANT, "Deepant")
        builder.add(RagiumItems.ENGINE, "V8 Engine")
        // builder.add(RagiumItems.ENGINEERING_PLASTIC_PLATE, "Engineering Plastic Plate")
        builder.add(RagiumItems.GLASS_SHARD, "Glass Shard")
        builder.add(RagiumItems.LASER_EMITTER, "Laser Emitter")
        builder.add(RagiumItems.LED, "L.E.D.")
        builder.add(RagiumItems.LUMINESCENCE_DUST, "Luminescence Dust")
        builder.add(RagiumItems.OBSIDIAN_TEAR, "Obsidian Tear")
        // builder.add(RagiumItems.PLASTIC_PLATE, "Plastic Plate")
        builder.add(RagiumItems.POLYMER_RESIN, "Polymer Resin")
        builder.add(RagiumItems.PROCESSOR_SOCKET, "Processor Socket")
        builder.add(RagiumItems.PULP, "Pulp")
        builder.add(RagiumItems.RAGI_ALLOY_COMPOUND, "Ragi-Alloy Compound")
        builder.add(RagiumItems.REFINED_SILICON, "Refined Silicon")
        builder.add(RagiumItems.RESIDUAL_COKE, "Residual Coke")
        // builder.add(RagiumItems.ROCK_WOOL, "Rock Wool")
        builder.add(RagiumItems.SILICON, "Silicon")
        builder.add(RagiumItems.SLAG, "Slag")
        builder.add(RagiumItems.SOAP, "Soap")
        builder.add(RagiumItems.SOLAR_PANEL, "Solar Panel")
        builder.add(RagiumItems.STELLA_PLATE, "S.T.E.L.L.A. Plate")
        builder.add(RagiumItems.WARPED_CRYSTAL, "Warped Crystal")

        builder.add(RagiumItems.Radioactives.URANIUM_FUEL, "Uranium Fuel")
        builder.add(RagiumItems.Radioactives.PLUTONIUM_FUEL, "Plutonium Fuel")
        builder.add(RagiumItems.Radioactives.YELLOW_CAKE, "Yellow Cake")
        builder.add(RagiumItems.Radioactives.YELLOW_CAKE_PIECE, "A piece of Yellow Cake")
        builder.add(RagiumItems.Radioactives.NUCLEAR_WASTE, "Nuclear Waste")

        builder.add(RagiumItems.RAGI_TICKET, "Ragi-Ticket")

        builder.add(RagiumItems.Processors.DIAMOND, "Diamond Processor")
        builder.add(RagiumItems.Processors.EMERALD, "Emerald Processor")
        builder.add(RagiumItems.Processors.NETHER_STAR, "Nether Star Processor")
        builder.add(RagiumItems.Processors.RAGIUM, "Ragium Processor")

        builder.add(RagiumItems.PressMolds.GEAR, "Press Mold (Gear)")
        builder.add(RagiumItems.PressMolds.PIPE, "Press Mold (Pipe)")
        builder.add(RagiumItems.PressMolds.PLATE, "Press Mold (Plate)")
        builder.add(RagiumItems.PressMolds.ROD, "Press Mold (Rod)")
        builder.add(RagiumItems.PressMolds.WIRE, "Press Mold (Wire)")

        builder.add(RagiumTranslationKeys.ANVIL_DYNAMITE, "Place Anvil when hit")
        builder.add(RagiumTranslationKeys.BACKPACK, "Shares inventory between the same color")
        builder.add(RagiumTranslationKeys.BEDROCK_DYNAMITE, "Flatten Bedrocks inside hit chunk")
        builder.add(RagiumTranslationKeys.BLAZING_DYNAMITE, "Ignite fire when hit on entity or block")
        builder.add(RagiumTranslationKeys.FLATTENING_DYNAMITE, "Remove ALL blocks above when hit")
        builder.add(RagiumTranslationKeys.FROSTING_DYNAMITE, "Place Powder Snow when hit")
        builder.add(
            RagiumTranslationKeys.RAGI_WRENCH,
            "Right-click to rotate horizontally, change front when sneaking",
        )
        builder.add(RagiumTranslationKeys.ROPE, "Place down Ropes when land on")
        builder.add(RagiumTranslationKeys.TRADER_CATALOG, "Right-click to open Wandering Trader's Screen")
        builder.add(
            RagiumTranslationKeys.WARPED_CRYSTAL,
            "Right-click to teleport on linked Teleport Anchor, or bind it with sneaking",
        )

        builder.add(RagiumTranslationKeys.DYNAMITE_DESTROY, "Destroy: %s")
        builder.add(RagiumTranslationKeys.DYNAMITE_POWER, "Power: %s")
        builder.add(RagiumTranslationKeys.FILTER, "Right-click on Exporters to apply, or open setting menu")
        builder.add(RagiumTranslationKeys.FILTER_FORMAT, "Example: \"minecraft:iron_ingot\", [\"minecraft:water\"], \"#c:ores\"")
        builder.add(RagiumTranslationKeys.RADIOACTIVITY, "Radioactivity Level: %s")
        builder.add(RagiumTranslationKeys.WARPED_CRYSTAL_DESTINATION, "Destination: %s")
        // Item Group
        builder.add(RagiumItemGroup.BUILDING, "Ragium - Buildings")
        builder.add(RagiumItemGroup.ITEM, "Ragium - Items")
        builder.add(RagiumItemGroup.FLUID, "Ragium - Fluids")
        builder.add(RagiumItemGroup.MACHINE, "Ragium - Machines")
        builder.add(RagiumItemGroup.TRANSFER, "Ragium - Transfer")
        // Machine
        builder.add(RagiumTranslationKeys.MACHINE_NAME, "Name: %s")
        builder.add(RagiumTranslationKeys.MACHINE_TIER, "Tier: %s")
        builder.add(RagiumTranslationKeys.MACHINE_NETWORK_ENERGY, "Network Energy: %s Units")
        builder.add(RagiumTranslationKeys.MACHINE_RECIPE_COST, "Recipe cost: %s E")
        builder.add(RagiumTranslationKeys.MACHINE_SHOW_PREVIEW, "Show preview: %s")

        builder.add(RagiumTranslationKeys.MULTI_SHAPE_ERROR, "Not matching condition; %s at %ss")
        builder.add(RagiumTranslationKeys.MULTI_SHAPE_SUCCESS, "The structure is valid!")
        // Machine Tier
        builder.add(HTMachineTier.PRIMITIVE, "Primitive", "Primitive %s")
        builder.add(HTMachineTier.BASIC, "Basic", "Basic %s")
        builder.add(HTMachineTier.ADVANCED, "Advanced", "Advanced %s")
        // Machine Type
        builder.add(
            RagiumMachineKeys.BEDROCK_MINER,
            "Bedrock Miner",
            "Collect minerals from Bedrock",
        )
        builder.add(
            RagiumMachineKeys.BIOMASS_FERMENTER,
            "Biomass Fermenter",
            "Produce Biomass from Composter inputs",
        )
        builder.add(
            RagiumMachineKeys.DRAIN,
            "Drain",
            "Drains fluids from front, experience from up, and fluid cube in slot",
        )
        builder.add(
            RagiumMachineKeys.FLUID_DRILL,
            "Fluid Drill",
            "Pump up fluids from specified biomes",
        )
        builder.add(
            RagiumMachineKeys.ROCK_GENERATOR,
            "Rock Generator",
            "Require water and lava source around",
        )

        builder.add(
            RagiumMachineKeys.COMBUSTION_GENERATOR,
            "Combustion Generator",
            "Generate energy from liquid fuels",
        )
        builder.add(
            RagiumMachineKeys.NUCLEAR_REACTOR,
            "Nuclear Reactor",
            "Generate energy from radioactive fuels",
        )
        builder.add(
            RagiumMachineKeys.SOLAR_GENERATOR,
            "Solar Generator",
            "Generate energy in daytime",
        )
        builder.add(
            RagiumMachineKeys.STEAM_GENERATOR,
            "Steam Generator",
            "Generate energy from water and coal like fuels",
        )
        builder.add(
            RagiumMachineKeys.THERMAL_GENERATOR,
            "Thermal Generator",
            "Generate energy from hot fluids",
        )
        builder.add(
            RagiumMachineKeys.VIBRATION_GENERATOR,
            "Vibration Generator",
            "Augh! Pervert! Death penalty!",
        )

        builder.add(RagiumMachineKeys.ASSEMBLER, "Assembler", "I am Dr.Doom")
        builder.add(RagiumMachineKeys.BLAST_FURNACE, "Large Blast Furnace", "Smelt multiple ingredients into one")
        builder.add(RagiumMachineKeys.CHEMICAL_REACTOR, "Chemical Reactor", "Are You Ready?")
        builder.add(RagiumMachineKeys.COMPRESSOR, "Compressor", "saves.zip.zip")
        builder.add(RagiumMachineKeys.CUTTING_MACHINE, "Cutting Machine", "Process Logs more efficiently")
        builder.add(RagiumMachineKeys.DISTILLATION_TOWER, "Distillation Tower", "Process Crude Oil")
        builder.add(RagiumMachineKeys.ELECTROLYZER, "Electrolyzer", "Elek On")
        builder.add(RagiumMachineKeys.EXTRACTOR, "Extractor", "Something like Centrifuge")
        builder.add(RagiumMachineKeys.GRINDER, "Grinder", "Crush Up")
        builder.add(RagiumMachineKeys.GROWTH_CHAMBER, "Growth Chamber")
        builder.add(RagiumMachineKeys.INFUSER, "Infuser", "Something not like Centrifuge")
        builder.add(RagiumMachineKeys.LASER_TRANSFORMER, "Laser Transformer")
        builder.add(RagiumMachineKeys.LARGE_CHEMICAL_REACTOR, "large Chemical Reactor", "larger than you think")
        builder.add(RagiumMachineKeys.MIXER, "Mixer", "Genomix...")
        builder.add(RagiumMachineKeys.MULTI_SMELTER, "Multi Smelter", "Smelt multiple items at once")
        // Material
        builder.add(RagiumMaterialKeys.ALKALI, "Alkali")
        builder.add(RagiumMaterialKeys.ALUMINUM, "Aluminum")
        builder.add(RagiumMaterialKeys.ASH, "Ash")
        builder.add(RagiumMaterialKeys.BAUXITE, "Bauxite")
        builder.add(RagiumMaterialKeys.BRASS, "Brass")
        builder.add(RagiumMaterialKeys.BRONZE, "Bronze")
        builder.add(RagiumMaterialKeys.CINNABAR, "Cinnabar")
        builder.add(RagiumMaterialKeys.COAL, "Coal")
        builder.add(RagiumMaterialKeys.COPPER, "Copper")
        builder.add(RagiumMaterialKeys.CRUDE_RAGINITE, "Crude Raginite")
        builder.add(RagiumMaterialKeys.CRYOLITE, "Cryolite")
        builder.add(RagiumMaterialKeys.DEEP_STEEL, "Deep Steel")
        builder.add(RagiumMaterialKeys.DIAMOND, "Diamond")
        builder.add(RagiumMaterialKeys.DRAGONIUM, "Dragonium")
        builder.add(RagiumMaterialKeys.ECHORIUM, "Echorium")
        builder.add(RagiumMaterialKeys.ELECTRUM, "Electrum")
        builder.add(RagiumMaterialKeys.EMERALD, "Emerald")
        builder.add(RagiumMaterialKeys.FIERIUM, "Fierium")
        builder.add(RagiumMaterialKeys.FLUORITE, "Fluorite")
        builder.add(RagiumMaterialKeys.GALENA, "Galena")
        builder.add(RagiumMaterialKeys.GOLD, "Gold")
        builder.add(RagiumMaterialKeys.INVAR, "Invar")
        builder.add(RagiumMaterialKeys.IRIDIUM, "Iridium")
        builder.add(RagiumMaterialKeys.IRON, "Iron")
        builder.add(RagiumMaterialKeys.LAPIS, "Lapis")
        builder.add(RagiumMaterialKeys.LEAD, "Lead")
        builder.add(RagiumMaterialKeys.NETHER_STAR, "Nether Star")
        builder.add(RagiumMaterialKeys.NETHERITE, "Netherite")
        builder.add(RagiumMaterialKeys.NICKEL, "Nickel")
        builder.add(RagiumMaterialKeys.NITER, "Niter")
        builder.add(RagiumMaterialKeys.PERIDOT, "Peridot")
        builder.add(RagiumMaterialKeys.PLATINUM, "Platinum")
        builder.add(RagiumMaterialKeys.PLUTONIUM, "Plutonium")
        builder.add(RagiumMaterialKeys.PYRITE, "Pyrite")
        builder.add(RagiumMaterialKeys.QUARTZ, "Quartz")
        builder.add(RagiumMaterialKeys.RAGI_ALLOY, "Ragi-Alloy")
        builder.add(RagiumMaterialKeys.RAGI_CRYSTAL, "Ragi-Crystal")
        builder.add(RagiumMaterialKeys.RAGI_STEEL, "Ragi-Steel")
        builder.add(RagiumMaterialKeys.RAGINITE, "Raginite")
        builder.add(RagiumMaterialKeys.RAGIUM, "Ragium")
        builder.add(RagiumMaterialKeys.REDSTONE, "Redstone")
        builder.add(RagiumMaterialKeys.REFINED_RAGI_STEEL, "Refined Ragi-Steel")
        builder.add(RagiumMaterialKeys.RUBY, "Ruby")
        builder.add(RagiumMaterialKeys.SALT, "Salt")
        builder.add(RagiumMaterialKeys.SAPPHIRE, "Sapphire")
        builder.add(RagiumMaterialKeys.SILVER, "Silver")
        builder.add(RagiumMaterialKeys.SPHALERITE, "Sphalerite")
        builder.add(RagiumMaterialKeys.STEEL, "Steel")
        builder.add(RagiumMaterialKeys.STONE, "Stone")
        builder.add(RagiumMaterialKeys.SULFUR, "Sulfur")
        builder.add(RagiumMaterialKeys.TIN, "Tin")
        builder.add(RagiumMaterialKeys.TITANIUM, "Titanium")
        builder.add(RagiumMaterialKeys.TUNGSTEN, "Tungsten")
        builder.add(RagiumMaterialKeys.URANIUM, "Uranium")
        builder.add(RagiumMaterialKeys.WOOD, "Wood")
        builder.add(RagiumMaterialKeys.ZINC, "Zinc")
        // Tag Prefix
        builder.add(HTTagPrefix.DUST, "%s Dust")
        builder.add(HTTagPrefix.GEAR, "%s Gear")
        builder.add(HTTagPrefix.GEM, "%s")
        builder.add(HTTagPrefix.INGOT, "%s Ingot")
        builder.add(HTTagPrefix.NUGGET, "%s Nugget")
        builder.add(HTTagPrefix.ORE, "%s Ore")
        builder.add(HTTagPrefix.PLATE, "%s Plate")
        builder.add(HTTagPrefix.RAW_MATERIAL, "Raw %s")
        builder.add(HTTagPrefix.ROD, "%s Rod")
        builder.add(HTTagPrefix.STORAGE_BLOCK, "Block of %s")
        builder.add(HTTagPrefix.WIRE, "%s Wire")
        // World
        builder.addWorld(World.OVERWORLD, "Overworld")
        builder.addWorld(World.NETHER, "Nether")
        builder.addWorld(World.END, "The End")

        generateIntegration(builder)
    }

    private fun generateIntegration(builder: TranslationBuilder) {
        // Config
        builder.add(RagiumTranslationKeys.CONFIG_CATEGORY_COMMON, "Common")
        builder.add(RagiumTranslationKeys.CONFIG_CATEGORY_MACHINE, "Machine")
        builder.add(RagiumTranslationKeys.CONFIG_CATEGORY_UTILITY, "Utility")

        builder.add(RagiumTranslationKeys.CONFIG_HARD_MODE, "Enable Hard Mode")
        builder.add(RagiumTranslationKeys.CONFIG_RADIOACTIVE, "Enable Radioactive Effect")

        builder.add(RagiumTranslationKeys.CONFIG_SHOW_PARTICLE, "Show Particle")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR, "Generator")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_COOLANT, "Coolant for Nuclear Generator")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_NITRO, "Nitro Fuel for Combustion Generator")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_NON_NITRO, "Non-Nitro Fuel for Combustion Generator")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_STEAM, "Water for Steam Generator")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_THERMAL, "Fuel for Thermal Generator")

        builder.add(RagiumTranslationKeys.CONFIG_AUTO_ILLUMINATOR, "Auto Illuminator Radius")
        builder.add(RagiumTranslationKeys.CONFIG_DYNAMITE_RADIUS, "Block Placing Radius for Dynamites")
        builder.add(RagiumTranslationKeys.CONFIG_DYNAMITE_POWER, "Default Dynamite Power")
        builder.add(RagiumTranslationKeys.CONFIG_GIGANT_HAMMER, "Mining Speed for Gigant Hammer")
        // Key Binds
        builder.add("category.ragium.ragium", RagiumAPI.MOD_NAME)

        builder.add("key.ragium.open_backpack", "Open Backpack")
        // Jade
        builder.add(RagiumTranslationKeys.CONFIG_JADE_EXPORTER, "Exporters")
        builder.add(RagiumTranslationKeys.CONFIG_JADE_MACHINE, "Machines")
        builder.add(RagiumTranslationKeys.CONFIG_JADE_NETWORK_INTERFACE, "E.N.I")

        builder.add(RagiumTranslationKeys.PROVIDER_JADE_NETWORK_INTERFACE, "Stored Energy: %s E")
        // Patchouli
        builder.add(RagiumTranslationKeys.PATCHOULI_NAME, "Ragi-Wiki")
        builder.add(RagiumTranslationKeys.PATCHOULI_LANDING, "An official guidebook for Ragium")

        builder.add(HTPatchouliCategory.FOOD, "Food", "GregTech is waiting for you...")
        builder.add(HTPatchouliCategory.MACHINE, "Machine", "")
        builder.add(HTPatchouliCategory.MATERIAL, "Material", "Ragi-Materials")
        builder.add(HTPatchouliCategory.PETROCHEMISTRY, "Petrochemistry", "Poetrotheum...?")
        builder.add(HTPatchouliCategory.UTILITY, "Utility", "Extra...?")

        builder.add(RagiumTranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE, "Find Crude Raginite")
        builder.add(
            RagiumTranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE0,
            "Found in Overworld, between y = [-48, 48]",
        )
        builder.add(RagiumTranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE1, "Drops [1, 3] from Crude Raginite Ore")

        builder.add(RagiumTranslationKeys.PATCHOULI_RAGI_ALLOY, "Craft Ragi-Alloy")
        builder.add(RagiumTranslationKeys.PATCHOULI_RAGI_ALLOY0, "Ragi-Alloy is the main metal for tier 1")
        // REI
        builder.add(RagiumTranslationKeys.REI_ENTRY_NO_MATCHING, "No matching entry for %s")
        builder.add(RagiumTranslationKeys.REI_ENTRY_APPLY_DAMAGE, "Apply %s damage when processed")
    }
}
