package me.improper.quicklifesteal.events;

import me.improper.quicklifesteal.QuickLifesteal;
import me.improper.quicklifesteal.data.Config;
import me.improper.quicklifesteal.entity.player.HeartsData;
import me.improper.quicklifesteal.entity.player.HeartsLoader;
import me.improper.quicklifesteal.server.Items;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeath implements Listener {

    @EventHandler
    public static void onDeath(PlayerDeathEvent e) {
        try {
            Player p = e.getEntity().getPlayer();
            Player k = e.getEntity().getKiller();

            if (k == null) return;
            HeartsData data = HeartsLoader.load(p);
            if (data.getMaxHearts() - 1 <= 0) {
                Config.Gameplay.on_eliminate.forEach(cmd -> {
                    cmd = cmd.replaceAll("%player%",p.getName()).replaceAll("%prefix%",QuickLifesteal.starter);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),cmd);
                });
                return;
            }
            else {
                data.setMaxHearts(data.getMaxHearts() - 1);
                data.update();
                data.save();

                // display the titles for the players
                String[] titles = Config.Messages.death_title_message.split("->");
                p.sendTitle(titles[0].trim(),titles[1].trim(),10,100,10);
                titles = Config.Messages.kill_title_message.split("->");
                k.sendTitle(titles[0].trim(),titles[1].trim(),10,100,10);

                // add heart to killer
                HeartsData kData = HeartsLoader.load(k);
                if (kData.getMaxHearts() + 1 > Config.Gameplay.max_hearts) {
                    k.getWorld().dropItemNaturally(p.getLocation(),Items.HEART);
                    k.sendMessage(QuickLifesteal.starter + "§cYou have already reached max heart capacity!");
                    k.sendMessage(QuickLifesteal.starter + "§cThe heart is dropped at the victim's death location!");
                }
                else {
                    kData.setMaxHearts(kData.getMaxHearts() + 1);
                    kData.update();
                    kData.save();
                }
            }
        } catch (Exception ex) {}
    }
}
