package me.improper.ogredupealias.events;

import me.improper.ogredupealias.OgredupeAlias;
import me.improper.ogredupealias.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OnCommand implements Listener {

    public static HashMap<String,Long> COMMAND_COOLDOWN = new HashMap<>();
    public static List<String> COMMANDLISTERS = new ArrayList<>();

    @EventHandler
    public static void PlayerCommandEvent(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage().split(" ")[0];
        String cmd = simplify(e.getMessage().split(" ")[0]);

        try {

            // CommandSpy
            if (!Config.LISTS.getCommandSpyBlacklist().contains(msg))
                for (Player online : Bukkit.getOnlinePlayers())
                    if (online.hasPermission("ogredupealias.chat.see") && COMMANDLISTERS.contains(online.getName()))
                        online.sendMessage(OgredupeAlias.STARTER
                                + ChatColor.GRAY + "["
                                + ChatColor.BLUE + "Command Spy"
                                + ChatColor.GRAY + "] "
                                + ChatColor.GREEN + p.getName()
                                + ChatColor.DARK_GRAY + " >> "
                                + ChatColor.YELLOW + e.getMessage().replaceAll(msg,ChatColor.GOLD
                                + msg + ChatColor.YELLOW));

            // Op bypass
            if (Config.BOOLEANS.allowOpBypass() && p.isOp()) return;

            // Plugin specific commands prevention
            if (!Config.BOOLEANS.allowPluginSpecificCommands() && cmd.contains(":")) {
                e.setCancelled(true);
                p.sendMessage(OgredupeAlias.STARTER + Config.MESSAGES.getPluginSpecific());
                return;
            }

            // Command spam prevention
            if (!Config.BOOLEANS.allowCommandSpam() && Config.LISTS.getCommandCooldownList().contains(cmd)  && COMMAND_COOLDOWN.containsKey(p.getName()) && COMMAND_COOLDOWN.get(p.getName()) > System.currentTimeMillis()) {
                e.setCancelled(true);
                double time = Math.floor((COMMAND_COOLDOWN.get(p.getName()) - System.currentTimeMillis()) / 10.0) / 100;
                p.sendMessage(OgredupeAlias.STARTER + Config.MESSAGES.getSpam());
                p.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "You are on cooldown for another " + ChatColor.YELLOW + time + ChatColor.RED + " seconds!");
                return;
            }
            COMMAND_COOLDOWN.put(p.getName(),System.currentTimeMillis() + (long) Config.VALUES.getCommandCooldown());
        } catch (Exception exception) {}
    }

    /**
     * Simplifies a string for comparison use.
     *
     * @param string String
     * @return The simplified string
     */
    private static String simplify(String string) {
        string = string.trim()
                .toLowerCase()
                .replaceAll("//.","")
                .replaceAll(" ","")
                .replaceAll("_","")
                .replaceAll("-","");
        return string;
    }
}
