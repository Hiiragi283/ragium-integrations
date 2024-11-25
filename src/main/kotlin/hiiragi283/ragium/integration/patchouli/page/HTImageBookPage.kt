package hiiragi283.ragium.integration.patchouli.page

import hiiragi283.ragium.integration.patchouli.HTBookPage
import hiiragi283.ragium.integration.patchouli.HTBookPageSerializer
import hiiragi283.ragium.integration.patchouli.HTBookPageSerializers
import net.minecraft.util.Identifier
import vazkii.patchouli.client.book.page.PageImage

class HTImageBookPage(val images: List<Identifier>, val title: String = "", val border: Boolean = false) :
    HTBookPage<PageImage, HTImageBookPage> {
    override val serializer: HTBookPageSerializer<PageImage, HTImageBookPage>
        get() = HTBookPageSerializers.IMAGE
}
