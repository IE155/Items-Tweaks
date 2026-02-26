package io.qzz.iie.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class BucketStackMixin {

    @Inject(method = "getMaxCount", at = @At("HEAD"), cancellable = true)
    private void injectBucketMaxCount(CallbackInfoReturnable<Integer> cir) {
        ItemStack self = (ItemStack) (Object) this;

        // 逻辑 A：仅让空桶堆叠到 32
        if (self.isOf(Items.BUCKET)) {
            cir.setReturnValue(32);
            return;
        }

    }
}