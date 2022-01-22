package dev.salmon.seraph.playerapi.exception;

import dev.salmon.seraph.util.References;
import gg.essential.universal.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class PlayerNullException extends Exception {

    public PlayerNullException() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(References.SERAPHPREFIX + ChatColor.RED + "Null player exception, try re-queue."));
    }

}
