package me.improper.ogredupealias.data;

import me.improper.ogredupealias.OgredupeAlias;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class ChatLogger {

    public static File LOGFILE = new File("plugins/OgredupeAlias/chatlog/" + OgredupeAlias.STARTTIME + ".log");
    public static String LOG = "";

    public static void writeLog(CommandSender sender, String message) {
        try {
            if (!LOGFILE.getParentFile().exists()) LOGFILE.getParentFile().mkdirs();
            if (!LOGFILE.exists()) LOGFILE.createNewFile();
            FileWriter fw = new FileWriter(LOGFILE);
            BufferedWriter bw = new BufferedWriter(fw);
            LOG += "\n" + sender.getName() + ": \"" + message + "\"";
            bw.write(LOG);
            bw.close();
        } catch (Exception exception) {
            Bukkit.getLogger().warning(exception.toString());
        }
    }

    public static void start() {
        LocalDateTime now = LocalDateTime.now();
        ChatLogger.writeLog(Bukkit.getConsoleSender(), "\n \n========================"
                + "\nSever has started!"
                + "\nChat will now start logging."
                + "\nCurrent time: " + now
                + "\n========================\n \n ");
    }

    public static void end() {
        LocalDateTime now = LocalDateTime.now();
        ChatLogger.writeLog(Bukkit.getConsoleSender(), "\n \nXXXXXXXXXXXXXXXXXXXXXXXX"
                + "\nSever has stopped!"
                + "\nChat logging has stopped."
                + "\nCurrent time: " + now
                + "\nClosing file stream..."
                + "\nXXXXXXXXXXXXXXXXXXXXXXXX\n \n ");
    }
}
