package me.improper.ogredupealias.events;

import me.improper.ogredupealias.OgredupeAlias;
import me.improper.ogredupealias.data.ChatLogger;
import me.improper.ogredupealias.data.Config;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OnChat implements Listener {

    public static HashMap<String,Long> CHAT_COOLDOWN = new HashMap<>();
    public static HashMap<String,String> LAST_MESSAGE = new HashMap<>();
    public static boolean ISCHATMUTED = false;
    public static List<String> CHATLISTENER = new ArrayList<>();

    @EventHandler
    public static void PlayerChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        // ChatLog
        // ChatLogger.writeLog(p,msg);

        // Unicode blocker
        String unifontBlock = msg.replaceAll("[\\!\\\"\\#\\$\\%\\&\\'\\(\\)\\*\\+\\,\\-\\.\\/[0-9]\\:\\;\\<\\=\\>\\?\\@[A-Z]\\[\\]\\^\\_\\'[a-z]\\{\\|\\}\\~\\⌂\\Ç\\ü\\é\\â\\ä\\à\\å\\ç\\ê\\ë\\è\\ï\\î\\ì\\Ä\\Å\\É\\æ\\Æ\\ô\\ö\\ò\\û\\ù\\ÿ\\Ö\\Ü\\ø\\£\\Ø\\×\\ƒ\\á\\í\\ó\\ú\\ñ\\Ñ\\ª\\º\\¿\\®\\¬\\½\\¼\\¡\\«\\»\\§]","");
        if (!Config.BOOLEANS.allowUnicode() && unifontBlock.trim().length() != 0) {
            e.setCancelled(true);
            p.sendMessage(OgredupeAlias.STARTER
                    + ChatColor.GRAY + "["
                    + ChatColor.RED + "Unicode Detector"
                    + ChatColor.GRAY + "] "
                    + Config.MESSAGES.getUnicodeMessage());
        }

        // Chat disabler
        if (ISCHATMUTED && !p.hasPermission("ogredupealias.chat.bypass")) {
            e.setCancelled(true);
            p.sendMessage(OgredupeAlias.STARTER
                    + ChatColor.GRAY + "["
                    + ChatColor.RED + "Chat"
                    + ChatColor.GRAY + "] "
                    + Config.MESSAGES.getChatDisabled());
            return;
        }

        // Op bypass
        if (Config.BOOLEANS.allowOpBypass() && p.isOp()) return;

        // Chat spam prevention
        if (!Config.BOOLEANS.allowChatSpam() && CHAT_COOLDOWN.containsKey(p.getName()) && CHAT_COOLDOWN.get(p.getName()) > System.currentTimeMillis()) {
            e.setCancelled(true);
            double time = Math.floor((CHAT_COOLDOWN.get(p.getName()) - System.currentTimeMillis()) / 10.0) / 100;
            p.sendMessage(OgredupeAlias.STARTER + Config.MESSAGES.getSpam());
            p.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "You are on cooldown for another " + ChatColor.YELLOW + time + ChatColor.RED + " seconds!");
            return;
        }
        CHAT_COOLDOWN.put(p.getName(),System.currentTimeMillis() + (long) Config.VALUES.getChatCooldown());

        // Chat repeat prevention
        if (!Config.BOOLEANS.allowChatSpam()
                && LAST_MESSAGE.containsKey(p.getName())
                && (simplify(LAST_MESSAGE.get(p.getName())).contains(simplify(msg))
                || simplify(msg).contains(simplify(LAST_MESSAGE.get(p.getName()))))) {
            e.setCancelled(true);
            p.sendMessage(OgredupeAlias.STARTER + Config.MESSAGES.getRepeat());
            return;
        }
        LAST_MESSAGE.put(p.getName(),msg);

        // Chat swear prevention
        try {
            if (Config.BOOLEANS.allowChatSwear()) return;
            String filtering = simplify(msg);
            for (String whitelist : Config.LISTS.getChatBlacklistOverrides()) {
                whitelist = simplify(whitelist);
                if (filtering.contains(whitelist)) filtering = filtering.replaceAll(whitelist,"");
            }
            for (String blacklisted : Config.LISTS.getChatBlacklist()) {
                blacklisted = simplify(blacklisted);
                if (filtering.contains(blacklisted)) {
                    e.setCancelled(true);
                    p.sendMessage(OgredupeAlias.STARTER + Config.MESSAGES.getSwear());
                    p.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "Your message: " + ChatColor.GRAY + msg);
                    p.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "Filtered: " + ChatColor.GRAY + filtering);
                    p.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "Caught: " + ChatColor.GRAY + blacklisted);
                    TextComponent broadcast = new TextComponent(OgredupeAlias.STARTER + ChatColor.GRAY + "[" + ChatColor.RED + "Anti-Swear"
                            + ChatColor.GRAY + "] " + ChatColor.GREEN + p.getName() + ChatColor.DARK_GRAY + " >> " + ChatColor.YELLOW + msg);
                    broadcast.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,TextComponent.fromLegacyText(ChatColor.RED + "Flagged: " + ChatColor.GRAY + blacklisted)));
                    for (Player online : Bukkit.getOnlinePlayers()) if (online.hasPermission(Config.PLUGIN.getAntiSwearNotifyPermission())) online.spigot().sendMessage(broadcast);
                    break;
                }
            }
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
                .replaceAll("\\.","")
                .replaceAll(" ","")
                .replaceAll("_","")
                .replaceAll("-","");
        return string;
    }
}
