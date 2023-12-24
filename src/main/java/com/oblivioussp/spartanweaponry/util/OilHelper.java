package com.oblivioussp.spartanweaponry.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mojang.datafixers.util.Pair;
import com.oblivioussp.spartanweaponry.api.OilEffects;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.capability.OilHandler;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class OilHelper
{
	
	private static final Component NO_EFFECT = new TranslatableComponent("effect.none").withStyle(ChatFormatting.GRAY);
	
	public static OilEffect getOilFromStack(ItemStack stackIn)
	{
		CompoundTag tag = stackIn.getOrCreateTag().getCompound(OilHandler.NBT_OIL);
		ResourceLocation oil = new ResourceLocation(tag.getString(OilHandler.NBT_OIL_EFFECT));
		ForgeRegistry<OilEffect> registry = RegistryManager.ACTIVE.getRegistry(OilEffects.REGISTRY_KEY);
		if(registry.containsKey(oil))
			return registry.getValue(oil);
		return OilEffects.NONE.get();
	}
	
	public static ItemStack makeOilStack(OilEffect oilIn)
	{
		ItemStack stack = new ItemStack(ModItems.WEAPON_OIL.get());
		CompoundTag tag = new CompoundTag();
		tag.putString(OilHandler.NBT_OIL_EFFECT, oilIn.getRegistryName().toString());
		stack.getOrCreateTag().put(OilHandler.NBT_OIL, tag);
		return stack;
	}
	
	public static Potion getPotionFromStack(ItemStack stackIn)
	{
		return PotionUtils.getPotion(stackIn.getOrCreateTag().getCompound(OilHandler.NBT_OIL));
	}
	
	public static ItemStack makePotionOilStack(Potion potionIn)
	{
		ItemStack stack = makeOilStack(OilEffects.POTION.get());
		CompoundTag tag = stack.getOrCreateTag().getCompound(OilHandler.NBT_OIL);
		tag.putString(PotionUtils.TAG_POTION, ForgeRegistries.POTIONS.getKey(potionIn).toString());
		stack.getTag().put(OilHandler.NBT_OIL, tag);
		return stack;
	}
	
	public static boolean isValidPotion(Potion potionIn)
	{
		boolean isValidPotion = true;
		if(potionIn.getEffects().isEmpty() || Config.INSTANCE.potionOilBlacklist.get().contains(ForgeRegistries.POTIONS.getKey(potionIn).toString()))
			return false;
		
		if(Config.INSTANCE.potionOilWhitelist.get().contains(ForgeRegistries.POTIONS.getKey(potionIn).toString()))
			return true;
			
		for(MobEffectInstance effect : potionIn.getEffects())
		{
			// Block non-harmful effects
			if(effect.getEffect().getCategory() != MobEffectCategory.HARMFUL)
			{
				isValidPotion = false;
				break;
			}
		}
		return isValidPotion;
	}
	
	public static void addPotionTooltip(ItemStack stackIn, List<Component> tooltipListIn, float durationModifierIn)
	{
		addPotionTooltip(stackIn.getOrCreateTag().getCompound(OilHandler.NBT_OIL), tooltipListIn, durationModifierIn);
	}
	
	public static void addPotionTooltip(CompoundTag tagIn, List<Component> tooltipListIn, float durationModifierIn)
	{
		// Modified version of PotionUtils.addPotionTooltip() to handle compound NBT tags
		List<MobEffectInstance> mobEffectList = PotionUtils.getAllEffects(tagIn);
		List<Pair<Attribute, AttributeModifier>> attributeList = new ArrayList<>();
		
		if(mobEffectList.isEmpty())
			tooltipListIn.add(NO_EFFECT);
		else
		{
			for(MobEffectInstance mobEffectInst : mobEffectList)
			{
				MutableComponent component = new TranslatableComponent(mobEffectInst.getDescriptionId());
				MobEffect mobEffect = mobEffectInst.getEffect();
				Map<Attribute, AttributeModifier> attributeMap = mobEffect.getAttributeModifiers();
				if(!attributeMap.isEmpty())
				{
					for(Entry<Attribute, AttributeModifier> entry : attributeMap.entrySet())
					{
						AttributeModifier modifier = entry.getValue();
						AttributeModifier modifierCopy = new AttributeModifier(modifier.getName(), mobEffect.getAttributeModifierValue(mobEffectInst.getAmplifier(), modifier), modifier.getOperation());
						attributeList.add(Pair.of(entry.getKey(), modifierCopy));
					}
				}
				
				if(mobEffectInst.getAmplifier() > 0)
					component = new TranslatableComponent("potion.withAmplifier", component, new TranslatableComponent("potion.potency." + mobEffectInst.getAmplifier()));
				
				if(mobEffectInst.getDuration() > 20)
					component = new TranslatableComponent("potion.withDuration", component, MobEffectUtil.formatDuration(mobEffectInst, durationModifierIn));
				
				tooltipListIn.add(component.withStyle(mobEffect.getCategory().getTooltipFormatting()));
			}
		}
		
		if(!attributeList.isEmpty())
		{
			tooltipListIn.add(TextComponent.EMPTY);
			tooltipListIn.add(new TranslatableComponent("potion.whenDrank").withStyle(ChatFormatting.DARK_PURPLE));
			
			for(Pair<Attribute, AttributeModifier> attributePair : attributeList)
			{
				AttributeModifier modifier = attributePair.getSecond();
				double amount = modifier.getAmount();
				double modifiedValue;
				
				if(modifier.getOperation() != Operation.MULTIPLY_BASE && modifier.getOperation() != Operation.MULTIPLY_TOTAL)
					modifiedValue = modifier.getAmount();
				else
					modifiedValue = modifier.getAmount() * 100.0d;
				
				if(amount > 0.0d)
					tooltipListIn.add(new TranslatableComponent("attribute.modifier.plus." + modifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(modifiedValue), new TranslatableComponent(attributePair.getFirst().getDescriptionId())).withStyle(ChatFormatting.BLUE));
				else if(amount < 0.0d)
				{
					modifiedValue *= -1.0d;
					tooltipListIn.add(new TranslatableComponent("attribute.modifier.take." + modifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(modifiedValue), new TranslatableComponent(attributePair.getFirst().getDescriptionId())).withStyle(ChatFormatting.RED));
				}
			}
		}
		
	}
}
