package me.improperissues.servertips.commands;

import me.improperissues.servertips.data.Tip;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Tabs implements TabCompleter {

    public static final List<String> soundList = listSounds();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        String commandName = command.getName().toLowerCase().trim();

        switch (commandName) {
            case "tip":
                switch (args.length) {
                    case 1:
                        list.add("create");
                        list.add("broadcast");
                        list.add("delete");
                        list.add("modify");
                        list.add("playsound");
                        list.add("pausetimer");
                        break;
                    case 2:
                        if (!args[0].equals("pausetimer")) return Tip.listTips();
                        break;
                    case 3:
                        switch (args[0]) {
                            case "modify":
                                list.add("message");
                                list.add("sound");
                                list.add("volume");
                                list.add("pitch");
                                list.add("resetevents");
                                list.add("clickevent");
                                list.add("hoverevent");
                                break;
                        }
                        break;
                    case 4:
                        switch (args[2]) {
                            case "message":
                                list.add("§8<message: string>");
                                break;
                            case "sound":
                                return soundList;
                            case "clickevent":
                                list.add("setsuggestion");
                                list.add("seturl");
                                break;
                            case "hoverevent":
                                list.add("settext");
                                break;
                            case "volume":
                            case "pitch":
                                list.add("§8<value: float>");
                                break;
                        }
                        break;
                    case 5:
                        switch (args[3]) {
                            case "setsuggestion":
                                list.add("§8<suggestion: string>");
                                break;
                            case "seturl":
                                list.add("§8<url: string>");
                                break;
                            case "settext":
                                list.add("§8<text: string>");
                                break;
                        }
                        break;
                }
                break;
        }

        return list;
    }


    static List<String> listSounds() {
        List<String> list = new ArrayList<>();
        for (Sound sound : Sound.class.getEnumConstants()) list.add(sound.name().toLowerCase());
        return list;
    }
}
