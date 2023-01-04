package me.improperissues.fireart.other;

import me.improperissues.fireart.data.PaintedBlock;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PaintRaycast {

    public static PaintedBlock getRaycasted(Player player) {
        Location loc = player.getEyeLocation();
        Vector dir = player.getLocation().getDirection();

        for (int i = 0; i < 200; i ++) {
            double x = dir.getX() * i;
            double y = dir.getY() * i;
            double z = dir.getZ() * i;
            Location newLoc = loc.clone().add(x,y,z);
            Block block = newLoc.getBlock();
            if (!block.isPassable() && block.getType().isBlock()) return new PaintedBlock(block);
        }
        return null;
    }
}
