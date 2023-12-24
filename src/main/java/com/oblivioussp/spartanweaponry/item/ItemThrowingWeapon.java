package com.oblivioussp.spartanweaponry.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.IPropertyCallback;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.init.EnchantmentRegistrySW;
import com.oblivioussp.spartanweaponry.init.SoundRegistry;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.Log;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.ITooltipFlag.TooltipFlags;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemThrowingWeapon extends Item implements IWeaponPropertyContainer<ItemThrowingWeapon>
{
	protected float attackDamage = 1.0f;
	protected double attackSpeed = 0.0D;
	protected List<WeaponProperty> properties;
	protected ToolMaterialEx materialEx;
	protected String modId = null;
	protected String displayName = null;
	protected boolean doCraftCheck = true;
	protected boolean canBeCrafted = true;
	protected int maxAmmo = 1;
	protected int maxChargeTicks = 5;
	
	protected float throwVelocity;
	protected float throwDamageMultiplier;
	
	public static final String NBT_AMMO = "Ammo";
	public static final String NBT_AMMO_USED = "AmmoUsed";
	public static final String NBT_UUID = "UUID";
	public static final String NBT_ORIGINAL = "Original";
	
	public ItemThrowingWeapon(String unlocName, ToolMaterialEx material, float weaponDamageBase, float weaponDamageMultiplier, float weaponSpeed, int maxAmmoCapacity, WeaponProperty... weaponProperties) 
	{
		super();
		this.setCreativeTab(CreativeTabsSW.TAB_SW);
		this.setRegistryName(unlocName);
		this.setTranslationKey(unlocName);
		this.setMaxStackSize(1);
		materialEx = material;
        this.setMaxDamage(materialEx.getMaxUses() / 4);
		attackDamage = Math.max(0.5f, (materialEx.getAttackDamage() * weaponDamageMultiplier) + weaponDamageBase - 1.0f);
		attackSpeed = weaponSpeed;
		maxAmmo = maxAmmoCapacity;
		properties = new ArrayList<WeaponProperty>();
		properties.addAll(Arrays.asList(weaponProperties));
		this.throwVelocity = 1.0f;
		this.throwDamageMultiplier = 1.0f;
		
		/*this.addPropertyOverride(new ResourceLocation("count"), new IItemPropertyGetter()
		{
			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) 
			{
				if(stack.hasTagCompound())
				{
					int stackCount = stack.getTagCompound().getInteger(NBT_STACK_COUNT);
					if(stackCount > 3)
						stackCount = 3;
					
					return stackCount;
				}
				return 1.0f;
			}
		});*/
		this.addPropertyOverride(new ResourceLocation("empty"), new IItemPropertyGetter()
			{
				@Override
				public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn)
				{
					return stack.hasTagCompound() && stack.getTagCompound().getInteger(NBT_AMMO_USED) == getMaxAmmo(stack) ? 1 : 0;
				}
			});
	}
	
	@Override
	public String getTranslationKey()
	{
		if(modId != null)
			return StringHelper.getItemUnlocalizedName(super.getTranslationKey(), modId);
		
		return StringHelper.getItemUnlocalizedName(super.getTranslationKey());
	}
	
	@Override
	public String getTranslationKey(ItemStack itemStack)
	{
		if(modId != null)
			return StringHelper.getItemUnlocalizedName(super.getTranslationKey(), modId);
		
		return StringHelper.getItemUnlocalizedName(super.getTranslationKey());
	}
	
	public EntityThrownWeapon createThrownWeaponEntity(World world, EntityPlayer player)
	{
		return new EntityThrownWeapon(world, player);
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		ItemStack stack = playerIn.getHeldItem(hand);
		if(stack.hasTagCompound() && stack.getTagCompound().getInteger(NBT_AMMO_USED) == getMaxAmmo(stack))
			return new ActionResult(EnumActionResult.FAIL, stack);
    	playerIn.setActiveHand(hand);
        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }
	
	/**
    * Return the enchantability factor of the item, most of the time is based on material.
    */
    @Override
	public int getItemEnchantability()
	{
    	return this.materialEx.getEnchantability();
	}
    
    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return stack.hasTagCompound() ? stack.getTagCompound().hasKey(NBT_ORIGINAL) ? stack.getTagCompound().getBoolean(NBT_ORIGINAL) : true : super.isEnchantable(stack);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) 
    {
    	return isEnchantable(stack) ? super.canApplyAtEnchantingTable(stack, enchantment) : false;
    }
    
    @Override
    public boolean isRepairable() 
    {
    	return false;
    }
	
	/**
     * Return whether this item is repairable in an anvil.
     */
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
    	if(toRepair.isEmpty() || repair.isEmpty())
    		return false;
    	if( materialEx.doesOreDictMatch(repair))
    		return true;
    	return super.getIsRepairable(toRepair, repair);
    }
	
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        for (String type : getToolClasses(stack))
        {
            if (state.getBlock().isToolEffective(type, state))
                return materialEx.getEfficiency() * 0.8f;
        }
        return 1.0F;
    }
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving)
	{
		// Make the throwing weapon take damage when digging
		if(!world.isRemote && state.getBlockHardness(world, pos) != 0.0f)
		{
			damageItem(stack, 2, entityLiving);
		}
		return false;
	}
	
	public void damageItem(ItemStack stack, int damage, EntityLivingBase entity)
	{
		//stack.damageItem(damage, entity);
		if(stack.isItemStackDamageable() && stack.hasTagCompound() && stack.getTagCompound().getInteger(NBT_AMMO_USED) < getMaxAmmo(stack) && 
				(!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).capabilities.isCreativeMode))
		{
			if(stack.attemptDamageItem(damage, entity.getRNG(), entity instanceof EntityPlayerMP ? (EntityPlayerMP)entity : null))
			{
				entity.renderBrokenItemStack(stack);
				if(stack.hasTagCompound())
				{
					int ammo = stack.getTagCompound().getInteger(NBT_AMMO_USED);
					stack.getTagCompound().setInteger(NBT_AMMO_USED, ++ammo);
				}
				
				if(entity instanceof EntityPlayer)
				{
					((EntityPlayer)entity).addStat(StatList.getObjectBreakStats(stack.getItem()));
				}
				
				stack.setItemDamage(0);
			}
		}
	}

    @Override
	public float getDirectAttackDamage()
	{
		return attackDamage;
	}
	
	public float getThrownDamageMultiplier()
	{
		return throwDamageMultiplier;
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", getDirectAttackDamage(), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.attackSpeed - 4.0D, 0));
        }

        return multimap;
    }
    
    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
    	// Deal double durability damage when used as a melee weapon
    	if(stack.hasTagCompound() && stack.getTagCompound().getInteger(NBT_AMMO_USED) < getMaxAmmo(stack))
    		damageItem(stack, 2, attacker);
        return true;
    }
    
    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
	@Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
		if(entityLiving instanceof EntityPlayer /*&& stack.hasTagCompound()*/)
		{
			int maxAmmo = getMaxAmmo(stack);
			int ammoCount = maxAmmo - stack.getTagCompound().getInteger(NBT_AMMO_USED);
			if(ammoCount > 0)
			{
				EntityPlayer player = (EntityPlayer)entityLiving;
	
				int maxCharge = getMaxChargeTicks(stack);
	            int charge = Math.min(this.getMaxItemUseDuration(stack) - timeLeft, maxCharge);
	            
//	            if(charge >= maxChargeTicks)
//	            	charge = maxChargeTicks;
				
				if (!worldIn.isRemote /*&& charge > 2*/)
		        {
		            EntityThrownWeapon thrown = createThrownWeaponEntity(worldIn, player);
		            float chargePerc = (charge / (float)maxCharge);
		            
		            if(thrown == null)	return;
		            
		            thrown.setWeapon(stack);
		            
		            int velocityBonus = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.THROWING_RANGE, stack);
		            
		            thrown.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, this.throwVelocity * ((velocityBonus * 0.2f) + 1) * (chargePerc * 0.9f + 0.1f), 0.5f);
		            double damageMultiplier = (this.throwDamageMultiplier - 1.0f) * chargePerc + 1.0f;
		            thrown.setDamage((this.getDirectAttackDamage() + 1.0d) * damageMultiplier);
		            
		            // Apply enchantments as necessary
		            int j = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.THROWING_DAMAGE, stack);
		            if (j > 0)
		            {
		            	thrown.setDamage(thrown.getDamage() + j * 0.5D + 0.5D);
		            }
		            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, stack);
		            if (k > 0)
		            {
		            	thrown.setKnockbackStrength(k);
		            }
		            if (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.THROWING_FIRE, stack) > 0)
		            {
		            	thrown.setFire(100);
		            }
		            
		            if(player.capabilities.isCreativeMode)
		            	thrown.pickupStatus = PickupStatus.CREATIVE_ONLY;
		            else if(thrown.isValidThrowingWeapon())
		            {
		            	
		            	ammoCount--;
		            	stack.getTagCompound().setInteger(NBT_AMMO_USED, maxAmmo - ammoCount);

		            	// If there is no ammo left and the stack isn't original (picked up from the ground to create a new stack), then delete the stack
		            	if(ammoCount == 0 && !stack.getTagCompound().getBoolean(NBT_ORIGINAL))
		            	{
			                stack.shrink(1);
			                if(stack.getCount() <= 0)
			                	player.inventory.deleteStack(stack);
		            	}
		            }
		            if(thrown.isValidThrowingWeapon())
		            {
		            	stack.setItemDamage(0);
			            worldIn.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundRegistry.THROWING_WEAPON_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			            worldIn.spawnEntity(thrown);
		            }
			        player.addStat(StatList.getObjectUseStats(this));
		        }
			}
		}
		super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }
    
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
    
    @Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) 
    {
    	initNBT(stack, true);
    		
		if(properties != null && entity instanceof EntityLivingBase)
		{
			EntityLivingBase living = (EntityLivingBase)entity;
			
			for(WeaponProperty property : properties)
			{
				IPropertyCallback callback = property.getCallback();
				if(callback != null)
					callback.onItemUpdate(materialEx, stack, world, living, itemSlot, isSelected);
			}
			if(materialEx.hasAnyWeaponProperty())
			{
				for(WeaponProperty property : materialEx.getAllWeaponProperties())
				{
					IPropertyCallback callback = property.getCallback();
					if(callback != null)
						callback.onItemUpdate(materialEx, stack, world, living, itemSlot, isSelected);
				}
			}
		}
	}

	@Override
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
		
		initNBT(stack, true);
