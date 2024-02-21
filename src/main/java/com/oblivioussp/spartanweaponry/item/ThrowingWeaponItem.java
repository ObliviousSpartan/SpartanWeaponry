package com.oblivioussp.spartanweaponry.item;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IReloadable;
import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.ReloadableHandler;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.trait.IThrowingTraitCallback;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.client.ClientHelper;
import com.oblivioussp.spartanweaponry.client.gui.HudCrosshairThrowingWeapon;
import com.oblivioussp.spartanweaponry.client.gui.ICrosshairOverlay;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.init.ModEnchantments;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;
import com.oblivioussp.spartanweaponry.util.ClientConfig;
import com.oblivioussp.spartanweaponry.util.WeaponArchetype;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

public class ThrowingWeaponItem extends Item implements IWeaponTraitContainer<ThrowingWeaponItem>, IReloadable, IHudCrosshair
{
	public static final String NBT_AMMO_USED = "AmmoUsed";
	public static final String NBT_UUID = "UUID";
	public static final String NBT_ORIGINAL = "Original";
	
	protected float attackDamage = 1.0f;
	protected double attackSpeed = 0.0D;
	protected float throwVelocity = 2.0f;
	protected float throwDamageMultiplier = 2.0f;
	protected WeaponMaterial material;
	protected String customDisplayName = null;
	protected boolean doCraftCheck = true;
	protected boolean canBeCrafted = true;
	protected int maxAmmo = 1;
	protected int maxChargeTicks = 5;

	protected Multimap<Attribute, AttributeModifier> modifiers;
	protected final WeaponArchetype archetype;
	
	protected List<WeaponTrait> traits;
	
	public ThrowingWeaponItem(Item.Properties prop, WeaponMaterial materialIn, WeaponArchetype archetypeIn, float weaponBaseDamage, float weaponDamageMultiplier, float weaponSpeed, int maxAmmoCapacity, int chargeTicks)
	{
		super(prop.durability(materialIn.getUses() / 4));
		material = materialIn;
		setAttackDamage(weaponBaseDamage, weaponDamageMultiplier);
		setAttackSpeed(weaponSpeed);
		maxAmmo = maxAmmoCapacity;
		setChargeTicks(chargeTicks);

		if(FMLEnvironment.dist.isClient())
			ClientHelper.registerThrowingWeaponPropertyOverrides(this);
		
		archetype = archetypeIn;
		ReloadableHandler.addToReloadList(this);
	}
	
	public ThrowingWeaponItem(Item.Properties prop, WeaponMaterial material, WeaponArchetype archetypeIn, float weaponBaseDamage, float weaponDamageMultiplier, float weaponSpeed, int maxAmmoCapacity, int chargeTicks, String customDisplayNameIn)
	{
		this(prop, material, archetypeIn, weaponBaseDamage, weaponDamageMultiplier, weaponSpeed, maxAmmoCapacity, chargeTicks);
		if(material.useCustomDisplayName())
			customDisplayName = customDisplayNameIn;
	}

