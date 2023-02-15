package me.improper.quicklifesteal.server;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ServerUtils {

    public static List<String> listPlayers() {
        List<String> list = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(p -> list.add(p.getName()));
        return list;
    }
}
