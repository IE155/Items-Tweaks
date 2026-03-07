package io.qzz.iie.newitems;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

// 自定义高亮度方块
public class LightTorchBlock extends Block {
    public LightTorchBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public int getLuminance(BlockState state) {
        // 返回比普通火把更亮的亮度（普通火把亮度为14，这里设为15，最大亮度）
        return 15;
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        // 检查方块是否可以放置在指定位置
        return world.getBlockState(pos.down()).isSolid();
    }
}