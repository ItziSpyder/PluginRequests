package me.improperissues.itemeffects.server;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

    public static ItemStack setDescription(ItemStack item, String string) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(StringManager.addListEffects(StringManager.addStringEffects(string)));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack setDisplayName(ItemStack item, String string) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(StringManager.addStringEffects(string));
        item.setItemMeta(meta);
        return item;
    }
}
