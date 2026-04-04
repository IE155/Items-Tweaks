package io.qzz.iie.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 使原木方块具有重力效果，像沙子一样立即下落
 */
@Mixin(AbstractBlock.class)
public class LogGravityMixin {
    
    /**
     * 方块放置时立即安排下落
     */
    @Inject(method = "onBlockAdded", at = @At("HEAD"))
    private void onLogBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        Block block = (Block) (Object) this;
        
        if (isLogBlock(block) && world instanceof ServerWorld serverWorld) {
            scheduleFallTick(serverWorld, pos);
        }
    }
    
    /**
     * 邻居方块更新时检查是否需要下落
     */
    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"))
    private void onLogNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random, CallbackInfoReturnable<BlockState> cir) {
        Block block = (Block) (Object) this;
        
        // 只检查下方方块的变化
        if (isLogBlock(block) && direction == Direction.DOWN && world instanceof ServerWorld serverWorld) {
            scheduleFallTick(serverWorld, pos);
        }
    }
    
    /**
     * 处理scheduledTick，执行下落逻辑
     */
    @Inject(method = "scheduledTick", at = @At("HEAD"))
    private void onLogScheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        Block block = (Block) (Object) this;
        
        if (!isLogBlock(block)) {
            return;
        }
        
        // 检查下方方块
        BlockPos belowPos = pos.down();
        BlockState belowState = world.getBlockState(belowPos);
        
        // 如果下方是空气、水或熔岩，则让原木下落
        if (belowState.isAir() || belowState.isOf(Blocks.WATER) || belowState.isOf(Blocks.LAVA)) {
            // 创建下落方块实体
            FallingBlockEntity.spawnFromBlock(world, pos, state);
        }
    }
    
    /**
     * 安排方块下落tick
     */
    private void scheduleFallTick(ServerWorld world, BlockPos pos) {
        // 立即安排tick，无延迟
        world.scheduleBlockTick(pos, (Block) (Object) this, 0);
    }
    
    /**
     * 检查方块是否为原木类型
     */
    private boolean isLogBlock(Block block) {
        return block == Blocks.OAK_LOG ||
               block == Blocks.SPRUCE_LOG ||
               block == Blocks.BIRCH_LOG ||
               block == Blocks.JUNGLE_LOG ||
               block == Blocks.ACACIA_LOG ||
               block == Blocks.DARK_OAK_LOG ||
               block == Blocks.MANGROVE_LOG ||
               block == Blocks.CHERRY_LOG ||
               block == Blocks.PALE_OAK_LOG ||
               block == Blocks.OAK_WOOD ||
               block == Blocks.SPRUCE_WOOD ||
               block == Blocks.BIRCH_WOOD ||
               block == Blocks.JUNGLE_WOOD ||
               block == Blocks.ACACIA_WOOD ||
               block == Blocks.DARK_OAK_WOOD ||
               block == Blocks.MANGROVE_WOOD ||
               block == Blocks.CHERRY_WOOD ||
               block == Blocks.PALE_OAK_WOOD ||
               block == Blocks.STRIPPED_OAK_LOG ||
               block == Blocks.STRIPPED_SPRUCE_LOG ||
               block == Blocks.STRIPPED_BIRCH_LOG ||
               block == Blocks.STRIPPED_JUNGLE_LOG ||
               block == Blocks.STRIPPED_ACACIA_LOG ||
               block == Blocks.STRIPPED_DARK_OAK_LOG ||
               block == Blocks.STRIPPED_MANGROVE_LOG ||
               block == Blocks.STRIPPED_CHERRY_LOG ||
               block == Blocks.STRIPPED_PALE_OAK_LOG ||
               block == Blocks.STRIPPED_OAK_WOOD ||
               block == Blocks.STRIPPED_SPRUCE_WOOD ||
               block == Blocks.STRIPPED_BIRCH_WOOD ||
               block == Blocks.STRIPPED_JUNGLE_WOOD ||
               block == Blocks.STRIPPED_ACACIA_WOOD ||
               block == Blocks.STRIPPED_DARK_OAK_WOOD ||
               block == Blocks.STRIPPED_MANGROVE_WOOD ||
               block == Blocks.STRIPPED_CHERRY_WOOD ||
               block == Blocks.STRIPPED_PALE_OAK_WOOD;
    }
}
