package me.improperissues.itemeffects.server;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class TickEffect {

    public static void addPotionEffects(Player player, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (meta.hasLore()) {
            lore = meta.getLore();
        }
        String[] words = lore.toString().split(" ");
        for (String word : words) {
            word = word.replaceAll("\\[","").replaceAll("]","");
            try {
                if (word.contains("+")) {
                    for (String type : Effects.getAllEffects(false)) {
                        if (word.toUpperCase().contains(type)) {
                            int amplifier = 0;
                            PotionEffectType potion = PotionEffectType.getByName(type);
                            if (word.contains(".")) {
                                String[] subword = word.split("\\.");
                                try {
                                    amplifier = Integer.parseInt(subword[1]);
                                } catch (IllegalArgumentException exception) {
                                    // empty
                                }
                            }
                            player.addPotionEffect(new PotionEffect(potion,20,amplifier));
                        }
                    }
                }
            } catch (StringIndexOutOfBoundsException | NullPointerException | IllegalArgumentException exception) {
                // empty
            }
        }
    }

    public static void tickAddEffects() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                ItemStack item = p.getInventory().getItemInMainHand();
                if (!item.getType().equals(Material.AIR)) {
                    addPotionEffects(p,item);
                }
            } catch (NullPointerException exception) {
                // empty
            }
        }
    }
}
