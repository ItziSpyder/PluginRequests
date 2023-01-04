package me.improperissues.playerblocks.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.io.Serializable;

public class PlayerBlock implements Serializable {

    private BlockLocation location;
    private String type, placer;

    public PlayerBlock(Player player, Block block) {
        this.location = new BlockLocation(block);
        this.type = block.getType().name();
        this.placer = player.getName();
    }

    public PlayerBlock(Player player, Location location) {
        this.location = new BlockLocation(location);
        this.type = location.getBlock().getType().name();
        this.placer = player.getName();
    }

    public boolean equals(PlayerBlock block) {
        return this.toString().equals(block.toString());
    }

    public Location toLocation() {
        return location.toLocation();
    }

    public String toString() {
        return "PlayerBlock:{placer=" + placer + ",blockType=" + type + ",location=" + location.toString() + "}";
    }

    public Block toBlock() {
        return location.toLocation().getBlock();
    }

    public void clear() {
        location.toLocation().getBlock().setType(Material.AIR);
    }

    public BlockLocation getLocation() {
        return location;
    }

    public String getPlacer() {
        return placer;
    }

    public String getType() {
        return type;
    }

    public void setPlacer(String placer) {
        this.placer = placer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(BlockLocation location) {
        this.location = location;
    }
}
