package me.improperissues.playerblocks.commands;

import me.improperissues.playerblocks.data.RegionFile;
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
            case "playerblocks":
                switch (args.length) {
                    case 1:
                        list.add("create");
                        list.add("delete");
                        list.add("teleport");
                        list.add("clear");
                        list.add("givewand");
                        list.add("togglelog");
                        break;
                    case 2:
                        return RegionFile.listNames();
                }
                break;
        }

        return list;
    }
}
