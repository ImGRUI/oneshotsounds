package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsConfig;
import com.hattolo.consolesounds.ConsoleSoundsSounds;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClickableWidget.class)
public class SelectClickableWidgetMixin {

	@Inject(at = @At("RETURN"), method = "isHovered")
	private void isHovered(CallbackInfoReturnable<Boolean> cir) {
		var widget = (ClickableWidget) (Object) this;

		if (cir.getReturnValue()) {
			if (!widget.active) return;
			if (!selected) {
				selected = true;
				if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().enableHoverSounds) {
        			float eventVolume = AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().hoverVolume;
        			float volume = eventVolume / 100.0F;
					MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ConsoleSoundsSounds.UI_SELECT, 1.0F, volume));
				}
			}
		}
		else {
			selected = false;
		}
	}

	Boolean selected = false;
}