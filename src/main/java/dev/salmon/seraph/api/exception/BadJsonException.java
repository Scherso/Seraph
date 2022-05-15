package dev.salmon.seraph.api.exception;

import dev.salmon.seraph.util.ChatColor;
import dev.salmon.seraph.util.ChatUtils;

public class BadJsonException extends Exception {
    public BadJsonException() {
        ChatUtils.showChatMessage(ChatColor.RED + "Hypixel API returned an bad json, Hypixel API may be down. If it is not, please contact a developer about this.");
    }
}
