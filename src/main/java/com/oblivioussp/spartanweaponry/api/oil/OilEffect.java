package com.oblivioussp.spartanweaponry.api.oil;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.tags.ModEntityTypeTags;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * Oil Effects allow compatible weapons to get a damage boost depending on the target entity type (either {@link MobType} or {@link TagKey} with the {@link EntityType} subtype)
 * @author ObliviousSpartan
 * @see {@link MobOilEffect}
 */
public class OilEffect extends ForgeRegistryEntry<OilEffect>
{
	public static final IUsePredicate USE_NOTHING = (baseDamage, effect, level, target, user) -> false;
	public static final IUsePredicate USE_UNDEAD = (baseDamage, effect, level, target, user) -> target.getMobType() == MobType.UNDEAD;
	public static final IUsePredicate USE_ARTHROPOD = (baseDamage, effect, level, target, user) -> target.getMobType() == MobType.ARTHROPOD;
	public static final IUsePredicate USE_CRYOTIC = (baseDamage, effect, level, target, user) -> target.getType().is(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES);
	public static final IUsePredicate USE_NECTROTIC = (baseDamage, effect, level, target, user) -> target.getType().is(ModEntityTypeTags.HUMANOIDS);
	public static final IUsePredicate USE_CREEPER = (baseDamage, effect, level, target, user) -> target.getType().is(ModEntityTypeTags.CREEPERS);
	public static final IUsePredicate USE_AQUATIC = (baseDamage, effect, level, target, user) -> target.getMobType() == MobType.WATER;
	public static final IUsePredicate USE_ENDER = (baseDamage, effect, level, target, user) ->  target.getType().is(ModEntityTypeTags.ENDER);
	
	// TODO: Add a way to change the values for oil effects via config file
	protected final String name;
	protected final int color;
	protected final int maxUses;
	protected final float damageModifier;
	protected final IUsePredicate usePredicate;
	protected final boolean isPotionOil;
	
	public OilEffect(String nameIn, int colorIn, int maxUsesIn, float damageModifierIn, IUsePredicate usePredicateIn, boolean isPotionOilIn)
	{
		name = nameIn;
		color = colorIn;
		maxUses = maxUsesIn;
		damageModifier = damageModifierIn;
		usePredicate = usePredicateIn;
		isPotionOil = isPotionOilIn;
	}
	
	public OilEffect(String nameIn, int colorIn, int maxUsesIn, float damageModifierIn, IUsePredicate usePredicateIn)
	{
		this(nameIn, colorIn, maxUsesIn, damageModifierIn, usePredicateIn, false);
	}

	public String getName()
	{
		return name;
	}

	public int getColor(ItemStack stackIn) 
	{
		return color;
	}
	
	public int getMaxUses()
	{
		return maxUses;
	}
	
	public float getDamageModifier()
	{
		return damageModifier;
	}
	
	public float onUse(float baseDamageIn, Level levelIn, LivingEntity targetEntityIn, LivingEntity userEntityIn, ItemStack oilStackIn)
	{
		return usePredicate.test(baseDamageIn, this, levelIn, targetEntityIn, userEntityIn) ? baseDamageIn + (baseDamageIn * getDamageModifier()) : baseDamageIn;
	}
	
	public void getTooltip(ItemStack stackIn, List<Component> tooltipListIn)
	{
		tooltipListIn.add(new TranslatableComponent("potion.whenDrank").withStyle(ChatFormatting.DARK_PURPLE));
		tooltipListIn.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".weapon_oil.applied." + getName(), (getDamageModifier() * 100.0f)).withStyle(ChatFormatting.BLUE));
	}
	
	@FunctionalInterface
	public interface IUsePredicate
	{
		public abstract boolean test(float baseDamageIn, OilEffect effectIn, Level levelIn, LivingEntity targetEntityIn, LivingEntity userEntityIn);
	}
}
