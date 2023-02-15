package me.improper.quicklifesteal;

import me.improper.quicklifesteal.commands.CommandLsHeal;
import me.improper.quicklifesteal.commands.CommandLsReset;
import me.improper.quicklifesteal.commands.CommandLsWithdraw;
import me.improper.quicklifesteal.data.Config;
import me.improper.quicklifesteal.events.OnClick;
import me.improper.quicklifesteal.events.OnDeath;
import me.improper.quicklifesteal.server.Items;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class QuickLifesteal extends JavaPlugin {

    public static String starter = "";

    @Override
    public void onEnable() {
        // Plugin startup logic
        starter = Config.Plugin.prefix + " ";

        // Events
        Bukkit.getPluginManager().registerEvents(new OnClick(),this);
        Bukkit.getPluginManager().registerEvents(new OnDeath(),this);

        // Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Commands
        getCommand("lsreset").setExecutor(new CommandLsReset());
        getCommand("lsreset").setTabCompleter(new CommandLsReset.Tabs());
        getCommand("lsheal").setExecutor(new CommandLsHeal());
        getCommand("lsheal").setTabCompleter(new CommandLsHeal.Tabs());
        getCommand("lswithdraw").setExecutor(new CommandLsWithdraw());
        getCommand("lswithdraw").setTabCompleter(new CommandLsWithdraw.Tabs());

        // Server
        Items.registerAll();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Returns an instance of this Plugin, can be used to perform
     * Bukkit tasks or call any methods that may come with it.
     * @return instance of this plugin
     */
    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("QuickLifesteal");
    }
}
