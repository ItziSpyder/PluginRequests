package me.improperissues.roboroan.server;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OpenInventory implements Listener {

    public static void openInventory(Player player, Player target) {
        Inventory inv = Bukkit.createInventory(player,45,"§eViewing " + target.getName() + "'s inventory");
        inv.setContents(target.getInventory().getContents());
        player.openInventory(inv);
    }

    @EventHandler
    public static void InventoryCloseEvent(InventoryCloseEvent e) {
        Inventory inv = e.getInventory();
        String title = e.getView().getTitle();

        try {
            if (title.contains("§eViewing ") && title.contains("'s inventory")) {
                if (inv.getType().equals(InventoryType.PLAYER)) return;
                Player target = Bukkit.getPlayer(title.replaceAll("§eViewing ","").replaceAll("'s inventory",""));
                if (target == null) return;
                for (int i = 0; i < 41; i ++) {
                    ItemStack item = inv.getItem(i);
                    if (item != null) target.getInventory().setItem(i,item);
                    else target.getInventory().setItem(i,new ItemStack(Material.AIR));
                }
            }
        } catch (NullPointerException exception) {}
    }
}
