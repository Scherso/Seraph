package dev.salmon.seraph.util;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.locraw.LocrawInfo;
import net.minecraft.client.Minecraft;

public class HypixelUtil {

    public static boolean isHypixel() {
        if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().theWorld != null && !Minecraft.getMinecraft().isSingleplayer()) {
            return Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
        }
        return false;
    }

    public static boolean checkForDuelsQueue() {
        if (Minecraft.getMinecraft().theWorld != null && isHypixel()) {
            LocrawInfo locrawInfo = Seraph.Instance.getLocrawUtil().getLocrawInfo();
            if (Seraph.Instance.getLocrawUtil().isInGame() && locrawInfo.getGameType() == LocrawInfo.GameType.DUELS && locrawInfo.getGameMode() != null) {
                return true;
            }
        }
        return false;
    }

}
