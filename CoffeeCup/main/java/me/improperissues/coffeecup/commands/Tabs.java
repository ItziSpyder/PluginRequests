package me.improperissues.coffeecup.commands;

import me.improperissues.coffeecup.CoffeeCup;
import me.improperissues.coffeecup.files.SpawnControl;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class Tabs implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();

        switch (command.getName().toLowerCase()) {
            case "spawncontrol":
                switch (args.length) {
                    case 1:
                        return SpawnControl.allGroups();
                    case 2:
                        list.add("true");
                        list.add("false");
                        break;
                }
                break;
            case "clearall":
                switch (args.length) {
                    case 1:
                        return SpawnControl.allGroups();
                }
                break;
            case "clearlag":
                switch (args.length) {
                    case 1:
                        list.add("resume");
                        list.add("setClearType");
                        list.add("setInterval");
                        break;
                    case 2:
                        switch (args[0].toLowerCase()) {
                            case "resume":
                                list.add("true");
                                list.add("false");
                                break;
                            case "setcleartype":
                                return SpawnControl.allGroups();
                            case "setinterval":
                                list.add("§8<seconds: double>");
                                break;
                        }
                        break;
                }
                break;
            case "pl":
                switch (args.length) {
                    case 1:
                        list.add("menu");
                        list.add("disable");
                        list.add("enable");
                        break;
                    case 2:
                        switch (args[0].toLowerCase()) {
                            case "disable":
                            case "enable":
                                return CoffeeCup.getPluginList();
                        }
                }
        }

        return list;
    }
}
