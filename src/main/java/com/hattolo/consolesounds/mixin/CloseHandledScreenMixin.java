package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsConfig;
import com.hattolo.consolesounds.ConsoleSoundsSounds;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public class CloseHandledScreenMixin {
    @Inject(at = @At("RETURN"), method = "keyPressed")
    private void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        var screen = (Screen) (Object) this;

        if (keyCode == GLFW.GLFW_KEY_E && screen.shouldCloseOnEsc()) {
            if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().playSoundOnInGameMenuExit) {
                float eventVolume = AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().menuExitVolume;
                float volume = eventVolume / 100.0F;
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ConsoleSoundsSounds.UI_BACK, 1.0F, volume));
            }
        }
    }
}