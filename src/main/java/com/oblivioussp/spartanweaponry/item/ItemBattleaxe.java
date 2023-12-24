package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class ItemBattleaxe extends ItemWeaponBase
{
//	protected static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);

	public ItemBattleaxe(String unlocName, ToolMaterialEx material) 
	{
		super(unlocName, material, ConfigHandler.damageBaseBattleaxe, ConfigHandler.damageMultiplierBattleaxe, ConfigHandler.speedBattleaxe, WeaponProperties.TWO_HANDED_1, WeaponProperties.VERSATILE);
		this.setHarvestLevel("axe", material.getHarvestLevel());
		displayName = "battleaxe_custom";
	}
	
	public ItemBattleaxe(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
		Material material = state.getMaterial();
		if(material != Material.WOOD && material != Material.PLANTS && material != Material.VINE)
		{
			for(String type : getToolClasses(stack))
			{
				if(state.getBlock().isToolEffective(type, state))
					return materialEx.getEfficiency();
			}
			return super.getDestroySpeed(stack, state);
		}
		else
			return materialEx.getEfficiency();
    }
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemBattleaxe(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}
}
