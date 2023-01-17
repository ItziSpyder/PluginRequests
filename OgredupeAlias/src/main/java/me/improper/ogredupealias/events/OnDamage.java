package me.improper.ogredupealias.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.UUID;

public class OnDamage implements Listener {

    private static HashMap<UUID,UUID> DAMAGELOG = new HashMap<>();

    @EventHandler
    public static void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        try {
            Entity victim = e.getEntity();
            Entity damager = e.getDamager();
            double damage = e.getDamage();

            if (victim instanceof LivingEntity && damager instanceof EnderCrystal) {
                Entity root = Bukkit.getEntity(DAMAGELOG.get(damager.getUniqueId()));
                if (victim.getUniqueId().equals(root.getUniqueId())) return;
                e.setCancelled(true);
                ((LivingEntity) victim).damage(damage,root);
            }
            else if (victim instanceof EnderCrystal && damager instanceof Player) {
                DAMAGELOG.put(victim.getUniqueId(),damager.getUniqueId());
            }
        } catch (Exception exception) {}
    }
}
