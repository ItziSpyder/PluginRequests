package me.improper.quicklifesteal.server;

import me.improper.quicklifesteal.QuickLifesteal;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Items {

    /**
     * Registers all the custom items
     */
    public static void registerAll() {
        setHEART();
    }

    public static ItemStack HEART;

    static void setHEART() {
        ItemStack item = new ItemStack(Material.RED_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(QuickLifesteal.starter + "§cHeart ❤");
        meta.setLore(List.of("§7RIGHT-CLICK to gain a heart"));
        item.setItemMeta(meta);
        HEART = item;

        NamespacedKey key = new NamespacedKey(QuickLifesteal.getInstance(),"heart_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                "nsn",
                "sbs",
                "nsn"
        );
        recipe.setIngredient('n',Material.NETHERITE_INGOT);
        recipe.setIngredient('s',Material.NETHER_STAR);
        recipe.setIngredient('b',Material.BEACON);
        try {
            Bukkit.addRecipe(recipe);
            Bukkit.getOnlinePlayers().forEach(p -> p.discoverRecipe(key));
        } catch (Exception ex) {}
    }
}
