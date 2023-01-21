package me.improper.ogredupealias.events;

import me.improper.ogredupealias.blocks.CustomBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlock implements Listener {

    @EventHandler
    public static void BlockPlaceEvent(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        try {
            CustomBlock block = new CustomBlock(e.getBlock());
        } catch (Exception exception) {}
    }
}
