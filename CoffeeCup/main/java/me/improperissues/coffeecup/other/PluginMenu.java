package me.improperissues.coffeecup.other;

import me.improperissues.coffeecup.CoffeeCup;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PluginMenu implements Listener {

    // events
    @EventHandler
    public static void ClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        String title = e.getView().getTitle();

        try {
            ItemStack item = e.getCurrentItem();
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();

            if (title.contains(Messages.starter + "cServer Plugins   §eP.") && !inv.getType().equals(InventoryType.PLAYER)) {
                e.setCancelled(true);
                int index = Integer.parseInt(title.substring((Messages.starter + "cServer Plugins   §eP.").length())) - 1;
                switch (display) {
                    case "§bTo Current Page":
                        p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE,10,10F);
                        openPluginMenu(p,getOccupiedPages() - 1);
                        break;
                    case "Previous Page":
                        if (index > 0) {
                            openPluginMenu(p,index - 1);
                            p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN,10,1.5F);
                        }
                        break;
                    case "Next Page":
                        openPluginMenu(p,index + 1);
                        p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN,10,1.5F);
                        break;

                }
            }
        } catch (NullPointerException exception) {
            // empty
        }
    }

    // methods
    public static void openPluginMenu(Player player, int index) {
        Inventory menu = Bukkit.createInventory(player,54,Messages.starter + "cServer Plugins   §eP." + (index + 1));
        ItemStack x = ItemManager.pane_red;
        ItemStack y = ItemManager.pane_black;
        ItemStack z = ItemManager.pane_brown;
        ItemStack a = ItemManager.air;
        ItemStack next = ItemManager.next;
        ItemStack previous = ItemManager.previous;
        ItemStack compass = ItemManager.compass;
        ItemStack info = new ItemStack(Material.KNOWLEDGE_BOOK);
        ItemMeta infoM = info.getItemMeta();
        infoM.setDisplayName("§aTotal §e" + CoffeeCup.getPluginList().size() + " §aPlugins");
        infoM.setLore(new ArrayList<>(Arrays.asList(
                "§7Enabled: §a" + CoffeeCup.getEnabledPlugins().size(),
                "§7Disabled: §c" + (CoffeeCup.getPluginList().size() - CoffeeCup.getEnabledPlugins().size()),
                "§e" + getOccupiedPages() + " §aoccupied pages"
        )));
        info.setItemMeta(infoM);

        ItemStack[] prefill = {
                y,y,y,y,y,y,y,y,y,
                y,a,a,a,a,a,a,a,y,
                y,a,a,a,a,a,a,a,y,
                y,a,a,a,a,a,a,a,y,
                y,y,y,y,y,y,y,y,y,
                previous,x,x,x,info,x,compass,x,next
        };
        menu.setContents(prefill);

        for (int i = (index * 21); i < (index * 21) + 21; i ++) {
            try {
                Plugin plugin = CoffeeCup.getPlugin(CoffeeCup.getPluginList().get(i));
                ItemStack item = new ItemStack(Material.OAK_SIGN);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§a" + plugin.getName());
                if (!plugin.isEnabled()) {
                    item.setType(Material.DARK_OAK_SIGN);
                    meta.setDisplayName("§c" + plugin.getName());
                }
                PluginDescriptionFile des = plugin.getDescription();
                assert des.getDescription() != null;
                List<String> lore = Messages.autoLoreSplit(des.getDescription(),5, ChatColor.of(new Color(133, 95, 21)) + "§o");
                lore.add("§8Authors: §7");
                lore.addAll(Messages.autoLoreSplit(des.getAuthors().toString(),2,"§7"));
                lore.add("§8Version: §7" + des.getVersion());
                lore.add("§8API V: §7" + des.getAPIVersion());
                lore.add("§8Site: §7" + des.getWebsite());
                lore.add("§8Contributors: §7");
                lore.addAll(Messages.autoLoreSplit(des.getContributors().toString(),2,"§7"));
                meta.setLore(lore);
                item.setItemMeta(meta);
                menu.setItem(menu.firstEmpty(),item);
            } catch (IndexOutOfBoundsException | IllegalArgumentException | NullPointerException exception) {
                // empty
            }
        }

        while (menu.firstEmpty() != -1) {
            menu.setItem(menu.firstEmpty(),z);
        }
        player.openInventory(menu);
    }

    public static int getOccupiedPages() {
        return (int) Math.ceil(CoffeeCup.getPluginList().size() / 21.0);
    }
}
