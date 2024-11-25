package hiiragi283.ragium.integration.patchouli

import com.mojang.serialization.MapCodec
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.fabricmc.fabric.api.event.registry.RegistryAttribute
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import vazkii.patchouli.client.book.BookPage

class HTBookPageSerializer<T : BookPage, R : HTBookPage<T, R>>(val codec: MapCodec<R>) {
    companion object {
        @JvmField
        val REGISTRY_KEY: RegistryKey<Registry<HTBookPageSerializer<*, *>>> =
            RegistryKey.ofRegistry(Identifier.of("patchouli_books", "ragi_wiki"))

        @JvmField
        val REGISTRY: Registry<HTBookPageSerializer<*, *>> = FabricRegistryBuilder
            .createSimple(REGISTRY_KEY)
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister()
    }
}
