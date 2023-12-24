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
	//protected Set<EffectInstance> customEffects = new HashSet<EffectInstance>();
	//protected boolean fixedColour;
	
	protected float baseDamage = 1.0f;
	protected float rangeMultiplier = 1.0f;
//	protected ItemStack arrowStack = ItemStack.EMPTY;
	
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
	
	public void initEntity(float baseDamage, float rangeMultiplier, ItemStack arrowStack)
	{
		this.baseDamage = baseDamage;
		this.rangeMultiplier = rangeMultiplier;
		this.setDamage(this.baseDamage);
//		this.arrowStack = arrowStack;
		setArrowStack(arrowStack);
	}
	
	/*@Override
	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) 
	{
		super.shoot(shooter, pitch, yaw, p_184547_4_, (float)(velocity * rangeMultiplier), inaccuracy);
	}*/
	
	@Override
	public void setDirectionAndMovement(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) 
	{
		super.setDirectionAndMovement(shooter, pitch, yaw, p_184547_4_, (float)(velocity * rangeMultiplier), inaccuracy);
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();
		this.dataManager.register(COLOUR, -1);
		this.dataManager.register(WEAPON, ItemStack.EMPTY);
	}

	@Override
	public void tick() 
	{
		super.tick();
		if(this.world.isRemote /*&& this.potion != null && this.potion != Potions.EMPTY*/)
		{
			if(this.inGround)
			{
				if(this.timeInGround % 5 == 0)
					spawnPotionParticles(1);
			}
			else
				spawnPotionParticles(2);
		}
		else if(this.inGround && this.timeInGround != 0 && this.timeInGround >= 600)
		{
	         this.world.setEntityState(this, (byte)0);
	         this.potion = Potions.EMPTY;
	         this.dataManager.set(COLOUR, -1);
		}
	}
	
	@Override
	protected void arrowHit(LivingEntity living) 
	{
		super.arrowHit(living);
		
		for(EffectInstance effect : this.potion.getEffects())
		{
			living.addPotionEffect(new EffectInstance(effect.getPotion(), Math.max(effect.getDuration() / 8, 1), effect.getAmplifier(), effect.isAmbient(), effect.doesShowParticles()));
		}
	}
	
	@Override
	public void handleStatusUpdate(byte id) 
	{
		if (id == 0) 
		{
			int i = this.dataManager.get(COLOUR);
			if (i != -1) 
			{
	           double cR = (double)(i >> 16 & 255) / 255.0D;
	           double cG = (double)(i >> 8 & 255) / 255.0D;
	           double cB = (double)(i >> 0 & 255) / 255.0D;
	
	           for(int j = 0; j < 20; ++j)
	           {
	              this.world.addParticle(ParticleTypes.ENTITY_EFFECT, this.getPosX() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), this.getPosY() + this.rand.nextDouble() * (double)this.getHeight(), this.getPosZ() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), cR, cG, cB);
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
//		return arrowStack;
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
//		buffer.writeItemStack(this.arrowStack);
		buffer.writeItemStack(getArrowStack());
	}

	@Override
	public void writeAdditional(CompoundNBT compound) 
	{
		super.writeAdditional(compound);
		CompoundNBT nbt = new CompoundNBT();
//		nbt = this.arrowStack.write(nbt);
		nbt = getArrowStack().write(nbt);
		compound.put(NBT_ARROW, nbt);
		
		if(this.potion != null && this.potion != Potions.EMPTY)
		{
			compound.putString(NBT_POTION, ForgeRegistries.POTION_TYPES.getKey(this.potion).toString());
		}
		
		compound.putInt(NBT_POTION_COLOUR, dataManager.get(COLOUR));
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) 
	{
//		this.arrowStack = additionalData.readItemStack();
		setArrowStack(additionalData.readItemStack());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) 
	{
		super.readAdditional(compound);
		CompoundNBT nbt = compound.getCompound(NBT_ARROW);
//		this.arrowStack = ItemStack.read(nbt);
		setArrowStack(ItemStack.read(nbt));
		
		if(compound.contains(NBT_POTION, 8))
		{
			this.potion = PotionUtils.getPotionTypeFromNBT(compound);
		}
		
		dataManager.set(COLOUR, compound.contains(NBT_POTION_COLOUR) ? compound.getInt(NBT_POTION_COLOUR) : -1);
	}
	
	public boolean isValid()
	{
//		return !this.arrowStack.isEmpty();
		return !getArrowStack().isEmpty();
	}
	
	public ResourceLocation getTexture()
	{
//		String arrowRegName = arrowStack.getItem().getRegistryName().getPath();
		String arrowRegName = getArrowStack().getItem().getRegistryName().getPath();
		
		if(this.potion.getRegistryName().getPath() != "empty")
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
		this.potion = PotionUtils.getPotionFromItem(stack);
		/*List<EffectInstance> effects = PotionUtils.getFullEffectsFromItem(stack);
		if(!effects.isEmpty())
		{
			for(EffectInstance effect : effects)
				customEffects.add(new EffectInstance(effect));
		}*/
		dataManager.set(COLOUR, PotionUtils.getColor(stack));
	}
	
	public void spawnPotionParticles(int particleCount)
	{
		int colour = this.dataManager.get(COLOUR);
		if(colour != -1 && particleCount > 0)
		{
	         double cR = (double)(colour >> 16 & 255) / 255.0D;
	         double cG = (double)(colour >> 8 & 255) / 255.0D;
	         double cB = (double)(colour >> 0 & 255) / 255.0D;
	         
	         for(int i = 0; i < particleCount; i++)
	         {
	        	 this.world.addParticle(ParticleTypes.ENTITY_EFFECT, this.getPosX() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), this.getPosY() + this.rand.nextDouble() * (double)this.getHeight(), this.getPosZ() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), cR, cG, cB);
	         }
		}
	}
}
