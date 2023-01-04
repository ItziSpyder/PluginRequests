package me.improperissues.playerblocks.data;

import org.bukkit.Bukkit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegionFile {

    public static File[] listFiles() {
        File file = new File("plugins/PlayerBlocks/blockregions");
        if (file.listFiles() == null) return new File[0];
        return file.listFiles();
    }

    public static List<String> listNames() {
        List<String> list = new ArrayList<>();
        for (File file : listFiles()) {
            if (file.getName().contains(".blocks"))
                list.add(file.getName().replaceAll(".blocks",""));
        }
        return list;
    }

    public static void save(BlockRegion region) {
        try {
            FileOutputStream fos = new FileOutputStream(region.getFile());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(region);
            oos.close();
        } catch (Exception exception) {
            Bukkit.getLogger().warning("Could not save file for " + region + "!");
        }
    }

    public static BlockRegion load(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            BlockRegion region = (BlockRegion) ois.readObject();
            ois.close();
            return region;
        } catch (Exception exception) {
            Bukkit.getLogger().warning("Could not load region from " + file.getPath() + "!");
        }
        return null;
    }
}
