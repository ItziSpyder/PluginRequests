package me.improperissues.roboroan.events;

import me.improperissues.roboroan.other.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class InteractEvent implements Listener {

    private static List<String> FROZEN = new ArrayList<>();

    @EventHandler
    public static void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (isFrozen(p)) {
            e.setCancelled(true);
            p.sendTitle("§4You are frozen!",Messages.STARTER + "cYou are frozen by an operator!",0,40,0);
            return;
        }
    }

    @EventHandler
    public static void PlayerMoveEvent(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (isFrozen(p)) {
            e.setCancelled(true);
            p.sendTitle("§4You are frozen!",Messages.STARTER + "cYou are frozen by an operator!",0,40,0);
            return;
        }
    }

    @EventHandler
    public static void PlayerDropItemEvent(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        if (isFrozen(p)) {
            e.setCancelled(true);
            p.sendTitle("§4You are frozen!",Messages.STARTER + "cYou are frozen by an operator!",0,40,0);
            return;
        }
    }

    @EventHandler
    public static void InventoryClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (isFrozen(p)) {
            e.setCancelled(true);
            p.closeInventory();
            p.sendTitle("§4You are frozen!",Messages.STARTER + "cYou are frozen by an operator!",0,40,0);
            return;
        }
    }

    public static boolean isFrozen(Player player) {
        return FROZEN.contains(player.getName());
    }

    public static void setFrozen(CommandSender freezer, Player player, boolean frozen) {
        if (frozen && !isFrozen(player)) {
            FROZEN.add(player.getName());
            player.sendMessage(Messages.STARTER + "cYou are now frozen!");
            freezer.sendMessage(Messages.STARTER + "bYou have frozen " + player.getName() + "!");
        }
        else if (!frozen && isFrozen(player)) {
            FROZEN.remove(player.getName());
            player.sendMessage(Messages.STARTER + "cYou are now unfrozen!");
            freezer.sendMessage(Messages.STARTER + "bYou have unfrozen " + player.getName() + "!");
        }
    }
}
