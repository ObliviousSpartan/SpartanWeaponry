package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;
import net.minecraftforge.registries.ForgeRegistries;

public class ArrowBaseEntity extends AbstractArrow implements IEntityAdditionalSpawnData
{
	protected final String NBT_ARROW = "Arrow";
	protected final String NBT_POTION = "Potion";
	protected final String NBT_POTION_COLOUR = "PotionColour";
	
	protected static final EntityDataAccessor<Integer> COLOUR = SynchedEntityData.defineId(ArrowBaseEntity.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<ItemStack> ARROW = SynchedEntityData.defineId(ArrowBaseEntity.class, EntityDataSerializers.ITEM_STACK);
	protected Potion potion = Potions.EMPTY;
	
	protected float baseDamage = 1.0f;
	protected float rangeMultiplier = 1.0f;
	
	public ArrowBaseEntity(EntityType<? extends ArrowBaseEntity> type, Level level) 
	{
		super(type, level);
	}

	public ArrowBaseEntity(Level level, double x, double y, double z) 
	{
		super(ModEntities.ARROW_SW.get() , x, y, z, level);
	}

	public ArrowBaseEntity(Level level, LivingEntity shooter) 
	{
		super(ModEntities.ARROW_SW.get(), shooter, level);
	}
	
	public ArrowBaseEntity(SpawnEntity spawnEntity, Level level)
	{
		this(ModEntities.ARROW_SW.get(), level);
	}
	
	public void initEntity(float baseDamage, float rangeMultiplier, ItemStack arrowStack)
	{
		this.baseDamage = baseDamage;
		this.rangeMultiplier = rangeMultiplier;
		setBaseDamage(baseDamage);
		setArrowStack(arrowStack);
	}
	
	@Override
	public void shootFromRotation(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) 
	{
		super.shootFromRotation(shooter, pitch, yaw, p_184547_4_, (float)(velocity * rangeMultiplier), inaccuracy);
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		getEntityData().define(COLOUR, -1);
		getEntityData().define(ARROW, ItemStack.EMPTY);
	}

	@Override
	public void tick() 
	{
		super.tick();
		if(level.isClientSide /*&& potion != null && potion != Potions.EMPTY*/)
		{
			if(inGround)
			{
				if(inGroundTime % 5 == 0)
					spawnPotionParticles(1);
			}
			else
				spawnPotionParticles(2);
		}
		else if(inGround && inGroundTime != 0 && inGroundTime >= 600)
		{
	         level.broadcastEntityEvent(this, (byte)0);
	         potion = Potions.EMPTY;
	         getEntityData().set(COLOUR, -1);
		}
	}
	
	@Override
	protected void doPostHurtEffects(LivingEntity living) 
	{
		Entity entity = getEffectSource();
		
		for(MobEffectInstance effect : potion.getEffects())
		{
			living.addEffect(new MobEffectInstance(effect.getEffect(), Math.max(effect.getDuration() / 8, 1), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()), entity);
		}
		
		Item arrowItem = getPickupItem().getItem();
		
		// Spawn lightning under the right weather conditions (during a thunderstorm)
		if(level.isThundering() && arrowItem == ModItems.COPPER_ARROW.get() || arrowItem == ModItems.TIPPED_COPPER_ARROW.get())
		{
			// Roll a chance to spawn lightning under the right circumstances
			if(random.nextInt(4) < 1)	// ~25%
			{
				LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
				lightning.moveTo(Vec3.atBottomCenterOf(living.blockPosition()));
				lightning.setCause(living instanceof ServerPlayer ? (ServerPlayer)living : null);
				level.addFreshEntity(lightning);
			}
		}
	}
	
	@Override
	public void handleEntityEvent(byte id) 
	{
		if (id == 0) 
		{
			int i = getEntityData().get(COLOUR);
			if (i != -1) 
			{
	           double cR = (double)(i >> 16 & 255) / 255.0D;
	           double cG = (double)(i >> 8 & 255) / 255.0D;
	           double cB = (double)(i >> 0 & 255) / 255.0D;
	
	           for(int j = 0; j < 20; ++j)
	           {
	              level.addParticle(ParticleTypes.ENTITY_EFFECT, getRandomX(0.5d), getRandomY(), getRandomZ(0.5d), cR, cG, cB);
	           }
			}
		} 
		else 
		{
			super.handleEntityEvent(id);
		}
	}
	
	@Override
	protected ItemStack getPickupItem() 
	{
		return getEntityData().get(ARROW);
//		return arrowStack;
	}
	
	protected void setArrowStack(ItemStack stack)
	{
		ItemStack copy = stack.copy();
		copy.setCount(1);
		getEntityData().set(ARROW, copy);
	}

	@Override
	public Packet<?> getAddEntityPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void writeSpawnData(FriendlyByteBuf buffer) 
	{
//		buffer.writeItemStack(arrowStack);
		buffer.writeItem(getPickupItem());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) 
	{
		super.addAdditionalSaveData(compound);
		CompoundTag nbt = new CompoundTag();
		nbt = getPickupItem().save(nbt);
		compound.put(NBT_ARROW, nbt);
		
		if(potion != null && potion != Potions.EMPTY)
		{
			compound.putString(NBT_POTION, ForgeRegistries.POTIONS.getKey(potion).toString());
		}
		
		compound.putInt(NBT_POTION_COLOUR, getEntityData().get(COLOUR));
	}

	@Override
	public void readSpawnData(FriendlyByteBuf additionalData) 
	{
		setArrowStack(additionalData.readItem());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) 
	{
		super.readAdditionalSaveData(compound);
		CompoundTag nbt = compound.getCompound(NBT_ARROW);
		setArrowStack(ItemStack.of(nbt));
		
		if(compound.contains(NBT_POTION, 8))
		{
			potion = PotionUtils.getPotion(compound);
		}
		
		getEntityData().set(COLOUR, compound.contains(NBT_POTION_COLOUR) ? compound.getInt(NBT_POTION_COLOUR) : -1);
	}
	
	public boolean isValid()
	{
		return !getPickupItem().isEmpty();
	}
	
	public ResourceLocation getTexture()
	{
		String arrowRegName = ForgeRegistries.ITEMS.getKey(getPickupItem().getItem()).getPath();
		
		if(ForgeRegistries.POTIONS.getKey(potion).getPath() != "empty")
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
		potion = PotionUtils.getPotion(stack);
		getEntityData().set(COLOUR, PotionUtils.getColor(stack));
	}
	
	public void spawnPotionParticles(int particleCount)
	{
		int colour = getEntityData().get(COLOUR);
		if(colour != -1 && particleCount > 0)
		{
	         double cR = (double)(colour >> 16 & 255) / 255.0D;
	         double cG = (double)(colour >> 8 & 255) / 255.0D;
	         double cB = (double)(colour >> 0 & 255) / 255.0D;
	         
	         for(int i = 0; i < particleCount; i++)
	         {
	        	 level.addParticle(ParticleTypes.ENTITY_EFFECT, getRandomX(0.5d), getRandomY(), getRandomZ(0.5d), cR, cG, cB);
	         }
		}
	}
}
