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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWeaponOil extends ItemSW
{

	public ItemWeaponOil(String unlocName) 
	{
		super(unlocName);
		/*this.setCreativeTab(CreativeTabsSW.TAB_SW);
		this.setRegistryName(unlocName);
		this.setUnlocalizedName(unlocName);*/
		this.setMaxStackSize(1);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if(this.isInCreativeTab(tab))
		{
	        for (PotionType potiontype : PotionType.REGISTRY)
	        {
	        	if (!potiontype.getEffects().isEmpty() && potiontype.getEffects().get(0).getPotion().isBadEffect())
                {
                    items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), potiontype));
                }
	        }
		}
    }
	
	/*@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
		return stack;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
    {
        return 0;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.NONE;
    }
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

	@Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
		return false;
    }*/

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.RED + StringHelper.translateString("wip", "dev"));
		tooltip.add("");
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
    	String name = I18n.translateToLocalFormatted(String.format("item.%s:proj_tipped.%s", ModSpartanWeaponry.ID, PotionUtils.getPotionFromItem(stack).getNamePrefixed("")), StringHelper.translateString("weapon_oil.name", "item"));
    	return name;
    }
}
