package com.oblivioussp.spartanweaponry.entity.projectile;

import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.trait.IMeleeTraitCallback;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.init.ModEnchantments;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;
import net.minecraftforge.fml.network.NetworkHooks;

public class ThrowingWeaponEntity extends AbstractArrowEntity implements IEntityAdditionalSpawnData
{
	public static final String NBT_WEAPON = "Weapon";
	protected static final DataParameter<ItemStack> DATA_WEAPON = EntityDataManager.createKey(ThrowingWeaponEntity.class, DataSerializers.ITEMSTACK);
	protected static final DataParameter<Byte> DATA_RETURN = EntityDataManager.createKey(ThrowingWeaponEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Boolean> DATA_IS_RETURNING = EntityDataManager.createKey(ThrowingWeaponEntity.class, DataSerializers.BOOLEAN);
	protected int ticksInAir = 0;
	protected float waterDrag = 0.0f;
	protected boolean playedReturnSound = false;
	
	public ThrowingWeaponEntity(EntityType<? extends ThrowingWeaponEntity> type, World world) 
	{
		super(type, world);
	}

	public ThrowingWeaponEntity(EntityType<? extends ThrowingWeaponEntity> type, World world, double x, double y, double z) 
	{
		super(type , x, y, z, world);
	}

	public ThrowingWeaponEntity(EntityType<? extends ThrowingWeaponEntity> type, LivingEntity shooter, World world) 
	{
		super(type, shooter, world);
	}
	
	public ThrowingWeaponEntity(SpawnEntity spawnEntity, World world)
	{
		this(ModEntities.THROWING_WEAPON, world);
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();
		getDataManager().register(DATA_WEAPON, ItemStack.EMPTY);
		getDataManager().register(DATA_RETURN, (byte)0);
		getDataManager().register(DATA_IS_RETURNING, false);
	}

	@Override
	protected ItemStack getArrowStack()
	{
		return getDataManager().get(DATA_WEAPON);
	}
	
	public boolean isReturning()
	{
		return getDataManager().get(DATA_IS_RETURNING);
	}
	
	@Override
	public void tick() 
	{
		if(waterDrag == 0.0f)
		{
			if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.THROWING_HYDRODYNAMIC, getWeaponStack()) == 1)
				waterDrag = 0.98f;
			else
				waterDrag = -1.0f;
		}
		
		Entity thrower = getShooter();			//func_234616_v_();
		if((timeInGround > 4 || isReturning()) && thrower != null)
		{
			int returnLevel = getDataManager().get(DATA_RETURN);
			if(returnLevel > 0)
			{
				if(thrower != null && thrower.isAlive() && !thrower.isSpectator())
				{
					// Return to thrower
					if(!isReturning())
					{
						setNoClip(true);
						inGround = false;
						getDataManager().set(DATA_IS_RETURNING, true);
						
						setNoGravity(true);
					}
					Vector3d distance = new Vector3d(thrower.getPosX() - getPosX(), thrower.getPosYEye() - getPosY(), thrower.getPosZ() - getPosZ());
					setRawPosition(getPosX(), getPosY() + distance.y * 0.015 * (double)returnLevel, getPosZ());
					if(world.isRemote)
					{
						lastTickPosY = getPosY();
					}
					
					double velocity = 1.0d + 0.25d * (double)returnLevel;
					setMotion(distance.normalize().scale(velocity));
					
					if(!playedReturnSound)
					{
						playSound(ModSounds.THROWING_WEAPON_LOYALTY_RETURN, 10.0F, 1.0F);
						playedReturnSound = true;
					}
				}
				else if(returnLevel > 0 && !thrower.isAlive())
				{
					setNoClip(false);
					getDataManager().set(DATA_IS_RETURNING, false);
					setNoGravity(false);
					getDataManager().set(DATA_RETURN, (byte)0);
				}
			}
		}
		
		super.tick();
		
