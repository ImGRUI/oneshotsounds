package ru.imgrui.oneshotsounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;

public final class OneShotSoundsSounds {

    public static final SoundEvent UI_SELECT = register("ui_select");

	public static final SoundEvent UI_BACK = register("ui_back");

    public static final SoundEvent NIKOMEOW = register("nikomeow");

	private static SoundEvent register(String name) {
        Identifier id = OneShotSoundsClient.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    
    public static void init() {
    }
}
