package io.qzz.iie.mixin;

import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ShulkerBoxMixin {

    @Inject(method = "getMaxCount", at = @At("HEAD"), cancellable = true)
    private void injectMaxCount(CallbackInfoReturnable<Integer> cir) {
        ItemStack self = (ItemStack) (Object) this;

        // 检查物品是否为潜影盒（涵盖所有颜色）
        if (self.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock) {

            // 获取容器组件
            ContainerComponent container = self.get(DataComponentTypes.CONTAINER);

            boolean isEmpty = (container == null || container.stream().allMatch(ItemStack::isEmpty));

            if (isEmpty) {
                cir.setReturnValue(64);
            } else {
                // 如果里面有东西，依然维持 1 个的堆叠上限，防止“套娃”导致数据崩溃
                cir.setReturnValue(1);
            }
        }
    }
}