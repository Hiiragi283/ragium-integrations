package hiiragi283.ragium.integration.patchouli.page

import hiiragi283.ragium.integration.patchouli.HTBookPage
import hiiragi283.ragium.integration.patchouli.HTBookPageSerializer
import hiiragi283.ragium.integration.patchouli.HTBookPageSerializers
import vazkii.patchouli.client.book.page.PageText

data class HTTextBookPage(val text: String, val title: String? = null) : HTBookPage<PageText, HTTextBookPage> {
    override val serializer: HTBookPageSerializer<PageText, HTTextBookPage>
        get() = HTBookPageSerializers.TEXT
}
