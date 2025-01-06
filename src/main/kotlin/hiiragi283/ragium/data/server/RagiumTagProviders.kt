package hiiragi283.ragium.data.server

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import hiiragi283.ragium.api.content.HTBlockContent
import hiiragi283.ragium.api.content.HTFluidContent
import hiiragi283.ragium.api.tags.RagiumBlockTags
import hiiragi283.ragium.api.tags.RagiumFluidTags
import hiiragi283.ragium.api.tags.RagiumItemTags
import hiiragi283.ragium.common.init.RagiumBlocks
import hiiragi283.ragium.common.init.RagiumEntityTypes
import hiiragi283.ragium.common.init.RagiumFluids
import hiiragi283.ragium.common.init.RagiumItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalFluidTags
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.entity.projectile.thrown.ThrownItemEntity
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.Fluids
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.EntityTypeTags
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import rearth.oritech.init.FluidContent
import rearth.oritech.init.ItemContent
import techreborn.init.ModFluids
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

object RagiumTagProviders {
    @JvmStatic
    fun init(pack: FabricDataGenerator.Pack) {
        pack.addProvider(::BlockProvider)
        pack.addProvider(::EntityProvider)
        pack.addProvider(::FluidProvider)
        pack.addProvider(::ItemProvider)
    }

    //    Block    //

