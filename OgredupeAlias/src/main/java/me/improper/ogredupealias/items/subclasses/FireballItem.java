package me.improper.ogredupealias.items.subclasses;

import me.improper.ogredupealias.items.CustomItem;
import me.improper.ogredupealias.other.ServerSound;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class FireballItem extends CustomItem {

    public FireballItem(ItemStack item) {
        super(item);
    }

    @Override
    public void onRightClick() {
        Player p = getWhoClicked();
        Location loc = p.getEyeLocation();
        Vector dir = p.getLocation().getDirection();
        ServerSound sound = new ServerSound(loc,Sound.ENTITY_BLAZE_SHOOT,1,1);
        Fireball fb = p.getWorld().spawn(loc,Fireball.class);
        fb.setShooter(p);
        fb.setYield(0);
        fb.setBounce(false);
        fb.setDirection(dir);
        fb.setVelocity(dir);
        sound.play(p);
    }
}
