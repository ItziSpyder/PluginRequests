package me.improper.ogredupealias.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serializable;

public class SavedLocation implements Serializable {

    private int x, y, z;
    private String world;

    public SavedLocation(World world, int x, int y, int z) {
        this.world = world.getName();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SavedLocation(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(world),x,y,z);
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
