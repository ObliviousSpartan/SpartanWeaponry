package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;

public class BoltSpectralEntity extends BoltEntity 
{
	private int duration = 200;
	private final String NBT_DURATION = "Duration";
	
	public BoltSpectralEntity(EntityType<? extends BoltEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public BoltSpectralEntity(EntityType<? extends BoltEntity> type, double x, double y, double z, World worldIn)
    {
        super(type, x, y, z, worldIn);
    }

    public BoltSpectralEntity(LivingEntity shooter, World world)
    {
        super(ModEntities.BOLT_SPECTRAL, shooter, world);
        //this.setDamage(baseDamage);
    }
    
    public BoltSpectralEntity(SpawnEntity spawnEntity, World world)
    {
    	this(ModEntities.BOLT_SPECTRAL, world);
    }
    
    @Override
    public void tick() 
    {
    	super.tick();
    	if(world.isRemote && !this.inGround)
    		world.addParticle(ParticleTypes.INSTANT_EFFECT, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0d, 0.0d, 0.0d);
    }
    
    @Override
    protected ItemStack getArrowStack() 
    {
    	return new ItemStack(ModItems.spectralBolt);
    }
    
    @Override
    protected void arrowHit(LivingEntity living) 
    {
    	super.arrowHit(living);
    	EffectInstance effect = new EffectInstance(Effects.GLOWING, this.duration, 0);
    	living.addPotionEffect(effect);
    }
    
    @Override
    public ResourceLocation getTexture() 
    {
    	return new ResourceLocation(ModSpartanWeaponry.ID, "textures/entity/projectiles/bolt_spectral.png");
    }
    
    @Override
    public void readAdditional(CompoundNBT compound)
    {
    	super.readAdditional(compound);
    	if(compound.contains(NBT_DURATION))
    		this.duration = compound.getInt(NBT_DURATION);
    }
    
    @Override
    public void writeAdditional(CompoundNBT compound) 
    {
    	super.writeAdditional(compound);
    	compound.putInt(NBT_DURATION, this.duration);
    }
}
