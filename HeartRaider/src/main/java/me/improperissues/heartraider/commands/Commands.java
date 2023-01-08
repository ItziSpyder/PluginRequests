package me.improperissues.heartraider.commands;

import me.improperissues.heartraider.server.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase().trim();

        try {
            switch (commandName) {
                case "giveitem" -> {
                    ItemStack item = Items.valueOf(args[0]);
                    Player p = null;
                    switch (args.length) {
                        case 1:
                            p = (Player) sender;
                            break;
                        case 2:
                            p = Bukkit.getPlayer(args[1]);
                            break;
                    }
                    p.getInventory().addItem(item);
                    return true;
                }
            }
        } catch (Exception exception) {
            String message = ChatColor.DARK_RED + "Command error: " + ChatColor.RED;
            if (exception instanceof NullPointerException) message += "Command contains a null value!";
            else if (exception instanceof IndexOutOfBoundsException) message += "Incomplete command! Please provide more information!";
            else message += exception.getMessage();
            sender.sendMessage(message);
            return true;
        }

        return false;
    }
}
