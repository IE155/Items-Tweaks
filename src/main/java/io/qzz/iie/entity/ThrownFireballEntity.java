package io.qzz.iie.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

/**
 * 自定义烈焰弹实体
 * 在击中方块或实体时产生爆炸效果
 */
public class ThrownFireballEntity extends SmallFireballEntity {
    
    // 爆炸威力 (1-10, 推荐2-4)
    private static final float EXPLOSION_POWER = 2.0F;
    // 是否引发火焰
    private static final boolean CREATE_FIRE = true;
    
    private final World fireballWorld;
    
    public ThrownFireballEntity(EntityType<? extends SmallFireballEntity> entityType, World world) {
        super(entityType, world);
        this.fireballWorld = world;
    }
    
    public ThrownFireballEntity(World world, LivingEntity owner) {
        super(EntityType.SMALL_FIREBALL, world);
        setOwner(owner);
        this.fireballWorld = world;
    }
    
    /**
     * 当击中实体时
     */
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        
        if (fireballWorld instanceof ServerWorld serverWorld) {
            // 在命中位置产生爆炸
            createExplosion(
                entityHitResult.getPos().getX(),
                entityHitResult.getPos().getY(),
                entityHitResult.getPos().getZ()
            );
            // 销毁实体
            discard();
        }
    }
    
    /**
     * 当击中方块时
     */
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        
        if (fireballWorld instanceof ServerWorld serverWorld) {
            // 在命中位置产生爆炸
            createExplosion(
                blockHitResult.getPos().getX(),
                blockHitResult.getPos().getY(),
                blockHitResult.getPos().getZ()
            );
            // 销毁实体
            discard();
        }
    }
    
    /**
     * 创建爆炸效果
     */
    private void createExplosion(double x, double y, double z) {
        fireballWorld.createExplosion(
            this,                    // 爆炸来源实体
            x, y, z,                // 爆炸位置
            EXPLOSION_POWER,        // 爆炸威力
            CREATE_FIRE,            // 是否产生火焰
            World.ExplosionSourceType.MOB  // 爆炸类型
        );
    }
    
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }
}
