package me.improper.quicklifesteal.data;

import me.improper.quicklifesteal.QuickLifesteal;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {

    private static final FileConfiguration config = QuickLifesteal.getInstance().getConfig();

    /**
     * The all configurations sections category Plugin
     */
    public class Plugin {
        public static final String prefix = config.getString("config.plugin.prefix");
    }

    /**
     * The all configurations sections category Gameplay
     */
    public class Gameplay {
        public static final int max_hearts = config.getInt("config.gameplay.max-hearts");
        public static final List<String> on_eliminate = config.getStringList("config.gameplay.on-eliminate");
    }

    /**
     * The all configurations sections category Messages
     */
    public class Messages {
        public static final String kill_title_message = config.getString("config.messages.kill-title-message");
        public static final String death_title_message = config.getString("config.messages.death-title-message");
    }
}
