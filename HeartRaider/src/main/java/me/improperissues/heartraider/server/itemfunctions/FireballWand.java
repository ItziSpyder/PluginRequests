package me.improperissues.heartraider.server.itemfunctions;

import me.improperissues.heartraider.server.Items;
import me.improperissues.heartraider.server.ServerSound;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;


public class FireballWand extends Items {

    private static HashMap<String,Long> cooldown = new HashMap<>();

    public static void function(Player player) {
        if (cooldown.containsKey(player.getName()) && cooldown.get(player.getName()) > System.currentTimeMillis()) return;
        cooldown.put(player.getName(), System.currentTimeMillis() + (2 * 1000));

        Location loc = player.getEyeLocation();
        Vector dir = player.getLocation().getDirection();
        ServerSound sound = new ServerSound(loc, Sound.ENTITY_BLAZE_SHOOT,1,0.1F);

        Fireball fb = player.getWorld().spawn(loc,Fireball.class);
        fb.setShooter(player);
        fb.setYield(0);
        fb.setBounce(false);
        fb.setDirection(dir);
        fb.setVelocity(dir);

        sound.playWithin(1000);
    }
}
