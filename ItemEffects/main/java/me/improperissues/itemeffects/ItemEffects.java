package me.improperissues.itemeffects;

import me.improperissues.itemeffects.commands.Commands;
import me.improperissues.itemeffects.commands.Tabs;
import me.improperissues.itemeffects.events.JoinLeave;
import me.improperissues.itemeffects.events.PluginConfig;
import me.improperissues.itemeffects.server.TickEffect;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemEffects extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getLogger().info("ItemEffects enabled!");

        // Events
        getServer().getPluginManager().registerEvents(new PluginConfig(this),this);
        getServer().getPluginManager().registerEvents(new JoinLeave(),this);

        // Files
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Commands
        getCommand("description").setExecutor(new Commands());
        getCommand("description").setTabCompleter(new Tabs());
        getCommand("displayname").setExecutor(new Commands());
        getCommand("displayname").setTabCompleter(new Tabs());
        getCommand("effectlist").setExecutor(new Commands());
        getCommand("colorcodes").setExecutor(new Commands());

        // Loops
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                TickEffect.tickAddEffects();
            }
        },0,10);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getLogger().info("ItemEffects disabled!");
    }
}
