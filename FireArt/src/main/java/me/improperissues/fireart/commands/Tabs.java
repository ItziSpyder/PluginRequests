package me.improperissues.fireart.commands;

import me.improperissues.fireart.data.SegmentFile;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Tabs implements TabCompleter {

    public static final List<String> materialList = listMaterials();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        String commandName = command.getName().toLowerCase().trim();

        switch (commandName) {
            case "segment" -> {
                switch (args.length) {
                    case 1:
                        list.add("revert");
                        list.add("settype");
                        list.add("delete");
                        list.add("save");
                        list.add("teleport");
                        list.add("list");
                        break;
                    case 2:
                        switch (args[0]) {
                            case "revert":
                            case "teleport":
                            case "settype":
                                list.addAll(SegmentFile.listFiles());
                                list.add("#SELECTION");
                                list.add("#ALL");
                                list.add("#RANDOM");
                                break;
                            case "delete":
                            case "save":
                                list.addAll(SegmentFile.listFiles());
                                break;
                        }
                        break;
                    case 3:
                        switch (args[0]) {
                            case "settype":
                                list.addAll(materialList);
                                list.add("#RANDOM");
                                break;
                        }
                        break;
                }
            }
            case "toggle" -> {
                list.add("flight");
                break;
            }
            case "giveselector", "giveinspector", "givebrush", "artkit", "paintselector" -> {
                break;
            }
        }

        list.removeIf(i -> !i.toLowerCase().contains(args[args.length - 1].toLowerCase()));
        return list;
    }

    public static List<String> listMaterials() {
        List<String> list = new ArrayList<>();
        for (Material material : Material.values()) {
            if (material.isBlock() && material.isSolid() && material.isOccluding()) list.add(material.name().toLowerCase());
        }
        return list;
    }
}
