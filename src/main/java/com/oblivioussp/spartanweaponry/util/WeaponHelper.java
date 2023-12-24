package com.oblivioussp.spartanweaponry.util;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.item.IBlockingWeapon;
import com.oblivioussp.spartanweaponry.item.ItemSwordBase;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.stats.StatList;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

public class WeaponHelper 
{
	
	@SuppressWarnings("unused")
	public static boolean inflictAttackDamage(ItemSwordBase item, ItemStack stack, EntityPlayer player, Entity targetEntity, float reach)
	{
		if (targetEntity != null && player != null && targetEntity.canBeAttackedWithItem())
        {
            if (!targetEntity.hitByEntity(player))
            {
//            	boolean offhand = isOffhandFromStacktrace();
            	
                float damage = /*offhand ? BetterCombatHelper.getOffhandDamage(player) :*/ (float)player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
                // Fix? For weakness putting damage below zero?
                if(damage <= 0.0f)
                	damage = 0.4f;
                float dmgModifier;
                ItemStack weaponStack = /*offhand ? player.getHeldItemOffhand() :*/ player.getHeldItemMainhand();
                // Ensure that the weapon specified is a Spartan Weaponry weapon, because if it is being called when a Spartan Weaponry weapon is equipped on the main hand then it shouldn't pass any special effects to it
                if((/*offhand &&*/ !player.isSneaking() && weaponStack.getItem() instanceof IBlockingWeapon) || !(weaponStack.getItem() instanceof ItemSwordBase))
                {
                	return false;
                }
            	ItemSwordBase weaponItem = /*offhand ? (ItemSwordBase)weaponStack.getItem() :*/ item;
            			
                if (targetEntity instanceof EntityLivingBase)
                {
                    dmgModifier = EnchantmentHelper.getModifierForCreature(weaponStack, ((EntityLivingBase)targetEntity).getCreatureAttribute());
                }
                else
                {
                    dmgModifier = EnchantmentHelper.getModifierForCreature(weaponStack, EnumCreatureAttribute.UNDEFINED);
                }

                int offhandCooldown = 0;
                float cooledAtkStrength;
                /*if(offhand)
                {
                	offhandCooldown = BetterCombatHelper.getOffhandCooldown(player);
                	cooledAtkStrength = BetterCombatHelper.getOffhandCooledAttackStrength(player, offhandCooldown);
                }
                else
                {*/
                	cooledAtkStrength = player.getCooledAttackStrength(0.5F);
//                }
                damage = damage * (0.2F + cooledAtkStrength * cooledAtkStrength * 0.8F);
                dmgModifier *= cooledAtkStrength;
/*                if(offhand)
                {
                	BetterCombatHelper.setOffhandCooldown(player, offhandCooldown);
                }
                else*/
                	player.resetCooldown();

                if (damage > 0.0F || dmgModifier > 0.0F)
                {
                    boolean flag = cooledAtkStrength > 0.9F;
                    boolean flag1 = false;
                    int i = 0;
                    i = i + EnchantmentHelper.getKnockbackModifier(player);

                    if (player.isSprinting() && flag)
                    {
                        player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
                        ++i;
                        flag1 = true;
                    }

                    boolean isCriticalHit = false;
                    // Add Better Combat Rebirth's randomised critical hits if applicable
/*                    if(ModSpartanWeaponry.isRLCombatLoaded)
                    	isCriticalHit = BetterCombatHelper.randomiseCriticalHits(player);
                    else*/
                    	isCriticalHit = flag && player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() &&
                    		!player.isInWater() && !player.isPotionActive(MobEffects.BLINDNESS) && !player.isRiding() && 
                    		targetEntity instanceof EntityLivingBase && !player.isSprinting();

                    CriticalHitEvent hitResult = ForgeHooks.getCriticalHit(player, targetEntity, isCriticalHit, isCriticalHit ? 1.5F : 1.0F);
                    isCriticalHit = hitResult != null;
                    if (isCriticalHit)
                    {
                        damage *= hitResult.getDamageModifier();
                    }

                    damage += dmgModifier;
                    boolean flag3 = false;
                    double d0 = player.distanceWalkedModified - player.prevDistanceWalkedModified;

                    if (flag && !isCriticalHit && !flag1 && /*player.onGround &&*/ d0 < player.getAIMoveSpeed())
                    {
                        ItemStack itemstack = player.getHeldItem(EnumHand.MAIN_HAND);

                        if (!itemstack.isEmpty() && itemstack.getItem() == weaponItem)
                        {
                            flag3 = true;
                        }
                    }

                    float f4 = 0.0F;
                    boolean flag4 = false;
                    int j = EnchantmentHelper.getFireAspectModifier(player);

                    if (targetEntity instanceof EntityLivingBase)
                    {
                        f4 = ((EntityLivingBase)targetEntity).getHealth();

                        if (j > 0 && !targetEntity.isBurning())
                        {
                            flag4 = true;
                            targetEntity.setFire(1);
                        }
                    }

                    double d1 = targetEntity.motionX;
                    double d2 = targetEntity.motionY;
                    double d3 = targetEntity.motionZ;
                    
                    WeaponProperty amrPrcProp = weaponItem.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_ARMOUR_PIERCING);
                    DamageSource dmgSrc = amrPrcProp != null ? new EntityDamageSourceArmorPiercing("player", player, amrPrcProp.getMagnitude() / 100.0f) : DamageSource.causePlayerDamage(player);
                    boolean attacked = /*offhand ? BetterCombatHelper.attackEntityFromOffhand(player, targetEntity, damage) : */
                		targetEntity.attackEntityFrom(dmgSrc, damage);

                    if (attacked)
                    {
                        if (i > 0)
                        {
                            if (targetEntity instanceof EntityLivingBase)
                            {
                                ((EntityLivingBase)targetEntity).knockBack(player, i * 0.5F, MathHelper.sin(player.rotationYaw * 0.017453292F), (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
                            }
                            else
                            {
                                targetEntity.addVelocity(-MathHelper.sin(player.rotationYaw * 0.017453292F) * i * 0.5F, 0.1D, MathHelper.cos(player.rotationYaw * 0.017453292F) * i * 0.5F);
                            }

                            player.motionX *= 0.6D;
                            player.motionZ *= 0.6D;
                            player.setSprinting(false);
                        }
                        
                        WeaponProperty sweepProp = weaponItem.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_SWEEP_DAMAGE);

                        if (flag3 && sweepProp != null)
                        {
                        	float sweepDamage = damage * (sweepProp.getMagnitude() / 100.0f);
                        	
                            for (EntityLivingBase entitylivingbase : player.world.getEntitiesWithinAABB(EntityLivingBase.class, targetEntity.getEntityBoundingBox().grow(1.0D, 0.25D, 1.0D)))
                            {
                                if (entitylivingbase != player && entitylivingbase != targetEntity && !player.isOnSameTeam(entitylivingbase) && player.getDistanceSq(entitylivingbase) < reach * reach)
                                {
                                    entitylivingbase.knockBack(player, 0.4F, MathHelper.sin(player.rotationYaw * 0.017453292F), (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
/*                                    if(offhand)
                                    	BetterCombatHelper.attackEntityFromOffhand(player, targetEntity, sweepDamage);
                                    else*/
                                    	entitylivingbase.attackEntityFrom(DamageSource.causePlayerDamage(player), sweepDamage);
                                }
                            }

                            player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
                            player.spawnSweepParticles();
                        }

                        if (targetEntity instanceof EntityPlayerMP && targetEntity.velocityChanged)
                        {
                            ((EntityPlayerMP)targetEntity).connection.sendPacket(new SPacketEntityVelocity(targetEntity));
                            targetEntity.velocityChanged = false;
                            targetEntity.motionX = d1;
                            targetEntity.motionY = d2;
                            targetEntity.motionZ = d3;
                        }

                        if (isCriticalHit)
                        {
                            player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(), 1.0F, 1.0F);
                            player.onCriticalHit(targetEntity);
                        }

                        // Play Better Combat Rebirth's attack/critical hit sounds if applicable
/*                        if(ModSpartanWeaponry.isRLCombatLoaded)
                        	BetterCombatHelper.playAttackSounds(player, isCriticalHit);*/

                        if (!isCriticalHit && !flag3)
                        {
                            if (flag)
                            {
                                player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, player.getSoundCategory(), 1.0F, 1.0F);
                            }
                            else
                            {
                                player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, player.getSoundCategory(), 1.0F, 1.0F);
                            }
                        }

                        if (dmgModifier > 0.0F)
                        {
                            player.onEnchantmentCritical(targetEntity);
                        }

                        if (!player.world.isRemote && targetEntity instanceof EntityPlayer)
                        {
                            EntityPlayer entityplayer = (EntityPlayer)targetEntity;
                            ItemStack itemstack2 = player.getHeldItemMainhand();
                            ItemStack itemstack3 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                            // Check for shield breaching property
                            if (!itemstack2.isEmpty() && !itemstack3.isEmpty() && (itemstack2.getItem() instanceof ItemAxe || (itemstack2.getItem() == weaponItem && weaponItem.canDisableShield(itemstack2, itemstack3, entityplayer, player)/* && weaponItem.hasWeaponProperty(WeaponProperties.SHIELD_BREACH)*/)) && itemstack3.getItem() instanceof ItemShield)
                            {
                                float f3 = 0.25F + EnchantmentHelper.getEfficiencyModifier(player) * 0.05F;

                                if (flag1)
                                {
                                    f3 += 0.75F;
                                }

                                if (player.world.rand.nextFloat() < f3)
                                {
                                    entityplayer.getCooldownTracker().setCooldown(itemstack3.getItem(), 100);
                                    player.world.setEntityState(entityplayer, (byte)30);
                                }
                            }
                        }

                        player.setLastAttackedEntity(targetEntity);

                        if (targetEntity instanceof EntityLivingBase)
                        {
                            EnchantmentHelper.applyThornEnchantments((EntityLivingBase)targetEntity, player);
                        }

                        EnchantmentHelper.applyArthropodEnchantments(player, targetEntity);
                        ItemStack itemstack1 = /*offhand ? player.getHeldItemOffhand() :*/ player.getHeldItemMainhand();
                        Entity entity = targetEntity;

                        if (targetEntity instanceof MultiPartEntityPart)
                        {
                            IEntityMultiPart ientitymultipart = ((MultiPartEntityPart)targetEntity).parent;

                            if (ientitymultipart instanceof EntityLivingBase)
                            {
                                entity = (EntityLivingBase)ientitymultipart;
                            }
                        }

                        if (!itemstack1.isEmpty() && entity instanceof EntityLivingBase)
                        {
                            itemstack1.hitEntity((EntityLivingBase)entity, player);

                            if (itemstack1.getCount() <= 0)
                            {
                            	EnumHand hand = /*offhand ? EnumHand.OFF_HAND :*/ EnumHand.MAIN_HAND;
                                player.setHeldItem(hand, ItemStack.EMPTY);
                                ForgeEventFactory.onPlayerDestroyItem(player, itemstack1, hand);
                            }
                        }

                        if (targetEntity instanceof EntityLivingBase)
                        {
                            float f5 = f4 - ((EntityLivingBase)targetEntity).getHealth();
                            player.addStat(StatList.DAMAGE_DEALT, Math.round(f5 * 10.0F));

                            if (j > 0)
                            {
                                targetEntity.setFire(j * 4);
                            }

                            if (player.world instanceof WorldServer && f5 > 2.0F)
                            {
                                int k = (int)(f5 * 0.5D);
                                ((WorldServer)player.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, targetEntity.posX, targetEntity.posY + targetEntity.height * 0.5F, targetEntity.posZ, k, 0.1D, 0.0D, 0.1D, 0.2D, new int[0]);
                            }
                        }

                        player.addExhaustion(0.3F);
                    }
                    else
                    {
                        player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(), 1.0F, 1.0F);

                        if (flag4)
                        {
                            targetEntity.extinguish();
                        }
                    }
                    
                    if(weaponItem.hasWeaponProperty(WeaponProperties.QUICK_STRIKE))
                    {
                    	targetEntity.hurtResistantTime = ConfigHandler.quickStrikeHurtResistTicks;
                    }
                }
            }
        }
		return true;
	}
	
	/**
	 * Inflicts Armour Piercing damage to a target (Target can be attacked again after this)
	 * @param attacker The Entity that attacked
	 * @param weapon The Item that is used to attack with
	 * @param victim The target of the attack
	 * @param dmgAmount The total damage amount to deal
	 * @return Returns the damage amount that will be dealt as normal damage
	 */
