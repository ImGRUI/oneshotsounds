package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class OpenChatScreenMixin {
    @Inject(at = @At("TAIL"), method = "openChatScreen")
    private void init(String text, CallbackInfo ci) {
        if (MinecraftClient.getInstance().currentScreen instanceof ChatScreen) {
            if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().playSoundOnInGameMenu) {
                float eventVolume = AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().inGameMenuVolume;
                float volume = eventVolume / 100.0F;
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK.value(), 1.0F, volume));
            }
        }
    }
}