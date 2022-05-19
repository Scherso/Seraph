package dev.salmon.seraph.api.exception;

import dev.salmon.seraph.util.ChatUtils;
import gg.essential.universal.ChatColor;

public class InvalidKeyException extends Exception {
    public InvalidKeyException() {
        ChatUtils.error("It seems your API key is empty or invalid. Please run " + ChatColor.BOLD + "/api new");
    }
}
