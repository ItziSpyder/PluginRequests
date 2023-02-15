package me.improper.ogredupealias.events;

import me.improper.ogredupealias.OgredupeAlias;
import me.improper.ogredupealias.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import java.util.Arrays;
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
            else if (victim instanceof Player pVictim && damager instanceof Player pDamager) {
                if (Config.BOOLEANS.allowNakedKill()) return;
                if (isNewbie(pVictim) && !isNewbie(pDamager)) {
                    e.setCancelled(true);
                    pDamager.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "This player is under-geared!");
                }
                else if (!isNewbie(pVictim) && isNewbie(pDamager)) {
                    e.setCancelled(true);
                    pDamager.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "You are under-geared!");
                }
            }
        } catch (Exception exception) {}
    }

    private static boolean isNewbie(Player player) {
        String contents = Arrays.asList(player.getInventory().getArmorContents()).toString();
        return !contents.contains("NETHERITE") && !contents.contains("DIAMOND");
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        Entity projectile = e.getEntity();
        Entity victim = e.getHitEntity();

        if (victim == null) return;

        victim.setVelocity(victim.getVelocity().add(new Vector(0,4,0)));
    }
}
