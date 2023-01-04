package me.improperissues.servertips.data;

import me.improperissues.servertips.ServerTips;
import me.improperissues.servertips.other.ServerSound;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tip {

    private String name;
    private String message;
    private String hoverText;
    private String clickUrl;
    private String clickSuggest;
    private String sound;
    private float volume;
    private float pitch;


    public static Tip load(String tipName) {
        File file = getParentFile();
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        String name = data.getString("servertips." + tipName + ".name");
        String message = data.getString("servertips." + tipName + ".message");
        String sound = data.getString("servertips." + tipName + ".sound");
        String hoverText = data.getString("servertips." + tipName + ".hoverText");
        String clickSuggest = data.getString("servertips." + tipName + ".clickSuggest");
        String clickUrl = data.getString("servertips." + tipName + ".clickUrl");
        float volume = (float) data.getDouble("servertips." + tipName + ".volume");
        float pitch = (float) data.getDouble("servertips." + tipName + ".pitch");
        return new Tip(name,message,sound,volume,pitch,hoverText,clickSuggest,clickUrl);
    }

    public static List<String> listTips() {
        File file = getParentFile();
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection section = data.getConfigurationSection("servertips");
        if (section == null) return new ArrayList<>();
        return new ArrayList<>(section.getKeys(false));
    }

    public static File getParentFile() {
        File file = new File("plugins/ServerTips/tips/tips.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                Bukkit.getServer().getLogger().warning(exception.toString());
            }
        }
        return file;
    }

    public static void saveParentFile(FileConfiguration data) {
        File file = new File("plugins/ServerTips/tips/tips.yml");
        try {
            data.save(file);
        } catch (IOException exception) {
            Bukkit.getServer().getLogger().warning(exception.toString());
        }
    }

    public Tip(String name, String message) {
        this.name = name;
        this.message = message;
        this.sound = "ENTITY_EXPERIENCE_ORB_PICKUP";
        this.volume = 10.0F;
        this.pitch = 10.0F;
    }

    public Tip(String name, String message, String sound, float volume, float pitch) {
        this.name = name;
        this.message = message;
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    public Tip(String name,String message,String sound,float volume,float pitch,String hoverText,String clickSuggest,String clickUrl) {
        this.name = name;
        this.message = message;
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        this.hoverText = hoverText;
        this.clickSuggest = clickSuggest;
        this.clickUrl = clickUrl;
    }

    public void send(Player player) {
        player.spigot().sendMessage(this.toMessage());
    }

    public void broadcast() {
        Bukkit.getServer().spigot().broadcast(this.toMessage());
    }

    public TextComponent toMessage() {
        TextComponent message = new TextComponent("\n\n" + ServerTips.STARTER + "7" + this.message.replaceAll("&","§") + "\n\n");
        if (this.hoverText != null) message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,TextComponent.fromLegacyText(this.hoverText.replaceAll("&","§"))));
        if (this.clickUrl != null) message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,this.clickUrl));
        if (this.clickSuggest != null) message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,this.clickSuggest));
        return message;
    }

    public ServerSound toSound() {
        return new ServerSound(null,Sound.valueOf(this.sound),this.volume,this.pitch);
    }

    public void save() {
        File file = getParentFile();
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        data.set("servertips." + this.name + ".name",this.name);
        data.set("servertips." + this.name + ".sound",this.sound);
        data.set("servertips." + this.name + ".message",this.message);
        data.set("servertips." + this.name + ".clickSuggest",this.clickSuggest);
        data.set("servertips." + this.name + ".clickUrl",this.clickUrl);
        data.set("servertips." + this.name + ".hoverText",this.hoverText);
        data.set("servertips." + this.name + ".volume",this.volume);
        data.set("servertips." + this.name + ".pitch",this.pitch);
        saveParentFile(data);
    }

    public void delete() {
        File file = getParentFile();
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        data.set("servertips." + this.name,null);
        saveParentFile(data);
    }

    public void resetEvents() {
        this.clickUrl = null;
        this.clickSuggest = null;
        this.hoverText = null;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getHoverText() {
        return hoverText;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public String getClickSuggest() {
        return clickSuggest;
    }

    public String getSound() {
        return sound;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHoverText(String hoverText) {
        this.hoverText = hoverText;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public void setClickSuggest(String clickSuggest) {
        this.clickSuggest = clickSuggest;
    }
}
