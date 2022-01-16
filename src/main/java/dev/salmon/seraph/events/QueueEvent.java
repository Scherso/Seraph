package dev.salmon.seraph.events;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
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
    }

    @SubscribeEvent
    public void onPlayerQueue(ClientChatReceivedEvent event) {
        String msg = event.message.getFormattedText();
        String umsg = event.message.getUnformattedText();
        if (umsg.startsWith("{\"server\":")) {
            event.setCanceled(true);
            JsonParser jsonParser = new JsonParser();
            JsonObject obj = jsonParser.parse(umsg).getAsJsonObject();

//            idk what to do with these...but they can be very useful
            String server = obj.get("server").getAsString();
            String gametype = obj.get("gametype").getAsString();

            this.ingame = obj.get("mode") != null && obj.get("map") != null && gametype.equalsIgnoreCase("DUELS"); // simplify from if/else to a one line value declaration
        }
    }
}
