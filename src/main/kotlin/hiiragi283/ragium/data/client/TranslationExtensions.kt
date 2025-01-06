package hiiragi283.ragium.data.client

import hiiragi283.ragium.api.content.HTBlockContent
import hiiragi283.ragium.api.content.HTItemContent
import hiiragi283.ragium.api.extension.splitWith
import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.material.HTMaterialKey
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.integration.patchouli.HTPatchouliCategory
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Util
import net.minecraft.world.World

@JvmName("addBlock")
fun FabricLanguageProvider.TranslationBuilder.add(entry: HTBlockContent, value: String) {
    val block: Block = entry.get()
    add(block, value)
}

@JvmName("addItem")
fun FabricLanguageProvider.TranslationBuilder.add(entry: HTItemContent, value: String) {
    add(entry.get(), value)
}

fun FabricLanguageProvider.TranslationBuilder.add(enchantment: RegistryKey<Enchantment>, value: String) {
    add("enchantment.${enchantment.value.splitWith('.')}", value)
}

fun FabricLanguageProvider.TranslationBuilder.add(tier: HTMachineTier, name: String, prefix: String) {
    add(tier.translationKey, name)
    add(tier.prefixKey, prefix)
}

fun FabricLanguageProvider.TranslationBuilder.add(key: HTMachineKey, value: String, desc: String? = null) {
    add(key.translationKey, value)
    desc?.let { add(key.descriptionKey, it) }
}

fun FabricLanguageProvider.TranslationBuilder.add(key: HTMaterialKey, value: String) {
    add(key.translationKey, value)
}

fun FabricLanguageProvider.TranslationBuilder.add(prefix: HTTagPrefix, value: String) {
    add(prefix.translationKey, value)
}

fun FabricLanguageProvider.TranslationBuilder.add(category: HTPatchouliCategory, name: String, description: String) {
    add(category.translationKey, name)
    add(category.descKey, description)
}

fun FabricLanguageProvider.TranslationBuilder.addWorld(key: RegistryKey<World>, value: String) {
    add(Util.createTranslationKey("world", key.value), value)
}
