package me.improperissues.fireart.other;

import me.improperissues.fireart.FireArt;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private static FileConfiguration CONFIG = FireArt.getInstance().getConfig();


    public static boolean getCanFly() {
        return CONFIG.getBoolean("config.player.nonop_flight");
    }

    public static boolean getAutoToggle() {
        return CONFIG.getBoolean("config.player.nonop_flight_auto");
    }
}
