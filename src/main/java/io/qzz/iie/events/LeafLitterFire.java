package io.qzz.iie.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.List;

public class LeafLitterFire {

    public static void registerEvents() {
        registerLeafLitterItemEntityEvent();
    }
    /**
     * 定期检查枯叶物品实体堆积
     */
    private static void registerLeafLitterItemEntityEvent() {
        ServerTickEvents.END_WORLD_TICK.register((ServerWorld world) -> {
            if (world.isClient()) {
                return;
            }
            
            // 每10tick检查一次
            long time = world.getTime();
            if (time % 10 != 0) {
                return;
            }
            
            var players = world.getPlayers();
            if (players.isEmpty()) {
                return;
            }
            
            // 检查每个玩家附近的枯叶物品实体
            for (var player : players) {
                BlockPos playerPos = player.getBlockPos();
                
                // 扩大检测范围到16格
                int radius = 16;
                for (int dx = -radius; dx <= radius; dx++) {
                    for (int dz = -radius; dz <= radius; dz++) {
                        // 检查Y轴-4到+4范围
                        for (int dy = -4; dy <= 4; dy++) {
                            BlockPos checkPos = playerPos.add(dx, dy, dz);
                            checkBlockLeafLitter(world, checkPos);
                        }
                    }
                }
            }
        });
    }
    
    /**
     * 检查指定方块位置的枯叶物品实体数量
     */
    private static void checkBlockLeafLitter(ServerWorld world, BlockPos pos) {
        // 创建一个检测区域(稍微扩大一点以捕捉物品实体)
        Box detectionBox = new Box(
            pos.getX() - 0.3, pos.getY() - 0.3, pos.getZ() - 0.3,
            pos.getX() + 1.3, pos.getY() + 1.3, pos.getZ() + 1.3
        );
        
        // 获取该区域内的所有枯叶物品实体
        List<ItemEntity> leafLitterItems = world.getEntitiesByClass(
            ItemEntity.class,
            detectionBox,
            entity -> entity.getStack().isOf(Items.LEAF_LITTER) && !entity.isRemoved()
        );
        
        if (leafLitterItems.isEmpty()) {
            return;
        }
        
        // 统计枯叶物品总数
        int totalLeafLitterCount = 0;
        
        for (ItemEntity item : leafLitterItems) {
            int count = item.getStack().getCount();
            totalLeafLitterCount += count;
        }
        
        // 如果达到或超过256个枯叶,在枯叶位置生成火焰烧毁它们
        if (totalLeafLitterCount >= 256) {
            // 直接在枯叶物品所在的位置生成火焰
            // 如果当前位置是空气,直接生成火焰
            var currentState = world.getBlockState(pos);
            if (currentState.isAir()) {
                world.setBlockState(pos, Blocks.FIRE.getDefaultState());
            } else {
                // 如果当前位置有方块,尝试在上方生成
                BlockPos firePos = pos.up();
                var fireState = world.getBlockState(firePos);
                if (fireState.isAir()) {
                    world.setBlockState(firePos, Blocks.FIRE.getDefaultState());
                }
            }
        }
    }
}
