package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBolt;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoltTipped;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBolt extends ItemSW 
{
	protected float baseDamage;
	protected float rangeMultiplier;
	protected float armorPiercingFactor;

	public ItemBolt(String unlocName, float baseDmg, float rngMultiplier, float armPrcFactor) 
	{
		super(unlocName);
		baseDamage = baseDmg;
		rangeMultiplier = rngMultiplier;
		armorPiercingFactor = armPrcFactor;
		this.setCreativeTab(CreativeTabsSW.TAB_SW_ARROWS_BOLTS);
	}

    public EntityBolt createBolt(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        EntityBoltTipped bolt = new EntityBoltTipped(worldIn, shooter);
        bolt.setDamage(baseDamage);
        bolt.setPotionEffect(stack);
        return bolt;
    }

    @SuppressWarnings("unused")
	public boolean isInfinite(ItemStack stack, ItemStack crossbow, EntityPlayer player)
    {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, crossbow);
        return enchant <= 0 ? false : this.getClass() == ItemBolt.class;
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
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("projectile.range_multiplier", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY + "" + StringHelper.translateFormattedString("projectile.range_multiplier.value", "tooltip", ModSpartanWeaponry.ID, rangeMultiplier)));
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("projectile.armor_piercing_factor", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY + "" + StringHelper.translateFormattedString("projectile.armor_piercing_factor.value", "tooltip", ModSpartanWeaponry.ID, armorPiercingFactor * 100.0f)));
    	    	
    	tooltip.add("");
    	
    	super.addInformation(stack, worldIn, tooltip, flagIn);
    }
    
    public float getRangeMultiplier()
    {
    	return rangeMultiplier;
    }
    
    public float getArmorPiercingFactor()
    {
    	return armorPiercingFactor;
    }
}
