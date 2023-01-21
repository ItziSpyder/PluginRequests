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

    public CustomItem(ItemStack item) {
        super(item);
        this.meta = super.getItemMeta();
    }

    public CustomItem(Material material) {
        super(new ItemStack(material));
        this.meta = super.getItemMeta();
    }

    public void onRightClick() {

    }

    public void onLeftClick() {

    }

    public void dropNaturally(Location location) {
        location.getWorld().dropItemNaturally(location,this);
    }

    public void drop(Location location) {
        location.getWorld().dropItem(location,this);
    }

    public void addAllItemFlags() {
        for (ItemFlag flag : ItemFlag.values()) meta.addItemFlags(flag);
    }

    public void setDisplayName(String displayName) {
        meta.setDisplayName(displayName);
    }

    public void setLore(List<String> lore) {
        meta.setLore(lore);
    }

    public void addItemFlags(ItemFlag... flags) {
        meta.addItemFlags(flags);
    }

    public void setCustomModelData(int customModelData) {
        meta.setCustomModelData(customModelData);
    }

    public void setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
    }

    public void setWhoClicked(Player whoClicked) {
        this.whoClicked = whoClicked;
    }

    public void setMeta(ItemMeta meta) {
        this.meta = meta;
    }

    public String getDisplayName() {
        return meta.getDisplayName();
    }

    public List<String> getLore() {
        return meta.getLore();
    }

    public Set<ItemFlag> getItemFlags() {
        return meta.getItemFlags();
    }

    public int getCustomModelData() {
        return meta.getCustomModelData();
    }

    public boolean getUnbreakable() {
        return meta.isUnbreakable();
    }

    public Player getWhoClicked() {
        return whoClicked;
    }

    public ItemMeta getMeta() {
        return meta;
    }
}
