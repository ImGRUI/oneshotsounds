package ru.imgrui.oneshotsounds.mixin;

import ru.imgrui.oneshotsounds.OneShotSoundsConfig;
import ru.imgrui.oneshotsounds.OneShotSoundsSounds;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.SleepingChatScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.LecternScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin (MinecraftClient.class)
public abstract class HandledScreenMixin {

    @Shadow
    @Nullable
    public Screen currentScreen;

    @Inject(at = @At("HEAD"), method = "setScreen")
    private void ScreenChange(@Nullable Screen screen, CallbackInfo ci) {
        if (currentScreen != screen && screen instanceof HandledScreen && !(screen instanceof CreativeInventoryScreen)) {
            if (AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().playSoundOnInGameMenu) {
                float eventVolume = AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().inGameMenuVolume;
                float volume = eventVolume / 100.0F;
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK.value(), 1.0F, volume));
            }
        } else if (screen == null && currentScreen instanceof HandledScreen) {
            if (AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().playSoundOnInGameMenuExit) {
                float eventVolume = AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().onInGameMenuExitVolume;
                float volume = eventVolume / 100.0F;
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(OneShotSoundsSounds.UI_BACK, 1.0F, volume));
            }
        }
        if (currentScreen != screen && screen instanceof LecternScreen) {
            if (AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().playSoundOnLecternOpen) {
                float eventVolume = AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().onLecternOpenVolume;
                float volume = eventVolume / 100.0F;
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK.value(), 1.0F, volume));
            }
        }
        if (currentScreen != screen && screen instanceof ChatScreen && !(screen instanceof SleepingChatScreen)) {
            if (AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().playSoundOnChatOpen) {
                float eventVolume = AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().onChatOpenVolume;
                float volume = eventVolume / 100.0F;
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK.value(), 1.0F, volume));
            }
        }
    }
}