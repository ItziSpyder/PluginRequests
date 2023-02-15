package me.improper.ogredupealias.commands;

import me.improper.ogredupealias.other.ServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Tabs implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String commandName = command.getName().toLowerCase().trim();
        List<String> list = new ArrayList<>();

        switch (commandName) {
            case "message" -> {
                switch (args.length) {
                    case 1 -> list.addAll(ServerUtil.listPlayers());
                    case 2 -> list.add("[<message>]");
                }
            }
            case "staffchat" -> {
                switch (args.length) {
                    case 1 -> list.add("[<message>]");
                }
            }
            case "tpa","tpahere" -> {
                switch (args.length) {
                    case 1 -> list.addAll(ServerUtil.listPlayers());
                }
            }
            case "tpaccept","tpdeny","tpcancel" -> {

            }
        }

        list.removeIf(i -> !i.toLowerCase().contains(args[args.length - 1].toLowerCase()));
        return list;
    }
}
