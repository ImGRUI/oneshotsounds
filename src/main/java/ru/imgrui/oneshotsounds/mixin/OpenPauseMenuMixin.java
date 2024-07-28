package ru.imgrui.oneshotsounds.mixin;

import ru.imgrui.oneshotsounds.OneShotSoundsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class OpenPauseMenuMixin {
    @Inject(at = @At("TAIL"), method = "openGameMenu")
    private void init(boolean pause, CallbackInfo ci) {
        if (MinecraftClient.getInstance().currentScreen instanceof GameMenuScreen) {
            if (AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().playSoundOnPauseMenu) {
                float eventVolume = AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().onPauseMenuVolume;
                float volume = eventVolume / 100.0F;
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK.value(), 1.0F, volume));
            }
        }
    }
}