//		super.onCreated(stack, worldIn, playerIn);
	}

	@Override
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
			
			initNBT(stack, false);
			items.add(stack);
		}
	}

	/**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		boolean isShiftPressed = GuiScreen.isShiftKeyDown();
		
    	if(doCraftCheck && worldIn != null)
    	{
    		if(this.materialEx.getModId() == ModSpartanWeaponry.ID)
    		{
		    	List<ItemStack> ores = OreDictionary.getOres(materialEx.getOreDictForRepairMaterial(), false);
		    	if(ores == null || ores.isEmpty())
		    		canBeCrafted = false;
    		}
	    	doCraftCheck = false;
    	}
    	
    	if(stack.hasTagCompound() && !stack.getTagCompound().hasKey("enchChecked"))
    	{
    		if(EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack) != 0 || EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, stack) != 0 ||
				EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack) != 0 || EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, stack) != 0 ||
				EnchantmentHelper.getEnchantmentLevel(Enchantments.SMITE, stack) != 0 || EnchantmentHelper.getEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, stack) != 0 ||
				EnchantmentHelper.getEnchantmentLevel(Enchantments.SWEEPING, stack) != 0)
			{
				stack.getTagCompound().setBoolean("enchantmentsInvalid", true);
			}
			stack.getTagCompound().setBoolean("enchChecked", true);
    	}
    	
    	if(!ConfigHandler.forceDisableUncraftableTooltips && !canBeCrafted)
    	{
    		tooltip.add(TextFormatting.RED + StringHelper.translateFormattedString("cantCraftMissingMaterial", "tooltip", ModSpartanWeaponry.ID, StringHelper.translateString(materialEx.getOreDictForRepairMaterial(), "material", materialEx.getModId())));
    	}
    	if(stack.hasTagCompound())
    	{
    		NBTTagCompound tag = stack.getTagCompound();
	    	if(tag.getBoolean("enchantmentsInvalid"))
	    		tooltip.add(TextFormatting.RED + StringHelper.translateString("invalidEnchantments", "tooltip"));
	    	
	    	if(tag.hasKey(NBT_ORIGINAL) && !tag.getBoolean(NBT_ORIGINAL))
	    		tooltip.add(TextFormatting.DARK_RED + StringHelper.translateString("throwable.not_original", "tooltip", ModSpartanWeaponry.ID));
	    	
	    	if(tag.hasKey(NBT_AMMO_USED))
	    	{
	    		int mxAmmo = getMaxAmmo(stack);
	    		tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("throwable.ammo", "tooltip", ModSpartanWeaponry.ID,
	    				TextFormatting.GRAY + StringHelper.translateFormattedString("throwable.ammo.value", "tooltip", ModSpartanWeaponry.ID, mxAmmo - tag.getInteger(NBT_AMMO_USED), mxAmmo)));
	    	}
    	}
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("throwable.charge_time", "tooltip", ModSpartanWeaponry.ID, 
    			TextFormatting.GRAY + StringHelper.translateFormattedString("throwable.charge_time.value", "tooltip", this.getMaxChargeTicks(stack) / 20.0f)));
    	
    	if(stack.hasTagCompound())
    	{
    		NBTTagCompound tag = stack.getTagCompound();
	    	if(tag.hasUniqueId(NBT_UUID) && flagIn == TooltipFlags.ADVANCED)
	    		tooltip.add(TextFormatting.DARK_PURPLE + "UUID: " + TextFormatting.GRAY + "" + tag.getUniqueId(NBT_UUID).toString());
    	}
    	
//        tooltip.add(TextFormatting.GRAY + StringHelper.translateFormattedString("throwable.max_stack_size", "tooltip", ModSpartanWeaponry.ID, stack.getMaxStackSize()));
    	
    	if(!properties.isEmpty() || materialEx.hasAnyWeaponProperty())
    	{
    		tooltip.add(TextFormatting.GOLD + StringHelper.translateFormattedString("properties", "tooltip", ModSpartanWeaponry.ID,
    				isShiftPressed ? TextFormatting.DARK_GRAY + StringHelper.translateString("showingDetails", "tooltip", ModSpartanWeaponry.ID) :
    					TextFormatting.DARK_GRAY + StringHelper.translateFormattedString("showDetails", "tooltip", ModSpartanWeaponry.ID, TextFormatting.AQUA + "SHIFT" + TextFormatting.DARK_GRAY)));
    	}
		for(WeaponProperty property : properties)
    	{
    		property.addTooltip(stack, tooltip, isShiftPressed);
    	}
    	
    	if(materialEx.hasAnyWeaponProperty())
    	{
    		//tooltip.add("");
    		tooltip.add(TextFormatting.DARK_AQUA + "Material Bonus:");
    		for(WeaponProperty matProp : this.materialEx.getAllWeaponProperties())
    		{
    			matProp.addTooltip(stack, tooltip, isShiftPressed);
    		}
    	}
    	
		/*if((!properties.isEmpty() || materialEx.hasAnyWeaponProperty()) && !isShiftPressed)
		{
			tooltip.add(TextFormatting.DARK_GRAY + StringHelper.translateFormattedString("showDetails", "tooltip", ModSpartanWeaponry.ID, TextFormatting.AQUA + "SHIFT" + TextFormatting.DARK_GRAY));
			//tooltip.add("");
		}*/
		tooltip.add("");
        super.addInformation(stack, worldIn, tooltip, flagIn);
    	
        /*int j = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.THROWING_DAMAGE, stack);
    	float throwDamage = (this.attackDamage + 1.0f) * this.throwDamageMultiplier + (j * 0.5f);
    	//String throwDamageStr = ;
    	tooltip.add(StringHelper.translateString("modifiers.thrown", "tooltip"));
    	tooltip.add(" " + StringHelper.translateFormattedString("attribute.damage", "tooltip", Reference.ModID, ItemStack.DECIMALFORMAT.format(throwDamage)));*/
