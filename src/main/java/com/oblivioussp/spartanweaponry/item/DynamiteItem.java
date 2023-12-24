package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.DynamiteEntity;
import com.oblivioussp.spartanweaponry.util.Config;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class DynamiteItem extends Item
{
	public DynamiteItem(Properties properties) 
	{
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack itemstack = playerIn.getItemInHand(handIn);

        if (!playerIn.getAbilities().instabuild)
        {
            itemstack.shrink(1);
        }

        levelIn.playSound((Player)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (levelIn.random.nextFloat() * 0.4F + 0.8F));
        levelIn.playSound((Player)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.TNT_PRIMED, SoundSource.NEUTRAL, 1.0F, 1.0F);

        if (!levelIn.isClientSide)
        {
        	DynamiteEntity entityDyanmite = new DynamiteEntity(playerIn, levelIn);
        	entityDyanmite.shootFromRotation(playerIn, playerIn.xRotO, playerIn.yRotO, 0.0F, 0.75F, 1.0F);
        	levelIn.addFreshEntity(entityDyanmite);
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.success(itemstack);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level levelIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		tooltip.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".dynamite.desc", (float)Config.INSTANCE.fuseTicksDynamite.get() / 20.0f).withStyle(ChatFormatting.GRAY));
		if(Config.INSTANCE.disableTerrainDamage.get())
			tooltip.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".dynamite.no_terrain_damage").withStyle(ChatFormatting.GRAY));
		else
			tooltip.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".dynamite.terrain_damage").withStyle(ChatFormatting.GRAY));
		super.appendHoverText(stack, levelIn, tooltip, flagIn);
	}
}
