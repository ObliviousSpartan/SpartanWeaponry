package com.oblivioussp.spartanweaponry.entity.projectile;

import java.util.Arrays;

import com.google.common.collect.Lists;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.util.ArmorPiercingIndirectEntityDamageSource;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

public class BoltEntity extends AbstractArrowEntity implements IEntityAdditionalSpawnData
{
	protected final String NBT_BOLT = "Bolt";
	protected final String NBT_POTION = "Potion";
	protected final String NBT_POTION_COLOUR = "PotionColour";

	protected static final DataParameter<Integer> DATA_COLOUR = EntityDataManager.createKey(BoltEntity.class, DataSerializers.VARINT);
	protected static final DataParameter<ItemStack> DATA_BOLT = EntityDataManager.createKey(BoltEntity.class, DataSerializers.ITEMSTACK);
	protected Potion potion = Potions.EMPTY;
	
	protected float baseDamage = 1.0f;
	protected float rangeMultiplier = 1.0f;
	protected float armorPiercingFactor = 0.0f;
	
	public BoltEntity(EntityType<? extends BoltEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public BoltEntity(EntityType<? extends BoltEntity> type, double x, double y, double z, World worldIn)
    {
        super(type, x, y, z, worldIn);
    }
    
    public BoltEntity(EntityType<? extends BoltEntity> type, LivingEntity shooter, World world)
    {
        super(type, shooter, world);
    }

    public BoltEntity(LivingEntity shooter, World world)
    {
    	this(ModEntities.BOLT, shooter, world);
    }
    
    public BoltEntity(SpawnEntity spawnEntity, World world)
    {
    	this(ModEntities.BOLT, world);
    }
	
	public void initEntity(float baseDamageIn, float rangeMultiplierIn, float armorPiercingFactorIn, ItemStack boltStack)
	{
		baseDamage = baseDamageIn;
		rangeMultiplier = rangeMultiplierIn;
		armorPiercingFactor = armorPiercingFactorIn;
		setDamage(baseDamageIn);
		getDataManager().set(DATA_BOLT, boltStack);
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
		dataManager.register(DATA_COLOUR, -1);
		dataManager.register(DATA_BOLT, ItemStack.EMPTY);
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
	         dataManager.set(DATA_COLOUR, -1);
		}
    }
    
    protected DamageSource causeArmorPiercingDamage(Entity sourceEntity, Entity indirectEntity)
    {
    	return new ArmorPiercingIndirectEntityDamageSource("arrow", sourceEntity, indirectEntity, armorPiercingFactor).setProjectile();
    }
    
