package dev.salmon.seraph.playerapi.exception;

import dev.salmon.seraph.util.References;
import gg.essential.universal.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class InvalidKeyException extends Exception {

    public InvalidKeyException() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(References.SERAPHPREFIX + ChatColor.RED + "It seems your API key is empty or invalid. Please run " + ChatColor.BOLD + "/api new"));
    }

}
