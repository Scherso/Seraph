package dev.salmon.seraph.util;

import gg.essential.universal.ChatColor;

public class DuelsUtils {
    public DuelsUtils() {

    }

    public static ChatColor getWinsColor(int wins) {
        if (wins >= 200000) {
            return ChatColor.GOLD;
        } else if (wins >= 100000) {
            return ChatColor.LIGHT_PURPLE;
        } else if (wins >= 50000) {
            return ChatColor.AQUA;
        } else if (wins >= 20000) {
            return ChatColor.DARK_PURPLE;
        } else if (wins >= 10000) {
            return ChatColor.YELLOW;
        } else if (wins >= 4000) {
            return ChatColor.DARK_RED;
        } else if (wins >= 2000) {
            return ChatColor.DARK_GREEN;
        } else if (wins >= 1000) {
            return ChatColor.DARK_AQUA;
        } else if (wins >= 500) {
            return ChatColor.GOLD;
        } else if (wins >= 200) {
            return ChatColor.WHITE;
        } else {
            return ChatColor.GRAY;
        }
    }

    public static ChatColor getWlrColor(double wlr) {
        if (wlr < 2) {
            return ChatColor.GRAY;
        } else if (wlr < 4) {
            return ChatColor.WHITE;
        } else if (wlr < 6) {
            return ChatColor.GOLD;
        } else if (wlr < 7) {
            return ChatColor.DARK_GREEN;
        } else if (wlr < 10) {
            return ChatColor.RED;
        } else if (wlr < 15) {
            return ChatColor.DARK_RED;
        } else if (wlr < 50) {
            return ChatColor.LIGHT_PURPLE;
        } else {
            return ChatColor.DARK_PURPLE;
        }
    }
}