//        tooltip.add("[DEBUG] - " + (stack.hasTagCompound() ? stack.getTagCompound().toString() : "No NBT Tag found!"));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
		if(displayName != null)
		{
			String name = I18n.translateToLocalFormatted(String.format("item.%s:%s.name", ModSpartanWeaponry.ID, displayName), I18n.translateToLocal(materialEx.getFullUnlocName()));
			return name;
		}
		return super.getItemStackDisplayName(stack);
		
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
    	if(modId != null)
            return modId;
    	return super.getCreatorModId(itemStack);
    }
    
    @Override
    public ItemThrowingWeapon addWeaponProperty(WeaponProperty prop)
    {
    	this.properties.add(prop);
    	return this;
    }
	
    @Override
	public boolean hasWeaponProperty(WeaponProperty prop)
	{
		return properties.contains(prop) || materialEx.getAllWeaponProperties().contains(prop);
	}

	@Override
	public WeaponProperty getFirstWeaponPropertyWithType(String type) 
	{
		for(WeaponProperty property : properties)
		{
			if(property.getType() == type)
				return property;
		}
		for(WeaponProperty property : materialEx.getAllWeaponProperties())
		{
			if(property.getType() == type)
				return property;
		}
		return null;
	}

	@Override
	public List<WeaponProperty> getAllWeaponPropertiesWithType(String type) 
	{
		List<WeaponProperty> result = new ArrayList<WeaponProperty>();
		
		for(WeaponProperty property : properties)
		{
			if(property.getType() == type)
				result.add(property);
		}
		for(WeaponProperty property : materialEx.getAllWeaponProperties())
		{
			if(property.getType() == type)
				result.add(property);
		}
		
		if(result.isEmpty())
			return null;
		return result;
	}

	@Override
	public List<WeaponProperty> getAllWeaponProperties() 
	{
		return new ArrayList(properties);
	}

	@Override
	public ToolMaterialEx getMaterialEx() 
	{
		return materialEx;
	}
	
	protected void initNBT(ItemStack stack, boolean initUUID) 
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = stack.getTagCompound();
		// Initialise ammo tag if necessary
		if(!tag.hasKey(NBT_AMMO_USED))
		{
			if(tag.hasKey(NBT_AMMO))
			{
				Log.info("Found old ammo in item" + stack.getItem().getRegistryName().toString() + "! Converted to new ammo system using a new ammo stack");
				tag.setInteger(NBT_AMMO_USED, getMaxAmmo(stack) - tag.getInteger(NBT_AMMO));
				tag.removeTag(NBT_AMMO);
			}
			tag.setInteger(NBT_AMMO_USED, 0);
		}
		// Initialise UUID tag if necessary, and flag as original stack
	    if(initUUID && !tag.hasUniqueId(NBT_UUID))
	    {
			stack.getTagCompound().setUniqueId(NBT_UUID, UUID.randomUUID());
			stack.getTagCompound().setBoolean(NBT_ORIGINAL, true);
	    }
	}
	
	public int getMaxAmmo(ItemStack stack)
	{
		int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.THROWING_AMMO, stack);
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
		return (int)(this.maxChargeTicks * (1 - EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.THROWING_CHARGE, stack) * 0.2f));
	}
}