/*	public static float dealArmourPiercingDamage(EntityLivingBase attacker, float piercingFactor, EntityLivingBase victim, float dmgAmount)
	{
		float piercingDamage = dmgAmount * piercingFactor;
		
		// Damage the victim with armour-piercing damage and deduct that from the total weapon damage.
		if(attacker instanceof EntityPlayer)
		{
			dealExtraDamageIngoringArmour(victim, DamageSourcesSW.causeArmourPiercingPlayerDamage((EntityPlayer)attacker), piercingDamage);
			Log.debug("Dealing " + piercingDamage + " armour-piercing damage as a player!");
		}
		else
		{
			dealExtraDamageIngoringArmour(victim, DamageSourcesSW.causeArmourPiercingMobDamage(attacker), piercingDamage);
			Log.debug("Dealing " + piercingDamage + " armour-piercing damage as a mob!");
		}
		Log.debug("Dealing " + (dmgAmount - piercingDamage) + " normal damage!");
		return dmgAmount - piercingDamage;
	}*/

	/*public static float dealArmourPiercingProjectileDamage(EntityBolt projectile, EntityLivingBase victim, float dmgAmount)
	{
		float piercingFactor = projectile.getArmourPiercingFactor();
		float piercingDamage = dmgAmount * piercingFactor;
		Entity attacker = projectile.shootingEntity;
		
		// Damage the victim with armour-piercing damage and deduct that from the total weapon damage.
		if(attacker instanceof EntityPlayer)
		{
			dealExtraDamageIngoringArmour(victim, DamageSource.causeArrowDamage(projectile, attacker), piercingDamage);
			LogHelper.debug("Dealing " + piercingDamage + " armour-piercing damage as a player!");
		}
		else
		{
			dealExtraDamageIngoringArmour(victim, DamageSource.causeArrowDamage(projectile, projectile), piercingDamage);
			LogHelper.debug("Dealing " + piercingDamage + " armour-piercing damage as a mob!");
		}
		//ev.setAmount(ev.getAmount() - piercingDamage);
		LogHelper.debug("Dealing " + (dmgAmount - piercingDamage) + " normal damage!");
		return dmgAmount - piercingDamage;
	}*/
	
