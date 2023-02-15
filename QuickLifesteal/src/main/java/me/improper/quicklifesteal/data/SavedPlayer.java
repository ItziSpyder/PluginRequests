package me.improper.quicklifesteal.data;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a saved player, serializable
 */
public class SavedPlayer implements Serializable {

    private String name;
    private UUID uuid;

    /**
     * Constructs a saved player using the player's uuid and name
     * @param player
     */
    public SavedPlayer(Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
    }

    /**
     * Constructs a saved player using the player's uuid and name
     * @param player
     */
    public SavedPlayer(OfflinePlayer player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
    }

    /**
     * Returns the player that this SavedPlayer is representing
     * @return the player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    /**
     * Returns the offline player that this SavedPlayer is representing
     * @return the offline player
     */
    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
