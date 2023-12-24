package com.oblivioussp.spartanweaponry.capability;

import java.util.Optional;

import com.oblivioussp.spartanweaponry.api.oil.OilEffect;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.INBTSerializable;

public interface IOilHandler extends INBTSerializable<CompoundTag>
{
	/**
	 * Attempts to retrieve the active oil effect
	 * @return An optional-wrapped version of the effect; an empty optional otherwise
	 */
	public Optional<OilEffect> getEffect();
	
	/**
	 * Attempts to retrieve the active oil potion
	 * @return An optional-wrapped version of the potion; an empty optional otherwise
	 */
	public Optional<Potion> getPotion();
	
	/**
	 * Applies the effect of the oil to the weapon. Also sets the uses to the maximum for the oil
	 * @param effectIn The effect to apply
	 * @param oilStackIn
	 */
	public void setEffect(OilEffect effectIn, ItemStack oilStackIn);
	
	/**
	 * Applies the effect of the potion as an oil to the weapon. Also sets the uses to the maximum for the oil
	 * @param potionIn The potion effect to apply
	 * @param oilStackIn
	 */
	public void setPotion(Potion potionIn, ItemStack oilStackIn);
	
	/**
	 * Removes any effect from the weapon. Also sets the uses to zero
	 */
	public void clearEffect();
	
	/**
	 * Applies the effect to the entity the player attacked. Also reduces uses by one
	 * @param baseDamageIn The prior damage of the weapon
	 * @param levelIn The game world/level
	 * @param targetIn The target entity
	 * @param userIn The entity that is attacking with the oiled weapon
	 * @param userWeaponIn The weapon's item stack
	 * @return The resulting damage with the bonus/penalty applied from the oil
	 */
	public float useEffect(float baseDamageIn, Level levelIn, LivingEntity targetIn, LivingEntity userIn, ItemStack userWeaponIn);
	
	/**
	 * Queries whether or not the weapon is oiled with a valid effect
	 * @return
	 */
	public boolean isOiled();
	
	/**
	 * Retrieves the amount of uses left for the oil on this weapon
	 * @return
	 */
	public int getUsesLeft();
}
