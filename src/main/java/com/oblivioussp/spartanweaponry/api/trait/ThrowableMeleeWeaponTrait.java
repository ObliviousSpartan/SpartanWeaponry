package com.oblivioussp.spartanweaponry.api.trait;

import java.util.Optional;

import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;

import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class ThrowableMeleeWeaponTrait extends WeaponTrait implements IActionTraitCallback
{

	public ThrowableMeleeWeaponTrait(String typeIn, String modIdIn, TraitQuality qualityIn) 
	{
		super(typeIn, modIdIn, qualityIn);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(ItemStack usingStackIn, Level levelIn, Player playerIn, InteractionHand handIn)
	{
		playerIn.startUsingItem(handIn);
		return InteractionResultHolder.consume(usingStackIn);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) 
	{
		return UseAnim.SPEAR;
	}
	
	@Override
	public Optional<IActionTraitCallback> getActionCallback()
	{
		return Optional.of(this);
	}
	
	@Override
	public void releaseUsing(ItemStack stackIn, Level levelIn, LivingEntity entityLivingIn, int timeLeftIn, float attackDamage) 
	{
		if(entityLivingIn instanceof Player)
		{
			Player player = (Player)entityLivingIn;

            int charge = this.getUseDuration(stackIn) - timeLeftIn;
            
            if(charge >= 5)
            	charge = 5;
			
			if (!levelIn.isClientSide && charge > 2)
	        {
	            ThrowingWeaponEntity thrown = new ThrowingWeaponEntity(ModEntities.THROWING_WEAPON.get(), player, levelIn);
	            thrown.setWeapon(stackIn);
	            thrown.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.5f * (charge / 10.0f + 0.5f), 0.5f);
	            thrown.setBaseDamage(attackDamage + 1.0f);
	            
	            // Apply enchantments as necessary
	            int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stackIn);
	            if (j > 0)
	            {
	            	thrown.setBaseDamage(thrown.getBaseDamage() + j * 0.5d + 0.5d);
	            }
	            int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, stackIn);
	            if (k > 0)
	            {
	            	thrown.setKnockback(k);
	            }
	            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, stackIn) > 0)
	            {
	            	thrown.setSecondsOnFire(100);
	            }
	            
	            if(player.getAbilities().instabuild)
	            	thrown.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
	            else if(thrown.isValidThrowingWeapon())
	            {
	            	stackIn.shrink(1);
	                if(stackIn.getCount() <= 0)
	                	player.getInventory().removeItem(stackIn);
	            }
	            
	            if(thrown.isValidThrowingWeapon())
	            {
	            	levelIn.playSound((Player)null, player.getX(), player.getY(), player.getZ(), ModSounds.THROWN_WEAPON_THROW.get(), SoundSource.PLAYERS, 0.5f, 0.4f / (levelIn.random.nextFloat() * 0.4f + 0.8f));
	            	levelIn.addFreshEntity(thrown);
	            }
	        }

	        player.awardStat(Stats.ITEM_USED.get(stackIn.getItem()));
		}
	}

}
