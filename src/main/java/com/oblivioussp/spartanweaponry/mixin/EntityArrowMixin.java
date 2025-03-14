package com.oblivioussp.spartanweaponry.mixin;

import java.util.List;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.oblivioussp.spartanweaponry.item.ItemQuiverBase;
import com.oblivioussp.spartanweaponry.util.Log;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@Mixin(EntityArrow.class)
public abstract class EntityArrowMixin extends Entity {
	
	public EntityArrowMixin(World worldIn) {
		super(worldIn);
	}
	
	@Shadow public PickupStatus pickupStatus;
	
	@Shadow protected abstract ItemStack getArrowStack();
	
	@Shadow protected boolean inGround;
	
	@Shadow public int arrowShake;
	
	/**
	 * Mixin method to collect the arrow entity and put the item in a Quiver
	 */
	@Inject(at = @At("HEAD"), method = "onCollideWithPlayer(Lnet/minecraft/entity/player/EntityPlayer;)V", cancellable = true)
	private void spartanWeaponry_vanillaEntityArrow_onCollideWithPlayer(EntityPlayer entityIn, CallbackInfo callback)
	{
		if(!this.world.isRemote && this.inGround && this.arrowShake <= 0)
		{
			Log.debug("Player collision with arrow entity intercepted!");
//			boolean pickupItem = pickupStatus == PickupStatus.ALLOWED || pickupStatus == PickupStatus.CREATIVE_ONLY && entityIn.abilities.isCreativeMode || getNoClip() && getShooter().getUniqueID() == entityIn.getUniqueID();
			
			if(this.pickupStatus == PickupStatus.ALLOWED)
			{
				// Attempt to pickup and put into the quiver first; if that fails, then do nothing
				List<ItemStack> quivers = QuiverHelper.findValidQuivers(entityIn);
				ItemStack arrowStack = this.getArrowStack();

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
						if(!arrowStack.isEmpty() && !quiver.isEmpty() && ((ItemQuiverBase)quiver.getItem()).isAmmoValid(arrowStack, quiver))
						{
							// Make sure auto-collect is enabled.
							if(!quiver.hasTagCompound())
								quiver.setTagCompound(new NBTTagCompound());
							if(quiver.getTagCompound().getBoolean("ammoCollect"))
							{
								// Attempt to place the arrows into the quiver.
								IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
								for(int i = 0; i < quiverHandler.getSlots(); i++)
								{
									arrowStack = quiverHandler.insertItem(i, arrowStack, false);
								}
							}
						}
						if(arrowStack.isEmpty())
						{
							Log.debug("Picked up arrow on the ground and placed it in the quiver!");
							Entity thisEntity = this.world.getEntityByID(this.getEntityId());
							entityIn.onItemPickup(thisEntity, 1);
							this.setDead();
							this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 0.0F);
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
}