package com.oblivioussp.spartanweaponry.data;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.init.ModLootModifiers;
import com.oblivioussp.spartanweaponry.loot.ConfigLootCondition;
import com.oblivioussp.spartanweaponry.loot.DecapitateLootModifier;

import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext.EntityTarget;
import net.minecraft.loot.conditions.EntityHasProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.KilledByPlayer;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider
{
	public ModGlobalLootModifierProvider(DataGenerator gen)
	{
		super(gen, ModSpartanWeaponry.ID);
	}

	@Override
	protected void start() 
	{
		add("player_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.PLAYER)).build()}, Items.PLAYER_HEAD));
		add("skeleton_drop_skull", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.SKELETON)).build()}, Items.SKELETON_SKULL));
		add("wither_skeleton_drop_skull", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.WITHER_SKELETON)).build()}, Items.WITHER_SKELETON_SKULL));
		add("zombie_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.ZOMBIE)).build()}, Items.ZOMBIE_HEAD));
		add("creeper_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.CREEPER)).build()}, Items.CREEPER_HEAD));
		add("blaze_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.BLAZE)).build()}, ModItems.blazeHead));
		add("enderman_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.ENDERMAN)).build()}, ModItems.endermanHead));
		add("spider_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.SPIDER)).build()}, ModItems.spiderHead));
		add("cave_spider_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.CAVE_SPIDER)).build()}, ModItems.caveSpiderHead));
		add("piglin_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.PIGLIN)).build()}, ModItems.piglinHead));
		add("zombified_piglin_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.ZOMBIFIED_PIGLIN)).build()}, ModItems.zombifiedPiglinHead));
		add("husk_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.HUSK)).build()}, ModItems.huskHead));
		add("stray_drop_skull", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.STRAY)).build()}, ModItems.straySkull));
		add("drowned_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.DROWNED)).build()}, ModItems.drownedHead));
		add("pillager_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.PILLAGER)).build()}, ModItems.illagerHead));
		add("vindicator_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.VINDICATOR)).build()}, ModItems.illagerHead));
		add("illusioner_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.ILLUSIONER)).build()}, ModItems.illagerHead));
		add("evoker_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.EVOKER)).build()}, ModItems.illagerHead));
		add("witch_drop_head", ModLootModifiers.DECAPITATE, new DecapitateLootModifier(new ILootCondition[] { ConfigLootCondition.builder().build(), KilledByPlayer.builder().build(), 
				EntityHasProperty.builder(EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityType.WITCH)).build()}, ModItems.witchHead));
	}
	
	@Override
	public String getName()
	{
		return ModSpartanWeaponry.NAME + " Global Loot Modifiers";
	}

}
