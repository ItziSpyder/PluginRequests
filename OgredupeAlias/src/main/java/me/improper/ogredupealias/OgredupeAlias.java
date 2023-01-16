package me.improper.ogredupealias;

import me.improper.ogredupealias.data.Config;
import me.improper.ogredupealias.events.OnChat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class OgredupeAlias extends JavaPlugin {

    public static String STARTER;

    @Override
    public void onEnable() {
        // Plugin startup logic
        STARTER = Config.getPluginPrefix();
        Bukkit.getLogger().info("Enabled OgredupeAlias " + getDescription().getVersion());

        // Files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Events
        Bukkit.getPluginManager().registerEvents(new OnChat(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Disabled OgredupeAlias " + getDescription().getVersion());
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("OgredupeAlias");
    }
}
