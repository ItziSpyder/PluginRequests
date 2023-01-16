package me.improper.ogredupealias.data;

import me.improper.ogredupealias.OgredupeAlias;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {

    private static FileConfiguration CONFIG = OgredupeAlias.getInstance().getConfig();

    /**
     * The plugin prefix, this is used in many server messages
     * for the plugin.
     *
     * @return The plugin prefix
     */
    public static String getPluginPrefix() {
        return CONFIG.getString("config.plugin.prefix");
    }

    /**
     * Checks if plugin specific commands are accessible or not.
     *
     * @return Whether plugin specific commands are enabled or not
     */
    public static boolean allowPluginSpecificCommands() {
        return CONFIG.getBoolean("config.plugin.specific-commands");
    }

    /**
     * Checks if server operators should bypass all the plugin
     * restrictions.
     *
     * @return Whether operators can bypass
     */
    public static boolean allowOpBypass() {
        return CONFIG.getBoolean("config.plugin.op-bypass-plugin-restrictions");
    }

    /**
     * Checks if chat spamming is allowed on the server.
     *
     * @return Whether chat spamming is allowed
     */
    public static boolean allowChatSpam() {
        return !CONFIG.getBoolean("config.chat.anti-spam");
    }

    /**
     * Returns the set chat cooldown.
     *
     * @return The chat cooldown
     */
    public static int getChatCooldown() {
        return CONFIG.getInt("config.chat.chat-cooldown");
    }

    /**
     * Checks if swearing is allowed on the server.
     *
     * @return Whether swearing in chat is allowed or not
     */
    public static boolean allowChatSwear() {
        return !CONFIG.getBoolean("config.chat.anti-swear");
    }

    /**
     * Checks if command spamming is allowed on the server.
     *
     * @return Whether command spam is allowed or not
     */
    public static boolean allowCommandSpam() {
        return !CONFIG.getBoolean("config.chat.anti-command-spam");
    }

    /**
     * Returns the permission required to be able to see
     * anti-swear chat messages.
     *
     * @return The permission required as a string
     */
    public static String getAntiSwearNotifyPermission() {
        return CONFIG.getString("config.chat.anti-swear-notify-permission");
    }

    /**
     * Returns a list of all the blacklisted words or phrases.
     *
     * @return Chat blacklist
     */
    public static List<String> getChatBlacklist() {
        return CONFIG.getStringList("config.chat.blacklist");
    }

    /**
     * Returns a list of all the whitelisted words or phrases.
     * These will override the blacklisted words and the plugin
     * will not check for profanity.
     *
     * @return Chat blacklist overrides (Whitelist)
     */
    public static List<String> getChatBlacklistOverrides() {
        return CONFIG.getStringList("config.chat.blacklist-override");
    }

    /**
     * Returns the set command cooldown
     *
     * @return Chat command cooldown
     */
    public static int getCommandCooldown() {
        return CONFIG.getInt("config.chat.command-cooldown");
    }

    /**
     * Returns a list of commands that should have command cooldown.
     *
     * @return Command cooldown list
     */
    public static List<String> getCommandCooldownList() {
        return CONFIG.getStringList("config.chat.command-cooldown-list");
    }


    /**
     * Returns the message that'll be sent to a player if they attempt to
     * bypass the anti swear.
     *
     * @return The message
     */
    public static String getSwearMessage() {
        return CONFIG.getString("config.messages.swear");
    }

    /**
     * Returns the message that'll be sent to a player if they attempt to
     * bypass the anti-spam.
     *
     * @return The message
     */
    public static String getSpamMessage() {
        return CONFIG.getString("config.messages.spam");
    }

    /**
     * Returns the message that'll be sent to a player if they attempt to
     * repeat the same message.
     *
     * @return The message
     */
    public static String getRepeatMessage() {
        return CONFIG.getString("config.messages.repeat");
    }

    /**
     * Returns the message that'll be sent to a player if they attempt to
     * run a plugin-specific command.
     *
     * @return The message
     */
    public static String getPluginSpecificMessage() {
        return CONFIG.getString("config.messages.plugin-specific");
    }
}
