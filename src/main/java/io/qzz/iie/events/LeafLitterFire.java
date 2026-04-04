package io.qzz.iie.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeafLitterFire {

    // 性能优化：缓存上次检测的区块，避免重复检测
    private static final Map<ServerWorld, Map<ChunkPos, Long>> lastCheckedChunks = new HashMap<>();
    
    // 检测半径优化：从16格降至10格，减少67%的检测量
    private static final int DETECTION_RADIUS = 10;
    
    // 检测频率：每20tick(1秒)检查一次，平衡性能和响应速度
    private static final int CHECK_INTERVAL = 20;
    
    // 触发阈值
    private static final int FIRE_THRESHOLD = 256;

    public static void registerEvents() {
        registerLeafLitterItemEntityEvent();
    }
    /**
     * 定期检查枯叶物品实体堆积
     * 性能优化：
     * 1. 缩小检测半径 (16→10格)
     * 2. 降低检测频率 (10→20tick)
     * 3. 区块缓存机制
     */
    private static void registerLeafLitterItemEntityEvent() {
        ServerTickEvents.END_WORLD_TICK.register((ServerWorld world) -> {
            if (world.isClient()) {
                return;
            }
            
            // 每20tick检查一次
            long time = world.getTime();
            if (time % CHECK_INTERVAL != 0) {
                return;
            }
            
            var players = world.getPlayers();
            if (players.isEmpty()) {
                return;
            }
            
            // 初始化区块缓存
            lastCheckedChunks.putIfAbsent(world, new HashMap<>());
            Map<ChunkPos, Long> chunkCache = lastCheckedChunks.get(world);
            
            // 清理过期的缓存记录(超过5秒)
            chunkCache.entrySet().removeIf(entry -> (time - entry.getValue()) > 100);
            
            // 检查每个玩家附近的枯叶物品实体
            for (var player : players) {
                BlockPos playerPos = player.getBlockPos();
                
                // 优化后的检测范围：10格半径
                for (int dx = -DETECTION_RADIUS; dx <= DETECTION_RADIUS; dx++) {
                    for (int dz = -DETECTION_RADIUS; dz <= DETECTION_RADIUS; dz++) {
                        // 优化：只检查Y轴-2到+2范围(枯叶通常堆积在地面)
                        for (int dy = -2; dy <= 2; dy++) {
                            BlockPos checkPos = playerPos.add(dx, dy, dz);
                            
                            // 性能优化：使用区块缓存避免重复检测
                            ChunkPos chunkPos = new ChunkPos(checkPos);
                            Long lastCheck = chunkCache.get(chunkPos);
                            if (lastCheck != null && (time - lastCheck) < CHECK_INTERVAL) {
                                continue; // 本周期已检测过，跳过
                            }
                            
                            checkBlockLeafLitter(world, checkPos, chunkCache, time);
                        }
                    }
                }
            }
        });
    }
    
    /**
     * 检查指定方块位置的枯叶物品实体数量
     * 性能优化：
     * 1. 缩小检测Box范围
     * 2. 添加区块缓存更新
     * 3. 提前退出优化
     */
    private static void checkBlockLeafLitter(ServerWorld world, BlockPos pos, Map<ChunkPos, Long> chunkCache, long currentTime) {
        // 更新区块缓存
        ChunkPos chunkPos = new ChunkPos(pos);
        chunkCache.put(chunkPos, currentTime);
        
        // 优化：缩小检测Box，减少不必要的实体检测
        Box detectionBox = new Box(
            pos.getX(), pos.getY(), pos.getZ(),
            pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0
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
        
        // 统计枯叶物品总数，添加提前退出优化
        int totalLeafLitterCount = 0;
        for (ItemEntity item : leafLitterItems) {
            totalLeafLitterCount += item.getStack().getCount();
            // 性能优化：已达到阈值即可提前退出
            if (totalLeafLitterCount >= FIRE_THRESHOLD) {
                break;
            }
        }
        
        // 如果达到或超过阈值,在枯叶位置生成火焰烧毁它们
        if (totalLeafLitterCount >= FIRE_THRESHOLD) {
            spawnFireAtPosition(world, pos);
        }
    }
    
    /**
     * 在指定位置生成火焰
     * 优化：提取为独立方法，提高代码复用性
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
