/**
 * Easier to make custom items now!
 * by ImproperIssues
 */

package me.improper.ogredupealias.items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Set;

public class CustomItem extends ItemStack {

    private ItemMeta meta;
    private Player whoClicked;

    /**
     * Make a custom item from an ItemStack
     * @param item ItemStack
     */
    public CustomItem(ItemStack item) {
        super(item);
        this.meta = super.getItemMeta();
    }

    /**
     * Make a custom item from a Material type
     * @param material Material
     */
    public CustomItem(Material material) {
        super(new ItemStack(material));
        this.meta = super.getItemMeta();
    }

    /**
     * Right click method (extend this class)
     */
    public void onRightClick() {

    }

    /**
     * Left click method (extend this class)
     */
    public void onLeftClick() {

    }

    /**
     * Drops this item naturally at the specified location
     * @param location Location
     */
    public void dropNaturally(Location location) {
        location.getWorld().dropItemNaturally(location,this);
    }

    /**
     * Drops this item at the specified location
     * @param location Location
     */
    public void drop(Location location) {
        location.getWorld().dropItem(location,this);
    }

    /**
     * Add all existing item flags to the item
     */
    public void addAllItemFlags() {
        for (ItemFlag flag : ItemFlag.values()) meta.addItemFlags(flag);
    }

    /**
     * Sets the display name of this custom item without accessing ItemMeta
     * @param displayName String
     */
    public void setDisplayName(String displayName) {
        meta.setDisplayName(displayName);
    }

    /**
     * Sets the lore of this custom item without accessing ItemMeta
     * @param lore List<String>
     */
    public void setLore(List<String> lore) {
        meta.setLore(lore);
    }

    /**
     * Adds the specified item flags to the item without accessing ItemMeta
     * @param flags ItemFlags...
     */
    public void addItemFlags(ItemFlag... flags) {
        meta.addItemFlags(flags);
    }

    /**
     * Sets the custom model data of the item without accessing ItemMeta
     * @param customModelData Integer
     */
    public void setCustomModelData(int customModelData) {
        meta.setCustomModelData(customModelData);
    }

    /**
     * Sets the unbreakable state of this custom item without accessing ItemMeta
     * @param unbreakable Boolean
     */
    public void setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
    }

    /**
     * Sets the clicker of this custom item
     * @param whoClicked Player
     */
    public void setWhoClicked(Player whoClicked) {
        this.whoClicked = whoClicked;
    }

    /**
     * Sets the ItemMeta of this custom item
     * @param meta ItemMeta
     */
    public void setMeta(ItemMeta meta) {
        this.meta = meta;
    }

    /**
     * Returns the display name of this custom item
     * @return String
     */
    public String getDisplayName() {
        return meta.getDisplayName();
    }

    /**
     * Returns the lore of this custom item
     * @return List<String>
     */
    public List<String> getLore() {
        return meta.getLore();
    }

    /**
     * Returns a set if item flags from this custom item
     * @return Set<ItemFlags>
     */
    public Set<ItemFlag> getItemFlags() {
        return meta.getItemFlags();
    }

    /**
     * Returns the custom model data of this custom item
     * @return Long
     */
    public int getCustomModelData() {
        return meta.getCustomModelData();
    }

    /**
     * Returns the unbreakable state of this custom item
     * @return Boolean
     */
    public boolean getUnbreakable() {
        return meta.isUnbreakable();
    }

    /**
     * Returns the clicker of this custom item
     * @return Player
     */
    public Player getWhoClicked() {
        return whoClicked;
    }

    /**
     * Returns the ItemMeta of this custom item
     * @return ItemMeta
     */
    public ItemMeta getMeta() {
        return meta;
    }
}
