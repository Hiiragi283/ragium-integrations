package hiiragi283.ragium.data.patchouli

import vazkii.patchouli.client.book.page.PageText

data class HTTextBookPage(val text: String, val title: String? = null) : HTBookPage<PageText, HTTextBookPage> {
    override val serializer: HTBookPageSerializer<PageText, HTTextBookPage>
        get() = HTBookPageSerializers.TEXT
}
