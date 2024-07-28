package ru.imgrui.oneshotsounds;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OneShotSoundsClient implements ClientModInitializer {
    public static final String MOD_ID = "oneshotmod";
    public static final Logger LOGGER = LogManager.getLogger("OneShot Sounds");

	public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    @Override
    public void onInitializeClient() {
		OneShotSoundsSounds.init();
        AutoConfig.register(OneShotSoundsConfig.class, GsonConfigSerializer::new);
        LOGGER.info("Registered config...");
    }
}
