package dev.salmon.seraph.util;

import gg.essential.universal.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ChatUtils {
    public static final String CHAT_PREFIX = ChatColor.GOLD + "Seraph" + ChatColor.DARK_GRAY + " » " + ChatColor.RESET;

    public static void show(IChatComponent message, boolean prefix) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            if (player == null) return; // Player is null, so they cannot receive messages.
            player.addChatMessage(prefix ? new ChatComponentText(CHAT_PREFIX).appendSibling(message) : message);
        });
    }

    public static void show(String message, boolean prefix) {
        show(new ChatComponentText(message), prefix);
    }

    public static void show(IChatComponent message) {
        show(message, true);
    }

    public static void show(String message) {
        show(new ChatComponentText(message));
    }

    public static void info(IChatComponent message) {
        show(new ChatComponentText(ChatColor.GRAY.toString()).appendSibling(message));
    }

    public static void info(String message) {
        show(ChatColor.GRAY + message);
    }

    public static void success(IChatComponent message) {
        show(new ChatComponentText(ChatColor.GREEN.toString()).appendSibling(message));
    }

    public static void success(String message) {
        show(ChatColor.GREEN + message);
    }

    public static void error(IChatComponent message) {
        show(new ChatComponentText(ChatColor.RED.toString()).appendSibling(message));
    }

    public static void error(String message) {
        show(ChatColor.RED + message);
    }
}
