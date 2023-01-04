package me.improperissues.coffeecup.events;

import me.improperissues.coffeecup.files.SpawnControl;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class SpawnEvent implements Listener {


    // events
    @EventHandler
    public static void EntitySpawnEvent(EntitySpawnEvent e) {
        Entity entity = e.getEntity();

        if (!SpawnControl.canSpawn(entity)) {
            e.setCancelled(true);
        }
    }

    // methods
    public static boolean isCommon(Entity entity) {
        return entity instanceof Item ||
                entity instanceof Arrow ||
                entity instanceof ExperienceOrb ||
                entity instanceof SpectralArrow ||
                entity instanceof Trident ||
                entity instanceof FallingBlock ||
                entity instanceof Fireball ||
                entity instanceof Snowball ||
                entity instanceof AreaEffectCloud;
    }

    public static boolean isLiving(Entity entity) {
        return entity instanceof LivingEntity;
    }

    public static boolean isNonLiving(Entity entity) {
        return !(entity instanceof LivingEntity);
    }

    public static boolean isMonster(Entity entity) {
        return entity instanceof Monster ||
                entity instanceof Flying;
    }

    public static boolean isPassive(Entity entity) {
        return !(entity instanceof Monster) &&
                !(entity instanceof Flying) &&
                entity instanceof LivingEntity;
    }

    public static boolean isNamed(Entity entity) {
        return entity.isCustomNameVisible();
    }






}
