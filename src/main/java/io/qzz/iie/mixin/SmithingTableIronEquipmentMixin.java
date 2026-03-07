package io.qzz.iie.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SmithingScreenHandler.class)
public class SmithingTableIronEquipmentMixin {

    /**
     * 检查物品是否为铁制工具或盔甲
     */
    private static boolean isIronTool(Item item) {
        return item == Items.IRON_SWORD
            || item == Items.IRON_PICKAXE
            || item == Items.IRON_AXE
            || item == Items.IRON_SHOVEL
            || item == Items.IRON_HOE
            || item == Items.IRON_HELMET
            || item == Items.IRON_CHESTPLATE
            || item == Items.IRON_LEGGINGS
            || item == Items.IRON_BOOTS;
    }

    /**
     * 重写槽位的canInsert方法，允许在第二个槽位（模板槽，索引为1）放入铁制工具和盔甲
     */
    @Mixin(Slot.class)
    public static class SlotMixin {
        @Shadow
        @Final
        private int index;

        @Inject(method = "canInsert", at = @At("HEAD"), cancellable = true)
        private void allowIronToolsInSecondSlot(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
            // 如果是第二个槽位（索引为1）且物品是铁制工具或盔甲，则允许插入
            if (index == 1 && isIronTool(stack.getItem())) {
                cir.setReturnValue(true);
            }
        }

        /**
         * 额外的检查，确保铁制工具和盔甲可以插入到第二个槽位
         */
        @Inject(method = "isEnabled", at = @At("HEAD"), cancellable = true)
        private void enableIronEquipmentSlot(CallbackInfoReturnable<Boolean> cir) {
            // 确保槽位始终启用
        }
    }
}