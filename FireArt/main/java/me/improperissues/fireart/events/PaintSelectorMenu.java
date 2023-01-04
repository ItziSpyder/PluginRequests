package me.improperissues.fireart.events;

import me.improperissues.fireart.FireArt;
import me.improperissues.fireart.data.PaintMenuPage;
import me.improperissues.fireart.map.PaintSelection;
import me.improperissues.fireart.other.Items;
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

            if (title.contains("§8>> §aPaint Selection: §e")) {
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
                p.sendMessage(FireArt.STARTER + "aSet your paint type to §7" + PaintSelection.getSelection(p).name() + "§a!");
            }
        } catch (Exception exception) {}
    }

    static int getMenuIndex(String title) {
        return Integer.parseInt(title.substring(("§8>> §aPaint Selection: §e").length())) - 1;
    }
}
