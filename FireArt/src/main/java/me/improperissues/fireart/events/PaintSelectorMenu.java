package me.improperissues.fireart.events;

import me.improperissues.fireart.FireArt;
import me.improperissues.fireart.data.PaintMenuPage;
import me.improperissues.fireart.map.PaintSelection;
import me.improperissues.fireart.other.Items;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PaintSelectorMenu implements Listener {

    @EventHandler
    public static void InventoryClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        String title = e.getView().getTitle();

        try {
            ItemStack item = e.getCurrentItem();
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();

            if (title.contains(ChatColor.DARK_GRAY + ">> " + ChatColor.GREEN + "Paint Selection: " + ChatColor.YELLOW)) {
                if (inv.getType().equals(InventoryType.PLAYER)) return;
                e.setCancelled(true);
                int index = getMenuIndex(title);
                if (display.equals(" ")) return;
                else if (display.equals(Items.getDisplay(Items.NEXT))) {
                    if (index < 0) index = 0;
                    PaintMenuPage.createInventory(p,index + 1);
                    return;
                }
                else if (display.equals(Items.getDisplay(Items.BACK))) {
                    if (index <= 0) index = 1;
                    PaintMenuPage.createInventory(p,index - 1);
                    return;
                }
                PaintSelection.setSelection(p,item.getType());
                p.closeInventory();
                p.sendMessage(FireArt.STARTER + ChatColor.GREEN + "Set your paint type to " + ChatColor.GRAY + PaintSelection.getSelection(p).name() + ChatColor.GREEN + "!");
            }
        } catch (Exception exception) {}
    }

    static int getMenuIndex(String title) {
        return Integer.parseInt(title.substring((ChatColor.DARK_GRAY + ">> " + ChatColor.GREEN + "Paint Selection: " + ChatColor.YELLOW).length())) - 1;
    }
}
