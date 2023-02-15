package me.improperissues.fireart.data;

import me.improperissues.fireart.other.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaintMenuPage {

    public static void createInventory(Player player, int index) {
        Inventory menu = Bukkit.createInventory(player,54, ChatColor.DARK_GRAY + ">> " + ChatColor.GREEN + "Paint Selection: " + ChatColor.YELLOW + (index + 1));
        ItemStack x = Items.setBlank(new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        ItemStack b = Items.setBlank(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        ItemStack a = Items.AIR;
        menu.setContents(new ItemStack[]{
                a,a,a,a,a,a,a,a,a,
                a,a,a,a,a,a,a,a,a,
                a,a,a,a,a,a,a,a,a,
                a,a,a,a,a,a,a,a,a,
                a,a,a,a,a,a,a,a,a,
                x,x,x,Items.BACK,x,Items.NEXT,x,x,x
        });
        List<Material> materials = new ArrayList<>(Arrays.asList(PaintedBlock.ALLPOSSIBLE));
        for (int i = (index * 45); i < (index * 45) + 45; i ++) {
            try {
                menu.addItem(new ItemStack(materials.get(i)));
            } catch (IndexOutOfBoundsException exception) { break; }
        }
        while (menu.firstEmpty() != -1) menu.setItem(menu.firstEmpty(),b);
        player.openInventory(menu);
    }
}
