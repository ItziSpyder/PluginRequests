package me.improperissues.fireart.map;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PaintSelection {

    private static HashMap<String,Material> SELECTION = new HashMap<>();

    public static Material getSelection(Player player) {
        Material material = SELECTION.get(player.getName());
        if (material == null) return Material.RED_TERRACOTTA;
        else return material;
    }

    public static void setSelection(Player player, Material material) {
        SELECTION.put(player.getName(),material);
    }
}
