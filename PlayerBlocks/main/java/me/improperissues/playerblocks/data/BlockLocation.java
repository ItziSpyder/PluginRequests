package me.improperissues.playerblocks.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.io.Serializable;

public class BlockLocation implements Serializable {

    private String world;
    private int x, y, z;

    public BlockLocation(Block block) {
        this.world = block.getLocation().getWorld().getName();
        this.x = block.getLocation().getBlockX();
        this.y = block.getLocation().getBlockY();
        this.z = block.getLocation().getBlockZ();
    }

    public BlockLocation(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(world),x,y,z);
    }

    public String toString() {
        return "BlockLocation:{world=" + world + ",x=" + x + ",y=" + y + ",z=" + z + "}";
    }

    public String getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
