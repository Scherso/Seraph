package dev.salmon.seraph.playerapi.exception;

import dev.salmon.seraph.util.References;
import gg.essential.universal.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ApiRequestException extends Exception {

    public ApiRequestException() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(References.SERAPHPREFIX + ChatColor.GRAY + "There was an API request exception. If this persists, contact a developer."));
    }

}
