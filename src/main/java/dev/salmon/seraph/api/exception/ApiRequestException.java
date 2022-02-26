package dev.salmon.seraph.api.exception;

import dev.salmon.seraph.Seraph;
import gg.essential.universal.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ApiRequestException extends Exception {

    public ApiRequestException() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.RED + "There was an API request exception. If this persists, contact a developer."));
    }

}
