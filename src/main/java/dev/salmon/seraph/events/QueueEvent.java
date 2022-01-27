package dev.salmon.seraph.events;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.salmon.seraph.util.handler.Handler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class QueueEvent {

    Minecraft mc = Minecraft.getMinecraft();
    private boolean ingame;
    private World world;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START || Minecraft.getMinecraft().theWorld == null) return;

        if (world != mc.theWorld) {
            this.world = mc.theWorld;
            mc.thePlayer.sendChatMessage("/locraw");
        }

        if (!mc.isGamePaused() && mc.thePlayer != null && ingame) {
            this.tick(); // it still ticks a couple more times than usual *every tick*
        }
    }

    private void tick() {
        Handler.asExecutor(() -> Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("you have queued")));
    }

    @SubscribeEvent
    public void onPlayerQueue(ClientChatReceivedEvent event) {
        String umsg = event.message.getUnformattedText();
        if (umsg.startsWith("{\"server\":")) {
            event.setCanceled(true);
            JsonParser jsonParser = new JsonParser();
            JsonObject obj = jsonParser.parse(umsg).getAsJsonObject();

            String gametype = obj.get("gametype").getAsString();

            this.ingame = obj.get("mode") != null && obj.get("map") != null && gametype.equalsIgnoreCase("DUELS");
        }
    }
}