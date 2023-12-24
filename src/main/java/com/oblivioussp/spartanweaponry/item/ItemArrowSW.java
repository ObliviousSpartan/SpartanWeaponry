package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArrowSW extends ItemArrow 
{
	protected float baseDamage;
	protected float rangeMultiplier;
	
	public ItemArrowSW(String unlocName, float baseDmg, float rngMultiplier)
	{
		super();
		this.setCreativeTab(CreativeTabsSW.TAB_SW_ARROWS_BOLTS);
		this.setRegistryName(unlocName);
		this.setTranslationKey(unlocName);
		baseDamage = baseDmg;
		rangeMultiplier = rngMultiplier;
	}
	
	@Override
	public String getTranslationKey()
	{
		return StringHelper.getItemUnlocalizedName(super.getTranslationKey());
	}
	
	@Override
	public String getTranslationKey(ItemStack itemStack)
	{
		return StringHelper.getItemUnlocalizedName(super.getTranslationKey());
	}
	
	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        EntityTippedArrow entitytippedarrow = new EntityTippedArrow(worldIn, shooter);
        entitytippedarrow.setPotionEffect(stack);
        return entitytippedarrow;
    }
	
	/**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	//tooltip.add(TextFormatting.RED + StringHelper.translateString("wip", "dev"));
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("projectile.base_damage", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY + "" + baseDamage));
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("projectile.range_multiplier", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY + StringHelper.translateFormattedString("modifiers.longbow.speed_multiplier.value", "tooltip", ModSpartanWeaponry.ID, rangeMultiplier)));
    	
    	tooltip.add("");
    	
    	super.addInformation(stack, worldIn, tooltip, flagIn);
    }
    
    public float getRangeMultiplier()
    {
    	return rangeMultiplier;
    }
}
