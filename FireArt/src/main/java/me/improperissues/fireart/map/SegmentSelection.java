package me.improperissues.fireart.map;

import me.improperissues.fireart.data.Segment;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SegmentSelection {

    private static HashMap<String,Segment> SEGMENTS = new HashMap<>();

    public static boolean hasSelection(Player player) {
        return SEGMENTS.containsKey(player.getName());
    }

    public static void addSegment(Player player, Segment segment) {
        SEGMENTS.put(player.getName(),segment);
    }

    public static void removeSegment(Player player) {
        SEGMENTS.remove(player.getName());
    }

    public static Segment getSegment(Player player) {
        return SEGMENTS.get(player.getName());
    }
}
