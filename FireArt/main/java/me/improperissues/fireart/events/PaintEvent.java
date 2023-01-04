package me.improperissues.fireart.events;

import me.improperissues.fireart.data.PaintAction;
import me.improperissues.fireart.data.PaintMenuPage;
import me.improperissues.fireart.data.PaintedBlock;
import me.improperissues.fireart.map.PaintSelection;
import me.improperissues.fireart.other.Items;
import me.improperissues.fireart.other.PaintRaycast;
import me.improperissues.fireart.other.ServerSound;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PaintEvent implements Listener {

    @EventHandler
    public static void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        try {
            ItemStack item = p.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();

            if (display.contains(Items.getDisplay(Items.PAINTBRUSH))) {
                e.setCancelled(true);
                ServerSound sound = new ServerSound(p.getLocation(),Sound.ITEM_BUCKET_EMPTY,1,10);
                ServerSound erase = new ServerSound(p.getLocation(),Sound.ITEM_DYE_USE,1,1);
                switch (e.getAction()) {
                    case LEFT_CLICK_AIR:
                        PaintAction.restoreLastAction(p);
                        erase.play(p);
                        break;
                    case RIGHT_CLICK_AIR:
                        PaintedBlock block = PaintRaycast.getRaycasted(p);
                        PaintAction action = new PaintAction();
                        block.spread(block,action,block.toLocation(),block.getType(),PaintSelection.getSelection(p),500);
                        sound.play(p);
                        PaintAction.addPlayerAction(p,action);
                        break;
                }
            }
        } catch (Exception | StackOverflowError exception) {}
    }

    @EventHandler
    public static void PlayerDropItemEvent(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        try {
            ItemStack item = e.getItemDrop().getItemStack();
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();

            if (display.contains(Items.getDisplay(Items.PAINTBRUSH))) {
                e.setCancelled(true);
                ServerSound sound = new ServerSound(p.getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP,10,10);
                PaintMenuPage.createInventory(p,0);
                sound.play(p);
            }
        } catch (Exception exception) {}
    }
}
