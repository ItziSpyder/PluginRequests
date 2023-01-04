package me.improperissues.itemeffects.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

import static me.improperissues.itemeffects.events.PluginConfig.*;
import static me.improperissues.itemeffects.server.StringManager.*;
import static org.bukkit.Bukkit.getServer;

public class JoinLeave implements Listener {

    @EventHandler
    public static void PlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (!p.hasPlayedBefore()) {
            if (isJoinMessageEnabled()) e.setJoinMessage(addStringEffects(getFirstJoinMessage()).replaceAll("%player.name%",p.getPlayerListName()));
        } else {
            if (isJoinMessageEnabled()) e.setJoinMessage(addStringEffects(getJoinMessage()).replaceAll("%player.name%",p.getPlayerListName()));
        }
        getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (isAdditionalMessageEnabled()) {
                    for (String message : getAdditionalMessage()) {
                        p.spigot().sendMessage(processLinks(addStringEffects(message.replaceAll("%player.name%",p.getPlayerListName()))));
                    }
                }
            }
        },1);
    }

    @EventHandler
    public static void PlayerLeaveEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (isLeaveMessageEnabled()) e.setQuitMessage(addStringEffects(getLeaveMessage()).replaceAll("%player.name%",p.getPlayerListName()));
    }

    @EventHandler
    public static void BlockPlaceEvent(BlockPlaceEvent e) {
        Block block = e.getBlock();
        Location loc = block.getLocation();

        if (
                block.getType().equals(Material.JUNGLE_PLANKS)
                        && loc.clone().add(0,-1,0).getBlock().getType().equals(Material.DIRT)
                        && loc.clone().add(0,-2,0).getBlock().getType().equals(Material.DIRT)
                        && loc.clone().add(0,-3,0).getBlock().isPassable()
        ) {
            if (loc.clone().add(1,-3,0).getBlock().getType().equals(Material.DIRT) && loc.clone().add(-1,-3,0).getBlock().getType().equals(Material.DIRT)) {
                block.setType(Material.AIR);
                loc.clone().add(0,-1,0).getBlock().setType(Material.AIR);
                loc.clone().add(0,-2,0).getBlock().setType(Material.AIR);
                loc.clone().add(1,-3,0).getBlock().setType(Material.AIR);
                loc.clone().add(-1,-3,0).getBlock().setType(Material.AIR);
                spawnStackedPigs(loc);
                return;
            } else if (loc.clone().add(0,-3,1).getBlock().getType().equals(Material.DIRT) && loc.clone().add(0,-3,-1).getBlock().getType().equals(Material.DIRT)) {
                block.setType(Material.AIR);
                loc.clone().add(0,-1,0).getBlock().setType(Material.AIR);
                loc.clone().add(0,-2,0).getBlock().setType(Material.AIR);
                loc.clone().add(0,-3,1).getBlock().setType(Material.AIR);
                loc.clone().add(0,-3,-1).getBlock().setType(Material.AIR);
                spawnStackedPigs(loc);
                return;
            }
        }
    }

    public static void spawnStackedPigs(Location location) {
        Pig pig = location.getWorld().spawn(location.clone().add(0.5,-3,0.5),Pig.class);
        pig.setCustomName("§c§l§o§nPP PIG!!!!");
        pig.setCustomNameVisible(true);
        pig.setGlowing(true);
        Pig pig1 = location.getWorld().spawn(location.clone().add(0.5,-3,0.5),Pig.class);
        pig1.setCustomName("§c§l§o§nPP PIG!!!!");
        pig1.setCustomNameVisible(true);
        pig1.setGlowing(true);
        Pig pig2 = location.getWorld().spawn(location.clone().add(0.5,-3,0.5),Pig.class);
        pig2.setCustomName("§c§l§o§nPP PIG!!!!");
        pig2.setCustomNameVisible(true);
        pig2.setGlowing(true);
        pig.addPassenger(pig1);
        pig1.addPassenger(pig2);
    }
}
