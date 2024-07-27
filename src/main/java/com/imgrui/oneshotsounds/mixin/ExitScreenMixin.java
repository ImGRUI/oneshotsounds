package com.imgrui.oneshotsounds.mixin;

import com.imgrui.oneshotsounds.OneShotSoundsConfig;
import com.imgrui.oneshotsounds.OneShotSoundsSounds;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.SleepingChatScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public class ExitScreenMixin {
    @Inject(at = @At("RETURN"), method = "keyPressed")
    private void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        var screen = (Screen) (Object) this;

        if (keyCode == GLFW.GLFW_KEY_ESCAPE && screen.shouldCloseOnEsc() && !(screen instanceof HandledScreen) && !(screen instanceof SleepingChatScreen)) {
            if (AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().playSoundOnMenuExit) {
                float eventVolume = AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().menuExitVolume;
                float volume = eventVolume / 100.0F;
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(OneShotSoundsSounds.UI_BACK, 1.0F, volume));
            }
        }
    }
}