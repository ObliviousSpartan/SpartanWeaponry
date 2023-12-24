
package com.oblivioussp.spartanweaponry.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.api.weaponproperty.IPropertyCallback;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.compat.sme.SMEHelper;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemSwordBase extends ItemSword implements IWeaponPropertyContainer<ItemSwordBase>
{
	protected float attackDamage = 1.0f;
	protected double attackSpeed = 0.0D;
	protected List<WeaponProperty> properties;
	protected ToolMaterialEx materialEx;
	protected String modId = null;
	protected String displayName = null;
	protected boolean doCraftCheck = true;
	protected boolean canBeCrafted = true;
	
	public ItemSwordBase(String unlocName, ToolMaterialEx material, float weaponBaseDamage, float weaponDamageMultiplier, double weaponSpeed, WeaponProperty... weaponProperties) 
	{
		super(material.getMaterial());
		this.setCreativeTab(CreativeTabsSW.TAB_SW);
		this.setRegistryName(unlocName);
		this.setTranslationKey(unlocName);
		materialEx = material;
		attackDamage = Math.max(0.5f, (materialEx.getAttackDamage() * weaponDamageMultiplier) + weaponBaseDamage - 1.0f);
		attackSpeed = weaponSpeed;
		properties = new ArrayList<WeaponProperty>();
		properties.addAll(Arrays.asList(weaponProperties));
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
	
	/**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
	@Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
    {
		// Check for two-handed properties
		if(entity instanceof EntityLivingBase)
		{
			EntityLivingBase living = (EntityLivingBase)entity;
			
			if(properties != null)
			{
				for(WeaponProperty property : properties)
				{
					IPropertyCallback callback = property.getCallback();
					if(callback != null)
						callback.onItemUpdate(materialEx, stack, world, living, itemSlot, isSelected);
				}
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

	/**
     * Returns the amount of damage this item will deal. One heart of damage is equal to 2 damage points.
     */
	@Override
    public float getAttackDamage()
    {
        return this.materialEx.getAttackDamage();
    }
	
	/**
    * Return the enchantability factor of the item, most of the time is based on material.
    */
    @Override
   public int getItemEnchantability()
   {
       return this.materialEx.getEnchantability();
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
	
	/**
     * Return the name for this tool's material.
     */
	@Override
    public String getToolMaterialName()
    {
        return this.materialEx.getMaterial().toString();
    }
	
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        for (String type : getToolClasses(stack))
        {
            if (state.getBlock().isToolEffective(type, state))
                return materialEx.getEfficiency();
        }
        return super.getDestroySpeed(stack, state);
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
	
	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity, EntityLivingBase attacker)
	{
		return this.hasWeaponProperty(WeaponProperties.SHIELD_BREACH);
	}
	
	/**
     * allows items to add custom lines of information to the mouseover description
     */
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
    	
    	if(!ConfigHandler.forceDisableUncraftableTooltips && !canBeCrafted)
    	{
    		tooltip.add(TextFormatting.RED + StringHelper.translateFormattedString("cantCraftMissingMaterial", "tooltip", ModSpartanWeaponry.ID, StringHelper.translateString(materialEx.getOreDictForRepairMaterial(), "material", materialEx.getModId())));
    	}

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
    		tooltip.add(TextFormatting.DARK_AQUA + "Material Bonus:");
    		for(WeaponProperty matProp : this.materialEx.getAllWeaponProperties())
    		{
    			matProp.addTooltip(stack, tooltip, isShiftPressed);
    		}
    	}
		tooltip.add("");
    	super.addInformation(stack, worldIn, tooltip, flagIn);
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
	
    @Override
	public float getDirectAttackDamage()
	{
		return attackDamage;
	}
	
	/**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		for(WeaponProperty prop : properties)
		{
			IPropertyCallback callback = prop.getCallback();
			if(callback != null)
				callback.onHitEntity(materialEx, stack, target, attacker, null);
		}
		
		for(WeaponProperty prop : materialEx.getAllWeaponProperties())
		{
			IPropertyCallback callback = prop.getCallback();
			if(callback != null)
				callback.onHitEntity(materialEx, stack, target, attacker, null);
		}
		
        return super.hitEntity(stack, target, attacker);
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) 
	{
		if(this.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_THROWABLE) != null)
		{
			ItemStack stack = playerIn.getHeldItem(hand);
	        playerIn.setActiveHand(hand);
	        return new ActionResult(EnumActionResult.SUCCESS, stack);
		}
		return super.onItemRightClick(worldIn, playerIn, hand);
	}

	@Override
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
	            thrown.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5f * (charge / 10.0f + 0.5f), 0.5f);
	            thrown.setDamage(getDirectAttackDamage() + 1.0d);
	            
	            double damageModifier = 0.0d;
	            // Apply enchantments as necessary
	            if(Loader.isModLoaded("somanyenchantments"))
	            {
		            Enchantment supSharpness = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation("somanyenchantments:supremesharpness"));
		            Enchantment advSharpness = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation("somanyenchantments:advancedsharpness"));
		            Enchantment lesSharpness = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation("somanyenchantments:lessersharpness"));
		            
		            int j;
		            if((j = EnchantmentHelper.getEnchantmentLevel(supSharpness, stack)) > 0)
		            	damageModifier = 4.0d + j * 1.6d;
		            else if((j = EnchantmentHelper.getEnchantmentLevel(advSharpness, stack)) > 0)
		            	damageModifier = 1.25d + j * 0.95d;
		            else if((j = EnchantmentHelper.getEnchantmentLevel(lesSharpness, stack)) > 0)
		            	damageModifier = 0.25d + j * 0.25d;
	            }
	            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack);
	            if (j > 0)
	            	damageModifier = 0.5d + j * 0.5d;
	            
	            if(damageModifier > 0.0d)
	            	thrown.setDamage(thrown.getDamage() + damageModifier);
	            
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
	            else if(thrown.isValidThrowingWeapon())
	            {
	                stack.setCount(stack.getCount() - 1);
	                if(stack.getCount() <= 0)
	                	player.inventory.deleteStack(stack);
	            }
	            if(thrown.isValidThrowingWeapon())
	            {
		            worldIn.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		            worldIn.spawnEntity(thrown);
	            }
		        player.addStat(StatList.getObjectUseStats(this));
	        }

		}
		super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		if(this.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_THROWABLE) != null)
			return 72000;
		return super.getMaxItemUseDuration(stack);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) 
	{
		if(this.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_THROWABLE) != null)
			return EnumAction.BOW;
		return super.getItemUseAction(stack);
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
		super.onCreated(stack, worldIn, playerIn);
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
			items.add(stack);
		}
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
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) 
	{
		WeaponProperty prop = getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_SWEEP_DAMAGE);
		boolean isSweepCompatible = prop != null && prop.getLevel() == 1;	// Sweeping Edge is only compatible with Sweep 1 (not 2 or 3)
		return (hasWeaponProperty(WeaponProperties.VERSATILE) && ModSpartanWeaponry.isSMELoaded && SMEHelper.isCombatAxeEnchantment(enchantment.type)) ||		// Battleaxes can have tool-type enchantments
				(enchantment == Enchantments.SWEEPING && isSweepCompatible) || enchantment != Enchantments.SWEEPING && super.canApplyAtEnchantingTable(stack, enchantment);
	}
    
    // IWeaponPropertyContainer
	@Override
    public ItemSwordBase addWeaponProperty(WeaponProperty prop)
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
		return new ArrayList<WeaponProperty>(properties);
	}

	@Override
	public ToolMaterialEx getMaterialEx() 
	{
		return materialEx;
	}
}
