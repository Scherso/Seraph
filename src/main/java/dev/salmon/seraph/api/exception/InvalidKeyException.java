package dev.salmon.seraph.api.exception;

import dev.salmon.seraph.Seraph;
import gg.essential.universal.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class InvalidKeyException extends Exception {

    public InvalidKeyException() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.RED + "It seems your API key is empty or invalid. Please run " + ChatColor.BOLD + "/api new"));
    }

}
