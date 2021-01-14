package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class DamageAbsorbWeaponTrait extends MeleeCallbackWeaponTrait 
{

	public DamageAbsorbWeaponTrait(String type, String modId, float magnitude) 
	{
		super(type, modId, magnitude, TraitQuality.POSITIVE);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<ITextComponent> tooltip)
	{
		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.trait.%s.desc", modId, type), magnitude * 100.0f).mergeStyle(WeaponTrait.DESCRIPTION_COLOUR));
		
	}

	@Override
	public float modifyDamageTaken(WeaponMaterial material, float baseDamage, DamageSource source, LivingEntity attacker,
			LivingEntity victim)
	{
		ItemStack heldItemVictim = victim.getHeldItemMainhand();
		if(!heldItemVictim.isEmpty())
		{
			heldItemVictim.damageItem(MathHelper.floor(baseDamage * magnitude), victim, (victimEntity) -> {
				victimEntity.sendBreakAnimation(Hand.MAIN_HAND);
				if(victimEntity instanceof PlayerEntity)
					net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem((PlayerEntity)victimEntity, heldItemVictim, Hand.MAIN_HAND);
			});
			return baseDamage * (1.0f - magnitude);
		}
			
		return baseDamage;
	}
}
