package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.block.ExtendedSkullBlock;
import com.oblivioussp.spartanweaponry.block.ExtendedWallSkullBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
	public static Block blazeHead, blazeWallHead;
	public static Block endermanHead, endermanWallHead;
	public static Block spiderHead, spiderWallHead;
	public static Block caveSpiderHead, caveSpiderWallHead;
	public static Block piglinHead, piglinWallHead;
	public static Block zombifiedPiglinHead, zombifiedPiglinWallHead;
	public static Block huskHead, huskWallHead;
	public static Block straySkull, strayWallSkull;
	public static Block drownedHead, drownedWallHead;
	public static Block illagerHead, illagerWallHead;
	public static Block witchHead, witchWallHead;
	
	static
	{
		AbstractBlock.Properties prop = AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f);
		blazeHead = new ExtendedSkullBlock("blaze_head", ExtendedSkullBlock.Types.BLAZE, prop);
		blazeWallHead = new ExtendedWallSkullBlock("blaze_wall_head", ExtendedSkullBlock.Types.BLAZE, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f).lootFrom(blazeHead));
		endermanHead = new ExtendedSkullBlock("enderman_head", ExtendedSkullBlock.Types.ENDERMAN, prop);
		endermanWallHead = new ExtendedWallSkullBlock("enderman_wall_head", ExtendedSkullBlock.Types.ENDERMAN, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f).lootFrom(endermanHead));
		spiderHead = new ExtendedSkullBlock("spider_head", ExtendedSkullBlock.Types.SPIDER, prop);
		spiderWallHead = new ExtendedWallSkullBlock("spider_wall_head", ExtendedSkullBlock.Types.SPIDER, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f).lootFrom(spiderHead));
		caveSpiderHead = new ExtendedSkullBlock("cave_spider_head", ExtendedSkullBlock.Types.CAVE_SPIDER, prop);
		caveSpiderWallHead = new ExtendedWallSkullBlock("cave_spider_wall_head", ExtendedSkullBlock.Types.CAVE_SPIDER, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f).lootFrom(caveSpiderHead));
		piglinHead = new ExtendedSkullBlock("piglin_head", ExtendedSkullBlock.Types.PIGLIN, prop);
		piglinWallHead = new ExtendedWallSkullBlock("piglin_wall_head", ExtendedSkullBlock.Types.PIGLIN, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f).lootFrom(piglinHead));
		zombifiedPiglinHead = new ExtendedSkullBlock("zombified_piglin_head", ExtendedSkullBlock.Types.ZOMBIE_PIGLIN, prop);
		zombifiedPiglinWallHead = new ExtendedWallSkullBlock("zombified_piglin_wall_head", ExtendedSkullBlock.Types.ZOMBIE_PIGLIN, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f).lootFrom(zombifiedPiglinHead));
		huskHead = new ExtendedSkullBlock("husk_head", ExtendedSkullBlock.Types.HUSK, prop);
		huskWallHead = new ExtendedWallSkullBlock("husk_wall_head", ExtendedSkullBlock.Types.HUSK, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f).lootFrom(huskHead));
		straySkull = new ExtendedSkullBlock("stray_skull", ExtendedSkullBlock.Types.STRAY, prop);
		strayWallSkull = new ExtendedWallSkullBlock("stray_wall_skull", ExtendedSkullBlock.Types.STRAY, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f).lootFrom(straySkull));
		drownedHead = new ExtendedSkullBlock("drowned_head", ExtendedSkullBlock.Types.DROWNED, prop);
		drownedWallHead = new ExtendedWallSkullBlock("drowned_wall_head", ExtendedSkullBlock.Types.DROWNED, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f).lootFrom(drownedHead));
		illagerHead = new ExtendedSkullBlock("illager_head", ExtendedSkullBlock.Types.ILLAGER, prop);
		illagerWallHead = new ExtendedWallSkullBlock("illager_wall_head", ExtendedSkullBlock.Types.ILLAGER, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f).lootFrom(illagerHead));
		witchHead = new ExtendedSkullBlock("witch_head", ExtendedSkullBlock.Types.WITCH, prop);
		witchWallHead = new ExtendedWallSkullBlock("witch_wall_head", ExtendedSkullBlock.Types.WITCH, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0f).lootFrom(witchHead));
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> ev)
	{
		IForgeRegistry<Block> reg = ev.getRegistry();
		
		reg.registerAll(blazeHead, blazeWallHead, endermanHead, endermanWallHead,
				spiderHead, spiderWallHead, caveSpiderHead, caveSpiderWallHead,
				piglinHead, piglinWallHead, zombifiedPiglinHead, zombifiedPiglinWallHead,
				huskHead, huskWallHead, straySkull, strayWallSkull,
				drownedHead, drownedWallHead, illagerHead, illagerWallHead,
				witchHead, witchWallHead);
	}
}
