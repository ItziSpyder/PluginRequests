package me.improper.quicklifesteal.commands;

import me.improper.quicklifesteal.QuickLifesteal;
import me.improper.quicklifesteal.entity.player.HeartsData;
import me.improper.quicklifesteal.exceptions.CmdExHandler;
import me.improper.quicklifesteal.server.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Command executor
 */
public class CommandLsReset implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player p;
            if (args.length == 0) p = (Player) sender;
            else p = Bukkit.getPlayer(args[0]);
            HeartsData data = new HeartsData(p);
            data.update();
            data.save();
            sender.sendMessage("\n" + QuickLifesteal.starter + "§bYou've reset the heart data of §7" + p.getName() + "\n ");
            p.sendMessage("\n" + QuickLifesteal.starter + "§bYour hearts data has been reset by §7" + sender.getName() + "\n ");
        } catch (Exception ex) {
            CmdExHandler handler = new CmdExHandler(ex,command);
            sender.sendMessage(handler.getErrorMsg());
        }
        return true;
    }

    /**
     * Command tab completer
     */
    public static class Tabs implements TabCompleter {

        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
            List<String> list = new ArrayList<>();
            switch (args.length) {
                case 1 -> list.addAll(ServerUtils.listPlayers());
            }
            list.removeIf(s -> !s.toLowerCase().contains(args[args.length - 1].toLowerCase()));
            return list;
        }
    }
}
