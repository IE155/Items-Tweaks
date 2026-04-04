package io.qzz.iie.newitems;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import io.qzz.iie.ItemsTweaks;

public class LeafLitterAxe extends Item {
    public static final RegistryKey<Item> LEAF_LITTER_AXE_KEY = RegistryKey.of(
            RegistryKeys.ITEM, Identifier.of(ItemsTweaks.MOD_ID, "leaf_litter_axe"));

    public LeafLitterAxe() {
        super(new Item.Settings()
                .registryKey(LEAF_LITTER_AXE_KEY)
                .maxCount(1)
                .maxDamage(8)
                .component(DataComponentTypes.TOOL, Items.WOODEN_AXE.getComponents().get(DataComponentTypes.TOOL))
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, Items.WOODEN_AXE.getComponents().get(DataComponentTypes.ATTRIBUTE_MODIFIERS))
        );
    }
}