/*	public static void dealExtraDamageIngoringArmour(EntityLivingBase entity, DamageSource dmgSrc, float dmgAmount)
	{
		// Copy of EntityLivingBase.attackEntityFrom()
		if (!entity.isEntityInvulnerable(dmgSrc))
        {
			// Nice try Minecraft Forge! I won't allow this hook to fire as it will cause a 
			// recursive loop probably resulting in a StackOverflowException
            //dmgAmount = net.minecraftforge.common.ForgeHooks.onLivingHurt(entity, dmgSrc, dmgAmount);
            if (dmgAmount <= 0) return;
            //dmgAmount = entity.applyArmorCalculations(dmgSrc, dmgAmount);
            dmgAmount = applyPotionDamageCalculations(entity, dmgSrc, dmgAmount);
            float f = dmgAmount;
            dmgAmount = Math.max(dmgAmount - entity.getAbsorptionAmount(), 0.0F);
            entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (f - dmgAmount));

            if (dmgAmount != 0.0F)
            {
                float f1 = entity.getHealth();
                entity.setHealth(f1 - dmgAmount);
                entity.getCombatTracker().trackDamage(dmgSrc, f1, dmgAmount);
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() - dmgAmount);
            }
        }
	}*/
	
	/**
     * Reduces damage, depending on potions -- Copy of EntityLiving.applyPotionDamageCalculations()
     */
