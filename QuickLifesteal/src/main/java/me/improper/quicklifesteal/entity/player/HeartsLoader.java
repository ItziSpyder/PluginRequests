package me.improper.quicklifesteal.entity.player;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.*;

public class HeartsLoader {

    private static final File parentFolder = new File("plugins/QuickLifesteal/heartsdata");

    /**
     * Loads a HeartData object from a player
     * @param player the player
     * @return the heart data information
     */
    public static HeartsData load(OfflinePlayer player) {
        try {
            File file = new File(parentFolder,player.getUniqueId() + ".dat");
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            HeartsData data = (HeartsData) ois.readObject();
            ois.close();
            return data;
        } catch (Exception ex) {
            Bukkit.getLogger().warning(ex.getMessage());
            HeartsData data = new HeartsData(player);
            data.save();
            return data;
        }
    }

    /**
     * Loads a HeartData object from a player
     * @param player the player
     * @return the heart data information
     */
    public static HeartsData load(Player player) {
        try {
            File file = new File(parentFolder,player.getUniqueId() + ".dat");
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            HeartsData data = (HeartsData) ois.readObject();
            ois.close();
            return data;
        } catch (Exception ex) {
            Bukkit.getLogger().warning(ex.getMessage());
            HeartsData data = new HeartsData(player);
            data.save();
            return data;
        }
    }

    /**
     * Saves a heart data object to a file
     * @param data the heart data to save
     */
    public static void save(HeartsData data) {
        try {
            Player player = data.getPlayer();
            File file = new File(parentFolder,player.getUniqueId() + ".dat");
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            if (!file.exists()) file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(data);
            oos.close();
        } catch (Exception ex) {
            Bukkit.getLogger().warning(ex.getMessage());
        }
    }
}
