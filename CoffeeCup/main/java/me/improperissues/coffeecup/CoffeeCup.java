package me.improperissues.coffeecup;

import me.improperissues.coffeecup.commands.Commands;
import me.improperissues.coffeecup.commands.Tabs;
import me.improperissues.coffeecup.events.ChatEvents;
import me.improperissues.coffeecup.events.SpawnEvent;
import me.improperissues.coffeecup.files.ClearLag;
import me.improperissues.coffeecup.files.SpawnControl;
import me.improperissues.coffeecup.other.ItemManager;
import me.improperissues.coffeecup.other.Messages;
import me.improperissues.coffeecup.other.PluginMenu;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public final class CoffeeCup extends JavaPlugin {

    public static double timer = 0;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getLogger().warning("CoffeeCup has loaded!");

        // Files
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        SpawnControl.setUp();
        SpawnControl.get().options().copyDefaults(true);
        SpawnControl.save();
        ClearLag.setUp();
        ClearLag.get().options().copyDefaults(true);
        ClearLag.save();

        // Events
        getServer().getPluginManager().registerEvents(new SpawnEvent(),this);
        getServer().getPluginManager().registerEvents(new ChatEvents(),this);
        getServer().getPluginManager().registerEvents(new PluginMenu(),this);

        // Commands
        getCommand("spawncontrol").setExecutor(new Commands());
        getCommand("spawncontrol").setTabCompleter(new Tabs());
        getCommand("clearall").setExecutor(new Commands());
        getCommand("clearall").setTabCompleter(new Tabs());
        getCommand("clearchat").setExecutor(new Commands());
        getCommand("clearlag").setExecutor(new Commands());
        getCommand("clearlag").setTabCompleter(new Tabs());
        getCommand("pl").setExecutor(new Commands());
        getCommand("pl").setTabCompleter(new Tabs());

        // Items
        ItemManager.registerItems();

        // Loops
        BukkitTask clearLag = new BukkitRunnable() {
            @Override
            public void run() {
                if (ClearLag.isResumed()) {
                    if (timer < ClearLag.getInterval()) {
                        int timeLeft = (int) (ClearLag.getInterval() - timer);
                        switch (timeLeft) {
                            case 60:
                            case 30:
                            case 10:
                                getServer().broadcastMessage(Messages.starter + "cClearing all §7\"" + ClearLag.getClearType() + "\" §cin §7" + timeLeft + " §cseconds!");
                                break;
                        }
                        timer ++;
                    } else {
                        getServer().broadcastMessage(Messages.starter + "cCleared all §7\"" + ClearLag.getClearType() + "\" §!");
                        getServer().dispatchCommand(getServer().getConsoleSender(),"clearall " + ClearLag.getClearType());
                        timer = 0;
                    }
                }
            }
        }.runTaskTimer(this,0,20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getLogger().warning("CoffeeCup has disabled!");

    }


    public static Plugin getPlugin(String name) {
        if (getPluginList().contains(name)) {
            return Bukkit.getServer().getPluginManager().getPlugin(name);
        }
        return null;
    }
    public static List<String> getPluginList() {
        List<String> list = new ArrayList<>();
        for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
            list.add(plugin.getName());
        }
        return list;
    }

    public static List<String> getEnabledPlugins() {
        List<String> list = new ArrayList<>();
        for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
            if (plugin.isEnabled()) {
                list.add(plugin.getName());
            }
        }
        return list;
    }
}
