package me.improper.ogredupealias.data;

import me.improper.ogredupealias.OgredupeAlias;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {

    private static FileConfiguration CONFIG = OgredupeAlias.getInstance().getConfig();

    public class PLUGIN {
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
         * Returns the permission required to be able to see
         * anti-swear chat messages.
         *
         * @return The permission required as a string
         */
        public static String getAntiSwearNotifyPermission() {
            return CONFIG.getString("config.chat.anti-swear-notify-permission");
        }
    }

    public class VALUES {
        /**
         * Returns the set command cooldown
         *
         * @return Chat command cooldown
         */
        public static int getCommandCooldown() {
            return CONFIG.getInt("config.chat.command-cooldown");
        }

        /**
         * Returns the set chat cooldown.
         *
         * @return The chat cooldown
         */
        public static int getChatCooldown() {
            return CONFIG.getInt("config.chat.chat-cooldown");
        }
    }



    public class BOOLEANS {
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
         * Checks if chat unicode is allowed on the server.
         *
         * @return Whether unicode are allowed
         */
        public static boolean allowUnicode() {
            return !CONFIG.getBoolean("config.chat.anti-unicode");
        }

        /**
         * Checks if sieving with scaffolding is allowed on the server.
         *
         * @return Whether sieving is allowed
         */
        public static boolean allowSieving() {
            return CONFIG.getBoolean("config.custom.sieving");
        }

        /**
         * Checks if naked killing is allowed on the server.
         *
         * @return Whether naked killing is allowed
         */
        public static boolean allowNakedKill() {
            return !CONFIG.getBoolean("config.combat.anti-nakedkill");
        }
    }

    public class MESSAGES {
        /**
         * Returns the message that'll be sent to a player if they attempt to
         * bypass the anti swear.
         *
         * @return The message
         */
        public static String getSwear() {
            return CONFIG.getString("config.messages.swear");
        }

        /**
         * Returns the message that'll be sent to a player if they attempt to
         * bypass the anti-spam.
         *
         * @return The message
         */
        public static String getSpam() {
            return CONFIG.getString("config.messages.spam");
        }

        /**
         * Returns the message that'll be sent to a player if they attempt to
         * repeat the same message.
         *
         * @return The message
         */
        public static String getRepeat() {
            return CONFIG.getString("config.messages.repeat");
        }

        /**
         * Returns the message that'll be sent to a player if they attempt to
         * run a plugin-specific command.
         *
         * @return The message
         */
        public static String getPluginSpecific() {
            return CONFIG.getString("config.messages.plugin-specific");
        }

        /**
         * Returns the message that'll be sent to a player if they attempt to
         * send a message in chat while chat is disabled.
         *
         * @return The message
         */
        public static String getChatDisabled() {
            return CONFIG.getString("config.messages.disabled");
        }

        /**
         * Returns the player join message.
         *
         * @return The message
         */
        public static String getJoinMessage() {
            return CONFIG.getString("config.messages.player-join");
        }

        /**
         * Returns the player first join message.
         *
         * @return The message
         */
        public static String getFirstJoinMessage() {
            return CONFIG.getString("config.messages.player-firstjoin");
        }

        /**
         * Returns the player quit message.
         *
         * @return The message
         */
        public static String getQuitMessage() {
            return CONFIG.getString("config.messages.player-quit");
        }

        /**
         * Returns the unicode message.
         *
         * @return The message
         */
        public static String getUnicodeMessage() {
            return CONFIG.getString("config.messages.unicode");
        }
    }

    public class LISTS {
        /**
         * Returns a list of commands blacklisted by command spy.
         *
         * @return The blacklist for command spy
         */
        public static List<String> getCommandSpyBlacklist() {
            return CONFIG.getStringList("config.socialspy.commandspy-blacklist");
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
    }


}
