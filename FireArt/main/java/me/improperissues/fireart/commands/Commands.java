package me.improperissues.fireart.commands;

import me.improperissues.fireart.FireArt;
import me.improperissues.fireart.data.PaintMenuPage;
import me.improperissues.fireart.other.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase().trim();

        try {
            switch (commandName) {
                case "givebrush":
                    ((Player) sender).getInventory().setItemInMainHand(Items.PAINTBRUSH);
                    sender.sendMessage(FireArt.STARTER + "aGave one PAINT BRUSH!");
                    return true;
                case "paintselector":
                    PaintMenuPage.createInventory((Player) sender,0);
                    return true;
            }
        } catch (Exception exception) {
            String message = FireArt.STARTER + "4Command error: §c";
            if (exception instanceof IndexOutOfBoundsException) message += "Incomplete command! Not even information was provided!";
            else if (exception instanceof NullPointerException) message += "Command contains a Null value!";
            else message += exception.getMessage();
            sender.sendMessage(message);
        }

        return false;
    }
}
