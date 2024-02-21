package com.oblivioussp.spartanweaponry.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.item.HeavyCrossbowItem;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;

public class QuiverHelper 
{
	public static List<IQuiverInfo> info;

	public static final Predicate<ItemStack> ARROW_QUIVER = (stack) -> stack.getItem().isIn(ItemTags.getCollection().get(new ResourceLocation(SpartanWeaponryAPI.MOD_ID, "arrow_quivers")));
	public static final Predicate<ItemStack> BOLT_QUIVER = (stack) -> stack.getItem().isIn(ItemTags.getCollection().get(new ResourceLocation(SpartanWeaponryAPI.MOD_ID, "bolt_quivers")));
	public static final Predicate<ItemStack> HEAVY_CROSSBOW = (stack) -> stack.getItem().isIn(ItemTags.getCollection().get(new ResourceLocation(SpartanWeaponryAPI.MOD_ID, "heavy_crossbows")));
	
	static
	{
		info = new ArrayList<IQuiverInfo>();

		info.add(new IQuiverInfo()
			{
				@Override
				public boolean isQuiver(ItemStack stack) 
				{
					return stack.isEmpty() ? false : BOLT_QUIVER.test(stack);
				}

				@Override
				public boolean isWeapon(ItemStack stack) 
				{
					return stack.isEmpty() ? false : HEAVY_CROSSBOW.test(stack);
				}

				@Override
				public boolean isAmmo(ItemStack stack)
				{
					return stack.isEmpty() ? false : HeavyCrossbowItem.BOLT.test(stack);
				}
		
			});
		info.add(new IQuiverInfo()
			{
				@Override
				public boolean isQuiver(ItemStack stack)
				{
					return stack.isEmpty() ? false : ARROW_QUIVER.test(stack);
				}
	
				@Override
				public boolean isWeapon(ItemStack stack) 
				{
					return stack.isEmpty() ? false : stack.getItem() instanceof ShootableItem && !(stack.getItem() instanceof HeavyCrossbowItem);
				}
	
				@Override
				public boolean isAmmo(ItemStack stack) 
				{
					return stack.isEmpty() ? false : BowItem.ARROWS.test(stack);
				}
			});
		
	}
	
	public static ItemStack findFirstOfType(PlayerEntity player, IQuiverInfo info)
	{
		// Find a quiver, if possible.
		// Via a Curios slot...
		if(ModList.get().isLoaded(CuriosApi.MODID))
		{
			Optional<ImmutableTriple<String, Integer, ItemStack>> opt = CuriosApi.getCuriosHelper().findEquippedCurio((stack) -> stack.getItem() instanceof QuiverBaseItem, player);
			if(opt.isPresent() && info.isQuiver(opt.get().right))
				return opt.get().right;
		}
		// ... or via the hotbar
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			if(!stack.isEmpty() && info.isQuiver(stack))
			{
				return stack;
			}
		}
		
		return ItemStack.EMPTY;
	}
	
	/**
	 * Find all valid Quivers in the player's hotbar or Curios slot (if Curios is installed), regardless of quiver type
	 * @param player
	 * @return
	 */
	public static List<ItemStack> findValidQuivers(PlayerEntity player)
	{
		List<ItemStack> result = new ArrayList<ItemStack>();
		
		// Find a quiver, if possible.
		// Via the Curios back slot 
		if(ModList.get().isLoaded(CuriosApi.MODID))
		{
			Optional<ImmutableTriple<String, Integer, ItemStack>> opt = CuriosApi.getCuriosHelper().findEquippedCurio((stack) -> stack.getItem() instanceof QuiverBaseItem, player);
			if(opt.isPresent())
				result.add(opt.get().right);
		}
		// ... or via the hotbar
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			if(!stack.isEmpty() && (stack.getItem() instanceof QuiverBaseItem))
				result.add(stack);
		}
		
		return result;
	}
	
	/**
	 * Find the first found Quiver in the player's hotbar or Curios slot (if Curios is installed), regardless of quiver type
	 * @param player
	 * @return
	 */
	public static ItemStack findFirstQuiver(PlayerEntity player)
	{
		
		// Find a quiver, if possible.
		// Via the Curios back slot 
		if(ModList.get().isLoaded(CuriosApi.MODID))
		{
			Optional<ImmutableTriple<String, Integer, ItemStack>> opt = CuriosApi.getCuriosHelper().findEquippedCurio((stack) -> stack.getItem() instanceof QuiverBaseItem, player);
			if(opt.isPresent())
				return opt.get().right;
		}
		// ... or via the hotbar
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			if(!stack.isEmpty() && (stack.getItem() instanceof QuiverBaseItem))
				return stack;
		}
		
		return ItemStack.EMPTY;
	}
	
	public interface IQuiverInfo
	{
		public boolean isQuiver(ItemStack stack);
		public boolean isWeapon(ItemStack stack);
		public boolean isAmmo(ItemStack stack);
	}
}
