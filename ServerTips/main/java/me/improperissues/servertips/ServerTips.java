package me.improperissues.servertips;

import me.improperissues.servertips.commands.Commands;
import me.improperissues.servertips.commands.Tabs;
import me.improperissues.servertips.data.Config;
import me.improperissues.servertips.data.Tip;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public final class ServerTips extends JavaPlugin {

    public static String STARTER;

    @Override
    public void onEnable() {
        // Plugin startup logic
        STARTER = Config.getPluginPrefix();

        // Commands
        getCommand("tip").setExecutor(new Commands());
        getCommand("tip").setTabCompleter(new Tabs());

        // Files
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Loops
        new BukkitRunnable() {
            int passSec = 0;
            int nextTip = (int) Math.ceil(Config.getGeneralMaxDelay() + Math.random() * (Config.getGeneralMaxDelay() - Config.getGeneralMinDelay()));
            int index = 0;

            @Override
            public void run() {
                if (passSec < nextTip) {
                    if (!Config.getGeneralPaused()) passSec ++;
                } else {
                    try {
                        List<Tip> tips = new ArrayList<>();
                        for (String name : Tip.listTips()) tips.add(Tip.load(name));
                        Tip tip = tips.get(index);
                        if (Config.getGeneralRandomize()) index = (int) Math.floor(Math.random() * tips.size());
                        else {
                            index ++;
                            index = (index < tips.size() ? index : 0);
                        }
                        tip.broadcast();
                        tip.toSound().playAllAt();
                    } catch (NullPointerException exception) {
                        // empty
                    }
                    passSec = 0;
                    nextTip = (int) Math.ceil(Config.getGeneralMaxDelay() + Math.random() * (Config.getGeneralMaxDelay() - Config.getGeneralMinDelay()));
                }
            }
        }.runTaskTimer(this,0,20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance() {
        return Bukkit.getServer().getPluginManager().getPlugin("ServerTips");
    }
}
