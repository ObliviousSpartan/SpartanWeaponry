package com.oblivioussp.spartanweaponry.command;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class PotionParser 
{
	private static final ImmutableList<Potion> invalidPotions = ImmutableList.of(Potions.EMPTY, Potions.WATER, Potions.MUNDANE, Potions.THICK, Potions.AWKWARD);
	private static final List<ResourceLocation> invalidPotionNames = invalidPotions.stream().map((potion) -> ForgeRegistries.POTIONS.getKey(potion)).collect(Collectors.toUnmodifiableList());
	
	public static final DynamicCommandExceptionType ERROR_UNKNOWN_POTION = new DynamicCommandExceptionType((object) -> 
	{
		return new TranslatableComponent("command." + ModSpartanWeaponry.ID + ".apply_oil.error.unknown_potion", object);
	});
	
	public static final DynamicCommandExceptionType ERROR_INVALID_POTION = new DynamicCommandExceptionType((object) -> 
	{
		return new TranslatableComponent("command." + ModSpartanWeaponry.ID + ".apply_oil.error.invalid_potion", object);
	});
	
	private static final BiFunction<SuggestionsBuilder, IForgeRegistry<Potion>, CompletableFuture<Suggestions>> SUGGEST_NOTHING = (builder, registry) -> builder.buildFuture();
	
	private final StringReader reader;
	@Nullable
	private Potion potion;
	
	private BiFunction<SuggestionsBuilder, IForgeRegistry<Potion>, CompletableFuture<Suggestions>> suggestionFunc;
	
	public PotionParser(StringReader readerIn)
	{
		reader = readerIn;
	}

	@Nullable
	public Potion getEffect()
	{
		return potion;
	}
	
	public void read() throws CommandSyntaxException
	{
		int idx = reader.getCursor();
		ResourceLocation loc = ResourceLocation.read(reader);
		potion = ForgeRegistries.POTIONS.getValue(loc);
		
		if(potion == null)
		{
			reader.setCursor(idx);
			throw ERROR_UNKNOWN_POTION.createWithContext(reader, loc.toString());
		}
		else if(invalidPotions.contains(potion))
		{
			reader.setCursor(idx);
			throw ERROR_INVALID_POTION.createWithContext(reader, loc.toString());
		}
	}
	
	public PotionParser parse() throws CommandSyntaxException
	{
		suggestionFunc = this::suggestPotionEffect;
		read();
		suggestionFunc = SUGGEST_NOTHING;
		return this;
	}
	
	private CompletableFuture<Suggestions> suggestPotionEffect(SuggestionsBuilder builderIn, IForgeRegistry<Potion> potionRegistryIn)
	{
		Set<ResourceLocation> suggestions = potionRegistryIn.getKeys().stream().filter((potion) -> !invalidPotionNames.contains(potion)).collect(Collectors.toSet());
		return SharedSuggestionProvider.suggestResource(suggestions, builderIn);
	}
	
	public CompletableFuture<Suggestions> fillSuggestions(SuggestionsBuilder builderIn, IForgeRegistry<Potion> oilRegistryIn)
	{
		return suggestionFunc.apply(builderIn.createOffset(reader.getCursor()), oilRegistryIn);
	}
}
