package com.oblivioussp.spartanweaponry.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.trait.IMeleeTraitCallback;
import com.oblivioussp.spartanweaponry.api.trait.IThrowingTraitCallback;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.client.ClientHelper;
import com.oblivioussp.spartanweaponry.client.gui.HudElementCrosshair;
import com.oblivioussp.spartanweaponry.client.gui.HudElementCrosshairThrowingWeapon;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.init.ModEnchantments;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class ThrowingWeaponItem extends Item implements IWeaponTraitContainer<ThrowingWeaponItem>, IHudCrosshair
{
	public static final String NBT_AMMO_USED = "AmmoUsed";
	public static final String NBT_UUID = "UUID";
	public static final String NBT_ORIGINAL = "Original";
	
	protected float attackDamage = 1.0f;
	protected double attackSpeed = 0.0D;
	protected float throwVelocity = 2.0f;
	protected float throwDamageMultiplier = 2.0f;
	protected List<WeaponTrait> traits;
	protected WeaponMaterial material;
	protected String customDisplayName = null;
	protected boolean doCraftCheck = true;
	protected boolean canBeCrafted = true;
	protected int maxAmmo = 1;
	protected int maxChargeTicks = 5;
	
	public ThrowingWeaponItem(String regName, Item.Properties prop, WeaponMaterial material, float weaponBaseDamage, float weaponDamageMultiplier, float weaponSpeed, int maxAmmoCapacity, int chargeTicks, boolean usingDeferredRegister, WeaponTrait... traits)
	{
		super(prop.maxDamage(material.getMaxUses() / 4));
		if(!usingDeferredRegister)
			setRegistryName(regName);
		this.material = material;
		setAttackDamage(weaponBaseDamage, weaponDamageMultiplier);
		setAttackSpeed(weaponSpeed);
		maxAmmo = maxAmmoCapacity;
		setChargeTicks(chargeTicks);
		this.traits = new ArrayList<WeaponTrait>();
		this.traits.addAll(Arrays.asList(traits));

		WeaponTrait extraDamageTrait = this.getFirstWeaponTraitWithType(WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_THROWN);
		this.throwDamageMultiplier = extraDamageTrait != null ? extraDamageTrait.getMagnitude() : 1.0f;

		if(FMLEnvironment.dist.isClient())
			ClientHelper.registerThrowingWeaponPropertyOverrides(this);
			
		/*this.addPropertyOverride(new ResourceLocation("throwing"), (stack, world, living) ->
		{
			if(living == null || !stack.isItemEqual(living.getActiveItemStack()))	return 0.0f;
			return living.getItemInUseCount() > 0 ? 1.0f : 0.0f;
		});*/
	}
	
	public ThrowingWeaponItem(String regName, Item.Properties prop, WeaponMaterial material, float weaponBaseDamage, float weaponDamageMultiplier, float weaponSpeed, int maxAmmoCapacity, int chargeTicks, String customDisplayName, boolean usingDeferredRegister, WeaponTrait... weaponTraits)
	{
		this(regName, prop, material, weaponBaseDamage, weaponDamageMultiplier, weaponSpeed, maxAmmoCapacity, chargeTicks, usingDeferredRegister, weaponTraits);
		if(material.useCustomDisplayName())
			this.customDisplayName = customDisplayName;
	}
	
	/*@Override
	public int getMaxDamage(ItemStack stack) 
	{
		return material.getMaxUses();
	}*/
	
	@Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
    {
		initNBT(stack, true);
		
		if(entity instanceof LivingEntity)
		{
			LivingEntity living = (LivingEntity)entity;
			
			if(traits != null)
			{
				for(WeaponTrait trait : traits)
				{
					IMeleeTraitCallback callback = trait.getMeleeCallback();
					if(callback != null)
						callback.onItemUpdate(material, stack, world, living, itemSlot, isSelected);
				}
			}
			if(material.hasAnyWeaponTrait())
			{
				for(WeaponTrait property : material.getAllWeaponTraits())
				{
					IMeleeTraitCallback callback = property.getMeleeCallback();
					if(callback != null)
						callback.onItemUpdate(material, stack, world, living, itemSlot, isSelected);
				}
			}
		}
    }
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos,
			LivingEntity entityLiving)
	{
		// Make the throwing weapon take damage when digging
		if(!world.isRemote && state.getBlockHardness(world, pos) != 0.0f)
		{
			damageThrowingWeapon(stack, 2, entityLiving);
		}
		return false;
//		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}
	
	public void damageThrowingWeapon(ItemStack stack, int damage, LivingEntity entity)
	{
		//stack.damageItem(damage, entity);
		if(stack.isDamageable() && stack.getOrCreateTag().getInt(NBT_AMMO_USED) < getMaxAmmo(stack) && 
				(!(entity instanceof PlayerEntity) || !((PlayerEntity)entity).abilities.isCreativeMode))
		{
			if(stack.attemptDamageItem(damage, entity.getRNG(), entity instanceof ServerPlayerEntity ? (ServerPlayerEntity)entity : null))
			{
				Hand breakHand = stack == entity.getHeldItemOffhand() ? Hand.OFF_HAND : stack == entity.getHeldItemMainhand() ? Hand.MAIN_HAND : null;
				if(breakHand != null)
					entity.sendBreakAnimation(breakHand);
				
//				entity.renderBrokenItemStack(stack);
				int ammo = stack.getTag().getInt(NBT_AMMO_USED);
				stack.getTag().putInt(NBT_AMMO_USED, ++ammo);
				
				if(entity instanceof PlayerEntity)
				{
					((PlayerEntity)entity).addStat(Stats.ITEM_BROKEN.get(stack.getItem()));
				}
				
				stack.setDamage(0);
			}
		}
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) 
	{
		if(customDisplayName == null)
			return super.getDisplayName(stack);
		return new TranslationTextComponent(customDisplayName, material.translateName());
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		boolean isShiftPressed = Screen.hasShiftDown();

    	if(doCraftCheck && worldIn != null)
    	{
    		if(material.getModId() == ModSpartanWeaponry.ID && material.getRepairMaterial() == Ingredient.EMPTY)
	    			canBeCrafted = false;
    		
	    	doCraftCheck = false;
    	}

    	if(!canBeCrafted)
    		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.uncraftable.missing_material", ModSpartanWeaponry.ID), material.getTagName()).mergeStyle(TextFormatting.RED));
    	
		//tooltip.add(new TranslationTextComponent(String.format("dev.%s.wip", Reference.MOD_ID)).applyTextStyles(TextFormatting.BOLD, TextFormatting.DARK_RED));
//		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.modifiers.max_stack_size", ModSpartanWeaponry.ID), stack.getMaxStackSize()).mergeStyle(TextFormatting.GRAY));
    	
    	if(stack.getOrCreateTag().contains(NBT_ORIGINAL) && !stack.getTag().getBoolean(NBT_ORIGINAL))
    		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.throwable.not_original", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.DARK_RED));
    	if(stack.getTag().hasUniqueId(NBT_UUID) && flagIn.isAdvanced())
    		tooltip.add(new StringTextComponent("UUID: " + TextFormatting.GRAY.toString() + stack.getTag().getUniqueId(NBT_UUID).toString()).mergeStyle(TextFormatting.DARK_PURPLE));
    	int mxAmmo = getMaxAmmo(stack);
    	tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.throwable.ammo", ModSpartanWeaponry.ID), new TranslationTextComponent(String.format("tooltip.%s.throwable.ammo.value", ModSpartanWeaponry.ID), mxAmmo - stack.getTag().getInt(NBT_AMMO_USED), mxAmmo).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.throwable.charge_time", ModSpartanWeaponry.ID), new TranslationTextComponent(String.format("tooltip.%s.throwable.charge_time.value", ModSpartanWeaponry.ID), getMaxChargeTicks(stack) / 20.0f).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
    	
		if(!traits.isEmpty() || material.hasAnyWeaponTrait())
		{
			if(isShiftPressed)
				tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".showing_details").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
			else
				tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".show_details", TextFormatting.AQUA.toString() + "SHIFT").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
			for(WeaponTrait trait : traits)
			{
				trait.addTooltip(stack, tooltip, isShiftPressed);
			}

			if(!traits.isEmpty())
				tooltip.add(new StringTextComponent(""));
			
	    	if(material.hasAnyWeaponTrait())
	    	{
	    		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits.material_bonus", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.AQUA));
	    		for(WeaponTrait matTrait : this.material.getAllWeaponTraits())
	    		{
	    			matTrait.addTooltip(stack, tooltip, isShiftPressed);
	    		}
	    	}
		}

		tooltip.add(new StringTextComponent(""));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) 
	{
		Multimap<Attribute, AttributeModifier> multimap = HashMultimap.<Attribute, AttributeModifier>create();
		if (slot == EquipmentSlotType.MAINHAND) 
		{
			multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.getDirectAttackDamage(), AttributeModifier.Operation.ADDITION));
			multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed - 4.0d, AttributeModifier.Operation.ADDITION));
		
			if(traits != null)
			{
				for(WeaponTrait trait : traits)
				{
					IMeleeTraitCallback callback = trait.getMeleeCallback();
					if(callback != null)
						callback.onModifyAttibutesMelee(multimap);
				}
			}
			if(material.hasAnyWeaponTrait())
			{
				for(WeaponTrait property : material.getAllWeaponTraits())
				{
					IMeleeTraitCallback callback = property.getMeleeCallback();
					if(callback != null)
						callback.onModifyAttibutesMelee(multimap);
				}
			}
		}

	    return multimap;
	}

	@Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
		for(WeaponTrait trait : traits)
		{
			IMeleeTraitCallback callback = trait.getMeleeCallback();
			if(callback != null)
				callback.onHitEntity(material, stack, target, attacker, null);
		}
		
		for(WeaponTrait trait : material.getAllWeaponTraits())
		{
			IMeleeTraitCallback callback = trait.getMeleeCallback();
			if(callback != null)
				callback.onHitEntity(material, stack, target, attacker, null);
		}
    	
		// Deal double durability damage when used as a melee weapon
    	if(stack.getOrCreateTag().getInt(NBT_AMMO_USED) < getMaxAmmo(stack))
    		damageThrowingWeapon(stack, 2, attacker);
    	
		//stack.damageItem(2, attacker, (player) -> player.sendBreakAnimation(Hand.MAIN_HAND));
        return true;
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getOrCreateTag().getInt(NBT_AMMO_USED) == getMaxAmmo(stack))
			return ActionResult.resultFail(stack);
        player.setActiveHand(hand);
        return ActionResult.resultConsume(stack);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) 
	{
		if(entityLiving instanceof PlayerEntity)
		{
			int maxAmmo = getMaxAmmo(stack);
			int ammoCount = maxAmmo - stack.getOrCreateTag().getInt(NBT_AMMO_USED);
			
			if(ammoCount > 0)
			{
				PlayerEntity player = (PlayerEntity)entityLiving;
	
				int maxCharge = getMaxChargeTicks(stack);
	            int charge = Math.min(this.getUseDuration(stack) - timeLeft, maxCharge);
	            
//	            if(charge >= 5)
//	            	charge = 5;
				
				if (!worldIn.isRemote && charge > 2)
		        {
		            ThrowingWeaponEntity thrown = createThrowingWeaponEntity(worldIn, player, stack, charge);
		            float chargePerc = (charge / (float)maxCharge);
		            
		            if(thrown == null)	return;
//		            ItemStack weaponStack = stack.copy();
//		            weaponStack.setCount(1);
		            
		            thrown.setWeapon(stack);
		            //float distance = (float)player.getDistance(player.posX + player.motionX, playerIn.posY + playerIn.motionY, playerIn.posZ + playerIn.motionZ);
		            int velocityBonus = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.THROWING_RANGE, stack);
		            thrown.setDirectionAndMovement(player, player.rotationPitch, player.rotationYaw, 0.0F, throwVelocity * ((velocityBonus * 0.2f) + 1) * (chargePerc * 0.9f + 0.1f), 0.5f);
		           
		            if(material.hasAnyWeaponTrait())
		            {
		            	for(WeaponTrait trait : material.getAllWeaponTraits())
		            	{
		            		if(trait.isThrowingTrait() && trait.getThrowingCallback() != null)
		            			trait.getThrowingCallback().onThrowingProjectileSpawn(material, thrown);
		            	}
		            }
		            
		            float damage = (getDirectAttackDamage() + 1.0f) * throwDamageMultiplier;
		            thrown.setDamage(damage);
		            
		            // Apply enchantments as necessary
		            int j = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.THROWING_DAMAGE, stack);
		            if (j > 0)
		            {
		            	thrown.setDamage(thrown.getDamage() + j * 0.5D + 0.5D);
		            }
		            if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.THROWING_FIRE, stack) > 0)
		            {
		            	thrown.setFire(100);
		            }
		            /*int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, stack);
		            if (k > 0)
		            {
		            	thrown.setKnockbackStrength(k);
		            }*/
		            
		            if(player.abilities.isCreativeMode)
		            	thrown.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
		            else if(thrown.isValidThrowingWeapon())
		            {
		            	ammoCount--;
		            	stack.getOrCreateTag().putInt(NBT_AMMO_USED, maxAmmo - ammoCount);
		            	
		            	// If there is no ammo left and the stack isn't original (picked up from the ground to create a new stack), then delete the stack
		            	if(ammoCount == 0 && !stack.getTag().getBoolean(NBT_ORIGINAL))
		            	{
			                stack.shrink(1);
			                if(stack.getCount() <= 0)
			                	player.inventory.deleteStack(stack);
		            	}
		            }
		            
		            if(thrown.isValidThrowingWeapon())
		            {
		            	stack.setDamage(0);
			            worldIn.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), getThrowingSound(), SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
			            worldIn.addEntity(thrown);
		            }
		            
			        player.addStat(Stats.ITEM_USED.get(this));
		        }
			}
		}
		super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
	}

	@Override
	public int getUseDuration(ItemStack stack) 
	{
		return 72000;
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) 
	{
		return UseAction.SPEAR;
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) 
	{
		for(WeaponTrait trait : traits)
		{
			IMeleeTraitCallback callback = trait.getMeleeCallback();
			if(callback != null)
				callback.onCreateItem(material, stack);
		}
		for(WeaponTrait trait : material.getAllWeaponTraits())
		{
			IMeleeTraitCallback callback = trait.getMeleeCallback();
			if(callback != null)
				callback.onCreateItem(material, stack);
		}
		
		initNBT(stack, true);
//		super.onCreated(stack, worldIn, playerIn);
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) 
	{
		if(this.isInGroup(group))
		{
			ItemStack stack = new ItemStack(this);
			for(WeaponTrait trait : traits)
			{
				IMeleeTraitCallback callback = trait.getMeleeCallback();
				if(callback != null)
					callback.onCreateItem(material, stack);
			}
			for(WeaponTrait trait : material.getAllWeaponTraits())
			{
				IMeleeTraitCallback callback = trait.getMeleeCallback();
				if(callback != null)
					callback.onCreateItem(material, stack);
			}
			initNBT(stack, false);
			items.add(stack);
		}
	}

	@Override
	public int getItemEnchantability(ItemStack stack) 
	{
		return this.material.getEnchantability();
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) 
	{
		// Allow Loyalty enchantments to work on Throwing Weapons
		return super.canApplyAtEnchantingTable(stack, enchantment) || enchantment == Enchantments.LOYALTY;
	}
	
	// IWeaponTraitContainer

	@Override
	public boolean hasWeaponTrait(WeaponTrait prop)
	{
		return traits.contains(prop) /*|| materialEx.getAllWeaponProperties().contains(prop)*/;
	}

	@Override
	public ThrowingWeaponItem addWeaponTrait(WeaponTrait prop) 
	{
    	this.traits.add(prop);
    	return this;
	}

	@Override
	public WeaponTrait getFirstWeaponTraitWithType(String type)
	{
		for(WeaponTrait property : traits)
		{
			if(property.getType() == type)
				return property;
		}
		for(WeaponTrait trait : material.getAllWeaponTraits())
		{
			if(trait.getType() == type)
				return trait;
		}
		return null;
	}

	@Override
	public List<WeaponTrait> getAllWeaponTraitsWithType(String type) 
	{
		List<WeaponTrait> result = new ArrayList<WeaponTrait>();
	
		for(WeaponTrait property : traits)
		{
			if(property.getType() == type)
				result.add(property);
		}
		for(WeaponTrait trait : material.getAllWeaponTraits())
		{
			if(trait.getType() == type)
				result.add(trait);
		}
	
		if(result.isEmpty())
			return null;
		return result;
	}

	@Override
	public List<WeaponTrait> getAllWeaponTraits() 
	{
		return new ArrayList<WeaponTrait>(traits);
	}

	@Override
	public WeaponMaterial getMaterial()
	{
		return this.material;
	}
	
	// New methods
	public float getDirectAttackDamage() 
	{
		return attackDamage;
	}

	public void setAttackDamage(float baseDamage, float damageMultiplier)
	{
		attackDamage = (material.getAttackDamage() * damageMultiplier) + baseDamage - 1.0f;
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
	 * @param worldIn The World instance
	 * @param player The Player throwing the weapon
	 * @param stack The Throwing Weapon Item
	 * @param charge The total time (in ticks) that the weapon is held for before throwing it
	 * @return
	 */
	public ThrowingWeaponEntity createThrowingWeaponEntity(World worldIn, PlayerEntity player, ItemStack stack, int charge)
	{
		return new ThrowingWeaponEntity(ModEntities.THROWING_WEAPON, player, worldIn);
	}
	
	protected SoundEvent getThrowingSound()
	{
		return ModSounds.THROWING_KNIFE_THROW;
	}
	
	protected void initNBT(ItemStack stack, boolean initUUID) 
	{
		CompoundNBT tag = stack.getOrCreateTag();
		// Initialise ammo tag if necessary
		if(!tag.contains(NBT_AMMO_USED))
		{
			/*if(tag.contains(NBT_AMMO))
			{
				Log.info("Found old ammo stack! Converted to new ammo system using a new ammo stack");
				tag.setInteger(NBT_AMMO_USED, getMaxAmmo(stack) - tag.getInteger(NBT_AMMO));
				tag.removeTag(NBT_AMMO);
			}*/
			// Convert the old-1.16.5-style throwing weapons (standard stackable) to the new version
			if(stack.getCount() > 1)
				stack.setCount(1);
			// And, because I don't think it would be a good idea to transfer the ammo value from the old version to the new one
			// Just fill 'er up
			tag.putInt(NBT_AMMO_USED, 0);
		}
		// Initialise UUID tag if necessary, and flag as original stack
	    if(initUUID && !tag.hasUniqueId(NBT_UUID))
	    {
			stack.getTag().putUniqueId(NBT_UUID, UUID.randomUUID());
			stack.getTag().putBoolean(NBT_ORIGINAL, true);
	    }
	}
	
	public int getMaxAmmo(ItemStack stack)
	{
		int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.THROWING_AMMO, stack);
		// Find the value to increase by per level (if ammo increase is too small e.g. Boomerang; then use ammo + 1 per level instead)
		int increasePerLevel = Math.max((int)(maxAmmo * 0.25f), 1);
		return this.maxAmmo + (increasePerLevel * level);
	}
	
	public int getMaxAmmoBase()
	{
		return maxAmmo;
	}
	
	public int getMaxChargeTicks(ItemStack stack)
	{
		int chargeTicks = (int)(this.maxChargeTicks * (1 - EnchantmentHelper.getEnchantmentLevel(ModEnchantments.THROWING_CHARGE, stack) * 0.2f));
		if(material.hasAnyWeaponTrait())
		{
			for(WeaponTrait trait : material.getAllWeaponTraits())
			{
				IThrowingTraitCallback callback = trait.getThrowingCallback();
				if(trait.isThrowingTrait() && callback != null)
				{
					chargeTicks = callback.modifyThrowingChargeTime(material, chargeTicks);
				}
			}
		}
		return chargeTicks;
	}

    @OnlyIn(Dist.CLIENT)
	@Override
	public HudElementCrosshair createHudElement() 
	{
		return new HudElementCrosshairThrowingWeapon();
	}

    @OnlyIn(Dist.CLIENT)
	@Override
	public ResourceLocation getType() 
	{
		return HudElementCrosshairThrowingWeapon.TYPE;
	}
}
