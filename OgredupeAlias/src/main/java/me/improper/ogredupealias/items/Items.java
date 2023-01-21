package me.improper.ogredupealias.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Items {

    public static void registerItems() {
        setSIEVE();
    }

    public static CustomItem SIEVE;

    static void setSIEVE() {
        CustomItem sieve = new CustomItem(new ItemStack(Material.SCAFFOLDING));
        sieve.setDisplayName(ChatColor.WHITE + "Sieve");
        sieve.setLore(Arrays.asList(ChatColor.GRAY + "- Place this down to spawn a sieve",
                ChatColor.GRAY + "- Upon placing this you will be able to right",
                ChatColor.GRAY + "  click it with sand or gravel to sieve loot",
                ChatColor.GRAY + "  out of them!"
        ));
        sieve.addAllItemFlags();
        SIEVE = sieve;
    }
}
