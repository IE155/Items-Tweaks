package io.qzz.iie.newitems;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import io.qzz.iie.ItemsTweaks;

public class LightTorch extends Item {
    public static final RegistryKey<Item> LIGHT_TORCH_KEY = RegistryKey.of(
            RegistryKeys.ITEM, Identifier.of(ItemsTweaks.MOD_ID, "light_torch"));

    public LightTorch() {
        super(new Item.Settings()
                .registryKey(LIGHT_TORCH_KEY)
                .maxCount(64)
        );
    }
}
