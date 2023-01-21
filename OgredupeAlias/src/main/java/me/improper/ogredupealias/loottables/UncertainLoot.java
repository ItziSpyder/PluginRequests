package me.improper.ogredupealias.loottables;

import me.improper.ogredupealias.items.CustomItem;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class UncertainLoot {

    private List<CustomItem> entries;
    private int iterations;

    public UncertainLoot() {
        this.iterations = 0;
        this.entries = new ArrayList<>();
    }

    public void spawn(Location location) {
        location = location.getBlock().getLocation().add(0.5,0,0.5);
        for (int i = 0; i < iterations; i ++) {
            try {
                int random = (int) Math.floor(Math.random() * entries.size());
                CustomItem item = entries.get(random);
                item.drop(location);
            } catch (Exception exception) {}
        }
    }

    public void addItem(CustomItem item) {
        entries.add(item);
    }

    public void removeItem(int index) {
        entries.remove(index);
    }

    public void removeItem(CustomItem item) {
        entries.remove(item);
    }

    public List<CustomItem> getEntries() {
        return entries;
    }

    public int getIterations() {
        return iterations;
    }

    public void setEntries(List<CustomItem> entries) {
        this.entries = entries;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
