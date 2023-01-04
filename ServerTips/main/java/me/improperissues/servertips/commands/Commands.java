package me.improperissues.servertips.commands;

import me.improperissues.servertips.ServerTips;
import me.improperissues.servertips.data.Config;
import me.improperissues.servertips.data.Tip;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    static String STARTER = ServerTips.STARTER;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase().trim();

        try {
            switch (commandName) {
                case "tip":
                    Tip tip;
                    switch (args[0]) {
                        case "create":
                            tip = new Tip(args[1],buildArgs(args,2));
                            tip.save();
                            sender.sendMessage(STARTER + "fCreated new tip \"" + tip.getName() + "\"");
                            return true;
                        case "delete":
                            tip = Tip.load(args[1]);
                            tip.delete();
                            sender.sendMessage(STARTER + "fDeleted tip \"" + tip.getName() + "\"");
                            return true;
                        case "broadcast":
                            tip = Tip.load(args[1]);
                            tip.broadcast();
                            return true;
                        case "playsound":
                            tip = Tip.load(args[1]);
                            tip.toSound().playAllAt();
                            return true;
                        case "pausetimer":
                            if (Config.getGeneralPaused()) {
                                Config.setGeneralPaused(false);
                                sender.sendMessage(STARTER + "fResumed tip broadcast timer!");
                                return true;
                            }
                            Config.setGeneralPaused(true);
                            sender.sendMessage(STARTER + "fPaused tip broadcast timer!");
                            return true;
                        case "modify":
                            tip = Tip.load(args[1]);
                            switch (args[2]) {
                                case "message":
                                    tip.setMessage(buildArgs(args,3));
                                    tip.save();
                                    sender.sendMessage(STARTER + "fModified message of tip \"" + tip.getName() + "\"");
                                    return true;
                                case "sound":
                                    Sound sound = Sound.valueOf(args[3].toUpperCase());
                                    tip.setSound(sound.name());
                                    tip.save();
                                    sender.sendMessage(STARTER + "fModified sound of tip \"" + tip.getName() + "\"");
                                    return true;
                                case "volume":
                                    tip.setVolume(Float.parseFloat(args[3]));
                                    tip.save();
                                    sender.sendMessage(STARTER + "fModified sound volume of tip \"" + tip.getName() + "\"");
                                    return true;
                                case "pitch":
                                    tip.setPitch(Float.parseFloat(args[3]));
                                    tip.save();
                                    sender.sendMessage(STARTER + "fModified sound pitch of tip \"" + tip.getName() + "\"");
                                    return true;
                                case "resetevents":
                                    tip.resetEvents();
                                    tip.save();
                                    sender.sendMessage(STARTER + "fReset all interaction events of tip \"" + tip.getName() + "\"");
                                    return true;
                                case "clickevent":
                                    switch (args[3]) {
                                        case "setsuggestion":
                                            tip.setClickSuggest(buildArgs(args,4));
                                            tip.save();
                                            sender.sendMessage(STARTER + "fModified command suggestion of tip \"" + tip.getName() + "\"");
                                            return true;
                                        case "seturl":
                                            tip.setClickUrl(buildArgs(args,4));
                                            tip.save();
                                            sender.sendMessage(STARTER + "fModified link of tip \"" + tip.getName() + "\"");
                                            return true;
                                    }
                                    return false;
                                case "hoverevent":
                                    switch (args[3]) {
                                        case "settext":
                                            tip.setHoverText(buildArgs(args,4));
                                            tip.save();
                                            sender.sendMessage(STARTER + "fModified hover text of tip \"" + tip.getName() + "\"");
                                            return true;
                                    }
                                    return false;
                            }
                            return false;
                    }
                    return false;
            }
        } catch (Exception exception) {
            String message = STARTER + "cCommand error: ";
            if (exception instanceof ClassCastException) {
                message += "You must be a player!";
            } else if (exception instanceof IllegalArgumentException) {
                message += "Enum constants does not contain command argument!";
            } else if (exception instanceof NullPointerException) {
                message += "Command contains a null value!";
            } else if (exception instanceof IndexOutOfBoundsException) {
                message += "Not enough information was provided!";
            } else {
                message += exception.toString();
            }
            sender.sendMessage(message);
            sender.sendMessage(STARTER + "cCaused by: §7" + exception.getMessage());
        }


        return false;
    }


    private static String buildArgs(String[] args, int beginIndex) {
        StringBuilder builder = new StringBuilder();
        for (int i = beginIndex; i < args.length; i ++) builder.append(args[i]).append(" ");
        return String.valueOf(builder).trim();
    }
}