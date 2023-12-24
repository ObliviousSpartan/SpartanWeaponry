package com.oblivioussp.spartanweaponry.event;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.api.weaponproperty.IPropertyCallback;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.init.EnchantmentRegistrySW;
import com.oblivioussp.spartanweaponry.item.IBlockingWeapon;
import com.oblivioussp.spartanweaponry.item.ItemQuiverBase;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;
import com.oblivioussp.spartanweaponry.util.AdvancementTrigger;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.Log;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper.IQuiverInfo;
import com.oblivioussp.spartanweaponry.util.WeaponHelper;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@EventBusSubscriber
public class EventHandlerCommon 
{
	public static Random rand = new Random();
	public static int tickCounter = 0;
	
	/**
	 * When an entity is hurt, check to see if extra damage should be applied, or if damage should be reduced, given the appropriate weapon.
	 * @param ev
	 */
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent ev)
	{
		DamageSource source = ev.getSource();
		float dmgDealt = ev.getAmount();
		EntityLivingBase victim = ev.getEntityLiving();
		
		// NOTE: Debug code to check damage inflicted to mobs
		if(ModSpartanWeaponry.debugMode)
			Log.info("Damage: Entity: " + victim.getDisplayName().getUnformattedComponentText() + " Armour value: " + victim.getTotalArmorValue() + " Damage value: " + dmgDealt + " Source: " + source.damageType + " BypassesArmour: " + source.isUnblockable());
		
		// Fix: Adds a much more thorough checking for non-player and non-melee damage, so extra damage is not applied to them.
		// Also added a damage check to prevent TaN from adding damage modifiers twice.
		if(dmgDealt == 0.0f || source.isProjectile() || source.isFireDamage() || source.isExplosion() || source.isMagicDamage() ||
				(!source.getDamageType().equals("player") && !source.getDamageType().equals("mob")))
			return;
		
		// Fix: Lycanites mobs that are tamed get damage bonuses from player weapons when attacking. This shouldn't happen. 
		// Added an extra check to only allow direct attacks, and not using projectiles, etc.
		if(source.getImmediateSource() == source.getTrueSource() && source.getTrueSource() instanceof EntityLivingBase && victim != null)
		{
			EntityLivingBase attacker = (EntityLivingBase) source.getTrueSource();
			// Check what weapon is equipped (believe it or not: Better Combat already handles this by swapping the offhand weapon (when appropriate) with the main hand weapon)
			ItemStack stack = attacker.getHeldItemMainhand();
			ItemStack victimStack = victim.getHeldItemMainhand();
			if (!stack.isEmpty())
			{
				if(stack.getItem() instanceof IWeaponPropertyContainer)
				{
					IWeaponPropertyContainer container = (IWeaponPropertyContainer)stack.getItem();
					float directDamage = container.getDirectAttackDamage();
					
					List<WeaponProperty> props = container.getAllWeaponProperties();
					for(WeaponProperty prop : props)
					{
						IPropertyCallback callback = prop.getCallback();
						if(callback != null)
							dmgDealt = callback.modifyDamageDealt(container.getMaterialEx(), dmgDealt, directDamage + 1.0f, source, attacker, victim);
					}
					for(WeaponProperty prop : container.getMaterialEx().getAllWeaponProperties())
					{
						IPropertyCallback callback = prop.getCallback();
						if(callback != null)
							dmgDealt = callback.modifyDamageDealt(container.getMaterialEx(), dmgDealt, directDamage + 1.0f, source, attacker, victim);
					}
				}
				if(stack.getItem() instanceof ItemThrowingWeapon && stack.hasTagCompound() && 
						stack.getTagCompound().getInteger(ItemThrowingWeapon.NBT_AMMO_USED) >= ((ItemThrowingWeapon)stack.getItem()).getMaxAmmo(stack))
				{
					// Only do punching damage when melee attacking using a throwing weapon without ammo
					dmgDealt = 1.0f;
				}
			}
			// Check the item that the victim is holding for damage reduction
			if(!victimStack.isEmpty())
			{
				if(victimStack.getItem() instanceof IWeaponPropertyContainer)
				{
					IWeaponPropertyContainer container = (IWeaponPropertyContainer)victimStack.getItem();
					List<WeaponProperty> props = container.getAllWeaponProperties();
					for(WeaponProperty prop : props)
					{
						IPropertyCallback callback = prop.getCallback();
						if(callback != null)
							dmgDealt = callback.modifyDamageTaken(container.getMaterialEx(), dmgDealt, source, attacker, victim);
					}
					for(WeaponProperty prop : container.getMaterialEx().getAllWeaponProperties())
					{
						IPropertyCallback callback = prop.getCallback();
						if(callback != null)
							dmgDealt = callback.modifyDamageTaken(container.getMaterialEx(), dmgDealt, source, attacker, victim);
					}
				}
			}
			
			// Set the real damage dealt through normal means.
			if(dmgDealt != ev.getAmount())
			{
				if(!attacker.world.isRemote) 
				{
					// Emit particles when damage has been enhanced or mitigated, depending on what has happened
					if(dmgDealt > ev.getAmount())
						((WorldServer)attacker.world).spawnParticle(EnumParticleTypes.CRIT_MAGIC, victim.posX, victim.posY + (victim.height / 2.0f), victim.posZ, 16, 0.2d, 0.2d, 0.2d, 0.0d);
					else if(dmgDealt < ev.getAmount())
						((WorldServer)attacker.world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, victim.posX, victim.posY + (victim.height / 2.0f), victim.posZ, 16, 0.2d, 0.2d, 0.2d, 0.0d);
				}

				//Log.debug(String.format("Changed damage dealt! From %s - %f -> %f", ev.getSource().toString(), ev.getAmount(), dmgDealt));
				ev.setAmount(dmgDealt);
			}
		}
	}
	
	/**
	 *  Attack Event - Handles damage.
	 * @param ev
	 */
	@SubscribeEvent
	public static void attackEvent(LivingAttackEvent ev)
	{
		if(ev.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)ev.getEntityLiving();

			if(player.isHandActive() && !player.getActiveItemStack().isEmpty())
			{
				ItemStack activeStack = player.getActiveItemStack();
				
				if(activeStack.getItem() instanceof IBlockingWeapon)
				{
					IBlockingWeapon weapon = (IBlockingWeapon)activeStack.getItem();
					DamageSource source = ev.getSource();
					boolean damageItem = false;
					
					// Block Melee attacks only! Explosion, Fire, Magic, Projectile and unblockable damage won't be blocked!
					if(weapon.canBlockMelee() && !source.isExplosion() && !source.isFireDamage() && !source.isMagicDamage() && !source.isProjectile() && !source.isUnblockable())
					{
						// Do knockback due to damage.
						Entity trueSourceEntity = source.getTrueSource();
						double mX = trueSourceEntity.motionX;
						double mY = trueSourceEntity.motionY;
						double mZ = trueSourceEntity.motionZ;
						
                        if (trueSourceEntity instanceof EntityLivingBase)
                        {
                            ((EntityLivingBase)source.getTrueSource()).knockBack(player, 0.3F, player.posX - source.getTrueSource().posX, player.posZ - source.getTrueSource().posZ);
                        }
                        if(trueSourceEntity instanceof EntityPlayerMP && trueSourceEntity.velocityChanged)
                        {
                        	((EntityPlayerMP)trueSourceEntity).connection.sendPacket(new SPacketEntityVelocity(trueSourceEntity));
                        	trueSourceEntity.velocityChanged = false;
                        	trueSourceEntity.motionX = mX;
                            trueSourceEntity.motionY = mY;
                            trueSourceEntity.motionZ = mZ;
                        }
                        damageItem = true;
					}
					else if(weapon.canBlockProjectiles() && ev.getSource().isProjectile())
                        damageItem = true;
					
					if(damageItem)
					{
						ItemStack copy = activeStack.copy();
                        int itemDamage = 1 + MathHelper.floor(ev.getAmount());
                        activeStack.damageItem(itemDamage, player);
                        
                        if(activeStack.isEmpty())
                        {
                        	EnumHand activeHand = player.getActiveHand();
                        	ForgeEventFactory.onPlayerDestroyItem(player, copy, activeHand);
                            player.renderBrokenItemStack(copy);
                        	if(activeHand == EnumHand.MAIN_HAND)
                        		player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
                        	else if(activeHand == EnumHand.OFF_HAND)
                        		player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);

                        	activeStack = ItemStack.EMPTY;
                        }
                        player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, SoundCategory.PLAYERS, 0.8f, 0.8f);
                        ev.setCanceled(true);                        
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onCraftEvent(ItemCraftedEvent ev)
	{
		if(ev.player instanceof EntityPlayerMP)
		{
//			if(ev.crafting.getItem().getRegistryName().getPath().equals(Reference.ModID))
			AdvancementTrigger.CRAFT_ITEM.trigger((EntityPlayerMP) ev.player, ev.crafting.getItem());
		}
	}
	
	/**
	 * Applies the Lucky Strike Enchantment to the Looting modifier
	 * @param ev
	 */
	@SubscribeEvent
	public static void onLootLevel(LootingLevelEvent ev)
	{
		Entity e = ev.getDamageSource().getImmediateSource();
		
		if(e instanceof EntityThrownWeapon)
		{
			// Allow the Lucky Strike Enchantment to use used as Looting
			EntityThrownWeapon thrownWeapon = (EntityThrownWeapon)e;
			int luckLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.THROWING_LUCK, thrownWeapon.getWeaponStack());
			
			ev.setLootingLevel(luckLevel);
		}
	}
	
	@SubscribeEvent
	public static void onEquipmentChange(LivingEquipmentChangeEvent ev)
	{
		// What should be done.
		// - Check to see if a Bow or Crossbow of any kind is equipped, in main-hand or off-hand.
		// - If true, get the opposite hand of the equipped weapon, and check if it's empty
		// - If it's empty, check to see if the appropriate quiver is in either the hotbar or the appropriate Baubles slot.
		// - If found, then place a stack of ammo in the appropriate opposite hand slot, and take that ammo from the quiver.
		// -- If the weapon is unequiped, do the opposite and place the arrow stack back into the quiver as appropriate.
		
		if((ev.getSlot() == EntityEquipmentSlot.MAINHAND || ev.getSlot() == EntityEquipmentSlot.OFFHAND) && ev.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)ev.getEntityLiving();
			ItemStack fromStack = ev.getFrom();
			ItemStack toStack = ev.getTo();
			EntityEquipmentSlot oppositeHand;
			ItemStack oppositeStack;
			
			// Get the opposite hand for equipping ammo in.
			if(ev.getSlot() == EntityEquipmentSlot.OFFHAND)
				oppositeHand = EntityEquipmentSlot.MAINHAND;
			else
				oppositeHand = EntityEquipmentSlot.OFFHAND;
			
			oppositeStack = player.getItemStackFromSlot(oppositeHand);

			// Check to see if a bow has been equipped //and if the opposite hand slot is empty.
			if(!toStack.isItemEqual(fromStack) /*&& oppositeStack.isEmpty()*/)
			{
				// Check to see if there is an item in the opposite slot
				if(!oppositeStack.isEmpty())
				{
					boolean toStackBlacklisted = false;
					String toName = toStack.getItem().getRegistryName().toString();
					
					// Small optimization for checking quiver bow blacklist
					if(ConfigHandler.quiverBowBlacklist.contains(toName))
						toStackBlacklisted = true;
					
					for(IQuiverInfo quiverInfo : QuiverHelper.info)
					{
						if(quiverInfo.isWeapon(fromStack) && (!quiverInfo.isWeapon(toStack) || toStackBlacklisted) && quiverInfo.isAmmo(oppositeStack))
						{
							ItemStack quiver = QuiverHelper.findFirstOfType(player, quiverInfo);
							placeAmmoIntoQuiver(player, quiver, oppositeHand);

							oppositeStack = player.getItemStackFromSlot(oppositeHand);
							if(oppositeStack.isEmpty())
							{
								// If there is any offhand item data in the quiver, find it and put it back
								NBTTagCompound nbt = quiver.getOrCreateSubCompound(ItemQuiverBase.NBT_OFFHAND_MOVED);
								if(nbt != null)
								{
									String itemId = nbt.getString(ItemQuiverBase.NBT_ITEM_ID);
									int itemSlot = nbt.getInteger(ItemQuiverBase.NBT_ITEM_SLOT);
									ItemStack offhandStack = player.inventory.getStackInSlot(itemSlot);
									// Check to see if the item in the slot is a match
									if(offhandStack.getItem().getRegistryName().toString().equals(itemId))
									{
										// Now move the item to the offhand from the appropriate inventory slot
										player.setItemStackToSlot(oppositeHand, offhandStack);
										player.inventory.setInventorySlotContents(itemSlot, ItemStack.EMPTY);
									}
									// Delete the NBT data stored in the quiver, regardless of whether or not anything happened
									quiver.getTagCompound().removeTag(ItemQuiverBase.NBT_OFFHAND_MOVED);
								}
								break;
							}
						}
					}
				}
				
				// Check to see if the weapon being equipped is blacklisted in the config
				String regName = toStack.getItem().getRegistryName().toString();
				if(ConfigHandler.quiverBowBlacklist.contains(regName))
					return;
				
				for(IQuiverInfo quiverInfo : QuiverHelper.info)
				{
					if(quiverInfo.isWeapon(toStack))
					{
						ItemStack quiver = QuiverHelper.findFirstOfType(player, quiverInfo);
						
						if(!quiver.isEmpty())
						{
							IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
							boolean isQuiverEmpty = true;
							for(int i = 0; i < quiverHandler.getSlots(); i++)
							{
								ItemStack arrowStack = quiverHandler.getStackInSlot(i);
								if(!arrowStack.isEmpty())
								{
									isQuiverEmpty = false;
									break;
								}
							}
							
							// Check to see if the opposite hand slot is not empty; attempt to move it somewhere else
							if(!isQuiverEmpty && !oppositeStack.isEmpty() && !quiverInfo.isAmmo(oppositeStack))
							{
								// Find the nearest empty slot...
								int emptySlot = -1;
								for(int i = 0; i < player.inventory.mainInventory.size(); i++)
								{
									ItemStack playerStack = player.inventory.mainInventory.get(i);
									if(playerStack.isEmpty())
									{
										emptySlot = i;
										break;
									}
								}
								// If found, place it in that empty slot
								if(emptySlot != -1)
								{
									String itemId = oppositeStack.getItem().getRegistryName().toString();
									// Store the relevant data to find the offhand item in the Quiver NBT Tag
									NBTTagCompound nbt = quiver.getOrCreateSubCompound(ItemQuiverBase.NBT_OFFHAND_MOVED);
									nbt.setString(ItemQuiverBase.NBT_ITEM_ID, itemId);
									nbt.setInteger(ItemQuiverBase.NBT_ITEM_SLOT, emptySlot);
									
									// Now move the item from the offhand to the appropriate inventory slot
									player.inventory.setInventorySlotContents(emptySlot, oppositeStack);
									player.setItemStackToSlot(oppositeHand, ItemStack.EMPTY);
								}
							}
							
							if(player.getItemStackFromSlot(oppositeHand).isEmpty())
								takeAmmoFromQuiver(player, quiver, oppositeHand);
	
							oppositeStack = player.getItemStackFromSlot(oppositeHand);
							if(!oppositeStack.isEmpty())
								break;
						}
					}
				}
				
				// Ensure that the Mining Fatigue effect with Two-Handed Weapons is removed as soon as either hand is empty.
				/*if(toStack.isEmpty() && player.isPotionActive(MobEffects.MINING_FATIGUE))
				{
					player.removePotionEffect(MobEffects.MINING_FATIGUE);
				}*/
				
				// Check when the weapon is equipped
				// Check when any other item is equipped, then check if the weapon is in the other slot
				// If either are true, then force the item in the inventory
				// Ensure a message is sent if this happens
				/*ItemStack twoHandedStackToCheck = ItemStack.EMPTY;
				ItemStack otherStack = ItemStack.EMPTY;
				EntityEquipmentSlot otherSlot = EnumHand.MAIN_HAND;
				// Check the newly equipped item
				if(toStack.getItem() instanceof IWeaponPropertyContainer)
				{
					twoHandedStackToCheck = toStack;
					otherStack = oppositeStack;
					otherSlot = oppositeHand;
				}
				// Check the opposite item
				else if(oppositeStack.getItem() instanceof IWeaponPropertyContainer)
				{
					twoHandedStackToCheck = oppositeStack;
					otherStack = toStack;
					otherSlot = ev.getSlot();
				}
				if(!twoHandedStackToCheck.isEmpty())
				{
					IWeaponPropertyContainer prop = (IWeaponPropertyContainer)twoHandedStackToCheck.getItem();
					if(prop.hasWeaponProperty(WeaponProperties.TWO_HANDED_2))
					{
						// Force the other stack into the inventory; if it can't fit, then drop it
						player.addItemStackToInventory(otherStack);
					}
				}*/
			}
		}
	}

	/**
	 *  Find the first empty stack in the quiver, then place the arrow item in that slot.
	 * @param player
	 * @param quiver
	 * @param oppositeHandSlot
	 */
	protected static void placeAmmoIntoQuiver(EntityPlayer player, ItemStack quiver, EntityEquipmentSlot oppositeHandSlot)
	{
		if(!quiver.isEmpty() && quiver.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null))
		{
			IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			ItemStack arrowStack = player.getItemStackFromSlot(oppositeHandSlot);
			for(int j = 0; j < quiverHandler.getSlots(); j++)
			{
				arrowStack = quiverHandler.insertItem(j, arrowStack, false);
				if(arrowStack.isEmpty())
					break;
			}
			player.setItemStackToSlot(oppositeHandSlot, arrowStack);
		}
	}
	
	/**
	 * Find the first stack in the quiver, then place it in the opposite hand.
	 * @param player
	 * @param quiver
	 * @param oppositeHandSlot
	 */
	protected static void takeAmmoFromQuiver(EntityPlayer player, ItemStack quiver, EntityEquipmentSlot oppositeHandSlot)
	{
		if(!quiver.isEmpty() && quiver.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null))
		{
			IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			ItemStack arrowStack = ItemStack.EMPTY;
			for(int j = 0; j < quiverHandler.getSlots(); j++)
			{
				arrowStack = quiverHandler.extractItem(j, 64, false);
				if(!arrowStack.isEmpty())
				{
					player.setItemStackToSlot(oppositeHandSlot, arrowStack);
					break;
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerPickupEvent(EntityItemPickupEvent ev)
	{
		// Also play the pick-up sound too, maybe a unique one...
		ItemStack pickedUpStack = ev.getItem().getItem().copy();
		int beforeCount = pickedUpStack.getCount(),
			afterCount = beforeCount;
    	EntityPlayer player = ev.getEntityPlayer();
		List<ItemStack> quivers = QuiverHelper.findValidQuivers(player);
		
		if(!quivers.isEmpty())
		{
			// Loop through all valid quivers to place the item into...
			for(ItemStack quiver : quivers)
			{
				if(!pickedUpStack.isEmpty() && !quiver.isEmpty() && quiver.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null) && ((ItemQuiverBase)quiver.getItem()).isAmmoValid(pickedUpStack, quiver))
				{
					// Make sure auto-collect is enabled.
					if(quiver.hasTagCompound() && quiver.getTagCompound().getBoolean("ammoCollect"))
					{
						// Attempt to place the arrows into the quiver.
						IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
						for(int i = 0; i < quiverHandler.getSlots(); i++)
						{
							pickedUpStack = quiverHandler.insertItem(i, pickedUpStack, false);
							if(pickedUpStack.isEmpty())
								break;
						}
					}
				}
				if(pickedUpStack.isEmpty())
					break;
			}
			afterCount = pickedUpStack.getCount();
			
			if(afterCount < beforeCount)
			{
				player.onItemPickup(ev.getItem(), beforeCount - afterCount);
				ev.getItem().getItem().setCount(afterCount);
				player.world.playSound((EntityPlayer)null, ev.getItem().posX, ev.getItem().posY, ev.getItem().posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, (rand.nextFloat() - rand.nextFloat()) * 0.7F + 0.0F);
			}
		}
		// Merge compatible itemstacks for throwing weapons
		if(pickedUpStack.getItem() instanceof ItemThrowingWeapon)
		{
			boolean pickUpAsNewItem = true;
			boolean removeItems = false;
        	
        	// Find any stack that might fit this item.
        	for(int i = 0; i < player.inventory.getSizeInventory(); i++)
        	{
        		ItemStack slotStack = player.inventory.getStackInSlot(i);
        		if(ItemStack.areItemsEqualIgnoreDurability(slotStack, pickedUpStack) && pickedUpStack.hasTagCompound() && slotStack.hasTagCompound() && 
        				pickedUpStack.getTagCompound().getUniqueId(ItemThrowingWeapon.NBT_UUID).equals(slotStack.getTagCompound().getUniqueId(ItemThrowingWeapon.NBT_UUID)) && 
        				pickedUpStack.getItem() instanceof ItemThrowingWeapon)
    			{
    				int maxAmmo = ((ItemThrowingWeapon)slotStack.getItem()).getMaxAmmo(slotStack);
    				int currentAmmo = maxAmmo - slotStack.getTagCompound().getInteger(ItemThrowingWeapon.NBT_AMMO_USED);
    				boolean currentNotOriginalStack = !slotStack.getTagCompound().getBoolean(ItemThrowingWeapon.NBT_ORIGINAL);
					boolean pickedUpOriginalStack = pickedUpStack.getTagCompound().getBoolean(ItemThrowingWeapon.NBT_ORIGINAL);
    				if(currentAmmo < maxAmmo || (currentNotOriginalStack && pickedUpOriginalStack))
    				{
    					int itemDamage = slotStack.getItemDamage() + pickedUpStack.getItemDamage();
    					
    					// If the total damage exceeds the damage of the equipped stack, then "break" one of the ammo items and not increment the ammo count
    					if(itemDamage > slotStack.getMaxDamage())
    					{
    						player.renderBrokenItemStack(pickedUpStack);
    	            		itemDamage -= slotStack.getMaxDamage() + 1;
    					}
    					else
    					{
    						int pickupMaxAmmo = ((ItemThrowingWeapon)pickedUpStack.getItem()).getMaxAmmo(pickedUpStack);
    						currentAmmo += (pickupMaxAmmo - pickedUpStack.getTagCompound().getInteger(ItemThrowingWeapon.NBT_AMMO_USED));
    						int newMaxAmmo = Math.max(maxAmmo, pickupMaxAmmo);
    						currentAmmo = MathHelper.clamp(currentAmmo, 0, newMaxAmmo);
    						slotStack.getTagCompound().setInteger(ItemThrowingWeapon.NBT_AMMO_USED, newMaxAmmo - currentAmmo);
    						if(currentNotOriginalStack && pickedUpOriginalStack)
    						{
    							slotStack.getTagCompound().setBoolean(ItemThrowingWeapon.NBT_ORIGINAL, pickedUpOriginalStack);
    							
    							// Additionally, restore any enchantments from the original stack
    							if(pickedUpStack.isItemEnchanted())
    							{
	    							Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(pickedUpStack);
	    							EnchantmentHelper.setEnchantments(enchantments, slotStack);
    							}
    						}
    					}
    					slotStack.setItemDamage(itemDamage);
    					pickUpAsNewItem = false;
    					removeItems = true;
    					break;
    				}
        		}
        	}
        	if(pickUpAsNewItem)
        		removeItems = player.inventory.addItemStackToInventory(pickedUpStack);
        	if(removeItems)
        	{
        		player.onItemPickup(ev.getItem(), 1);
        		player.world.playSound((EntityPlayer)null, ev.getItem().posX, ev.getItem().posY, ev.getItem().posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, (rand.nextFloat() - rand.nextFloat()) * 0.7F + 0.0F);
				ev.getItem().getItem().setCount(0);
        	}
		}
	}
	
	@SubscribeEvent
	public static void onLootTableLoad(LootTableLoadEvent ev)
	{
		if(ev.getName().toString().equals("minecraft:chests/village_blacksmith"))
		{
			LootEntry entry = new LootEntryTable(new ResourceLocation(ModSpartanWeaponry.ID, "inject/village_blacksmith"), 1, 1, new LootCondition[0], "spartanweaponry_inject_entry");
			LootPool pool = new LootPool(new LootEntry[] {entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "spartanweaponry_inject_pool");
			ev.getTable().addPool(pool);
		}
	}
	
	/*@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent ev)
	{
		if(ev.getEntity() instanceof EntityZombie)
		{
			EntityZombie entity = (EntityZombie)ev.getEntity();
			float rand = entity.world.rand.nextFloat();
			float chance = entity.world.getDifficulty() == EnumDifficulty.HARD ? 0.5f : 0.25f;
			
			if(rand > 1 - chance)
			{
				ItemStack weapon = ItemStack.EMPTY;
				Item[] possibleWeapons = 
				{
					ItemRegistrySW.daggerIron,
					ItemRegistrySW.longswordIron,
					ItemRegistrySW.katanaIron,
					ItemRegistrySW.saberIron,
					ItemRegistrySW.rapierIron,
					ItemRegistrySW.greatswordIron,
					ItemRegistrySW.hammerIron,
					ItemRegistrySW.warhammerIron,
					ItemRegistrySW.battleaxeIron,
					ItemRegistrySW.maceIron
				};
				
				weapon = generateRandomItem(entity.world, possibleWeapons);
				entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, weapon);
			}
		}
		if(ev.getEntity() instanceof EntitySkeleton)
		{
			EntitySkeleton entity = (EntitySkeleton)ev.getEntity();
			float rand = entity.world.rand.nextFloat();
			float chance = entity.world.getDifficulty() == EnumDifficulty.HARD ? 0.5f : 0.25f;
			
			if(rand > 1 - chance)
			{
				ItemStack weapon = ItemStack.EMPTY;
				Item[] possibleWeapons = 
				{
					ItemRegistrySW.longbowWood,
					ItemRegistrySW.longbowLeather,
					ItemRegistrySW.longbowIron
				};
				
				weapon = generateRandomItem(entity.world, possibleWeapons);
				entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, weapon);
			}
		}
	}
	
	private static ItemStack generateRandomItem(World world, Item[] items)
	{
		float weaponRand = world.rand.nextFloat();
		float divider = 1.0f / items.length;
		int idx = MathHelper.floor(weaponRand / divider);
		idx = idx > items.length - 1 ? items.length - 1 : idx;
		
		return new ItemStack(items[idx]);
	}*/
	
	@SubscribeEvent
	public static void handleAnvilUpdate(AnvilUpdateEvent ev)
	{
		ItemStack left = ev.getLeft();
		ItemStack right = ev.getRight();
		if(left.getItem() instanceof ItemThrowingWeapon && left.hasTagCompound() && left.getTagCompound().getBoolean(ItemThrowingWeapon.NBT_ORIGINAL)
				&& left.isItemEqualIgnoreDurability(right))
		{
			ItemThrowingWeapon throwingWeapon = (ItemThrowingWeapon)left.getItem();
			int leftAmmo = left.getTagCompound().getInteger(ItemThrowingWeapon.NBT_AMMO_USED);
			int rightAmmo = right.getTagCompound().getInteger(ItemThrowingWeapon.NBT_AMMO_USED);
			
			if(leftAmmo == 0)
				return;
			
			// Combine ammo and durability
			int maxAmmo = ((ItemThrowingWeapon)left.getItem()).getMaxAmmo(left);
			int durability = left.getItemDamage() + right.getItemDamage();
			int combinedAmmo = MathHelper.clamp((maxAmmo - leftAmmo) + (maxAmmo - rightAmmo), 0, throwingWeapon.getMaxAmmo(left));
			// Reduce ammo count if combined durability value exceeds maximum durability value
			if(durability > left.getMaxDamage())
			{
				combinedAmmo = Math.max(combinedAmmo - 1, 0);
				durability -= left.getMaxDamage();
			}
			ItemStack resultStack = ev.getLeft().copy();
			resultStack.getTagCompound().setInteger(ItemThrowingWeapon.NBT_AMMO_USED, maxAmmo - combinedAmmo);
			resultStack.setItemDamage(durability);
			
			// Calculate enchantment level to set the XP cost (This should help discourage potential duping)
			Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.getEnchantments(left);
			int cost = 2;	// Base enchantment cost
			
			for(Entry<Enchantment, Integer> enchantment : enchantmentMap.entrySet())
			{
				// Increase cost by one per enchantment level (counts ALL enchantments)
				cost += (enchantment.getValue());
			}
			
			ev.setCost(cost);
			ev.setOutput(resultStack);
		}
	}
}
