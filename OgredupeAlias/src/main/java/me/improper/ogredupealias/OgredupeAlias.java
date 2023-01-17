package me.improper.ogredupealias;

import me.improper.ogredupealias.commands.Commands;
import me.improper.ogredupealias.commands.Tabs;
import me.improper.ogredupealias.data.Config;
import me.improper.ogredupealias.events.OnChat;
import me.improper.ogredupealias.events.OnCommand;
import me.improper.ogredupealias.events.OnDamage;
import me.improper.ogredupealias.events.OnJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class OgredupeAlias extends JavaPlugin {

    public static String STARTER = "";

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Enabled OgredupeAlias " + getDescription().getVersion());
        STARTER = Config.PLUGIN.getPluginPrefix();

        // Files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Events
        Bukkit.getPluginManager().registerEvents(new OnChat(),this);
        Bukkit.getPluginManager().registerEvents(new OnCommand(),this);
        Bukkit.getPluginManager().registerEvents(new OnJoin(),this);
        Bukkit.getPluginManager().registerEvents(new OnDamage(),this);

        // Commands
        getCommand("mutechat").setExecutor(new Commands());
        getCommand("mutechat").setTabCompleter(new Tabs());
        getCommand("commandspy").setExecutor(new Commands());
        getCommand("commandspy").setTabCompleter(new Tabs());
        getCommand("socialspy").setExecutor(new Commands());
        getCommand("socialspy").setTabCompleter(new Tabs());
        getCommand("message").setExecutor(new Commands());
        getCommand("message").setTabCompleter(new Tabs());
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
