package me.improperissues.fireart;

import me.improperissues.fireart.commands.Commands;
import me.improperissues.fireart.data.PaintedBlock;
import me.improperissues.fireart.events.PaintEvent;
import me.improperissues.fireart.events.PaintSelectorMenu;
import me.improperissues.fireart.other.Items;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class FireArt extends JavaPlugin {

    public static String STARTER = "§7[§aFireArt§7] §";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getLogger().info("Enabled FireArt " + getDescription().getVersion() + "!");

        // Events
        getServer().getPluginManager().registerEvents(new PaintEvent(),this);
        getServer().getPluginManager().registerEvents(new PaintSelectorMenu(),this);

        // Commands
        getCommand("givebrush").setExecutor(new Commands());
        getCommand("paintselector").setExecutor(new Commands());

        // Items
        PaintedBlock.ALLPOSSIBLE = PaintedBlock.getAllPossible();
        Items.registerItems();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getLogger().info("Disabled FireArt " + getDescription().getVersion() + "!");
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("FireArt");
    }
}
