package com.oblivioussp.spartanweaponry.loot;

import java.util.List;

import com.google.gson.JsonObject;
import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class DecapitateLootModifier extends LootModifier 
{
	private final Item skull;	

	public DecapitateLootModifier(LootItemCondition[] conditionsIn, Item skullItem)
	{
		super(conditionsIn);
		skull = skullItem;
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
	{
		if(!context.hasParam(LootContextParams.KILLER_ENTITY))
			return generatedLoot;
		
		Entity killer = context.getParam(LootContextParams.KILLER_ENTITY);
		if(killer instanceof LivingEntity)
		{
			LivingEntity living = (LivingEntity)killer;
			ItemStack weapon = living.getMainHandItem();
//			ItemStack weapon = context.get(LootContextParams.TOOL);
			
			// TODO: See if there is a way that this weapon (above) can be retrieved in a dual-wielding friendly way.
			if(context.getRandom().nextDouble() < WeaponTraits.DECAPITATE.get().getMagnitude() / 100.0f && weapon.getItem() instanceof IWeaponTraitContainer)
			{
				IWeaponTraitContainer<?> container = (IWeaponTraitContainer<?>)weapon.getItem();
				
				if(container.hasWeaponTraitWithType(WeaponTraits.TYPE_DECAPITATE))
				{
					ItemStack skullStack = new ItemStack(skull);
					Entity thisEntity = context.getParam(LootContextParams.THIS_ENTITY);
					if(thisEntity instanceof Player)
					{
						Player player = (Player)thisEntity;
						// Add the player NBT data to the skull ItemStack
						skullStack.getOrCreateTag().putString("SkullOwner", player.getGameProfile().getName());
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
				LootItemCondition[] lootConditions) 
		{
			Item skullItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "skull")));
			return new DecapitateLootModifier(lootConditions, skullItem);
		}

		@Override
		public JsonObject write(DecapitateLootModifier instance)
		{
			JsonObject result = this.makeConditions(instance.conditions);
			result.addProperty("skull", ForgeRegistries.ITEMS.getKey(instance.skull).toString());
			return result;
		}
		
	}
}
