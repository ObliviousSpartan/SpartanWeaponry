package com.oblivioussp.spartanweaponry.api.trait;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;

/**
 * Callback class for Action Weapon Traits; Implement this in your weapon trait class to implement custom action behavior for your weapon<br>
 * @author ObliviousSpartan
 * @apiNote Only 1 Action Weapon Trait can be part of any weapon at a time and they cannot be added as a material bonus Trait
 */
public interface IActionTraitCallback
{
	/**
	 * Called when the player uses the item on a block. Called before {@link IActionTraitCallback#use(ItemStack, Level, LivingEntity, int, float)}<br>
	 * If the result is not {@link InteractionResult#PASS}, then the standard use action will not be used
	 * @param contextIn The context containing info on the player, stack, block position etc
	 * @return
	 */
	public default InteractionResult useOn(UseOnContext contextIn)
	{
		return InteractionResult.PASS;
	}
	
	/**
	 * Defines the interaction of the item when using it without block context. This will determine whether or not using the item will be passed down to the block below it, or not<br>
	 * Hooks into the {@link Item#use(Level, Player, InteractionHand)} method
	 * @param usingStackIn The item stack currently in use
	 * @param levelIn The level the player is currently in
	 * @param playerIn The player entity
	 * @param handIn The hand in use
	 * @return The interaction result
	 */
	public InteractionResultHolder<ItemStack> use(ItemStack usingStackIn, Level levelIn, Player playerIn, InteractionHand handIn);
	
	/**
	 * Called when the player releases (lets go of) the use key when using a weapon with this trait<br>
	 * Hooks into the {@link Item#releaseUsing(ItemStack, Level, LivingEntity, int)} method
	 * @param stackIn The item stack currently in use
	 * @param levelIn The level the using entity is currently in
	 * @param entityLivingIn The entity that is using the item
	 * @param timeLeftIn The remaining use duration left
	 */
	public default void releaseUsing(ItemStack stackIn, Level levelIn, LivingEntity entityLivingIn, int timeLeftIn, float attackDamage) {}
	
	/**
	 * Called when the player holds down the use key when using a weapon with this trait<br>
	 * Hooks into the {@link Item#onUsingTick(ItemStack, LivingEntity, int)} method
	 * @param stackIn The item stack currently in use
	 * @param userEntityIn The entity that is using the item
	 * @param countIn
	 */
	public default void onUsingTick(ItemStack stackIn, LivingEntity userEntityIn, int countIn, float attackDamage) {}
	
	
	/**
	 * Defines the maximum duration that a weapon using this trait can be used for. Change this if you wish the release logic automatically triggered after a certain period<br>
	 * Hooks into the {@link Item#getUseDuration(ItemStack)} method
	 * @param stackIn The item stack the duration applies to
	 * @return The maximum usage duration, in ticks.
	 */
	public default int getUseDuration(ItemStack stackIn)
	{
		return 72000;
	}
	
	/**
	 * Defines the animation for the use action<br>
	 * Hooks into the {@link Item#getUseAnimation(ItemStack)} method
	 * @param stackIn The item stack the animation applies to
	 * @return The use animation
	 */
	public default UseAnim getUseAnimation(ItemStack stackIn)
	{
		return UseAnim.NONE;
	}
	
	/**
	 * This will determine if sneaking by the using entity will bypass the use action<br>
	 * Hooks into the {@link Item#doesSneakBypassUse(ItemStack, LevelReader, BlockPos, Player)} method
	 * @param stackIn The item stack this method applies to
	 * @param levelIn The level the using entity is currently in
	 * @param posIn The position of the block being pointed at by the using entity
	 * @param playerIn The player using the item
	 * @return True if the use action won't be activated when sneaking, false otherwise
	 */
	public default boolean doesSneakBypassUse(ItemStack stackIn, LevelReader levelIn, BlockPos posIn, Player playerIn)
	{
		return false;
	}
}
