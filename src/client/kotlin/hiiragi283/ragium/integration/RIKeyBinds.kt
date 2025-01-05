package hiiragi283.ragium.integration

import hiiragi283.ragium.api.RagiumAPI
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import org.lwjgl.glfw.GLFW

@Environment(EnvType.CLIENT)
object RIKeyBinds : ClientModInitializer {
    const val CATEGORY: String = "category.${RagiumAPI.Companion.MOD_ID}.${RagiumAPI.Companion.MOD_ID}"

    @JvmField
    val OPEN_BACKPACK: KeyBinding = KeyBindingHelper.registerKeyBinding(
        KeyBinding(keyId("open_backpack"), GLFW.GLFW_KEY_O, CATEGORY),
    )

    @JvmStatic
    private fun keyId(name: String): String = "key.${RagiumAPI.Companion.MOD_ID}.$name"

    override fun onInitializeClient() {}
}
