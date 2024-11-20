package hiiragi283.ragium.data.patchouli

import net.minecraft.util.Identifier
import vazkii.patchouli.client.book.page.PageImage

class HTImageBookPage(val images: List<Identifier>, val title: String = "", val border: Boolean = false) :
    HTBookPage<PageImage, HTImageBookPage> {
    override val serializer: HTBookPageSerializer<PageImage, HTImageBookPage>
        get() = HTBookPageSerializers.IMAGE
}
