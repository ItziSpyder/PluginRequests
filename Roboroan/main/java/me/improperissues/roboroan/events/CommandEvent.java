package me.improperissues.roboroan.events;

import me.improperissues.roboroan.other.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {

    @EventHandler
    public static void PlayerCommandEvent(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();
        String cmd = msg.split(" ")[0].replaceFirst("/","");

        if (ChatEvent.isMuted(p)) {
            switch (cmd) {
                case "me":
                case "minecraft:me":
                case "say":
                case "minecraft:say":
                    e.setCancelled(true);
                    p.sendMessage(Messages.STARTER + "4You have been muted!");
                    break;
            }
        }
    }
}
