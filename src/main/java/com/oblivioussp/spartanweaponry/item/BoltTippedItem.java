package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class BoltTippedItem extends BoltItem
{
	protected String baseName;
	
	public BoltTippedItem(String unlocName, String baseName, float damageModifier, float rangeModifier, float armorPiercingFactor) 
	{
		super(unlocName, damageModifier, rangeModifier, armorPiercingFactor);
		this.baseName = baseName;
	}
	
	@Override
	public BoltEntity createBolt(World world, ItemStack stack, LivingEntity shooter) 
	{
		BoltEntity bolt = new BoltEntity(shooter, world);
    	ItemStack boltStack = stack.copy();
    	boltStack.setCount(1);
    	bolt.initEntity(damageModifier, rangeModifier, armorPiercingFactor, boltStack);
		bolt.setPotionEffect(stack);
    	if(bolt.isValid())
    		return bolt;
    	
    	return null;
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items)
	{
		if(this.isInGroup(group))
		{
			for(Potion potion : ForgeRegistries.POTION_TYPES)
			{
				if(!potion.getEffects().isEmpty())
				{
					ItemStack stack = new ItemStack(this);
					PotionUtils.addPotionToItemStack(stack, potion);
					items.add(stack);
				}
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		//if(PotionUtils.getPotionFromItem(stack) == Potions.EMPTY)
		//	tooltip.add(new TranslationTextComponent("tooltip." + Reference.MOD_ID + ".tipped_arrow.crafting").applyTextStyle(TextFormatting.YELLOW));
		
		super.addInformation(stack, worldIn, tooltip, flagIn);

		tooltip.add(new StringTextComponent(""));
		PotionUtils.addPotionTooltip(stack, tooltip, 0.125f);
	}

	public ITextComponent getDisplayName(ItemStack stack)
	{
		Potion potion = PotionUtils.getPotionFromItem(stack);
		
		String transKey = potion.getNamePrefixed("item.spartanweaponry.proj_tipped.effect.");
		return new TranslationTextComponent(transKey, I18n.format("item." + ModSpartanWeaponry.ID + "." + baseName));
	}
}
