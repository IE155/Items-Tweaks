package io.qzz.iie.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class TotemStackMixin {

    @Inject(method = "getMaxCount", at = @At("HEAD"), cancellable = true)
    private void injectTotemMaxCount(CallbackInfoReturnable<Integer> cir) {
        ItemStack self = (ItemStack) (Object) this;

        // 检查物品是否是不死图腾
        if (self.isOf(Items.TOTEM_OF_UNDYING)) {
            cir.setReturnValue(64);
        }
    }
}