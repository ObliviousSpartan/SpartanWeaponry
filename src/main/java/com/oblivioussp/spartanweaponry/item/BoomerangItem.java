package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.entity.projectile.BoomerangEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.init.ModEnchantments;
import com.oblivioussp.spartanweaponry.init.ModSounds;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class BoomerangItem extends ThrowingWeaponItem
{

	public BoomerangItem(String regName, Item.Properties prop, WeaponMaterial material, boolean usingDeferredRegister)
	{
		super(regName, prop.maxDamage(material.getMaxUses()), material, Defaults.DamageBaseBoomerang, Defaults.DamageMultiplierBoomerang, Defaults.MeleeSpeedBoomerang, 1, Defaults.ChargeTicksBoomerang, usingDeferredRegister);
	}
	
	public BoomerangItem(String regName, Item.Properties prop, WeaponMaterial material, String customDisplayNameIn, boolean usingDeferredRegister)
	{
		this(regName, prop.maxDamage(material.getMaxUses()), material, usingDeferredRegister);
		if(material.useCustomDisplayName())
			customDisplayName = customDisplayNameIn;
	}

	@Override
	public ThrowingWeaponEntity createThrowingWeaponEntity(World worldIn, PlayerEntity player, ItemStack stack, int charge)
	{
		BoomerangEntity boomerang = new BoomerangEntity(worldIn, player);
		boomerang.setDistanceToReturn((charge / 5.0d) * (BoomerangEntity.DISTANCE_TO_RETURN - 3.0d) + 3.0d + 
				EnchantmentHelper.getEnchantmentLevel(ModEnchantments.THROWING_RANGE, stack) * 3.0f);
		return boomerang;
	}
	
	@Override
	protected SoundEvent getThrowingSound() 
	{
		return ModSounds.BOOMERANG_THROW;
	}
}
