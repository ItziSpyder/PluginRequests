package me.improper.quicklifesteal.events;

import me.improper.quicklifesteal.QuickLifesteal;
import me.improper.quicklifesteal.data.Config;
import me.improper.quicklifesteal.entity.player.HeartsData;
import me.improper.quicklifesteal.entity.player.HeartsLoader;
import me.improper.quicklifesteal.server.Items;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnClick implements Listener {

    @EventHandler
    public static void onClick(PlayerInteractEvent e) {
        try {
            Player p = e.getPlayer();
            ItemStack item = e.getItem();
            switch (e.getAction()) {
                case RIGHT_CLICK_AIR,RIGHT_CLICK_BLOCK -> {
                    if (getDisplay(item).equals(getDisplay(Items.HEART))) {
                        e.setCancelled(true);
                        HeartsData data = HeartsLoader.load(p);
                        if (data.getMaxHearts() + 1 > Config.Gameplay.max_hearts) {
                            p.sendMessage(QuickLifesteal.starter + "§cYou can't exceed the heart cap!");
                            return;
                        }
                        item.setAmount(item.getAmount() - 1);
                        data.setMaxHearts(data.getMaxHearts() + 1);
                        data.update();
                        data.save();
                        p.sendMessage(QuickLifesteal.starter + "§bYou've used §7" + 1 + " §bhearts!");
                    }
                }
            }
        } catch (Exception ex) {}
    }

    static String getDisplay(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return "";
        return meta.getDisplayName();
    }
}
