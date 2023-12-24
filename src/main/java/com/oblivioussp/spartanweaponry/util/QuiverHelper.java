package com.oblivioussp.spartanweaponry.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableList;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.item.HeavyCrossbowItem;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

public class QuiverHelper 
{
	public interface IQuiverInfo
	{
		public boolean isQuiver(ItemStack stack);
		public boolean isWeapon(ItemStack stack);
		public boolean isAmmo(ItemStack stack);
	}
	
	public static final Predicate<ItemStack> ARROW_QUIVER = (stack) -> stack.is(ModItemTags.ARROW_QUIVERS);
	public static final Predicate<ItemStack> BOLT_QUIVER = (stack) -> stack.is(ModItemTags.BOLT_QUIVERS);
	public static final Predicate<ItemStack> HEAVY_CROSSBOW = (stack) -> stack.is(ModItemTags.HEAVY_CROSSBOWS);
	
	public static List<IQuiverInfo> info = ImmutableList.of(new IQuiverInfo()
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
	
		},
		new IQuiverInfo()
		{
			@Override
			public boolean isQuiver(ItemStack stack)
			{
				return stack.isEmpty() ? false : ARROW_QUIVER.test(stack);
			}
	
			@Override
			public boolean isWeapon(ItemStack stack) 
			{
				return stack.isEmpty() ? false : (stack.getItem() instanceof ProjectileWeaponItem && !(stack.getItem() instanceof HeavyCrossbowItem));
			}
	
			@Override
			public boolean isAmmo(ItemStack stack) 
			{
				return stack.isEmpty() ? false : ProjectileWeaponItem.ARROW_ONLY.test(stack);
			}
		});

//	public static final Predicate<ItemStack> ARROW_QUIVER = (stack) -> stack.getItem().isIn(ItemTags.getCollection().get(new ResourceLocation(SpartanWeaponryAPI.MOD_ID, "arrow_quivers")));
//	public static final Predicate<ItemStack> BOLT_QUIVER = (stack) -> stack.getItem().isIn(ItemTags.getCollection().get(new ResourceLocation(SpartanWeaponryAPI.MOD_ID, "bolt_quivers")));
//	public static final Predicate<ItemStack> HEAVY_CROSSBOW = (stack) -> stack.getItem().isIn(ItemTags.getCollection().get(new ResourceLocation(SpartanWeaponryAPI.MOD_ID, "heavy_crossbows")));
	
	public static ItemStack findFirstOfType(Player player, IQuiverInfo info)
	{
		// Find a quiver, if possible.
		// Via a Curios slot...
		if(ModList.get().isLoaded(CuriosApi.MODID))
		{
			Optional<SlotResult> opt = CuriosApi.getCuriosHelper().findFirstCurio(player, (stack) -> stack.getItem() instanceof QuiverBaseItem);
			if(opt.isPresent() && info.isQuiver(opt.get().stack()))
				return opt.get().stack();
		}
		// ... or via the hotbar
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = player.getInventory().getItem(i);
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
	public static List<ItemStack> findValidQuivers(Player player)
	{
		List<ItemStack> result = new ArrayList<ItemStack>();
		
		// Find a quiver, if possible.
		// Via the Curios back slot 
		if(ModList.get().isLoaded(CuriosApi.MODID))
		{
			Optional<SlotResult> opt = CuriosApi.getCuriosHelper().findFirstCurio(player, (stack) -> stack.getItem() instanceof QuiverBaseItem);
			if(opt.isPresent())
				result.add(opt.get().stack());
		}
		// ... or via the hotbar
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = player.getInventory().getItem(i);
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
	public static ItemStack findFirstQuiver(Player player)
	{
		// Find a quiver, if possible.
		// Via the Curios back slot 
		if(ModList.get().isLoaded(CuriosApi.MODID))
		{
			Optional<SlotResult> opt = CuriosApi.getCuriosHelper().findFirstCurio(player, (stack) -> stack.getItem() instanceof QuiverBaseItem);
			if(opt.isPresent())
				return opt.get().stack();
		}
		// ... or via the hotbar
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = player.getInventory().getItem(i);
			if(!stack.isEmpty() && (stack.getItem() instanceof QuiverBaseItem))
				return stack;
		}
		
		return ItemStack.EMPTY;
	}
}
