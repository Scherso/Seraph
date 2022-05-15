package dev.salmon.seraph.api.exception;

import dev.salmon.seraph.util.ChatColor;
import dev.salmon.seraph.util.ChatUtils;

public class PlayerNullException extends Exception {
    public PlayerNullException() {
        ChatUtils.showChatMessage(ChatColor.RED + "Null player exception, try re-queue.");
    }
}
