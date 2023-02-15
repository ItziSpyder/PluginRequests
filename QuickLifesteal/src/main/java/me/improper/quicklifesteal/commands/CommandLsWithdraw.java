package me.improper.quicklifesteal.commands;

import me.improper.quicklifesteal.QuickLifesteal;
import me.improper.quicklifesteal.entity.player.HeartsData;
import me.improper.quicklifesteal.entity.player.HeartsLoader;
import me.improper.quicklifesteal.exceptions.CmdExHandler;
import me.improper.quicklifesteal.server.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Command executor
 */
public class CommandLsWithdraw implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player p = (Player) sender;
            int amount = Integer.parseInt(args[0]);
            HeartsData data = HeartsLoader.load(p);
            if (p.getInventory().firstEmpty() == -1) throw new IllegalArgumentException("You do not have enough inventory space!");
            if (data.getMaxHearts() - amount <= 0) throw new IllegalArgumentException("You do not have enough hearts!");

            data.setMaxHearts(data.getMaxHearts() - amount);
            data.update();
            data.save();

            ItemStack hearts = new ItemStack(Items.HEART.clone());
            hearts.setAmount(amount);
            p.getInventory().addItem(hearts);
            p.sendMessage(QuickLifesteal.starter + "§bYou've withdrawn §7" + amount + " §bhearts!");
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
                case 1 -> list.add("§7<amount>");
            }
            list.removeIf(s -> !s.toLowerCase().contains(args[args.length - 1].toLowerCase()));
            return list;
        }
    }
}
