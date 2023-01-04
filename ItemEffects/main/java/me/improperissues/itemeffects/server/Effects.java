package me.improperissues.itemeffects.server;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Effects {

    public static List<String> getAllEffects(boolean lowercase) {
        List<String> effects = new ArrayList<>();
        for (PotionEffectType type : PotionEffectType.values()) {
            String name = type.getName();
            if (lowercase) {
                name = name.toLowerCase();
            }
            effects.add(name);
        }
        return effects;
    }

}
