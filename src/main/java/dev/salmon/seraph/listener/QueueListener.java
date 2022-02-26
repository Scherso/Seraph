package dev.salmon.seraph.listener;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.locraw.LocrawInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class QueueListener {
    public boolean hasQueued;

    LocrawInfo locrawInfo = Seraph.getInstance().getLocrawInfo();

    // this doesn't work i'm just commiting it
    public void sendMessageOnQueue() {

        hasQueued = (locrawInfo.getGameType().name().equals("DUELS") && locrawInfo.getMapName() != null && locrawInfo.getGameMode() != null);

        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("you have queued."));
    }

}