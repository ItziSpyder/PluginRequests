package me.improperissues.roboroan.commands;

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
            case "broadcast":
                break;
        }

        return list;
    }
}
