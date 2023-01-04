package me.improperissues.roboroan.other;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

    public static void registerItems() {
        setAIR();
        setNEXT();
        setBACK();
    }

    public static ItemStack setBlank(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        for (ItemFlag flag : ItemFlag.values()) meta.addItemFlags(flag);
        item.setItemMeta(meta);
        return item;
    }

    public static String getDisplay(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null || meta.getDisplayName().trim().equals("")) return item.getType().name().toLowerCase();
        return meta.getDisplayName();
    }

    public static ItemStack AIR;
    public static ItemStack NEXT;
    public static ItemStack BACK;

    static void setAIR() {
        Items.AIR = new ItemStack(Material.AIR);
    }

    static void setNEXT() {
        ItemStack item = new ItemStack(Material.ARROW);
        setBlank(item);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fNext Page");
        item.setItemMeta(meta);
        NEXT = item;
    }

    static void setBACK() {
        ItemStack item = new ItemStack(Material.ARROW);
        setBlank(item);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fPrevious Page");
        item.setItemMeta(meta);
        BACK = item;
    }
}
