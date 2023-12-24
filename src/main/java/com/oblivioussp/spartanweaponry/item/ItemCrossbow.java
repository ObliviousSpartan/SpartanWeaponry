package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBolt;
import com.oblivioussp.spartanweaponry.init.EnchantmentRegistrySW;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.init.SoundRegistry;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.NBTHelper;
import com.oblivioussp.spartanweaponry.util.Quaternion;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemCrossbow extends ItemSW implements /*IHudQuiverDisplay,*/ IHudLoadState
{
	protected ToolMaterialEx material;
	protected String modId = null;
	protected String displayName = null;
	protected boolean doCraftCheck = true;
	protected boolean canBeCrafted = true;
	protected IWeaponCallback callback = null;
	
	public static final String NBT_IS_LOADED = "isLoaded";
	public static final String nbtAmmoStack = "ammoStack";

	public ItemCrossbow(String unlocName, ToolMaterialEx toolMaterial) 
	{
		super(unlocName);
		/*super();
		setCreativeTab(CreativeTabsSW.TAB_SW);
		setRegistryName(unlocName);*/
		//setUnlocalizedName(unlocName);
		
		material = toolMaterial;
		maxStackSize = 1;
		setMaxDamage((int) (toolMaterial.getMaxUses() * 1.5f));
		addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null && !(entityIn instanceof EntityPlayer))
                {
                    return 0.0F;
                }
                
                ItemStack itemstack = entityIn.getActiveItemStack();
                //return itemstack != null && itemstack.getItem() instanceof ItemCrossbow  ? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F : 0.0F;
            	if(NBTHelper.getBoolean(stack, NBT_IS_LOADED))
            		return 1.0f;
                if(itemstack != null && itemstack.isItemEqual(stack) && itemstack.getItem() instanceof ItemCrossbow)
                {
                	return (float)(getLoadProgress(stack, entityIn));
                }
                return 0.0f;
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
	
	public ItemCrossbow(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
		displayName = "crossbow_custom";
	}
	
	public ItemCrossbow(String unlocName, String externalModId, ToolMaterialEx material, IWeaponCallback weaponCallback)
	{
		this(unlocName, externalModId, material);
		callback = weaponCallback;
	}
	
	@Override
	public String getTranslationKey()
	{
		if(modId != null)
			return StringHelper.getItemUnlocalizedName(super.getTranslationKey(), modId);
		
		return super.getTranslationKey();
	}
	
	@Override
	public String getTranslationKey(ItemStack itemStack)
	{
		if(modId != null)
			return StringHelper.getItemUnlocalizedName(super.getTranslationKey(), modId);
		
		return super.getTranslationKey();
	}

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        //if(oldStack.hasTagCompound() && newStack.hasTagCompound() && oldStack.getTagCompound() != newStack.getTagCompound())
            //return true;
    	if(!ItemStack.areItemStackTagsEqual(oldStack, newStack))
    		return true;
        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
    }

    protected ItemStack findAmmo(EntityPlayer player)
    {
        if (isBolt(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (isBolt(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (isBolt(itemstack))
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
        if(player.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemQuiverBolt)
        {
        	return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if(player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemQuiverBolt)
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
                if(itemstack.getItem() instanceof ItemQuiverBolt)
		        {
		        	return itemstack;
		        }
            }
        	
            return ItemStack.EMPTY;
        }
	}*/

    protected boolean isBolt(@Nullable ItemStack stack)
    {
        return stack != null && stack.getItem() instanceof ItemBolt;
    }
    
    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	if(!NBTHelper.getBoolean(stack, NBT_IS_LOADED))
    	{
    		if(entityLiving instanceof EntityPlayer)
    		{
    			EntityPlayer player = (EntityPlayer) entityLiving;
                //boolean isCreativeOrInfinite = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
        		ItemStack bolt = findAmmo(player);
    			if(bolt.isEmpty())
    				bolt = new ItemStack(ItemRegistrySW.bolt);
    			
        		boolean isCreativeOrArrowInfinite = player.capabilities.isCreativeMode || (bolt.getItem() instanceof ItemBolt ? ((ItemBolt)bolt.getItem()).isInfinite(bolt, stack, player) : false);

        		int count = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.CROSSBOW_SPREADSHOT, stack) > 0 ? 3 : 1;
        		// Create a copy of the bolt, then save it to NBT.
        		ItemStack boltToStore = bolt.copy();
        		boltToStore.setCount(count);
        		NBTTagCompound nbtBolt = new NBTTagCompound();
        		boltToStore.writeToNBT(nbtBolt);
        		NBTHelper.setTagCompound(stack, nbtAmmoStack, nbtBolt);
        		
        		if (!isCreativeOrArrowInfinite)
                {
        			bolt.shrink(1);
        			
        			if (bolt.isEmpty())
                    {
                    	player.inventory.deleteStack(bolt);
                    }
                }
        		worldIn.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundRegistry.CROSSBOW_LOAD, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) * 0.5F);
    			player.getCooldownTracker().setCooldown(this, ConfigHandler.crossbowTicksCooldown);
        		NBTHelper.setBoolean(stack, NBT_IS_LOADED, true);
    			//player.resetCooldown();
    		}
    	}
    	return stack;
    }
    
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
            //ItemStack itemstack = findAmmo(entityplayer);
            ItemStack itemstack = ItemStack.EMPTY;
            NBTTagCompound tag = NBTHelper.getTagCompound(stack, nbtAmmoStack);
            if(tag != null)
            	itemstack = new ItemStack(tag);
            
            int i = getMaxItemUseDuration(stack) - timeLeft;
            
            //i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, (EntityPlayer)entityLiving, i, itemstack != null || flag);
            if (i < 0 || !NBTHelper.getBoolean(stack, NBT_IS_LOADED)) return;

            if (!itemstack.isEmpty() || flag)
            {
                if (itemstack.isEmpty())
                {
                    itemstack = new ItemStack(ItemRegistrySW.bolt);
                }

                float vel = getBoltSpeed();

                //if ((double)f >= 0.99D)
                //{
                    boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemBolt ? ((ItemBolt)itemstack.getItem()).isInfinite(itemstack, stack, entityplayer) : false);

                    if (!worldIn.isRemote)
                    {
                        ItemBolt itemBolt = ((ItemBolt)(itemstack.getItem() instanceof ItemBolt ? itemstack.getItem() : ItemRegistrySW.bolt));
//                        EntityBolt entityBolt = itemBolt.createBolt(worldIn, itemstack, entityplayer);
                        
                        // Account for lack of accuracy.
                        int aimTicks = getAimTicks(stack);
                        int inaccuracy = aimTicks - i;
                        float inaccuracyModifier = 0.0f;
                        if(i >= aimTicks)
                        	inaccuracy = 0;		// Max accuracy
                        
                        //float maxInaccuracy = ConfigHandler.crossbowInaccuracyMax;	// Max inaccuracy value (not accounting for variation)
                        
                        if(inaccuracy != 0)		// Apply inaccuracy if there is any.
                        {
                        	//offsetPitch = (maxInaccuracy * (inaccuracy / (float)Reference.DefaultCrossbowInaccuracyTicks)  * (itemRand.nextFloat() - 0.5f));		// Final offset for pitch and yaw
                        	//offsetPitch -= (maxInaccuracy / 2.0f);
                        	//offsetYaw = (maxInaccuracy * (inaccuracy / (float)Reference.DefaultCrossbowInaccuracyTicks)  * (itemRand.nextFloat() - 0.5f));
                        	//offsetYaw -= (maxInaccuracy / 2.0f);
                        	inaccuracyModifier = 10.0f * ((float)inaccuracy / aimTicks);
                        }
                        
                        /*entityBolt.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, vel * 3.0F, inaccuracyModifier);
                        //entityBolt.setDamage(entityBolt.getDamage() * 2.5f);
                        //entityBolt.setDamage(ConfigHandler.boltBaseDamage);
                        //entityBolt.setIsCritical(true);
                        
                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                        if (j > 0)
                        {
                            entityBolt.setDamage(entityBolt.getDamage() + j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                        if (k > 0)
                        {
                            entityBolt.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
                        {
                            entityBolt.setFire(100);
                        }

                        stack.damageItem(1, entityplayer);

                        if (flag1)
                        {
                            entityBolt.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }*/
                        
                        spawnProjectile(stack, itemBolt, itemstack, worldIn, entityplayer, flag1, inaccuracyModifier, 0.0f);
                        if(itemstack.getCount() > 1)
                        {
                            spawnProjectile(stack, itemBolt, itemstack, worldIn, entityplayer, true, inaccuracyModifier, -10.0f);
                            spawnProjectile(stack, itemBolt, itemstack, worldIn, entityplayer, true, inaccuracyModifier, 10.0f);
                        }
                        
                        int damage = itemstack.getCount() > 1 ? 3 : 1;
                        stack.damageItem(damage, entityplayer);
                        
                        NBTHelper.setBoolean(stack, NBT_IS_LOADED, false);
                        NBTHelper.setTagCompound(stack, nbtAmmoStack, new NBTTagCompound());
//                        worldIn.spawnEntity(entityBolt);
                    }

                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundRegistry.CROSSBOW_FIRE, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + vel * 0.5F);

                    entityplayer.addStat(StatList.getObjectUseStats(this));
                //}
            }
        }
    }
    
    /**
     * Gets the velocity of the bolt entity from the crossbow's charge
     */
    public float getBoltSpeed()
    {
    	return ConfigHandler.crossbowBaseBoltSpeed + ((float)material.getHarvestLevel() * ConfigHandler.crossbowMaterialBoltSpeedModifier);
    }
    
    protected void spawnProjectile(ItemStack crossbow, ItemBolt boltItem, ItemStack boltStack, World world, EntityPlayer player, boolean noPickup, float inaccuracyModifier, float projectileAngle)
    {
    	EntityBolt bolt = boltItem.createBolt(world, boltStack, player);
    	bolt.setIsCritical(true);
    	
    	Vec3d lookVec = player.getLook(1.0f);
    	Vec3d vector = new Vec3d(lookVec.x, lookVec.y, lookVec.z);
    	
    	if(projectileAngle != 0.0f)
    	{
        	Vec3d playerUpVec = calculateEntityViewVector(player.rotationPitch - 90.0f, player.rotationYaw);
    		Quaternion quat = new Quaternion(playerUpVec, projectileAngle, true);
    		vector = quat.transformVector(lookVec);
    	}
    	
//    	if(projectileAngle != 0.0f)
//    		vector = vector.rotateYaw(projectileAngle * ((float)Math.PI / 180.0f));
    	bolt.shoot(vector.x, vector.y, vector.z, getBoltSpeed() * 3.0f, inaccuracyModifier);
 //   	bolt.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, getBoltVelocity() * 3.0f, inaccuracyModifier);

        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, crossbow);

        if (j > 0)
        {
        	bolt.setDamage(bolt.getDamage() + j * 0.5D + 0.5D);
        }

        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, crossbow);

        if (k > 0)
        {
        	bolt.setKnockbackStrength(k);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, crossbow) > 0)
        {
        	bolt.setFire(100);
        }

        if (noPickup || projectileAngle != 0.0f)
        {
        	bolt.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
        }
        
        world.spawnEntity(bolt);
    }
    
    private Vec3d calculateEntityViewVector(float pitch, float yaw)
    {
    	/*float pitchRad = pitch * ((float)Math.PI / 180.0f);
    	float yawRad = yaw * ((float)Math.PI / 180.0f);
    	
    	float yawCos = MathHelper.cos(yawRad);
    	float yawSin = MathHelper.sin(yawRad);
    	float pitchCos = MathHelper.cos(pitchRad);
    	float pitchSin = MathHelper.cos(pitchRad);
    	return new Vec3d((double)(yawSin * pitchCos), (double)(-pitchSin), (double)(yawCos * pitchCos));*/
    	
    	float degToRad = (2.0f * (float)Math.PI) / 360.0f;
    	float yawCos = MathHelper.cos(-yaw * degToRad - (float)Math.PI);
    	float yawSin = MathHelper.sin(-yaw * degToRad - (float)Math.PI);
    	float pitchCos = -MathHelper.cos(-pitch * degToRad - (float)Math.PI);
    	float pitchSin = MathHelper.sin(-pitch * degToRad - (float)Math.PI);
    	
    	return new Vec3d((double)(yawSin * pitchCos), (double)pitchSin, (double)(yawCos * pitchCos));
    }
    
    /*public int getArrowSlotToUseFirst(@Nonnull IItemHandler quiverHandler)
	{
		for(int i = 0; i < quiverHandler.getSlots(); i++)
		{
			ItemStack stack = quiverHandler.getStackInSlot(i);
			if(!stack.isEmpty())
				return i;
		}
		
		return -1;
	}*/

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
    	if(!NBTHelper.getBoolean(stack, NBT_IS_LOADED))
    		return getFullLoadTicks(stack);
        return 72000;
    }
    
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
    	if(!NBTHelper.getBoolean(stack, NBT_IS_LOADED))
    		return EnumAction.NONE;
        return EnumAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack stack = playerIn.getHeldItem(handIn);
        boolean flag = false;
        
        flag = !findAmmo(playerIn).isEmpty();

        /*if (!playerIn.capabilities.isCreativeMode && !flag && !NBTHelper.getBoolean(stack, nbtIsLoaded) && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) == 0)
        {
            return !flag ? new ActionResult(EnumActionResult.FAIL, stack) : new ActionResult(EnumActionResult.PASS, stack);
        }*/
        // Allow right-clicking when: 
        // - Creative mode is enabled (Creative = Unlimited Ammo) OR
        // - Crossbow is loaded (Already has ammo in it; allows aiming) OR
        // - Ammo is available for loading into the crossbow OR
        // - The Crossbow has the Infinity Enchantment
        if(playerIn.capabilities.isCreativeMode || NBTHelper.getBoolean(stack, NBT_IS_LOADED) || flag  || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0)
        {
	        playerIn.setActiveHand(handIn);
	        return new ActionResult(EnumActionResult.SUCCESS, stack);
        }
        return !flag ? new ActionResult(EnumActionResult.FAIL, stack) : new ActionResult(EnumActionResult.PASS, stack);
    }
	
    @Override
	public int getItemEnchantability(ItemStack stack)
	{
		if(material == null)
			return 1;
		return material.getEnchantability();
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
	
	/*@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND || equipmentSlot == EntityEquipmentSlot.OFFHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 2.0f - 4.0D, 0));
        }

        return multimap;
    }*/
    
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
    	
    	ItemStack bolt = ItemStack.EMPTY;
    	
    	if(!stack.hasTagCompound())
    		stack.setTagCompound(new NBTTagCompound());
    	
    	if(stack.getTagCompound().hasKey(nbtAmmoStack));
    		bolt = new ItemStack(stack.getTagCompound().getCompoundTag(nbtAmmoStack));
    	if(bolt != null && !bolt.isEmpty())
    	{
    		tooltip.add(StringHelper.translateFormattedString("crossbow.loaded_bolt", "tooltip", ModSpartanWeaponry.ID, String.format("[%s x%d]", TextFormatting.AQUA.toString() + bolt.getDisplayName() + TextFormatting.WHITE.toString(), bolt.getCount())));
    		tooltip.add("");
    	}
    	
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
	    	tooltip.add(StringHelper.translateString("crossbow", "tooltip"));
	    	tooltip.add(StringHelper.translateString("crossbow.desc", "tooltip"));
	    	tooltip.add(StringHelper.translateString("crossbow.desc2", "tooltip"));
    	}
