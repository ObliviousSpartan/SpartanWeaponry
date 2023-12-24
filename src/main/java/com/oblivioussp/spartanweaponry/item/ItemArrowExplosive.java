package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityArrowExplosive;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArrowExplosive extends ItemArrowSW 
{

	public ItemArrowExplosive(String unlocName)
	{
		super(unlocName, 0.1f, ConfigHandler.rangeMultiplierArrowExplosive);
	}
	
	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
		EntityArrowExplosive entity = new EntityArrowExplosive(worldIn, shooter);
		//entity.setPotionEffect(stack);
        return entity;
    }
	
	/**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	//tooltip.add(TextFormatting.RED + StringHelper.translateString("wip", "dev"));
    	super.addInformation(stack, worldIn, tooltip, flagIn);
    	tooltip.add(TextFormatting.BLUE + StringHelper.translateString("projectile.impact.explosion", "tooltip", ModSpartanWeaponry.ID));
    }
}
