package hiiragi283.ragium.integration.patchouli

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class HTBookCategory(val name: String, val description: String, val icon: String) {
    companion object {
        @JvmField
        val CODEC: Codec<HTBookCategory> = RecordCodecBuilder.create { instance ->
            instance
                .group(
                    Codec.STRING.fieldOf("name").forGetter(HTBookCategory::name),
                    Codec.STRING.fieldOf("description").forGetter(HTBookCategory::description),
                    Codec.STRING.fieldOf("icon").forGetter(HTBookCategory::icon),
                ).apply(instance, ::HTBookCategory)
        }
    }
}
