package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBoltTipped extends ItemBolt
{

	public ItemBoltTipped(String unlocName, float baseDamage, float rangeMultiplier, float armPrcFactor)
	{
		super(unlocName, baseDamage, rangeMultiplier, armPrcFactor);
	}

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if(this.isInCreativeTab(tab))
		{
	        for (PotionType potiontype : PotionType.REGISTRY)
	        {
	        	if (!potiontype.getEffects().isEmpty())
                {
                    items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), potiontype));
                }
	        }
		}
		//super.getSubItems(tab, items);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        
        PotionUtils.addPotionTooltip(stack, tooltip, 0.125F);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
    	String name = I18n.translateToLocalFormatted(String.format("item.%s:proj_tipped.%s", ModSpartanWeaponry.ID, PotionUtils.getPotionFromItem(stack).getNamePrefixed("")), StringHelper.translateString("bolt.name", "item"));
    	return name;
    }

}
