package me.improperissues.itemeffects.commands;

import me.improperissues.itemeffects.server.Effects;
import me.improperissues.itemeffects.server.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;
        switch (command.getName().toLowerCase().trim()) {
            case "description":
                if (args.length >= 1) {
                    try {
                        StringBuilder builder = new StringBuilder();
                        for (String arg : args) {
                            builder.append(arg).append(" ");
                        }
                        ItemStack item = p.getInventory().getItemInMainHand();
                        p.getInventory().setItemInMainHand(Items.setDescription(item,String.valueOf(builder).trim()));
                        p.sendMessage("§fModified data of §7" + item.getType().name().toLowerCase() + " §f!");
                        return true;
                    } catch (NullPointerException | IllegalArgumentException exception) {
                        p.sendMessage("§fUse '§a+§f' and §b§othe name of the potion effect §fto §eadd a potion effect §f!\n" +
                                "§fUse '§a->§f' to §eskip to the next line §f!\n" +
                                "§fUse '§a&§f' as a color code to §emake your text colorful §f!");
                        return false;
                    }
                }
                p.sendMessage("§fUse '§a+§f' and §b§othe name of the potion effect §fto §eadd a potion effect §f!\n" +
                        "§fUse '§a->§f' to §eskip to the next line §f!\n" +
                        "§fUse '§a&§f' as a color code to §emake your text colorful §f!");
                return false;
            case "displayname":
                if (args.length >= 1) {
                    try {
                        StringBuilder builder = new StringBuilder();
                        for (String arg : args) {
                            builder.append(arg).append(" ");
                        }
                        ItemStack item = p.getInventory().getItemInMainHand();
                        p.getInventory().setItemInMainHand(Items.setDisplayName(item,String.valueOf(builder).trim()));
                        p.sendMessage("§fModified data of §7" + item.getType().name().toLowerCase() + " §f!");
                        return true;
                    } catch (NullPointerException | IllegalArgumentException exception) {
                        p.sendMessage("§fUse '§a&§f' as a color code to §emake your text colorful §f!");
                        return false;
                    }
                }
                p.sendMessage("§fUse '§a&§f' as a color code to §emake your text colorful §f!");
                return false;
            case "effectlist":
                p.sendMessage("§fThere are a total of (§a" + Effects.getAllEffects(false).size() + "§f) effects: §7"
                        + Effects.getAllEffects(false).toString().replaceAll("\\[","").replaceAll("]",""));
                return true;
            case "colorcodes":
                p.sendMessage("§fThere are a total of (§a22§f) colors: §7'& + ( §00 §7, §11 §7, §22 §7, §33 §7, §44 §7, §55 §7, §66 §7, §77 §7, §88 §7, §99 §7, " +
                        "§aa §7, §bb §7, §cc §7, §dd §7, §ee §7, §ff §7, §nn §7, §mm §7, §kk §7, §ll §7, §oo §7, §rr §7)'");
                return true;
        }
        return false;
    }
}
