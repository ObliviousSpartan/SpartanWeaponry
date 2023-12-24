package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.entity.projectile.BoomerangEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.init.ModEnchantments;
import com.oblivioussp.spartanweaponry.init.ModSounds;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.WeaponArchetype;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class BoomerangItem extends ThrowingWeaponItem
{

	public BoomerangItem(Item.Properties prop, WeaponMaterial materialIn, WeaponArchetype archetypeIn)
	{
		super(prop.durability(materialIn.getUses()), materialIn, archetypeIn, Defaults.DamageBaseBoomerang, Defaults.DamageMultiplierBoomerang, Defaults.MeleeSpeedBoomerang, 1, Defaults.ChargeTicksBoomerang);
	}
	
	public BoomerangItem(Item.Properties prop, WeaponMaterial material, WeaponArchetype archetypeIn, String customDisplayName)
	{
		this(prop.durability(material.getUses()), material, archetypeIn);
		if(material.useCustomDisplayName())
			this.customDisplayName = customDisplayName;
	}

	@Override
	public ThrowingWeaponEntity createThrowingWeaponEntity(Level levelIn, Player player, ItemStack stack, int charge)
	{
		BoomerangEntity boomerang = new BoomerangEntity(levelIn, player);
		boomerang.setDistanceToReturn((charge / 5.0d) * (BoomerangEntity.DISTANCE_TO_RETURN - 3.0d) + 3.0d + 
				EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.PROPEL.get(), stack) * 3.0f);
		return boomerang;
	}
	
	@Override
	protected SoundEvent getThrowingSound() 
	{
		return ModSounds.BOOMERANG_THROW.get();
	}
}
