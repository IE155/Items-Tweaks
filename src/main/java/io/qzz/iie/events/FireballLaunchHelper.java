package io.qzz.iie.events;

import io.qzz.iie.entity.ThrownFireballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * 烈焰弹发射工具类
 * 提供统一的烈焰弹发射逻辑
 */
public class FireballLaunchHelper {
    
    /**
     * 发射烈焰弹
     * @param player 玩家
     * @param world 世界
     * @param stack 烈焰弹物品栈
     */
    public static void launchFireball(PlayerEntity player, World world, ItemStack stack) {
        // 创建自定义烈焰弹实体(带爆炸效果)
        ThrownFireballEntity fireballEntity = new ThrownFireballEntity(world, player);
        
        // 设置初始位置(玩家眼睛位置)
        fireballEntity.setPosition(
            player.getX(),
            player.getEyeY(),
            player.getZ()
        );
        
        // 获取玩家的视线方向
        Vec3d rotationVec = player.getRotationVec(1.0F);
        
        // 设置速度向量(烈焰弹速度较慢,设置为1.0)
        fireballEntity.setVelocity(
            rotationVec.x * 1.0,
            rotationVec.y * 1.0,
            rotationVec.z * 1.0,
            1.0F,  // 速度
            0.0F   // 发散度(精准射击)
        );
        
        // 生成实体
        world.spawnEntity(fireballEntity);
        
        // 非创造模式消耗烈焰弹
        if (!player.isCreative()) {
            stack.decrement(1);
        }
    }
}
