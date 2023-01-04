package me.improperissues.roboroan.other;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.awt.*;

public class Messages {

    public static String STARTER = "§7[§6Server§7] §7[" +
            ChatColor.of(new Color(24, 41, 66)) + "§l" + "R" +
            ChatColor.of(new Color(35, 62, 98)) + "§l" + "O" +
            ChatColor.of(new Color(45, 71, 112)) + "§l" + "B" +
            ChatColor.of(new Color(46, 76, 128)) + "§l" + "O" +
            ChatColor.of(new Color(64, 98, 154)) + "§l" + "R" +
            ChatColor.of(new Color(81, 112, 166)) + "§l" + "O" +
            ChatColor.of(new Color(98, 142, 185)) + "§l" + "A" +
            ChatColor.of(new Color(124, 160, 217)) + "§l" + "N" +
            "§7] §";

    public static String buildString(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) builder.append(arg).append(" ");
        return String.valueOf(builder).trim();
    }

    public static String impPings(String string) {
        ServerSound sound = new ServerSound(null, Sound.BLOCK_NOTE_BLOCK_PLING, 10, 10);
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (string.contains(p.getName())) {
                string = string.replaceAll(p.getName(),"§3§o@" + p.getName() + "§r");
                sound.repeatAt(p, 3, 5);
            }
        }
        if (string.toLowerCase().contains("everyone")) {
            string = string.replaceAll("everyone","§3§o@everyone§r").replaceAll("Everyone","§3§o@Everyone§r");
            sound.repeatAllAt(3,5);
        }
        return string;
    }

    public static String impColors(String string) {
        return string.replaceAll("&","§");
    }
}
