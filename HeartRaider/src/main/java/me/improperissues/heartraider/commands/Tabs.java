package me.improperissues.heartraider.commands;

import me.improperissues.heartraider.server.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Tabs implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        String commandName = command.getName().toLowerCase().trim();

        switch (commandName) {
            case "giveitem" -> {
                switch (args.length) {
                    case 1:
                        list.add("fireballwand");
                        list.add("witherblade");
                        break;
                }
            }
        }

        return list;
    }
}
