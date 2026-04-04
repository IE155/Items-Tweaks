package io.qzz.iie.entity;

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
}