	@Override
	public void reload() 
	{
		ImmutableList.Builder<WeaponTrait> builder = ImmutableList.builder();
		builder.addAll(archetype.getTraits());
		builder.addAll(material.getBonusTraits());
		traits = builder.build();

		// Initialize the weapon's attribute modifier map
		ImmutableMultimap.Builder<Attribute, AttributeModifier> mapBuilder = ImmutableMultimap.builder();
		mapBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)getDirectAttackDamage(), AttributeModifier.Operation.ADDITION));
		mapBuilder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)attackSpeed - 4.0d, AttributeModifier.Operation.ADDITION));
		
		// Add melee attributes from Weapon Traits
		if(traits != null)
			traits.forEach((trait) ->
			{
				trait.getMeleeCallback().ifPresent((callback) -> callback.onModifyAttributesMelee(mapBuilder));
			});
		
		modifiers = mapBuilder.build();

		WeaponTrait extraDamageTrait = getFirstWeaponTraitWithType(WeaponTraits.TYPE_DAMAGE_BONUS_THROWN);
		throwDamageMultiplier = extraDamageTrait != null ? extraDamageTrait.getMagnitude() : 1.0f;
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) 
	{
		return modifiers != null && slot == EquipmentSlot.MAINHAND ? modifiers : super.getAttributeModifiers(slot, stack);
	}
	
	@Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected)
    {
		initNBT(stack, true);
		
		if(entity instanceof LivingEntity)
		{
			LivingEntity living = (LivingEntity)entity;
			
			if(traits != null)
			{
				traits.forEach((trait) -> trait.getMeleeCallback().ifPresent((callback) -> callback.onItemUpdate(material, stack, level, living, itemSlot, isSelected)));
			}
		}
    }

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state)
    {
		return archetype.isBladed() && state.is(Blocks.COBWEB) ? 15.0f : 1.0f;
    }
	
	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos,
			LivingEntity entityLiving)
	{
		// Make the throwing weapon take damage when digging
		if(!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0f)
		{
			damageThrowingWeapon(stack, 2, entityLiving);
		}
		return false;
	}
	
	public void damageThrowingWeapon(ItemStack stack, int damage, LivingEntity entity)
	{
		if(stack.isDamageableItem() && stack.getOrCreateTag().getInt(NBT_AMMO_USED) < getMaxAmmo(stack) && 
				(!(entity instanceof Player) || !((Player)entity).getAbilities().instabuild))
		{
			if(stack.hurt(damage, entity.getRandom(), entity instanceof ServerPlayer ? (ServerPlayer)entity : null))
			{
				InteractionHand breakHand = stack == entity.getOffhandItem() ? InteractionHand.OFF_HAND : stack == entity.getMainHandItem() ? InteractionHand.MAIN_HAND : null;
				if(breakHand != null)
					entity.broadcastBreakEvent(breakHand);
				
				int ammo = stack.getTag().getInt(NBT_AMMO_USED);
				stack.getTag().putInt(NBT_AMMO_USED, ++ammo);
				
				if(entity instanceof Player)
				{
					((Player)entity).awardStat(Stats.ITEM_BROKEN.get(stack.getItem()));
				}
				
				stack.setDamageValue(0);
			}
		}
	}
	
	@Override
	public Component getName(ItemStack stack) 
	{
		if(customDisplayName == null)
			return super.getName(stack);
		return new TranslatableComponent(customDisplayName, material.translateName());
	}

	@Override
	public void appendHoverText(ItemStack stack, Level levelIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		boolean isShiftPressed = Screen.hasShiftDown();

    	if(doCraftCheck && levelIn != null)
    	{
    		if(!ClientConfig.INSTANCE.forceDisableUncraftableTooltips.get() && material.getModId() == ModSpartanWeaponry.ID)
    		{
    			ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
        		if(!tagManager.isKnownTagName(material.getRepairTag()) || tagManager.getTag(material.getRepairTag()).isEmpty())
	    			canBeCrafted = false;
    		}
	    	doCraftCheck = false;
    	}

    	if(!canBeCrafted)
    		tooltip.add(new TranslatableComponent(String.format("tooltip.%s.uncraftable_missing_material", ModSpartanWeaponry.ID), material.getRepairTagName()).withStyle(ChatFormatting.RED));
    	
    	archetype.addTagErrorTooltip(stack, tooltip);
    	material.addTagErrorTooltip(stack, tooltip);
    	
    	if(stack.getOrCreateTag().contains(NBT_ORIGINAL) && !stack.getTag().getBoolean(NBT_ORIGINAL))
    		tooltip.add(new TranslatableComponent(String.format("tooltip.%s.throwable.not_original", ModSpartanWeaponry.ID)).withStyle(ChatFormatting.DARK_RED));
    	if(stack.getTag().hasUUID(NBT_UUID) && flagIn.isAdvanced())
    		tooltip.add(new TextComponent("UUID: " + ChatFormatting.GRAY.toString() + stack.getTag().getUUID(NBT_UUID).toString()).withStyle(ChatFormatting.DARK_PURPLE));
    	int mxAmmo = getMaxAmmo(stack);
    	tooltip.add(new TranslatableComponent(String.format("tooltip.%s.throwable.ammo", ModSpartanWeaponry.ID), new TranslatableComponent(String.format("tooltip.%s.throwable.ammo.value", ModSpartanWeaponry.ID), mxAmmo - stack.getTag().getInt(NBT_AMMO_USED), mxAmmo).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_AQUA));
		tooltip.add(new TranslatableComponent(String.format("tooltip.%s.throwable.charge_time", ModSpartanWeaponry.ID), new TranslatableComponent(String.format("tooltip.%s.throwable.charge_time.value", ModSpartanWeaponry.ID), getMaxChargeTicks(stack) / 20.0f).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_AQUA));
    	
		if(traits != null && !traits.isEmpty())
		{
			if(isShiftPressed)
				tooltip.add(new TranslatableComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".showing_details").withStyle(ChatFormatting.DARK_GRAY)).withStyle(ChatFormatting.GOLD));
			else
				tooltip.add(new TranslatableComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".show_details", ChatFormatting.AQUA.toString() + "SHIFT").withStyle(ChatFormatting.DARK_GRAY)).withStyle(ChatFormatting.GOLD));

			archetype.addTraitsToTooltip(stack, tooltip, isShiftPressed);
			
	    	if(material.hasAnyBonusTraits())
	    	{
	    		tooltip.add(new TranslatableComponent(String.format("tooltip.%s.trait.material_bonus", ModSpartanWeaponry.ID)).withStyle(ChatFormatting.AQUA));
	    		material.getBonusTraits().forEach((trait) -> trait.addTooltip(stack, tooltip, isShiftPressed));
	    	}
		}

		tooltip.add(new TextComponent(""));
		super.appendHoverText(stack, levelIn, tooltip, flagIn);
	}

	@Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
		traits.forEach((trait) -> trait.getMeleeCallback().ifPresent((callback) -> callback.onHitEntity(material, stack, target, attacker, null)));
    	
		// Deal double durability damage when used as a melee weapon
    	if(stack.getOrCreateTag().getInt(NBT_AMMO_USED) < getMaxAmmo(stack))
    		damageThrowingWeapon(stack, 2, attacker);
    	
        return true;
    }
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level levelIn, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		if(stack.getOrCreateTag().getInt(NBT_AMMO_USED) == getMaxAmmo(stack))
			return InteractionResultHolder.fail(stack);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
	}
	
	@Override
	public void releaseUsing(ItemStack stack, Level levelIn, LivingEntity entityLiving, int timeLeft) 
	{
		if(entityLiving instanceof Player)
		{
			int maxAmmo = getMaxAmmo(stack);
			int ammoCount = maxAmmo - stack.getOrCreateTag().getInt(NBT_AMMO_USED);
			
			if(ammoCount > 0)
			{
				Player player = (Player)entityLiving;
	
				int maxCharge = getMaxChargeTicks(stack);
	            int charge = Math.min(getUseDuration(stack) - timeLeft, maxCharge);
				
				if (!levelIn.isClientSide && charge > 2)
		        {
		            ThrowingWeaponEntity thrown = createThrowingWeaponEntity(levelIn, player, stack, charge);
		            float chargePerc = (charge / (float)maxCharge);
		            
		            if(thrown == null)	return;
		            
		            thrown.setWeapon(stack);
		            int velocityBonus = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.PROPEL.get(), stack);
		            thrown.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, throwVelocity * ((velocityBonus * 0.2f) + 1) * (chargePerc * 0.9f + 0.1f), 0.5f);
		            
		            traits.forEach((trait) -> trait.getThrowingCallback().ifPresent((callback) -> callback.onThrowingProjectileSpawn(material, thrown)));

		            double damageMultiplier = (throwDamageMultiplier - 1.0f) * chargePerc + 1.0f;
		            thrown.setBaseDamage((getDirectAttackDamage() + 1.0d) * damageMultiplier);
		            
		            // Apply enchantments as necessary
		            int j = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.RAZORS_EDGE.get(), stack);
		            if (j > 0)
		            {
		            	thrown.setBaseDamage(thrown.getBaseDamage() + j * 0.5D + 0.5D);
		            }
		            if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.INCENDIARY.get(), stack) > 0)
		            {
		            	thrown.setSecondsOnFire(100);
		            }
		            
		            if(player.getAbilities().instabuild)
		            	thrown.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
		            else if(thrown.isValidThrowingWeapon())
		            {
		            	ammoCount--;
		            	stack.getOrCreateTag().putInt(NBT_AMMO_USED, maxAmmo - ammoCount);
		            	
		            	// If there is no ammo left and the stack isn't original (picked up from the ground to create a new stack), then delete the stack
		            	if(ammoCount == 0 && !stack.getTag().getBoolean(NBT_ORIGINAL))
		            	{
			                stack.shrink(1);
			                if(stack.getCount() <= 0)
			                	player.getInventory().removeItem(stack);
		            	}
		            }
		            
		            if(thrown.isValidThrowingWeapon())
		            {
		            	stack.setDamageValue(0);
		            	levelIn.playSound((Player)null, player.getX(), player.getY(), player.getZ(), getThrowingSound(), SoundSource.PLAYERS, 0.5F, 0.4F / (levelIn.random.nextFloat() * 0.4F + 0.8F));
		            	levelIn.addFreshEntity(thrown);
		            }
		            
			        player.awardStat(Stats.ITEM_USED.get(this));
		        }
			}
		}
		super.releaseUsing(stack, levelIn, entityLiving, timeLeft);
	}

	@Override
	public int getUseDuration(ItemStack stack) 
	{
		return 72000;
	}
	
	@Override
	public UseAnim getUseAnimation(ItemStack stack) 
	{
		return UseAnim.SPEAR;
	}

	@Override
	public void onCraftedBy(ItemStack stack, Level levelIn, Player playerIn) 
	{
		traits.forEach((trait) -> trait.getMeleeCallback().ifPresent((callback) -> callback.onCreateItem(material, stack)));
		
		initNBT(stack, true);
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) 
	{
		if(allowdedIn(group))
		{
			ItemStack stack = new ItemStack(this);

			if(traits != null)
				traits.forEach((trait) -> trait.getMeleeCallback().ifPresent((callback) -> callback.onCreateItem(material, stack)));
			
			initNBT(stack, false);
			items.add(stack);
		}
	}

	@Override
	public int getItemEnchantability(ItemStack stack) 
	{
		return material.getEnchantmentValue();
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) 
	{
		// Allow Loyalty enchantments to work on Throwing Weapons
		return super.canApplyAtEnchantingTable(stack, enchantment) || enchantment == Enchantments.LOYALTY;
	}
	
	// IWeaponTraitContainer

	@Override
	public ThrowingWeaponItem getAsItem() 
	{
		return this;
	}
    
	@Override
	public boolean hasWeaponTrait(WeaponTrait prop)
	{
		return traits.contains(prop);
	}

	@Override
	public boolean hasWeaponTraitWithType(String type) 
	{
		return traits.stream().anyMatch((trait) -> trait.getType() == type);
	}

	@Override
	public WeaponTrait getFirstWeaponTraitWithType(String type)
	{
		for(WeaponTrait trait : traits)
		{
			if(trait.getType() == type)
				return trait;
		}
		return null;
	}

	@Override
	public List<WeaponTrait> getAllWeaponTraitsWithType(String type) 
	{
		if(traits.isEmpty())
			return ImmutableList.of();
		
		return traits.stream().filter((trait) -> trait.getType() == type).collect(Collectors.toUnmodifiableList());
	}

	@Override
	public Collection<WeaponTrait> getAllWeaponTraits() 
	{
		// Traits are immutable after reloading anyway so it should be safe to reference them directly
		return traits;
	}

	@Override
	public WeaponMaterial getMaterial()
	{
		return material;
	}
	
	// New methods
	public float getDirectAttackDamage() 
	{
		return attackDamage;
	}

	public void setAttackDamage(float baseDamage, float damageMultiplier)
	{
		attackDamage = (material.getAttackDamageBonus() * damageMultiplier) + baseDamage - 1.0f;
	}
	
	public void setAttackSpeed(double speed)
	{
		attackSpeed = speed;
	}
	
	public void setChargeTicks(int chargeTicks)
	{
		maxChargeTicks = chargeTicks;
	}
	
	public void updateFromConfig(float baseDamage, float damageMultiplier, double speed, int chargeTicks)
	{
		setAttackDamage(baseDamage, damageMultiplier);
		setAttackSpeed(speed);
		setChargeTicks(chargeTicks);
	}
	
	/**
	 * Creates a new Throwing Weapon Entity that is used as a projectile.
	 * @param levelIn The World instance
	 * @param player The Player throwing the weapon
	 * @param stack The Throwing Weapon Item
	 * @param charge The total time (in ticks) that the weapon is held for before throwing it
	 * @return
	 */
	public ThrowingWeaponEntity createThrowingWeaponEntity(Level levelIn, Player player, ItemStack stack, int charge)
	{
		return new ThrowingWeaponEntity(ModEntities.THROWING_WEAPON.get(), player, levelIn);
	}
	
	protected SoundEvent getThrowingSound()
	{
		return ModSounds.THROWN_WEAPON_THROW.get();
	}
	
	protected void initNBT(ItemStack stack, boolean initUUID) 
	{
		CompoundTag tag = stack.getOrCreateTag();
		// Initialise ammo tag if necessary
		if(!tag.contains(NBT_AMMO_USED))
		{
			// And, because I don't think it would be a good idea to transfer the ammo value from the old version to the new one
			// Just fill 'er up
			tag.putInt(NBT_AMMO_USED, 0);
		}
		// Initialise UUID tag if necessary, and flag as original stack
	    if(initUUID && !tag.hasUUID(NBT_UUID))
	    {
			stack.getTag().putUUID(NBT_UUID, UUID.randomUUID());
			stack.getTag().putBoolean(NBT_ORIGINAL, true);
	    }
	}
	
	public int getMaxAmmo(ItemStack stack)
	{
		int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.EXPANSE.get(), stack);
		// Find the value to increase by per level (if ammo increase is too small e.g. Boomerang; then use ammo + 1 per level instead)
		int increasePerLevel = Math.max((int)(maxAmmo * 0.25f), 1);
		return maxAmmo + (increasePerLevel * level);
	}
	
	public int getMaxAmmoBase()
	{
		return maxAmmo;
	}
	
	public int getMaxChargeTicks(ItemStack stack)
	{
		int chargeTicks = (int)(maxChargeTicks * (1 - EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SUPERCHARGE.get(), stack) * 0.2f));
		if(traits != null)
			for(WeaponTrait trait : traits)
			{
				Optional<IThrowingTraitCallback> opt = trait.getThrowingCallback();
				if(opt.isPresent())
					chargeTicks = opt.get().modifyThrowingChargeTime(material, chargeTicks);
			}
		return chargeTicks;
	}
    
    @Override
    public ICrosshairOverlay getCrosshairHudElement() 
    {
    	return HudCrosshairThrowingWeapon::render;
    }
}
