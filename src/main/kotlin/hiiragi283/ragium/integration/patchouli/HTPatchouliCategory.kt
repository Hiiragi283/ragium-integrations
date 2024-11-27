package hiiragi283.ragium.integration.patchouli

import hiiragi283.ragium.api.RagiumAPI
import net.minecraft.util.Identifier
import net.minecraft.util.StringIdentifiable

enum class HTPatchouliCategory : StringIdentifiable {
    TIER_1,
    TIER_2,
    TIER_3,
    TIER_4,
    FOOD,
    MACHINES,
    PETROCHEMISTRY,
    ;

    val id: Identifier = RagiumAPI.id(asString())

    override fun asString(): String = name.lowercase()
}
