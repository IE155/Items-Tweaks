package io.qzz.iie.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeafLitterFire {

    // 记录上次输出日志的时间,key: 位置, value: 时间戳
    private static final Map<String, Long> lastLogTime = new HashMap<>();
    // 日志输出冷却时间(100tick = 5秒)
    private static final long LOG_COOLDOWN = 100;

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
        
        // 调试日志(带冷却机制)
        if (totalLeafLitterCount > 0) {
            String posKey = pos.toShortString();
            long currentTime = world.getTime();
            Long lastTime = lastLogTime.get(posKey);
            
            // 只在冷却时间过后输出日志
            if (lastTime == null || currentTime - lastTime >= LOG_COOLDOWN) {
                io.qzz.iie.ItemsTweaks.LOGGER.info("[LeafLitterFire] 检测到枯叶: {} 个, 位置: {}", totalLeafLitterCount, pos);
                lastLogTime.put(posKey, currentTime);
                
                // 清理旧数据,避免内存泄漏
                if (lastLogTime.size() > 200) {
                    lastLogTime.clear();
                }
            }
        }
        
        // 如果达到或超过256个枯叶,在枯叶位置生成火焰烧毁它们
        if (totalLeafLitterCount >= 256) {
            String posKey = pos.toShortString();
            long currentTime = world.getTime();
            
            // 直接在枯叶物品所在的位置生成火焰(不受冷却限制)
            // 如果当前位置是空气,直接生成火焰
            var currentState = world.getBlockState(pos);
            if (currentState.isAir()) {
                world.setBlockState(pos, Blocks.FIRE.getDefaultState());
                
                // 日志输出受冷却限制
                Long lastTime = lastLogTime.get(posKey);
                if (lastTime == null || currentTime - lastTime >= LOG_COOLDOWN) {
                    io.qzz.iie.ItemsTweaks.LOGGER.info("[LeafLitterFire] 枯叶数量达到256,生成火焰! 位置: {}", pos);
                    io.qzz.iie.ItemsTweaks.LOGGER.info("[LeafLitterFire] 在位置 {} 生成火焰", pos);
                    lastLogTime.put(posKey, currentTime);
                }
            } else {
                // 如果当前位置有方块,尝试在上方生成
                BlockPos firePos = pos.up();
                var fireState = world.getBlockState(firePos);
                if (fireState.isAir()) {
                    world.setBlockState(firePos, Blocks.FIRE.getDefaultState());
                    
                    // 日志输出受冷却限制
                    Long lastTime = lastLogTime.get(posKey);
                    if (lastTime == null || currentTime - lastTime >= LOG_COOLDOWN) {
                        io.qzz.iie.ItemsTweaks.LOGGER.info("[LeafLitterFire] 枯叶数量达到256,生成火焰! 位置: {}", pos);
                        io.qzz.iie.ItemsTweaks.LOGGER.info("[LeafLitterFire] 在位置 {} 上方生成火焰", pos);
                        lastLogTime.put(posKey, currentTime);
                    }
                }
            }
        }
    }
}