		if(!inGround || getNoClip())
			++ticksInAir;
		else if(ticksInAir != 0)
			ticksInAir = 0;
	}
	
	@Override
	protected void func_225516_i_() 
	{
		++ticksInGround;
		if (ticksInGround >= 1200) 
		{
			dropAsItem();
			remove();
		}
	}
	
	@Override
	public int getMaxInPortalTime()
	{
		// Set this time to hopefully prevent this entity from being duplicated...
		return 100;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onEntityHit(EntityRayTraceResult rayTrace) 
	{
		Entity entity = rayTrace.getEntity();
		Entity shooter = getShooter();
		
		if(entity != null)
		{
			ItemStack weapon = getWeaponStack();
			float damage = MathHelper.ceil(getDamage());
			DamageSource src;

            if (getIsCritical())
            {
            	damage += rand.nextInt((int)damage / 2 + 2);
            }
            
			// Try and catch the throwing weapon if possible.
			if(shooter != null && (canBeCaughtInMidair(shooter, entity) || isReturning()) && entity instanceof PlayerEntity)
			{
				if(attemptCatch((PlayerEntity)entity))
					return;
			}
			if(shooter == null)
				src = new IndirectEntityDamageSource("mob", this, this).setProjectile();
			else if(shooter instanceof PlayerEntity)
				src = new IndirectEntityDamageSource("player", this, shooter).setProjectile();
			else
				src = new IndirectEntityDamageSource("mob", this, shooter).setProjectile();
			
			if(weapon.getItem() instanceof IWeaponTraitContainer && entity instanceof LivingEntity && shooter instanceof LivingEntity && !entity.isEntityEqual(shooter))
			{
				IWeaponTraitContainer<Item> container = (IWeaponTraitContainer<Item>)weapon.getItem();
            	WeaponMaterial material = container.getMaterial();
            	List<WeaponTrait> traits = container.getAllWeaponTraits();
            	
            	for(WeaponTrait trait : traits)
            	{
            		IMeleeTraitCallback callback = trait.getMeleeCallback();
            		if(callback != null)
            		{
            			damage = callback.modifyDamageDealt(material, damage, src, (LivingEntity)shooter, (LivingEntity)entity);
            			callback.onHitEntity(container.getMaterial(), weapon, (LivingEntity)entity, (LivingEntity)shooter, this);
            		}
            	}
        		for(WeaponTrait prop : material.getAllWeaponTraits())
        		{
        			IMeleeTraitCallback callback = prop.getMeleeCallback();
        			if(callback != null)
        			{
        				damage = callback.modifyDamageDealt(material, damage, src, (LivingEntity)shooter, (LivingEntity)entity);
            			callback.onHitEntity(material, weapon, (LivingEntity)entity, (LivingEntity)shooter, this);
        			}
        		}
			}
			
            if (isBurning() && !(entity instanceof EndermanEntity))
            {
                entity.setFire(5);
            }
            
            if(entity.attackEntityFrom(src, damage))
            {
            	if(weapon.isDamageable() && weapon.attemptDamageItem(1, rand, null))
            	{
            		playSound(SoundEvents.ENTITY_ITEM_BREAK, 0.8f, 0.8f + rand.nextFloat() * 0.4f);
            		world.setEntityState(this, (byte)3);
            		remove();
            	}
            	
            	if (entity instanceof LivingEntity)
                {
            		LivingEntity entitylivingbase = (LivingEntity)entity;

                    if (knockbackStrength > 0)
                    {
                        Vector3d knockVec = getMotion().mul(1.0D, 0.0D, 1.0D).normalize().scale((double)knockbackStrength * 0.6D);

                        if (knockVec.lengthSquared() > 0.0F)
                        {
                            entitylivingbase.addVelocity(knockVec.x, 0.1d, knockVec.z);
                        }
                    }

                    if (!world.isRemote && shooter instanceof LivingEntity)
                    {
                        EnchantmentHelper.applyThornEnchantments(entitylivingbase, shooter);
                        EnchantmentHelper.applyArthropodEnchantments((LivingEntity)shooter, entitylivingbase);
                    }

                    arrowHit(entitylivingbase);

                    if (shooter != null && entitylivingbase != shooter && entitylivingbase instanceof PlayerEntity && shooter instanceof ServerPlayerEntity)
                    {
                        ((ServerPlayerEntity)shooter).connection.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.HIT_PLAYER_ARROW, 0.0F));
                    }
                }

                playSound(getMobHitSound(), 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));

                if (!(entity instanceof EndermanEntity))
                {
                	setMotion(getMotion().scale(-0.1d));
                    rotationYaw += 180.0F;
                    prevRotationYaw += 180.0F;
                }
            }
            else
            {
            	setMotion(getMotion().scale(-0.1d));
                rotationYaw += 180.0F;
                prevRotationYaw += 180.0F;
                ticksInAir = 0;

                if (!world.isRemote && getMotion().lengthSquared() < 1.0e-7d)
                {
                	if(getDataManager().get(DATA_RETURN) > 0 && !getNoClip())
                		setNoClip(true);
                	else if (pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED)
                    {
                		dropAsItem();
                    }
                    remove();
                }
            }
        }
        else
        {
    		super.onEntityHit(rayTrace);
        }
	}
	
	@Override
	protected void func_230299_a_(BlockRayTraceResult result) 
	{
    	if(result.getType() == Type.BLOCK)
    	{
    		if(!world.isRemote)
    		{
    			BlockState state = world.getBlockState(result.getPos());
    			((ServerWorld)world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, state).setPos(result.getPos()), getPosX(), getPosY(), getPosZ(), 5, 0.1d, 0.1d, 0.1d, 0.05d);
    		}
    		
    		ItemStack stack = getWeaponStack();
    		removeEnchantments(stack);
    	}
		super.func_230299_a_(result);
	}
	
	/**
	 * Delete any enchantments from the provided item stack to prevent duping of said enchantments
	 * @param stack The item stack to remove enchantments from
	 */
	protected void removeEnchantments(ItemStack stack)
	{
		if(stack.isEnchanted() && stack.getOrCreateTag().contains(ThrowingWeaponItem.NBT_AMMO_USED))
		{
			// If the enchanted stack contains the Expanse enchantment, then re-calculate the ammo count
			if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.THROWING_AMMO, stack) > 0)
			{
				ThrowingWeaponItem throwingWeapon = (ThrowingWeaponItem)stack.getItem();
				stack.getOrCreateTag().putInt(ThrowingWeaponItem.NBT_AMMO_USED, throwingWeapon.getMaxAmmoBase() - (throwingWeapon.getMaxAmmo(stack) - stack.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED)));
			}
			
    		EnchantmentHelper.setEnchantments(ImmutableMap.of(), stack);

    		// Spawn magic dispersal particles
    		if(!world.isRemote)
    			((ServerWorld)world).spawnParticle(ParticleTypes.WITCH, getPosX(), getPosY(), getPosZ(), 10, 0.1d, 0.1d, 0.1d, 0.2d);
		}
	}
	
	protected void dropAsItem()
	{
		ItemStack stack = getArrowStack();
		removeEnchantments(stack);
        entityDropItem(stack, 0.1F);
	}
	
	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn)
	{
		if(inGround || isReturning())
			attemptCatch(entityIn);
	}
	
	@Override
	protected float getWaterDrag()
	{
		return waterDrag > 0.0f ? waterDrag : super.getWaterDrag();
	}
	
	@Override
	public void setKnockbackStrength(int knockbackStrengthIn)
	{
		knockbackStrength = knockbackStrengthIn;
	}
	
	@Override
	protected SoundEvent getHitEntitySound() 
	{
		return getGroundHitSound();
	}
	
	protected SoundEvent getGroundHitSound()
	{
		return ModSounds.THROWING_KNIFE_HIT_GROUND;
	}
	
	protected SoundEvent getMobHitSound()
	{
		return ModSounds.THROWING_KNIFE_HIT_MOB;
	}

	@Override
	public void writeSpawnData(PacketBuffer buffer)
	{
		buffer.writeItemStack(getWeaponStack(), false);
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) 
	{
		setWeapon(additionalData.readItemStack());
	}
	
	@Override
	public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	// New Methods
	public ItemStack getWeaponStack()
	{
		return getArrowStack();
	}
	
	public boolean isValidThrowingWeapon()
	{
		return !getWeaponStack().isEmpty();
	}
	
	public void setWeapon(ItemStack weaponStack)
	{
		ItemStack stack = weaponStack.copy();
		if(!stack.isEmpty() && stack.getItem() instanceof ThrowingWeaponItem)
		{
			int maxAmmo = ((ThrowingWeaponItem)stack.getItem()).getMaxAmmo(stack);
			stack.getOrCreateTag().putInt(ThrowingWeaponItem.NBT_AMMO_USED, maxAmmo - 1);
			stack.getTag().putBoolean(ThrowingWeaponItem.NBT_ORIGINAL, false);
		}
		getDataManager().set(DATA_WEAPON, stack);
		getDataManager().set(DATA_RETURN, (byte)EnchantmentHelper.getEnchantmentLevel(Enchantments.LOYALTY, stack));
	}
	
	protected boolean attemptCatch(PlayerEntity player)
	{
		if(!world.isRemote && arrowShake <= 0)
		{
			boolean canBePickedUp = pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED || pickupStatus == AbstractArrowEntity.PickupStatus.CREATIVE_ONLY && player.abilities.isCreativeMode;
			
			if(pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED)
			{
				boolean pickUpAsNewItem = true;
				
				ItemStack weapon = getWeaponStack();
				
				for(int i = 0; i < player.inventory.getSizeInventory(); i++)
				{
					ItemStack slotStack = player.inventory.getStackInSlot(i);
					if(slotStack.getItem() == weapon.getItem() && 
							weapon.getOrCreateTag().hasUniqueId(ThrowingWeaponItem.NBT_UUID) && slotStack.getOrCreateTag().hasUniqueId(ThrowingWeaponItem.NBT_UUID) && 
							weapon.getTag().getUniqueId(ThrowingWeaponItem.NBT_UUID).equals(slotStack.getTag().getUniqueId(ThrowingWeaponItem.NBT_UUID)) &&
							weapon.getItem() instanceof ThrowingWeaponItem)
					{
						int maxAmmo = ((ThrowingWeaponItem)slotStack.getItem()).getMaxAmmo(slotStack);
						int currentAmmo = maxAmmo - slotStack.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED);
						
						if(currentAmmo < maxAmmo)
						{
							int itemDamage = slotStack.getDamage() + weapon.getDamage();
							
							// If the total damage exceeds the damage of the equipped stack, then "break" one of the ammo items and not increment the ammo count
	    					if(itemDamage > slotStack.getMaxDamage())
	    					{
	    	            		itemDamage -= slotStack.getMaxDamage() + 1;
	    					}
	    					else
	    					{
	    						currentAmmo++;
	    						slotStack.getTag().putInt(ThrowingWeaponItem.NBT_AMMO_USED, maxAmmo - currentAmmo);
	    					}
	    					slotStack.setDamage(itemDamage);
	    					pickUpAsNewItem = false;
	    					canBePickedUp = true;
	    					break;
						}
					}
				}
				if(pickUpAsNewItem)
				{
					ItemStack pickUpStack = weapon.copy();
					removeEnchantments(pickUpStack);
					canBePickedUp = player.inventory.addItemStackToInventory(pickUpStack);
				}
			}
			
			if(canBePickedUp)
			{
				player.onItemPickup(this, 1);
				remove();
			}
			
			return canBePickedUp;
		}
		return false;
	}
	
	protected boolean canBeCaughtInMidair(Entity shooter, Entity entityHit)
	{
		return shooter == entityHit && noClip;
	}
	
	public int getTicksInAir()
	{
		return ticksInAir;
	}
}
