package com.oblivioussp.spartanweaponry.api.trait;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

public class HarvesterWeaponTrait extends WeaponTrait implements IActionTraitCallback
{

	public HarvesterWeaponTrait(String typeIn, String modIdIn) 
	{
		super(typeIn, modIdIn, TraitQuality.POSITIVE);
	}
	
	@Override
	public Optional<IActionTraitCallback> getActionCallback() 
	{
		return Optional.of(this);
	}
	
	@Override
	public InteractionResult useOn(UseOnContext contextIn) 
	{
		Level level = contextIn.getLevel();
		BlockPos clickedPos = contextIn.getClickedPos();
		Player player = contextIn.getPlayer();
		
		if(!level.isClientSide)
		{
			AtomicBoolean applyCooldown = new AtomicBoolean(false);
			BlockPos.betweenClosed(clickedPos.getX() - 1, clickedPos.getY(), clickedPos.getZ() - 1, clickedPos.getX() + 1, clickedPos.getY(), clickedPos.getZ() + 1).
				forEach((pos) -> {
					if(harvestCrops(level, player, contextIn.getItemInHand(), pos))
						applyCooldown.set(true);
				});
			if(applyCooldown.get())
			{
				player.swing(contextIn.getHand(), true);
				player.getCooldowns().addCooldown(contextIn.getItemInHand().getItem(), 10);
				return InteractionResult.CONSUME;
			}
			
		}
		return InteractionResult.PASS;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(ItemStack usingStackIn, Level levelIn, Player playerIn,
			InteractionHand handIn) 
	{
		return InteractionResultHolder.pass(usingStackIn);
	}

	@SuppressWarnings("deprecation")
	public boolean harvestCrops(Level levelIn, Player playerIn, ItemStack toolIn, BlockPos posIn)
	{
		BlockState state = levelIn.getBlockState(posIn);
		Block block = state.getBlock();
		if((block instanceof CropBlock || state.is(Blocks.NETHER_WART)))
		{
			// Now reset the crop age to the first age
			IntegerProperty ageProp = (IntegerProperty)state.getProperties().stream().filter((prop) -> prop.getName() == "age").findFirst().orElseThrow();
			int maxAge = Collections.max(ageProp.getPossibleValues());
			
			if(state.getValue(ageProp) >= maxAge)
			{
				List<ItemStack> drops = state.getDrops(new LootContext.Builder((ServerLevel)levelIn).
						withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(posIn)).
						withParameter(LootContextParams.BLOCK_STATE, state).
						withParameter(LootContextParams.THIS_ENTITY, playerIn).
						withParameter(LootContextParams.TOOL, toolIn));
				
				// Remove 1 seed from this drop list
				ItemStack targetSeed = block.getCloneItemStack(levelIn, posIn, state);
				for(ItemStack dropStack : drops)
				{
					if(dropStack.is(targetSeed.getItem()))
					{
						dropStack.shrink(1);
						break;
					}
				}
				
				// Drop all the things
				drops.forEach((stack) -> Block.popResource(levelIn, posIn, stack));
				
				levelIn.setBlockAndUpdate(posIn, state.setValue(ageProp, 0));
				SoundType blockSound = block.getSoundType(state, levelIn, posIn, null);
				levelIn.playSound(null, posIn, blockSound.getBreakSound(), SoundSource.BLOCKS, blockSound.getVolume(), blockSound.getPitch());
				return true;
			}
		}
		return false;
	}

}
