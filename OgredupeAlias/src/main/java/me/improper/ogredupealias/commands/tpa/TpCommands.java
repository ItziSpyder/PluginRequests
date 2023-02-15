package me.improper.ogredupealias.commands.tpa;

import me.improper.ogredupealias.OgredupeAlias;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase().trim();

        try {
            switch (commandName) {
                case "tpa" -> {
                    Player requester = (Player) sender;
                    Player requested = Bukkit.getPlayer(args[0]);
                    TpRequest request = new TpRequest(TpRequest.RequestType.TPA,requester,requested);
                    TpAsk.sendRequest(request);
                    return true;
                }
                case "tpahere" -> {
                    Player requester = (Player) sender;
                    Player requested = Bukkit.getPlayer(args[0]);
                    TpRequest request = new TpRequest(TpRequest.RequestType.TPAHERE,requester,requested);
                    TpAsk.sendRequest(request);
                    return true;
                }
                case "tpcancel" -> {
                    Player requester = (Player) sender;
                    TpRequest request = TpAsk.getRequest(requester);
                    if (!TpAsk.hasRequest(requester) || request == null) {
                        requester.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "You do not have any outgoing requests!");
                        return true;
                    }
                    TpAsk.cancelRequest(request);
                    return true;
                }
                case "tpaccept" -> {
                    Player requested = (Player) sender;
                    TpRequest request = TpAsk.getRequest(requested);
                    if (!TpAsk.hasRequest(requested) || request == null) {
                        requested.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "You do not have any incoming requests!");
                        return true;
                    }
                    TpAsk.acceptRequest(request);
                    return true;
                }
                case "tpdeny" -> {
                    Player requested = (Player) sender;
                    TpRequest request = TpAsk.getRequest(requested);
                    if (!TpAsk.hasRequest(requested) || request == null) {
                        requested.sendMessage(OgredupeAlias.STARTER + ChatColor.RED + "You do not have any incoming requests!");
                        return true;
                    }
                    TpAsk.denyRequest(request);
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
