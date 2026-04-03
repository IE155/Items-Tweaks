package io.qzz.iie.events;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaterObsidian {
    
    public static void registerEvents() {
        registerObsidianConversionEvent();
        registerCryingObsidianConversionEvent();
    }
    
    /**
     * 水瓶右键黑曜石转换为哭泣的黑曜石
     */
    private static void registerObsidianConversionEvent() {
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
            
            // 检查目标方块是否为黑曜石
            if (state.getBlock() != Blocks.OBSIDIAN) {
                return ActionResult.PASS;
            }
            
            // 检查玩家手持物品是否为水瓶（空水瓶或装水的瓶子）
            ItemStack handStack = player.getStackInHand(hand);
            if (!handStack.isOf(Items.POTION) && !handStack.isOf(Items.GLASS_BOTTLE)) {
                return ActionResult.PASS;
            }
            
            // 如果是药水，检查是否为水瓶
            if (handStack.isOf(Items.POTION)) {
                PotionContentsComponent potionContents = handStack.get(DataComponentTypes.POTION_CONTENTS);
                // 只允许水瓶（包括空水瓶）
                if (potionContents != null && !potionContents.matches(Potions.WATER) && !potionContents.matches(Potions.AWKWARD)) {
                    return ActionResult.PASS;
                }
            }
            
            // 将黑曜石变为哭泣的黑曜石
            world.setBlockState(pos, Blocks.CRYING_OBSIDIAN.getDefaultState(), Block.NOTIFY_ALL);
            
            // 非创造模式才消耗物品
            if (!player.isCreative()) {
                // 减少水瓶
                handStack.decrement(1);
                
                // 尝试给玩家玻璃瓶
                if (!player.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE))) {
                    // 如果背包满了，掉落玻璃瓶
                    ItemEntity itemEntity = new ItemEntity(
                            world,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            new ItemStack(Items.GLASS_BOTTLE)
                    );
                    world.spawnEntity(itemEntity);
                }
            }
            
            return ActionResult.SUCCESS;
        });
    }
    
    /**
     * 玻璃瓶右键哭泣的黑曜石转换为普通黑曜石
     */
    private static void registerCryingObsidianConversionEvent() {
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
            
            // 检查目标方块是否为哭泣的黑曜石
            if (state.getBlock() != Blocks.CRYING_OBSIDIAN) {
                return ActionResult.PASS;
            }
            
            // 检查玩家手持物品是否为玻璃瓶
            ItemStack handStack = player.getStackInHand(hand);
            if (!handStack.isOf(Items.GLASS_BOTTLE)) {
                return ActionResult.PASS;
            }
            
            // 检查背包是否有空间或准备掉落
            ItemStack waterBottle = new ItemStack(Items.POTION);
            waterBottle.set(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
            
            // 将哭泣的黑曜石变为普通黑曜石
            world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState(), Block.NOTIFY_ALL);
            
            // 非创造模式才消耗物品
            if (!player.isCreative()) {
                // 减少玻璃瓶
                handStack.decrement(1);
                
                // 尝试给玩家水瓶（空水瓶）
                ItemStack waterBottleStack = new ItemStack(Items.POTION);
                waterBottle.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Potions.WATER));
                
                if (!player.getInventory().insertStack(waterBottleStack)) {
                    // 如果背包满了，掉落水瓶
                    ItemEntity itemEntity = new ItemEntity(
                            world,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            waterBottle
                    );
                    world.spawnEntity(itemEntity);
                }
            }
            
            return ActionResult.SUCCESS;
        });
    }
}