/*    public static float applyPotionDamageCalculations(EntityLivingBase entity, DamageSource source, float damage)
    {
        if (source.isDamageAbsolute())
        {
            return damage;
        }
        
        if (entity.isPotionActive(MobEffects.RESISTANCE) && source != DamageSource.OUT_OF_WORLD)
        {
            int i = (entity.getActivePotionEffect(MobEffects.RESISTANCE).getAmplifier() + 1) * 5;
            int j = 25 - i;
            float f = damage * j;
            damage = f / 25.0F;
        }

        if (damage <= 0.0F)
        {
            return 0.0F;
        }
        
        int k = EnchantmentHelper.getEnchantmentModifierDamage(entity.getArmorInventoryList(), source);

        if (k > 0)
        {
            damage = CombatRules.getDamageAfterMagicAbsorb(damage, k);
        }

        return damage;
    }*/
    
    /**
     * Method to multiply extra weapon damage to targets.
     * @param weapon The Item that will be dealing the damage
     * @param victim The target of the attack
     * @param dmgAmount The amount of damage before the multiplier
     * @return The damage that will be dealt.
     * Note that this method does *NOT* make the damage happen
     */
    /*public static float addExtraDamage(EntityLivingBase attacker, IExtraDamageWeapon weapon, EntityLivingBase victim, float dmgAmount)
    {
		EntityEquipmentSlot[] slots = weapon.getEquipmentRequiredForMultiplier();
		boolean doExtraDamage = true;
		// Do extra damage if riding an entity.
		if(weapon.isRidingRequiredForMultiplier() && !attacker.isRiding())
			doExtraDamage = false;
		else if(slots != null)
		{
			// Do extra damage if target's chosen equipment slots are absent.
			for(EntityEquipmentSlot slot : slots)
			{
				if(!CompatHelper.isStackEmpty(victim.getItemStackFromSlot(slot)))
				{
					doExtraDamage = false;
					break;
				}
			}
		}
		else if(weapon.getAttributeRequired() != victim.getCreatureAttribute())
			doExtraDamage = false;
		
		// Deal the damage!
		if(doExtraDamage)
		{
			LogHelper.debug(String.format("Dealing x%f extra damage! Total damage: %f", weapon.getDamageMultiplier(), dmgAmount * weapon.getDamageMultiplier()));
			return dmgAmount * weapon.getDamageMultiplier();
		}
		return dmgAmount;
    }*/
    
    /**
     * Gets the damage multiplier for the weapon with the Weapon Property prop
     * @param attacker The entity attacking
     * @param prop The Weapon Property on the weapon
     * @param victim The entity being attacked
     * @return The damage multiplier
     */
