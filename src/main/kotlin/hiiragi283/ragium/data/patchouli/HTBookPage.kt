package hiiragi283.ragium.data.patchouli

import com.mojang.serialization.Codec
import vazkii.patchouli.client.book.BookPage

interface HTBookPage<T : BookPage, R : HTBookPage<T, R>> {
    companion object {
        @JvmField
        val CODEC: Codec<HTBookPage<*, *>> =
            HTBookPageSerializer.REGISTRY.codec.dispatch(
                HTBookPage<*, *>::serializer,
                HTBookPageSerializer<*, *>::codec,
            )
    }

    val serializer: HTBookPageSerializer<T, R>
}