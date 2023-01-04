package me.improperissues.coffeecup.other;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

    public static void registerItems() {
        setPane_red();
        setPane_black();
        setPane_brown();
        setPrevious();
        setNext();
        setCompass();
    }

    public static ItemStack pane_red;
    public static ItemStack pane_brown;
    public static ItemStack pane_black;
    public static ItemStack next;
    public static ItemStack previous;
    public static ItemStack air = new ItemStack(Material.AIR);
    public static ItemStack compass;

    public static void setPane_red() {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(" ");

        item.setItemMeta(meta);
        pane_red = item;
    }
    public static void setPane_black() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(" ");

        item.setItemMeta(meta);
        pane_black = item;
    }
    public static void setPane_brown() {
        ItemStack item = new ItemStack(Material.BROWN_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(" ");

        item.setItemMeta(meta);
        pane_brown = item;
    }
    public static void setNext() {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("Next Page");

        item.setItemMeta(meta);
        next = item;
    }
    public static void setPrevious() {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("Previous Page");

        item.setItemMeta(meta);
        previous = item;
    }
    public static void setCompass() {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§bTo Current Page");

        item.setItemMeta(meta);
        compass = item;
    }
}
