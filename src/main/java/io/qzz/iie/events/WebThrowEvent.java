package io.qzz.iie.events;

import io.qzz.iie.entity.FlyingCobwebEntity;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * 玩家手持蜘蛛网右键投掷事件
 */
public class WebThrowEvent {
    
    public static void registerEvents() {
        registerWebThrowEvent();
    }
    
    /**
     * 玩家手持蜘蛛网右键时，蜘蛛网沿直线飞出
     */
    private static void registerWebThrowEvent() {
        UseItemCallback.EVENT.register((PlayerEntity player, World world, Hand hand) -> {
            ItemStack stack = player.getStackInHand(hand);
            
            // 检查是否为蜘蛛网
            if (!stack.isOf(Items.COBWEB)) {
                return ActionResult.PASS;
            }
            
            // 只在服务器端执行
            if (!world.isClient()) {
                // 创建飞行蜘蛛网实体
                FlyingCobwebEntity cobwebEntity = new FlyingCobwebEntity(player, world);
                
                // 设置初始位置
                cobwebEntity.setPosition(
                    player.getX(),
                    player.getEyeY() - 0.1,
                    player.getZ()
                );
                
                // 设置速度（沿玩家视线方向）
                cobwebEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
                
                // 生成实体
                world.spawnEntity(cobwebEntity);
                
                // 非创造模式消耗蜘蛛网
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
            }
            
            // 返回 SUCCESS 阻止默认的放置行为
            return ActionResult.SUCCESS;
        });
    }
}
