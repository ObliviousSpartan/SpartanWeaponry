package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class BoltItem extends Item
{
	protected float damageModifier = Defaults.BaseDamageBolt;
	protected float rangeModifier = Defaults.RangeMultiplierBolt;
	protected float armorPiercingFactor = Defaults.ArmorPiercingFactorBolt;

	public BoltItem(float damageModifier, float rangeModifier, float armorPiercingFactor) 
	{
		super(new Item.Properties().tab(ModItems.TAB_SW_ARROWS_BOLTS));
		this.damageModifier = damageModifier;
		this.rangeModifier = rangeModifier;
		this.armorPiercingFactor = armorPiercingFactor;
	}

    public BoltEntity createBolt(Level level, ItemStack stack, LivingEntity shooter)
    {
    	BoltEntity bolt = new BoltEntity(shooter, level);
    	ItemStack boltStack = stack.copy();
    	boltStack.setCount(1);
    	bolt.initEntity(damageModifier, rangeModifier, armorPiercingFactor, boltStack);
    	if(bolt.isValid())
    		return bolt;
    	
    	return null;
    }

	public boolean isInfinite(ItemStack stack, ItemStack crossbow, Player player)
    {
        int enchant = crossbow.getEnchantmentLevel(Enchantments.INFINITY_ARROWS);
        return enchant <= 0 ? false : this.getClass() == BoltItem.class;
    }
    
	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flagIn) 
	{
//		tooltip.add(Component.translatable("tooltip." + ModSpartanWeaponry.ID + ".modifiers").withStyle(ChatFormatting.GOLD));
		tooltip.add(Component.translatable("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.base_damage", Component.translatable("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.base_damage.value", damageModifier).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_AQUA));
		tooltip.add(Component.translatable("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.range", Component.translatable("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.range.value", rangeModifier).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_AQUA));
		tooltip.add(Component.translatable("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.armor_piercing_factor", Component.translatable("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.armor_piercing_factor.value", armorPiercingFactor * 100.0f).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_AQUA));
	}
	
	public void updateFromConfig(float damageModifier, float rangeModifier, float armorPiercingFactor)
	{
		this.damageModifier = damageModifier;
		this.rangeModifier = rangeModifier;
		this.armorPiercingFactor = armorPiercingFactor;
	} 
}
