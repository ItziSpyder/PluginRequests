package me.improper.ogredupealias.commands;

import me.improper.ogredupealias.OgredupeAlias;
import me.improper.ogredupealias.events.OnChat;
import me.improper.ogredupealias.events.OnCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
                case "mutechat" -> {
                    OnChat.ISCHATMUTED = !OnChat.ISCHATMUTED;
                    String message = " disabled chat for all players!";
                    message = OnChat.ISCHATMUTED ? message : " enabled chat!";
                    Bukkit.broadcastMessage(OgredupeAlias.STARTER
                            + ChatColor.GRAY + "["
                            + ChatColor.RED + "Chat"
                            + ChatColor.GRAY + "] " + sender.getName()
                            + ChatColor.RED + message);
                    return true;
                }
                case "commandspy" -> {
                    if (OnCommand.COMMANDLISTERS.contains(sender.getName())) {
                        OnCommand.COMMANDLISTERS.remove(sender.getName());
                        sender.sendMessage(OgredupeAlias.STARTER
                                + ChatColor.GRAY + "["
                                + ChatColor.BLUE + "Command Spy"
                                + ChatColor.GRAY + "] "
                                + ChatColor.RED + "You will no longer receive command logs!");
                    }
                    else {
                        OnCommand.COMMANDLISTERS.add(sender.getName());
                        sender.sendMessage(OgredupeAlias.STARTER
                                + ChatColor.GRAY + "["
                                + ChatColor.BLUE + "Command Spy"
                                + ChatColor.GRAY + "] "
                                + ChatColor.RED + "You will now receive command logs!");
                    }
                    return true;
                }
                case "socialspy" -> {
                    if (OnChat.CHATLISTENER.contains(sender.getName())) {
                        OnChat.CHATLISTENER.remove(sender.getName());
                        sender.sendMessage(OgredupeAlias.STARTER
                                + ChatColor.GRAY + "["
                                + ChatColor.BLUE + "Social Spy"
                                + ChatColor.GRAY + "] "
                                + ChatColor.RED + "You will no longer receive private message logs!");
                    }
                    else {
                        OnChat.CHATLISTENER.add(sender.getName());
                        sender.sendMessage(OgredupeAlias.STARTER
                                + ChatColor.GRAY + "["
                                + ChatColor.BLUE + "Social Spy"
                                + ChatColor.GRAY + "] "
                                + ChatColor.RED + "You will now receive private message logs!");
                    }
                    return true;
                }
                case "message" -> {
                    Player messager = (Player) sender;
                    Player receiver = Bukkit.getPlayer(args[0]);
                    String message = buildArgs(args,1);
                    if (message.trim().equals("")) {
                        messager.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "Please enter a message to send!");
                        return true;
                    }
                    messager.sendMessage(OgredupeAlias.STARTER
                            + ChatColor.GRAY + "["
                            + ChatColor.RED + ChatColor.BOLD + "(!) "
                            + ChatColor.YELLOW + "me -> " + receiver.getName()
                            + ChatColor.GRAY + "] "
                            + ChatColor.AQUA + message
                    );
                    receiver.sendMessage(OgredupeAlias.STARTER
                            + ChatColor.GRAY + "["
                            + ChatColor.RED + ChatColor.BOLD + "(!) "
                            + ChatColor.YELLOW + messager.getName() + " -> me"
                            + ChatColor.GRAY + "] "
                            + ChatColor.AQUA + message
                    );
                    for (Player online : Bukkit.getOnlinePlayers())
                        if (online != messager && online != receiver && online.hasPermission("ogredupealias.chat.see") && OnChat.CHATLISTENER.contains(online.getName()))
                            online.sendMessage(OgredupeAlias.STARTER
                                    + ChatColor.GRAY + "["
                                    + ChatColor.BLUE + "Social Spy"
                                    + ChatColor.GRAY + ": "
                                    + ChatColor.YELLOW + messager.getName() + " -> " + receiver.getName()
                                    + ChatColor.GRAY + "] "
                                    + ChatColor.AQUA + message
                            );
                    return true;
                }
                case "staffchat" -> {
                    String message = buildArgs(args,0);
                    if (message.trim().equals("")) {
                        sender.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "Please enter a message to send!");
                        return true;
                    }
                    for (Player online : Bukkit.getOnlinePlayers()) if (online.hasPermission("ogredupealias.chat.staff")) {
                        online.sendMessage(OgredupeAlias.STARTER
                                + ChatColor.GRAY + "["
                                + ChatColor.RED + ChatColor.BOLD + "(!) "
                                + ChatColor.GREEN + "Staff Chat: "
                                + ChatColor.YELLOW + sender.getName()
                                + ChatColor.GRAY + "] "
                                + ChatColor.GREEN + message);
                    }
                    return true;
                }
            }
        } catch (Exception exception) {
            String message = OgredupeAlias.STARTER
                    + ChatColor.DARK_RED + "Command error: "
                    + ChatColor.RED;
            if (exception instanceof NullPointerException) message += "Command contains a null value!";
            else if (exception instanceof IndexOutOfBoundsException) message += "Incomplete command! Please provide more information!";
            else message += exception.getMessage();
            sender.sendMessage(message);
            return true;
        }

        return false;
    }

    private static String buildArgs(String[] args, int beginIndex) {
        StringBuilder builder = new StringBuilder();
        for (int i = beginIndex; i < args.length; i ++) builder.append(args[i]).append(" ");
        return builder.toString().trim();
    }
}
