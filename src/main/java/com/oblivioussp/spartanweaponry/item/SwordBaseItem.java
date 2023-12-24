
package com.oblivioussp.spartanweaponry.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.trait.IMeleeTraitCallback;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.client.ClientHelper;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;
import com.oblivioussp.spartanweaponry.util.WeaponHelper;

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
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.UseAction;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class SwordBaseItem extends SwordItem implements IWeaponTraitContainer<SwordBaseItem>
{
	protected float attackDamage = 1.0f;
	protected double attackSpeed = 0.0D;
	protected List<WeaponTrait> traits;
	protected WeaponMaterial material;
//	protected String modId = null;
	protected String customDisplayName = null;
//	protected boolean useMaterialDisplayName = false;
//	protected ITextComponent displayText = null;
	
	protected boolean doCraftCheck = true;
	protected boolean canBeCrafted = true;
	
	// TODO: Find out if the Battleaxe can truly chop all types of wood (via the Versatile Weapon Trait)
	
	// NOTE: This must be retained for API compatibility (most likely)
	public SwordBaseItem(String regName, Item.Properties prop, WeaponMaterial material, float weaponBaseDamage, float weaponDamageMultiplier, double weaponSpeed, boolean usingDeferredRegister, WeaponTrait... weaponTraits) 
	{
		super(material, 3, -2.4f, prop.maxDamage(material.getMaxUses()));
		if(!usingDeferredRegister)
			this.setRegistryName(regName);
		this.material = material;
		this.setAttackDamage(weaponBaseDamage, weaponDamageMultiplier);
		this.setAttackSpeed(weaponSpeed);
		traits = new ArrayList<WeaponTrait>();
		traits.addAll(Arrays.asList(weaponTraits));
		
		if(FMLEnvironment.dist.isClient()) 
		{
			if(hasWeaponTrait(WeaponTraits.THROWABLE))
				ClientHelper.registerThrowingWeaponPropertyOverrides(this);
			if(hasWeaponTrait(WeaponTraits.BLOCK_MELEE))
				ClientHelper.registerBlockablePropertyOverrides(this);
		}

		
		/*this.addPropertyOverride(new ResourceLocation("throwing"), (stack, world, living) ->
		{
			if(living == null || !this.hasWeaponTrait(WeaponTraits.THROWABLE) || !stack.isItemEqual(living.getActiveItemStack()))	return 0.0f;
			return living.getItemInUseCount() > 0 ? 1.0f : 0.0f;
		});*/
	}
	
	public SwordBaseItem(String regName, Item.Properties prop, WeaponMaterial material, float weaponBaseDamage, float weaponDamageMultiplier, double weaponSpeed, String customDisplayName, boolean usingDeferredRegister, WeaponTrait... weaponTraits)
	{
		this(regName, prop, material, weaponBaseDamage, weaponDamageMultiplier, weaponSpeed, usingDeferredRegister, weaponTraits);
		if(material.useCustomDisplayName())
			this.customDisplayName = customDisplayName;
	}
	
	/**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
	@Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
    {
		// Check for two-handed traits, and other such effects
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
	

	/**
     * Returns the amount of damage this item will deal. One heart of damage is equal to 2 damage points.
     */
	@Override
    public float getAttackDamage()
    {
        return material.getAttackDamage();
    }
	
	@Override
	public int getMaxDamage(ItemStack stack) 
	{
		return material.getMaxUses();
	}
	
	/**
    * Return the enchantability factor of the item, most of the time is based on material.
    */
	/*@Override
	public int getItemEnchantability()
	{
		return this.material.getEnchantability();
	}*/
	
	/**
     * Return whether this item is repairable in an anvil.
     */
	/*@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
    	if(toRepair.isEmpty() || repair.isEmpty())
    		return false;
    	if( materialEx.doesOreDictMatch(repair))
    		return true;
    	return super.getIsRepairable(toRepair, repair);
    }*/
	
	/**
     * Return the name for this tool's material.
     */
	/*@Override
    public String getToolMaterialName()
    {
        return this.materialEx.getMaterial().toString();
    }*/
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state)
    {
        for (ToolType type : getToolTypes(stack))
        {
            if (state.getBlock().isToolEffective(state, type))
                return material.getEfficiency();
        }
        return super.getDestroySpeed(stack, state);
    }

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
    {
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.<Attribute, AttributeModifier>create();

        if (equipmentSlot == EquipmentSlotType.MAINHAND)
        {
            multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", getDirectAttackDamage(), AttributeModifier.Operation.ADDITION));
            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.attackSpeed - 4.0D, AttributeModifier.Operation.ADDITION));

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
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker)
	{
		return hasWeaponTrait(WeaponTraits.SHIELD_BREACH);
	}
    
    /*@Override
    public String getItemStackDisplayName(ItemStack stack)
    {
		if(displayName != null)
		{
			String name = I18n.translateToLocalFormatted(String.format("item.%s:%s.name", Reference.ModID, displayName), I18n.translateToLocal(materialEx.getFullUnlocName()));
			return name;
		}
		return super.getItemStackDisplayName(stack);
    }*/
	
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
		
		//tooltip.add(new TranslationTextComponent(String.format("dev.%s.wip", Reference.MOD_ID)).applyTextStyles(TextFormatting.BOLD, TextFormatting.DARK_RED));

    	if(doCraftCheck && worldIn != null)
    	{
    		if(material.getModId() == ModSpartanWeaponry.ID && material.getRepairMaterial() == Ingredient.EMPTY)
	    			canBeCrafted = false;
    		
	    	doCraftCheck = false;
    	}

    	if(!canBeCrafted)
    		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.uncraftable.missing_material", ModSpartanWeaponry.ID), material.getTagName()).mergeStyle(TextFormatting.RED));
		
		if(!traits.isEmpty())
		{
			if(isShiftPressed)
				tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".showing_details").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
			else
				tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".show_details", TextFormatting.AQUA.toString() + "SHIFT").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
			for(WeaponTrait trait : traits)
			{
				if(trait.isMeleeTrait())
					trait.addTooltip(stack, tooltip, isShiftPressed);
			}
			tooltip.add(new StringTextComponent(""));
		}

    	if(material.hasAnyWeaponTrait())
    	{
    		//tooltip.add("");
    		//tooltip.add(TextFormatting.DARK_AQUA + "Material Bonus:");
    		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits.material_bonus", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.AQUA));
    		for(WeaponTrait matTrait : this.material.getAllWeaponTraits())
    		{
    			matTrait.addTooltip(stack, tooltip, isShiftPressed);
    		}
    	}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public float getDirectAttackDamage()
	{
		return attackDamage;
	}
	
	/**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
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
		
        return super.hitEntity(stack, target, attacker);
    }
	
	/*@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) 
	{
		if(this.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_THROWABLE) != null)
		{
			ItemStack stack = playerIn.getHeldItem(hand);
	        playerIn.setActiveHand(hand);
	        return new ActionResult(EnumActionResult.SUCCESS, stack);
		}
		return super.onItemRightClick(worldIn, playerIn, hand);
	}*/
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		if(hasWeaponTrait(WeaponTraits.THROWABLE))
		{
	        player.setActiveHand(hand);
	        return ActionResult.resultConsume(stack);
		}
		if(hasWeaponTrait(WeaponTraits.BLOCK_MELEE))
		{
			if(player.isSneaking())
				return ActionResult.resultFail(stack);
			player.setActiveHand(hand);
			return ActionResult.resultConsume(stack);
		}
		return super.onItemRightClick(worldIn, player, hand);
	}

	/*@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) 
	{
		if(this.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_THROWABLE) != null && entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entityLiving;

            int charge = this.getMaxItemUseDuration(stack) - timeLeft;
            
            if(charge >= 5)
            	charge = 5;
			
			if (!worldIn.isRemote && charge > 2)
	        {
	            EntityThrownWeapon thrown = new EntityThrownWeapon(worldIn, player);
	            thrown.setWeapon(stack);
	            //float distance = (float)player.getDistance(player.posX + player.motionX, playerIn.posY + playerIn.motionY, playerIn.posZ + playerIn.motionZ);
	            thrown.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5f * (charge / 10.0f + 0.5f), 0.5f);
	            thrown.setDamage(this.getDirectAttackDamage() + 1.0f);
	            
	            // Apply enchantments as necessary
	            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack);
	            if (j > 0)
	            {
	            	thrown.setDamage(thrown.getDamage() + j * 0.5D + 0.5D);
	            }
	            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, stack);
	            if (k > 0)
	            {
	            	thrown.setKnockbackStrength(k);
	            }
	            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, stack) > 0)
	            {
	            	thrown.setFire(100);
	            }
	            
	            if(player.capabilities.isCreativeMode)
	            	thrown.pickupStatus = PickupStatus.CREATIVE_ONLY;
	            else
	            {
	                stack.setCount(stack.getCount() - 1);
	                if(stack.getCount() <= 0)
	                	player.inventory.deleteStack(stack);
	            }
	            
	            worldIn.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	            worldIn.spawnEntity(thrown);
	        }

	        player.addStat(StatList.getObjectUseStats(this));
		}
		super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
	}*/
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) 
	{
		if(this.getFirstWeaponTraitWithType(WeaponTraits.TRAIT_TYPE_THROWABLE) != null && entityLiving instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)entityLiving;

            int charge = this.getUseDuration(stack) - timeLeft;
            
            if(charge >= 5)
            	charge = 5;
			
			if (!worldIn.isRemote && charge > 2)
	        {
	            ThrowingWeaponEntity thrown = new ThrowingWeaponEntity(ModEntities.THROWING_WEAPON, player, worldIn);
	            thrown.setWeapon(stack);
	            //float distance = (float)player.getDistance(player.posX + player.motionX, playerIn.posY + playerIn.motionY, playerIn.posZ + playerIn.motionZ);
	            thrown.setDirectionAndMovement(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5f * (charge / 10.0f + 0.5f), 0.5f);
	            thrown.setDamage(this.getDirectAttackDamage() + 1.0f);
	            
	            // Apply enchantments as necessary
	            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack);
	            if (j > 0)
	            {
	            	thrown.setDamage(thrown.getDamage() + j * 0.5D + 0.5D);
	            }
	            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, stack);
	            if (k > 0)
	            {
	            	thrown.setKnockbackStrength(k);
	            }
	            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, stack) > 0)
	            {
	            	thrown.setFire(100);
	            }
	            
	            if(player.abilities.isCreativeMode)
	            	thrown.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
	            else if(thrown.isValidThrowingWeapon())
	            {
	                stack.shrink(1);
	                if(stack.getCount() <= 0)
	                	player.inventory.deleteStack(stack);
	            }
	            
	            if(thrown.isValidThrowingWeapon())
	            {
		            worldIn.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), ModSounds.THROWING_KNIFE_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		            worldIn.addEntity(thrown);
	            }
	        }

	        player.addStat(Stats.ITEM_USED.get(this));
		}
		super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
	}

	/*@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		if(this.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_THROWABLE) != null)
			return 72000;
		return super.getMaxItemUseDuration(stack);
	}*/
	
	@Override
	public int getUseDuration(ItemStack stack) 
	{
		if(hasWeaponTrait(WeaponTraits.THROWABLE) || hasWeaponTrait(WeaponTraits.BLOCK_MELEE))
			return 72000;
		return super.getUseDuration(stack);
	}

	/*@Override
	public EnumAction getItemUseAction(ItemStack stack) 
	{
		if(this.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_THROWABLE) != null)
			return EnumAction.BOW;
		return super.getItemUseAction(stack);
	}*/
	
	@Override
	public UseAction getUseAction(ItemStack stack) 
	{
		if(hasWeaponTrait(WeaponTraits.THROWABLE))
			return UseAction.SPEAR;
		if(hasWeaponTrait(WeaponTraits.BLOCK_MELEE))
			return UseAction.BLOCK;
		return super.getUseAction(stack);
	}

	/*@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) 
	{
		for(WeaponProperty prop : properties)
		{
			IPropertyCallback callback = prop.getCallback();
			if(callback != null)
				callback.onCreateItem(materialEx, stack);
		}
		for(WeaponProperty prop : materialEx.getAllWeaponProperties())
		{
			IPropertyCallback callback = prop.getCallback();
			if(callback != null)
				callback.onCreateItem(materialEx, stack);
		}
		super.onCreated(stack, worldIn, playerIn);
	}*/
	
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
		super.onCreated(stack, worldIn, playerIn);
	}
	
	/*@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) 
	{
		if(this.isInCreativeTab(tab))
		{
			ItemStack stack = new ItemStack(this);
			for(WeaponProperty prop : properties)
			{
				IPropertyCallback callback = prop.getCallback();
				if(callback != null)
					callback.onCreateItem(materialEx, stack);
			}
			for(WeaponProperty prop : materialEx.getAllWeaponProperties())
			{
				IPropertyCallback callback = prop.getCallback();
				if(callback != null)
					callback.onCreateItem(materialEx, stack);
			}
			items.add(stack);
		}
	}*/
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
			items.add(stack);
		}
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity)
	{
		/*WeaponTrait sweepTrait = this.getFirstWeaponTraitWithType(WeaponTraits.TRAIT_TYPE_SWEEP_DAMAGE);
		if(sweepTrait != null && sweepTrait.getMagnitude() == 1.0f)
			return super.onLeftClickEntity(stack, player, entity);*/
		
		WeaponHelper.inflictAttackDamage(this, player, entity);
		return true;
	}
	
	/**
     * Called to get the Mod ID of the mod that *created* the ItemStack,
     * instead of the real Mod ID that *registered* it.
     *
     * For example the Forge Universal Bucket creates a subitem for each modded fluid,
     * and it returns the modded fluid's Mod ID here.
     *
     * Mods that register subitems for other mods can override this.
     * Informational mods can call it to show the mod that created the item.
     *
     * @param itemStack the ItemStack to check
     * @return the Mod ID for the ItemStack, or
     *         null when there is no specially associated mod and {@link #getRegistryName()} would return null.
     */
    @Nullable
    public String getCreatorModId(ItemStack itemStack)
    {
//    	if(this.modId != null)
//            return this.modId;
    	return super.getCreatorModId(itemStack);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment)
    {
    	if(this.getFirstWeaponTraitWithType(WeaponTraits.TRAIT_TYPE_SWEEP_DAMAGE) == null)
        	return enchantment != Enchantments.SWEEPING && super.canApplyAtEnchantingTable(stack, enchantment);
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }
    
    // IWeaponTraitContainer

	@Override
	public boolean hasWeaponTrait(WeaponTrait prop) 
	{
		return traits.contains(prop) /*|| materialEx.getAllWeaponProperties().contains(prop)*/;
	}

	// TODO: Determine whether or not removing this will break any addon mods
	@Override
	public SwordBaseItem addWeaponTrait(WeaponTrait prop) 
	{
    	traits.add(prop);
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

	// TODO: Determine whether or not changing the type to immutable breaks anything...
	@Override
	public List<WeaponTrait> getAllWeaponTraits() 
	{
//		return new ArrayList<WeaponTrait>(traits);
		return ImmutableList.copyOf(traits);
	}

	@Override
	public WeaponMaterial getMaterial()
	{
		return material;
	}
	
	public void setAttackDamage(float baseDamage, float damageMultiplier)
	{
		attackDamage = (material.getAttackDamage() * damageMultiplier) + baseDamage - 1.0f;
	}
	
	public void setAttackSpeed(double speed)
	{
		attackSpeed = speed;
	}
	
	// Sets Weapon Traits to this weapon, provided they are strictly melee traits
	/*public List<WeaponTrait> setTraits(WeaponTrait... traits)
	{
		List<WeaponTrait> traitList = new ArrayList<WeaponTrait>();
		for(WeaponTrait trait : traits)
		{
			
		}
		return traitList;
	}*/
    
    /*@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) 
    {
    	if(entityLiving instanceof EntityPlayer)
    		LogHelper.info("Swinging weapon! Cooldown " + ((EntityPlayer)entityLiving).getCooledAttackStrength(0.0f));
		return super.onEntitySwing(entityLiving, stack);
	}*/

	/*@Override
	public List<WeaponProperty> getAllWeaponProperties() 
	{
		return new ArrayList<WeaponProperty>(properties);
	}*/

	/*@Override
	public List<WeaponProperty> getWeaponProperties() 
	{
		return Collections.unmodifiableList(properties);
	}*/
}
