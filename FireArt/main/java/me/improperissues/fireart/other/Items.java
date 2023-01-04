package me.improperissues.fireart.other;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class Items {

    public static void registerItems() {
        setPAINTBRUSH();
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

    public static ItemStack PAINTBRUSH;
    public static ItemStack AIR;
    public static ItemStack NEXT;
    public static ItemStack BACK;

    static void setPAINTBRUSH() {
        ItemStack item = new ItemStack(Material.ARROW);
        setBlank(item);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§8>> §aPaint Brush");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7/givebrush §8for a new brush!",
                "§7/paintselector §8to select paint type!",
                "§7",
                "§7RIGHT CLICK §8to paint!",
                "§7LEFT CLICK §8to undo an action!"
        )));
        item.setItemMeta(meta);
        PAINTBRUSH = item;
    }

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
