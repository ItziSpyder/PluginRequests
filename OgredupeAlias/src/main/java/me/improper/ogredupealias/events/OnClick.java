package me.improper.ogredupealias.events;

import me.improper.ogredupealias.blocks.subclasses.Sieve;
import me.improper.ogredupealias.data.Config;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnClick implements Listener {

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        try {
            ItemStack itemStack = getClickedItem(p);
            Action action = e.getAction();

            if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                Block block = e.getClickedBlock();
                switch (block.getType()) {
                    case SCAFFOLDING -> {
                        if (p.isSneaking() || !Config.BOOLEANS.allowSieving()) return;
                        switch (itemStack.getType()) {
                            case SAND,GRAVEL,DIRT -> {
                                e.setCancelled(true);
                                Sieve sieve = new Sieve(block);
                                sieve.setWhoClicked(p);
                                sieve.onRightClick();
                            }
                        }
                    }
                }
            }
        } catch (Exception exception) {}
    }

    public static ItemStack getClickedItem(Player p) {
        ItemStack main = p.getInventory().getItemInMainHand();
        ItemStack off = p.getInventory().getItemInOffHand();
        if (main.getType().isAir() && !off.getType().isAir()) return off;
        else return main;
    }
}
