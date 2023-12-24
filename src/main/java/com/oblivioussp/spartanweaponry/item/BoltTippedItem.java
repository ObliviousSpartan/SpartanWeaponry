package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class BoltTippedItem extends BoltItem
{
	protected String baseName;
	
	public BoltTippedItem(String baseName, float damageModifier, float rangeModifier, float armorPiercingFactor) 
	{
		super(damageModifier, rangeModifier, armorPiercingFactor);
		this.baseName = baseName;
	}
	
	@Override
	public BoltEntity createBolt(Level level, ItemStack stack, LivingEntity shooter) 
	{
		BoltEntity bolt = new BoltEntity(shooter, level);
    	ItemStack boltStack = stack.copy();
    	boltStack.setCount(1);
    	bolt.initEntity(damageModifier, rangeModifier, armorPiercingFactor, boltStack);
		bolt.setPotionEffect(stack);
    	if(bolt.isValid())
    		return bolt;
    	
    	return null;
	}

	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items)
	{
		if(allowdedIn(tab))
		{
			for(Potion potion : ForgeRegistries.POTIONS)
			{
				if(!potion.getEffects().isEmpty())
				{
					ItemStack stack = new ItemStack(this);
					PotionUtils.setPotion(stack, potion);
					items.add(stack);
				}
			}
		}
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level levelIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		super.appendHoverText(stack, levelIn, tooltip, flagIn);

		tooltip.add(new TextComponent(""));
		PotionUtils.addPotionTooltip(stack, tooltip, 0.125f);
	}

	public Component getName(ItemStack stack)
	{
		Potion potion = PotionUtils.getPotion(stack);
		
		String potionKey = potion.getName("item.spartanweaponry.proj_tipped.effect.");
		return new TranslatableComponent(potionKey, new TranslatableComponent("item." + ModSpartanWeaponry.ID + "." + baseName));
	}
}
