package hiiragi283.ragium.integration.mixin;

import com.google.gson.JsonObject;
import hiiragi283.ragium.api.RagiumAPI;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.patchouli.common.book.Book;
import vazkii.patchouli.common.book.BookRegistry;
import vazkii.patchouli.xplat.IXplatAbstractions;

import java.util.Map;

@Mixin(value = BookRegistry.class, remap = false)
public abstract class BookRegistryMixin {

    @Final
    @Shadow
    public Map<Identifier, Book> books;

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lvazkii/patchouli/common/book/BookFolderLoader;findBooks()V"))
    private void ragium$injectBook(CallbackInfo ci) {
        Identifier id = RagiumAPI.id("ragi_wiki");
        JsonObject root = new JsonObject();
        root.addProperty("name", "Ragi Wiki");
        root.addProperty("landing_text", "An official wiki for Ragium");
        root.addProperty("version", 1);
        root.addProperty("book_texture", "patchouli:textures/gui/book_red.png");
        root.addProperty("creative_tab", "ragium:item");
        root.addProperty("i18n", true);
        root.addProperty("use_resource_pack", true);
        books.put(id, new Book(root, IXplatAbstractions.INSTANCE.getModContainer(RagiumAPI.MOD_ID), id, false));
        RagiumAPI.getLOGGER().info("Added Patchouli Book!");
    }

}
