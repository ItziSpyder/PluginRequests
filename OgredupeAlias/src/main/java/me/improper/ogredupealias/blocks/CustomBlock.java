package me.improper.ogredupealias.blocks;

import me.improper.ogredupealias.data.SavedLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.io.Serializable;

public class CustomBlock implements Serializable{

    private String material, locationId, whoClicked;
    private SavedLocation location;

    public CustomBlock(Block block) {
        this.material = block.getType().name();
        this.location = new SavedLocation(block.getLocation());
        this.locationId = "BlockId:{world="
                + location.getWorld()
                + ",x=" + location.getX()
                + ",y=" + location.getY()
                + ",z=" + location.getZ()
                + "}";
    }

    public CustomBlock(Location location) {
        Block block = location.getBlock();
        this.material = block.getType().name();
        this.location = new SavedLocation(block.getLocation());
        this.locationId = "BlockId:{world="
                + this.location.getWorld()
                + ",x=" + this.location.getX()
                + ",y=" + this.location.getY()
                + ",z=" + this.location.getZ()
                + "}";
    }

    public void onBreak() {

    }

    public void onPlace() {

    }

    public void onRightClick() {

    }

    public void onLeftClick() {

    }

    public Location toLocation() {
        return location.toLocation();
    }

    public Block toBlock() {
        return location.toLocation().getBlock();
    }

    public void setLocation(SavedLocation location) {
        this.location = location;
    }

    public void setMaterial(Material material) {
        this.material = material.name();
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void setWhoClicked(Player whoClicked) {
        this.whoClicked = whoClicked.getName();
    }

    public SavedLocation getLocation() {
        return location;
    }

    public Material getMaterial() {
        return Material.valueOf(material);
    }

    public String getLocationId() {
        return locationId;
    }

    public Player getWhoClicked() {
        return Bukkit.getPlayer(whoClicked);
    }
}
