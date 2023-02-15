package me.improperissues.fireart.data;

import org.bukkit.Bukkit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SegmentFile {

    private static final File PARENTFILE = new File("fireartsegments");


    public static List<String> listFiles() {
        List<String> list = new ArrayList<>();
        File[] files = PARENTFILE.listFiles();
        if (files == null) return list;
        for (File file : files)
            if (file.getName().contains(".segment"))
                list.add(file.getName().replaceAll(".segment",""));
        return list;
    }

    public static void save(Segment segment) {
        try {
            File file = new File(PARENTFILE,segment.getName() + ".segment");
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            if (!file.exists()) file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(segment);
            oos.close();
        } catch (Exception exception) {
            Bukkit.getLogger().warning(exception.toString());
        }
    }

    public static Segment load(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Segment segment = (Segment) ois.readObject();
            ois.close();
            return segment;
        } catch (Exception exception) {
            Bukkit.getLogger().warning(exception.toString());
        }
        return null;
    }

    public static Segment load(String fileName) {
        try {
            File file = new File(PARENTFILE,fileName + ".segment");

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Segment segment = (Segment) ois.readObject();
            ois.close();
            return segment;
        } catch (Exception exception) {
            Bukkit.getLogger().warning(exception.toString());
        }
        return null;
    }

    public static void delete(String fileName) {
        try {
            File file = new File(PARENTFILE,fileName + ".segment");
            file.delete();
        } catch (Exception exception) {}
    }
}
