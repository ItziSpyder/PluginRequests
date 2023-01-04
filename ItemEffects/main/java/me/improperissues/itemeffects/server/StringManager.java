package me.improperissues.itemeffects.server;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class StringManager {

    public static String addStringEffects(String string) {
        return string.replaceAll("&","§");
    }

    public static List<String> addListEffects(String string) {
        String[] sections = string.split("->");
        List<String> list = new ArrayList<>();
        for (String section : sections) {
            list.add(section.trim());
        }
        return list;
    }

    public static TextComponent processLinks(String string) {
        String[] words = string.split(" ");
        TextComponent message = new TextComponent(string);
        for (String word : words) {
            if (word.contains("https://") && word.contains(".")) {
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,word));
                return message;
            }
        }
        return message;
    }
}
