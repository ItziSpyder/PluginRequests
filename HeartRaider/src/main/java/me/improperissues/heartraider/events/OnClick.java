package me.improperissues.heartraider.events;

import me.improperissues.heartraider.server.itemfunctions.FireballWand;
import me.improperissues.heartraider.server.itemfunctions.WitherBlade;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class OnClick implements Listener {

    @EventHandler
    public static void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        try {
            ItemStack item = getClickedItem(p);
            List<String> lore = getLore(item);

            switch (e.getAction()) {
                case RIGHT_CLICK_AIR:
                case RIGHT_CLICK_BLOCK:
                    if (lore.contains(ChatColor.DARK_GRAY + "heartraider:fireballwand")) {
                        e.setCancelled(true);
                        FireballWand.function(p);
                    }
                    if (lore.contains(ChatColor.DARK_GRAY + "heartraider:witherblade")) {
                        e.setCancelled(true);
                        WitherBlade.function(p);
                    }
                    break;
            }
        } catch (Exception exception) {}
    }


    private static String getDisplay(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null || meta.getDisplayName().trim().equals("")) return item.getType().name();
        return meta.getDisplayName();
    }

    public static List<String> getLore(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null || meta.getLore() == null) return new ArrayList<>();
        return meta.getLore();
    }

    private static ItemStack getClickedItem(Player player) {
        ItemStack off = player.getInventory().getItemInOffHand();
        ItemStack main = player.getInventory().getItemInMainHand();
        if (!main.getType().isAir()) return main;
        else if (!off.getType().isAir()) return off;
        else return main;
    }
}
