package io.qzz.iie.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Optional;

/**
 * 为所有剑添加blocks_attacks组件
 * 让剑可以像盾牌一样格挡（Minecraft 1.21.5+机制）
 * 
 * 通过Mixin Item.use方法，在使用剑时动态添加blocks_attacks组件
 */
@Mixin(net.minecraft.item.Item.class)
public class SwordBlockingMixin {
    
    /**
     * 在物品使用时，检查是否为剑并添加blocks_attacks组件
     */
    @Inject(method = "use", at = @At("HEAD"))
    private void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = user.getStackInHand(hand);
        
        // 检查是否已经有blocks_attacks组件
        if (stack.contains(DataComponentTypes.BLOCKS_ATTACKS)) {
            return; // 已经有了，不需要添加
        }
        
        // 检查是否为剑
        if (isSwordItem(stack)) {
            // 创建blocks_attacks组件
            BlocksAttacksComponent blockingComponent = new BlocksAttacksComponent(
                0.05f,  // blockDelaySeconds - 格挡延迟
                1.0f,   // disableCooldownScale - cooldown缩放  
                new ArrayList<>(),  // damageReductions - 空列表表示默认50%减免
                new BlocksAttacksComponent.ItemDamage(1.0f, 1.0f, 1.0f),  // itemDamage
                Optional.empty(),  // bypassedBy - 没有伤害类型可以穿透
                Optional.empty(),  // blockSound - 使用默认格挡音效
                Optional.empty()   // disableSound - 使用默认音效
            );
            
            // 添加组件到物品堆栈
            stack.set(DataComponentTypes.BLOCKS_ATTACKS, blockingComponent);
        }
    }
    
    /**
     * 检查物品堆栈是否为剑
     */
    private boolean isSwordItem(ItemStack stack) {
        String itemName = stack.getItem().toString();
        return itemName.contains("sword") || itemName.contains("_sword");
    }
}
