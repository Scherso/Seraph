package dev.salmon.seraph.util;

import net.minecraft.client.Minecraft;

public class Utils {

    public static boolean isHypixel() {
        if (Minecraft.getMinecraft().theWorld != null && !Minecraft.getMinecraft().isSingleplayer()) {
            return Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
        }
        return false;
    }

}
