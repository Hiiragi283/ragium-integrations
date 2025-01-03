package hiiragi283.ragium.data.client

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.content.HTBlockContent
import hiiragi283.ragium.api.content.HTItemContent
import hiiragi283.ragium.api.extension.splitWith
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.common.init.*
import hiiragi283.ragium.integration.RITranslationKeys
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Util
import net.minecraft.world.World
import java.util.concurrent.CompletableFuture

object RagiumLangProviders {
    @JvmStatic
    fun init(pack: FabricDataGenerator.Pack) {
        pack.addProvider(RagiumLangProviders::EnglishLang)
        pack.addProvider(RagiumLangProviders::JapaneseLang)
    }

    @JvmName("addBlock")
    fun TranslationBuilder.add(entry: HTBlockContent, value: String) {
        val block: Block = entry.get()
        add(block, value)
    }

    @JvmName("addItem")
    fun TranslationBuilder.add(entry: HTItemContent, value: String) {
        add(entry.get(), value)
    }

    fun TranslationBuilder.add(enchantment: RegistryKey<Enchantment>, value: String) {
        add("enchantment.${enchantment.value.splitWith('.')}", value)
    }

    fun TranslationBuilder.add(tier: HTMachineTier, name: String, prefix: String) {
        add(tier.translationKey, name)
        add(tier.prefixKey, prefix)
    }

    fun TranslationBuilder.add(key: HTMachineKey, value: String, desc: String? = null) {
        add(key.translationKey, value)
        desc?.let { add(key.descriptionKey, it) }
    }

    fun TranslationBuilder.add(key: HTMaterialKey, value: String) {
        add(key.translationKey, value)
    }

    fun TranslationBuilder.add(prefix: HTTagPrefix, value: String) {
        add(prefix.translationKey, value)
    }

    fun TranslationBuilder.addWorld(key: RegistryKey<World>, value: String) {
        add(Util.createTranslationKey("world", key.value), value)
    }

    //    English    //

    private class EnglishLang(output: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>) :
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

            builder.add(RagiumBlocks.SPONGE_CAKE, "Sponge Cake")
            builder.add(RagiumBlocks.SWEET_BERRIES_CAKE, "Sweet Berries Cake")

            builder.add(RagiumBlocks.AUTO_ILLUMINATOR, "Auto Illuminator")
            builder.add(RagiumBlocks.BACKPACK_INTERFACE, "Backpack Interface")
            builder.add(RagiumBlocks.ITEM_DISPLAY, "Item Display")
            builder.add(RagiumBlocks.EXTENDED_PROCESSOR, "Extended Processor")
            builder.add(RagiumBlocks.MANUAL_FORGE, "Ragi-Anvil")
            builder.add(RagiumBlocks.MANUAL_GRINDER, "Ragi-Grinder")
            builder.add(RagiumBlocks.MANUAL_MIXER, "Ragi-Basin")
            builder.add(RagiumBlocks.NETWORK_INTERFACE, "E.N.I.")
            builder.add(RagiumBlocks.OPEN_CRATE, "Open Crate")
            builder.add(RagiumBlocks.SHAFT, "Shaft")
            builder.add(RagiumBlocks.TELEPORT_ANCHOR, "Teleport Anchor")
            builder.add(RagiumBlocks.TRASH_BOX, "Trash Box")

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

            builder.add(RagiumItems.BASALT_MESH, "Basalt Mesh")
            builder.add(RagiumItems.BEE_WAX, "Bee Wax")
            builder.add(RagiumItems.BLAZING_CARBON_ELECTRODE, "Blazing Carbon Electrode")
            builder.add(RagiumItems.CARBON_ELECTRODE, "Carbon Electrode")
            builder.add(RagiumItems.CHARGED_CARBON_ELECTRODE, "Charged Carbon Electrode")
            builder.add(RagiumItems.COAL_CHIP, "Coal Chip")
            builder.add(RagiumItems.CRIMSON_CRYSTAL, "Crimson Crystal")
            builder.add(RagiumItems.CRUDE_SILICON, "Crude Silicon")
            builder.add(RagiumItems.DEEPANT, "Deepant")
            builder.add(RagiumItems.ENGINE, "V8 Engine")
            builder.add(RagiumItems.ENGINEERING_PLASTIC_PLATE, "Engineering Plastic Plate")
            builder.add(RagiumItems.GLASS_SHARD, "Glass Shard")
            builder.add(RagiumItems.LASER_EMITTER, "Laser Emitter")
            builder.add(RagiumItems.LED, "L.E.D.")
            builder.add(RagiumItems.LUMINESCENCE_DUST, "Luminescence Dust")
            builder.add(RagiumItems.OBSIDIAN_TEAR, "Obsidian Tear")
            builder.add(RagiumItems.PLASTIC_PLATE, "Plastic Plate")
            builder.add(RagiumItems.POLYMER_RESIN, "Polymer Resin")
            builder.add(RagiumItems.PROCESSOR_SOCKET, "Processor Socket")
            builder.add(RagiumItems.PULP, "Pulp")
            builder.add(RagiumItems.RAGI_ALLOY_COMPOUND, "Ragi-Alloy Compound")
            builder.add(RagiumItems.REFINED_SILICON, "Refined Silicon")
            builder.add(RagiumItems.RESIDUAL_COKE, "Residual Coke")
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
            builder.add(RagiumItemGroup.ITEM_KEY, "Ragium - Items")
            builder.add(RagiumItemGroup.FLUID_KEY, "Ragium - Fluids")
            builder.add(RagiumItemGroup.MACHINE_KEY, "Ragium - Machines")
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

