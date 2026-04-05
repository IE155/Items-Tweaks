package io.qzz.iie.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 允许三叉戟的激流附魔在无水环境下也能使用
 * 通过Mixin修改Entity的isTouchingWaterOrRain方法
 * 当玩家手持三叉戟时,让游戏认为玩家在水中或雨中
 */
@Mixin(Entity.class)
public class TridentRiptideMixin {
    
    /**
     * 修改isTouchingWaterOrRain方法
     * 当玩家手持三叉戟时,返回true让游戏认为玩家在水中或雨中
     * 这样激流附魔就可以在无水环境下使用了
     */
    @Inject(method = "isTouchingWaterOrRain", at = @At("HEAD"), cancellable = true)
    private void onIsTouchingWaterOrRain(CallbackInfoReturnable<Boolean> cir) {
        // 只处理PlayerEntity
        Entity entity = (Entity) (Object) this;
        if (!(entity instanceof PlayerEntity player)) {
            return;
        }
        
        // 检查主手和副手是否有三叉戟
        ItemStack mainHand = player.getMainHandStack();
        ItemStack offHand = player.getOffHandStack();
        
        boolean hasTrident = mainHand.isOf(Items.TRIDENT) || offHand.isOf(Items.TRIDENT);
        
        // 如果手持三叉戟,强制返回true(认为在水中或雨中)
        if (hasTrident) {
            cir.setReturnValue(true);
        }
    }
}