/*    public static float getDamageMultiplier(EntityLivingBase attacker, WeaponProperty prop, EntityLivingBase victim)
    {
    	boolean doExtraDamage = true;
    	if(prop.getType() == WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_RIDING && !attacker.isRiding())
    		doExtraDamage = false;
    	else if(prop.getType() == WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_UNDEAD && !(victim.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD))
    		doExtraDamage = false;
    	else if(prop.getType() == WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_CHEST && !victim.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty())
    		doExtraDamage = false;
    	else if(prop.getType() == WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_UNARMOURED && !victim.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty() &&
    			!victim.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty() && !victim.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty() &&
    			!victim.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty());
    		doExtraDamage = false;
    	if(doExtraDamage)
    		return prop.getMagnitude() / 100.0f;
    	return 1.0f;
    }*/
    
	/**
	 * Thanks to Charles445 for this code<br>
	 * Authors Note: I don't like the fact that we have to check the stacktrace to determine whether or not the attack is an offhand one or not.
	 * I intend on replacing this as soon as I able to with a better way.
	 * @return
	 */
    public static boolean stacktraceHasClass(String clazz)
    {
    	for(StackTraceElement element : Thread.currentThread().getStackTrace())
		{
			if(element.getClassName().equals(clazz))
				return true;
		}
		return false;
    }
    
    /**
	 * Authors Note: I don't like the fact that we have to check the stacktrace to determine whether or not the attack is an offhand one or not.
	 * I intend on replacing this as soon as I able to with a better way.
     * @return
     */
	public static boolean isOffhandFromStacktrace()
	{
		return stacktraceHasClass("bettercombat.mod.network.PacketOffhandAttack$Handler");
	}
}
