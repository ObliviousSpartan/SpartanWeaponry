package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.oredict.OreDictionary;

public class ItemLongbow extends ItemBow implements IHudQuiverDisplay
{
	
	protected ToolMaterialEx material;
//	protected int drawTicks = 25;
//	protected float maxVelocity = 1.25f;
	protected String modId = null;
	protected String displayName = null;
	protected boolean doCraftCheck = true;
	protected boolean canBeCrafted = true;
	protected IWeaponCallback callback = null;
	
	public ItemLongbow(String unlocName, ToolMaterialEx toolMaterial)
	{
		maxStackSize = 1;
		material = toolMaterial;
        setMaxDamage((int) (toolMaterial.getMaxUses() * 1.5f));	// Originally 384
        setCreativeTab(CreativeTabsSW.TAB_SW);
		setRegistryName(unlocName);
		setTranslationKey(unlocName);
//		drawTicks = MathHelper.floor(ConfigHandler.multiplierLongbow * 20.0f);
//		maxVelocity = ;
//		drawTicks = ;
		
        addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                ItemStack itemstack = entityIn.getActiveItemStack();
                return !itemstack.isEmpty() ? MathHelper.clamp((float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / getDrawTicks(), 0.0f, 1.0f) : 0.0F;
            }
        });
        addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
	}

	public ItemLongbow(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
		displayName = "longbow_custom";
	}
	
	public ItemLongbow(String unlocName, String externalModId, ToolMaterialEx material, IWeaponCallback weaponCallback)
	{
		this(unlocName, externalModId, material);
		callback = weaponCallback;
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
	
	protected ItemStack findAmmo(EntityPlayer player)
    {
        if (isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (isArrow(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }
	
	/*public ItemStack findQuiver(EntityPlayer player)
	{
        // Is the item a quiver?
        if(player.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemQuiverArrow)
        {
        	return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if(player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemQuiverArrow)
        {
        	return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
        	// Only look for a quiver in the hotbar
        	for (int i = 0; i < 9 /*player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

		        // Is the item a quiver?
                if(itemstack.getItem() instanceof ItemQuiverArrow)
		        {
		        	return itemstack;
		        }
            }
        	
            return ItemStack.EMPTY;
        }
	}*/
	
	/**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
	@Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = findAmmo(entityplayer);
            
            int i = getMaxItemUseDuration(stack) - timeLeft;
            i = ForgeEventFactory.onArrowLoose(stack, worldIn, (EntityPlayer)entityLiving, i, itemstack != null || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag)
            {
                if (itemstack.isEmpty())
                    itemstack = new ItemStack(Items.ARROW);

                float f = getArrowSpeed(i);

                if (f >= 0.1D)
                {
                    boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow ? ((ItemArrow)itemstack.getItem()).isInfinite(itemstack, stack, entityplayer) : false);

                    if (!worldIn.isRemote)
                    {
                        ItemArrow itemarrow = ((ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW));
                        EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
                        entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 0.5F);
                        //entityarrow.setDamage(entityarrow.getDamage() * 1.25f);
                        
//                        if (f == maxVelocity)
                        if (i == getDrawTicks())
                        {
                            entityarrow.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                        if (j > 0)
                        {
                            entityarrow.setDamage(entityarrow.getDamage() + j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                        if (k > 0)
                        {
                            entityarrow.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
                        {
                            entityarrow.setFire(100);
                        }

                        stack.damageItem(1, entityplayer);

                        if (flag1)
                        {
                            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.spawnEntity(entityarrow);
                    }

                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!flag1)
                    {
                        itemstack.shrink(1);
                        
                        /*if(!)
                    	{
                        	// Make sure to set the quiver contents to make sure that the arrow is used up
                        	if(quiverHandler != null && quiverSlot != -1)
                            	quiverHandler.setInventorySlotContents(quiverSlot, itemstack);
                    	}
                        else*/ if (/*quiver.isEmpty() &&*/ itemstack.isEmpty())
                        {
                            entityplayer.inventory.deleteStack(itemstack);
                        }
                    }

                    entityplayer.addStat(StatList.getObjectUseStats(this));
                }
            }
        }
    }
	
	/**
     * Gets the velocity of the arrow entity from the longbow's charge
     */
    public float getArrowSpeed(int charge)
    {
        float f = charge / 20.0F;		// Draw time in seconds
        f = (f * f + f * 2.0F) / 3.0F;
        
        float maxArrowSpeed = getMaxArrowSpeed();

        if (f > maxArrowSpeed)
        {
            f = maxArrowSpeed;
        }

        return f;
    }
    
    public int getArrowSlotToUseFirst(@Nonnull IItemHandler quiverHandler)
	{
		for(int i = 0; i < quiverHandler.getSlots(); i++)
		{
			ItemStack stack = quiverHandler.getStackInSlot(i);
			if(!stack.isEmpty())
				return i;
		}
		
		return -1;
	}
    
    /**
     * Called when the equipped item is right clicked.
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack bow = playerIn.getHeldItem(handIn);
        boolean flag = false;
        
        // Attempt to find a quiver first
        /*ItemStack quiver = findQuiver(playerIn);
        if(!quiver.isEmpty())
        {
        	//InventoryQuiverArrow invQuiver = new InventoryQuiverArrow(quiver);
        	//flag = invQuiver.getArrowSlotToUseFirst() != -1;
        	IItemHandler quiverHandler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        	flag = getArrowSlotToUseFirst(quiverHandler) != -1;
        }
        // If none can be found then find arrows lying around in the inventory
        if(!flag)*/
        	flag = !findAmmo(playerIn).isEmpty() || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bow) > 0;

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(bow, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.capabilities.isCreativeMode && !flag)
        {
            return flag ? new ActionResult(EnumActionResult.PASS, bow) : new ActionResult(EnumActionResult.FAIL, bow);
        }
        
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, bow);
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
    	if(toRepair.isEmpty() || repair.isEmpty())
    		return false;
    	if( material.doesOreDictMatch(repair))
    		return true;
    	return super.getIsRepairable(toRepair, repair);
    }
    
    /*public ItemStack getRepairItemStack()
    {
		return material.getMaterial().getRepairItemStack();
    }*/
    
    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
		if(displayName != null)
		{
			String name = I18n.translateToLocalFormatted(String.format("item.%s:%s.name", ModSpartanWeaponry.ID, displayName), I18n.translateToLocal(material.getFullUnlocName()));
			return name;
		}
		return super.getItemStackDisplayName(stack);
		
        //return ("" + I18n.translateToLocal(getUnlocalizedNameInefficiently(stack) + ".name")).trim();
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
	
	/**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		boolean isShiftPressed = GuiScreen.isShiftKeyDown();
		
    	if(doCraftCheck && worldIn != null)
    	{
    		if(material.getModId() == ModSpartanWeaponry.ID)
    		{
		    	List<ItemStack> ores = OreDictionary.getOres(material.getOreDictForRepairMaterial(), false);
		    	if(ores == null || ores.isEmpty())
		    		canBeCrafted = false;
    		}
	    	doCraftCheck = false;
    	}
    	else if(!ConfigHandler.forceDisableUncraftableTooltips && !canBeCrafted)
    		tooltip.add(TextFormatting.RED + StringHelper.translateFormattedString("cantCraftMissingMaterial", "tooltip", ModSpartanWeaponry.ID, StringHelper.translateString(material.getOreDictForRepairMaterial(), "material", material.getModId())));
    	
    	if(callback != null)
    	{
    		callback.onTooltip(material, stack, worldIn, tooltip, flagIn);
    		tooltip.add("");
    	}

		tooltip.add(TextFormatting.GOLD + StringHelper.translateFormattedString("description", "tooltip", ModSpartanWeaponry.ID,
				isShiftPressed ? TextFormatting.DARK_GRAY + StringHelper.translateString("showingDescription", "tooltip", ModSpartanWeaponry.ID) :
					TextFormatting.DARK_GRAY + StringHelper.translateFormattedString("showDescription", "tooltip", ModSpartanWeaponry.ID, TextFormatting.AQUA + "SHIFT" + TextFormatting.DARK_GRAY)));
    	if(GuiScreen.isShiftKeyDown())
    	{
	    	tooltip.add(StringHelper.translateString("longbow", "tooltip"));
	    	tooltip.add(StringHelper.translateString("longbow.desc", "tooltip"));
    	}
//    	else
//    		tooltip.add(TextFormatting.DARK_GRAY + StringHelper.translateFormattedString("showDetails", "tooltip", ModSpartanWeaponry.ID, TextFormatting.AQUA + "SHIFT" + TextFormatting.DARK_GRAY));
    	
    	tooltip.add("");
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("modifiers.ammo.type", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY + StringHelper.translateString("modifiers.ammo.arrow", "tooltip")));
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("modifiers.longbow.draw_length", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY + "" + ((float)getDrawTicks() / 20.0f)));
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("modifiers.longbow.speed_multiplier", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY + StringHelper.translateFormattedString("modifiers.longbow.speed_multiplier.value", "tooltip", ModSpartanWeaponry.ID, getMaxArrowSpeed())));
    	tooltip.add("");
    }
    
    @Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
    	if(callback != null)
    		callback.onCreateItem(material, stack);
		super.onCreated(stack, worldIn, playerIn);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) 
	{
		if(isInCreativeTab(tab))
		{
			ItemStack stack = new ItemStack(this);
			if(callback != null)
				callback.onCreateItem(material, stack);
			items.add(stack);
		}
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
		if(callback != null && entityIn instanceof EntityLivingBase)
			callback.onItemUpdate(material, stack, worldIn, (EntityLivingBase)entityIn, itemSlot, isSelected);
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	/*@Override
	public HudQuiverAmmo createHudElement(EntityPlayer player, ItemStack stack, ItemStack secondaryStack) 
	{
		return new HudQuiverAmmo(64, 20, secondaryStack);
	}*/
	
/*	@Override
	public Class<? extends HudElement> getHudClass()
	{
		return HudQuiverAmmo.class;
	}*/

	/*@Override
	public boolean requiresItemInHotbar() 
	{
		return true;
	}*/

	@Override
	public Class<? extends ItemQuiverBase> getRequiredQuiverClass() 
	{
		return ItemQuiverArrow.class;
	}
	
	public int getDrawTicks()
	{
//		return drawTicks;
		return MathHelper.floor((ConfigHandler.multiplierLongbow * 20.0f)) + (material.getHarvestLevel() * ConfigHandler.materialDrawModifierLongbow);
	}
	
	public float getMaxArrowSpeed()
	{
		return ConfigHandler.multiplierLongbow + ((float)material.getHarvestLevel() * ConfigHandler.materialSpeedModifierLongbow);
	}
}
