package hiiragi283.ragium.integration.patchouli

import hiiragi283.ragium.api.RagiumAPI
import net.minecraft.util.Identifier
import net.minecraft.util.StringIdentifiable

enum class HTPatchouliCategory : StringIdentifiable {
    FOOD,
    MACHINE,
    MATERIAL,
    PETROCHEMISTRY,
    UTILITY,
    ;

    val id: Identifier = RagiumAPI.id(asString())
    val translationKey = "ragium.category.${asString()}"
    val descKey = "ragium.category.${asString()}0"

    override fun asString(): String = name.lowercase()
}
