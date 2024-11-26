package hiiragi283.ragium.data

import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.api.tags.RagiumFluidTags
import hiiragi283.ragium.common.init.RagiumFluids
import hiiragi283.ragium.integration.RagiumOritechPlugin
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.fluid.Fluid
import net.minecraft.item.ItemConvertible
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.TagKey
import rearth.oritech.init.FluidContent
import techreborn.init.ModFluids
import java.util.concurrent.CompletableFuture

object RITagProviders {
    @JvmStatic
    fun init(pack: FabricDataGenerator.Pack) {
        pack.addProvider(::FluidProvider)
        pack.addProvider(::ItemProvider)
    }

    //    Fluid    //

    class FluidProvider(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
        FabricTagProvider.FluidTagProvider(output, completableFuture) {
        override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup) {
            fun add(tagKey: TagKey<Fluid>, fluid: Fluid) {
                getOrCreateTagBuilder(tagKey).addOptional(Registries.FLUID.getId(fluid))
            }

            fun add(tagKey: RagiumFluids, fluid: Fluid) {
                add(tagKey.tagKey, fluid)
            }

            fun add(tagKey: RagiumFluids, fluid: ModFluids) {
                add(tagKey, fluid.fluid)
            }
            // Oritech
            add(RagiumFluids.CRUDE_OIL, FluidContent.STILL_OIL)
            // Tech Reborn
            add(RagiumFluids.AIR, ModFluids.COMPRESSED_AIR)
            add(RagiumFluids.BIO_FUEL, ModFluids.BIOFUEL)
            add(RagiumFluids.CRUDE_OIL, ModFluids.OIL)
            add(RagiumFluids.GLYCEROL, ModFluids.GLYCERYL)
            add(RagiumFluids.HYDROGEN, ModFluids.HYDROGEN)
            add(RagiumFluids.NITRO_FUEL, ModFluids.NITROCOAL_FUEL)
            add(RagiumFluids.NITRO_FUEL, ModFluids.NITROFUEL)
            add(RagiumFluids.NOBLE_GAS, ModFluids.HELIUM)
            add(RagiumFluids.REFINED_GAS, ModFluids.METHANE)
            add(RagiumFluids.SULFURIC_ACID, ModFluids.SULFURIC_ACID)

            add(RagiumFluidTags.NON_NITRO_FUELS, ModFluids.BIOFUEL.fluid)
            add(RagiumFluidTags.NON_NITRO_FUELS, ModFluids.DIESEL.fluid)
            add(RagiumFluidTags.NITRO_FUELS, ModFluids.NITROCOAL_FUEL.fluid)
            add(RagiumFluidTags.NITRO_FUELS, ModFluids.NITROFUEL.fluid)
        }
    }

    //    Item    //

    class ItemProvider(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
        FabricTagProvider.ItemTagProvider(output, completableFuture) {
        override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup) {
            registerFromPlugin(RagiumOritechPlugin)
        }

        private fun registerFromPlugin(plugin: RagiumPlugin) {
            plugin.bindMaterialToItem { prefix: HTTagPrefix, key: HTMaterialKey, item: ItemConvertible ->
                getOrCreateTagBuilder(prefix.createTag(key)).addOptional(reverseLookup(item.asItem()))
            }
        }
    }
}
