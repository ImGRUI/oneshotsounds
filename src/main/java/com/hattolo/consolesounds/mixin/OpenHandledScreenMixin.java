package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public class OpenHandledScreenMixin {
    @Inject(at = @At("HEAD"), method = "init")
    private void init(CallbackInfo ci) {
        if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().playSoundOnInGameMenu) {
            SoundEvent eventSound = SoundEvents.UI_BUTTON_CLICK.get();
            float eventVolume = AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().inGameMenuVolume;
            float volume = eventVolume / 100.0F;
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(eventSound, 1.0F, volume));
        }
    }
}