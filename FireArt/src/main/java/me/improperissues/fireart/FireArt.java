package me.improperissues.fireart;

import me.improperissues.fireart.commands.Commands;
import me.improperissues.fireart.commands.Tabs;
import me.improperissues.fireart.data.PaintedBlock;
import me.improperissues.fireart.events.PaintEvent;
import me.improperissues.fireart.events.PaintSelectorMenu;
import me.improperissues.fireart.events.PlayerEvent;
import me.improperissues.fireart.other.Items;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class FireArt extends JavaPlugin {

    public static String STARTER = ChatColor.GRAY + "[" + ChatColor.GREEN + "FireArt" + ChatColor.GRAY + "] ";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getLogger().info("Enabled FireArt " + getDescription().getVersion() + "!");
        getServer().broadcastMessage(STARTER + ChatColor.GREEN + "FireArt has enabled!");

        // Events
        getServer().getPluginManager().registerEvents(new PaintEvent(),this);
        getServer().getPluginManager().registerEvents(new PaintSelectorMenu(),this);
        getServer().getPluginManager().registerEvents(new PlayerEvent(),this);

        // Files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Commands
        getCommand("givebrush").setExecutor(new Commands());
        getCommand("givebrush").setTabCompleter(new Tabs());
        getCommand("giveselector").setExecutor(new Commands());
        getCommand("giveselector").setTabCompleter(new Tabs());
        getCommand("giveinspector").setExecutor(new Commands());
        getCommand("giveinspector").setTabCompleter(new Tabs());
        getCommand("paintselector").setExecutor(new Commands());
        getCommand("paintselector").setTabCompleter(new Tabs());
        getCommand("segment").setExecutor(new Commands());
        getCommand("segment").setTabCompleter(new Tabs());
        getCommand("artkit").setExecutor(new Commands());
        getCommand("artkit").setTabCompleter(new Tabs());
        getCommand("toggle").setExecutor(new Commands());
        getCommand("toggle").setTabCompleter(new Tabs());

        // Items
        PaintedBlock.ALLPOSSIBLE = PaintedBlock.getAllPossible();
        Items.registerItems();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().broadcastMessage(STARTER + ChatColor.GREEN + "FireArt has disabled!");
        getServer().getLogger().info("Disabled FireArt " + getDescription().getVersion() + "!");
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("FireArt");
    }
}
