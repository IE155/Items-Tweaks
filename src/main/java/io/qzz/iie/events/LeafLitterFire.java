package io.qzz.iie.events;


import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeafLitterFire {

    // 检测频率：每10tick(0.5秒)检查一次
    private static final int CHECK_INTERVAL = 10;
    
    // 触发阈值：128个枯叶
    private static final int FIRE_THRESHOLD = 128;
    
    // 缓存已检测的方块位置，避免重复检测
    private static final Map<ServerWorld, Set<BlockPos>> checkedPositions = new HashMap<>();

    public static void registerEvents() {
        registerLeafLitterItemEntityEvent();
    }
    
    /**
     * 定期检查枯叶物品实体堆积
     * 优化策略：
     * 1. 遍历所有枯叶物品实体，而不是扫描方块位置
     * 2. 按方块位置分组统计
     * 3. 达到阈值后生成火焰
     */
    private static void registerLeafLitterItemEntityEvent() {
        ServerTickEvents.END_WORLD_TICK.register((ServerWorld world) -> {
            if (world.isClient()) {
                return;
            }
            
            long time = world.getTime();
            if (time % CHECK_INTERVAL != 0) {
                return;
            }
            
            // 初始化缓存
            checkedPositions.putIfAbsent(world, new HashSet<>());
            Set<BlockPos> cachedPositions = checkedPositions.get(world);
            
            // 获取所有枯叶物品实体（以第一个玩家为中心，64格范围）
            var players = world.getPlayers();
            if (players.isEmpty()) {
                cachedPositions.clear();
                return;
            }
            
            var firstPlayer = players.getFirst();
            var centerPos = firstPlayer.getEyePos();
            List<ItemEntity> allLeafLitter = world.getEntitiesByClass(
                ItemEntity.class,
                Box.of(centerPos, 64, 64, 64),
                entity -> entity.getStack().isOf(Items.LEAF_LITTER) && !entity.isRemoved()
            );
            
            if (allLeafLitter.isEmpty()) {
                cachedPositions.clear();
                return;
            }
            
            // 按方块位置分组统计
            Map<BlockPos, Integer> blockCounts = new HashMap<>();
            for (ItemEntity item : allLeafLitter) {
                BlockPos blockPos = item.getBlockPos();
                blockCounts.merge(blockPos, item.getStack().getCount(), Integer::sum);
            }
            
            // 检查每个方块位置的枯叶数量
            for (Map.Entry<BlockPos, Integer> entry : blockCounts.entrySet()) {
                BlockPos pos = entry.getKey();
                int count = entry.getValue();
                
                // 达到阈值，生成火焰
                if (count >= FIRE_THRESHOLD) {
                    if (!cachedPositions.contains(pos)) {
                        spawnFireAtPosition(world, pos);
                        cachedPositions.add(pos);
                    }
                } else {
                    // 未达到阈值，移除缓存
                    cachedPositions.remove(pos);
                }
            }
            
            // 清理过期的缓存（每5秒清理一次）
            if (time % 100 == 0) {
                cachedPositions.clear();
            }
        });
    }
    
    /**
     * 在指定位置生成火焰
     */
    private static void spawnFireAtPosition(ServerWorld world, BlockPos pos) {
        // 优先在当前位置生成火焰
        if (world.getBlockState(pos).isAir()) {
            world.setBlockState(pos, Blocks.FIRE.getDefaultState());
            return;
        }
        
        // 如果当前位置有方块,尝试在上方生成
        BlockPos firePos = pos.up();
        if (world.getBlockState(firePos).isAir()) {
            world.setBlockState(firePos, Blocks.FIRE.getDefaultState());
        }
    }
}
