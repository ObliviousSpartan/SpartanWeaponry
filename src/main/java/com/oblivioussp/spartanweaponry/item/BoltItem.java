package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class BoltItem extends Item
{
	protected float damageModifier = Defaults.BaseDamageBolt;
	protected float rangeModifier = Defaults.RangeMultiplierBolt;
	protected float armorPiercingFactor = Defaults.ArmorPiercingFactorBolt;

	public BoltItem(String unlocName, float damageModifier, float rangeModifier, float armorPiercingFactor) 
	{
		super(new Item.Properties().group(ModItems.GROUP_SW_ARROWS_BOLTS));
		this.setRegistryName(ModSpartanWeaponry.ID, unlocName);
		this.damageModifier = damageModifier;
		this.rangeModifier = rangeModifier;
		this.armorPiercingFactor = armorPiercingFactor;
	}

    public BoltEntity createBolt(World world, ItemStack stack, LivingEntity shooter)
    {
    	BoltEntity bolt = new BoltEntity(shooter, world);
    	ItemStack boltStack = stack.copy();
    	boltStack.setCount(1);
    	bolt.initEntity(damageModifier, rangeModifier, armorPiercingFactor, boltStack);
    	if(bolt.isValid())
    		return bolt;
    	
    	return null;
    }

	public boolean isInfinite(ItemStack stack, ItemStack crossbow, PlayerEntity player)
    {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, crossbow);
        return enchant <= 0 ? false : this.getClass() == BoltItem.class;
    }
	
	/**
     * allows items to add custom lines of information to the mouseover description
     */
/*    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	//tooltip.add(TextFormatting.RED + StringHelper.translateString("wip", "dev"));
    	tooltip.add(StringHelper.translateFormattedString("projectile_base_damage", "tooltip", baseDamage));
    	tooltip.add(StringHelper.translateFormattedString("projectile_range_multiplier", "tooltip", rangeMultiplier));
    	    	
    	tooltip.add("");
    	
    	super.addInformation(stack, worldIn, tooltip, flagIn);
    }*/
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
//		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers").mergeStyle(TextFormatting.GOLD));
		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.base_damage", new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.base_damage.value", damageModifier).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.range", new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.range.value", rangeModifier).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.armor_piercing_factor", new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.armor_piercing_factor.value", armorPiercingFactor * 100.0f).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
	}
	
	public void updateFromConfig(float damageModifier, float rangeModifier, float armorPiercingFactor)
	{
		this.damageModifier = damageModifier;
		this.rangeModifier = rangeModifier;
		this.armorPiercingFactor = armorPiercingFactor;
	} 
}
