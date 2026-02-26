package io.qzz.iie.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndPortalFrameBlock.class)
public class EndPortalFrameBlockMixin {

    @Inject(
            method = "onUse",
            at = @At("HEAD"),
            cancellable = true
    )
    private void itemsTweaks$removeEye(
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            Hand hand,
            BlockHitResult hit,
            CallbackInfoReturnable<ActionResult> cir
    ) {

        // 只在服务器执行
        if (world.isClient()) return;

        // 必须潜行
        if (!player.isSneaking()) return;

        // 必须空手
        if (!player.getMainHandStack().isEmpty()) return;

        // 必须有末影之眼
        if (!state.get(EndPortalFrameBlock.EYE)) return;


        BlockState newState = state.with(EndPortalFrameBlock.EYE, false);

        world.setBlockState(pos, newState, 3);

        // 掉落末影之眼
        ItemEntity itemEntity = new ItemEntity(
                world,
                pos.getX() + 0.5,
                pos.getY() + 1.0,
                pos.getZ() + 0.5,
                new ItemStack(Items.ENDER_EYE)
        );

        world.spawnEntity(itemEntity);

        // 阻止原版逻辑
        cir.setReturnValue(ActionResult.SUCCESS);
    }
}