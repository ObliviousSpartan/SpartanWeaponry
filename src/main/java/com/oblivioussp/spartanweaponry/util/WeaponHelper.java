package com.oblivioussp.spartanweaponry.util;

import com.oblivioussp.spartanweaponry.api.APIAttributes;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.item.SwordBaseItem;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

public class WeaponHelper
{
    public static void inflictAttackDamage(SwordBaseItem item, PlayerEntity player, Entity targetEntity)
	{
    	if(player != null && targetEntity != null && targetEntity.canBeAttackedWithItem()) 
    	{
            if(!targetEntity.hitByEntity(player)) 
            {
                float baseDamage = (float)player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                float damageModifier;
                if (targetEntity instanceof LivingEntity)
                    damageModifier = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), ((LivingEntity)targetEntity).getCreatureAttribute());
                else
                    damageModifier = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), CreatureAttribute.UNDEFINED);

                float cooledAttackStrength = player.getCooledAttackStrength(0.5F);
                baseDamage = baseDamage * (0.2F + cooledAttackStrength * cooledAttackStrength * 0.8F);
                damageModifier = damageModifier * cooledAttackStrength;
               
                player.resetCooldown();
                if(baseDamage > 0.0F || damageModifier > 0.0F) 
                {
                    boolean minCooldown = cooledAttackStrength > 0.9F;
                    boolean extraKnockback = false;
                    float knockbackValue = (float)player.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
                    knockbackValue = knockbackValue + EnchantmentHelper.getKnockbackModifier(player);
                    if(player.isSprinting() && minCooldown) 
                    {
                	     player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
                        ++knockbackValue;
                        extraKnockback = true;
                    }

                    boolean isCriticalDamage = minCooldown && player.fallDistance > 0.0F && !player.isOnGround() && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(Effects.BLINDNESS) && !player.isPassenger() && targetEntity instanceof LivingEntity;
                    isCriticalDamage = isCriticalDamage && !player.isSprinting();
                    CriticalHitEvent hitResult = ForgeHooks.getCriticalHit(player, targetEntity, isCriticalDamage, isCriticalDamage ? 1.5F : 1.0F);
                    isCriticalDamage = hitResult != null;
                    if (isCriticalDamage)
                        baseDamage *= hitResult.getDamageModifier();

                    baseDamage = baseDamage + damageModifier;
                    boolean doSweep = false;
                    float sweepFactor = 0.0f;
                    double deltaDistanceWalked = (double)(player.distanceWalkedModified - player.prevDistanceWalkedModified);
                    if (minCooldown && !isCriticalDamage && !extraKnockback && player.isOnGround() && deltaDistanceWalked < (double)player.getAIMoveSpeed()) 
                    {
                        ItemStack itemstack = player.getHeldItem(Hand.MAIN_HAND);
                        WeaponTrait sweepTrait = item.getFirstWeaponTraitWithType(WeaponTraits.TRAIT_TYPE_SWEEP_DAMAGE);
                        if(!itemstack.isEmpty() && itemstack.getItem() == item && sweepTrait != null)
                        {
                        	doSweep = true;
                        	sweepFactor = sweepTrait.getMagnitude() / 100.0f;
                        }
                    }

                    float targetHealth = 0.0F;
                    boolean isIgniting = false;
                    int fireAspectLevel = EnchantmentHelper.getFireAspectModifier(player);
                    if (targetEntity instanceof LivingEntity) 
                    {
                        targetHealth = ((LivingEntity)targetEntity).getHealth();
                        if(fireAspectLevel > 0 && !targetEntity.isBurning()) 
                        {
                            isIgniting = true;
                            targetEntity.setFire(1);
                        }
                    }

                    Vector3d targetMotion = targetEntity.getMotion();
                    //boolean isAttacked = targetEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), baseDamage);
                    
                    // Apply armor-piercing damage when using a weapon with the armor-piercing trait
                    WeaponTrait armorPiercingTrait = item.getFirstWeaponTraitWithType(WeaponTraits.TRAIT_TYPE_ARMOUR_PIERCING);
                    DamageSource src = armorPiercingTrait != null ? new ArmorPiercingEntityDamageSource("player", player, armorPiercingTrait.getMagnitude() / 100.0f) : DamageSource.causePlayerDamage(player);
                    boolean isAttacked = targetEntity.attackEntityFrom(src, baseDamage);
                    if (isAttacked) 
                    {
                        if(knockbackValue > 0)
                        {
                            if(targetEntity instanceof LivingEntity)
                                ((LivingEntity)targetEntity).applyKnockback((float)knockbackValue * 0.5F, (double)MathHelper.sin(player.rotationYaw * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(player.rotationYaw * ((float)Math.PI / 180F))));
                            else
                                targetEntity.addVelocity((double)(-MathHelper.sin(player.rotationYaw * ((float)Math.PI / 180F)) * (float)knockbackValue * 0.5F), 0.1D, (double)(MathHelper.cos(player.rotationYaw * ((float)Math.PI / 180F)) * (float)knockbackValue * 0.5F));

                            player.setMotion(player.getMotion().mul(0.6D, 1.0D, 0.6D));
                            player.setSprinting(false);
                        }

                        if(doSweep && sweepFactor != 0.0f) 
                        {
                        	// Do sweep damage dependent of the sweeping weapon trait
                            float sweepDamage = Math.max(sweepFactor * baseDamage, 1.0f);
                            double sweepReach = player.getAttributeValue(APIAttributes.ATTACK_REACH) - 2.0d;

                            for(LivingEntity livingentity : player.world.getEntitiesWithinAABB(LivingEntity.class, targetEntity.getBoundingBox().grow(5.0D, 0.25D, 5.0D)))
                            {
                                if(livingentity != player && livingentity != targetEntity && !player.isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity)livingentity).hasMarker()) && player.getDistanceSq(livingentity) < sweepReach * sweepReach) 
                                {
                                    livingentity.applyKnockback(0.4F, (double)MathHelper.sin(player.rotationYaw * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(player.rotationYaw * ((float)Math.PI / 180F))));
                                    livingentity.attackEntityFrom(DamageSource.causePlayerDamage(player), sweepDamage);
                                }
                            }

                            player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
                            player.spawnSweepParticles();
                        }
                        
                	    // Reset hurt resistance time if needed
                	    if(item.hasWeaponTrait(WeaponTraits.QUICK_STRIKE))
                      	    targetEntity.hurtResistantTime = Defaults.QuickStrikeHurtResistTicks;

                        if(targetEntity instanceof ServerPlayerEntity && targetEntity.velocityChanged)
                        {
                            ((ServerPlayerEntity)targetEntity).connection.sendPacket(new SEntityVelocityPacket(targetEntity));
                            targetEntity.velocityChanged = false;
                            targetEntity.setMotion(targetMotion);
                        }

                        if(isCriticalDamage)
                        {
                            player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(), 1.0F, 1.0F);
                            player.onCriticalHit(targetEntity);
                        }

                        if(!isCriticalDamage && !doSweep) 
                        {
                            if(minCooldown) 
                                player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, player.getSoundCategory(), 1.0F, 1.0F);
                            else
                                player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, player.getSoundCategory(), 1.0F, 1.0F);
                        }

                        if(damageModifier > 0.0F)
                            player.onEnchantmentCritical(targetEntity);

                        player.setLastAttackedEntity(targetEntity);
                        if(targetEntity instanceof LivingEntity)
                            EnchantmentHelper.applyThornEnchantments((LivingEntity)targetEntity, player);

                        EnchantmentHelper.applyArthropodEnchantments(player, targetEntity);
                        ItemStack itemstack1 = player.getHeldItemMainhand();
                        Entity entity = targetEntity;
                        if(targetEntity instanceof PartEntity)
                            entity = ((PartEntity<?>) targetEntity).getParent();

                        if(!player.world.isRemote && !itemstack1.isEmpty() && entity instanceof LivingEntity) 
                        {
                            ItemStack copy = itemstack1.copy();
                            itemstack1.hitEntity((LivingEntity)entity, player);
                            if(itemstack1.isEmpty()) 
                            {
                                ForgeEventFactory.onPlayerDestroyItem(player, copy, Hand.MAIN_HAND);
                                player.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
                            }
                        }

                        if(targetEntity instanceof LivingEntity) 
                        {
                            float damageDealt = targetHealth - ((LivingEntity)targetEntity).getHealth();
                            player.addStat(Stats.DAMAGE_DEALT, Math.round(damageDealt * 10.0F));
                            if(fireAspectLevel > 0)
                                targetEntity.setFire(fireAspectLevel * 4);

                            if(player.world instanceof ServerWorld && damageDealt > 2.0F) 
                            {
                                int particleCount = (int)((double)damageDealt * 0.5D);
                                ((ServerWorld)player.world).spawnParticle(ParticleTypes.DAMAGE_INDICATOR, targetEntity.getPosX(), targetEntity.getPosYHeight(0.5D), targetEntity.getPosZ(), particleCount, 0.1D, 0.0D, 0.1D, 0.2D);
                            }
                        }

                        player.addExhaustion(0.1F);
                    }
                    else 
                    {
                        player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(), 1.0F, 1.0F);
                        if(isIgniting)
                        targetEntity.extinguish();
                    }
                }
            }
        }
	}
}
