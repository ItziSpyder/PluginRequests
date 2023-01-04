package me.improperissues.coffeecup.commands;

import me.improperissues.coffeecup.CoffeeCup;
import me.improperissues.coffeecup.events.SpawnEvent;
import me.improperissues.coffeecup.files.ClearLag;
import me.improperissues.coffeecup.files.SpawnControl;
import me.improperissues.coffeecup.other.Messages;
import me.improperissues.coffeecup.other.PluginMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName().toLowerCase()) {
            case "spawncontrol":
                if (args.length >= 2) {
                    boolean bool = Boolean.parseBoolean(args[1]);
                    try {
                        EntityType type = EntityType.valueOf(args[0].toUpperCase());
                        SpawnControl.get().set("server.options.types." + type.name(),bool);
                        SpawnControl.save();
                        Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7set §f" + type.name().toLowerCase() + " §7spawning to §f" + bool);
                        return true;
                    } catch (IllegalArgumentException | NullPointerException exception) {
                        if (args[0].charAt(0) == '#') {
                            switch (args[0].toUpperCase()) {
                                case "#ALL":
                                    for (EntityType type : EntityType.class.getEnumConstants()) {
                                        SpawnControl.get().set("server.options.types." + type.name(),bool);
                                    }
                                    SpawnControl.save();
                                    Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7set §f" + args[0] + " §7spawning to §f" + bool);
                                    break;
                                case "#COMMON":
                                case "#NON-LIVING":
                                case "#LIVING":
                                case "#PASSIVE":
                                case "#MONSTER":
                                case "#NAMED":
                                    SpawnControl.get().set("server.options.groups." + args[0],bool);
                                    SpawnControl.save();
                                    Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7set §f" + args[0] + " §7spawning to §f" + bool);
                                    break;
                            }
                            return true;
                        } else {
                            sender.sendMessage(Messages.invalid);
                        }
                    }
                } else {
                    sender.sendMessage(Messages.invalid);
                }
                break;
            case "clearall":
                int count = 0;
                if (args.length >= 1) {
                    try {
                        EntityType type = EntityType.valueOf(args[0].toUpperCase());
                        for (Entity entity : SpawnControl.getServerEntities()) {
                            if (entity.getType().equals(type)) {
                                entity.remove();
                                count++;
                            }
                        }
                        Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7cleared all §f" + type.name().toLowerCase() + " §f(" + count + ")");
                        return true;
                    } catch (IllegalArgumentException | NullPointerException exception) {
                        if (args[0].charAt(0) == '#') {
                            switch (args[0].toUpperCase()) {
                                case "#ALL":
                                    for (Entity entity : SpawnControl.getServerEntities()) {
                                        entity.remove();
                                        count ++;
                                    }
                                    Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7cleared §f" + args[0] + " §f(" + count + ")");
                                    break;
                                case "#NAMED":
                                    for (Entity entity : SpawnControl.getServerEntities()) {
                                        if (SpawnEvent.isNamed(entity)) {
                                            entity.remove();
                                            count ++;
                                        }
                                    }
                                    Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7cleared §f" + args[0] + " §f(" + count + ")");
                                    break;
                                case "#LIVING":
                                    for (Entity entity : SpawnControl.getServerEntities()) {
                                        if (SpawnEvent.isLiving(entity)) {
                                            entity.remove();
                                            count ++;
                                        }
                                    }
                                    Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7cleared §f" + args[0] + " §f(" + count + ")");
                                    break;
                                case "#NON-LIVING":
                                    for (Entity entity : SpawnControl.getServerEntities()) {
                                        if (SpawnEvent.isNonLiving(entity)) {
                                            entity.remove();
                                            count ++;
                                        }
                                    }
                                    Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7cleared §f" + args[0] + " §f(" + count + ")");
                                    break;
                                case "#MONSTER":
                                    for (Entity entity : SpawnControl.getServerEntities()) {
                                        if (SpawnEvent.isMonster(entity)) {
                                            entity.remove();
                                            count ++;
                                        }
                                    }
                                    Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7cleared §f" + args[0] + " §f(" + count + ")");
                                    break;
                                case "#PASSIVE":
                                    for (Entity entity : SpawnControl.getServerEntities()) {
                                        if (SpawnEvent.isPassive(entity)) {
                                            entity.remove();
                                            count ++;
                                        }
                                    }
                                    Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7cleared §f" + args[0] + " §f(" + count + ")");
                                    break;
                                case "#COMMON":
                                    for (Entity entity : SpawnControl.getServerEntities()) {
                                        if (SpawnEvent.isCommon(entity)) {
                                            entity.remove();
                                            count ++;
                                        }
                                    }
                                    Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7cleared §f" + args[0] + " §f(" + count + ")");
                                    break;
                            }
                            return true;
                        } else {
                            sender.sendMessage(Messages.invalid);
                        }
                    }
                } else {
                    for (Entity entity : SpawnControl.getServerEntities()) {
                        entity.remove();
                        count ++;
                    }
                    Messages.bmOp(Messages.starter + "f" + sender.getName() + " §7cleared all entities §f(" + count + ")");
                    return true;
                }
                break;
            case "clearchat":
                StringBuilder msg = new StringBuilder();
                for (int i = 0; i < 2000; i ++) {
                    msg.append("\n").append(" ");
                }
                msg.append(Messages.starter + "f" + sender.getName() + " §7cleared the chat");
                Messages.bm(String.valueOf(msg));
                return true;
            case "clearlag":
                if (args.length >= 2) {
                    switch (args[0].toLowerCase()) {
                        case "setinterval":
                            try {
                                double interval = Double.parseDouble(args[1]);
                                ClearLag.get().set("server.options.interval",interval);
                                ClearLag.save();
                                CoffeeCup.timer = interval;
                                sender.sendMessage(Messages.starter + "7Set §fclear interval §7to §f" + interval);
                            } catch (IllegalArgumentException exception) {
                                sender.sendMessage(Messages.invalid);
                            }
                            break;
                        case "resume":
                            boolean bool = Boolean.parseBoolean(args[1]);
                            ClearLag.get().set("server.options.resume",bool);
                            ClearLag.save();
                            sender.sendMessage(Messages.starter + "7Set §fclear lag §7to §f" + bool);
                            break;
                        case "setcleartype":
                            String type = args[1];
                            if (SpawnControl.allGroups().contains(type)) {
                                ClearLag.get().set("server.options.cleartype",type);
                                ClearLag.save();
                                sender.sendMessage(Messages.starter + "7Set §fclear type §7to §f" + type);
                            } else {
                                sender.sendMessage(Messages.invalid);
                            }
                            break;
                    }
                    return true;
                } else {
                    sender.sendMessage(Messages.invalid);
                }
                break;
            case "pl":
                if (args.length >= 2) {
                    Plugin plugin = CoffeeCup.getPlugin(args[1]);
                    if (plugin == null) {
                        return false;
                    }
                    switch (args[0].toLowerCase()) {
                        case "enable":
                            sender.sendMessage(Messages.starter + "aEnabling plugin §7\"" + plugin.getName() + "\" §a...");
                            Bukkit.getServer().getPluginManager().enablePlugin(plugin);
                            sender.sendMessage(Messages.starter + "aEnabled §7\"" + plugin.getName() + "\"");
                            break;
                        case "disable":
                            sender.sendMessage(Messages.starter + "cDisabling plugin §7\"" + plugin.getName() + "\" §c...");
                            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
                            sender.sendMessage(Messages.starter + "cDisabled §7\"" + plugin.getName() + "\"");
                            break;
                    }
                    return true;
                } else {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        PluginMenu.openPluginMenu(p,PluginMenu.getOccupiedPages() - 1);
                    }
                    return true;
                }
        }
        return false;
    }
}
