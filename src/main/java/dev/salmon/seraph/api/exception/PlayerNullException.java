package dev.salmon.seraph.api.exception;

import dev.salmon.seraph.Seraph;
import gg.essential.universal.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class PlayerNullException extends Exception {

    public PlayerNullException() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Seraph.Prefix.SeraphPrefix + ChatColor.RED + "Null player exception, try re-queue."));
    }

}
