package me.improperissues.itemeffects.events;

import me.improperissues.itemeffects.ItemEffects;
import org.bukkit.event.Listener;

import java.util.List;

public class PluginConfig implements Listener {
    public static ItemEffects plugin;
    public PluginConfig(ItemEffects plugin) {
        PluginConfig.plugin = plugin;
    }

    public static boolean isJoinMessageEnabled() {
        return plugin.getConfig().getBoolean("join_message_options.custom_join_messages");
    }

    public static boolean isAdditionalMessageEnabled() {
        return plugin.getConfig().getBoolean("join_message_options.additional_messages");
    }

    public static boolean isLeaveMessageEnabled() {
        return plugin.getConfig().getBoolean("join_message_options.custom_leave_messages");
    }

    public static String getFirstJoinMessage() {
        return plugin.getConfig().getString("join_message_options.custom_first_join");
    }

    public static String getJoinMessage() {
        return plugin.getConfig().getString("join_message_options.custom_join_message");
    }

    public static String getLeaveMessage() {
        return plugin.getConfig().getString("join_message_options.custom_leave_message");
    }

    public static List<String> getAdditionalMessage() {
        return plugin.getConfig().getStringList("join_message_options.additional_message");
    }
}
