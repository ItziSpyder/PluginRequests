package me.improperissues.servertips.data;

import me.improperissues.servertips.ServerTips;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private static FileConfiguration CONFIG = ServerTips.getInstance().getConfig();

    public static String getPluginPrefix() {
        String prefix = CONFIG.getString("config.plugin.prefix");
        if (prefix == null || prefix.equals("")) prefix = "§7[§aTip§7] §";
        return prefix;
    }
    public static int getGeneralMinDelay() {
        return CONFIG.getInt("config.general.min_delay");
    }
    public static int getGeneralMaxDelay() {
        return CONFIG.getInt("config.general.max_delay");
    }
    public static boolean getGeneralPaused() {
        return CONFIG.getBoolean("config.general.paused");
    }
    public static boolean getGeneralRandomize() {
        return CONFIG.getBoolean("config.general.randomize");
    }

    public static void setGeneralPaused(boolean paused) {
        CONFIG.set("config.general.paused",paused);
        ServerTips.getInstance().saveConfig();
    }
}
