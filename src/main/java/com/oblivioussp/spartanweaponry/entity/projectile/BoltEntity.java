package com.oblivioussp.spartanweaponry.entity.projectile;

import java.util.Arrays;

import com.google.common.collect.Lists;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.damagesource.ArmorPiercingIndirectEntityDamageSource;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModItems;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;
import net.minecraftforge.registries.ForgeRegistries;

public class BoltEntity extends AbstractArrow implements IEntityAdditionalSpawnData
{
	protected final String NBT_BOLT = "Bolt";
	protected final String NBT_POTION = "Potion";
	protected final String NBT_POTION_COLOUR = "PotionColour";

	protected static final EntityDataAccessor<Integer> DATA_COLOUR = SynchedEntityData.defineId(BoltEntity.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<ItemStack> DATA_BOLT = SynchedEntityData.defineId(BoltEntity.class, EntityDataSerializers.ITEM_STACK);
	protected Potion potion = Potions.EMPTY;
	
	protected float baseDamage = 1.0f;
	protected float rangeMultiplier = 1.0f;
	protected float armorPiercingFactor = 0.0f;
	
	public BoltEntity(EntityType<? extends BoltEntity> type, Level level)
    {
        super(type, level);
    }

    public BoltEntity(EntityType<? extends BoltEntity> type, double x, double y, double z, Level level)
    {
        super(type, x, y, z, level);
    }
    
    public BoltEntity(EntityType<? extends BoltEntity> type, LivingEntity shooter, Level level)
    {
        super(type, shooter, level);
    }

    public BoltEntity(LivingEntity shooter, Level level)
    {
    	this(ModEntities.BOLT.get(), shooter, level);
    }
    
    public BoltEntity(SpawnEntity spawnEntity, Level level)
    {
    	this(ModEntities.BOLT.get(), level);
    }
	
	public void initEntity(float baseDamage, float rangeMultiplier, float armorPiercingFactor, ItemStack boltStack)
	{
		this.baseDamage = baseDamage;
		this.rangeMultiplier = rangeMultiplier;
		this.armorPiercingFactor = armorPiercingFactor;
		this.setBaseDamage(baseDamage);
		getEntityData().set(DATA_BOLT, boltStack);
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
		getEntityData().define(DATA_COLOUR, -1);
		getEntityData().define(DATA_BOLT, ItemStack.EMPTY);
	}
    
    @Override
    public void tick() 
    {
    	super.tick();

		if(level.isClientSide)
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
	         getEntityData().set(DATA_COLOUR, -1);
		}
    }
    
    protected DamageSource causeArmorPiercingDamage(Entity sourceEntity, Entity indirectEntity)
    {
    	return new ArmorPiercingIndirectEntityDamageSource("arrow", sourceEntity, indirectEntity, armorPiercingFactor).setProjectile();
    }
    
