package dev.salmon.seraph.api.exception;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.chat.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class BadJsonException extends Exception {

    public BadJsonException() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.RED + "Hypixel API returned an bad json, Hypixel API may be down. If it is not, please contact a developer about this."));
    }

}