            builder.add(RagiumMachineKeys.ASSEMBLER, "Assembler", "Dr.Doom, Assemble!")
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
            builder.add(RagiumMachineKeys.MIXER, "Mixer", "Genomix...")
            builder.add(RagiumMachineKeys.MULTI_SMELTER, "Multi Smelter", "Smelt multiple items at once")
            // Material
            builder.add(RagiumMaterialKeys.CRUDE_RAGINITE, "Crude Raginite")
            builder.add(RagiumMaterialKeys.RAGI_ALLOY, "Ragi-Alloy")
            builder.add(RagiumMaterialKeys.ALKALI, "Alkali")
            builder.add(RagiumMaterialKeys.ASH, "Ash")
            builder.add(RagiumMaterialKeys.BRASS, "Brass")
            builder.add(RagiumMaterialKeys.BRONZE, "Bronze")
            builder.add(RagiumMaterialKeys.COPPER, "Copper")
            builder.add(RagiumMaterialKeys.IRON, "Iron")
            builder.add(RagiumMaterialKeys.NITER, "Niter")
            builder.add(RagiumMaterialKeys.SALT, "Salt")
            builder.add(RagiumMaterialKeys.STONE, "Stone")
            builder.add(RagiumMaterialKeys.SULFUR, "Sulfur")
            builder.add(RagiumMaterialKeys.WOOD, "Wood")

            builder.add(RagiumMaterialKeys.RAGINITE, "Raginite")
            builder.add(RagiumMaterialKeys.RAGI_STEEL, "Ragi-Steel")
            builder.add(RagiumMaterialKeys.ELECTRUM, "Electrum")
            builder.add(RagiumMaterialKeys.FLUORITE, "Fluorite")
            builder.add(RagiumMaterialKeys.GALENA, "Galena")
            builder.add(RagiumMaterialKeys.GOLD, "Gold")
            builder.add(RagiumMaterialKeys.INVAR, "Invar")
            builder.add(RagiumMaterialKeys.PYRITE, "Pyrite")
            builder.add(RagiumMaterialKeys.REDSTONE, "Redstone")
            builder.add(RagiumMaterialKeys.STEEL, "Steel")

            builder.add(RagiumMaterialKeys.RAGI_CRYSTAL, "Ragi-Crystal")
            builder.add(RagiumMaterialKeys.REFINED_RAGI_STEEL, "Refined Ragi-Steel")
            builder.add(RagiumMaterialKeys.ALUMINUM, "Aluminum")
            builder.add(RagiumMaterialKeys.BAUXITE, "Bauxite")
            builder.add(RagiumMaterialKeys.CRYOLITE, "Cryolite")
            builder.add(RagiumMaterialKeys.DEEP_STEEL, "Deep Steel")

            builder.add(RagiumMaterialKeys.RAGIUM, "Ragium")
            builder.add(RagiumMaterialKeys.NETHERITE, "Netherite")

            builder.add(RagiumMaterialKeys.CINNABAR, "Cinnabar")
            builder.add(RagiumMaterialKeys.COAL, "Coal")
            builder.add(RagiumMaterialKeys.DIAMOND, "Diamond")
            builder.add(RagiumMaterialKeys.EMERALD, "Emerald")
            builder.add(RagiumMaterialKeys.LAPIS, "Lapis")
            builder.add(RagiumMaterialKeys.NETHER_STAR, "Nether Star")
            builder.add(RagiumMaterialKeys.PERIDOT, "Peridot")
            builder.add(RagiumMaterialKeys.QUARTZ, "Quartz")
            builder.add(RagiumMaterialKeys.SAPPHIRE, "Sapphire")
            builder.add(RagiumMaterialKeys.RUBY, "Ruby")

            builder.add(RagiumMaterialKeys.IRIDIUM, "Iridium")
            builder.add(RagiumMaterialKeys.LEAD, "Lead")
            builder.add(RagiumMaterialKeys.NICKEL, "Nickel")
            builder.add(RagiumMaterialKeys.PLATINUM, "Platinum")
            builder.add(RagiumMaterialKeys.PLUTONIUM, "Plutonium")
            builder.add(RagiumMaterialKeys.SILVER, "Silver")
            builder.add(RagiumMaterialKeys.TIN, "Tin")
            builder.add(RagiumMaterialKeys.TITANIUM, "Titanium")
            builder.add(RagiumMaterialKeys.TUNGSTEN, "Tungsten")
            builder.add(RagiumMaterialKeys.URANIUM, "Uranium")
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
            builder.add(RITranslationKeys.CONFIG_JADE_EXPORTER, "Exporters")
            builder.add(RITranslationKeys.CONFIG_JADE_MACHINE, "Machines")
            builder.add(RITranslationKeys.CONFIG_JADE_NETWORK_INTERFACE, "E.N.I")

