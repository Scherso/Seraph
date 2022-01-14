package dev.salmon.seraph.playerapi.exception;

import dev.salmon.seraph.util.References;
import gg.essential.universal.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class BadJsonException extends Exception {

    public BadJsonException() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(References.SERAPHPREFIX + ChatColor.GRAY + "Hypixel API returned an bad json, Hypixel API may be down. If it is not, please contact a developer about this."));
    }

}
