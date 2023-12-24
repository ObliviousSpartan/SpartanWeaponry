package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMultiSW extends ItemSW 
{
	protected String[] unlocNames;
	
	public ItemMultiSW(String registryName, String... unlocalizedNames) 
	{
		super(registryName);
		this.unlocNames = unlocalizedNames;
		this.hasSubtypes = true;
	}
	
	public String[] getAllUnlocalizedNames()
	{
		return unlocNames;
	}

	/**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
    	if(tab == this.getCreativeTab())
    	{
	    	if(unlocNames == null)
	    	{
	    		super.getSubItems(tab, subItems);
	    		return;
	    	}
	    	for(int i = 0; i < unlocNames.length; i++)
	    	{
	    		subItems.add(new ItemStack(this, 1, i));
	    	}
    	}
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
    	if(stack.getItemDamage() >= unlocNames.length)
    		return super.getItemStackDisplayName(stack);
    	
    	String unlocName = unlocNames[stack.getItemDamage()];
        return StringHelper.translateString(unlocName + ".name", "item");
    }
}
