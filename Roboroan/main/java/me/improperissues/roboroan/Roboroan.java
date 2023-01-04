package me.improperissues.roboroan;

import me.improperissues.roboroan.commands.Commands;
import me.improperissues.roboroan.commands.Tabs;
import me.improperissues.roboroan.events.ChatEvent;
import me.improperissues.roboroan.events.CommandEvent;
import me.improperissues.roboroan.events.InteractEvent;
import me.improperissues.roboroan.other.Messages;
import me.improperissues.roboroan.server.OpenInventory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Roboroan extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.broadcastMessage(Messages.STARTER + "cServer pack has enabled!");

        // Events
        getServer().getPluginManager().registerEvents(new ChatEvent(),this);
        getServer().getPluginManager().registerEvents(new InteractEvent(),this);
        getServer().getPluginManager().registerEvents(new CommandEvent(),this);
        getServer().getPluginManager().registerEvents(new OpenInventory(),this);

        // Commands
        getCommand("mute").setExecutor(new Commands());
        getCommand("unmute").setExecutor(new Commands());
        getCommand("freeze").setExecutor(new Commands());
        getCommand("unfreeze").setExecutor(new Commands());
        getCommand("invsee").setExecutor(new Commands());
        getCommand("broadcast").setExecutor(new Commands());
        getCommand("broadcast").setTabCompleter(new Tabs());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.broadcastMessage(Messages.STARTER + "cServer pack has disabled!");
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("Roboroan");
    }
}
