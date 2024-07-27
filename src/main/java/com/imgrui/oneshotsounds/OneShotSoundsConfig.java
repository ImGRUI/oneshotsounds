package com.imgrui.oneshotsounds;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "oneshotsounds")
public class OneShotSoundsConfig implements ConfigData {
    @Comment("Enables the sound you hear when you hover over buttons.")
    public boolean enableHoverSounds = true;

    @Comment("Plays the open sound whenever you open the pause menu. You might want to disable this if you set the game to auto pause on focus loss.")
    public boolean playSoundOnPauseMenu = true;

    @Comment("Plays the open sound whenever you open an in-game menu, like a crafting table or your inventory.")
    public boolean playSoundOnInGameMenu = true;

    @Comment("Plays the back sound whenever you close a menu.")
    public boolean playSoundOnMenuExit = true;

    @Comment("Plays the back sound whenever you close an in-game menu, like a crafting table or your inventory.")
    public boolean playSoundOnInGameMenuExit = true;

    @Comment("Plays the open sound whenever you open the chat.")
    public boolean playSoundOnChatOpen = true;

    @Comment("Plays the open sound whenever you open the lectern.")
    public boolean playSoundOnLecternOpen = true;



    @Comment("Volume when you hover over buttons.")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int hoverVolume = 45;

    @Comment("Volume when you open the pause menu.")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int onPauseMenuVolume = 25;

    @Comment("Volume when you open an in-game menu.")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int inGameMenuVolume = 25;

    @Comment("Volume when you close a menu.")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int menuExitVolume = 25;

    @Comment("Volume when you close an in-game menu.")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int onInGameMenuExitVolume = 25;

    @Comment("Volume when you open the chat.")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int onChatOpenVolume = 25;

    @Comment("Volume when you open the lectern.")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int onLecternOpenVolume = 25;
}

