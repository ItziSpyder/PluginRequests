package me.improperissues.roboroan.commands;

import me.improperissues.roboroan.events.ChatEvent;
import me.improperissues.roboroan.events.InteractEvent;
import me.improperissues.roboroan.other.Messages;
import me.improperissues.roboroan.other.ServerSound;
import me.improperissues.roboroan.server.OpenInventory;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
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
                case "mute":
                    Player mute = Bukkit.getPlayer(args[0]);
                    ChatEvent.setMuted(sender,mute,true);
                    return true;
                case "unmute":
                    Player unmute = Bukkit.getPlayer(args[0]);
                    ChatEvent.setMuted(sender,unmute,false);
                    return true;
                case "freeze":
                    Player freeze = Bukkit.getPlayer(args[0]);
                    InteractEvent.setFrozen(sender,freeze,true);
                    return true;
                case "unfreeze":
                    Player unfreeze = Bukkit.getPlayer(args[0]);
                    InteractEvent.setFrozen(sender,unfreeze,false);
                    return true;
                case "invsee":
                    Player invsee = Bukkit.getPlayer(args[0]);
                    OpenInventory.openInventory((Player) sender,invsee);
                    return true;
                case "broadcast":
                    if (args.length == 0) {
                        sender.sendMessage(Messages.STARTER + "cPlease provide a message!");
                        return true;
                    }
                    ServerSound sound = new ServerSound(null, Sound.BLOCK_NOTE_BLOCK_PLING, 10, 10);
                    Bukkit.broadcastMessage(Messages.STARTER + "7[§c§l§nBROADCAST§7] §f§l" + Messages.impColors(Messages.buildString(args)));
                    sound.repeatAllAt(3,5);
                    return true;
            }
        } catch (Exception exception) {
            String message = Messages.STARTER + "4Command error: §c";
            if (exception instanceof NullPointerException) message += "Command contains a null value!";
            else if (exception instanceof IndexOutOfBoundsException) message += "Command incomplete! Please provide more information";
            else message += exception.getMessage();
            sender.sendMessage(message);
        }

        return false;
    }
}
