package com.oblivioussp.spartanweaponry.loot;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class DecapitateLootModifier extends LootModifier 
{
	public static final Supplier<Codec<DecapitateLootModifier>> DECAPITATE_CODEC = () -> RecordCodecBuilder.create(instance -> codecStart(instance).and(
				instance.group(ForgeRegistries.ITEMS.getCodec().fieldOf("skull").forGetter(modifier -> modifier.skull)).t1()
			).apply(instance, DecapitateLootModifier::new));
	
	private Item skull;

	public DecapitateLootModifier(LootItemCondition[] conditionsIn, Item skullItem)
	{
		super(conditionsIn);
		skull = skullItem;
	}

	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
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

/*	public static class Serializer extends GlobalLootModifierSerializer<DecapitateLootModifier>
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
		
	}*/

	@Override
	public Codec<? extends IGlobalLootModifier> codec() 
	{
		return DECAPITATE_CODEC.get();
	}
}
