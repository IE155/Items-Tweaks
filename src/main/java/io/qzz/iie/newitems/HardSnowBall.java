package io.qzz.iie.newitems;

import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class HardSnowBall {
    public static final Item HARD_SNOW_BALL_ITEM = registerItem("hardSnowBall",new Item.Settings());

    private static Item registerItem(String name, Item.Settings settings) {
        Identifier id = Identifier.of("hardSnowBall", name);
        RegistryKey<Item>
    }
}
