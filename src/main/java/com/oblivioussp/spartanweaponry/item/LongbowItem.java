package com.oblivioussp.spartanweaponry.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.trait.IRangedTraitCallback;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.client.ClientHelper;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class LongbowItem extends BowItem /*implements IHudQuiverDisplay*/
{
	protected WeaponMaterial material;
	protected float drawTime = 1.25f;
	protected float maxVelocity = 1.25f;
	protected String modId = null;
	protected String customDisplayName = null;
	protected boolean doCraftCheck = true;
	protected boolean canBeCrafted = true;
//	protected IWeaponCallback callback = null;
	
	public LongbowItem(String unlocName, Item.Properties prop, WeaponMaterial materialIn, boolean usingDeferredRegister)
	{
		super(prop.maxDamage((int)(materialIn.getMaxUses() * 2.0f)));
		if(!usingDeferredRegister)
			setRegistryName(unlocName);
		material = materialIn;
		maxVelocity = Defaults.MultiplierLongbow;
		
		// Modify load and aim ticks via traits
		if(material.hasAnyWeaponTrait())
		{
			for(WeaponTrait trait : material.getAllWeaponTraits())
			{
				IRangedTraitCallback callback = trait.getRangedCallback();
				if(trait.isRangedTrait() && callback != null)
				{
					drawTime = callback.modifyLongbowDrawTime(material, drawTime);
				}
			}
		}
		
		if(FMLEnvironment.dist.isClient())
			ClientHelper.registerLongbowPropertyOverrides(this);
	}
	
	public LongbowItem(String regName, Item.Properties prop, WeaponMaterial materialIn, String customDisplayNameIn, boolean usingDeferredRegister)
	{
		this(regName, prop, materialIn, usingDeferredRegister);
		if(materialIn.useCustomDisplayName())
			customDisplayName = customDisplayNameIn;
	}
	@Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft)
    {
        if (entityLiving instanceof PlayerEntity)
        {
        	PlayerEntity player = (PlayerEntity)entityLiving;
            boolean flag = player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = player.findAmmo(stack);

            int i = getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, (PlayerEntity)entityLiving, i, itemstack != null || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag)
            {
                if (itemstack.isEmpty())
                    itemstack = new ItemStack(net.minecraft.item.Items.ARROW);

                float f = getArrowSpeed(i);

                if (f >= 0.1D)
                {
                    boolean flag1 = player.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem ? ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, player) : false);

                    if (!worldIn.isRemote)
                    {
                    	ArrowItem itemarrow = ((ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : net.minecraft.item.Items.ARROW));
                        AbstractArrowEntity entityarrow = itemarrow.createArrow(worldIn, itemstack, player);
//                        entityarrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, f * 3.0F, 0.5F);
                        entityarrow.setDirectionAndMovement(player, player.rotationPitch, player.rotationYaw, 0.0F, f * 3.0F, 0.5F);
                        //entityarrow.setDamage(entityarrow.getDamage() * 1.25f);
                        
                        if(material.hasAnyWeaponTrait())
                        {
                        	for(WeaponTrait trait : material.getAllWeaponTraits())
                        	{
                        		if(trait.isRangedTrait() && trait.getRangedCallback() != null)
                        			trait.getRangedCallback().onProjectileSpawn(material, entityarrow);
                        	}
                        }
                        
                        if (f == maxVelocity)
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

                        stack.damageItem(1, player, (playerEntity) -> {
                        	playerEntity.sendBreakAnimation(player.getActiveHand());
                        });

                        if (flag1 || player.abilities.isCreativeMode && (itemstack.getItem() ==  net.minecraft.item.Items.SPECTRAL_ARROW || itemstack.getItem() ==  net.minecraft.item.Items.TIPPED_ARROW))
                        {
                            entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.addEntity(entityarrow);
                    }

                    worldIn.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!flag1)
                    {
                        itemstack.shrink(1);
                        if(itemstack.isEmpty())
                        	player.inventory.deleteStack(itemstack);
                    }

                    player.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

	
	/**
     * Gets the velocity of the arrow entity from the longbow's charge
     */
    public float getArrowSpeed(int charge)
    {
        float f = charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > maxVelocity)
        {
            f = maxVelocity;
        }

        return f;
    } 

	@Override
	public int getItemEnchantability()
	{
		return material.getEnchantability();
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) 
	{
		return material.getRepairMaterial().test(repair);
	}

	@Override
	public ITextComponent getDisplayName(ItemStack stack) 
	{
		if(customDisplayName == null)
			return super.getDisplayName(stack);
		return new TranslationTextComponent(customDisplayName, material.translateName());
	}
    
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
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
    	
    	if(material.hasAnyWeaponTrait())
    	{
			List<WeaponTrait> rangedTraits = material.getAllWeaponTraits().stream().filter((trait) -> trait.isRangedTrait()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			if(!rangedTraits.isEmpty())
			{			
				if(isShiftPressed)
					tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".showing_details").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
				else
					tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".show_details", TextFormatting.DARK_AQUA.toString() + "SHIFT").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
				tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits.material_bonus", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.AQUA));

	    		for(WeaponTrait matTrait : rangedTraits)
	    		{
	    			if(matTrait.isRangedTrait())
	    				matTrait.addTooltip(stack, tooltip, isShiftPressed);
	    		}
	        	tooltip.add(new StringTextComponent(""));
			}
    	}
		
    	if(isShiftPressed)
    	{
			tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.description", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".showing_details").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
    		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.longbow.desc", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.GRAY, TextFormatting.ITALIC));
    		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.longbow.desc_2", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.GRAY, TextFormatting.ITALIC));
    	}
    	else
			tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.description", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".show_details", TextFormatting.AQUA.toString() + "SHIFT").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
   	
    	
    	tooltip.add(new StringTextComponent(""));
    	tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.modifiers.ammo.type", ModSpartanWeaponry.ID), new TranslationTextComponent(String.format("tooltip.%s.modifiers.ammo.arrow", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
    	tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.modifiers.longbow.draw_length", ModSpartanWeaponry.ID), new TranslationTextComponent(String.format("tooltip.%s.modifiers.longbow.draw_length.value", ModSpartanWeaponry.ID), drawTime).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
    	tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.modifiers.longbow.speed_multiplier", ModSpartanWeaponry.ID), new TranslationTextComponent(String.format("tooltip.%s.modifiers.longbow.draw_length.value", ModSpartanWeaponry.ID), maxVelocity).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
    	tooltip.add(new StringTextComponent(""));
    }
	
	// ---- ---- ---- ---- ---- ---- ---- ----
	// Internal methods
	// ---- ---- ---- ---- ---- ---- ---- ----
    public float getNockProgress(ItemStack stack, LivingEntity shooter)
    {
    	return (float)(stack.getUseDuration() - shooter.getItemInUseCount()) / (20.0F * drawTime);
    }
}
