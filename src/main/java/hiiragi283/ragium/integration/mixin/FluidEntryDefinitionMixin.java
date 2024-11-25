package hiiragi283.ragium.integration.mixin;

import dev.architectury.fluid.FluidStack;
import hiiragi283.ragium.api.RagiumAPI;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.plugin.client.entry.FluidEntryDefinition;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidEntryDefinition.class)
public abstract class FluidEntryDefinitionMixin {
    @Inject(method = "cheatsAs(Lme/shedaniel/rei/api/common/entry/EntryStack;Ldev/architectury/fluid/FluidStack;)Lnet/minecraft/item/ItemStack;", at = @At("RETURN"), cancellable = true)
    private void ragium$cheatAs(EntryStack<FluidStack> entry, FluidStack value, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack stack = cir.getReturnValue();
        if (stack.isEmpty()) {
            Fluid fluid = value.getFluid();
            if (fluid != null) {
                cir.setReturnValue(RagiumAPI.getInstance().createFilledCube(fluid, 1));
            }
        }
    }
}
