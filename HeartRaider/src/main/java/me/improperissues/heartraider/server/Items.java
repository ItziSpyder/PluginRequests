package me.improperissues.heartraider.server;

import me.improperissues.heartraider.HeartRaider;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Items {

    public static void registerAllItems() {
        try {
            setFIREBALLWAND();
            setWITHERBLADE();
        } catch (Exception exception) {}
    }

    public static List<ItemStack> values() {
        return new ArrayList<>(Arrays.asList(
                FIREBALLWAND,
                WITHERBLADE
        ));
    }

    public static ItemStack valueOf(String name) {
        switch (name.toLowerCase()) {
            case "fireballwand": return FIREBALLWAND;
            case "witherblade": return WITHERBLADE;
        }
        return null;
    }

    public static ItemStack setBlank(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        for (ItemFlag flag : ItemFlag.values()) meta.addItemFlags(flag);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack FIREBALLWAND;
    public static ItemStack WITHERBLADE;

    static void setFIREBALLWAND() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Fireball Wand");
        meta.setLore(new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "- RIGHT CLICK to shoot a fireball",
                ChatColor.GRAY + "- LEFT CLICK acts like a melee weapon",
                "",
                ChatColor.DARK_GRAY + "heartraider:fireballwand"
        )));
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(),"generic.attack_speed",-2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier damage = new AttributeModifier(UUID.randomUUID(),"generic.attack_damage",7, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,damage);
        meta.addEnchant(Enchantment.FIRE_ASPECT,3,true);
        item.setItemMeta(meta);
        FIREBALLWAND = item;

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(HeartRaider.getInstance(),"fireballwand"),FIREBALLWAND);
        recipe.shape(
                "fnf",
                "bib",
                " s "
        );
        recipe.setIngredient('f',Material.FIRE_CHARGE);
        recipe.setIngredient('n',Material.NETHER_STAR);
        recipe.setIngredient('i',Material.NETHERITE_INGOT);
        recipe.setIngredient('s',Material.STICK);
        recipe.setIngredient('b',Material.BEACON);
        Bukkit.addRecipe(recipe);
    }

    static void setWITHERBLADE() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Wither Blade");
        meta.setLore(new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "- RIGHT CLICK to shoot a wither skull",
                ChatColor.GRAY + "- LEFT CLICK acts like a melee weapon",
                "",
                ChatColor.DARK_GRAY + "heartraider:witherblade"
        )));
        item.setItemMeta(meta);
        WITHERBLADE = item;

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(HeartRaider.getInstance(),"witherblade"),WITHERBLADE);
        recipe.shape(
                "fnf",
                "bib",
                " s "
        );
        recipe.setIngredient('f',Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('n',Material.NETHER_STAR);
        recipe.setIngredient('i',Material.NETHERITE_SWORD);
        recipe.setIngredient('s',Material.STICK);
        recipe.setIngredient('b',Material.BEACON);
        Bukkit.addRecipe(recipe);
    }
}
