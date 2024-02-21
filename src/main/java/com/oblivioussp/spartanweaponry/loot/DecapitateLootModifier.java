package com.oblivioussp.spartanweaponry.loot;

import java.util.List;

import com.google.gson.JsonObject;
import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class DecapitateLootModifier extends LootModifier 
{
	private final Item skull;	

	public DecapitateLootModifier(ILootCondition[] conditionsIn, Item skullItem)
	{
		super(conditionsIn);
		skull = skullItem;
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
	{
		Entity killer = context.get(LootParameters.KILLER_ENTITY);
		if(killer instanceof LivingEntity)
		{
			LivingEntity living = (LivingEntity)killer;
			ItemStack weapon = living.getHeldItemMainhand();
			
			// TODO: See if there is a way that this weapon (above) can be retrieved in a dual-wielding friendly way.
			if(context.getRandom().nextDouble() < WeaponTraits.DECAPITATE.getMagnitude() / 100.0f && weapon.getItem() instanceof IWeaponTraitContainer)
			{
				IWeaponTraitContainer<?> container = (IWeaponTraitContainer<?>)weapon.getItem();
				WeaponTrait trait = container.getFirstWeaponTraitWithType(WeaponTraits.TRAIT_TYPE_DECAPITATE);
				
				if(trait != null)
				{
					ItemStack skullStack = new ItemStack(skull);
					Entity thisEntity = context.get(LootParameters.THIS_ENTITY);
					if(thisEntity instanceof PlayerEntity)
					{
						PlayerEntity player = (PlayerEntity)thisEntity;
						// Add the player NBT data to the skull ItemStack
						skullStack.getTag().putString("SkullOwner", player.getGameProfile().getName());
					}
					generatedLoot.add(skullStack);
				}
			}
		}
		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<DecapitateLootModifier>
	{
		
		@Override
		public DecapitateLootModifier read(ResourceLocation location, JsonObject object,
				ILootCondition[] lootConditions) 
		{
			Item skullItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getString(object, "skull")));
			return new DecapitateLootModifier(lootConditions, skullItem);
		}

		@Override
		public JsonObject write(DecapitateLootModifier instance)
		{
			JsonObject result = makeConditions(instance.conditions);
			result.addProperty("skull", ForgeRegistries.ITEMS.getKey(instance.skull).toString());
			return result;
		}
		
	}
}
