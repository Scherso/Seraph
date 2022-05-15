package dev.salmon.seraph.api.exception;

import gg.essential.universal.ChatColor;
import dev.salmon.seraph.util.ChatUtils;

public class BadJsonException extends Exception {
    public BadJsonException() {
        ChatUtils.error("Hypixel API returned an bad json, Hypixel API may be down. If it is not, please contact a developer about this.");
    }
}
