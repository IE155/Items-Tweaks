package io.qzz.iie.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

/**
 * 玩家穿靴子时免疫甜浆果丛伤害
 */
public class BerryBushBootProtection {
    
    public static void registerEvents() {
        registerBerryBushProtection();
    }
    
    /**
     * 当玩家受到伤害时，如果伤害来源是甜浆果丛且玩家穿了靴子，则取消伤害
     */
    private static void registerBerryBushProtection() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((LivingEntity entity, DamageSource source, float amount) -> {
            // 只处理玩家
            if (!(entity instanceof PlayerEntity player)) {
                return true;
            }
            
            // 检查伤害来源是否为甜浆果丛
            if (!source.isOf(DamageTypes.SWEET_BERRY_BUSH)) {
                return true;
            }
            
            // 检查玩家脚部装备槽是否有靴子
            ItemStack bootsStack = player.getEquippedStack(EquipmentSlot.FEET);
            if (!bootsStack.isEmpty() && (
                bootsStack.isOf(Items.LEATHER_BOOTS) 
                || bootsStack.isOf(Items.CHAINMAIL_BOOTS) 
                || bootsStack.isOf(Items.IRON_BOOTS) 
                || bootsStack.isOf(Items.GOLDEN_BOOTS) 
                || bootsStack.isOf(Items.DIAMOND_BOOTS) 
                || bootsStack.isOf(Items.NETHERITE_BOOTS)
            )) {
                // 取消伤害
                return false;
            }
            
            return true;
        });
    }
}
