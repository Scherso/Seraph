package dev.salmon.seraph.api.exception;

import dev.salmon.seraph.util.ChatColor;
import dev.salmon.seraph.util.ChatUtils;

public class InvalidKeyException extends Exception {
    public InvalidKeyException() {
        ChatUtils.showChatMessage(ChatColor.RED + "It seems your API key is empty or invalid. Please run " + ChatColor.BOLD + "/api new");
    }
}
