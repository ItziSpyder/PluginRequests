package me.improperissues.playerblocks;

import me.improperissues.playerblocks.commands.Commands;
import me.improperissues.playerblocks.commands.Tabs;
import me.improperissues.playerblocks.events.RegionInteraction;
import me.improperissues.playerblocks.events.SelectionEvent;
import me.improperissues.playerblocks.other.Items;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerBlocks extends JavaPlugin {

    public static String STARTER = "§fPlayerBlocks: §";

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Events
        getServer().getPluginManager().registerEvents(new SelectionEvent(),this);
        getServer().getPluginManager().registerEvents(new RegionInteraction(),this);

        // Commands
        getCommand("playerblocks").setExecutor(new Commands());
        getCommand("playerblocks").setTabCompleter(new Tabs());

        // Methods
        Items.registerAll();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("PlayerBlocks");
    }
}
