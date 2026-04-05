package io.qzz.iie.events;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * 玩家手持烈焰弹右键发射事件(处理右键空中的情况)
 */
public class FireballThrowEvent {
    
    public static void registerEvents() {
        registerFireballThrowEvent();
    }
    
    /**
     * 玩家手持烈焰弹右键空中时，烈焰弹沿视线方向发射
     */
    private static void registerFireballThrowEvent() {
        UseItemCallback.EVENT.register((PlayerEntity player, World world, Hand hand) -> {
            ItemStack stack = player.getStackInHand(hand);
            
            // 检查是否为烈焰弹
            if (!stack.isOf(Items.FIRE_CHARGE)) {
                return ActionResult.PASS;
            }
            
            // 只在服务器端执行
            if (!world.isClient()) {
                // 使用工具类发射烈焰弹
                FireballLaunchHelper.launchFireball(player, world, stack);
            }
            
            // 返回 SUCCESS 阻止默认的使用行为
            return ActionResult.SUCCESS;
        });
    }
}
