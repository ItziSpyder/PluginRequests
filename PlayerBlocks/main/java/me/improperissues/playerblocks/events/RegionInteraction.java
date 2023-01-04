package me.improperissues.playerblocks.events;

import me.improperissues.playerblocks.data.BlockRegion;
import me.improperissues.playerblocks.data.PlayerBlock;
import me.improperissues.playerblocks.data.RegionFile;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.File;

public class RegionInteraction implements Listener {

    @EventHandler
    public static void BlockPlaceEvent(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlock();

        for (File file : RegionFile.listFiles()) {
            BlockRegion region = RegionFile.load(file);
            if (region.isLogging() && region.isWithin(block.getLocation())) {
                region.addPlayerBlock(p,new PlayerBlock(p,block));
                RegionFile.save(region);
            }
        }
    }

    @EventHandler
    public static void BlockBreakEvent(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlock();

        for (File file : RegionFile.listFiles()) {
            BlockRegion region = RegionFile.load(file);
            if (region.isLogging() && region.isWithin(block.getLocation())) {
                PlayerBlock pb = new PlayerBlock(p,block);
                if (region.getBlockLog().contains(pb.toString())) {
                    region.removePlayerBlock(p,pb);
                    RegionFile.save(region);
                }
                else e.setCancelled(true);
            }
        }
    }
}
