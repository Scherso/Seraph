package dev.salmon.seraph.api.exception;

import gg.essential.universal.ChatColor;
import dev.salmon.seraph.util.ChatUtils;

public class PlayerNullException extends Exception {
    public PlayerNullException() {
        ChatUtils.error("Null player exception, try re-queue.");
    }
}
