package me.improper.ogredupealias.other;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ServerUtil {

    public static List<String> listPlayers() {
        List<String> list = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) list.add(p.getName());
        return list;
    }

    public static List<String> listStaff() {
        List<String> list = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) if (p.isOp()) list.add(p.getName());
        return list;
    }
}
