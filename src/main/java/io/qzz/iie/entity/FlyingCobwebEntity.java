package io.qzz.iie.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * 飞行中的蜘蛛网实体
 * 当碰到方块或实体时会放置蜘蛛网
 */
public class FlyingCobwebEntity extends ThrownItemEntity {
    
    private final World cobwebWorld;
    
    public FlyingCobwebEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
        this.cobwebWorld = world;
    }
    
    public FlyingCobwebEntity(LivingEntity owner, World world) {
        super(EntityType.SNOWBALL, world);
        setOwner(owner);
        this.cobwebWorld = world;
    }
    
    @Override
    protected Item getDefaultItem() {
        return Items.COBWEB;
    }
    
    /**
     * 当击中实体时
     */
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        
        if (cobwebWorld instanceof ServerWorld) {
            placeCobweb(entityHitResult.getPos().getX(), entityHitResult.getPos().getY(), entityHitResult.getPos().getZ());
            discard();
        }
    }
    
    /**
     * 当击中方块时
     */
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        
        if (cobwebWorld instanceof ServerWorld) {
            BlockPos pos = blockHitResult.getBlockPos().offset(blockHitResult.getSide());
            placeCobweb(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            discard();
        }
    }
    
    /**
     * 在指定位置放置蜘蛛网
     */
    private void placeCobweb(double x, double y, double z) {
        BlockPos pos = new BlockPos((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
        
        // 检查目标位置是否可以放置蜘蛛网
        if (cobwebWorld.getBlockState(pos).isAir()) {
            cobwebWorld.setBlockState(pos, net.minecraft.block.Blocks.COBWEB.getDefaultState());
        }
    }
    
    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        
        if (cobwebWorld instanceof ServerWorld) {
            discard();
        }
    }
    
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }
    
    /**
     * 每帧检测是否碰到特殊方块（如草丛等高草类方块）
     * 这些方块不会触发正常的碰撞检测
     */
    @Override
    public void tick() {
        super.tick();
        
        // 只在服务端执行
        if (!(cobwebWorld instanceof ServerWorld)) {
            return;
        }
        
        // 如果实体已经标记为移除，则不再检测
        if (this.isRemoved()) {
            return;
        }
        
        // 获取当前所在位置的方块
        BlockPos currentPos = this.getBlockPos();
        BlockState blockState = cobwebWorld.getBlockState(currentPos);
        
        // 检测是否碰到可放置蜘蛛网的非空气方块（如草丛、高草等）
        if (shouldPlaceCobwebOnBlock(blockState)) {
            placeCobweb(this.getX(), this.getY(), this.getZ());
            discard();
        }
    }
    
    /**
     * 判断是否应该在该方块上放置蜘蛛网
     * 包括草丛、高草、蕨类、花等可穿透方块
     */
    private boolean shouldPlaceCobwebOnBlock(BlockState state) {
        // 如果是空气，不放置
        if (state.isAir()) {
            return false;
        }
        
        // 检查是否是草类、花卉等可替换方块
        return state.isOf(Blocks.SHORT_GRASS) ||
               state.isOf(Blocks.TALL_GRASS) ||
               state.isOf(Blocks.FERN) ||
               state.isOf(Blocks.LARGE_FERN) ||
               state.isOf(Blocks.DEAD_BUSH) ||
               state.isOf(Blocks.DANDELION) ||
               state.isOf(Blocks.POPPY) ||
               state.isOf(Blocks.BLUE_ORCHID) ||
               state.isOf(Blocks.ALLIUM) ||
               state.isOf(Blocks.AZURE_BLUET) ||
               state.isOf(Blocks.RED_TULIP) ||
               state.isOf(Blocks.ORANGE_TULIP) ||
               state.isOf(Blocks.WHITE_TULIP) ||
               state.isOf(Blocks.PINK_TULIP) ||
               state.isOf(Blocks.OXEYE_DAISY) ||
               state.isOf(Blocks.CORNFLOWER) ||
               state.isOf(Blocks.LILY_OF_THE_VALLEY) ||
               state.isOf(Blocks.WITHER_ROSE) ||
               state.isOf(Blocks.SUNFLOWER) ||
               state.isOf(Blocks.LILAC) ||
               state.isOf(Blocks.ROSE_BUSH) ||
               state.isOf(Blocks.PEONY) ||
               state.isOf(Blocks.TORCHFLOWER) ||
               state.isOf(Blocks.PITCHER_PLANT) ||
               state.isOf(Blocks.SMALL_DRIPLEAF) ||
               state.isOf(Blocks.BIG_DRIPLEAF) ||
               state.isOf(Blocks.BIG_DRIPLEAF_STEM) ||
               state.isOf(Blocks.HANGING_ROOTS) ||
               state.isOf(Blocks.ROOTED_DIRT) ||
               state.isOf(Blocks.MOSS_CARPET) ||
               state.isOf(Blocks.PINK_PETALS) ||
               state.isOf(Blocks.CAVE_VINES) ||
               state.isOf(Blocks.CAVE_VINES_PLANT) ||
               state.isOf(Blocks.SPORE_BLOSSOM) ||
               state.isOf(Blocks.GLOW_LICHEN) ||
               state.isOf(Blocks.WEEPING_VINES) ||
               state.isOf(Blocks.WEEPING_VINES_PLANT) ||
               state.isOf(Blocks.TWISTING_VINES) ||
               state.isOf(Blocks.TWISTING_VINES_PLANT) ||
               state.isOf(Blocks.VINE) ||
               state.isOf(Blocks.SEAGRASS) ||
               state.isOf(Blocks.TALL_SEAGRASS) ||
               state.isOf(Blocks.KELP) ||
               state.isOf(Blocks.KELP_PLANT) ||
               state.isOf(Blocks.SEA_PICKLE) ||
               state.isOf(Blocks.BROWN_MUSHROOM) ||
               state.isOf(Blocks.RED_MUSHROOM) ||
               state.isOf(Blocks.CRIMSON_FUNGUS) ||
               state.isOf(Blocks.WARPED_FUNGUS) ||
               state.isOf(Blocks.CRIMSON_ROOTS) ||
               state.isOf(Blocks.WARPED_ROOTS) ||
               state.isOf(Blocks.NETHER_SPROUTS) ||
               state.isOf(Blocks.WEEPING_VINES) ||
               state.isOf(Blocks.TWISTING_VINES) ||
               state.isOf(Blocks.SUGAR_CANE) ||
               state.isOf(Blocks.BAMBOO) ||
               state.isOf(Blocks.BAMBOO_SAPLING) ||
               state.isOf(Blocks.COCOA) ||
               state.isOf(Blocks.SWEET_BERRY_BUSH) ||
               state.isOf(Blocks.FIRE) ||
               state.isOf(Blocks.SOUL_FIRE) ||
               state.isOf(Blocks.SNOW) ||
               state.isOf(Blocks.POWDER_SNOW) ||
               state.isOf(Blocks.TRIPWIRE) ||
               state.isOf(Blocks.COBWEB); // 蜘蛛网本身也可以触发
    }
}
