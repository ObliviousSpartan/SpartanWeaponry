package com.oblivioussp.spartanweaponry.init;

import java.util.function.Predicate;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.capability.IOilHandler;
import com.oblivioussp.spartanweaponry.command.OilArgument;
import com.oblivioussp.spartanweaponry.command.OilInput;
import com.oblivioussp.spartanweaponry.command.PotionArgument;
import com.oblivioussp.spartanweaponry.command.PotionInput;
import com.oblivioussp.spartanweaponry.util.Log;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.event.RegisterCommandsEvent;

public class ModCommands
{
	private static final DynamicCommandExceptionType ERROR_NO_ITEM = new DynamicCommandExceptionType((object) -> 
	{
		return Component.translatable("command." + ModSpartanWeaponry.ID + ".apply_oil.error.no_item", object);
	});

	private static final DynamicCommandExceptionType ERROR_INCOMPATIBLE_ITEM = new DynamicCommandExceptionType((object) -> 
	{
		return Component.translatable("command." + ModSpartanWeaponry.ID + ".apply_oil.error.incompatible_item", object);
	});
	
	public static void registerCommands(RegisterCommandsEvent ev)
	{
		// Command "spartanweaponry". Currently only has the "applyOil", "applyPotionOil" and "clearOil" sub-commands
		Predicate<CommandSourceStack> requireOPPermission = (commandSource) -> commandSource.hasPermission(2);
		LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("spartanweaponry").requires(requireOPPermission);
		
    	command.then(Commands.literal("applyOil").			// Apply Oil command
    			then(Commands.argument("player", EntityArgument.player()).
    			then(Commands.argument("oil", OilArgument.oil()).
    			executes((context) -> applyOil(context.getSource(), EntityArgument.getPlayer(context, "player"), OilArgument.getOil(context, "oil"))))));
    	
    	command.then(Commands.literal("applyPotionOil").	// Apply Potion Oil command
    			then(Commands.argument("player", EntityArgument.player()).
    			then(Commands.argument("potion", PotionArgument.potion()).
    			executes((context) ->  applyPotionOil(context.getSource(), EntityArgument.getPlayer(context, "player"), PotionArgument.getPotion(context, "potion"))))));
    	
    	command.then(Commands.literal("clearOil").			// Clear Oil command
    			then(Commands.argument("player", EntityArgument.player()).
    			executes((context) -> clearOil(context.getSource(), EntityArgument.getPlayer(context, "player")))));
    	
    	LiteralCommandNode<CommandSourceStack> mainCommand = ev.getDispatcher().register(command);	// Requires server op permission
    	// Command "sw" is an alias to command "spartanweaponry"
    	ev.getDispatcher().register(Commands.literal("sw").requires(requireOPPermission).redirect(mainCommand));
    	Log.info("Finished registering commands for " + ModSpartanWeaponry.NAME + "!");
	}
	
	private static int applyOil(CommandSourceStack commandSourceIn, ServerPlayer playerIn, OilInput oilIn) throws CommandSyntaxException 
	{
		ItemStack applyStack = playerIn.getMainHandItem();
		if(applyStack.isEmpty())
			throw ERROR_NO_ITEM.create(playerIn.getName().getContents());
		
		IOilHandler oilHandler = applyStack.getCapability(ModCapabilities.OIL_CAPABILITY).
				orElseThrow(() -> ERROR_INCOMPATIBLE_ITEM.create(applyStack.getItem().getName(applyStack).getString()));

		OilEffect oilEffect = oilIn.getEffect();
		ItemStack oilStack = OilHelper.makeOilStack(oilEffect);
		oilHandler.setEffect(oilEffect, ItemStack.EMPTY);
		playerIn.playNotifySound(ModSounds.OIL_APPLIED.get(), playerIn.getSoundSource(), 1.0f, 1.0f);
		commandSourceIn.sendSuccess(Component.translatable("command." + ModSpartanWeaponry.ID + ".apply_oil.success", oilStack.getHoverName(), applyStack.getItem().getName(applyStack).getString(), playerIn.getName().getString()), true);
		return 1;
	}
	
	private static int applyPotionOil(CommandSourceStack commandSourceIn, ServerPlayer playerIn, PotionInput potionIn) throws CommandSyntaxException
	{
		ItemStack applyStack = playerIn.getMainHandItem();
		if(applyStack.isEmpty())
			throw ERROR_NO_ITEM.create(playerIn.getName().getContents());
		
		IOilHandler oilHandler = applyStack.getCapability(ModCapabilities.OIL_CAPABILITY).
				orElseThrow(() -> ERROR_INCOMPATIBLE_ITEM.create(applyStack.getItem().getName(applyStack).getString()));

		Potion potion = potionIn.getEffect();
		ItemStack oilStack = OilHelper.makePotionOilStack(potion);
		oilHandler.setPotion(potion, ItemStack.EMPTY);
		playerIn.playNotifySound(ModSounds.OIL_APPLIED.get(), playerIn.getSoundSource(), 1.0f, 1.0f);
		commandSourceIn.sendSuccess(Component.translatable("command." + ModSpartanWeaponry.ID + ".apply_potion_oil.success", oilStack.getHoverName(), applyStack.getItem().getName(applyStack).getString(), playerIn.getName().getString()), true);
		return 1;
	}
	
	private static int clearOil(CommandSourceStack commandSourceIn, ServerPlayer playerIn) throws CommandSyntaxException 
	{
		ItemStack applyStack = playerIn.getMainHandItem();
		if(applyStack.isEmpty())
			throw ERROR_NO_ITEM.create(playerIn.getName().getContents());
		
		IOilHandler oilHandler = applyStack.getCapability(ModCapabilities.OIL_CAPABILITY).
				orElseThrow(() -> ERROR_INCOMPATIBLE_ITEM.create(applyStack.getItem().getName(applyStack).getString()));

		oilHandler.clearEffect();
		commandSourceIn.sendSuccess(Component.translatable("command." + ModSpartanWeaponry.ID + ".clear_oil.success", applyStack.getItem().getName(applyStack).getString(), playerIn.getName().getString()), true);
		
		return 1;
	}
	
	public static void registerArgumentSerializers()
	{
		ArgumentTypeInfos.registerByClass(OilArgument.class, SingletonArgumentInfo.contextFree(OilArgument::oil));
		ArgumentTypeInfos.registerByClass(PotionArgument.class, SingletonArgumentInfo.contextFree(PotionArgument::potion));
	}
}
