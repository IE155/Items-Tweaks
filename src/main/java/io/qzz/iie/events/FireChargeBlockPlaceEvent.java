package io.qzz.iie.events;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

/**
 * 将烈焰弹右键放置方块改为发射
 * 烈焰弹只能用于发射,不能放置火焰
 */
public class FireChargeBlockPlaceEvent {
    
    public static void registerEvents() {
        registerFireChargeBlockPlaceEvent();
    }
    
    /**
     * 将烈焰弹右键放置方块改为发射
     */
    private static void registerFireChargeBlockPlaceEvent() {
        UseBlockCallback.EVENT.register((PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) -> {
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
            
            // 返回 SUCCESS 阻止默认放置火焰行为,并阻止 UseItemCallback 再次触发
            return ActionResult.SUCCESS;
        });
    }
}
