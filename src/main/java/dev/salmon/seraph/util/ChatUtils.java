package dev.salmon.seraph.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ChatUtils {
    public static final String CHAT_PREFIX = ChatColor.GOLD + "Seraph" + ChatColor.DARK_GRAY + " » " + ChatColor.RESET;

    public static void showChatMessage(IChatComponent message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            if (player == null) return; // Player is null, so they cannot receive messages.
            player.addChatMessage(new ChatComponentText(CHAT_PREFIX).appendSibling(message));
        });
    }

    public static void showChatMessage(String message) {
        showChatMessage(new ChatComponentText(message));
    }
}
