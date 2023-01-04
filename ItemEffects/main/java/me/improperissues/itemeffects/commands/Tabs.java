package me.improperissues.itemeffects.commands;

import me.improperissues.itemeffects.server.Effects;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Tabs implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();

        switch (command.getName().toLowerCase().trim()) {
            case "description":
            case "displayname":
                switch (args.length) {
                    case 1:
                        list.add("§8<string>");
                        break;
                }
                break;
        }

        return list;
    }
}
