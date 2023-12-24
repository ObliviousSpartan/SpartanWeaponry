package com.oblivioussp.spartanweaponry.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoomerang;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.init.EnchantmentRegistrySW;
import com.oblivioussp.spartanweaponry.init.SoundRegistry;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemBoomerang extends ItemThrowingWeapon
{
	public ItemBoomerang(String unlocName, ToolMaterialEx toolMaterial) 
	{
		super(unlocName, toolMaterial, ConfigHandler.damageBaseBoomerang, ConfigHandler.damageMultiplierBoomerang, ConfigHandler.meleeSpeedBoomerang, 1, WeaponProperties.THROWABLE);
		displayName = "boomerang_custom";
        this.setMaxDamage(materialEx.getMaxUses());
        this.maxChargeTicks = ConfigHandler.chargeTicksBoomerang;
	}
	
	public ItemBoomerang(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemBoomerang(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}
	
	@Override
	public EntityThrownWeapon createThrownWeaponEntity(World world, EntityPlayer player)
	{
		return new EntityBoomerang(world, player);
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
			//if(ammoCount > 0)
			//{
				EntityPlayer player = (EntityPlayer)entityLiving;
				this.throwVelocity = 2.0f;
	
	            int charge = this.getMaxItemUseDuration(stack) - timeLeft;
	            
	            if(charge >= 5)
	            	charge = 5;
				
				if (!worldIn.isRemote && charge > 2)
		        {
		            EntityBoomerang thrown = (EntityBoomerang) createThrownWeaponEntity(worldIn, player);
		            
		            if(thrown == null)	return;
		            
		            thrown.setWeapon(stack);
		            
		            int i = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.THROWING_RANGE, stack);
		            thrown.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, this.throwVelocity * ((i * 0.2f) + 1) * (charge / 10.0f + 0.5f), 0.5F);
		            thrown.setPosition(player.posX, player.posY + (player.getEyeHeight() * 0.9D) - 0.10000000149011612D, player.posZ);
		            thrown.setDamage(this.getDirectAttackDamage() + 1.0d);
		            thrown.setMaxDistance((charge / 5.0D) * (EntityBoomerang.MAX_DISTANCE_LIMIT - 3.0d) + 3.0d + (i * 3.0d));
		            
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
			         
		            	worldIn.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundRegistry.BOOMERANG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		            	worldIn.spawnEntity(thrown);
		            }
		        }
	
		        player.addStat(StatList.getObjectUseStats(this));
			//}
		}
    }
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        /*if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0f, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.attackSpeed - 4.0D, 0));
        }*/

        return multimap;
    }
	
	/**
     * allows items to add custom lines of information to the mouseover description
     */
    /*@SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	//tooltip.add(TextFormatting.RED + StringHelper.translateString("wip", "dev"));
    	super.addInformation(stack, worldIn, tooltip, flagIn);
    }*/
}
