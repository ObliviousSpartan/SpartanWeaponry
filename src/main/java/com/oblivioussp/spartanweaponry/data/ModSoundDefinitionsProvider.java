package com.oblivioussp.spartanweaponry.data;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModSounds;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinition.Sound;
import net.minecraftforge.common.data.SoundDefinition.SoundType;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class ModSoundDefinitionsProvider extends SoundDefinitionsProvider 
{

	public ModSoundDefinitionsProvider(DataGenerator generator, ExistingFileHelper helper)
	{
		super(generator, ModSpartanWeaponry.ID, helper);
	}

	@Override
	public void registerSounds() 
	{
		add(ModSounds.DAGGER_THROW, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.dagger_throw").
				with(Sound.sound(new ResourceLocation("item/trident/throw1"), SoundType.SOUND), 
					Sound.sound(new ResourceLocation("item/trident/throw2"), SoundType.SOUND)));
		add(ModSounds.DAGGER_HIT_MOB, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.dagger_hit_mob").
				with(Sound.sound(new ResourceLocation("item/trident/pierce1"), SoundType.SOUND),
					Sound.sound(new ResourceLocation("item/trident/pierce2"), SoundType.SOUND),
					Sound.sound(new ResourceLocation("item/trident/pierce3"), SoundType.SOUND)));
		add(ModSounds.DAGGER_HIT_GROUND, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.dagger_hit_ground").
				with(Sound.sound(new ResourceLocation("item/trident/ground_impact1"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact2"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact3"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact4"), SoundType.SOUND).volume(0.9)));

		add(ModSounds.THROWING_KNIFE_THROW, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.throwing_knife_throw").
				with(Sound.sound(new ResourceLocation("item/trident/throw1"), SoundType.SOUND), 
					Sound.sound(new ResourceLocation("item/trident/throw2"), SoundType.SOUND)));
		add(ModSounds.THROWING_KNIFE_HIT_MOB, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.throwing_knife_hit_mob").
				with(Sound.sound(new ResourceLocation("item/trident/pierce1"), SoundType.SOUND),
					Sound.sound(new ResourceLocation("item/trident/pierce2"), SoundType.SOUND),
					Sound.sound(new ResourceLocation("item/trident/pierce3"), SoundType.SOUND)));
		add(ModSounds.THROWING_KNIFE_HIT_GROUND, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.throwing_knife_hit_ground").
				with(Sound.sound(new ResourceLocation("item/trident/ground_impact1"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact2"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact3"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact4"), SoundType.SOUND).volume(0.9)));

		add(ModSounds.TOMAHAWK_THROW, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.tomahawk_throw").
				with(Sound.sound(new ResourceLocation("item/trident/throw1"), SoundType.SOUND), 
					Sound.sound(new ResourceLocation("item/trident/throw2"), SoundType.SOUND)));
		add(ModSounds.TOMAHAWK_HIT_MOB, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.tomahawk_hit_mob").
				with(Sound.sound(new ResourceLocation("item/trident/pierce1"), SoundType.SOUND),
					Sound.sound(new ResourceLocation("item/trident/pierce2"), SoundType.SOUND),
					Sound.sound(new ResourceLocation("item/trident/pierce3"), SoundType.SOUND)));
		add(ModSounds.TOMAHAWK_HIT_GROUND, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.tomahawk_hit_ground").
				with(Sound.sound(new ResourceLocation("item/trident/ground_impact1"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact2"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact3"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact4"), SoundType.SOUND).volume(0.9)));

		add(ModSounds.JAVELIN_THROW, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.javelin_throw").
				with(Sound.sound(new ResourceLocation("item/trident/throw1"), SoundType.SOUND), 
					Sound.sound(new ResourceLocation("item/trident/throw2"), SoundType.SOUND)));
		add(ModSounds.JAVELIN_HIT_MOB, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.javelin_hit_mob").
				with(Sound.sound(new ResourceLocation("item/trident/pierce1"), SoundType.SOUND),
					Sound.sound(new ResourceLocation("item/trident/pierce2"), SoundType.SOUND),
					Sound.sound(new ResourceLocation("item/trident/pierce3"), SoundType.SOUND)));
		add(ModSounds.JAVELIN_HIT_GROUND, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.javelin_hit_ground").
				with(Sound.sound(new ResourceLocation("item/trident/ground_impact1"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact2"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact3"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact4"), SoundType.SOUND).volume(0.9)));

		add(ModSounds.BOOMERANG_THROW, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.boomerang_throw").
				with(Sound.sound(new ResourceLocation("item/trident/throw1"), SoundType.SOUND), 
					Sound.sound(new ResourceLocation("item/trident/throw2"), SoundType.SOUND)));
		add(ModSounds.BOOMERANG_FLY, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.boomerang_fly").
				with(Sound.sound(new ResourceLocation("random/bow"), SoundType.SOUND)));
		add(ModSounds.BOOMERANG_HIT_MOB, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.boomerang_hit_mob").
				with(Sound.sound(new ResourceLocation("item/trident/pierce1"), SoundType.SOUND),
					Sound.sound(new ResourceLocation("item/trident/pierce2"), SoundType.SOUND),
					Sound.sound(new ResourceLocation("item/trident/pierce3"), SoundType.SOUND)));
		add(ModSounds.BOOMERANG_BOUNCE, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.boomerang_bounce").
				with(Sound.sound(new ResourceLocation("entity/player/attack/weak1"), SoundType.SOUND).volume(0.7),
					Sound.sound(new ResourceLocation("entity/player/attack/weak2"), SoundType.SOUND).volume(0.7),
					Sound.sound(new ResourceLocation("entity/player/attack/weak3"), SoundType.SOUND).volume(0.7),
					Sound.sound(new ResourceLocation("entity/player/attack/weak4"), SoundType.SOUND).volume(0.7)));
		add(ModSounds.BOOMERANG_HIT_GROUND, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.boomerang_hit_ground").
				with(Sound.sound(new ResourceLocation("item/trident/ground_impact1"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact2"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact3"), SoundType.SOUND).volume(0.9), 
					Sound.sound(new ResourceLocation("item/trident/ground_impact4"), SoundType.SOUND).volume(0.9)));
		
		add(ModSounds.THROWING_WEAPON_LOYALTY_RETURN, SoundDefinition.definition().subtitle("subtitle.spartanweaponry.throwing_weapon_loyalty_return").
				with(Sound.sound(new ResourceLocation("item/trident/return1"), SoundType.SOUND).volume(0.8),
					Sound.sound(new ResourceLocation("item/trident/return2"), SoundType.SOUND).pitch(1.2).volume(0.8),
					Sound.sound(new ResourceLocation("item/trident/return3"), SoundType.SOUND).pitch(0.8).volume(0.8),
					Sound.sound(new ResourceLocation("item/trident/return2"), SoundType.SOUND).volume(0.8),
					Sound.sound(new ResourceLocation("item/trident/return2"), SoundType.SOUND).pitch(1.2).volume(0.8),
					Sound.sound(new ResourceLocation("item/trident/return2"), SoundType.SOUND).pitch(0.8).volume(0.8),
					Sound.sound(new ResourceLocation("item/trident/return3"), SoundType.SOUND).volume(0.8),
					Sound.sound(new ResourceLocation("item/trident/return3"), SoundType.SOUND).pitch(1.2).volume(0.8),
					Sound.sound(new ResourceLocation("item/trident/return3"), SoundType.SOUND).pitch(0.8).volume(0.8)));
	}

	@Override
	public String getName() 
	{
		return ModSpartanWeaponry.NAME + " Sound Definitions";
	}
}
