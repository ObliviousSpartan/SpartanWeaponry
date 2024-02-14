package com.oblivioussp.spartanweaponry.api.oil;

import java.util.List;

import com.oblivioussp.spartanweaponry.capability.OilHandler;
import com.oblivioussp.spartanweaponry.util.Config;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class PotionOilEffect extends OilEffect 
{
	public PotionOilEffect()
	{
		super("potion", OilEffectType.EFFECT_ONLY, 0x0, Defaults.OIL_USES_NORMAL, 0.0f, OilEffect.USE_NOTHING, true);
	}
	
	@Override
	public int getColor(ItemStack stackIn) 
	{
		Potion potion = Potions.EMPTY;
		CompoundTag oilTag = stackIn.getOrCreateTag().getCompound(OilHandler.NBT_OIL);
		if(!oilTag.isEmpty())
		{
			potion = PotionUtils.getPotion(oilTag);
			int color = PotionUtils.getColor(potion);
			return color;
		}
		return super.getColor(stackIn);
	}

	@Override
	public float onUse(float baseDamageIn, Level levelIn, LivingEntity targetEntityIn, LivingEntity userEntityIn, ItemStack oilStackIn) 
	{
		Potion potion = Potions.EMPTY;
		CompoundTag oilTag = oilStackIn.getOrCreateTag().getCompound(OilHandler.NBT_OIL);
		if(!oilTag.isEmpty())
		{
			potion = PotionUtils.getPotion(oilTag);
			potion.getEffects().forEach((effect) -> {
				targetEntityIn.addEffect(new MobEffectInstance(effect.getEffect(), Mth.floor(effect.getDuration() * Config.INSTANCE.potionOilDurationModifier.get()), effect.getAmplifier()), userEntityIn);
			});
		}
		return super.onUse(baseDamageIn, levelIn, targetEntityIn, userEntityIn, oilStackIn);
	}
	
	@Override
	public void getTooltip(ItemStack stackIn, List<Component> tooltipListIn)
	{
		OilHelper.addPotionTooltip(stackIn, tooltipListIn, Config.INSTANCE.potionOilDurationModifier.get().floatValue());
	}
}
