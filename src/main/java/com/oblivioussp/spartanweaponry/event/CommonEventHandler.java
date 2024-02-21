package com.oblivioussp.spartanweaponry.event;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.trait.IMeleeTraitCallback;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.init.ModEnchantments;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.item.SwordBaseItem;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;
import com.oblivioussp.spartanweaponry.loot.ModLootTables;
import com.oblivioussp.spartanweaponry.merchant.villager.FletcherTrades;
import com.oblivioussp.spartanweaponry.merchant.villager.WeaponsmithTrades;
import com.oblivioussp.spartanweaponry.util.Config;
import com.oblivioussp.spartanweaponry.util.Log;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper.IQuiverInfo;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEventHandler 
{
	public static Random rand = new Random();
	
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent ev)
	{
		DamageSource src = ev.getSource();
		float dmgDealt = ev.getAmount();
		LivingEntity target = ev.getEntityLiving();
		
//		if(ModSpartanWeaponry.debugMode)
//			Log.info("Damage: Entity: " + target.getDisplayName().getUnformattedComponentText() + " Armour value: " + target.getTotalArmorValue() + " Damage value: " + dmgDealt + " Source: " + src.damageType);			
		
		if(dmgDealt == 0.0f || src.isProjectile() || src.isFireDamage() || src.isExplosion() || src.isMagicDamage() ||
				(!src.getDamageType().equals("player") && !src.getDamageType().equals("mob")))
			return;
		
		// Ensure that the source of damage is direct (not from projectiles, etc)
		if(src.getImmediateSource() == src.getTrueSource() && src.getTrueSource() instanceof LivingEntity && target != null)
		{
			LivingEntity attacker = (LivingEntity)src.getTrueSource();
			
			ItemStack attackerStack = attacker.getHeldItemMainhand();
			ItemStack targetStack = target.getHeldItemMainhand();
			
			if(!attackerStack.isEmpty() && attackerStack.getItem() instanceof IWeaponTraitContainer)
			{
				IWeaponTraitContainer<?> container = (IWeaponTraitContainer<?>)attackerStack.getItem();
				
				for(WeaponTrait trait : container.getAllWeaponTraits())
				{
					IMeleeTraitCallback callback = trait.getMeleeCallback();
					if(callback != null)
						dmgDealt = callback.modifyDamageDealt(container.getMaterial(), dmgDealt, src, attacker, target);
				}
				for(WeaponTrait trait : container.getMaterial().getAllWeaponTraits())
				{
					IMeleeTraitCallback callback = trait.getMeleeCallback();
					if(callback != null)
						dmgDealt = callback.modifyDamageDealt(container.getMaterial(), dmgDealt, src, attacker, target);
				}
			}
			if(attackerStack.getItem() instanceof ThrowingWeaponItem && attackerStack.hasTag() && 
					attackerStack.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED) >= ((ThrowingWeaponItem)attackerStack.getItem()).getMaxAmmo(attackerStack))
			{
				// Only do punching damage when melee attacking using a throwing weapon without ammo
				dmgDealt = 1.0f;
			}
			
			if(!targetStack.isEmpty() && targetStack.getItem() instanceof IWeaponTraitContainer)
			{
				IWeaponTraitContainer<?> container = (IWeaponTraitContainer<?>)targetStack.getItem();
				
				for(WeaponTrait trait : container.getAllWeaponTraits())
				{
					IMeleeTraitCallback callback = trait.getMeleeCallback();
					if(callback != null)
						dmgDealt = callback.modifyDamageTaken(container.getMaterial(), dmgDealt, src, attacker, target);
				}
				for(WeaponTrait trait : container.getMaterial().getAllWeaponTraits())
				{
					IMeleeTraitCallback callback = trait.getMeleeCallback();
					if(callback != null)
						dmgDealt = callback.modifyDamageTaken(container.getMaterial(), dmgDealt, src, attacker, target);
				}
			}
			
			if(dmgDealt != ev.getAmount())
			{
				if(!attacker.world.isRemote) 
				{
					// Emit particles when damage has been enhanced or mitigated, depending on what has happened
					if(dmgDealt > ev.getAmount())
						((ServerWorld)attacker.world).spawnParticle(ParticleTypes.ENCHANTED_HIT, target.getPosX(), target.getPosY() + (target.getHeight() / 2.0f), target.getPosZ(), 16, 0.2d, 0.2d, 0.2d, 0.0d);
					else if(dmgDealt < ev.getAmount())
						((ServerWorld)attacker.world).spawnParticle(ParticleTypes.SMOKE, target.getPosX(), target.getPosY() + (target.getHeight() / 2.0f), target.getPosZ(), 16, 0.2d, 0.2d, 0.2d, 0.0d);
				}
				
				//Log.info(String.format("Changed damage dealt! %f -> %f", ev.getAmount(), dmgDealt));
				ev.setAmount(dmgDealt);
			}
		}
	}
	
	@SubscribeEvent
	public static void attackEvent(LivingAttackEvent ev)
	{
		if(ev.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)ev.getEntityLiving();
			
			if(player.isHandActive() && !player.getActiveItemStack().isEmpty())
			{
				ItemStack activeStack = player.getActiveItemStack();
				
				if(activeStack.getItem() instanceof SwordBaseItem && ((SwordBaseItem)activeStack.getItem()).hasWeaponTrait(WeaponTraits.BLOCK_MELEE))
				{
					DamageSource source = ev.getSource();
					boolean damageItem = false;
					
					// Block Melee attacks only! Explosion, Fire, Magic, Projectile and unblockable damage won't be blocked!
					if(!source.isExplosion() && !source.isFireDamage() && !source.isMagicDamage() && !source.isProjectile() && !source.isUnblockable())
					{
						// Do knockback due to damage.
						Entity trueSourceEntity = source.getTrueSource();
						
                        if (trueSourceEntity instanceof LivingEntity)
                        {
                        	LivingEntity living = (LivingEntity)source.getTrueSource();
                        	living.applyKnockback(0.3F, player.getPosX() - living.getPosX(), player.getPosZ() - living.getPosZ());
                        }
                        damageItem = true;
					}
					if(damageItem)
					{
                        int itemDamage = 1 + MathHelper.floor(ev.getAmount());
                        activeStack.damageItem(itemDamage, player, (playerEntity) -> playerEntity.sendBreakAnimation(playerEntity.getActiveHand()));
                        
                        player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, SoundCategory.PLAYERS, 0.8f, 0.8f);
                        ev.setCanceled(true);                        
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void modifyLootLevel(LootingLevelEvent ev)
	{
		// Null-check the passed damage source itself (fixes issue #150)
		if(ev.getDamageSource() == null)	return;
		
		Entity e = ev.getDamageSource().getImmediateSource();
		
		// Null-check the passed damage source's immediate entity (fixes issue #150)
		if(e != null && e instanceof ThrowingWeaponEntity)
		{
			ThrowingWeaponEntity throwingWeapon = (ThrowingWeaponEntity)e;
			int luckLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.THROWING_LUCK, throwingWeapon.getWeaponStack());
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
		
		if((ev.getSlot() == EquipmentSlotType.MAINHAND || ev.getSlot() == EquipmentSlotType.OFFHAND) && ev.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)ev.getEntityLiving();
			ItemStack fromStack = ev.getFrom();
			ItemStack toStack = ev.getTo();
			EquipmentSlotType oppositeHand;
			ItemStack oppositeStack;
			
			// Get the opposite hand for equipping ammo in.
			if(ev.getSlot() == EquipmentSlotType.OFFHAND)
				oppositeHand = EquipmentSlotType.MAINHAND;
			else
				oppositeHand = EquipmentSlotType.OFFHAND;
			
			oppositeStack = player.getItemStackFromSlot(oppositeHand);
			
			// Check and see if the bow has been unequipped and if the opposite hand slot has valid ammo in it.
			if(!fromStack.isItemEqual(toStack) && !oppositeStack.isEmpty())
			{
				
				// Check that if the item being switched to is blacklisted in the config
				boolean toStackBlacklisted = false;
				String toName = toStack.getItem().getRegistryName().toString();
				
				// If the item being switched to is blacklisted, it will allow the quiver to put the arrows away when equipped
				if(Config.INSTANCE.quiverBowBlacklist.get().contains(toName))
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
							CompoundNBT nbt = quiver.getChildTag(QuiverBaseItem.NBT_OFFHAND_MOVED);
							if(nbt != null)
							{
								String itemId = nbt.getString(QuiverBaseItem.NBT_ITEM_ID);
								int itemSlot = nbt.getInt(QuiverBaseItem.NBT_ITEM_SLOT);
								ItemStack offhandStack = player.inventory.getStackInSlot(itemSlot);
								// Check to see if the item in the slot is a match
								if(offhandStack.getItem().getRegistryName().toString().equals(itemId))
								{
									// Now move the item to the offhand from the appropriate inventory slot
									player.setItemStackToSlot(oppositeHand, offhandStack);
									player.inventory.setInventorySlotContents(itemSlot, ItemStack.EMPTY);
								}
								// Delete the NBT data stored in the quiver, regardless of whether or not anything happened
								quiver.getTag().remove(QuiverBaseItem.NBT_OFFHAND_MOVED);
							}
							break;
						}
					}
				}
			}
			// Check to see if a bow has been equipped
			if(!toStack.isItemEqual(fromStack))
			{
				// Check to see if the weapon being equipped is blacklisted in the config
				String regName = toStack.getItem().getRegistryName().toString();
				// If so, then the quiver will *NOT* take any arrows out. However, arrows will be put into the Quiver
				if(Config.INSTANCE.quiverBowBlacklist.get().contains(regName))
					return;
				
				for(IQuiverInfo quiverInfo : QuiverHelper.info)
				{
					if(quiverInfo.isWeapon(toStack))
					{
						ItemStack quiver = QuiverHelper.findFirstOfType(player, quiverInfo);
						
						// Check to see if the opposite hand slot is not empty; attempt to move it somewhere else
						if(!quiver.isEmpty() && !oppositeStack.isEmpty() && !quiverInfo.isAmmo(oppositeStack))
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
								CompoundNBT nbt = quiver.getOrCreateChildTag(QuiverBaseItem.NBT_OFFHAND_MOVED);
								nbt.putString(QuiverBaseItem.NBT_ITEM_ID, itemId);
								nbt.putInt(QuiverBaseItem.NBT_ITEM_SLOT, emptySlot);
								
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
		}
	}

	/**
	 *  Find the first empty stack in the quiver, then place the arrow item in that slot.
	 * @param player
	 * @param quiver
	 * @param oppositeHandSlot
	 */
	protected static void placeAmmoIntoQuiver(PlayerEntity player, ItemStack quiver, EquipmentSlotType oppositeHandSlot)
	{
		if(!quiver.isEmpty())
		{
			IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(QuiverBaseItem.CAPABILITY_EXCEPTION);
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
	protected static void takeAmmoFromQuiver(PlayerEntity player, ItemStack quiver, EquipmentSlotType oppositeHandSlot)
	{
		if(!quiver.isEmpty())
		{
			IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(QuiverBaseItem.CAPABILITY_EXCEPTION);
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
	public static void onPlayerPickup(EntityItemPickupEvent ev)
	{
		ItemStack pickedUpStack = ev.getItem().getItem().copy();
		int beforeCount = pickedUpStack.getCount(),
			afterCount = beforeCount;
		PlayerEntity player  = ev.getPlayer();
		List<ItemStack> quivers = QuiverHelper.findValidQuivers(player);
		
		if(!quivers.isEmpty())
		{
			// Loop through all valid quivers to place the item into...
			for(ItemStack quiver : quivers)
			{
				if(!pickedUpStack.isEmpty() && !quiver.isEmpty() && ((QuiverBaseItem)quiver.getItem()).isAmmoValid(pickedUpStack, quiver))
				{
					// Make sure auto-collect is enabled.
					if(quiver.getOrCreateTag().getBoolean("ammoCollect"))
					{
						// Attempt to place the arrows into the quiver.
						IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElseThrow(QuiverBaseItem.CAPABILITY_EXCEPTION);
						for(int i = 0; i < quiverHandler.getSlots(); i++)
						{
							pickedUpStack = quiverHandler.insertItem(i, pickedUpStack, false);
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
				player.world.playSound((PlayerEntity)null, ev.getItem().getPosX(), ev.getItem().getPosY(), ev.getItem().getPosZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, (rand.nextFloat() - rand.nextFloat()) * 0.7F + 0.0F);
			}
		}
		// Merge compatible itemstacks for throwing weapons
		if(pickedUpStack.getItem() instanceof ThrowingWeaponItem)
		{
			boolean pickUpAsNewItem = true;
			boolean removeItems = false;
			
        	// Find any stack that might fit this item.
			for(int i = 0; i < player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = player.inventory.getStackInSlot(i);
				if(ItemStack.areItemsEqualIgnoreDurability(slotStack, pickedUpStack) && pickedUpStack.hasTag() && slotStack.hasTag() &&
						slotStack.getTag().hasUniqueId(ThrowingWeaponItem.NBT_UUID) && pickedUpStack.getTag().hasUniqueId(ThrowingWeaponItem.NBT_UUID) &&
						pickedUpStack.getTag().getUniqueId(ThrowingWeaponItem.NBT_UUID).equals(slotStack.getTag().getUniqueId(ThrowingWeaponItem.NBT_UUID)))
				{
					int maxAmmo = ((ThrowingWeaponItem)slotStack.getItem()).getMaxAmmo(slotStack);
					int currentAmmo = maxAmmo - slotStack.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED);
					boolean currentNotOriginalStack = !slotStack.getTag().getBoolean(ThrowingWeaponItem.NBT_ORIGINAL);
					boolean pickedUpOriginalStack = pickedUpStack.getTag().getBoolean(ThrowingWeaponItem.NBT_ORIGINAL);
					
					if(currentAmmo < maxAmmo || (currentNotOriginalStack  && pickedUpOriginalStack))
					{
						int itemDamage = slotStack.getDamage() + pickedUpStack.getDamage();
						
						// If the total damage exceeds the damage of the equipped stack, then "break" one of the ammo items and not increment the ammo count
    					if(itemDamage > slotStack.getMaxDamage())
    					{
    	            		itemDamage -= slotStack.getMaxDamage() + 1;
    					}
    					else
    					{
    						int pickupMaxAmmo = ((ThrowingWeaponItem)pickedUpStack.getItem()).getMaxAmmo(pickedUpStack);
    						int newMaxAmmo = Math.max(maxAmmo, pickupMaxAmmo);
    						currentAmmo += (pickupMaxAmmo - pickedUpStack.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED));
    						currentAmmo = MathHelper.clamp(currentAmmo, 0, newMaxAmmo);
    						slotStack.getTag().putInt(ThrowingWeaponItem.NBT_AMMO_USED, newMaxAmmo - currentAmmo);
    						if(currentNotOriginalStack && pickedUpOriginalStack)
    						{
    							slotStack.getTag().putBoolean(ThrowingWeaponItem.NBT_ORIGINAL, pickedUpOriginalStack);
    							
    							// Additionally, restore any enchantments from the original stack
    							if(pickedUpStack.isEnchanted())
    							{
	    							Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(pickedUpStack);
	    							EnchantmentHelper.setEnchantments(enchantments, slotStack);
    							}
    						}
    					}
    					slotStack.setDamage(itemDamage);
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
        		player.world.playSound((PlayerEntity)null, ev.getItem().getPosX(), ev.getItem().getPosY(), ev.getItem().getPosZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, (rand.nextFloat() - rand.nextFloat()) * 0.7F + 0.0F);
				ev.getItem().getItem().setCount(0);
        	}
		}
	}
	
	@SubscribeEvent
	public static void onLootTableLoad(LootTableLoadEvent ev)
	{
		if(Config.INSTANCE.addIronWeaponsToVillageWeaponsmith.get() && 
				ev.getName().equals(LootTables.CHESTS_VILLAGE_VILLAGE_WEAPONSMITH))
		{
			Log.info("Adding Iron Weapons to the Village Weaponsmith Loot Table!");
			ev.getTable().addPool(generateLootPool(ModLootTables.INJECT_VILLAGE_WEAPONSMITH));
		}
		else if(Config.INSTANCE.addBowAndCrossbowLootToVillageFletcher.get() && 
				ev.getName().equals(LootTables.CHESTS_VILLAGE_VILLAGE_FLETCHER))
		{
			Log.info("Adding Longbow and Heavy Crossbow related loot to the Village Fletcher Loot Table!");
			ev.getTable().addPool(generateLootPool(ModLootTables.INJECT_VILLAGE_FLETCHER));
		}
		else if(Config.INSTANCE.addDiamondWeaponsToEndCity.get() && 
				ev.getName().equals(LootTables.CHESTS_END_CITY_TREASURE))
		{
			Log.info("Adding Diamond Weapons to the End City Treasure Loot Table!");
			ev.getTable().addPool(generateLootPool(ModLootTables.INJECT_END_CITY_TREASURE));
		}
	}
	
	private static LootPool generateLootPool(ResourceLocation lootName)
	{
		return LootPool.builder().addEntry(generateLootEntry(lootName))
						.bonusRolls(0, 1)
						.name(ModSpartanWeaponry.ID + "_inject")
						.build();
	}
	
	private static LootEntry.Builder<?> generateLootEntry(ResourceLocation lootName)
	{
		return TableLootEntry.builder(lootName).weight(1);
	}
	
	@SubscribeEvent
	public static void onJoinWorld(EntityJoinWorldEvent ev)
	{
		if(Config.INSTANCE.disableSpawningZombieWithWeapon.get() && ev.getEntity() instanceof ZombieEntity)
		{
			ZombieEntity entity = (ZombieEntity)ev.getEntity();
			float rand = entity.world.rand.nextFloat();
			float chance = entity.world.getDifficulty() == Difficulty.HARD ? 
					Config.INSTANCE.zombieWithMeleeSpawnChanceHard.get().floatValue() : 
					Config.INSTANCE.zombieWithMeleeSpawnChanceNormal.get().floatValue();
			
			if(rand > 1 - chance)
			{
				ItemStack weapon = ItemStack.EMPTY;
				Item[] possibleWeapons = 
				{
					ModItems.daggers.iron,
					ModItems.longswords.iron,
					ModItems.katanas.iron,
					ModItems.sabers.iron,
					ModItems.rapiers.iron,
					ModItems.greatswords.iron,
					ModItems.battleHammers.iron,
					ModItems.warhammers.iron,
					ModItems.battleaxes.iron,
					ModItems.flangedMaces.iron
				};
				
				weapon = generateRandomItem(entity.world, possibleWeapons);
				
				entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, weapon);
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
	}
	
	@SubscribeEvent
	public static void addVillagerTrades(VillagerTradesEvent ev)
	{
		if(Config.INSTANCE.disableVillagerTrading.get())
			return;
		
		// TODO: Remove items from trades list when they are disabled in the config
		if(ev.getType() == VillagerProfession.WEAPONSMITH)
		{
			List<ITrade> tradesLv1 = ev.getTrades().get(1);
			List<ITrade> tradesLv2 = ev.getTrades().get(2);
			List<ITrade> tradesLv3 = ev.getTrades().get(3);
			List<ITrade> tradesLv4 = ev.getTrades().get(4);
			List<ITrade> tradesLv5 = ev.getTrades().get(5);
			if(!WeaponsmithTrades.LVL1_ITEMS.isEmpty())		tradesLv1.add(WeaponsmithTrades.LVL1_TRADE);
			if(!WeaponsmithTrades.LVL2_ITEMS.isEmpty())		tradesLv2.add(WeaponsmithTrades.LVL2_TRADE);
			if(!WeaponsmithTrades.LVL3_ITEMS.isEmpty())		tradesLv3.add(WeaponsmithTrades.LVL3_TRADE);
			if(!WeaponsmithTrades.LVL4_ITEMS.isEmpty())		tradesLv4.add(WeaponsmithTrades.LVL4_TRADE);
			if(!WeaponsmithTrades.LVL5_ITEMS.isEmpty())		tradesLv5.add(WeaponsmithTrades.LVL5_TRADE);
		}
		else if(ev.getType() == VillagerProfession.FLETCHER)
		{
			List<ITrade> tradesLv1 = ev.getTrades().get(1);
			List<ITrade> tradesLv3 = ev.getTrades().get(3);
			List<ITrade> tradesLv5 = ev.getTrades().get(5);
			if(!Config.INSTANCE.longbows.disableRecipes.get())			tradesLv1.add(FletcherTrades.LONGBOW_WOOD_TRADE);
			if(!Config.INSTANCE.longbows.disableRecipes.get())			tradesLv3.add(FletcherTrades.LONGBOW_IRON_TRADE);
			if(!Config.INSTANCE.heavyCrossbows.disableRecipes.get())	tradesLv3.add(FletcherTrades.HEAVY_CROSSBOW_TRADE);
			if(!Config.INSTANCE.heavyCrossbows.disableRecipes.get())	tradesLv3.add(FletcherTrades.BOLT_TRADE);
			if(!Config.INSTANCE.longbows.disableRecipes.get())			tradesLv5.add(FletcherTrades.ENCHANTED_DIAMOND_LONGBOW_TRADE);
			if(!Config.INSTANCE.heavyCrossbows.disableRecipes.get())	tradesLv5.add(FletcherTrades.ENCHANTED_DIAMOND_HEAVY_CROSSBOW_TRADE);
		}
	}
	
	@SubscribeEvent
	public static void handleAnvilUpdate(AnvilUpdateEvent ev)
	{
		ItemStack left = ev.getLeft();
		ItemStack right = ev.getRight();
		if(left.getItem() instanceof ThrowingWeaponItem && left.hasTag() && left.getTag().getBoolean(ThrowingWeaponItem.NBT_ORIGINAL)
				&& left.isItemEqualIgnoreDurability(right))
		{
			ThrowingWeaponItem throwingWeapon = (ThrowingWeaponItem)left.getItem();
			int leftAmmo = left.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED);
			int rightAmmo = right.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED);
			
			if(leftAmmo == 0)
				return;
			
			// Combine ammo and durability
			int maxAmmo = ((ThrowingWeaponItem)left.getItem()).getMaxAmmo(left);
			int durability = left.getDamage() + right.getDamage();
			int combinedAmmo = MathHelper.clamp((maxAmmo - leftAmmo) + (maxAmmo - rightAmmo), 0, throwingWeapon.getMaxAmmo(left));
			// Reduce ammo count if combined durability value exceeds maximum durability value
			if(durability > left.getMaxDamage())
			{
				combinedAmmo = Math.max(combinedAmmo - 1, 0);
				durability -= left.getMaxDamage();
			}
			ItemStack resultStack = ev.getLeft().copy();
			resultStack.getTag().putInt(ThrowingWeaponItem.NBT_AMMO_USED, maxAmmo - combinedAmmo);
			resultStack.setDamage(durability);
			
			// Calculate enchantment level to set the XP cost (This should help discourage potential duping)
			Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.getEnchantments(left);
			int cost = 1;
			
			for(Entry<Enchantment, Integer> enchantment : enchantmentMap.entrySet())
			{
				cost += (enchantment.getValue());
			}
			
			ev.setCost(cost);
			ev.setOutput(resultStack);
		}
	}
}