    @Override
    protected void onEntityHit(EntityRayTraceResult result)
    {
    	Entity entity = result.getEntity();
        float velocity = (float)getMotion().length();
        int damage = MathHelper.ceil(MathHelper.clamp((double)velocity * getDamage(), 0.0D, 2.147483647E9D));
        if(getPierceLevel() > 0) 
        {
        	if(piercedEntities == null)
        		piercedEntities = new IntOpenHashSet(5);

        	if(hitEntities == null)
        		hitEntities = Lists.newArrayListWithCapacity(5);
		
        	if(piercedEntities.size() >= getPierceLevel() + 1)
        	{
        		remove();
        		return;
        	}

        	piercedEntities.add(entity.getEntityId());
        }

        if(getIsCritical()) 
        {
        	long critDamageBonus = (long)rand.nextInt(damage / 2 + 2);
        	damage = (int)Math.min(critDamageBonus + (long)damage, 2147483647L);
        }

        Entity shooter = getShooter();
        DamageSource source;
        if(shooter == null)
        	source = causeArmorPiercingDamage(this, this);
        else 
        {
        	source = causeArmorPiercingDamage(this, shooter);
        	if(shooter instanceof LivingEntity)
        		((LivingEntity)shooter).setLastAttackedEntity(entity);
        }

        boolean isEnderman = entity.getType() == EntityType.ENDERMAN;
        int fireTimer = entity.getFireTimer();
        if(isBurning() && !isEnderman)
        	entity.setFire(5);

        if(entity.attackEntityFrom(source, (float)damage)) 
        {
        	if(isEnderman)
        		return;

        	if(entity instanceof LivingEntity) 
        	{
        		LivingEntity livingentity = (LivingEntity)entity;
        		if(!world.isRemote && getPierceLevel() <= 0)
        			livingentity.setArrowCountInEntity(livingentity.getArrowCountInEntity() + 1);

        		if(knockbackStrength > 0)
        		{
        			Vector3d vector3d = getMotion().mul(1.0D, 0.0D, 1.0D).normalize().scale((double)knockbackStrength * 0.6D);
        			if (vector3d.lengthSquared() > 0.0D)
        				livingentity.addVelocity(vector3d.x, 0.1D, vector3d.z);
        		}

        		if(!world.isRemote && shooter instanceof LivingEntity) 
        		{
        			EnchantmentHelper.applyThornEnchantments(livingentity, shooter);
        			EnchantmentHelper.applyArthropodEnchantments((LivingEntity)shooter, livingentity);
        		}

        		arrowHit(livingentity);
        		if(shooter != null && livingentity != shooter && livingentity instanceof PlayerEntity && shooter instanceof ServerPlayerEntity && !isSilent())
        			((ServerPlayerEntity)shooter).connection.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.HIT_PLAYER_ARROW, 0.0F));

        		if(!entity.isAlive() && hitEntities != null)
        			hitEntities.add(livingentity);

        		if(!world.isRemote && shooter instanceof ServerPlayerEntity)
        		{
        			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)shooter;
        			if (hitEntities != null && getShotFromCrossbow()) 
        				CriteriaTriggers.KILLED_BY_CROSSBOW.test(serverplayerentity, hitEntities);
        			else if (!entity.isAlive() && getShotFromCrossbow())
        				CriteriaTriggers.KILLED_BY_CROSSBOW.test(serverplayerentity, Arrays.asList(entity));
        		}
        	}

        	playSound(getHitGroundSound(), 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
        	if(getPierceLevel() <= 0)
        		remove();
        } 
        else 
        {
        	entity.forceFireTicks(fireTimer);
        	setMotion(getMotion().scale(-0.1D));
        	rotationYaw += 180.0F;
        	prevRotationYaw += 180.0F;
        	if(!world.isRemote && getMotion().lengthSquared() < 1.0E-7D)
        	{
        		if (pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED)
        			entityDropItem(getArrowStack(), 0.1F);

        		remove();
        	}
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
	protected ItemStack getArrowStack()
	{
		return getDataManager().get(DATA_BOLT);
	}

	@Override
	public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void readSpawnData(PacketBuffer buffer)
	{
		double x, y, z;
		x = buffer.readDouble();
		y = buffer.readDouble();
		z = buffer.readDouble();
		setMotion(x, y, z);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		
		if(compound.contains(NBT_POTION, 8))
		{
			potion = PotionUtils.getPotionTypeFromNBT(compound);
		}
		
		dataManager.set(DATA_COLOUR, compound.contains(NBT_POTION_COLOUR) ? compound.getInt(NBT_POTION_COLOUR) : -1);
	}

	@Override
	public void writeSpawnData(PacketBuffer buffer)
	{
		buffer.writeDouble(getMotion().getX());
		buffer.writeDouble(getMotion().getY());
		buffer.writeDouble(getMotion().getZ());
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) 
	{
		super.writeAdditional(compound);
		
		if(potion != null && potion != Potions.EMPTY)
		{
			compound.putString(NBT_POTION, ForgeRegistries.POTION_TYPES.getKey(potion).toString());
		}
		
		compound.putInt(NBT_POTION_COLOUR, dataManager.get(DATA_COLOUR));
	}
	
	public void setPotionEffect(ItemStack stack)
	{
		potion = PotionUtils.getPotionFromItem(stack);
		dataManager.set(DATA_COLOUR, PotionUtils.getColor(stack));
	}
	
	private void spawnPotionParticles(int particleCount)
	{
		int colour = dataManager.get(DATA_COLOUR);
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
	
	public boolean isValid()
	{
		return !getArrowStack().isEmpty();
	}
	
	public ResourceLocation getTexture()
	{
		ItemStack boltStack = getArrowStack();
		if(boltStack.isEmpty())
			return new ResourceLocation(ModSpartanWeaponry.ID, "missing_stack");
					
		String boltRegName = boltStack.getItem().getRegistryName().getPath();
		
		if(potion.getRegistryName().getPath() != "empty")
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
