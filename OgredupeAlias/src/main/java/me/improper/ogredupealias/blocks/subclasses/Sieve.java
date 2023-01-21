package me.improper.ogredupealias.blocks.subclasses;

import me.improper.ogredupealias.OgredupeAlias;
import me.improper.ogredupealias.blocks.CustomBlock;
import me.improper.ogredupealias.events.OnClick;
import me.improper.ogredupealias.loottables.LootTables;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Sieve extends CustomBlock {

    private static HashMap<String,Long> COOLDOWN = new HashMap<>();

    public Sieve(Block block) {
        super(block);
    }

    public Sieve(Location location) {
        super(location);
    }

    @Override
    public void onRightClick() {
        Player p = getWhoClicked();
        if (COOLDOWN.containsKey(p.getName()) && COOLDOWN.get(p.getName()) > System.currentTimeMillis()) return;
        else COOLDOWN.put(p.getName(),System.currentTimeMillis() + (500));

        ItemStack item = OnClick.getClickedItem(p);
        Location loc = toLocation().clone().add(0.5,-0.3,0.5);
        ArmorStand sand = loc.getWorld().spawn(loc,ArmorStand.class);
        sand.setInvisible(true);
        sand.setMarker(true);
        sand.setHelmet(item);
        sand.setCustomName("entity.ogredupealias.sieve");
        sand.addScoreboardTag(ChatColor.GRAY + sand.getCustomName());

        new BukkitRunnable() {
            int x = 0;
            @Override
            public void run() {
                if (x < 10) {
                    sand.teleport(sand.getLocation().add(0,-0.1,0));
                    x ++;
                }
                else {
                    Location drop = loc.clone().add(0,1.5,0);
                    switch (item.getType()) {
                        case GRAVEL -> LootTables.SIEVE_GRAVEL.spawn(drop);
                        case SAND -> LootTables.SIEVE_SAND.spawn(drop);
                        case DIRT -> LootTables.SIEVE_DIRT.spawn(drop);
                    }
                    sand.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(OgredupeAlias.getInstance(),0,1);
        if (!p.getGameMode().equals(GameMode.CREATIVE)) item.setAmount(item.getAmount() - 1);
    }
}
