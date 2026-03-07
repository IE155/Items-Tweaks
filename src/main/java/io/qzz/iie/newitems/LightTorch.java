package io.qzz.iie.newitems;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

// 高亮度火把物品
public class LightTorch extends BlockItem {
    public LightTorch(Block block) {
        super(block, new Item.Settings().maxCount(64));
    }
}
