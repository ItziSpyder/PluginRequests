package me.improperissues.coffeecup.files;

import me.improperissues.coffeecup.events.SpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpawnControl {

    private static File file;
    private static FileConfiguration data;

    public static void setUp() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("CoffeeCup").getDataFolder(),"options/spawncontrol.yml");

        try {
            if (file.exists()) {
                file.createNewFile();
            }
        } catch (IOException exception) {
            // empty
        }

        data = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection section = data.getConfigurationSection("server.options");
        if (section == null) {
            for (EntityType type : EntityType.class.getEnumConstants()) {
                data.set("server.options.types." + type.name(),true);
            }
            data.set("server.options.groups.#COMMON",true);
            data.set("server.options.groups.#NON-LIVING",true);
            data.set("server.options.groups.#LIVING",true);
            data.set("server.options.groups.#MONSTER",true);
            data.set("server.options.groups.#PASSIVE",true);
            data.set("server.options.groups.#NAMED",true);
            save();
        }
    }

    public static void save() {
        try {
            data.save(file);
        } catch (IOException exception) {
            // empty
        }
    }

    public static FileConfiguration get() {
        return data;
    }

    public static void reload() {
        data = YamlConfiguration.loadConfiguration(file);
    }

    public static boolean canSpawn(Entity entity) {
        if (!data.getBoolean("server.options.types." + entity.getType().name())) {
            return false;
        }
        if (SpawnEvent.isCommon(entity) && !data.getBoolean("server.options.groups.#COMMON")) {
            return false;
        }
        if (SpawnEvent.isLiving(entity) && !data.getBoolean("server.options.groups.#LIVING")) {
            return false;
        }
        if (SpawnEvent.isNonLiving(entity) && !data.getBoolean("server.options.groups.#NON-LIVING")) {
            return false;
        }
        if (SpawnEvent.isMonster(entity) && !data.getBoolean("server.options.groups.#MONSTER")) {
            return false;
        }
        if (SpawnEvent.isPassive(entity) && !data.getBoolean("server.options.groups.#PASSIVE")) {
            return false;
        }
        if (SpawnEvent.isNamed(entity) && !data.getBoolean("server.options.groups.#NAMED")) {
            return false;
        }

        return true;
    }

    public static List<String> allGroups() {
        List<String> list = new ArrayList<>();
        for (EntityType type : EntityType.class.getEnumConstants()) {
            list.add(type.name().toLowerCase());
        }
        list.remove("player");
        list.add("#ALL");
        list.add("#MONSTER");
        list.add("#PASSIVE");
        list.add("#NON-LIVING");
        list.add("#LIVING");
        list.add("#COMMON");
        list.add("#NAMED");
        return list;
    }

    public static List<Entity> getServerEntities() {
        List<Entity> list = new ArrayList<>();
        for (World world : Bukkit.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (!(entity instanceof Player)) {
                    list.add(entity);
                }
            }
        }
        return list;
    }
}
