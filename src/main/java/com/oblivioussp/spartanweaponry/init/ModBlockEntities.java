package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.block.entity.ExtendedSkullBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities 
{
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, ModSpartanWeaponry.ID);
	
	public static final RegistryObject<BlockEntityType<ExtendedSkullBlockEntity>> EXTENDED_SKULL_TYPE = REGISTRY.register("skull_extended", () -> BlockEntityType.Builder.of(ExtendedSkullBlockEntity::new, 
			ModBlocks.BLAZE_HEAD.get(), ModBlocks.BLAZE_WALL_HEAD.get(),
			ModBlocks.ENDERMAN_HEAD.get(), ModBlocks.ENDERMAN_WALL_HEAD.get(),
			ModBlocks.SPIDER_HEAD.get(), ModBlocks.SPIDER_WALL_HEAD.get(),
			ModBlocks.CAVE_SPIDER_HEAD.get(), ModBlocks.CAVE_SPIDER_WALL_HEAD.get(),
			ModBlocks.PIGLIN_HEAD.get(), ModBlocks.PIGLIN_WALL_HEAD.get(),
			ModBlocks.ZOMBIFIED_PIGLIN_HEAD.get(), ModBlocks.ZOMBIFIED_PIGLIN_WALL_HEAD.get(),
			ModBlocks.HUSK_HEAD.get(), ModBlocks.HUSK_WALL_HEAD.get(),
			ModBlocks.STRAY_SKULL.get(), ModBlocks.STRAY_WALL_SKULL.get(),
			ModBlocks.DROWNED_HEAD.get(), ModBlocks.DROWNED_WALL_HEAD.get(),
			ModBlocks.ILLAGER_HEAD.get(), ModBlocks.ILLAGER_WALL_HEAD.get(),
			ModBlocks.WITCH_HEAD.get(), ModBlocks.WITCH_WALL_HEAD.get()).build(null));
}
