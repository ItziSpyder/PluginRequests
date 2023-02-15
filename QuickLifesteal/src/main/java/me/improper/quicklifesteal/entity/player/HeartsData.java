package me.improper.quicklifesteal.entity.player;

import me.improper.quicklifesteal.data.SavedPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.Serializable;

public class HeartsData implements Serializable {

    public int maxHearts;
    public SavedPlayer player;

    /**
     * Constructs a new heart data object from a player.
     * @param player the player
     * @throws IllegalArgumentException if player is null
     */
    public HeartsData(Player player) {
        if (player == null) throw new IllegalArgumentException("Player cannot be null!");
        this.maxHearts = 10;
        this.player = new SavedPlayer(player);
    }

    /**
     * Constructs a new heart data object from a player.
     * @param player the player
     * @throws IllegalArgumentException if player is null
     */
    public HeartsData(OfflinePlayer player) {
        if (player == null) throw new IllegalArgumentException("Player cannot be null!");
        this.maxHearts = 10;
        this.player = new SavedPlayer(player);
    }

    /**
     * Updates the player's max health to the current max health
     */
    public void update() {
        Player p = getPlayer();
        p.setMaxHealth(maxHearts * 2);
        p.damage(0);
    }

    /**
     * Saves the current data into the data file
     */
    public void save() {
        HeartsLoader.save(this);
    }

    public int getMaxHearts() {
        return maxHearts;
    }

    public Player getPlayer() {
        return player.getPlayer();
    }

    public OfflinePlayer getOfflinePlayer() {
        return player.getOfflinePlayer();
    }

    public void setMaxHearts(int maxHearts) {
        this.maxHearts = maxHearts;
    }

    public void setPlayer(Player player) {
        this.player = new SavedPlayer(player);
    }
}
