package me.improperissues.fireart.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.io.Serializable;

public class SelectedBlock implements Serializable {

    private String world, type, ogType;
    private int x, y, z;

    public SelectedBlock(Block block) {
        this.world = block.getWorld().getName();
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.type = block.getType().name();
        this.ogType = block.getType().name();
    }

    public SelectedBlock(Location location) {
        Block block = location.getBlock();
        this.world = block.getWorld().getName();
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.type = block.getType().name();
        this.ogType = block.getType().name();
    }

    public void revert() {
        Block block = toBlock();
        block.setType(Material.valueOf(ogType));
    }

    public void setMaterial(Material type) {
        toBlock().setType(type);
    }

    public Segment spreadSelect(Segment segment, SelectedBlock block, Location ogLoc, Material ogType) {
        if (segment.getLocationIdLog().size() >= 2000) return segment;
        Location loc = block.toLocation();
        segment.getBlocks().add(block);
        segment.getBlockLog().add(block.toString());
        segment.getLocationIdLog().add(block.toLocationId());
        SelectedBlock front = new SelectedBlock(loc.clone().add(1,0,0).getBlock());
        SelectedBlock back = new SelectedBlock(loc.clone().add(-1,0,0).getBlock());
        SelectedBlock left = new SelectedBlock(loc.clone().add(0,0,1).getBlock());
        SelectedBlock right = new SelectedBlock(loc.clone().add(0,0,-1).getBlock());
        SelectedBlock top = new SelectedBlock(loc.clone().add(0,1,1).getBlock());
        SelectedBlock bottom = new SelectedBlock(loc.clone().add(0,-1,0).getBlock());
        if (front.getOgType().equals(ogType) && !segment.getBlockLog().contains(front.toString()) && front.toLocation().distanceSquared(ogLoc) < 500) {
            segment.getBlocks().add(front);
            segment.getBlockLog().add(front.toString());
            segment.getLocationIdLog().add(front.toLocationId());
            front.spreadSelect(segment,front,ogLoc,ogType);
        }
        if (back.getOgType().equals(ogType) && !segment.getBlockLog().contains(back.toString()) && back.toLocation().distanceSquared(ogLoc) < 500) {
            segment.getBlocks().add(back);
            segment.getBlockLog().add(back.toString());
            segment.getLocationIdLog().add(back.toLocationId());
            back.spreadSelect(segment,back,ogLoc,ogType);
        }
        if (left.getOgType().equals(ogType) && !segment.getBlockLog().contains(left.toString()) && left.toLocation().distanceSquared(ogLoc) < 500) {
            segment.getBlocks().add(left);
            segment.getBlockLog().add(left.toString());
            segment.getLocationIdLog().add(left.toLocationId());
            left.spreadSelect(segment,left,ogLoc,ogType);
        }
        if (right.getOgType().equals(ogType) && !segment.getBlockLog().contains(right.toString()) && right.toLocation().distanceSquared(ogLoc) < 500) {
            segment.getBlocks().add(right);
            segment.getBlockLog().add(right.toString());
            segment.getLocationIdLog().add(right.toLocationId());
            right.spreadSelect(segment,right,ogLoc,ogType);
        }
        if (top.getOgType().equals(ogType) && !segment.getBlockLog().contains(top.toString()) && top.toLocation().distanceSquared(ogLoc) < 50) {
            segment.getBlocks().add(top);
            segment.getBlockLog().add(top.toString());
            segment.getLocationIdLog().add(top.toLocationId());
            top.spreadSelect(segment,top,ogLoc,ogType);
        }
        if (bottom.getOgType().equals(ogType) && !segment.getBlockLog().contains(bottom.toString()) && bottom.toLocation().distanceSquared(ogLoc) < 50) {
            segment.getBlocks().add(bottom);
            segment.getBlockLog().add(bottom.toString());
            segment.getLocationIdLog().add(bottom.toLocationId());
            bottom.spreadSelect(segment,bottom,ogLoc,ogType);
        }
        return segment;
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(world),x,y,z);
    }

    public Block toBlock() {
        return toLocation().getBlock();
    }

    public String toString() {
        return "SelectedBlock:{type="+type+",originalType="+ogType+",location:{world="+world+",x="+x+",y="+y+",z="+z+"}}";
    }

    public String toLocationId() {
        return "BlockLocationId:{world="+world+",x="+x+",y="+y+",z="+z+"}";
    }

    public void setType(Material type) {
        this.type = type.name();
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void setOgType(Material ogType) {
        this.ogType = ogType.name();
    }

    public int getZ() {
        return z;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String getWorld() {
        return world;
    }

    public Material getType() {
        return Material.valueOf(type);
    }

    public Material getOgType() {
        return Material.valueOf(ogType);
    }
}
