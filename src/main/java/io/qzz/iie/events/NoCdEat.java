package io.qzz.iie.events;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class NoCdEat {
    
    public static void registerEvents() {
        registerSweetBerriesNoCdEat();
    }
    
    /**
     * 玩家右键甜浆果即食用，无冷却，无进食动画
     */
    private static void registerSweetBerriesNoCdEat() {
        UseItemCallback.EVENT.register((PlayerEntity player, World world, Hand hand) -> {
            ItemStack stack = player.getStackInHand(hand);
            
            // 检查是否为甜浆果
            if (!stack.isOf(Items.SWEET_BERRIES)) {
                return ActionResult.PASS;
            }
            
            // 服务器端执行
            if (!world.isClient()) {
                // 获取甜浆果的食物组件
                var foodComponent = stack.get(DataComponentTypes.FOOD);
                if (foodComponent != null) {
                    // 直接食用食物组件
                    player.getHungerManager().eat(foodComponent);
                    
                    // 消耗一个甜浆果
                    stack.decrement(1);
                }
            }
            
            // 返回成功，阻止默认的进食动画
            return ActionResult.SUCCESS;
        });
    }
}
