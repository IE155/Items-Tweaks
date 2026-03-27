package io.qzz.iie.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class tmp1 {

    @Inject(method = "onBlockCollision", at = @At("HEAD"), cancellable = true)
    private void onBlockCollision(BlockState state, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        
        // 获取玩家脚部的装备（靴子）
        ItemStack bootsStack = player.getEquippedStack(EquipmentSlot.FEET);
        
        // 如果穿了靴子，则取消甜浆果丛的碰撞伤害
        if (!bootsStack.isEmpty()) {
            ci.cancel();
        }
    }
}
