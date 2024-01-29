package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class BoltSpectralEntity extends BoltEntity 
{
	private int duration = 200;
	private final String NBT_DURATION = "Duration";
	
	public BoltSpectralEntity(EntityType<? extends BoltEntity> type, Level level)
    {
        super(type, level);
    }

    public BoltSpectralEntity(EntityType<? extends BoltEntity> type, double x, double y, double z, Level level)
    {
        super(type, x, y, z, level);
    }

    public BoltSpectralEntity(LivingEntity shooter, Level level)
    {
        super(ModEntities.BOLT_SPECTRAL.get(), shooter, level);
    }
    
    public BoltSpectralEntity(SpawnEntity spawnEntity, Level level)
    {
    	this(ModEntities.BOLT_SPECTRAL.get(), level);
    }
    
    @Override
    public void tick() 
    {
    	super.tick();
    	if(level.isClientSide && !inGround)
    		level.addParticle(ParticleTypes.INSTANT_EFFECT, getX(), getY(), getZ(), 0.0d, 0.0d, 0.0d);
    }
    
    @Override
    protected ItemStack getPickupItem() 
    {
    	return new ItemStack(ModItems.SPECTRAL_BOLT.get());
    }
    
    @Override
    protected void doPostHurtEffects(LivingEntity living) 
    {
    	super.doPostHurtEffects(living);
    	MobEffectInstance effect = new MobEffectInstance(MobEffects.GLOWING, duration, 0);
    	living.addEffect(effect);
    }
    
    @Override
    public ResourceLocation getTexture() 
    {
    	return new ResourceLocation(ModSpartanWeaponry.ID, "textures/entity/projectiles/spectral_bolt.png");
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag compound)
    {
    	super.readAdditionalSaveData(compound);
    	if(compound.contains(NBT_DURATION))
    		duration = compound.getInt(NBT_DURATION);
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag compound) 
    {
    	super.addAdditionalSaveData(compound);
    	compound.putInt(NBT_DURATION, duration);
    }
}
