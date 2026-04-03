package io.qzz.iie.newitems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import io.qzz.iie.ItemsTweaks;

public class LeafLitterSword extends Item {
    public static final RegistryKey<Item> LEAF_LITTER_SWORD_KEY = RegistryKey.of(
            RegistryKeys.ITEM, Identifier.of(ItemsTweaks.MOD_ID, "leaf_litter_sword"));

    public LeafLitterSword() {
        super(new Item.Settings()
                .registryKey(LEAF_LITTER_SWORD_KEY)
                .maxCount(1)
                .maxDamage(15)
                .component(DataComponentTypes.TOOL, Items.WOODEN_SWORD.getComponents().get(DataComponentTypes.TOOL))
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, Items.WOODEN_SWORD.getComponents().get(DataComponentTypes.ATTRIBUTE_MODIFIERS))
        );
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        stack.damage(2, miner, EquipmentSlot.MAINHAND);
        return true;
    }
}
