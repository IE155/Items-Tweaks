package io.qzz.iie;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.qzz.iie.newitems.LeafLitterPickaxe;
import io.qzz.iie.newitems.LeafLitterSword;
import io.qzz.iie.newitems.HardSnowBall;
import io.qzz.iie.newitems.LightTorch;
import io.qzz.iie.events.WaterObsidian;
import io.qzz.iie.events.NoCdEat;
import io.qzz.iie.events.DyeCobblestoneToObsidian;
import io.qzz.iie.events.LeafLitterFire;
import io.qzz.iie.events.BerryBushBootProtection;

public class ItemsTweaks implements ModInitializer {
	public static final String MOD_ID = "items-tweaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final RegistryKey<Item> LIGHT_TORCH_KEY = RegistryKey.of(
			RegistryKeys.ITEM, Identifier.of(MOD_ID, "light_torch"));
	public static final RegistryKey<Item> HARD_SNOW_BALL_KEY = RegistryKey.of(
			RegistryKeys.ITEM, Identifier.of(MOD_ID, "hard_snow_ball"));
	public static final RegistryKey<Block> LIGHT_TORCH_BLOCK_KEY = RegistryKey.of(
			RegistryKeys.BLOCK, Identifier.of(MOD_ID, "light_torch"));

// 实例变量
	public static Item LEAF_LITTER_PICKAXE;
	public static Item LEAF_LITTER_SWORD;
	public static Item LIGHT_TORCH;
	public static Item HARD_SNOW_BALL;
	
	@Override
	public void onInitialize() {
		// 注册物品
		registerItems();
		
		// 注册事件
		WaterObsidian.registerEvents();
		NoCdEat.registerEvents();
		DyeCobblestoneToObsidian.registerEvents();
		LeafLitterFire.registerEvents();
		BerryBushBootProtection.registerEvents();

		
		LOGGER.info("Items Tweaks 加载成功");
	}
	
	/**
	 * 注册所有自定义物品
	 */
	private void registerItems() {
		LEAF_LITTER_PICKAXE = Registry.register(
				Registries.ITEM,
				LeafLitterPickaxe.LEAF_LITTER_PICKAXE_KEY,
				new LeafLitterPickaxe()
		);

		LEAF_LITTER_SWORD = Registry.register(
				Registries.ITEM,
				LeafLitterSword.LEAF_LITTER_SWORD_KEY,
				new LeafLitterSword()
		);

		LIGHT_TORCH = Registry.register(
				Registries.ITEM,
				LightTorch.LIGHT_TORCH_KEY,
				new LightTorch()
		);

		HARD_SNOW_BALL = Registry.register(
				Registries.ITEM,
				HardSnowBall.HARD_SNOW_BALL_KEY,
				new HardSnowBall()
		);
	}
}