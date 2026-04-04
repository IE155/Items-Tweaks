package io.qzz.iie.events;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DyeCobblestoneToObsidian {

    public static void registerEvents() {
        registerCobblestoneToObsidianEvent();
    }

    /**
     * 玩家手持黑色染料右键圆石,圆石变为黑曜石
     */
    private static void registerCobblestoneToObsidianEvent() {
        UseBlockCallback.EVENT.register((PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) -> {
            // 检查是否是方块点击
            if (hitResult.getType() != HitResult.Type.BLOCK) {
                return ActionResult.PASS;
            }

            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockPos pos = blockHitResult.getBlockPos();
            net.minecraft.block.BlockState state = world.getBlockState(pos);

            // 服务器端执行
            if (world.isClient()) {
                return ActionResult.PASS;
            }

            // 检查目标方块是否为圆石
            if (state.getBlock() != Blocks.COBBLESTONE) {
                return ActionResult.PASS;
            }

            // 检查玩家手持物品是否为黑色染料
            ItemStack handStack = player.getStackInHand(hand);
            if (!handStack.isOf(Items.BLACK_DYE)) {
                return ActionResult.PASS;
            }

            // 将圆石变为黑曜石
            world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState(), Block.NOTIFY_ALL);

            // 非创造模式才消耗物品
            if (!player.isCreative()) {
                // 消耗黑色染料
                handStack.decrement(1);
            }

            return ActionResult.SUCCESS;
        });
    }
}
