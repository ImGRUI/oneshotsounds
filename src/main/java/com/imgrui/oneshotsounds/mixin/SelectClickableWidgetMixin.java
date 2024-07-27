package com.imgrui.oneshotsounds.mixin;

import com.imgrui.oneshotsounds.OneShotSoundsConfig;
import com.imgrui.oneshotsounds.OneShotSoundsSounds;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.LocalDate;

@Mixin(ClickableWidget.class)
public class SelectClickableWidgetMixin {

	@Inject(at = @At("RETURN"), method = "isHovered")
	private void isHovered(CallbackInfoReturnable<Boolean> cir) {
		var widget = (ClickableWidget) (Object) this;

		if (cir.getReturnValue()) {
			if (!widget.active) return;
			if (!selected) {
				selected = true;
				if (AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().enableHoverSounds) {
					float eventVolume = AutoConfig.getConfigHolder(OneShotSoundsConfig.class).getConfig().hoverVolume;
					float volume = eventVolume / 100.0F;
					MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master((LocalDate.now().getMonthValue() == 4 && LocalDate.now().getDayOfMonth() == 1) ? OneShotSoundsSounds.NIKOMEOW : OneShotSoundsSounds.UI_SELECT, 1.0F, volume));
				}
			}
		}
		else {
			selected = false;
		}
	}

	@Unique
	Boolean selected = false;
}