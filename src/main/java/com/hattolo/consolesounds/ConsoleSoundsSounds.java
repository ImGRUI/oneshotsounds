package com.hattolo.consolesounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;

public final class ConsoleSoundsSounds {

    public static final SoundEvent UI_SELECT = register("ui_select");

	public static final SoundEvent UI_BACK = register("ui_back");

	private static SoundEvent register(String name) {
        Identifier id = ConsoleSoundsClient.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    
    public static void init() {
    }
}
