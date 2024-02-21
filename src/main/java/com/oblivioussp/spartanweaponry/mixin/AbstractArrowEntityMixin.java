package com.oblivioussp.spartanweaponry.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.util.Log;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity.PickupStatus;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@Mixin(AbstractArrowEntity.class)
public abstract class AbstractArrowEntityMixin extends ProjectileEntityMixin
{
	/**
	 * Mixin method to collect the arrow entity and put the item in a Quiver
	 * @param entityIn
	 * @param callback
	 */
	@Inject(at = @At("HEAD"), method = "onCollideWithPlayer(Lnet/minecraft/entity/player/PlayerEntity;)V", cancellable = true)
	private void onCollideWithPlayer(PlayerEntity entityIn, CallbackInfo callback)
	{
		if(!world.isRemote && (inGround || getNoClip()) && arrowShake <= 0)
		{
			Log.debug("Player collision with arrow entity intercepted!");
			
			if(pickupStatus == PickupStatus.ALLOWED)
			{
				// Attempt to pickup and put into the quiver first; if that fails, then do nothing
				List<ItemStack> quivers = QuiverHelper.findValidQuivers(entityIn);
				ItemStack arrowStack = getArrowStack();

				// Check if the player has the same arrow type and space to store more in one of their hands first before placing it in the quiver
				ItemStack mainHand = entityIn.getHeldItemMainhand();
				ItemStack offHand = entityIn.getHeldItemOffhand();
				if((offHand.getItem() == arrowStack.getItem() && offHand.getCount() < offHand.getMaxStackSize()) || 
						mainHand.getItem() == arrowStack.getItem() && mainHand.getCount() < mainHand.getMaxStackSize())
					return;
				
				if(!quivers.isEmpty())
				{
					// Loop through all valid quivers to place the item into...
					for(ItemStack quiver : quivers)
					{
						if(!arrowStack.isEmpty() && !quiver.isEmpty() && ((QuiverBaseItem)quiver.getItem()).isAmmoValid(arrowStack, quiver))
						{
							// Make sure auto-collect is enabled.
							if(quiver.getOrCreateTag().getBoolean("ammoCollect"))
							{
								// Attempt to place the arrows into the quiver.
								IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElseThrow(QuiverBaseItem.CAPABILITY_EXCEPTION);
								for(int i = 0; i < quiverHandler.getSlots(); i++)
								{
									arrowStack = quiverHandler.insertItem(i, arrowStack, false);
								}
							}
						}
						if(arrowStack.isEmpty())
						{
							Log.debug("Picked up arrow on the ground and placed it in the quiver!");
							Entity thisEntity = world.getEntityByID(getEntityId());
							entityIn.onItemPickup(thisEntity, 1);
							remove();
							world.playSound((PlayerEntity)null, getPosX(), getPosY(), getPosZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, (rand.nextFloat() - rand.nextFloat()) * 0.7F + 0.0F);
							// Cancel running the underlying method if the arrow is directly picked up
							callback.cancel();
							break;
						}
					}
				}
			}
		}
//		callback.cancel();
	}
	
	@Shadow
	protected boolean inGround;
	@Shadow
	public int arrowShake;
	@Shadow
	public PickupStatus pickupStatus;
	
	@Shadow
	public boolean getNoClip()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"AbstractArrowEntity.getNoClip()\" method!");
	}
	
	@Shadow
	public ItemStack getArrowStack()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"AbstractArrowEntity.getArrowStack()\" method!");
	}
}
