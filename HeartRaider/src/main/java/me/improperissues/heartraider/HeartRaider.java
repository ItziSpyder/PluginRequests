package me.improperissues.heartraider;

import me.improperissues.heartraider.commands.Commands;
import me.improperissues.heartraider.commands.Tabs;
import me.improperissues.heartraider.events.OnClick;
import me.improperissues.heartraider.server.Items;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeartRaider extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Events
        getServer().getPluginManager().registerEvents(new OnClick(),this);

        // Commands
        getCommand("giveitem").setExecutor(new Commands());
        getCommand("giveitem").setTabCompleter(new Tabs());

        // Items
        Items.registerAllItems();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("HeartRaider");
    }
}
