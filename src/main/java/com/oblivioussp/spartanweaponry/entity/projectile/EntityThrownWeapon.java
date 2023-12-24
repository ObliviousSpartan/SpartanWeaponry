package com.oblivioussp.spartanweaponry.entity.projectile;

import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.IPropertyCallback;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.init.EnchantmentRegistrySW;
import com.oblivioussp.spartanweaponry.init.SoundRegistry;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;
import com.oblivioussp.spartanweaponry.util.DamageSourcesSW;
import com.oblivioussp.spartanweaponry.util.Log;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public class EntityThrownWeapon extends EntityArrow implements IThrowableEntity, IEntityAdditionalSpawnData
{
	public static final String NBT_WEAPON = "weapon";
	public static final String NBT_LIFE = "life";
	protected static final DataParameter<ItemStack> DATA_WEAPON = EntityDataManager.createKey(EntityThrownWeapon.class, DataSerializers.ITEM_STACK);
	protected static final DataParameter<Byte> DATA_RETURN = EntityDataManager.createKey(EntityThrownWeapon.class, DataSerializers.BYTE);
	protected boolean isReturning = false;
	protected boolean playedReturnSound = false;
	
	public EntityThrownWeapon(World world) 
	{
		super(world);
	}
	
	public EntityThrownWeapon(World world, double x, double y, double z)
    {
		super(world, x, y, z);
    }
	
	public EntityThrownWeapon(World world, EntityLivingBase shooter)
    {
		super(world, shooter);
    }
	
	@Override
	protected void entityInit() 
	{
		super.entityInit();
		getDataManager().register(DATA_WEAPON, ItemStack.EMPTY);
		getDataManager().register(DATA_RETURN, (byte)0);
	}
	
	public void setWeapon(ItemStack weaponStack)
	{
		ItemStack stack = weaponStack.copy();
		if(stack.hasTagCompound() && stack.getItem() instanceof ItemThrowingWeapon)
		{
			int maxAmmo = ((ItemThrowingWeapon)stack.getItem()).getMaxAmmoBase();
			stack.getTagCompound().setInteger(ItemThrowingWeapon.NBT_AMMO_USED, maxAmmo - 1);
			stack.getTagCompound().setBoolean(ItemThrowingWeapon.NBT_ORIGINAL, false);
		}
		getDataManager().set(DATA_WEAPON, stack);
		getDataManager().setDirty(DATA_WEAPON);
		getDataManager().set(DATA_RETURN, (byte)EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.THROWING_RETURN, stack));
		getDataManager().setDirty(DATA_RETURN);
	}

	@Override
	protected ItemStack getArrowStack() 
	{
		return getDataManager().get(DATA_WEAPON);
	}
	
	/**
	 * Returns the Thrown Item Stack for rendering.
	 * @return
	 */
	public ItemStack getWeaponStack()
	{
		return getArrowStack();
	}
	
	public boolean isReturning()
	{
		return isReturning;
	}
	
	public boolean isValidThrowingWeapon()
	{
		return !getWeaponStack().isEmpty();
	}
	
	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
    	super.writeEntityToNBT(compound);
    	
        compound.setShort(NBT_LIFE, (short)ticksInGround);
    }
	
	/**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
	@Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);

    	ticksInGround = compound.getShort(NBT_LIFE);
    }
	
	/**
     * Called when the arrow hits a block or an entity
     */
	@Override
    protected void onHit(RayTraceResult raytraceResultIn)
    {
        Entity entity = raytraceResultIn.entityHit;

        if (entity != null)
        {
        	ItemStack weapon = getWeaponStack();
        	float i = (float)getDamage();
        	float damage = i;
        	
        	// Damage Dealt modifier...
            
            if(noClip)
            	return;

            DamageSource damagesource;

            if (shootingEntity == null)
            	damagesource = DamageSourcesSW.causeThrownWeaponMobDamage(this, this);
            else if(shootingEntity instanceof EntityPlayer)
            	damagesource = DamageSourcesSW.causeThrownWeaponPlayerDamage(this, (EntityPlayer) shootingEntity);
            else
            	damagesource = DamageSourcesSW.causeThrownWeaponMobDamage(this, shootingEntity);
            
            if(weapon.getItem() instanceof IWeaponPropertyContainer && entity instanceof EntityLivingBase && shootingEntity instanceof EntityLivingBase && !entity.isEntityEqual(shootingEntity))
            {
            	IWeaponPropertyContainer<Item> container = (IWeaponPropertyContainer)weapon.getItem();
            	float damageDirect = container.getDirectAttackDamage();
            	ToolMaterialEx materialEx = container.getMaterialEx();
            	List<WeaponProperty> properties = container.getAllWeaponProperties();
            	
            	for(WeaponProperty prop : properties)
            	{
            		IPropertyCallback callback = prop.getCallback();
            		if(callback != null)
            		{
            			damage = callback.modifyDamageDealt(materialEx, damage, damageDirect + 1, damagesource, (EntityLivingBase)shootingEntity, (EntityLivingBase)entity);
            			callback.onHitEntity(container.getMaterialEx(), weapon, (EntityLivingBase)entity, (EntityLivingBase)shootingEntity, this);
            		}
            	}
        		for(WeaponProperty prop : materialEx.getAllWeaponProperties())
        		{
        			IPropertyCallback callback = prop.getCallback();
        			if(callback != null)
        			{
        				damage = callback.modifyDamageDealt(materialEx, damage, damageDirect + 1, damagesource, (EntityLivingBase)shootingEntity, (EntityLivingBase)entity);
            			callback.onHitEntity(materialEx, weapon, (EntityLivingBase)entity, (EntityLivingBase)shootingEntity, this);
        			}
        		}
            }

            if (isBurning() && !(entity instanceof EntityEnderman))
            {
                entity.setFire(5);
            }

            if (entity.attackEntityFrom(damagesource, damage))
            {
            	if(!world.isRemote) 
				{
					// Emit particles when damage has been enhanced or mitigated, depending on what has happened
					if(damage > i)
						((WorldServer)world).spawnParticle(EnumParticleTypes.CRIT_MAGIC, entity.posX, entity.posY + (entity.height / 2.0f), entity.posZ, 16, 0.2d, 0.2d, 0.2d, 0.0d);
					else if(damage < i)
						((WorldServer)world).spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entity.posX, entity.posY + (entity.height / 2.0f), entity.posZ, 16, 0.2d, 0.2d, 0.2d, 0.0d);
				}
            	
            	// Changed to attemptDamageItem to take into account the Unbreaking enchantment
            	if(weapon.getMaxStackSize() == 1 && weapon.attemptDamageItem(1, rand, null))
            	{
//            		playSound(SoundEvents.ENTITY_ITEM_BREAK, 0.8F, 0.8F + world.rand.nextFloat() * 0.4F);

                	// Copy of EntityLivingBase.renderBrokenItemStack(), with appropriate modifications
            		world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_ITEM_BREAK, getSoundCategory(), 0.8F, 0.8F + world.rand.nextFloat() * 0.4F); //Forge: Fix MC-2518 Items are not damaged on the client so client needs packet as well.

                    for (int j = 0; j < 5; ++j)
                    {
                        Vec3d vec3d = new Vec3d((rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
                        vec3d = vec3d.rotatePitch(-rotationPitch * 0.017453292F);
                        vec3d = vec3d.rotateYaw(-rotationYaw * 0.017453292F);
                        double d0 = -rand.nextFloat() * 0.6D - 0.3D;
                        Vec3d vec3d1 = new Vec3d((rand.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
                        vec3d1 = vec3d1.rotatePitch(-rotationPitch * 0.017453292F);
                        vec3d1 = vec3d1.rotateYaw(-rotationYaw * 0.017453292F);
                        vec3d1 = vec3d1.add(posX, posY + getEyeHeight(), posZ);
                        if (world instanceof WorldServer) //Forge: Fix MC-2518 spawnParticle is nooped on server, need to use server specific variant
                            ((WorldServer)world).spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, 0,  vec3d.x, vec3d.y + 0.05D, vec3d.z, 0.0D, Item.getIdFromItem(weapon.getItem()), weapon.getMetadata());
                        else //Fix the fact that spawning ItemCrack uses TWO arguments.
                            world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, Item.getIdFromItem(weapon.getItem()), weapon.getMetadata());
                        
                    }
            		setDead();
            	}
            	
                if (entity instanceof EntityLivingBase)
                {
                    EntityLivingBase entitylivingbase = (EntityLivingBase)entity;

                    if (knockbackStrength > 0)
                    {
                        float f1 = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);

                        if (f1 > 0.0F)
                        {
                            entitylivingbase.addVelocity(motionX * knockbackStrength * 0.6000000238418579D / f1, 0.1D, motionZ * knockbackStrength * 0.6000000238418579D / f1);
                        }
                    }

                    if (shootingEntity instanceof EntityLivingBase)
                    {
                        EnchantmentHelper.applyThornEnchantments(entitylivingbase, shootingEntity);
                        EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)shootingEntity, entitylivingbase);
                    }

                    arrowHit(entitylivingbase);

                    if (shootingEntity != null && entitylivingbase != shootingEntity && entitylivingbase instanceof EntityPlayer && shootingEntity instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                    }
                }

                playSound(SoundRegistry.THROWING_WEAPON_HIT, 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));

                if (!(entity instanceof EntityEnderman))
                {
                	motionX *= -0.1D;
                	motionY *= -0.1D;
                	motionZ *= -0.1D;
                    rotationYaw += 180.0F;
                    prevRotationYaw += 180.0F;
                }
            }
            else
            {
                motionX *= -0.10000000149011612D;
                motionY *= -0.10000000149011612D;
                motionZ *= -0.10000000149011612D;
                rotationYaw += 180.0F;
                prevRotationYaw += 180.0F;
                ticksInAir = 0;

                if (!world.isRemote && motionX * motionX + motionY * motionY + motionZ * motionZ < 0.0010000000474974513D)
                {
                	if(getDataManager().get(DATA_RETURN) > 0 && !noClip)
                		noClip = true;
                	
                    if (pickupStatus == EntityArrow.PickupStatus.ALLOWED)
                    	dropAsItem();

                    setDead();
                }
            }
        }
        else if(!noClip)
        {
        	if(raytraceResultIn.typeOfHit == Type.BLOCK)
        	{
        		// Spawn impact particles
        		if(!world.isRemote)
	    			((WorldServer)world).spawnParticle(EnumParticleTypes.BLOCK_DUST, posX, posY, posZ, 5, 0.1d, 0.1d, 0.1d, 0.05d, Block.getIdFromBlock(world.getBlockState(raytraceResultIn.getBlockPos()).getBlock()));

        		ItemStack stack = getArrowStack();
        		removeEnchantments(stack);
        	}
        	
        	super.onHit(raytraceResultIn);
        }
    }
	
	/**
	 * Delete any enchantments from the provided item stack to prevent duping of said enchantments
	 * @param stack The item stack to remove enchantments from
	 */
	protected void removeEnchantments(ItemStack stack)
	{
		if(stack.isItemEnchanted() && stack.hasTagCompound() && stack.getTagCompound().hasKey(ItemThrowingWeapon.NBT_AMMO_USED))
		{
    		EnchantmentHelper.setEnchantments(ImmutableMap.of(), stack);

    		// Spawn magic dispersal particles
    		if(!world.isRemote)
    			((WorldServer)world).spawnParticle(EnumParticleTypes.SPELL_WITCH, posX, posY, posZ, 10, 0.1d, 0.1d, 0.1d, 0.2d);
		}
	}
	
	protected void dropAsItem()
	{
		ItemStack stack = getArrowStack();
		removeEnchantments(stack);
        entityDropItem(stack, 0.1F);
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn)
	{
		if(inGround)
			attemptCatch(entityIn);		// Pick up the throwing weapon when it's in the ground
	}

	/**
	 * Attempt for the player to catch this entity in midair 
	 * @param player The player trying to catch it
	 */
	protected boolean attemptCatch(EntityPlayer player)
	{
		if (!world.isRemote && arrowShake <= 0)
        {
            boolean flag = pickupStatus == EntityArrow.PickupStatus.ALLOWED || pickupStatus == EntityArrow.PickupStatus.CREATIVE_ONLY && player.capabilities.isCreativeMode;

            if (pickupStatus == EntityArrow.PickupStatus.ALLOWED)
        	{
            	boolean pickUpAsNewItem = true;
            	
            	ItemStack weapon = getWeaponStack();
            	
            	// Find any stack that might fit this item.
            	for(int i = 0; i < player.inventory.getSizeInventory(); i++)
            	{
            		ItemStack slotStack = player.inventory.getStackInSlot(i);
            		if(ItemStack.areItemsEqualIgnoreDurability(slotStack, weapon) && weapon.hasTagCompound() && slotStack.hasTagCompound() && 
            				weapon.getTagCompound().getUniqueId(ItemThrowingWeapon.NBT_UUID).equals(slotStack.getTagCompound().getUniqueId(ItemThrowingWeapon.NBT_UUID)) && 
            				weapon.getItem() instanceof ItemThrowingWeapon)
        			{
        				int maxAmmo = ((ItemThrowingWeapon)slotStack.getItem()).getMaxAmmo(slotStack);
        				int currentAmmo = maxAmmo - slotStack.getTagCompound().getInteger(ItemThrowingWeapon.NBT_AMMO_USED);
        				if(currentAmmo < maxAmmo)
        				{
        					int itemDamage = slotStack.getItemDamage() + weapon.getItemDamage();
        					
        					// If the total damage exceeds the damage of the equipped stack, then "break" one of the ammo items and not increment the ammo count
        					if(itemDamage > slotStack.getMaxDamage())
        					{
//        	            		playSound(SoundEvents.ENTITY_ITEM_BREAK, 0.8F, 0.8F + world.rand.nextFloat() * 0.4F);
        						player.renderBrokenItemStack(weapon);
        	            		itemDamage -= slotStack.getMaxDamage() + 1;
        					}
        					else
        					{
        						currentAmmo++;
        						slotStack.getTagCompound().setInteger(ItemThrowingWeapon.NBT_AMMO_USED, maxAmmo - currentAmmo);
        					}
        					slotStack.setItemDamage(itemDamage);
        					pickUpAsNewItem = false;
        					flag = true;
        					break;
        				}
            		}
            	}
            	if(pickUpAsNewItem)
            	{
            		ItemStack pickUpStack = getArrowStack().copy();
            		// Delete any enchantments to prevent duping of them.
            		removeEnchantments(pickUpStack);
                	flag = player.inventory.addItemStackToInventory(pickUpStack);
            	}
        	}

            if (flag)
            {
            	player.onItemPickup(this, 1);
                setDead();
            }
            
            return flag;
        }
		return false;
	}

	/**
	 * Can this entity be caught by the shooter in midair?
	 * @param shooter The Entity who shot this projectile
	 * @param entityHit The Entity who was hit by the projectile
	 * @return
	 */
	protected boolean canBeCaughtInMidair(Entity shooter, Entity entityHit) 
	{
		return shooter == entityHit && noClip;
	}

	public int getTicksInAir()
	{
		return ticksInAir;
	}

	/**
     * Called to update the entity's position/logic.
     */
	@Override
    public void onUpdate()
    {
		if(inGround)
			++ticksInGround;
		
		if(timeInGround > 4 || isReturning && shootingEntity != null)
		{
			int returnLevel = getDataManager().get(DATA_RETURN);
			if(returnLevel > 0 && shootingEntity.isEntityAlive() && (!(shootingEntity instanceof EntityPlayerMP) || (shootingEntity instanceof EntityPlayer && !((EntityPlayer)shootingEntity).isSpectator())))
			{
				if(!isReturning)
				{
					noClip = true;
					inGround = false;
					isReturning = true;
					setNoGravity(true);
				}
				
				Vec3d distance = shootingEntity.getPositionEyes(1.0f).subtract(getPositionVector());
				posY = posY + distance.y * 0.015d * returnLevel;
				if(world.isRemote)
					prevPosY = posY;
				
				double velocity = 1.0d + 0.25d * (double)(returnLevel - 1);
				Vec3d motionVec = distance.normalize().scale(velocity);
				motionX = motionVec.x;
				motionY = motionVec.y;
				motionZ = motionVec.z;
				
				if(!playedReturnSound)
				{
					world.playSound((EntityPlayer)null, posX, posY, posZ, SoundRegistry.THROWING_WEAPON_RETURN, SoundCategory.NEUTRAL, 10.0f, 0.1f);
					playedReturnSound = true;
				}
			}
			else if(returnLevel > 0 && !shootingEntity.isEntityAlive())
			{
				noClip = false;
				isReturning = false;
				setNoGravity(false);
				getDataManager().set(DATA_RETURN, (byte)0);
			}
		}
		
		if(ticksInGround >= 1200)
		{
			// Drop the weapon as an item
			if (pickupStatus == EntityArrow.PickupStatus.ALLOWED)
            	dropAsItem();
			
			setDead();
		}
		
		if(!inGround)
			++ticksInAir;
		else if(ticksInAir != 0)
			ticksInAir = 0;
		
		// Copy of Entity.onUpdate()
		if(!world.isRemote)
		{
			setFlag(6, isGlowing());
		}
		onEntityUpdate();
		
		// Modified copy of EntityArrow.onUpdate() [Modified to allow for the Return enchantment to work correctly]
		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
            rotationYaw = (float)(MathHelper.atan2(motionX, motionZ) * (180D / Math.PI));
            rotationPitch = (float)(MathHelper.atan2(motionY, (double)f) * (180D / Math.PI));
            prevRotationYaw = rotationYaw;
            prevRotationPitch = rotationPitch;
        }

        BlockPos blockpos = new BlockPos(xTile, yTile, zTile);
        IBlockState iblockstate = world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();

        if (iblockstate.getMaterial() != Material.AIR && !noClip)
        {
            AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(world, blockpos);

            if (axisalignedbb != Block.NULL_AABB && axisalignedbb.offset(blockpos).contains(new Vec3d(posX, posY, posZ)))
            {
                inGround = true;
            }
        }

        if (arrowShake > 0)
        {
            --arrowShake;
        }

        if (inGround && !noClip)
        {
            int j = block.getMetaFromState(iblockstate);

            if ((block != inTile || j != inData) && !world.collidesWithAnyBlock(getEntityBoundingBox().grow(0.05D)))
            {
                inGround = false;
                motionX *= (double)(rand.nextFloat() * 0.2F);
                motionY *= (double)(rand.nextFloat() * 0.2F);
                motionZ *= (double)(rand.nextFloat() * 0.2F);
                ticksInGround = 0;
                ticksInAir = 0;
            }
            else
            {
                ++ticksInGround;

                if (ticksInGround >= 1200 )
                {
        			if (pickupStatus == EntityArrow.PickupStatus.ALLOWED)
        				dropAsItem();
                    setDead();
                }
            }

            ++timeInGround;
        }
        else
        {
            timeInGround = 0;
            ++ticksInAir;
            Vec3d vec3d1 = new Vec3d(posX, posY, posZ);
            Vec3d vec3d = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
            RayTraceResult raytraceresult = world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
            vec3d1 = new Vec3d(posX, posY, posZ);
            vec3d = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
            
            boolean caught = false;

            if (raytraceresult != null)
            {
                vec3d = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
            }

            Entity entity = findEntityOnPath(vec3d1, vec3d);

            if (entity != null)
            {
                raytraceresult = new RayTraceResult(entity);
            }

            if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)raytraceresult.entityHit;

                if (shootingEntity instanceof EntityPlayer)
                {
                	// Catch the throwing weapon only when the weapon is returning to the player (via the Return enchantment)
                    if(canBeCaughtInMidair(shootingEntity, entityplayer))
                    	caught = attemptCatch(entityplayer);
                	if(!((EntityPlayer)shootingEntity).canAttackPlayer(entityplayer))
                		raytraceresult = null;
                }
            }

            if (raytraceresult != null && raytraceresult.typeOfHit != Type.MISS && !caught && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
            {
                onHit(raytraceresult);
            }

            if (getIsCritical())
            {
                for (int k = 0; k < 4; ++k)
                {
                    world.spawnParticle(EnumParticleTypes.CRIT, posX + motionX * (double)k / 4.0D, posY + motionY * (double)k / 4.0D, posZ + motionZ * (double)k / 4.0D, -motionX, -motionY + 0.2D, -motionZ);
                }
            }

            posX += motionX;
            posY += motionY;
            posZ += motionZ;
            float f4 = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
            if(noClip)
            	rotationYaw = (float)(MathHelper.atan2(-motionX, -motionZ) * (180.0d / Math.PI));
            else
            	rotationYaw = (float)(MathHelper.atan2(motionX, motionZ) * (180D / Math.PI));

            for (rotationPitch = (float)(MathHelper.atan2(motionY, (double)f4) * (180D / Math.PI)); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F)
            {
                ;
            }

            while (rotationPitch - prevRotationPitch >= 180.0F)
            {
                prevRotationPitch += 360.0F;
            }

            while (rotationYaw - prevRotationYaw < -180.0F)
            {
                prevRotationYaw -= 360.0F;
            }

            while (rotationYaw - prevRotationYaw >= 180.0F)
            {
                prevRotationYaw += 360.0F;
            }

            rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
            rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
            float f1 = 0.99F;
            float f2 = 0.05F;

            if (isInWater())
            {
                for (int i = 0; i < 4; ++i)
                {
                    float f3 = 0.25F;
                    world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * 0.25D, posY - motionY * 0.25D, posZ - motionZ * 0.25D, motionX, motionY, motionZ);
                }

                ItemStack weaponStack = getWeaponStack();
                f1 = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.THROWING_HYDRODYNAMIC, weaponStack) > 0 ? 0.99f : 0.6f;
            }

            if (isWet())
            {
                extinguish();
            }

            if(!noClip)
            {
            motionX *= (double)f1;
            motionY *= (double)f1;
            motionZ *= (double)f1;

	            if (!hasNoGravity())
	            {
	                motionY -= 0.05000000074505806D;
	            }
            }

            setPosition(posX, posY, posZ);
            doBlockCollisions();
        }
    }
	
	/**
     * Sets the amount of knockback the projectile applies when it hits a mob.
     */
	@Override
    public void setKnockbackStrength(int knockback)
    {
        knockbackStrength = knockback;
    }

	@Override
	public Entity getThrower() 
	{
		return shootingEntity;
	}

	@Override
	public void setThrower(Entity entity) 
	{
		shootingEntity = entity;
	}
	
	/**
	 * Drop the item entity when the projectile entity is killed via the /kill command
	 */
	@Override
	public void onKillCommand() 
	{
		if(pickupStatus == EntityArrow.PickupStatus.ALLOWED)
			dropAsItem();
		super.onKillCommand();
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) 
	{
	}
}
