package com.oblivioussp.spartanweaponry.capability;

import java.util.Optional;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.OilEffects;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class OilHandler implements IOilHandler
{
	public static final String NBT_OIL = "Oil";
	public static final String NBT_OIL_EFFECT = "Effect";
	public static final String NBT_USES_LEFT = "UsesLeft";
	
	private final ItemStack stack;
	private Optional<OilEffect> effect;
	private Optional<Potion> potion;
	private int usesLeft;
	
	public OilHandler(ItemStack stackIn)
	{
		stack = stackIn;
		effect = Optional.empty();
		potion = Optional.empty();
		usesLeft = 0;
		// Load the values contained in the NBT if available
		if(stack.getOrCreateTag().contains(NBT_OIL))
			deserializeNBT(stack.getTag().getCompound(NBT_OIL));
	}

	@Override
	public Optional<OilEffect> getEffect() 
	{
		return effect;
	}
	
	@Override
	public Optional<Potion> getPotion() 
	{
		return potion;
	}

	@Override
	public void setEffect(OilEffect effectIn, ItemStack oilStackIn) 
	{
		effect = Optional.of(effectIn);
		usesLeft = effectIn.getMaxUses();
		potion = effectIn == OilEffects.POTION.get() ? Optional.of(PotionUtils.getPotion(oilStackIn)) : Optional.empty();
		stack.getOrCreateTag().put(NBT_OIL, serializeNBT());
	}

	@Override
	public void setPotion(Potion potionIn, ItemStack oilStackIn) 
	{
		effect = Optional.of(OilEffects.POTION.get());
		usesLeft = OilEffects.POTION.get().getMaxUses();
		potion = Optional.of(potionIn);
		stack.getOrCreateTag().put(NBT_OIL, serializeNBT());
	}
	
	@Override
	public void clearEffect() 
	{
		effect = Optional.empty();
		usesLeft = 0;
		stack.getOrCreateTag().remove(NBT_OIL);
	}
	
	@Override
	public float useEffect(float baseDamageIn, Level levelIn, LivingEntity targetIn, LivingEntity userIn, ItemStack userWeaponIn) 
	{
		OilEffect oilEffect = effect.get();
		ItemStack oilStack = oilEffect == OilEffects.POTION.get() ? OilHelper.makePotionOilStack(potion.get()) : OilHelper.makeOilStack(oilEffect);
		float resultDamage = oilEffect.onUse(baseDamageIn, levelIn, targetIn, userIn, oilStack);
		
		usesLeft--;
		stack.getOrCreateTag().put(NBT_OIL, serializeNBT());
		
		if(usesLeft <= 0)
		{
			if(userIn instanceof Player)
				((Player)userIn).displayClientMessage(Component.translatable("message." + ModSpartanWeaponry.ID + ".oil_depleted", oilStack.getHoverName(), userWeaponIn.getHoverName()), true);
			clearEffect();
		}
		return resultDamage;
	}

	@Override
	public boolean isOiled() 
	{
		return effect.isPresent() && effect.get() != OilEffects.NONE.get();
	}

	@Override
	public int getUsesLeft()
	{
		return usesLeft;
	}

	@Override
	public CompoundTag serializeNBT() 
	{
		CompoundTag nbt = new CompoundTag();
		IForgeRegistry<OilEffect> registry = RegistryManager.ACTIVE.getRegistry(OilEffects.REGISTRY_KEY);
		if(registry != null && effect.isPresent())
		{
			if(potion.isPresent())
			{
				ResourceLocation potionLoc = ForgeRegistries.POTIONS.getKey(potion.get());
				nbt.putString(PotionUtils.TAG_POTION, potionLoc.toString());
			}
			ResourceLocation loc = registry.getKey(effect.get());
			nbt.putString(NBT_OIL_EFFECT, loc.toString());
			nbt.putInt(NBT_USES_LEFT, usesLeft);
		}
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) 
	{
		String oilEffectReg = nbt.getString(NBT_OIL_EFFECT);
		IForgeRegistry<OilEffect> registry = RegistryManager.ACTIVE.getRegistry(OilEffects.REGISTRY_KEY);
		if(registry != null)
		{
			effect = !oilEffectReg.isEmpty() ? Optional.of(registry.getValue(new ResourceLocation(oilEffectReg))) : Optional.empty();
			if(nbt.contains(PotionUtils.TAG_POTION))
			{
				String potionReg = nbt.getString(PotionUtils.TAG_POTION);
				potion = !potionReg.isEmpty() ? Optional.of(ForgeRegistries.POTIONS.getValue(new ResourceLocation(potionReg))) : Optional.empty();
			}
		}
		usesLeft = nbt.getInt(NBT_USES_LEFT);
	}
}
