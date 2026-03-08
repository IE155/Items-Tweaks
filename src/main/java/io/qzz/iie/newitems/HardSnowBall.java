package io.qzz.iie.newitems;
/*
这个类还没在主类里注册,所以runClient可能报错

 */
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class HardSnowBall {
    public static final Item HARD_SNOW_BALL_ITEM = registerItem("hardSnowBall",new Item.Settings());

    private static Item registerItem(String name, Item.Settings settings) {
        Identifier id = Identifier.of("items-tweaks", name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        return Registry.register(Registries.ITEM, key, new Item(settings.registryKey(key)));
    }
    public static void registerItems() {
        //留空
    }
}
