package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDagger extends ItemWeaponBase 
{
//	int ticker = 0;
	
	public ItemDagger(String unlocName, ToolMaterialEx material) 
	{
		super(unlocName, material, ConfigHandler.damageBaseDagger, ConfigHandler.damageMultiplierDagger, ConfigHandler.speedDagger, WeaponProperties.THROWABLE, WeaponProperties.EXTRA_DAMAGE_BACKSTAB);
		displayName = "dagger_custom";
	}
	
	public ItemDagger(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemDagger(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) 
	{
		super.onUpdate(stack, world, entity, itemSlot, isSelected);

		/*if(!isSelected || world.isRemote)
			return;
		
		++ticker;
		if(ticker >= 20)
		{
			Log.info("Rotation Yaw: " + entity.rotationYaw);
			ticker = 0;
		}*/
	}
	
	//@Override
    //public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    //{
        /*worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
            EntityThrownWeapon thrown = new EntityThrownWeapon(worldIn, playerIn);
            thrown.setWeapon(itemStackIn);
            float distance = (float)playerIn.getDistance(playerIn.posX + playerIn.motionX, playerIn.posY + playerIn.motionY, playerIn.posZ + playerIn.motionZ);
            thrown.setAim(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.7F + distance, 0.5F);
            thrown.setDamage(this.getAttackDamage());
            
            // Apply enchantments as necessary
            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, itemStackIn);
            if (j > 0)
            {
            	thrown.setDamage(thrown.getDamage() + (double)j * 0.5D + 0.5D);
            }
            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, itemStackIn);
            if (k > 0)
            {
            	thrown.setKnockbackStrength(k);
            }
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, itemStackIn) > 0)
            {
            	thrown.setFire(100);
            }
            
            if(playerIn.capabilities.isCreativeMode)
            	thrown.pickupStatus = PickupStatus.CREATIVE_ONLY;
            else
            {
                --itemStackIn.stackSize;
                if(itemStackIn.stackSize <= 0)
                	playerIn.inventory.deleteStack(itemStackIn);
            }
            
            worldIn.spawnEntityInWorld(thrown);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));*/

	/*	ItemStack stack = playerIn.getHeldItem(hand);
        playerIn.setActiveHand(hand);
        return new ActionResult(EnumActionResult.SUCCESS, stack);
    	//return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }
    
	@Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
		if(entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entityLiving;

            int charge = this.getMaxItemUseDuration(stack) - timeLeft;
            
            if(charge >= 10)
            	charge = 10;
			
			if (!worldIn.isRemote && charge > 2)
	        {
	            EntityThrownWeapon thrown = new EntityThrownWeapon(worldIn, player);
	            thrown.setWeapon(stack);
	            //float distance = (float)player.getDistance(player.posX + player.motionX, playerIn.posY + playerIn.motionY, playerIn.posZ + playerIn.motionZ);
	            thrown.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 0.8f * (charge / 20.0f + 0.5f), 0.5f);
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
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }*/

}
