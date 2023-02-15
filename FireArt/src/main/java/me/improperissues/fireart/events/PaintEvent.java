package me.improperissues.fireart.events;

import me.improperissues.fireart.FireArt;
import me.improperissues.fireart.commands.Tabs;
import me.improperissues.fireart.data.*;
import me.improperissues.fireart.map.PaintSelection;
import me.improperissues.fireart.map.SegmentSelection;
import me.improperissues.fireart.other.Items;
import me.improperissues.fireart.other.PaintRaycast;
import me.improperissues.fireart.other.ServerSound;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class PaintEvent implements Listener {

    private static HashMap<String,Long> cooldown = new HashMap<>();

    @EventHandler
    public static void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        try {
            ItemStack item = p.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();

            if (display.contains(ChatColor.DARK_GRAY + ">> ") && cooldown.containsKey(p.getName()) && cooldown.get(p.getName()) > System.currentTimeMillis()) return;
            cooldown.put(p.getName(),System.currentTimeMillis() + 50);

            if (display.contains(Items.getDisplay(Items.PAINTBRUSH))) {
                e.setCancelled(true);
                ServerSound sound = new ServerSound(p.getLocation(),Sound.ITEM_BUCKET_EMPTY,1,10);
                ServerSound erase = new ServerSound(p.getLocation(),Sound.ITEM_DYE_USE,1,1);
                switch (e.getAction()) {
                    case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> {
                        PaintAction.restoreLastAction(p);
                        erase.play(p);
                    }
                    case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                        PaintedBlock block = PaintRaycast.getRaycasted(p);
                        PaintAction action = new PaintAction();
                        PaintedBlock.spread(block, action, block.toLocation(), block.getType(), PaintSelection.getSelection(p), 500);
                        sound.play(p);
                        PaintAction.addPlayerAction(p, action);
                    }
                }
            } else if (display.contains(Items.getDisplay(Items.SELECTOR))) {
                e.setCancelled(true);
                ServerSound sound = new ServerSound(p.getLocation(),Sound.ITEM_BUCKET_EMPTY,1,10);
                ServerSound erase = new ServerSound(p.getLocation(),Sound.ITEM_DYE_USE,1,1);
                ServerSound button = new ServerSound(p.getLocation(),Sound.UI_BUTTON_CLICK,1,1);
                switch (e.getAction()) {
                    case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> {
                        SelectedBlock block = PaintRaycast.getSelectedBlock(p);
                        String id = block.toLocationId();
                        if (p.isSneaking() && Tabs.materialList.contains(block.getType().name().toLowerCase().trim())) {
                            PaintSelection.setSelection(p,block.getType());
                            p.sendMessage(FireArt.STARTER + ChatColor.GREEN + "Set your paint type to " + ChatColor.GRAY + PaintSelection.getSelection(p).name() + ChatColor.GREEN + "!");
                            button.play(p);
                            return;
                        }
                        for (String segmentName : SegmentFile.listFiles()) {
                            Segment segment = SegmentFile.load(segmentName);
                            if (segment.getLocationIdLog().contains(id)) {
                                segment.fillWith(PaintSelection.getSelection(p));
                                break;
                            }
                        }
                        erase.play(p);
                    }
                    case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                        if (!p.hasPermission("fireart.segment")) return;
                        SelectedBlock block = PaintRaycast.getSelectedBlock(p);
                        Segment segment = new Segment("spread");
                        segment = block.spreadSelect(segment,block,block.toLocation(),block.getOgType());
                        SegmentSelection.addSegment(p,segment);
                        sound.play(p);
                        p.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Selected " + segment.getBlocks().size() + " blocks as your selected segment!");
                    }
                }
            } else if (display.contains(Items.getDisplay(Items.INSPECTOR))) {
                e.setCancelled(true);
                ServerSound sound = new ServerSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE,1,10);
                ServerSound erase = new ServerSound(p.getLocation(),Sound.ITEM_DYE_USE,1,1);
                switch (e.getAction()) {
                    case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> {
                        PaintMenuPage.createInventory(p,0);
                        erase.play(p);
                    }
                    case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                        SelectedBlock block = PaintRaycast.getSelectedBlock(p);
                        String id = block.toLocationId();
                        sound.play(p);
                        for (String segmentName : SegmentFile.listFiles()) {
                            Segment segment = SegmentFile.load(segmentName);
                            if (segment.getLocationIdLog().contains(id)) {
                                p.sendMessage(FireArt.STARTER + ChatColor.GRAY + block.getType() +
                                        ChatColor.LIGHT_PURPLE + " is part of segment " + ChatColor.GRAY + segmentName);
                                return;
                            }
                        }
                        p.sendMessage(FireArt.STARTER + ChatColor.RED + "The selected block is not part of any segment!");
                    }
                }
            }
        } catch (Exception | StackOverflowError exception) {}
    }

    @EventHandler
    public static void PlayerDropItemEvent(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        try {
            ItemStack item = e.getItemDrop().getItemStack();
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();

            if (display.contains(Items.getDisplay(Items.PAINTBRUSH))) {
                e.setCancelled(true);
                ServerSound sound = new ServerSound(p.getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP,10,10);
                PaintMenuPage.createInventory(p,0);
                sound.play(p);
            }
        } catch (Exception exception) {}
    }
}
