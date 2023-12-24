package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.item.ItemBolt;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.World;

public class EntityBoltDiamond extends EntityBoltTipped
{

	public EntityBoltDiamond(World worldIn, double x, double y, double z) 
	{
		super(worldIn, x, y, z);
	}

	public EntityBoltDiamond(World worldIn, EntityLivingBase shooter) 
	{
		super(worldIn, shooter);
	}

	public EntityBoltDiamond(World worldIn)
	{
		super(worldIn);
	}

	@Override
	protected ItemStack getArrowStack() 
	{
		if (this.customPotionEffects.isEmpty() && this.potion == PotionTypes.EMPTY)
        {
            return new ItemStack(ItemRegistrySW.boltDiamond);
        }
		
        ItemStack itemstack = new ItemStack(ItemRegistrySW.boltTippedDiamond);
        PotionUtils.addPotionToItemStack(itemstack, this.potion);
        PotionUtils.appendEffects(itemstack, this.customPotionEffects);
        return itemstack;
	}

	@Override
	public BoltType getBoltType() 
	{
		return BoltType.DIAMOND;
	}

	@Override
	protected ItemBolt getBoltItem() 
	{
		return ItemRegistrySW.boltDiamond;
	}
}
