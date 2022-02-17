package dev.salmon.seraph.listener;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class QueueListener {
    public boolean hasQueued;
    private int tick;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        if (tick % 20 == 0) {
            if (Minecraft.getMinecraft().theWorld != null) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/locraw");
            }
            tick = 0;
        }

        tick++;
        if (!Minecraft.getMinecraft().isGamePaused() && Minecraft.getMinecraft().thePlayer != null && hasQueued)
            this.onPlayerJoinTeam();
    }

    public void onPlayerJoinTeam() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("you queued i think O_O"));
    }

    @SubscribeEvent
    public void onPlayerQueue(ClientChatReceivedEvent event) {
        String umessage = event.message.getUnformattedText();
        if (umessage.startsWith("{\"server\":")) {
            event.setCanceled(true);
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse(umessage).getAsJsonObject();

            String gametype = object.get("gametype").getAsString();

            hasQueued = object.get("mode") != null && object.get("map") != null && gametype.equalsIgnoreCase("DUELS");
         }
    }
}