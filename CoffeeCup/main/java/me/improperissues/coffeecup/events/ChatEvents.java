package me.improperissues.coffeecup.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatEvents implements Listener {

    @EventHandler
    public static void PlayerCommandEvent(PlayerCommandPreprocessEvent e) {
        String msg = e.getMessage();
        switch (msg) {
            case "/rl":
            case "/reload":
                e.setMessage("/reload confirm");
                break;
            case "pl":
            case "plugin":
            case "plugins":
                e.setMessage("/coffeecup:plugins");
                break;
        }
    }
}
