package me.improperissues.playerblocks.commands;

import me.improperissues.playerblocks.PlayerBlocks;
import me.improperissues.playerblocks.data.BlockRegion;
import me.improperissues.playerblocks.data.RegionFile;
import me.improperissues.playerblocks.other.Items;
import me.improperissues.playerblocks.other.Selection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase().trim();
        File PARENT = new File("plugins/PlayerBlocks/blockregions");

        try {
            switch (commandName) {
                case "playerblocks":
                    switch (args[0]) {
                        case "clear":
                            BlockRegion clear = RegionFile.load(new File(PARENT,args[1] + ".blocks"));
                            clear.clearBlocks();
                            sender.sendMessage(PlayerBlocks.STARTER + "7Cleared all placed blocks for " + clear.getName() + "!");
                            RegionFile.save(clear);
                            return true;
                        case "teleport":
                            BlockRegion teleport = RegionFile.load(new File(PARENT,args[1] + ".blocks"));
                            ((Player) sender).teleport(teleport.getCorner1().toLocation());
                            sender.sendMessage(PlayerBlocks.STARTER + "7Teleported to " + teleport.getName() + "!");
                            return true;
                        case "create":
                            if (!Selection.hasFullSelection((Player) sender)) {
                                sender.sendMessage(PlayerBlocks.STARTER + "cYou do not have a region selection! §e/playerblocks givewand");
                                return true;
                            }
                            BlockRegion create = new BlockRegion(args[1],Selection.getSelection1((Player) sender),Selection.getSelection2((Player) sender));
                            RegionFile.save(create);
                            sender.sendMessage(PlayerBlocks.STARTER + "7Created new block region " + create.getName() + "!");
                            return true;
                        case "givewand":
                            ((Player) sender).getInventory().setItemInMainHand(Items.SELECTION);
                            sender.sendMessage(PlayerBlocks.STARTER + "7Gave new selection wand!");
                            return true;
                        case "delete":
                            File delete = new File(PARENT,args[1] + ".blocks");
                            sender.sendMessage(PlayerBlocks.STARTER + "7Deleting block region " + delete.getPath() + "!");
                            delete.delete();
                            return true;
                        case "togglelog":
                            BlockRegion togglelog = RegionFile.load(new File(PARENT,args[1] + ".blocks"));
                            togglelog.setLogging(!togglelog.isLogging());
                            sender.sendMessage(PlayerBlocks.STARTER + "7Set player block logging for " + togglelog.getName() + " to " + togglelog.isLogging() + "!");
                            RegionFile.save(togglelog);
                            return true;
                    }
                    return true;
            }
        } catch (Exception exception) {
            String message = PlayerBlocks.STARTER + "4Command error: §c";
            if (exception instanceof NullPointerException) message += "Command contains a null value!";
            else if (exception instanceof IndexOutOfBoundsException) message += "Incomplete command! Please provide more information!";
            else message += exception.getMessage();
            sender.sendMessage(message);
        }

        return false;
    }
}
