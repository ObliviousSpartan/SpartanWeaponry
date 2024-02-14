package com.oblivioussp.spartanweaponry.data;

// TODO: Find a way to allow custom loot conditions to be added to datagen (as well as getting Codecs right!)
/*import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.loot.ConfigLootCondition;
import com.oblivioussp.spartanweaponry.loot.DecapitateLootModifier;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext.EntityTarget;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
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
		add("player_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.PLAYER)).build()}, Items.PLAYER_HEAD));
		add("skeleton_drop_skull", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.SKELETON)).build()}, Items.SKELETON_SKULL));
		add("wither_skeleton_drop_skull", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.WITHER_SKELETON)).build()}, Items.WITHER_SKELETON_SKULL));
		add("zombie_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.ZOMBIE)).build()}, Items.ZOMBIE_HEAD));
		add("creeper_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.CREEPER)).build()}, Items.CREEPER_HEAD));
		add("ender_dragon_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.ENDER_DRAGON)).build()}, Items.DRAGON_HEAD));
		add("blaze_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.BLAZE)).build()}, ModItems.BLAZE_HEAD.get()));
		add("enderman_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.ENDERMAN)).build()}, ModItems.ENDERMAN_HEAD.get()));
		add("spider_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.SPIDER)).build()}, ModItems.SPIDER_HEAD.get()));
		add("cave_spider_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.CAVE_SPIDER)).build()}, ModItems.CAVE_SPIDER_HEAD.get()));
		add("piglin_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.PIGLIN)).build()}, ModItems.PIGLIN_HEAD.get()));
		add("zombified_piglin_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.ZOMBIFIED_PIGLIN)).build()}, ModItems.ZOMBIFIED_PIGLIN_HEAD.get()));
		add("husk_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.HUSK)).build()}, ModItems.HUSK_HEAD.get()));
		add("stray_drop_skull", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.STRAY)).build()}, ModItems.STRAY_SKULL.get()));
		add("drowned_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.DROWNED)).build()}, ModItems.DROWNED_HEAD.get()));
		add("pillager_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.PILLAGER)).build()}, ModItems.ILLAGER_HEAD.get()));
		add("vindicator_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.VINDICATOR)).build()}, ModItems.ILLAGER_HEAD.get()));
		add("illusioner_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.ILLUSIONER)).build()}, ModItems.ILLAGER_HEAD.get()));
		add("evoker_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.EVOKER)).build()}, ModItems.ILLAGER_HEAD.get()));
		add("witch_drop_head", new DecapitateLootModifier(new LootItemCondition[] { ConfigLootCondition.builder().build(), LootItemKilledByPlayerCondition.killedByPlayer().build(), 
				LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.WITCH)).build()}, ModItems.WITCH_HEAD.get()));
	}
	
	@Override
	public String getName()
	{
		return ModSpartanWeaponry.NAME + " Global Loot Modifiers";
	}

}*/
