package me.improperissues.coffeecup.other;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Messages {

    static HashMap<String,Long> cooldown = new HashMap<>();

    // §

    public static String starter = ChatColor.of(new Color(102, 98, 90)) + "§l>" + ChatColor.of(new Color(133, 95, 21)) + "§l> §";
    public static String invalid = starter + "cIncomplete or invalid command! \"/help\" for command help!";
    public static String noperms = starter + "4You do not have access to this";
    public static String cannotuse = starter + "4You cannot use this here or this action is disabled!";

    public static boolean send(Player player, String message) {
        if (cooldown.containsKey(player.getName()) && cooldown.get(player.getName()) > System.currentTimeMillis()) {
            return false;
        }
        cooldown.put(player.getName(),System.currentTimeMillis() + (500));
        player.sendMessage(message);
        return true;
    }
    public static boolean bm(String message) {
        if (cooldown.containsKey("server") && cooldown.get("server") > System.currentTimeMillis()) {
            return false;
        }
        cooldown.put("server",System.currentTimeMillis() + (500));
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(message);
        }
        return true;
    }
    public static boolean bmOp(String message) {
        if (cooldown.containsKey("server") && cooldown.get("server") > System.currentTimeMillis()) {
            return false;
        }
        cooldown.put("server",System.currentTimeMillis() + (500));
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.isOp()) {
                p.sendMessage(message);
            }
        }
        return true;
    }
    public static List<String> autoLoreSplit(String string, int wordsPerLine, String prefix) {
        List<String> lore = new ArrayList<>();
        List<String> words = new ArrayList<>(Arrays.asList(string.split(" ")));
        int lines = (int) Math.ceil(words.size() / (double) wordsPerLine);
        for (int i = 0; i < lines; i ++) {
            StringBuilder builder = new StringBuilder();
            for (int j = (i * wordsPerLine); j < (i * wordsPerLine) + wordsPerLine; j ++) {
                try {
                    builder.append(prefix).append(words.get(j)).append(" ");
                } catch (IndexOutOfBoundsException exception) {
                    // empty
                }
            }
            lore.add(String.valueOf(builder).trim());
        }
        return lore;
    }
}
