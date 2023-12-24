package com.oblivioussp.spartanweaponry.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraftforge.registries.ForgeRegistries;

public class PotionArgument implements ArgumentType<PotionInput> 
{
	private static final Collection<String> EXAMPLES = Arrays.asList("spartanweaponry:undead");
	
	public static PotionArgument potion()
	{
		return new PotionArgument();
	}

	@Override
	public PotionInput parse(StringReader reader) throws CommandSyntaxException 
	{
		PotionParser parser = new PotionParser(reader).parse();
		return new PotionInput(parser.getEffect());
	}
	
	public static <S> PotionInput getPotion(CommandContext<S> context, String string)
	{
		return context.getArgument(string, PotionInput.class);
	}
	
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) 
	{
		StringReader reader = new StringReader(builder.getInput());
		reader.setCursor(builder.getStart());
		PotionParser parser = new PotionParser(reader);
		
		try
		{
			parser.parse();
		}
		catch(CommandSyntaxException e) {}
		
		return parser.fillSuggestions(builder, ForgeRegistries.POTIONS);
	}

	@Override
	public Collection<String> getExamples()
	{
		return EXAMPLES;
	}
}