            builder.add(RITranslationKeys.PROVIDER_JADE_NETWORK_INTERFACE, "Stored Energy: %s E")
            // Patchouli
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER1, "Tier 1")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER10, "The first stage")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER2, "Tier 2")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER20, "The second stage")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER3, "Tier 3")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER30, "The third stage")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER4, "Tier 4")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER40, "The final stage")

            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE, "Find Crude Raginite")
            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE0, "Found in Overworld, between y = [-48, 48]")
            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE1, "Drops [1, 3] from Crude Raginite Ore")

            builder.add(RITranslationKeys.PATCHOULI_RAGI_ALLOY, "Craft Ragi-Alloy")
            builder.add(RITranslationKeys.PATCHOULI_RAGI_ALLOY0, "Ragi-Alloy is the main metal for tier 1")
            // REI
            builder.add(RITranslationKeys.REI_ENTRY_NO_MATCHING, "No matching entry for %s")
            builder.add(RITranslationKeys.REI_ENTRY_APPLY_DAMAGE, "Apply %s damage when processed")

            builder.add(RITranslationKeys.REI_RECIPE_BIOME, "Found in the biome: %s")
            builder.add(RITranslationKeys.REI_RECIPE_INFO, "Recipe Info")
        }
    }

    //    Japanese    //

    private class JapaneseLang(output: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>) :
        FabricLanguageProvider(output, "ja_jp", registryLookup) {
        override fun generateTranslations(registryLookup: RegistryWrapper.WrapperLookup, builder: TranslationBuilder) {
            RagiumFluids.entries.forEach { fluid: RagiumFluids ->
                builder.add(
                    fluid.translationKey,
                    fluid.jaName,
                )
            }

            builder.add("modmenu.descriptionTranslation.ragium", "Fabric向けの新しい工業mod")
            builder.add("modmenu.nameTranslation.ragium", RagiumAPI.MOD_NAME)
            builder.add("text.autoconfig.ragium.title", RagiumAPI.MOD_NAME)
            builder.add(RagiumTranslationKeys.FOR_INTEGRATION, "他modとの連携用のコンテンツ")
            builder.add(RagiumTranslationKeys.PRESS_CTRL, "Ctrlキーを押して説明を表示")
            // Advancements
            builder.add(RagiumTranslationKeys.ADVANCEMENT_BUJIN, "タイクーン将軍")
            builder.add(RagiumTranslationKeys.ADVANCEMENT_STELLA_SUIT, "ｽｺﾞｲ ﾂﾖｸﾃ ｴｹﾞﾂﾅｲｸﾗｲ Love-Loveﾅ ｱｰﾏｰ")
            builder.add(RagiumTranslationKeys.ADVANCEMENT_THIS_CAKE_IS_DIE, "つばさレストラン名物「デスケーキ」")
            // Blocks
            builder.add(RagiumBlocks.Creatives.CRATE, "クリエイティブ用クレート")
            builder.add(RagiumBlocks.Creatives.DRUM, "クリエイティブ用ドラム")
            builder.add(RagiumBlocks.Creatives.EXPORTER, "クリエイティブ用搬出機")
            builder.add(RagiumBlocks.Creatives.SOURCE, "クリエイティブ用エネルギー源")

            builder.add(RagiumBlocks.MUTATED_SOIL, "変異した土壌")
            builder.add(RagiumBlocks.POROUS_NETHERRACK, "多孔質ネザーラック")

            builder.add(RagiumBlocks.Slabs.ASPHALT, "アスファルトのハーフブロック")
            builder.add(RagiumBlocks.Slabs.GYPSUM, "石膏のハーフブロック")
            builder.add(RagiumBlocks.Slabs.POLISHED_ASPHALT, "磨かれたアスファルトのハーフブロック")
            builder.add(RagiumBlocks.Slabs.POLISHED_GYPSUM, "磨かれた石膏のハーフブロック")
            builder.add(RagiumBlocks.Slabs.POLISHED_SLATE, "磨かれたスレートのハーフブロック")
            builder.add(RagiumBlocks.Slabs.SLATE, "スレートのハーフブロック")
            builder.add(RagiumBlocks.Stairs.ASPHALT, "アスファルトの階段")
            builder.add(RagiumBlocks.Stairs.GYPSUM, "石膏の階段")
            builder.add(RagiumBlocks.Stairs.POLISHED_ASPHALT, "磨かれたアスファルトの階段")
            builder.add(RagiumBlocks.Stairs.POLISHED_GYPSUM, "磨かれた石膏の階段")
            builder.add(RagiumBlocks.Stairs.POLISHED_SLATE, "磨かれたスレートの階段")
            builder.add(RagiumBlocks.Stairs.SLATE, "スレートの階段")
            builder.add(RagiumBlocks.Stones.ASPHALT, "アスファルト")
            builder.add(RagiumBlocks.Stones.GYPSUM, "石膏")
            builder.add(RagiumBlocks.Stones.POLISHED_ASPHALT, "磨かれたアスファルト")
            builder.add(RagiumBlocks.Stones.POLISHED_GYPSUM, "磨かれた石膏")
            builder.add(RagiumBlocks.Stones.POLISHED_SLATE, "磨かれたスレート")
            builder.add(RagiumBlocks.Stones.SLATE, "スレート")

            builder.add(RagiumBlocks.WhiteLines.SIMPLE, "白線")
            builder.add(RagiumBlocks.WhiteLines.T_SHAPED, "白線（T字）")
            builder.add(RagiumBlocks.WhiteLines.CROSS, "白線（交差）")
            builder.add(RagiumBlocks.Glasses.STEEL, "鋼鉄ガラス")
            builder.add(RagiumBlocks.Glasses.OBSIDIAN, "黒曜石ガラス")
            builder.add(RagiumBlocks.Glasses.RAGIUM, "ラギウムガラス")

            builder.add(RagiumBlocks.SPONGE_CAKE, "スポンジケーキ")
            builder.add(RagiumBlocks.SWEET_BERRIES_CAKE, "スイートベリーケーキ")

            builder.add(RagiumBlocks.AUTO_ILLUMINATOR, "光源置き太郎")
            builder.add(RagiumBlocks.BACKPACK_INTERFACE, "バックパックインターフェース")
            builder.add(RagiumBlocks.ITEM_DISPLAY, "アイテムティスプレイ")
            builder.add(RagiumBlocks.EXTENDED_PROCESSOR, "拡張処理装置")
            builder.add(RagiumBlocks.MANUAL_FORGE, "らぎ金床")
            builder.add(RagiumBlocks.MANUAL_GRINDER, "らぎ臼")
            builder.add(RagiumBlocks.MANUAL_MIXER, "らぎ釜")
            builder.add(RagiumBlocks.NETWORK_INTERFACE, "E.N.I.")
            builder.add(RagiumBlocks.OPEN_CRATE, "オープンクレート")
            builder.add(RagiumBlocks.SHAFT, "シャフト")
            builder.add(RagiumBlocks.TELEPORT_ANCHOR, "テレポートアンカー")
            builder.add(RagiumBlocks.TRASH_BOX, "ゴミ箱")

            builder.add(RagiumBlocks.Pipes.STONE, "石パイプ")
            builder.add(RagiumBlocks.Pipes.WOODEN, "木製パイプ")
            builder.add(RagiumBlocks.Pipes.IRON, "鉄パイプ")
            builder.add(RagiumBlocks.Pipes.COPPER, "銅パイプ")
            builder.add(RagiumBlocks.Pipes.UNIVERSAL, "万能パイプ")

            builder.add(RagiumBlocks.CrossPipes.STEEL, "鋼鉄パイプ")
            builder.add(RagiumBlocks.CrossPipes.GOLD, "金パイプ")

            builder.add(RagiumBlocks.PipeStations.ITEM, "アイテムパイプステーション")
            builder.add(RagiumBlocks.PipeStations.FLUID, "液体パイプステーション")

            builder.add(RagiumBlocks.FilteringPipes.ITEM, "アイテムフィルタリングパイプ")
            builder.add(RagiumBlocks.FilteringPipes.FLUID, "液体フィルタリングパイプ")

            builder.add(RagiumTranslationKeys.AUTO_ILLUMINATOR, "半径%sブロックの範囲に光源を自動で設置する")
            builder.add(RagiumTranslationKeys.LARGE_PROCESSOR, "マルチブロック内の加工機械を拡張する")
            builder.add(RagiumTranslationKeys.MANUAL_GRINDER, "ホッパーなどでアイテムを搬入できる")
            builder.add(RagiumTranslationKeys.MUTATED_SOIL, "成長チャンバーで使用")
            builder.add(
                RagiumTranslationKeys.NETWORK_INTERFACE,
                "無線ネットワークと他modのエネルギーケーブルをつなげる",
            )
            builder.add(RagiumTranslationKeys.OBSIDIAN_GLASS, "黒曜石と同じ爆破耐性を持つガラス")
            builder.add(RagiumTranslationKeys.OPEN_CRATE, "搬入されたアイテムを下にドロップする")
            builder.add(RagiumTranslationKeys.PIPE_STATION, "優先して隣接したストレージに輸送する")
            builder.add(RagiumTranslationKeys.POROUS_NETHERRACK, "スポンジのように溶岩を吸い取る（使い切り）")
            builder.add(RagiumTranslationKeys.RAGIUM_GLASS, "岩盤と同じ爆破耐性を持つガラス")
            builder.add(RagiumTranslationKeys.SPONGE_CAKE, "着地時のダメージを軽減する")
            builder.add(RagiumTranslationKeys.STEEL_GLASS, "水と同じ爆破耐性を持つガラス")
            builder.add(RagiumTranslationKeys.TRASH_BOX, "搬入された「すべて」のアイテムや液体を消滅させる")

            builder.add(RagiumTranslationKeys.CRATE_CAPACITY, "容量: %s 個")

            builder.add(RagiumTranslationKeys.TRANSPORTER_FLUID_SPEED, "液体速度: %s ユニット/秒")
            builder.add(RagiumTranslationKeys.TRANSPORTER_ITEM_SPEED, "アイテム速度: %s個/秒")

            builder.add(RagiumTranslationKeys.EXPORTER_FLUID_FILTER, "現在の液体フィルタ: %s")
            builder.add(RagiumTranslationKeys.EXPORTER_ITEM_FILTER, "現在のアイテムフィルタ: %s")
            // Contents
            builder.add(RagiumTranslationKeys.BATTERY, "バッテリー")
            builder.add(RagiumTranslationKeys.CASING, "外装")
            builder.add(RagiumTranslationKeys.CIRCUIT, "回路")
            builder.add(RagiumTranslationKeys.CIRCUIT_BOARD, "回路基板")
            builder.add(RagiumTranslationKeys.COIL, "コイル")
            builder.add(RagiumTranslationKeys.CRATE, "クレート")
            builder.add(RagiumTranslationKeys.DRILL_HEAD, "ドリルヘッド")
            builder.add(RagiumTranslationKeys.DRUM, "ドラム")
            builder.add(RagiumTranslationKeys.EXPORTER, "搬出機")
            builder.add(RagiumTranslationKeys.GRATE, "格子")
            builder.add(RagiumTranslationKeys.HULL, "筐体")
            // Entity
            builder.add(RagiumEntityTypes.DYNAMITE, "ダイナマイト")
            builder.add(RagiumEntityTypes.ANVIL_DYNAMITE, "金床ダイナマイト")
            builder.add(RagiumEntityTypes.BLAZING_DYNAMITE, "燃え盛るダイナマイト")
            builder.add(RagiumEntityTypes.BEDROCK_DYNAMITE, "岩盤ダイナマイト")
            builder.add(RagiumEntityTypes.FLATTENING_DYNAMITE, "整地用ダイナマイト")
            builder.add(RagiumEntityTypes.FROSTING_DYNAMITE, "凍えるダイナマイト")
            // Fluid
            builder.add(RagiumTranslationKeys.FLUID_AMOUNT, "液体量: %s")
            builder.add(RagiumTranslationKeys.FLUID_CAPACITY, "容量: %s")
            builder.add(RagiumTranslationKeys.FLUID_TITLE, "液体名: %s")
            builder.add(RagiumTranslationKeys.FORMATTED_FLUID, "%s B, %s ユニット")
            // Item
            builder.add(RagiumItems.SteelArmors.HELMET, "スチールのヘルメット")
            builder.add(RagiumItems.SteelArmors.CHESTPLATE, "スチールのチェストプレート")
            builder.add(RagiumItems.SteelArmors.LEGGINGS, "スチールのレギンス")
            builder.add(RagiumItems.SteelArmors.BOOTS, "スチールのブーツ")

            builder.add(RagiumItems.DeepSteelArmors.HELMET, "深層鋼のヘルメット")
            builder.add(RagiumItems.DeepSteelArmors.CHESTPLATE, "深層鋼のチェストプレート")
            builder.add(RagiumItems.DeepSteelArmors.LEGGINGS, "深層鋼のレギンス")
            builder.add(RagiumItems.DeepSteelArmors.BOOTS, "深層鋼のブーツ")

            builder.add(RagiumItems.StellaSuits.GOGGLE, "S.T.E.L.L.A.ゴーグル")
            builder.add(RagiumItems.StellaSuits.JACKET, "S.T.E.L.L.A.ジャケット")
            builder.add(RagiumItems.StellaSuits.LEGGINGS, "S.T.E.L.L.A.レギンス")
            builder.add(RagiumItems.StellaSuits.BOOTS, "S.T.E.L.L.A.ブーツ")

            builder.add(RagiumItems.SteelTools.AXE, "スチールの斧")
            builder.add(RagiumItems.SteelTools.HOE, "スチールのクワ")
            builder.add(RagiumItems.SteelTools.PICKAXE, "スチールのツルハシ")
            builder.add(RagiumItems.SteelTools.SHOVEL, "スチールのショベル")
            builder.add(RagiumItems.SteelTools.SWORD, "スチールの剣")

            builder.add(RagiumItems.DeepSteelTools.AXE, "深層鋼の斧")
            builder.add(RagiumItems.DeepSteelTools.HOE, "深層鋼のクワ")
            builder.add(RagiumItems.DeepSteelTools.PICKAXE, "深層鋼のツルハシ")
            builder.add(RagiumItems.DeepSteelTools.SHOVEL, "深層鋼のショベル")
            builder.add(RagiumItems.DeepSteelTools.SWORD, "深層鋼の剣")

            builder.add(RagiumItems.Dynamites.SIMPLE, "ダイナマイト")
            builder.add(RagiumItems.Dynamites.ANVIL, "金床ダイナマイト")
            builder.add(RagiumItems.Dynamites.BEDROCK, "岩盤ダイナマイト")
            builder.add(RagiumItems.Dynamites.BLAZING, "燃え盛るダイナマイト")
            builder.add(RagiumItems.Dynamites.FLATTENING, "整地用ダイナマイト")
            builder.add(RagiumItems.Dynamites.FROSTING, "凍えるダイナマイト")

            builder.add(RagiumItems.BACKPACK, "バックパック")
            builder.add(RagiumItems.EMPTY_FLUID_CUBE, "液体キューブ（なし）")
            builder.add(RagiumItems.FILLED_FLUID_CUBE, "液体キューブ（%s）")
            builder.add(RagiumItems.FLUID_FILTER, "液体フィルタ")
            builder.add(RagiumItems.FORGE_HAMMER, "鍛造ハンマー")
            builder.add(RagiumItems.GIGANT_HAMMER, "ギガントハンマー")
            builder.add(RagiumItems.ITEM_FILTER, "アイテムフィルタ")
            builder.add(RagiumItems.RAGI_WRENCH, "らぎレンチ")
            builder.add(RagiumItems.RAGIUM_SABER, "ラギウムセイバー")
            builder.add(RagiumItems.STELLA_SABER, "S.T.E.L.L.A.セイバー")
            builder.add(RagiumItems.TRADER_CATALOG, "行商人カタログ")

            builder.add(RagiumItems.SWEET_BERRIES_CAKE_PIECE, "一切れのスイートベリーケーキ")
            builder.add(RagiumItems.MELON_PIE, "メロンパイ")

            builder.add(RagiumItems.BUTTER, "バター")
            builder.add(RagiumItems.CARAMEL, "キャラメル")
            builder.add(RagiumItems.DOUGH, "生地")
            builder.add(RagiumItems.FLOUR, "小麦粉")

            builder.add(RagiumItems.CHOCOLATE, "チョコレート")
            builder.add(RagiumItems.CHOCOLATE_APPLE, "チョコリンゴ")
            builder.add(RagiumItems.CHOCOLATE_BREAD, "チョコパン")
            builder.add(RagiumItems.CHOCOLATE_COOKIE, "チョコレートクッキー")

            builder.add(RagiumItems.CINNAMON_STICK, "シナモンスティック")
            builder.add(RagiumItems.CINNAMON_POWDER, "シナモンパウダー")
            builder.add(RagiumItems.CINNAMON_ROLL, "シナモンロール")

            builder.add(RagiumItems.MINCED_MEAT, "ひき肉")
            builder.add(RagiumItems.MEAT_INGOT, "生肉インゴット")
            builder.add(RagiumItems.COOKED_MEAT_INGOT, "焼肉インゴット")
            builder.add(RagiumItems.CANNED_COOKED_MEAT, "焼肉缶詰")

            builder.add(RagiumItems.AMBROSIA, "アンブロシア")

            builder.add(RagiumItems.BASALT_MESH, "玄武岩メッシュ")
            builder.add(RagiumItems.BEE_WAX, "蜜蠟")
            builder.add(RagiumItems.BLAZING_CARBON_ELECTRODE, "燃え盛る炭素電極")
            builder.add(RagiumItems.CARBON_ELECTRODE, "炭素電極")
            builder.add(RagiumItems.CHARGED_CARBON_ELECTRODE, "チャージされた炭素電極")
            builder.add(RagiumItems.COAL_CHIP, "石炭チップ")
            builder.add(RagiumItems.CRIMSON_CRYSTAL, "深紅の結晶")
            builder.add(RagiumItems.CRUDE_SILICON, "粗製シリコン")
            builder.add(RagiumItems.DEEPANT, "ディーパント")
            builder.add(RagiumItems.ENGINE, "V8エンジン")
            builder.add(RagiumItems.ENGINEERING_PLASTIC_PLATE, "エンジニアリングプラスチック板")
            builder.add(RagiumItems.GLASS_SHARD, "ガラスの破片")
            builder.add(RagiumItems.LASER_EMITTER, "レーザーエミッタ")
            builder.add(RagiumItems.LED, "L.E.D.")
            builder.add(RagiumItems.LUMINESCENCE_DUST, "ルミネッセンスダスト")
            builder.add(RagiumItems.OBSIDIAN_TEAR, "黒曜石の涙")
            builder.add(RagiumItems.PLASTIC_PLATE, "プラスチック板")
            builder.add(RagiumItems.POLYMER_RESIN, "高分子樹脂")
            builder.add(RagiumItems.PROCESSOR_SOCKET, "プロセッサソケット")
            builder.add(RagiumItems.PULP, "パルプ")
            builder.add(RagiumItems.RAGI_ALLOY_COMPOUND, "ラギ合金混合物")
            builder.add(RagiumItems.REFINED_SILICON, "精製シリコン")
            builder.add(RagiumItems.RESIDUAL_COKE, "残渣油コークス")
            builder.add(RagiumItems.SILICON, "シリコン")
            builder.add(RagiumItems.SLAG, "スラグ")
            builder.add(RagiumItems.SOAP, "石鹸")
            builder.add(RagiumItems.SOLAR_PANEL, "太陽光パネル")
            builder.add(RagiumItems.STELLA_PLATE, "S.T.E.L.L.A.板")
            builder.add(RagiumItems.WARPED_CRYSTAL, "歪んだ結晶")

            builder.add(RagiumItems.Radioactives.URANIUM_FUEL, "ウラン燃料")
            builder.add(RagiumItems.Radioactives.PLUTONIUM_FUEL, "プルトニウム燃料")
            builder.add(RagiumItems.Radioactives.YELLOW_CAKE, "イエローケーキ")
            builder.add(RagiumItems.Radioactives.YELLOW_CAKE_PIECE, "一切れのイエローケーキ")
            builder.add(RagiumItems.Radioactives.NUCLEAR_WASTE, "核廃棄物")

            builder.add(RagiumItems.RAGI_TICKET, "らぎチケット")

            builder.add(RagiumItems.Processors.DIAMOND, "ダイヤモンドプロセッサ")
            builder.add(RagiumItems.Processors.EMERALD, "エメラルドプロセッサ")
            builder.add(RagiumItems.Processors.NETHER_STAR, "ネザースタープロセッサ")
            builder.add(RagiumItems.Processors.RAGIUM, "ラギウムプロセッサ")

            builder.add(RagiumItems.PressMolds.GEAR, "プレス型（歯車）")
            builder.add(RagiumItems.PressMolds.PIPE, "プレス型（パイプ）")
            builder.add(RagiumItems.PressMolds.PLATE, "プレス型（板材）")
            builder.add(RagiumItems.PressMolds.ROD, "プレス型（棒材）")

            builder.add(RagiumTranslationKeys.ANVIL_DYNAMITE, "着弾点に金床を設置する")
            builder.add(RagiumTranslationKeys.BACKPACK, "同じ色同士でインベントリを共有する")
            builder.add(RagiumTranslationKeys.BEDROCK_DYNAMITE, "着弾したチャンク内の岩盤を整地する")
            builder.add(RagiumTranslationKeys.BLAZING_DYNAMITE, "エンティティやブロックに当たると着火する")
            builder.add(RagiumTranslationKeys.FLATTENING_DYNAMITE, "着弾点より上のブロックを「すべて」消滅させる")
            builder.add(RagiumTranslationKeys.FROSTING_DYNAMITE, "着弾点に粉雪を設置する")
            builder.add(RagiumTranslationKeys.RAGI_WRENCH, "右クリックで水平方向の回転，シフト右クリックで正面を変更")
            builder.add(RagiumTranslationKeys.ROPE, "着弾点からロープを下す")
            builder.add(RagiumTranslationKeys.TRADER_CATALOG, "右クリックで行商人との取引を行う")
            builder.add(
                RagiumTranslationKeys.WARPED_CRYSTAL,
                "右クリックでテレポートアンカーの上にテレポート，シフト右クリックで紐づけ",
            )

            builder.add(RagiumTranslationKeys.DYNAMITE_DESTROY, "地形破壊: %s")
            builder.add(RagiumTranslationKeys.DYNAMITE_POWER, "威力: %s")
            builder.add(RagiumTranslationKeys.FILTER, "搬出機に右クリックで適用，または設定画面を開く")
            builder.add(RagiumTranslationKeys.FILTER_FORMAT, "例: \"minecraft:iron_ingot\", [\"minecraft:water\"], \"#c:ores\"")
            builder.add(RagiumTranslationKeys.RADIOACTIVITY, "放射能レベル: %s")
            builder.add(RagiumTranslationKeys.WARPED_CRYSTAL_DESTINATION, "座標: %s")
            // Item Group
            builder.add(RagiumItemGroup.ITEM_KEY, "Ragium - アイテム")
            builder.add(RagiumItemGroup.FLUID_KEY, "Ragium - 液体")
            builder.add(RagiumItemGroup.MACHINE_KEY, "Ragium - 機械")
            // Machine
            builder.add(RagiumTranslationKeys.MACHINE_NAME, "名称: %s")
            builder.add(RagiumTranslationKeys.MACHINE_TIER, "ティア: %s")
            builder.add(RagiumTranslationKeys.MACHINE_NETWORK_ENERGY, "ネットワーク上のエネルギー量: %s ユニット")
            builder.add(RagiumTranslationKeys.MACHINE_RECIPE_COST, "処理コスト: %s E")
            builder.add(RagiumTranslationKeys.MACHINE_SHOW_PREVIEW, "プレビューの表示: %s")

            builder.add(RagiumTranslationKeys.MULTI_SHAPE_ERROR, "次の条件を満たしていません: %s (座標: %s)")
            builder.add(RagiumTranslationKeys.MULTI_SHAPE_SUCCESS, "構造物は有効です！")
            // Machine Tier
            builder.add(HTMachineTier.PRIMITIVE, "簡易", "簡易型%s")
            builder.add(HTMachineTier.BASIC, "基本", "基本型%s")
            builder.add(HTMachineTier.ADVANCED, "発展", "発展型%s")
            // Machine Type
            builder.add(RagiumMachineKeys.BEDROCK_MINER, "岩盤採掘機", "岩盤から鉱物を採掘する")
            builder.add(RagiumMachineKeys.BIOMASS_FERMENTER, "バイオマス発酵槽", "植物からバイオマスを生産する")
            builder.add(RagiumMachineKeys.DRAIN, "排水溝", "正面から液体を，上から経験値を，スロット内の液体キューブから中身を吸い取る")
            builder.add(RagiumMachineKeys.FLUID_DRILL, "液体採掘機", "特定のバイオームから液体を汲み上げる")
            builder.add(RagiumMachineKeys.ROCK_GENERATOR, "岩石生成機", "水と溶岩を少なくとも一つずつ隣接させる")

            builder.add(RagiumMachineKeys.COMBUSTION_GENERATOR, "燃焼発電機", "液体燃料から発電する")
            builder.add(RagiumMachineKeys.NUCLEAR_REACTOR, "原子炉", "放射性燃料から発電する")
            builder.add(RagiumMachineKeys.SOLAR_GENERATOR, "太陽光発電機", "日中に発電する")
            builder.add(RagiumMachineKeys.STEAM_GENERATOR, "蒸気発電機", "水と石炭類から発電する")
            builder.add(RagiumMachineKeys.THERMAL_GENERATOR, "地熱発電機", "高温の液体から発電する")

            builder.add(RagiumMachineKeys.ASSEMBLER, "組立機", "悪魔博士，アッセンブル！")
            builder.add(RagiumMachineKeys.BLAST_FURNACE, "大型高炉", "複数の素材を一つに焼き上げる")
            builder.add(RagiumMachineKeys.CHEMICAL_REACTOR, "化学反応槽", "Are You Ready?")
            builder.add(RagiumMachineKeys.COMPRESSOR, "圧縮機", "saves.zip.zip")
            builder.add(RagiumMachineKeys.CUTTING_MACHINE, "裁断機", "")
            builder.add(RagiumMachineKeys.DISTILLATION_TOWER, "蒸留塔", "原油を処理する")
            builder.add(RagiumMachineKeys.ELECTROLYZER, "電解槽", "エレキ オン")
            builder.add(RagiumMachineKeys.EXTRACTOR, "抽出器", "遠心分離機みたいなやつ")
            builder.add(RagiumMachineKeys.GRINDER, "粉砕機", "クラッシュ・アップ")
            builder.add(RagiumMachineKeys.GROWTH_CHAMBER, "成長チャンバー")
            builder.add(RagiumMachineKeys.INFUSER, "注入機", "遠心分離機じゃないみたいなやつ")
            builder.add(RagiumMachineKeys.LASER_TRANSFORMER, "レーザー変換機")
            builder.add(RagiumMachineKeys.MULTI_SMELTER, "並列精錬機", "複数のアイテムを一度に製錬する")
            builder.add(RagiumMachineKeys.MIXER, "ミキサー", "ゲノミクス...")
            // Material
            builder.add(RagiumMaterialKeys.CRUDE_RAGINITE, "粗製ラギナイト")
            builder.add(RagiumMaterialKeys.RAGI_ALLOY, "ラギ合金")
            builder.add(RagiumMaterialKeys.ALKALI, "アルカリ")
            builder.add(RagiumMaterialKeys.ASH, "灰")
            builder.add(RagiumMaterialKeys.BRASS, "真鍮")
            builder.add(RagiumMaterialKeys.BRONZE, "青銅")
            builder.add(RagiumMaterialKeys.COPPER, "銅")
            builder.add(RagiumMaterialKeys.IRON, "鉄")
            builder.add(RagiumMaterialKeys.NITER, "硝石")
            builder.add(RagiumMaterialKeys.SALT, "塩")
            builder.add(RagiumMaterialKeys.STONE, "石材")
            builder.add(RagiumMaterialKeys.SULFUR, "硫黄")
            builder.add(RagiumMaterialKeys.WOOD, "木材")

            builder.add(RagiumMaterialKeys.RAGINITE, "ラギナイト")
            builder.add(RagiumMaterialKeys.RAGI_STEEL, "ラギスチール")
            builder.add(RagiumMaterialKeys.ELECTRUM, "琥珀金")
            builder.add(RagiumMaterialKeys.FLUORITE, "蛍石")
            builder.add(RagiumMaterialKeys.GALENA, "方鉛鉱")
            builder.add(RagiumMaterialKeys.GOLD, "金")
            builder.add(RagiumMaterialKeys.INVAR, "不変鋼")
            builder.add(RagiumMaterialKeys.PYRITE, "黄鉄鉱")
            builder.add(RagiumMaterialKeys.REDSTONE, "レッドストーン")
            builder.add(RagiumMaterialKeys.STEEL, "スチール")

            builder.add(RagiumMaterialKeys.RAGI_CRYSTAL, "ラギクリスタリル")
            builder.add(RagiumMaterialKeys.REFINED_RAGI_STEEL, "精製ラギスチール")
            builder.add(RagiumMaterialKeys.ALUMINUM, "アルミニウム")
            builder.add(RagiumMaterialKeys.BAUXITE, "ボーキサイト")
            builder.add(RagiumMaterialKeys.CRYOLITE, "氷晶石")
            builder.add(RagiumMaterialKeys.DEEP_STEEL, "深層鋼")

            builder.add(RagiumMaterialKeys.RAGIUM, "ラギウム")
            builder.add(RagiumMaterialKeys.NETHERITE, "ネザライト")

            builder.add(RagiumMaterialKeys.CINNABAR, "辰砂")
            builder.add(RagiumMaterialKeys.COAL, "石炭")
            builder.add(RagiumMaterialKeys.DIAMOND, "ダイアモンド")
            builder.add(RagiumMaterialKeys.EMERALD, "エメラルド")
            builder.add(RagiumMaterialKeys.LAPIS, "ラピス")
            builder.add(RagiumMaterialKeys.NETHER_STAR, "ネザースター")
            builder.add(RagiumMaterialKeys.PERIDOT, "ペリドット")
            builder.add(RagiumMaterialKeys.QUARTZ, "水晶")
            builder.add(RagiumMaterialKeys.SAPPHIRE, "サファイア")
            builder.add(RagiumMaterialKeys.RUBY, "ルビー")

            builder.add(RagiumMaterialKeys.IRIDIUM, "イリジウム")
            builder.add(RagiumMaterialKeys.LEAD, "鉛")
            builder.add(RagiumMaterialKeys.NICKEL, "ニッケル")
            builder.add(RagiumMaterialKeys.PLATINUM, "白金")
            builder.add(RagiumMaterialKeys.PLUTONIUM, "プルトニウム")
            builder.add(RagiumMaterialKeys.SILVER, "銀")
            builder.add(RagiumMaterialKeys.TIN, "スズ")
            builder.add(RagiumMaterialKeys.TITANIUM, "チタン")
            builder.add(RagiumMaterialKeys.TUNGSTEN, "タングステン")
            builder.add(RagiumMaterialKeys.URANIUM, "ウラニウム")
            builder.add(RagiumMaterialKeys.ZINC, "亜鉛")
            // Tag Prefix
            builder.add(HTTagPrefix.DUST, "%sの粉")
            builder.add(HTTagPrefix.GEAR, "%sの歯車")
            builder.add(HTTagPrefix.GEM, "%s")
            builder.add(HTTagPrefix.INGOT, "%sインゴット")
            builder.add(HTTagPrefix.NUGGET, "%sのナゲット")
            builder.add(HTTagPrefix.ORE, "%s鉱石")
            builder.add(HTTagPrefix.PLATE, "%s板")
            builder.add(HTTagPrefix.RAW_MATERIAL, "%sの原石")
            builder.add(HTTagPrefix.ROD, "%s棒")
            builder.add(HTTagPrefix.STORAGE_BLOCK, "%sブロック")
            // World
            builder.addWorld(World.OVERWORLD, "オーバーワールド")
            builder.addWorld(World.NETHER, "ネザー")
            builder.addWorld(World.END, "ジ・エンド")

            generateIntegration(builder)
        }

        private fun generateIntegration(builder: TranslationBuilder) {
            // Config
            builder.add(RagiumTranslationKeys.CONFIG_CATEGORY_COMMON, "一般")
            builder.add(RagiumTranslationKeys.CONFIG_CATEGORY_MACHINE, "機械")
            builder.add(RagiumTranslationKeys.CONFIG_CATEGORY_UTILITY, "ユーティリティ")

            builder.add(RagiumTranslationKeys.CONFIG_HARD_MODE, "ハードモード")
            builder.add(RagiumTranslationKeys.CONFIG_RADIOACTIVE, "放射線によるデバフ")

            builder.add(RagiumTranslationKeys.CONFIG_SHOW_PARTICLE, "パーティクルの表示")
            builder.add(RagiumTranslationKeys.CONFIG_GENERATOR, "発電機")
            builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_COOLANT, "原子炉の冷却液")
            builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_NITRO, "燃焼発電機のニトロ燃料")
            builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_NON_NITRO, "燃焼発電機の非ニトロ燃料")
            builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_STEAM, "蒸気発電機の水")
            builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_THERMAL, "地熱発電機の液体")

            builder.add(RagiumTranslationKeys.CONFIG_AUTO_ILLUMINATOR, "光源置き太郎の稼働半径")
            builder.add(RagiumTranslationKeys.CONFIG_DYNAMITE_RADIUS, "ダイナマイトによるブロック設置の半径")
            builder.add(RagiumTranslationKeys.CONFIG_DYNAMITE_POWER, "ダイナマイトの威力のデフォルト値")
            builder.add(RagiumTranslationKeys.CONFIG_GIGANT_HAMMER, "ギガントハンマーの採掘速度")
            // Key Binds
            builder.add("category.ragium.ragium", RagiumAPI.MOD_NAME)

            builder.add("key.ragium.open_backpack", "バックパックを開く")
            // Jade
            builder.add(RITranslationKeys.CONFIG_JADE_EXPORTER, "搬出機")
            builder.add(RITranslationKeys.CONFIG_JADE_MACHINE, "機械")
            builder.add(RITranslationKeys.CONFIG_JADE_NETWORK_INTERFACE, "E.N.I")

            builder.add(RITranslationKeys.PROVIDER_JADE_NETWORK_INTERFACE, "エネルギー量: %s E")
            // Patchouli
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER1, "Tier 1")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER10, "最初のステージ")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER2, "Tier 2")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER20, "次なるステージ")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER3, "Tier 3")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER30, "さらなるステージ")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER4, "Tier 4")
            builder.add(RITranslationKeys.PATCHOULI_CATEGORY_TIER40, "最後のステージ")

            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE, "粗製ラギナイトを見つけよう")
            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE0, "オーバーワールドのy = [-48, 48]の範囲に生成される")
            builder.add(RITranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE1, "ラギナイト鉱石から[1, 3]個ドロップする")

            builder.add(RITranslationKeys.PATCHOULI_RAGI_ALLOY, "ラギ合金を作ろう")
            builder.add(RITranslationKeys.PATCHOULI_RAGI_ALLOY0, "ラギ合金はTier 1のメイン金属となる")
            // REI
            builder.add(RITranslationKeys.REI_ENTRY_NO_MATCHING, "次を満たす値がありません: %s")
            builder.add(RITranslationKeys.REI_ENTRY_APPLY_DAMAGE, "実行時に%sだけ耐久値を減らす")

            builder.add(RITranslationKeys.REI_RECIPE_BIOME, "次のバイオームで見つかる: %s")
            builder.add(RITranslationKeys.REI_RECIPE_INFO, "レシピ情報")
        }
    }
}