    @Override
    protected void onHitEntity(EntityHitResult result)
    {
    	Entity entity = result.getEntity();
        float velocity = (float)getDeltaMovement().length();
        int damage = Mth.ceil(Mth.clamp((double)velocity * getBaseDamage(), 0.0D, 2.147483647E9D));
        if(getPierceLevel() > 0) 
        {
        	if(piercingIgnoreEntityIds == null)
        		piercingIgnoreEntityIds = new IntOpenHashSet(5);

        	if(piercedAndKilledEntities == null)
        		piercedAndKilledEntities = Lists.newArrayListWithCapacity(5);
		
        	if(piercingIgnoreEntityIds.size() >= getPierceLevel() + 1)
        	{
        		discard();
        		return;
        	}

        	piercingIgnoreEntityIds.add(entity.getId());
        }

        if(isCritArrow()) 
        {
        	long critDamageBonus = (long)random.nextInt(damage / 2 + 2);
        	damage = (int)Math.min(critDamageBonus + (long)damage, 2147483647L);
        }

        Entity shooter = getOwner();
        DamageSource source;
        if(shooter == null)
        	source = causeArmorPiercingDamage(this, this);
        else 
        {
        	source = causeArmorPiercingDamage(this, shooter);
        	if(shooter instanceof LivingEntity)
        		((LivingEntity)shooter).setLastHurtMob(entity);
        }

        boolean isEnderman = entity.getType() == EntityType.ENDERMAN;
        int fireTimer = entity.getRemainingFireTicks();
        if(isOnFire() && !isEnderman)
        	entity.setSecondsOnFire(5);

        if(entity.hurt(source, (float)damage)) 
        {
        	if(isEnderman)
        		return;

        	if(entity instanceof LivingEntity) 
        	{
        		LivingEntity livingentity = (LivingEntity)entity;
        		if(!level.isClientSide && getPierceLevel() <= 0)
        			livingentity.setArrowCount(livingentity.getArrowCount() + 1);

        		if(getKnockback() > 0)
        		{
        			Vec3 vector3d = getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)getKnockback() * 0.6D);
        			if (vector3d.lengthSqr() > 0.0D)
        				livingentity.push(vector3d.x, 0.1D, vector3d.z);
        		}

        		if(!level.isClientSide && shooter instanceof LivingEntity) 
        		{
        			EnchantmentHelper.doPostHurtEffects(livingentity, shooter);
        			EnchantmentHelper.doPostDamageEffects((LivingEntity)shooter, livingentity);
        		}

        		doPostHurtEffects(livingentity);
        		if(shooter != null && livingentity != shooter && livingentity instanceof Player && shooter instanceof ServerPlayer && !isSilent())
        			((ServerPlayer)shooter).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));

        		if(!entity.isAlive() && piercedAndKilledEntities != null)
        			piercedAndKilledEntities.add(livingentity);

        		if(!level.isClientSide && shooter instanceof ServerPlayer)
        		{
        			ServerPlayer serverplayerentity = (ServerPlayer)shooter;
        			if (piercedAndKilledEntities != null && shotFromCrossbow()) 
        				CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, piercedAndKilledEntities);
        			else if (!entity.isAlive() && shotFromCrossbow())
        				CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, Arrays.asList(entity));
        		}
        	}

        	playSound(getHitGroundSoundEvent(), 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));
        	if(getPierceLevel() <= 0)
        		discard();
        } 
        else 
        {
        	entity.setRemainingFireTicks(fireTimer);
        	setDeltaMovement(getDeltaMovement().scale(-0.1D));
        	setYRot(getYRot() + 180.0f);
        	yRotO += 180.0F;
        	if(!level.isClientSide && getDeltaMovement().lengthSqr() < 1.0E-7D)
        	{
        		if (pickup == AbstractArrow.Pickup.ALLOWED)
        			spawnAtLocation(getPickupItem(), 0.1F);

        		discard();
        	}
        }
    }
	
	@Override
	protected void doPostHurtEffects(LivingEntity living) 
	{
		super.doPostHurtEffects(living);
		
		for(MobEffectInstance effect : potion.getEffects())
		{
			living.addEffect(new MobEffectInstance(effect.getEffect(), Math.max(effect.getDuration() / 8, 1), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
		}
		
		Item arrowItem = getPickupItem().getItem();
		
		// Spawn lightning under the right weather conditions (during a thunderstorm)
		if(level.isThundering() && arrowItem == ModItems.COPPER_BOLT.get() || arrowItem == ModItems.TIPPED_COPPER_BOLT.get())
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
	protected ItemStack getPickupItem()
	{
		return getEntityData().get(DATA_BOLT);
	}

	@Override
	public Packet<?> getAddEntityPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void readSpawnData(FriendlyByteBuf buffer)
	{
		double x, y, z;
		x = buffer.readDouble();
		y = buffer.readDouble();
		z = buffer.readDouble();
		setDeltaMovement(x, y, z);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		
		if(compound.contains(NBT_POTION, 8))
		{
			potion = PotionUtils.getPotion(compound);
		}
		
		getEntityData().set(DATA_COLOUR, compound.contains(NBT_POTION_COLOUR) ? compound.getInt(NBT_POTION_COLOUR) : -1);
	}

	@Override
	public void writeSpawnData(FriendlyByteBuf buffer)
	{
		buffer.writeDouble(getDeltaMovement().x);
		buffer.writeDouble(getDeltaMovement().y);
		buffer.writeDouble(getDeltaMovement().z);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) 
	{
		super.addAdditionalSaveData(compound);
		
		if(potion != null && potion != Potions.EMPTY)
		{
			compound.putString(NBT_POTION, ForgeRegistries.POTIONS.getKey(potion).toString());
		}
		
		compound.putInt(NBT_POTION_COLOUR, getEntityData().get(DATA_COLOUR));
	}
	
	public void setPotionEffect(ItemStack stack)
	{
		potion = PotionUtils.getPotion(stack);
		getEntityData().set(DATA_COLOUR, PotionUtils.getColor(stack));
	}
	
	private void spawnPotionParticles(int particleCount)
	{
		int colour = getEntityData().get(DATA_COLOUR);
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
	
	public boolean isValid()
	{
		return !getPickupItem().isEmpty();
	}
	
	public ResourceLocation getTexture()
	{
		ItemStack boltStack = getPickupItem();
		if(boltStack.isEmpty())
			return new ResourceLocation(ModSpartanWeaponry.ID, "missing_stack");
					
		String boltRegName = ForgeRegistries.ITEMS.getKey(boltStack.getItem()).getPath();
		
		if(ForgeRegistries.POTIONS.getKey(potion).getPath() != "empty")
		{
			int idx = boltRegName.indexOf("_tipped");
			if(idx != -1)
			{
				boltRegName = boltRegName.substring(0, idx);
			}
		}
		return new ResourceLocation(ModSpartanWeaponry.ID, "textures/entity/projectiles/" + boltRegName + ".png");
	}
}
