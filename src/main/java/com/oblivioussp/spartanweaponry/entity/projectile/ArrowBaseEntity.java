package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModEntities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

public class ArrowBaseEntity extends AbstractArrowEntity implements IEntityAdditionalSpawnData
{
	protected final String NBT_ARROW = "Arrow";
	protected final String NBT_POTION = "Potion";
	protected final String NBT_POTION_COLOUR = "PotionColour";
	
	protected static final DataParameter<Integer> COLOUR = EntityDataManager.createKey(ArrowBaseEntity.class, DataSerializers.VARINT);
	protected static final DataParameter<ItemStack> WEAPON = EntityDataManager.createKey(ArrowBaseEntity.class, DataSerializers.ITEMSTACK);
	protected Potion potion = Potions.EMPTY;
	
	protected float baseDamage = 1.0f;
	protected float rangeMultiplier = 1.0f;
	
	public ArrowBaseEntity(EntityType<? extends ArrowBaseEntity> type, World world) 
	{
		super(type, world);
	}

	public ArrowBaseEntity(World worldIn, double x, double y, double z) 
	{
		super(ModEntities.ARROW_SW , x, y, z, worldIn);
	}

	public ArrowBaseEntity(World worldIn, LivingEntity shooter) 
	{
		super(ModEntities.ARROW_SW, shooter, worldIn);
	}
	
	public ArrowBaseEntity(SpawnEntity spawnEntity, World world)
	{
		this(ModEntities.ARROW_SW, world);
	}
	
	public void initEntity(float baseDamageIn, float rangeMultiplierIn, ItemStack arrowStack)
	{
		baseDamage = baseDamageIn;
		rangeMultiplier = rangeMultiplierIn;
		setDamage(baseDamageIn);
		setArrowStack(arrowStack);
	}
	
	@Override
	public void setDirectionAndMovement(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) 
	{
		super.setDirectionAndMovement(shooter, pitch, yaw, p_184547_4_, (float)(velocity * rangeMultiplier), inaccuracy);
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();
		dataManager.register(COLOUR, -1);
		dataManager.register(WEAPON, ItemStack.EMPTY);
	}

	@Override
	public void tick() 
	{
		super.tick();
		if(world.isRemote)
		{
			if(inGround)
			{
				if(timeInGround % 5 == 0)
					spawnPotionParticles(1);
			}
			else
				spawnPotionParticles(2);
		}
		else if(inGround && timeInGround != 0 && timeInGround >= 600)
		{
	         world.setEntityState(this, (byte)0);
	         potion = Potions.EMPTY;
	         dataManager.set(COLOUR, -1);
		}
	}
	
	@Override
	protected void arrowHit(LivingEntity living) 
	{
		super.arrowHit(living);
		
		for(EffectInstance effect : potion.getEffects())
		{
			living.addPotionEffect(new EffectInstance(effect.getPotion(), Math.max(effect.getDuration() / 8, 1), effect.getAmplifier(), effect.isAmbient(), effect.doesShowParticles()));
		}
	}
	
	@Override
	public void handleStatusUpdate(byte id) 
	{
		if (id == 0) 
		{
			int i = dataManager.get(COLOUR);
			if (i != -1) 
			{
	           double cR = (double)(i >> 16 & 255) / 255.0D;
	           double cG = (double)(i >> 8 & 255) / 255.0D;
	           double cB = (double)(i >> 0 & 255) / 255.0D;
	
	           for(int j = 0; j < 20; ++j)
	           {
	              world.addParticle(ParticleTypes.ENTITY_EFFECT, getPosX() + (rand.nextDouble() - 0.5D) * (double)getWidth(), getPosY() + rand.nextDouble() * (double)getHeight(), getPosZ() + (rand.nextDouble() - 0.5D) * (double)getWidth(), cR, cG, cB);
	           }
			}
		} 
		else 
		{
			super.handleStatusUpdate(id);
		}
	}
	
	@Override
	protected ItemStack getArrowStack() 
	{
		return getDataManager().get(WEAPON);
	}
	
	protected void setArrowStack(ItemStack stack)
	{
		ItemStack copy = stack.copy();
		copy.setCount(1);
		getDataManager().set(WEAPON, copy);
	}

	@Override
	public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void writeSpawnData(PacketBuffer buffer) 
	{
		buffer.writeItemStack(getArrowStack());
	}

	@Override
	public void writeAdditional(CompoundNBT compound) 
	{
		super.writeAdditional(compound);
		CompoundNBT nbt = new CompoundNBT();
		nbt = getArrowStack().write(nbt);
		compound.put(NBT_ARROW, nbt);
		
		if(potion != null && potion != Potions.EMPTY)
		{
			compound.putString(NBT_POTION, ForgeRegistries.POTION_TYPES.getKey(potion).toString());
		}
		
		compound.putInt(NBT_POTION_COLOUR, dataManager.get(COLOUR));
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) 
	{
		setArrowStack(additionalData.readItemStack());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) 
	{
		super.readAdditional(compound);
		CompoundNBT nbt = compound.getCompound(NBT_ARROW);
		setArrowStack(ItemStack.read(nbt));
		
		if(compound.contains(NBT_POTION, 8))
		{
			potion = PotionUtils.getPotionTypeFromNBT(compound);
		}
		
		dataManager.set(COLOUR, compound.contains(NBT_POTION_COLOUR) ? compound.getInt(NBT_POTION_COLOUR) : -1);
	}
	
	public boolean isValid()
	{
		return !getArrowStack().isEmpty();
	}
	
	public ResourceLocation getTexture()
	{
		String arrowRegName = getArrowStack().getItem().getRegistryName().getPath();
		
		if(potion.getRegistryName().getPath() != "empty")
		{
			int idx = arrowRegName.indexOf("_tipped");
			if(idx != -1)
			{
				arrowRegName = arrowRegName.substring(0, idx);
			}
		}
		return new ResourceLocation(ModSpartanWeaponry.ID, "textures/entity/projectiles/" + arrowRegName + ".png");
	}
	
	public void setPotionEffect(ItemStack stack)
	{
		potion = PotionUtils.getPotionFromItem(stack);
		dataManager.set(COLOUR, PotionUtils.getColor(stack));
	}
	
	public void spawnPotionParticles(int particleCount)
	{
		int colour = dataManager.get(COLOUR);
		if(colour != -1 && particleCount > 0)
		{
	         double cR = (double)(colour >> 16 & 255) / 255.0D;
	         double cG = (double)(colour >> 8 & 255) / 255.0D;
	         double cB = (double)(colour >> 0 & 255) / 255.0D;
	         
	         for(int i = 0; i < particleCount; i++)
	         {
	        	 world.addParticle(ParticleTypes.ENTITY_EFFECT, getPosX() + (rand.nextDouble() - 0.5D) * (double)getWidth(), getPosY() + rand.nextDouble() * (double)getHeight(), getPosZ() + (rand.nextDouble() - 0.5D) * (double)getWidth(), cR, cG, cB);
	         }
		}
	}
}
