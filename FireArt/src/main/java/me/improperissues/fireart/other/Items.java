package me.improperissues.fireart.other;

import net.md_5.bungee.api.ChatColor;
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
        setSELECTOR();
        setINSPECTOR();
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
    public static ItemStack SELECTOR;
    public static ItemStack INSPECTOR;


    static void setSELECTOR() {
        ItemStack item = new ItemStack(Material.SPECTRAL_ARROW);
        setBlank(item);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + ">> " + ChatColor.GREEN + "Segment Selector");
        meta.setLore(new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "/segment settype <segment> <blocktype>",
                ChatColor.DARK_GRAY + "to set a selection to a certain block type!",
                ChatColor.GRAY + "/segment revert <segment>",
                ChatColor.DARK_GRAY + "to revert a segment back to original!",
                ChatColor.GRAY + "/segment teleport <segment>",
                ChatColor.DARK_GRAY + "to teleport to a segment!",
                ChatColor.GRAY + "/segment save <new name>",
                ChatColor.DARK_GRAY + "to save or override a segment!",
                ChatColor.GRAY + "/segment delete <segment>",
                ChatColor.DARK_GRAY + "to remove a saved segment from saved files!",
                ChatColor.GRAY + "",
                ChatColor.GRAY + "RIGHT CLICK to make a selection!",
                ChatColor.GRAY + "LEFT CLICK to paint an existing segment!"
        )));
        item.setItemMeta(meta);
        SELECTOR = item;
    }

    static void setPAINTBRUSH() {
        ItemStack item = new ItemStack(Material.ARROW);
        setBlank(item);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + ">> " + ChatColor.GREEN + "Paint Brush");
        meta.setLore(new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "/givebrush " + ChatColor.DARK_GRAY + "for a new brush!",
                ChatColor.GRAY + "/paintselector " + ChatColor.DARK_GRAY + "to select paint type!",
                "",
                ChatColor.GRAY + "RIGHT CLICK " + ChatColor.DARK_GRAY + "to paint!",
                ChatColor.GRAY + "LEFT CLICK " + ChatColor.DARK_GRAY + "to undo an action!"
        )));
        item.setItemMeta(meta);
        PAINTBRUSH = item;
    }

    static void setINSPECTOR() {
        ItemStack item = new ItemStack(Material.CLOCK);
        setBlank(item);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + ">> " + ChatColor.GREEN + "Segment Inspector");
        meta.setLore(new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "/segment list " + ChatColor.DARK_GRAY + "for a list of!",
                ChatColor.DARK_GRAY + "currently existing segments!",
                "",
                ChatColor.GRAY + "RIGHT CLICK " + ChatColor.DARK_GRAY + "see what segment",
                ChatColor.DARK_GRAY + "a raycasted block is from!",
                ChatColor.GRAY + "LEFT CLICK " + ChatColor.DARK_GRAY + "open paint menu"
                )));
        item.setItemMeta(meta);
        INSPECTOR = item;
    }

    static void setAIR() {
        Items.AIR = new ItemStack(Material.AIR);
    }

    static void setNEXT() {
        ItemStack item = new ItemStack(Material.ARROW);
        setBlank(item);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Next Page");
        item.setItemMeta(meta);
        NEXT = item;
    }

    static void setBACK() {
        ItemStack item = new ItemStack(Material.ARROW);
        setBlank(item);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Previous Page");
        item.setItemMeta(meta);
        BACK = item;
    }
}
