package io.qzz.iie;





import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;

import net.minecraft.block.BlockState;

import net.minecraft.block.Blocks;

import net.minecraft.block.EndPortalFrameBlock;

import net.minecraft.component.DataComponentTypes;

import net.minecraft.entity.ItemEntity;

import net.minecraft.item.Item;

import net.minecraft.item.ItemStack;

import net.minecraft.item.Items;



import net.minecraft.registry.Registries;

import net.minecraft.registry.Registry;

import net.minecraft.registry.RegistryKey;

import net.minecraft.registry.RegistryKeys;

import net.minecraft.state.property.BooleanProperty;

import net.minecraft.util.ActionResult;

import net.minecraft.util.Identifier;

import net.minecraft.util.hit.BlockHitResult;

import net.minecraft.util.hit.HitResult;

import net.minecraft.util.math.BlockPos;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import net.minecraft.item.Item.Settings.*;



public class ItemsTweaks implements ModInitializer {



	public static final String MOD_ID = "items-tweaks";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final RegistryKey<Item> LEAF_LITTER_PICKAXE_KEY = RegistryKey.of(

			RegistryKeys.ITEM, Identifier.of(MOD_ID, "leaf_litter_pickaxe"));



// 实例变量

	public static Item LEAF_LITTER_PICKAXE;

	@Override

	public void onInitialize() {



		LOGGER.info("Items Tweaks 加载成功");





// 在这里进行真正的初始化

		LEAF_LITTER_PICKAXE = Registry.register(

				Registries.ITEM,

				LEAF_LITTER_PICKAXE_KEY,

				new Item(new Item.Settings()

						.registryKey(LEAF_LITTER_PICKAXE_KEY)

						.maxCount(1)

						.maxDamage(59)

						.component(DataComponentTypes.TOOL, Items.WOODEN_PICKAXE.getComponents().get(DataComponentTypes.TOOL))

						.component(DataComponentTypes.ATTRIBUTE_MODIFIERS, Items.WOODEN_PICKAXE.getComponents().get(DataComponentTypes.ATTRIBUTE_MODIFIERS))

				)

		);



// 注册玩家使用方块事件

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {

// 检查是否是方块点击

			if (hitResult.getType() != HitResult.Type.BLOCK) {

				return ActionResult.PASS;

			}



			BlockHitResult blockHitResult = (BlockHitResult) hitResult;

			BlockPos pos = blockHitResult.getBlockPos();

			BlockState state = world.getBlockState(pos);



// 检查是否是末地传送门框架

			if (state.getBlock() != Blocks.END_PORTAL_FRAME) {

				return ActionResult.PASS;

			}



// 服务器端执行

			if (world.isClient()) {

				return ActionResult.PASS;

			}



// 必须空手 - 如果玩家持有物品，则让正常的游戏逻辑处理（放入末影之眼）

			ItemStack handStack = player.getStackInHand(hand);

			if (!handStack.isEmpty()) {

				return ActionResult.PASS;

			}



// 如果框架里有末影之眼，则取出

			BooleanProperty EYE = EndPortalFrameBlock.EYE;

			if (state.get(EYE)) {

// 移除末影之眼

				world.setBlockState(pos, state.with(EYE, false), 3);



// 掉落末影之眼到玩家位置

				ItemStack eyeStack = new ItemStack(Items.ENDER_EYE);



// 尝试给玩家，如果背包满了则掉落

				if (!player.getInventory().insertStack(eyeStack)) {

					ItemEntity item = new ItemEntity(

							world,

							player.getX(),

							player.getY(),

							player.getZ(),

							eyeStack

					);

					world.spawnEntity(item);

				}



				return ActionResult.SUCCESS;

			}



			return ActionResult.PASS;

		});

	}

}