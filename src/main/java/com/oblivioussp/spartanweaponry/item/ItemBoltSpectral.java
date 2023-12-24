package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBolt;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoltSpectral;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBoltSpectral extends ItemBolt 
{

	public ItemBoltSpectral(String unlocName, float baseDamage, float rangeMultiplier, float armPrcFactor) 
	{
		super(unlocName, baseDamage, rangeMultiplier, armPrcFactor);
	}

    public EntityBolt createBolt(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
    	EntityBoltSpectral bolt = new EntityBoltSpectral(worldIn, shooter);
    	bolt.setDamage(baseDamage);
        return bolt;
    }
    
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.BLUE + StringHelper.translateString("projectile.impact.glowing", "tooltip", ModSpartanWeaponry.ID));
	}
}
