package me.improperissues.fireart.commands;

import me.improperissues.fireart.FireArt;
import me.improperissues.fireart.data.*;
import me.improperissues.fireart.map.SegmentSelection;
import me.improperissues.fireart.other.Config;
import me.improperissues.fireart.other.Items;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase().trim();

        try {
            switch (commandName) {
                case "givebrush" -> {
                    ((Player) sender).getInventory().setItemInMainHand(Items.PAINTBRUSH);
                    sender.sendMessage(FireArt.STARTER + ChatColor.GREEN + "Gave one PAINT BRUSH!");
                    return true;
                }
                case "giveselector" -> {
                    ((Player) sender).getInventory().setItemInMainHand(Items.SELECTOR);
                    sender.sendMessage(FireArt.STARTER + ChatColor.GREEN + "Gave one SEGMENT SELECTOR!");
                    return true;
                }
                case "giveinspector" -> {
                    ((Player) sender).getInventory().setItemInMainHand(Items.INSPECTOR);
                    sender.sendMessage(FireArt.STARTER + ChatColor.GREEN + "Gave one SEGMENT INSPECTOR!");
                    return true;
                }
                case "paintselector" -> {
                    PaintMenuPage.createInventory((Player) sender,0);
                    return true;
                }
                case "segment" -> {
                    switch (args[0]) {
                        case "revert" -> {
                            if (args[1].equals("#RANDOM")) {
                                if (SegmentFile.listFiles().size() <= 0) {
                                    sender.sendMessage(FireArt.STARTER + ChatColor.RED + "This server does not have any saved segments! " + ChatColor.YELLOW + "/segment save <new name>");
                                    return true;
                                }
                                List<String> list = SegmentFile.listFiles();
                                Segment segment = SegmentFile.load(list.get((int) Math.floor(Math.random() * list.size())));
                                segment.revert();
                                sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Reverted a random segment (" + segment.getName() + ") to its original form!");
                                return true;
                            }
                            if (args[1].equals("#SELECTION")) {
                                if (!SegmentSelection.hasSelection((Player) sender)) {
                                    sender.sendMessage(FireArt.STARTER + ChatColor.RED + "You do not have any selections! " + ChatColor.YELLOW + "/giveselector");
                                    return true;
                                }
                                Segment segment = SegmentSelection.getSegment((Player) sender);
                                segment.revert();
                                sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Reverted your current selection to its original form!");
                                return true;
                            }
                            if (args[1].equals("#ALL")) {
                                for (String name : SegmentFile.listFiles()) SegmentFile.load(name).revert();
                                sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Reverted all existing segments to their original forms!");
                                return true;
                            }
                            Segment segment = SegmentFile.load(args[1]);
                            segment.revert();
                            sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Reverted segment " + segment.getName() + " to original form");
                            return true;
                        }
                        case "settype" -> {
                            Material type = null;
                            boolean randomize = false;
                            try {
                                type = Material.valueOf(args[2].toUpperCase().trim());
                            } catch (IllegalArgumentException exception) {
                                switch (args[2]) {
                                    case "#RANDOM":
                                        randomize = true;
                                        type = PaintedBlock.ALLPOSSIBLE[(int) Math.floor(Math.random() * PaintedBlock.ALLPOSSIBLE.length)];
                                        break;
                                }
                            }
                            if (args[1].equals("#RANDOM")) {
                                if (SegmentFile.listFiles().size() <= 0) {
                                    sender.sendMessage(FireArt.STARTER + ChatColor.RED + "This server does not have any saved segments! " + ChatColor.YELLOW + "/segment save <new name>");
                                    return true;
                                }
                                List<String> list = SegmentFile.listFiles();
                                Segment segment = SegmentFile.load(list.get((int) Math.floor(Math.random() * list.size())));
                                segment.fillWith(type);
                                sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Set a random segment (" + segment.getName() + ") to " + type.name());
                                return true;
                            }
                            if (args[1].equals("#SELECTION")) {
                                if (!SegmentSelection.hasSelection((Player) sender)) {
                                    sender.sendMessage(FireArt.STARTER + ChatColor.RED + "You do not have any selections! " + ChatColor.YELLOW + "/giveselector");
                                    return true;
                                }
                                Segment segment = SegmentSelection.getSegment((Player) sender);
                                segment.fillWith(type);
                                sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Set your current selection to " + type.name());
                                return true;
                            }
                            if (args[1].equals("#ALL")) {
                                if (randomize) for (String name : SegmentFile.listFiles()) SegmentFile.load(name).fillWith(PaintedBlock.ALLPOSSIBLE[(int) Math.floor(Math.random() * PaintedBlock.ALLPOSSIBLE.length)]);
                                else for (String name : SegmentFile.listFiles()) SegmentFile.load(name).fillWith(type);
                                sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Reshuffled all existing segments to a new block type!");
                                return true;
                            }
                            Segment segment = SegmentFile.load(args[1]);
                            segment.fillWith(type);
                            sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Set segment " + segment.getName() + " to " + type.name());
                            return true;
                        }
                        case "delete" -> {
                            SegmentFile.delete(args[1]);
                            sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Attempted to delete " + args[1]);
                            return true;
                        }
                        case "save" -> {
                            if (!SegmentSelection.hasSelection((Player) sender)) {
                                sender.sendMessage(FireArt.STARTER + ChatColor.RED + "You do not have any selections! " + ChatColor.YELLOW + "/giveselector");
                                return true;
                            }
                            Segment segment = SegmentSelection.getSegment((Player) sender);
                            segment.setName(args[1]);
                            SegmentFile.save(segment);
                            sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Attempted to save " + segment.getName());
                            return true;
                        }
                        case "teleport" -> {
                            if (args[1].equals("#RANDOM")) {
                                if (SegmentFile.listFiles().size() <= 0) {
                                    sender.sendMessage(FireArt.STARTER + ChatColor.RED + "This server does not have any saved segments! " + ChatColor.YELLOW + "/segment save <new name>");
                                    return true;
                                }
                                List<String> list = SegmentFile.listFiles();
                                Segment segment = SegmentFile.load(list.get((int) Math.floor(Math.random() * list.size())));
                                SelectedBlock block = segment.getBlocks().get(segment.getBlocks().size() - 1);
                                ((Player) sender).teleport(block.toLocation());
                                sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Teleporting you to a random saved segment! (" + segment.getName() + ")");
                                return true;
                            }
                            if (args[1].equals("#SELECTION")) {
                                if (!SegmentSelection.hasSelection((Player) sender)) {
                                    sender.sendMessage(FireArt.STARTER + ChatColor.RED + "You do not have any selections! " + ChatColor.YELLOW + "/giveselector");
                                    return true;
                                }
                                Segment segment = SegmentSelection.getSegment((Player) sender);
                                SelectedBlock block = segment.getBlocks().get(segment.getBlocks().size() - 1);
                                ((Player) sender).teleport(block.toLocation());
                                sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Teleporting you to your current segment selection!");
                                return true;
                            }
                            Segment segment = SegmentFile.load(args[1]);
                            SelectedBlock block = segment.getBlocks().get(segment.getBlocks().size() - 1);
                            ((Player) sender).teleport(block.toLocation());
                            sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Teleporting you to " + segment.getName());
                        }
                        case "list" -> {
                            sender.sendMessage(FireArt.STARTER + ChatColor.GREEN + "There are currently " + SegmentFile.listFiles().size() +
                                    " total segments saved! " + ChatColor.GRAY + SegmentFile.listFiles().toString()
                                    .replaceAll("\\[",ChatColor.DARK_GRAY + "[" + ChatColor.GRAY)
                                    .replaceAll("]",ChatColor.DARK_GRAY + "]")
                                    .replaceAll(",",ChatColor.DARK_GRAY + "," + ChatColor.GRAY)
                            );
                        }
                    }
                    return true;
                }
                case "artkit" -> {
                    ((Player) sender).getInventory().clear();
                    ((Player) sender).updateInventory();
                    if (sender.isOp()) {
                        ((Player) sender).getInventory().setItem(1,Items.SELECTOR);
                        ((Player) sender).getInventory().setItem(4,Items.INSPECTOR);
                        ((Player) sender).getInventory().setItem(7,Items.PAINTBRUSH);
                        return true;
                    }
                    ((Player) sender).getInventory().setItem(3,Items.INSPECTOR);
                    ((Player) sender).getInventory().setItem(5,Items.SELECTOR);
                    return true;
                }
                case "toggle" -> {
                    switch (args[0]) {
                        case "flight":
                            if (!sender.isOp() && !Config.getCanFly()) {
                                sender.sendMessage(FireArt.STARTER + ChatColor.RED + "Non-op flight is toggled off for server players!");
                                return true;
                            }
                            ((Player) sender).setAllowFlight(!((Player) sender).getAllowFlight());
                            sender.sendMessage(FireArt.STARTER + ChatColor.LIGHT_PURPLE + "Now set flight to " + ((Player) sender).getAllowFlight());
                            return true;
                    }
                    return true;
                }
            }
        } catch (Exception exception) {
            String message = FireArt.STARTER + ChatColor.DARK_RED + "Command error: " + ChatColor.RED;
            if (exception instanceof IndexOutOfBoundsException) message += "Incomplete command! Not even information was provided!";
            else if (exception instanceof NullPointerException) message += "Command contains a Null value!";
            else message += exception.getMessage();
            sender.sendMessage(message);
            return true;
        }

        return false;
    }
}
