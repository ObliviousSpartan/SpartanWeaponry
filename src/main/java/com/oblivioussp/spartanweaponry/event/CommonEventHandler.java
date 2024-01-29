package com.oblivioussp.spartanweaponry.event;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.OilEffects;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.api.trait.IMeleeTraitCallback;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.capability.IOilHandler;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.init.ModCapabilities;
import com.oblivioussp.spartanweaponry.init.ModCriteriaTriggers;
import com.oblivioussp.spartanweaponry.init.ModEnchantments;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.init.ModMobEffects;
import com.oblivioussp.spartanweaponry.init.ModParticles;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.item.SwordBaseItem;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;
import com.oblivioussp.spartanweaponry.loot.ModLootTables;
import com.oblivioussp.spartanweaponry.merchant.villager.FletcherTrades;
import com.oblivioussp.spartanweaponry.merchant.villager.WeaponsmithTrades;
import com.oblivioussp.spartanweaponry.util.Config;
import com.oblivioussp.spartanweaponry.util.Log;
import com.oblivioussp.spartanweaponry.util.OilHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper.IQuiverInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.SpecialSpawn;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

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
		
		// Debug crap (code doesn't seem to be called anymore, even in an IDE)
//		if(SharedConstants.IS_RUNNING_IN_IDE)
//			Log.info("Damage: Entity: " + target.getDisplayName().getContents() + " Armour value: " + target.getArmorValue() + " Damage value: " + dmgDealt + " Source: " + src.msgId);			
		
		
		if(dmgDealt == 0.0f || src.isProjectile() || src.isFire() || src.isExplosion() || src.isMagic() ||
				(!src.getMsgId().equals("player") && !src.getMsgId().equals("mob")))
			return;
		
		// Ensure that the source of damage is direct (not from projectiles, etc)
		if(src.getDirectEntity() == src.getEntity() && src.getEntity() instanceof LivingEntity && target != null)
		{
			LivingEntity attacker = (LivingEntity)src.getEntity();
			
			ItemStack attackerStack = attacker.getMainHandItem();
			ItemStack targetStack = target.getMainHandItem();

			boolean doTraitDamageParticles = false;
			boolean doOilDamageParticles = false;
			
			if(!attackerStack.isEmpty() && attackerStack.getItem() instanceof IWeaponTraitContainer<?> container)
			{
				float dmgUnmodified = dmgDealt;
				
				for(WeaponTrait trait : container.getAllWeaponTraits())
				{
					Optional<IMeleeTraitCallback> opt = trait.getMeleeCallback();
					if(opt.isPresent())
						dmgDealt = opt.get().modifyDamageDealt(container.getMaterial(), dmgDealt, src, attacker, target);
				}
				
				if(dmgDealt > dmgUnmodified)
					doTraitDamageParticles = true;
			}
			if(attackerStack.getItem() instanceof ThrowingWeaponItem && attackerStack.hasTag() && 
					attackerStack.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED) >= ((ThrowingWeaponItem)attackerStack.getItem()).getMaxAmmo(attackerStack))
				// Only do punching damage when melee attacking using a throwing weapon without ammo
				dmgDealt = 1.0f;
			
			// Apply any valid oil effects
			if(attackerStack.is(ModItemTags.OILABLE_WEAPONS))
			{
				IOilHandler oilHandler = attackerStack.getCapability(ModCapabilities.OIL_CAPABILITY).resolve().get();
				if(oilHandler.isOiled())
				{
					float dmgUnmodified = dmgDealt;
					dmgDealt = oilHandler.useEffect(dmgDealt, attacker.level, target, attacker, attackerStack);
					if(dmgDealt != dmgUnmodified)
						doOilDamageParticles = true;
				}
			}
			
			if(!targetStack.isEmpty() && targetStack.getItem() instanceof IWeaponTraitContainer<?> container)
			{
				for(WeaponTrait trait : container.getAllWeaponTraits())
				{
					Optional<IMeleeTraitCallback> opt = trait.getMeleeCallback();
					if(opt.isPresent())
						dmgDealt = opt.get().modifyDamageTaken(container.getMaterial(), dmgDealt, src, attacker, target);
				}
			}
			
			if(dmgDealt != ev.getAmount())
			{
				if(!attacker.level.isClientSide) 
				{
					// Emit particles when damage has been enhanced or mitigated, depending on what has happened
					if(doTraitDamageParticles && dmgDealt > ev.getAmount())
						((ServerLevel)attacker.level).sendParticles(ModParticles.DAMAGE_BOOSTED.get(), target.getX(), target.getY() + (target.getBbHeight() / 2.0f), target.getZ(), 8, 0.2d, 0.2d, 0.2d, 0.5d);
					else if(dmgDealt < ev.getAmount())
						((ServerLevel)attacker.level).sendParticles(ModParticles.DAMAGE_REDUCED.get(), target.getX(), target.getY() + (target.getBbHeight() / 2.0f), target.getZ(), 8, 0.2d, 0.2d, 0.2d, 0.5d);
					if(doOilDamageParticles)
						((ServerLevel)attacker.level).sendParticles(ModParticles.OIL_DAMAGE_BOOSTED.get(), target.getX(), target.getY() + (target.getBbHeight() / 2.0f), target.getZ(), 8, 0.2d, 0.2d, 0.2d, 0.5d);
				}
				
				//Log.info(String.format("Changed damage dealt! %f -> %f", ev.getAmount(), dmgDealt));
				ev.setAmount(dmgDealt);
			}
		}
	}
	
	@SubscribeEvent
	public static void attackEvent(LivingAttackEvent ev)
	{
		if(ev.getEntityLiving() instanceof Player player && player.isUsingItem() && !player.getUseItem().isEmpty())
		{
			ItemStack activeStack = player.getUseItem();
			
			if(activeStack.getItem() instanceof SwordBaseItem weapon && weapon.hasWeaponTrait(WeaponTraits.BLOCK_MELEE.get()))
			{
				DamageSource source = ev.getSource();
				boolean damageItem = false;
				
				// Block Melee attacks only! Explosion, Fire, Magic, Projectile and unblockable damage won't be blocked!
				if(!source.isExplosion() && !source.isFire() && !source.isMagic() && !source.isProjectile() && !source.isBypassArmor())
				{
					// Do knockback due to damage.
					Entity trueSourceEntity = source.getEntity();
					
                    if (trueSourceEntity instanceof LivingEntity)
                    {
                    	LivingEntity living = (LivingEntity)source.getEntity();
                    	living.knockback(0.3F, player.getX() - living.getX(), player.getZ() - living.getZ());
                    }
                    damageItem = true;
				}
				if(damageItem)
				{
                    int itemDamage = 1 + Mth.floor(ev.getAmount());
                    activeStack.hurtAndBreak(itemDamage, player, (playerEntity) -> playerEntity.broadcastBreakEvent(playerEntity.getUsedItemHand()));
                    player.level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_KNOCKBACK, SoundSource.PLAYERS, 0.8f, 0.8f);
                    ev.setCanceled(true);                        
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void modifyLootLevel(LootingLevelEvent ev)
	{
		// Null-check the passed damage source itself (fixes issue #150)
		if(ev.getDamageSource() == null)	return;
		
		Entity e = ev.getDamageSource().getDirectEntity();
		
		// Null-check the passed damage source's immediate entity (fixes issue #150)
		if(e != null && e instanceof ThrowingWeaponEntity throwingWeapon)
		{
			int luckLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.LUCKY_THROW.get(), throwingWeapon.getWeaponItem());
			ev.setLootingLevel(luckLevel);
		}
	}
	
	@SubscribeEvent
	public static void onEquipmentChange(LivingEquipmentChangeEvent ev)
	{
		// What should be done.
		// - Check to see if a Bow or Heavy Crossbow of any kind is equipped, in main-hand or off-hand.
		// - If true, get the opposite hand of the equipped weapon, and check if it's empty
		// - If it's empty, check to see if the appropriate quiver is in either the hotbar or the appropriate Curios slot.
		// - If found, then place a stack of ammo in the appropriate opposite hand slot, and take that ammo from the quiver.
		// -- If the weapon is unequiped, do the opposite and place the arrow stack back into the quiver as appropriate.
		
		if((ev.getSlot() == EquipmentSlot.MAINHAND || ev.getSlot() == EquipmentSlot.OFFHAND) && ev.getEntityLiving() instanceof Player player)
		{
			ItemStack fromStack = ev.getFrom();
			ItemStack toStack = ev.getTo();
			EquipmentSlot oppositeHand;
			ItemStack oppositeStack;
			
			// Get the opposite hand for equipping ammo in.
			if(ev.getSlot() == EquipmentSlot.OFFHAND)
				oppositeHand = EquipmentSlot.MAINHAND;
			else
				oppositeHand = EquipmentSlot.OFFHAND;
			
			oppositeStack = player.getItemBySlot(oppositeHand);
			
			// Check and see if the bow has been unequipped and if the opposite hand slot has valid ammo in it.
			if(!ItemStack.isSame(fromStack, toStack) && !oppositeStack.isEmpty())
			{
				// Check if the item being switched to is blacklisted in the config
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

						oppositeStack = player.getItemBySlot(oppositeHand);
						if(oppositeStack.isEmpty())
						{
							// If there is any offhand item data in the quiver, find it and put it back
							CompoundTag nbt = quiver.getTagElement(QuiverBaseItem.NBT_OFFHAND_MOVED);
							if(nbt != null)
							{
								String itemId = nbt.getString(QuiverBaseItem.NBT_ITEM_ID);
								int itemSlot = nbt.getInt(QuiverBaseItem.NBT_ITEM_SLOT);
								ItemStack offhandStack = player.getInventory().getItem(itemSlot);
								// Check to see if the item in the slot is a match
								if(offhandStack.getItem().getRegistryName().toString().equals(itemId))
								{
									// Now move the item to the offhand from the appropriate inventory slot
									player.setItemSlot(oppositeHand, offhandStack);
									player.getInventory().setItem(itemSlot, ItemStack.EMPTY);
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
			if(!ItemStack.isSame(toStack, fromStack))
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
						
						if(!quiver.isEmpty())
						{
							// TODO: Possibly make an isEmpty() method for a custom item handler
							IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).resolve().orElseThrow();
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
								for(int i = 0; i < player.getInventory().items.size(); i++)
								{
									ItemStack playerStack = player.getInventory().items.get(i);
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
									CompoundTag nbt = quiver.getOrCreateTagElement(QuiverBaseItem.NBT_OFFHAND_MOVED);
									nbt.putString(QuiverBaseItem.NBT_ITEM_ID, itemId);
									nbt.putInt(QuiverBaseItem.NBT_ITEM_SLOT, emptySlot);
									
									// Now move the item from the offhand to the appropriate inventory slot
									player.getInventory().setItem(emptySlot, oppositeStack);
									player.setItemSlot(oppositeHand, ItemStack.EMPTY);
								}
							}
							
							if(player.getItemBySlot(oppositeHand).isEmpty())
								takeAmmoFromQuiver(player, quiver, oppositeHand);
	
							oppositeStack = player.getItemBySlot(oppositeHand);
							if(!oppositeStack.isEmpty())
								break;
						}
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
	protected static void placeAmmoIntoQuiver(Player player, ItemStack quiver, EquipmentSlot oppositeHandSlot)
	{
		if(!quiver.isEmpty())
		{
			IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).resolve().orElseThrow();
			ItemStack arrowStack = player.getItemBySlot(oppositeHandSlot);
			
			int prioritySlot = quiver.getOrCreateTag().getInt(QuiverBaseItem.NBT_PROIRITY_SLOT);
			arrowStack = quiverHandler.insertItem(prioritySlot, arrowStack, false);
			if(!arrowStack.isEmpty())
			{
				for(int j = 0; j < quiverHandler.getSlots(); j++)
				{
					if(j == prioritySlot)	
						continue;	// Skip the priority slot, since it's been checked already
					
					arrowStack = quiverHandler.insertItem(j, arrowStack, false);
					if(arrowStack.isEmpty())
						break;
				}
			}
			player.setItemSlot(oppositeHandSlot, arrowStack);
		}
	}
	
	/**
	 * Find the first stack in the quiver, then place it in the opposite hand.
	 * @param player
	 * @param quiver
	 * @param oppositeHandSlot
	 */
	protected static void takeAmmoFromQuiver(Player player, ItemStack quiver, EquipmentSlot oppositeHandSlot)
	{
		if(!quiver.isEmpty())
		{
			IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).resolve().orElseThrow();
			ItemStack arrowStack = ItemStack.EMPTY;
			
			int prioritySlot = quiver.getOrCreateTag().getInt(QuiverBaseItem.NBT_PROIRITY_SLOT);
			arrowStack = quiverHandler.extractItem(prioritySlot, 64, false);
			if(!arrowStack.isEmpty())
			{
				player.setItemSlot(oppositeHandSlot, arrowStack);
				return;
			}
			
			for(int j = 0; j < quiverHandler.getSlots(); j++)
			{
				if(j == prioritySlot)	
					continue;	// Skip the priority slot, since it's been checked already
				
				arrowStack = quiverHandler.extractItem(j, 64, false);
				if(!arrowStack.isEmpty())
				{
					player.setItemSlot(oppositeHandSlot, arrowStack);
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
		Player player  = ev.getPlayer();
		List<ItemStack> quivers = QuiverHelper.findValidQuivers(player);
		
		if(!quivers.isEmpty())
		{
			// Loop through all valid quivers to place the item into...
			for(ItemStack quiver : quivers)
			{
				if(!pickedUpStack.isEmpty() && !quiver.isEmpty() && ((QuiverBaseItem)quiver.getItem()).isAmmoValid(pickedUpStack, quiver))
				{
					// Make sure auto-collect is enabled.
					if(quiver.getOrCreateTag().getBoolean(QuiverBaseItem.NBT_AMMO_COLLECT))
					{
						// Attempt to place the arrows into the quiver.
						IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).resolve().orElseThrow();
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
				player.take(ev.getItem(), beforeCount - afterCount);
				ev.getItem().getItem().setCount(afterCount);
				player.level.playSound((Player)null, ev.getItem().getX(), ev.getItem().getY(), ev.getItem().getZ(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F, (rand.nextFloat() - rand.nextFloat()) * 0.7F + 0.0F);
			}
		}
		// Merge compatible itemstacks for throwing weapons
		if(pickedUpStack.getItem() instanceof ThrowingWeaponItem throwingWeapon)
		{
			boolean pickUpAsNewItem = true;
			boolean removeItems = false;
			
        	// Find any stack that might fit this item.
			for(int i = 0; i < player.getInventory().getContainerSize(); i++)
			{
				ItemStack slotStack = player.getInventory().getItem(i);
				if(ItemStack.isSameIgnoreDurability(slotStack, pickedUpStack) && pickedUpStack.hasTag() && slotStack.hasTag() &&
						slotStack.getTag().hasUUID(ThrowingWeaponItem.NBT_UUID) && pickedUpStack.getTag().hasUUID(ThrowingWeaponItem.NBT_UUID) &&
						pickedUpStack.getTag().getUUID(ThrowingWeaponItem.NBT_UUID).equals(slotStack.getTag().getUUID(ThrowingWeaponItem.NBT_UUID)))
				{
					int maxAmmo = throwingWeapon.getMaxAmmo(slotStack);
					int currentAmmo = maxAmmo - slotStack.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED);
					boolean currentNotOriginalStack = !slotStack.getTag().getBoolean(ThrowingWeaponItem.NBT_ORIGINAL);
					boolean pickedUpOriginalStack = pickedUpStack.getTag().getBoolean(ThrowingWeaponItem.NBT_ORIGINAL);
					
					if(currentAmmo < maxAmmo || (currentNotOriginalStack  && pickedUpOriginalStack))
					{
						int itemDamage = slotStack.getDamageValue() + pickedUpStack.getDamageValue();
						
						// If the total damage exceeds the damage of the equipped stack, then "break" one of the ammo items and not increment the ammo count
    					if(itemDamage > slotStack.getMaxDamage())
    					{
    						// TODO: Reimplement broken item particles
//    						this.playSound(SoundEvents.ENTITY_ITEM_BREAK, 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);
//    						player.renderBrokenItemStack(pickedUpStack);
    	            		itemDamage -= slotStack.getMaxDamage() + 1;
    					}
    					else
    					{
    						currentAmmo += (maxAmmo - pickedUpStack.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED));
    						currentAmmo = Mth.clamp(currentAmmo, 0, maxAmmo);
    						slotStack.getTag().putInt(ThrowingWeaponItem.NBT_AMMO_USED, maxAmmo - currentAmmo);
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
    					slotStack.setDamageValue(itemDamage);
    					pickUpAsNewItem = false;
    					removeItems = true;
    					break;
					}
				}
			}
        	if(pickUpAsNewItem)
        		removeItems = player.getInventory().add(pickedUpStack);
        	if(removeItems)
        	{
        		player.take(player, 1);
        		player.level.playSound((Player)null, ev.getItem().getX(), ev.getItem().getY(), ev.getItem().getZ(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F, (rand.nextFloat() - rand.nextFloat()) * 0.7F + 0.0F);
				ev.getItem().getItem().setCount(0);
        	}
		}
	}
	
	/**
	 * Inject loot tables with weapons from this mod
	 * @param ev
	 */
	@SubscribeEvent
	public static void onLootTableLoad(LootTableLoadEvent ev)
	{
		if(Config.INSTANCE.addIronWeaponsToVillageWeaponsmith.get() && 
				ev.getName().equals(BuiltInLootTables.VILLAGE_WEAPONSMITH))
		{
			Log.info("Adding Iron Weapons to the Village Weaponsmith Loot Table!");
			ev.getTable().addPool(generateLootPool(ModLootTables.INJECT_VILLAGE_WEAPONSMITH));
		}
		else if(Config.INSTANCE.addBowAndCrossbowLootToVillageFletcher.get() && 
				ev.getName().equals(BuiltInLootTables.VILLAGE_FLETCHER))
		{
			Log.info("Adding Longbow and Heavy Crossbow related loot to the Village Fletcher Loot Table!");
			ev.getTable().addPool(generateLootPool(ModLootTables.INJECT_VILLAGE_FLETCHER));
		}
		else if(Config.INSTANCE.addDiamondWeaponsToEndCity.get() && 
				ev.getName().equals(BuiltInLootTables.END_CITY_TREASURE))
		{
			Log.info("Adding Diamond Weapons to the End City Treasure Loot Table!");
			ev.getTable().addPool(generateLootPool(ModLootTables.INJECT_END_CITY_TREASURE));
		}
	}
	
	private static LootPool generateLootPool(ResourceLocation lootName)
	{
		return LootPool.lootPool().add(generateLootEntry(lootName))
						.setBonusRolls(UniformGenerator.between(0, 1))
						.name(ModSpartanWeaponry.ID + "_inject")
						.build();
	}
	
	private static LootPoolEntryContainer.Builder<?> generateLootEntry(ResourceLocation lootName)
	{
		return LootTableReference.lootTableReference(lootName).setWeight(1);
	}
	
	/**
	 * Allow Mobs to spawn with weapons from this mod; Zombies with most melee weapons and Skeletons with Longbows
	 * @param ev
	 */
	@SubscribeEvent
	public static void onJoinWorld(SpecialSpawn ev)
	{
		if(!Config.INSTANCE.disableSpawningZombieWithWeapon.get() && ev.getEntity() instanceof Zombie zombie)
		{
			float rand = zombie.level.random.nextFloat();
			float chance = zombie.level.getDifficulty() == Difficulty.HARD ? 
					Config.INSTANCE.zombieWithMeleeSpawnChanceHard.get().floatValue() : 
					Config.INSTANCE.zombieWithMeleeSpawnChanceNormal.get().floatValue();
			
			if(rand > 1 - chance)
			{
				ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(ModItemTags.ZOMBIE_SPAWN_WEAPONS);
				if(!tag.isEmpty())
				{
					ItemStack weapon = ItemStack.EMPTY;
					List<Item> possibleWeapons = tag.stream().toList();
					
					weapon = generateRandomItem(zombie.level, possibleWeapons);
				
					zombie.setItemSlot(EquipmentSlot.MAINHAND, weapon);
				}
			}
		}
		if(!Config.INSTANCE.disableSpawningSkeletonWithLongbow.get() && ev.getEntity() instanceof AbstractSkeleton skeleton)
		{
			float rand = skeleton.level.random.nextFloat();
			float chance = skeleton.level.getDifficulty() == Difficulty.HARD ? 
					Config.INSTANCE.skeletonWithLongbowSpawnChanceHard.get().floatValue() : 
					Config.INSTANCE.skeletonWithLongbowSpawnChanceNormal.get().floatValue();
			
			if(rand > 1 - chance)
			{
				ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(ModItemTags.SKELETON_SPAWN_LONGBOWS);
				if(!tag.isEmpty())
				{
					ItemStack weapon = ItemStack.EMPTY;
					List<Item> possibleWeapons = tag.stream().toList();
					weapon = generateRandomItem(skeleton.level, possibleWeapons);
					skeleton.setItemSlot(EquipmentSlot.MAINHAND, weapon);
				}
			}
		}
	}
	
	private static ItemStack generateRandomItem(Level level, List<Item> items)
	{
		float weaponRand = level.random.nextFloat();
		float divider = 1.0f / items.size();
		int idx = Mth.floor(weaponRand / divider);
		idx = idx > items.size() - 1 ? items.size() - 1 : idx;
		
		return new ItemStack(items.get(idx));
	}
	
	@SubscribeEvent
	public static void addVillagerTrades(VillagerTradesEvent ev)
	{
		if(Config.INSTANCE.disableVillagerTrading.get())
			return;
		
		if(ev.getType() == VillagerProfession.WEAPONSMITH)
		{
			List<ItemListing> tradesLv1 = ev.getTrades().get(1);
			List<ItemListing> tradesLv2 = ev.getTrades().get(2);
			List<ItemListing> tradesLv3 = ev.getTrades().get(3);
			List<ItemListing> tradesLv4 = ev.getTrades().get(4);
			List<ItemListing> tradesLv5 = ev.getTrades().get(5);
			if(!WeaponsmithTrades.LVL1_ITEMS.isEmpty())		tradesLv1.add(WeaponsmithTrades.LVL1_TRADE);
			if(!WeaponsmithTrades.LVL2_ITEMS.isEmpty())		tradesLv2.add(WeaponsmithTrades.LVL2_TRADE);
			if(!WeaponsmithTrades.LVL3_ITEMS.isEmpty())		tradesLv3.add(WeaponsmithTrades.LVL3_TRADE);
			if(!WeaponsmithTrades.LVL4_ITEMS.isEmpty())		tradesLv4.add(WeaponsmithTrades.LVL4_TRADE);
			if(!WeaponsmithTrades.LVL5_ITEMS.isEmpty())		tradesLv5.add(WeaponsmithTrades.LVL5_TRADE);
		}
		else if(ev.getType() == VillagerProfession.FLETCHER)
		{
			List<ItemListing> tradesLv1 = ev.getTrades().get(1);
			List<ItemListing> tradesLv3 = ev.getTrades().get(3);
			List<ItemListing> tradesLv5 = ev.getTrades().get(5);
			if(!Config.INSTANCE.longbows.disableRecipes.get())			tradesLv1.add(FletcherTrades.LONGBOW_WOOD_TRADE);
			if(!Config.INSTANCE.longbows.disableRecipes.get())			tradesLv3.add(FletcherTrades.LONGBOW_IRON_TRADE);
			if(!Config.INSTANCE.heavyCrossbows.disableRecipes.get())	tradesLv3.add(FletcherTrades.HEAVY_CROSSBOW_TRADE);
			if(!Config.INSTANCE.heavyCrossbows.disableRecipes.get())	tradesLv3.add(FletcherTrades.BOLT_TRADE);
			if(!Config.INSTANCE.longbows.disableRecipes.get())			tradesLv5.add(FletcherTrades.ENCHANTED_DIAMOND_LONGBOW_TRADE);
			if(!Config.INSTANCE.heavyCrossbows.disableRecipes.get())	tradesLv5.add(FletcherTrades.ENCHANTED_DIAMOND_HEAVY_CROSSBOW_TRADE);
		}
	}
	
	/**
	 * Events to supress Ender Teleportation using the Ender Disruption Mob Effect
	 */
	@SubscribeEvent
	public static void onEnderTeleport(EntityTeleportEvent.EnderEntity ev)
	{
		ev.setCanceled(checkToCancelTeleport(ev.getEntityLiving()));
	}
	
	@SubscribeEvent
	public static void onEnderTeleport(EntityTeleportEvent.EnderPearl ev)
	{
		ev.setCanceled(checkToCancelTeleport(ev.getPlayer()));
	}
	
	@SubscribeEvent
	public static void onEnderTeleport(EntityTeleportEvent.ChorusFruit ev)
	{
		ev.setCanceled(checkToCancelTeleport(ev.getEntityLiving()));
	}
	
	public static boolean checkToCancelTeleport(LivingEntity teleportingEntity)
	{
		return teleportingEntity.hasEffect(ModMobEffects.ENDER_DISRPUTION.get());
	}
	
	/**
	 * Repair Throwing Weapons with other Throwing Weapons of the same type<br>
	 * Allows replenishing of ammo for enchanted Throwing Weapons at an XP cost per enchantment
	 * @param ev
	 */
	@SubscribeEvent
	public static void handleAnvilUpdate(AnvilUpdateEvent ev)
	{
		ItemStack left = ev.getLeft();
		ItemStack right = ev.getRight();
		if(left.getItem() instanceof ThrowingWeaponItem && left.hasTag() && left.getTag().getBoolean(ThrowingWeaponItem.NBT_ORIGINAL)
				&& ItemStack.isSameIgnoreDurability(left, right))
		{
			ThrowingWeaponItem throwingWeapon = (ThrowingWeaponItem)left.getItem();
			int leftAmmo = left.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED);
			int rightAmmo = right.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED);
			
			if(leftAmmo == 0)	// Used ammo is zero when ammo is full
				return;
			
			// Combine ammo and durability
			int maxAmmo = ((ThrowingWeaponItem)left.getItem()).getMaxAmmo(left);
			int durability = left.getDamageValue() + right.getDamageValue();
			int combinedAmmo = Mth.clamp((maxAmmo - leftAmmo) + (maxAmmo - rightAmmo), 0, throwingWeapon.getMaxAmmo(left));
			// Reduce ammo count if combined durability value exceeds maximum durability value
			if(durability > left.getMaxDamage())
			{
				combinedAmmo = Math.max(combinedAmmo - 1, 0);
				durability -= left.getMaxDamage();
			}
			ItemStack resultStack = ev.getLeft().copy();
			resultStack.getTag().putInt(ThrowingWeaponItem.NBT_AMMO_USED, maxAmmo - combinedAmmo);
			resultStack.setDamageValue(durability);
			
			// Calculate enchantment level to set the XP cost (This should help discourage potential duping)
			Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.getEnchantments(left);
			int cost = 1;	// 1 Level of cost per total levels of enchantment
			
			for(Entry<Enchantment, Integer> enchantment : enchantmentMap.entrySet())
			{
				cost += (enchantment.getValue());
			}
			
			ev.setCost(cost);
			ev.setOutput(resultStack);
		}
	}
	
	/**
	 * Trigger Oil brewing Advancement when appropriate
	 * @param ev
	 */
	@SubscribeEvent
	public static void onBrewPotion(PlayerBrewedPotionEvent ev)
	{
		ItemStack stack = ev.getStack();
		OilEffect oil = OilEffects.NONE.get();
		if(stack.is(ModItems.WEAPON_OIL.get()) && (oil = OilHelper.getOilFromStack(stack)) != OilEffects.NONE.get())
			ModCriteriaTriggers.BREW_OIL.trigger((ServerPlayer)ev.getPlayer(), oil);
	}
	
	/**
	 * Simple Handle in-world conversion -> Stick in hand + Use on Grass
	 */
	
	// Conversion of blocks when Simple Handles are crafted. Tall blocks turn into their smaller variants
	private static ImmutableMap<Block, Block> conversionMap = ImmutableMap.of(Blocks.GRASS, Blocks.AIR,
																Blocks.TALL_GRASS, Blocks.GRASS,
																Blocks.SEAGRASS, Blocks.WATER,
																Blocks.TALL_SEAGRASS, Blocks.SEAGRASS,
																Blocks.FERN, Blocks.AIR,
																Blocks.LARGE_FERN, Blocks.FERN);
	
	@SubscribeEvent
	public static void onItemRightClick(PlayerInteractEvent.RightClickBlock ev)
	{
		// Skip if the item is not some form of stick or if the stick is on a cooldown
		Player player = ev.getPlayer();
		InteractionHand hand = ev.getHand();
		ItemStack stack = player.getItemInHand(hand);
		if(!stack.is(Tags.Items.RODS_WOODEN) || player.getCooldowns().isOnCooldown(stack.getItem()))
			return;

		Level level = ev.getWorld();
		BlockPos pos = ev.getPos();
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();
		Block blockTo;
		
		// Check the conversion map to determine what the block turns into
		if((blockTo = conversionMap.get(block)) != null)
		{
			if(block == Blocks.TALL_GRASS || block == Blocks.TALL_SEAGRASS || block == Blocks.LARGE_FERN)
			{
				// Check to see what half of the tall block has been clicked to get the proper block to convert
				if(state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER)
				{
					pos = pos.below();
					state = ev.getWorld().getBlockState(pos);
				}
			}
			// Remove an item of the main stack
			player.getCooldowns().addCooldown(stack.getItem(), 5);
			stack.shrink(1);
			if(stack.getCount() <= 0)
			{
				stack = ItemStack.EMPTY;
				player.setItemInHand(hand, stack);
			}
			// Now spawn the converted item on the ground
			stack = new ItemStack(ModItems.SIMPLE_HANDLE.get());
			Block.popResource(level, pos, stack);
			
			// Now change the block appropriately
			level.setBlock(pos, blockTo.defaultBlockState(), 35);
			level.levelEvent(player, 2001, pos, Block.getId(state));
			ev.setCancellationResult(InteractionResult.CONSUME);
		}
	}
}