    private class BlockProvider(output: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>) :
        FabricTagProvider.BlockTagProvider(output, registryLookup) {
        override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup) {
            val blockCache: Multimap<TagKey<Block>, Block> = HashMultimap.create()

            fun add(tagKey: TagKey<Block>, content: HTBlockContent) {
                blockCache.put(tagKey, content.get())
            }

            // Vanilla
            add(BlockTags.SHOVEL_MINEABLE, RagiumBlocks.MUTATED_SOIL)
            add(BlockTags.PICKAXE_MINEABLE, RagiumBlocks.POROUS_NETHERRACK)

            add(BlockTags.SLABS, RagiumBlocks.Slabs.ASPHALT)
            add(BlockTags.SLABS, RagiumBlocks.Slabs.POLISHED_ASPHALT)
            add(BlockTags.SLABS, RagiumBlocks.Slabs.GYPSUM)
            add(BlockTags.SLABS, RagiumBlocks.Slabs.POLISHED_GYPSUM)
            add(BlockTags.SLABS, RagiumBlocks.Slabs.SLATE)
            add(BlockTags.SLABS, RagiumBlocks.Slabs.POLISHED_SLATE)
            add(BlockTags.STAIRS, RagiumBlocks.Stairs.ASPHALT)
            add(BlockTags.STAIRS, RagiumBlocks.Stairs.POLISHED_ASPHALT)
            add(BlockTags.STAIRS, RagiumBlocks.Stairs.GYPSUM)
            add(BlockTags.STAIRS, RagiumBlocks.Stairs.POLISHED_GYPSUM)
            add(BlockTags.STAIRS, RagiumBlocks.Stairs.SLATE)
            add(BlockTags.STAIRS, RagiumBlocks.Stairs.POLISHED_SLATE)

            // add(BlockTags.AXE_MINEABLE, RagiumBlocks.ROPE)
            // add(BlockTags.CLIMBABLE, RagiumBlocks.ROPE)

            RagiumBlocks.FOODS.forEach { add(BlockTags.HOE_MINEABLE, it) }

            buildList {
                add(RagiumBlocks.BACKPACK_CRATE)
                add(RagiumBlocks.OPEN_CRATE)
                add(RagiumBlocks.VOID_CRATE)

                addAll(RagiumBlocks.Ores.entries)
                addAll(RagiumBlocks.StorageBlocks.entries)
                addAll(RagiumBlocks.Grates.entries)
                addAll(RagiumBlocks.Casings.entries)
                addAll(RagiumBlocks.Hulls.entries)
                addAll(RagiumBlocks.Coils.entries)

                addAll(RagiumBlocks.Exporters.entries)
                addAll(RagiumBlocks.Pipes.entries)
                addAll(RagiumBlocks.CrossPipes.entries)
                addAll(RagiumBlocks.PipeStations.entries)
                addAll(RagiumBlocks.FilteringPipes.entries)

                addAll(RagiumBlocks.Crates.entries)
                addAll(RagiumBlocks.Drums.entries)

                addAll(RagiumBlocks.Creatives.entries)
                addAll(RagiumBlocks.Stones.entries)
                addAll(RagiumBlocks.Slabs.entries)
                addAll(RagiumBlocks.Stairs.entries)
                addAll(RagiumBlocks.Glasses.entries)
                addAll(RagiumBlocks.MECHANICS)
                addAll(RagiumBlocks.MISC)
            }.forEach { add(BlockTags.PICKAXE_MINEABLE, it) }

            RagiumBlocks.Ores.entries.forEach { ore: RagiumBlocks.Ores ->
                add(BlockTags.DRAGON_IMMUNE, ore)
            }
            // Ragium
            buildList {
                addAll(RagiumBlocks.Exporters.entries)
                addAll(RagiumBlocks.Pipes.entries)
                addAll(RagiumBlocks.CrossPipes.entries)
                addAll(RagiumBlocks.PipeStations.entries)
                addAll(RagiumBlocks.FilteringPipes.entries)
            }.forEach { add(RagiumBlockTags.PIPE_CONNECTABLES, it) }
            add(RagiumBlockTags.PIPE_CONNECTABLES, RagiumBlocks.Creatives.CRATE)
            add(RagiumBlockTags.PIPE_CONNECTABLES, RagiumBlocks.Creatives.DRUM)
            add(RagiumBlockTags.PIPE_CONNECTABLES, RagiumBlocks.Creatives.EXPORTER)

            blockCache.asMap().forEach { (tagKey: TagKey<Block>, blocks: Collection<Block>) ->
                blocks.sortedBy(Registries.BLOCK::getId).forEach { block: Block ->
                    getOrCreateTagBuilder(tagKey).add(block)
                }
            }
        }
    }

    //    EntityType    //

    private class EntityProvider(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
        FabricTagProvider.EntityTypeTagProvider(output, completableFuture) {
        override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup) {
            // Fabric
            RagiumEntityTypes.DYNAMITES.forEach { entityType: EntityType<out ThrownItemEntity> ->
                getOrCreateTagBuilder(EntityTypeTags.IMPACT_PROJECTILES).add(entityType)
                getOrCreateTagBuilder(ConventionalEntityTypeTags.CAPTURING_NOT_SUPPORTED).add(entityType)
                getOrCreateTagBuilder(ConventionalEntityTypeTags.TELEPORTING_NOT_SUPPORTED).add(entityType)
            }
        }
    }

    //    Fluid    //

    private class FluidProvider(output: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>) :
        FabricTagProvider.FluidTagProvider(output, registryLookup) {
        override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup) {
            fun add(tagKey: TagKey<Fluid>, fluid: Fluid) {
                Registries.FLUID.getKey(fluid).ifPresent { key: RegistryKey<Fluid> -> getOrCreateTagBuilder(tagKey).add(key) }
            }

            fun add(tagKey: TagKey<Fluid>, fluid: HTFluidContent) {
                add(tagKey, fluid.get())
            }

            fun add(tagKey: TagKey<Fluid>, child: TagKey<Fluid>) {
                getOrCreateTagBuilder(tagKey).addTag(child)
            }

            fun addOptional(tagKey: TagKey<Fluid>, fluid: Fluid) {
                Registries.FLUID.getKey(fluid).ifPresent { key: RegistryKey<Fluid> -> getOrCreateTagBuilder(tagKey).addOptional(key) }
            }

            fun addOptional(ragiumFluid: RagiumFluids, fluid: ModFluids) {
                addOptional(ragiumFluid.tagKey, fluid.fluid)
            }

            fun addOptional(ragiumFluid: RagiumFluids, fluid: Supplier<out Fluid>) {
                addOptional(ragiumFluid.tagKey, fluid.get())
            }

            // Fabric
            add(ConventionalFluidTags.MILK, RagiumFluids.MILK)
            add(ConventionalFluidTags.HONEY, RagiumFluids.HONEY)
            add(ConventionalFluidTags.EXPERIENCE, RagiumFluids.EXPERIENCE)

            add(ConventionalFluidTags.GASEOUS, RagiumFluids.AIR)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.HYDROGEN)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.NITROGEN)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.OXYGEN)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.CHLORINE)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.CARBON_MONOXIDE)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.CARBON_DIOXIDE)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.HYDROGEN_FLUORIDE)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.HYDROGEN_CHLORIDE)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.CHLOROSILANE)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.REFINED_GAS)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.NOBLE_GAS)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.URANIUM_HEXAFLUORIDE)
            add(ConventionalFluidTags.GASEOUS, RagiumFluids.ENRICHED_URANIUM_HEXAFLUORIDE)
            // Ragium
            add(RagiumFluidTags.COOLANTS, Fluids.WATER)

            add(RagiumFluidTags.FUELS, RagiumFluidTags.NITRO_FUELS)
            add(RagiumFluidTags.FUELS, RagiumFluidTags.NON_NITRO_FUELS)

            add(RagiumFluidTags.NON_NITRO_FUELS, RagiumFluids.BIO_FUEL)
            add(RagiumFluidTags.NON_NITRO_FUELS, RagiumFluids.FUEL)
            add(RagiumFluidTags.NITRO_FUELS, RagiumFluids.NITRO_FUEL)

            add(RagiumFluidTags.ORGANIC_OILS, RagiumFluids.TALLOW)
            add(RagiumFluidTags.ORGANIC_OILS, RagiumFluids.SEED_OIL)

            add(RagiumFluidTags.THERMAL_FUELS, Fluids.LAVA)
            // Oritech
            addOptional(RagiumFluids.CRUDE_OIL, FluidContent.STILL_OIL)

            addOptional(RagiumFluids.NITRO_FUEL, FluidContent.STILL_FUEL)
            // Tech Reborn
            addOptional(RagiumFluids.AIR, ModFluids.COMPRESSED_AIR)
            addOptional(RagiumFluids.BIO_FUEL, ModFluids.BIOFUEL)
            addOptional(RagiumFluids.CRUDE_OIL, ModFluids.OIL)
            addOptional(RagiumFluids.GLYCEROL, ModFluids.GLYCERYL)
            addOptional(RagiumFluids.HYDROGEN, ModFluids.HYDROGEN)
            addOptional(RagiumFluids.MERCURY, ModFluids.MERCURY)
            addOptional(RagiumFluids.NITRO_FUEL, ModFluids.NITROCOAL_FUEL)
            addOptional(RagiumFluids.NITRO_FUEL, ModFluids.NITROFUEL)
            addOptional(RagiumFluids.NOBLE_GAS, ModFluids.HELIUM)
            addOptional(RagiumFluids.REFINED_GAS, ModFluids.METHANE)
            addOptional(RagiumFluids.SULFURIC_ACID, ModFluids.SULFURIC_ACID)

            addOptional(RagiumFluidTags.COOLANTS, ModFluids.ELECTROLYZED_WATER.fluid)
            addOptional(RagiumFluidTags.NON_NITRO_FUELS, ModFluids.BIOFUEL.fluid)
            addOptional(RagiumFluidTags.NON_NITRO_FUELS, ModFluids.DIESEL.fluid)
            addOptional(RagiumFluidTags.NITRO_FUELS, ModFluids.NITROCOAL_FUEL.fluid)
            addOptional(RagiumFluidTags.NITRO_FUELS, ModFluids.NITROFUEL.fluid)
        }
    }

    //    Item    //

    private class ItemProvider(output: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>) :
        FabricTagProvider.ItemTagProvider(output, registryLookup) {
        override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup) {
            val itemCache: Multimap<TagKey<Item>, Item> = HashMultimap.create()

            fun add(tagKey: TagKey<Item>?, item: ItemConvertible?) {
                val item1: Item = item?.asItem() ?: return
                if (tagKey == null) return
                itemCache.put(tagKey, item1)
            }

            fun FabricTagBuilder.addOptional(item: ItemConvertible) {
                Registries.ITEM.getKey(item.asItem()).ifPresent(::addOptional)
            }

            RagiumItems.SteelArmors.entries.forEach {
                add(it.armorType.armorTag, it)
            }
            RagiumItems.DeepSteelArmors.entries.forEach {
                add(it.armorType.armorTag, it)
            }
            RagiumItems.StellaSuits.entries.forEach {
                add(it.armorType.armorTag, it)
            }
            RagiumItems.SteelTools.entries.forEach {
                add(it.toolType.toolTag, it)
            }
            RagiumItems.DeepSteelTools.entries.forEach {
                add(it.toolType.toolTag, it)
            }
            // Vanilla
            add(ItemTags.AXES, RagiumItems.GIGANT_HAMMER)
            add(ItemTags.HOES, RagiumItems.GIGANT_HAMMER)
            add(ItemTags.PICKAXES, RagiumItems.GIGANT_HAMMER)
            add(ItemTags.SHOVELS, RagiumItems.GIGANT_HAMMER)
            add(ItemTags.SWORDS, RagiumItems.STELLA_SABER)
            add(ItemTags.SWORDS, RagiumItems.RAGIUM_SABER)

            add(ItemTags.PLANKS, RagiumItems.Plates.WOOD)
            add(ItemTags.COALS, RagiumItems.RESIDUAL_COKE)
            // Ragium
            add(RagiumItemTags.ALKALI, RagiumItems.Dusts.ALKALI)
            add(RagiumItemTags.ALKALI, RagiumItems.Dusts.ASH)

            add(RagiumItemTags.FLUID_EXPORTER_FILTERS, RagiumItems.FLUID_FILTER)

            add(RagiumItemTags.ITEM_EXPORTER_FILTERS, RagiumItems.ITEM_FILTER)

            add(RagiumItemTags.SILICON, RagiumItems.CRUDE_SILICON)
            add(RagiumItemTags.SILICON_PLATES, RagiumItems.SILICON)
            add(RagiumItemTags.REFINED_SILICON_PLATES, RagiumItems.REFINED_SILICON)

            add(RagiumItemTags.ADVANCED_UPGRADES, RagiumBlocks.Casings.ADVANCED)
            add(RagiumItemTags.BASIC_UPGRADES, RagiumBlocks.Casings.BASIC)

            getOrCreateTagBuilder(RagiumItemTags.PROTEIN_FOODS)
                .add(Items.ROTTEN_FLESH)
                .addOptionalTag(ConventionalItemTags.RAW_MEAT_FOODS)
                .addOptionalTag(ConventionalItemTags.COOKED_MEAT_FOODS)
                .addOptionalTag(ConventionalItemTags.RAW_FISH_FOODS)
                .addOptionalTag(ConventionalItemTags.COOKED_FISH_FOODS)

            RagiumItems.Plastics.entries.forEach { plastic: RagiumItems.Plastics ->
                add(plastic.tagKey, plastic)
            }

            // Oritech
            getOrCreateTagBuilder(RagiumItems.Plastics.PRIMITIVE.tagKey)
                .addOptional(ItemContent.PLASTIC_SHEET)

            itemCache.asMap().forEach { (tagKey: TagKey<Item>, items: Collection<Item>) ->
                items.sortedBy(Registries.ITEM::getId).forEach { item: Item ->
                    getOrCreateTagBuilder(tagKey).add(item)
                }
            }
        }
    }
}
