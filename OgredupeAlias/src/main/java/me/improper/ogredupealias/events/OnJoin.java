package me.improper.ogredupealias.events;

import me.improper.ogredupealias.data.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnJoin implements Listener {

    @EventHandler
    public static void PlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPlayedBefore()) e.setJoinMessage(Config.MESSAGES.getJoinMessage().replaceAll("%player%",p.getName()));
        else e.setJoinMessage(Config.MESSAGES.getFirstJoinMessage().replaceAll("%player%",p.getName()));
    }

    @EventHandler
    public static void PlayerQuitEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(Config.MESSAGES.getQuitMessage().replaceAll("%player%",p.getName()));
    }
}