//    	else
//    		tooltip.add(TextFormatting.DARK_GRAY + StringHelper.translateFormattedString("showDetails", "tooltip", ModSpartanWeaponry.ID, TextFormatting.AQUA + "SHIFT" + TextFormatting.DARK_GRAY));
    	
    	tooltip.add("");
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("modifiers.ammo.type", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY + StringHelper.translateString("modifiers.ammo.bolt", "tooltip")));
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("modifiers.crossbow.load_time", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY + "" + (float)getFullLoadTicks(stack) / 20.0f));
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("modifiers.crossbow.aim_time", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY + "" + (float)getAimTicks(stack) / 20.0f));
    	tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("modifiers.crossbow.speed_multiplier", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY + StringHelper.translateFormattedString("modifiers.crossbow.speed_multiplier.value", "tooltip", ModSpartanWeaponry.ID, getBoltSpeed())));
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

	/**
     * Allow the item one last chance to modify its name used for the
     * tool highlight useful for adding something extra that can't be removed
     * by a user in the displayed name, such as a mode of operation.
     *
     * @param item the ItemStack for the item.
     * @param displayName the name that will be displayed unless it is changed in this method.
     */
    @Override
    public String getHighlightTip( ItemStack item, String displayName )
    {
    	if(!item.hasTagCompound())
    		item.setTagCompound(new NBTTagCompound());
    	
    	ItemStack stack = new ItemStack(item.getTagCompound().getCompoundTag(nbtAmmoStack));
    	if(!stack.isEmpty())
    		return String.format("%s (%s x%d)", displayName, stack.getDisplayName(), stack.getCount());
    	return displayName;
    }

	/*@Override
	public Class<? extends ItemQuiverBase> getRequiredQuiverClass() 
	{
		return ItemQuiverBolt.class;
	}*/

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) 
	{
		//return super.canApplyAtEnchantingTable(stack, enchantment);
		/*return enchantment == Enchantments.POWER || enchantment == Enchantments.PUNCH || enchantment == Enchantments.FLAME || 
				enchantment == Enchantments.INFINITY || super.canApplyAtEnchantingTable(stack, enchantment);*/
		return ConfigHandler.enchantmentWhitelistCrossbow.contains(enchantment.getRegistryName()) || super.canApplyAtEnchantingTable(stack, enchantment);
	}

	@Override
	public boolean isLoaded(ItemStack stack) 
	{
		if(!stack.hasTagCompound())
			return false;
		return stack.getTagCompound().getBoolean(NBT_IS_LOADED);
	}

	@Override
	public float getLoadProgress(ItemStack stack, EntityLivingBase entity)
	{
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		
//		return (entity.isHandActive() && !stack.getTagCompound().getBoolean(NBT_IS_LOADED)) ? 1.0f - ((float)entity.getItemInUseCount() / (float)getMaxItemUseDuration(stack)) : 0.0f;
		
		// Added a paradox-absorbing crumple zone by checking load ticks so a division by zero doesn't occur
		int fullLoadTicks = getFullLoadTicks(stack);
		return (entity.isHandActive() && !stack.getTagCompound().getBoolean(NBT_IS_LOADED)) ? fullLoadTicks == 0.0f ? 1.0f : 1.0f - MathHelper.clamp((float)getLoadingTicks(stack, entity) / (float)fullLoadTicks, 0.0f, 1.0f) : 0.0f;
	}
	
	protected int getFullLoadTicks(ItemStack stack)
	{
		int i = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.CROSSBOW_CHARGE, stack);
		// Only needs to be clamped on the lower bound to prevent load ticks being 0 or less
		return Math.max(ConfigHandler.crossbowTicksToLoad - (4 * i) + (material.getHarvestLevel() * ConfigHandler.crossbowMaterialLoadModifier), 1);
	}
	
	protected int getLoadingTicks(ItemStack stack, EntityLivingBase entity)
	{
		return entity.getItemInUseCount();
	}
	
	public int getAimTicks(ItemStack stack)
	{
		int i = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.CROSSBOW_SHARPSHOOTER, stack);
		// Only needs to be clamped on the lower bound to prevent aim ticks being 0 or less
		return Math.max(Defaults.CrossbowInaccuracyTicks - (2 * i) + (material.getHarvestLevel() * ConfigHandler.crossbowMaterialAimModifier), 1);
	}
}
