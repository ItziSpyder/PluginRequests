package me.improperissues.fireart.events;

import me.improperissues.fireart.other.Config;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;

public class PlayerEvent implements Listener {

    @EventHandler
    public static void PlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!p.isOp() && Config.getAutoToggle() && Config.getCanFly()) {
            p.setAllowFlight(true);
            p.setVelocity(new Vector(0,1,0));
            p.setFlying(true);
        } else if (p.isOp() && Config.getAutoToggle()) {
            p.setAllowFlight(true);
            p.setVelocity(new Vector(0,1,0));
            p.setFlying(true);
        }
    }

    @EventHandler
    public static void EntityInteractAtEntityEvent(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        Entity entity = e.getRightClicked();

        try {
            if (entity instanceof Player && !p.getPassengers().contains(entity)) entity.addPassenger(p);
        } catch (NullPointerException | IllegalArgumentException | ClassCastException exception) {}
    }
}
