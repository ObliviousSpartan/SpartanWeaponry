package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBolt;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoltDiamond;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoltTipped;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemBoltDiamondTipped extends ItemBoltTipped 
{
	public ItemBoltDiamondTipped(String unlocName) 
	{
		super(unlocName, ConfigHandler.boltDiamondBaseDamage, ConfigHandler.boltDiamondRangeMultiplier, ConfigHandler.boltDiamondArmorPiercingFactor);
	}

    @Override
	public EntityBolt createBolt(World worldIn, ItemStack stack, EntityLivingBase shooter) 
    {
    	EntityBoltTipped bolt = new EntityBoltDiamond(worldIn, shooter);
    	bolt.setDamage(baseDamage);
        bolt.setPotionEffect(stack);
        return bolt;
	}

	@Override
    public String getItemStackDisplayName(ItemStack stack)
    {
    	String name = I18n.translateToLocalFormatted(String.format("item.%s:proj_tipped.%s", ModSpartanWeaponry.ID, PotionUtils.getPotionFromItem(stack).getNamePrefixed("")), StringHelper.translateString("bolt_diamond.name", "item"));
    	return name;
    }
}
