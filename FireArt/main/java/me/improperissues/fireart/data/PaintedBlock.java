package me.improperissues.fireart.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.ArrayList;
import java.util.List;

public class PaintedBlock {

    public static Material[] ALLPOSSIBLE = new Material[0];

    public static Material[] getAllPossible() {
        List<Material> list = new ArrayList<>();
        for (Material material : Material.values()) {
            if (material.isBlock() && material.isSolid() && material.isOccluding()) list.add(material);
        }
        return list.toArray(new Material[0]);
    }
    private Material type, ogType;
    private String world;
    private int x, y, z;
    private BlockData ogData;

    public PaintedBlock(Block block) {
        this.type = block.getType();
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.world = block.getWorld().getName();
        this.ogType = block.getType();
        this.ogData = block.getBlockData();
    }

    public static void spread(PaintedBlock block, PaintAction action, Location origin, Material ogType, Material newType, int radius) {
        action.addChanged(block);
        block.toBlock().setType(newType);
        Location loc = block.toLocation();
        Block top = loc.clone().add(0,0,1).getBlock();
        Block bottom = loc.clone().add(0,0,-1).getBlock();
        Block left = loc.clone().add(1,0,0).getBlock();
        Block right = loc.clone().add(-1,0,0).getBlock();
        Block above = loc.clone().add(0,1,0).getBlock();
        Block below = loc.clone().add(0,-1,0).getBlock();
        if (top.getType().equals(ogType) && !ogType.equals(newType) && top.getLocation().distanceSquared(origin) < radius) {
            PaintedBlock painting = new PaintedBlock(top);
            action.addChanged(painting);
            spread(painting,action,origin,ogType,newType,radius);
        }
        if (bottom.getType().equals(ogType) && !ogType.equals(newType) && bottom.getLocation().distanceSquared(origin) < radius) {
            PaintedBlock painting = new PaintedBlock(bottom);
            action.addChanged(painting);
            spread(painting,action,origin,ogType,newType,radius);
        }
        if (left.getType().equals(ogType) && !ogType.equals(newType) && left.getLocation().distanceSquared(origin) < radius) {
            PaintedBlock painting = new PaintedBlock(left);
            action.addChanged(painting);
            spread(painting,action,origin,ogType,newType,radius);
        }
        if (right.getType().equals(ogType) && !ogType.equals(newType) && right.getLocation().distanceSquared(origin) < radius) {
            PaintedBlock painting = new PaintedBlock(right);
            action.addChanged(painting);
            spread(painting,action,origin,ogType,newType,radius);
        }
        if (above.getType().equals(ogType) && !ogType.equals(newType) && above.getLocation().distanceSquared(origin) < (radius / 50.0)) {
            PaintedBlock painting = new PaintedBlock(above);
            action.addChanged(painting);
            spread(painting,action,origin,ogType,newType,radius);
        }
        if (below.getType().equals(ogType) && !ogType.equals(newType) && below.getLocation().distanceSquared(origin) < (radius / 50.0)) {
            PaintedBlock painting = new PaintedBlock(below);
            action.addChanged(painting);
            spread(painting,action,origin,ogType,newType,radius);
        }
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(world),x,y,z);
    }

    public Block toBlock() {
        return toLocation().getBlock();
    }

    public BlockData getOgData() {
        return ogData;
    }

    public Material getOgType() {
        return ogType;
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

    public Material getType() {
        return type;
    }

    public void setType(Material type) {
        this.type = type;
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
