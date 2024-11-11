package hiiragi283.ragium.data

import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.integration.RagiumOritechPlugin
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.ItemConvertible
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class RITagProvider(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
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
