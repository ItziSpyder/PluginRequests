package me.improperissues.roboroan.events;

import me.improperissues.roboroan.other.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatEvent implements Listener {

    private static List<String> MUTED = new ArrayList<>();

    @EventHandler
    public static void PlayerChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();

        if (isMuted(p)) {
            e.setCancelled(true);
            p.sendMessage(Messages.STARTER + "4You have been muted!");
            return;
        }
        e.setMessage(": " + Messages.impPings(Messages.impColors(message)));
    }

    public static boolean isMuted(Player player) {
        return MUTED.contains(player.getName());
    }

    public static void setMuted(CommandSender muter, Player player, boolean muted) {
        if (muted && !isMuted(player)) {
            MUTED.add(player.getName());
            player.sendMessage(Messages.STARTER + "cYou are now muted!");
            muter.sendMessage(Messages.STARTER + "bYou have muted " + player.getName() + "!");
        }
        else if (!muted && isMuted(player)) {
            MUTED.remove(player.getName());
            player.sendMessage(Messages.STARTER + "cYou are now unmuted!");
            muter.sendMessage(Messages.STARTER + "bYou have unmuted " + player.getName() + "!");
        }
    }
}
