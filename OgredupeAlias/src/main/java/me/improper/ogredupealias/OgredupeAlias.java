package me.improper.ogredupealias;

import me.improper.ogredupealias.commands.Commands;
import me.improper.ogredupealias.commands.Tabs;
import me.improper.ogredupealias.commands.tpa.TpCommands;
import me.improper.ogredupealias.data.Config;
import me.improper.ogredupealias.events.*;
import me.improper.ogredupealias.items.Items;
import me.improper.ogredupealias.loottables.LootTables;
import me.improper.ogredupealias.loottables.UncertainLoot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;

public final class OgredupeAlias extends JavaPlugin {

    public static String STARTER = "";
    public static String STARTTIME = "";

    @Override
    public void onEnable() {
        // Plugin startup logic
        LocalDateTime now = LocalDateTime.now();
        Bukkit.getLogger().info("Enabled OgredupeAlias " + getDescription().getVersion());
        STARTER = Config.PLUGIN.getPluginPrefix();
        STARTTIME = now.getMonthValue() + "_"
                + now.getDayOfMonth() + "_"
                + now.getYear() + "_"
                + now.getHour() + "-"
                + now.getMinute() + "-"
                + now.getSecond();
        LootTables.registerTables();
        Items.registerItems();

        // Files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Events
        Bukkit.getPluginManager().registerEvents(new OnChat(),this);
        Bukkit.getPluginManager().registerEvents(new OnCommand(),this);
        Bukkit.getPluginManager().registerEvents(new OnJoin(),this);
        Bukkit.getPluginManager().registerEvents(new OnDamage(),this);
        Bukkit.getPluginManager().registerEvents(new OnBlock(),this);
        Bukkit.getPluginManager().registerEvents(new OnClick(),this);

        // Commands
        getCommand("mutechat").setExecutor(new Commands());
        getCommand("mutechat").setTabCompleter(new Tabs());
        getCommand("commandspy").setExecutor(new Commands());
        getCommand("commandspy").setTabCompleter(new Tabs());
        getCommand("socialspy").setExecutor(new Commands());
        getCommand("socialspy").setTabCompleter(new Tabs());
        getCommand("message").setExecutor(new Commands());
        getCommand("message").setTabCompleter(new Tabs());
        getCommand("staffchat").setExecutor(new Commands());
        getCommand("staffchat").setTabCompleter(new Tabs());
        getCommand("tpa").setExecutor(new TpCommands());
        getCommand("tpa").setTabCompleter(new Tabs());
        getCommand("tpahere").setExecutor(new TpCommands());
        getCommand("tpahere").setTabCompleter(new Tabs());
        getCommand("tpaccept").setExecutor(new TpCommands());
        getCommand("tpaccept").setTabCompleter(new Tabs());
        getCommand("tpcancel").setExecutor(new TpCommands());
        getCommand("tpcancel").setTabCompleter(new Tabs());
        getCommand("tpdeny").setExecutor(new TpCommands());
        getCommand("tpdeny").setTabCompleter(new Tabs());
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
