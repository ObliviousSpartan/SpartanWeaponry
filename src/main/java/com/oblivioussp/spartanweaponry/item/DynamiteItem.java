package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.DynamiteEntity;
import com.oblivioussp.spartanweaponry.util.Config;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class DynamiteItem extends Item 
{
	public DynamiteItem(String regName, Properties properties) 
	{
		super(properties);
		this.setRegistryName(ModSpartanWeaponry.ID, regName);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.abilities.isCreativeMode)
        {
            itemstack.shrink(1);
        }

        worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.NEUTRAL, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
        	DynamiteEntity entityDyanmite = new DynamiteEntity(playerIn, worldIn);
        	entityDyanmite.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.75F, 1.0F);
            worldIn.addEntity(entityDyanmite);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        return ActionResult.resultSuccess(itemstack);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".dynamite.desc", (float)Config.INSTANCE.fuseTicksDynamite.get() / 20.0f).mergeStyle(TextFormatting.GRAY));
		if(Config.INSTANCE.disableTerrainDamage.get())
			tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".dynamite.no_terrain_damage").mergeStyle(TextFormatting.GRAY));
		else
			tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".dynamite.terrain_damage").mergeStyle(TextFormatting.GRAY));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
