package dev.salmon.seraph.api.exception;

import gg.essential.universal.ChatColor;
import dev.salmon.seraph.util.ChatUtils;

public class InvalidKeyException extends Exception {
    public InvalidKeyException() {
        ChatUtils.error("It seems your API key is empty or invalid. Please run " + ChatColor.BOLD + "/api new");
    }
}
