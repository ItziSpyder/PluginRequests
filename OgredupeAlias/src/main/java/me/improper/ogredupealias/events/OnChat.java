package me.improper.ogredupealias.events;

import me.improper.ogredupealias.OgredupeAlias;
import me.improper.ogredupealias.data.Config;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;

public class OnChat implements Listener {

    public static HashMap<String,Long> COMMAND_COOLDOWN = new HashMap<>();
    public static HashMap<String,Long> CHAT_COOLDOWN = new HashMap<>();
    public static HashMap<String,String> LAST_MESSAGE = new HashMap<>();


    @EventHandler
    public static void PlayerChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        // Op bypass
        if (Config.allowOpBypass() && p.isOp()) return;

        // Chat spam prevention
        if (!Config.allowChatSpam() && CHAT_COOLDOWN.containsKey(p.getName()) && CHAT_COOLDOWN.get(p.getName()) > System.currentTimeMillis()) {
            e.setCancelled(true);
            double time = Math.floor((CHAT_COOLDOWN.get(p.getName()) - System.currentTimeMillis()) / 10.0) / 100;
            p.sendMessage(OgredupeAlias.STARTER + Config.getSpamMessage());
            p.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "You are on cooldown for another " + ChatColor.YELLOW + time + ChatColor.RED + " seconds!");
            return;
        }
        CHAT_COOLDOWN.put(p.getName(),System.currentTimeMillis() + (long) Config.getChatCooldown());

        // Chat repeat prevention
        if (!Config.allowChatSpam() && LAST_MESSAGE.containsKey(p.getName()) && simplify(LAST_MESSAGE.get(p.getName())).equals(simplify(msg))) {
            e.setCancelled(true);
            p.sendMessage(OgredupeAlias.STARTER + Config.getRepeatMessage());
            return;
        }
        LAST_MESSAGE.put(p.getName(),msg);

        // Chat swear prevention
        try {
            if (Config.allowChatSwear()) return;
            String filtering = simplify(msg);
            for (String whitelist : Config.getChatBlacklistOverrides()) {
                whitelist = simplify(whitelist);
                if (filtering.contains(whitelist)) filtering = filtering.replaceAll(whitelist,"");
            }
            for (String blacklisted : Config.getChatBlacklist()) {
                blacklisted = simplify(blacklisted);
                if (filtering.contains(blacklisted)) {
                    e.setCancelled(true);
                    p.sendMessage(OgredupeAlias.STARTER + Config.getSwearMessage());
                    p.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "Your message: " + ChatColor.GRAY + msg);
                    p.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "Filtered: " + ChatColor.GRAY + filtering);
                    p.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "Caught: " + ChatColor.GRAY + blacklisted);
                    TextComponent broadcast = new TextComponent(OgredupeAlias.STARTER + ChatColor.GRAY + "[" + ChatColor.RED + "Anti-Swear"
                            + ChatColor.GRAY + "] " + ChatColor.GREEN + p.getName() + ChatColor.DARK_GRAY + " >> " + ChatColor.YELLOW + msg);
                    broadcast.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,TextComponent.fromLegacyText(ChatColor.RED + "Flagged: " + ChatColor.GRAY + blacklisted)));
                    for (Player online : Bukkit.getOnlinePlayers()) if (online.hasPermission(Config.getAntiSwearNotifyPermission())) online.spigot().sendMessage(broadcast);
                    break;
                }
            }
        } catch (Exception exception) {}
    }

    @EventHandler
    public static void PlayerCommandEvent(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String cmd = simplify(e.getMessage().split(" ")[0]);

        try {
            // Op bypass
            if (Config.allowOpBypass() && p.isOp()) return;

            // Plugin specific commands prevention
            if (!Config.allowPluginSpecificCommands() && cmd.contains(":")) {
                e.setCancelled(true);
                p.sendMessage(OgredupeAlias.STARTER + Config.getPluginSpecificMessage());
                return;
            }

            // Command spam prevention
            if (!Config.allowCommandSpam() && Config.getCommandCooldownList().contains(cmd)  && COMMAND_COOLDOWN.containsKey(p.getName()) && COMMAND_COOLDOWN.get(p.getName()) > System.currentTimeMillis()) {
                e.setCancelled(true);
                double time = Math.floor((COMMAND_COOLDOWN.get(p.getName()) - System.currentTimeMillis()) / 10.0) / 100;
                p.sendMessage(OgredupeAlias.STARTER + Config.getSpamMessage());
                p.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "You are on cooldown for another " + ChatColor.YELLOW + time + ChatColor.RED + " seconds!");
                return;
            }
            COMMAND_COOLDOWN.put(p.getName(),System.currentTimeMillis() + (long) Config.getCommandCooldown());
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
