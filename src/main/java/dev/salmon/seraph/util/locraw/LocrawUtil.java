package dev.salmon.seraph.util.locraw;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.salmon.seraph.listener.event.LocrawEvent;
import dev.salmon.seraph.util.Multithreading;
import dev.salmon.seraph.util.Utils;
import dev.salmon.seraph.util.chat.ChatReceiveHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class LocrawUtil implements ChatReceiveHelper {

    private int tick = 0;
    private int limboLoop = 0;

    private boolean sentCommand = false;
    private boolean sendPermitted = false;

    private boolean inDuelsGame;
    private boolean inGame;

    private LocrawInfo locraw;
    Gson gson = new Gson();

    public void queueUpdate(long interval) {
        this.sendPermitted = true;
        Multithreading.schedule(() -> {
            if (this.sendPermitted) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/locraw");
            }
        }, interval, TimeUnit.MILLISECONDS);
    }


    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        this.tick++;

        if (this.tick % 20 == 0) {
            this.tick = 0;
            if (Utils.isHypixel() && !this.sentCommand) {
                queueUpdate(500);
                this.sentCommand = true;
            }
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        this.locraw = null;
        this.sendPermitted = false;
        this.sentCommand = false;
        this.limboLoop = 0;
    }

    @SubscribeEvent(priority = EventPriority.HIGH, receiveCanceled = true)
    public void onMessageReceived(@NotNull ClientChatReceivedEvent event) {
        if (!this.sentCommand) return;
        JsonParser parser = new JsonParser();

        try {
            final String msg = EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getUnformattedText());
            if (msg.startsWith("{")) {
                if (!Utils.isValidJson(msg)) {
                    if (msg.contains("You are sending too many commands! Please try again in a few seconds.")) {
                        queueUpdate(5000);
                    }
                    return;
                }

                JsonElement raw = parser.parse(msg);
                if (!raw.isJsonObject()) return;
                JsonObject json = raw.getAsJsonObject();
                LocrawInfo parsed = gson.fromJson(json, LocrawInfo.class);
                if (parsed.getGameType() == LocrawInfo.GameType.LIMBO) {
                    this.sentCommand = false;
                    this.limboLoop++;
                    this.queueUpdate(1000);
                } else locraw = parsed;
                this.locraw.setGameType(LocrawInfo.GameType.getFromLocraw(this.locraw.getRawGameType()));

                if (!parsed.getGameMode().equals("lobby") && parsed.getGameType() == LocrawInfo.GameType.DUELS) {
                    this.inDuelsGame = true;
                    MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinGame(this.locraw));
                }

                if (parsed.getGameMode().equals("lobby")) {
                    this.inGame = false;
                    MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinLobby(this.locraw));
                } else {
                    this.inGame = true;
                    MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinGame(this.locraw));
                }

                event.setCanceled(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean isInGame() {
        return this.inGame;
    }

    public boolean isInDuelsGame() {
        return this.inDuelsGame;
    }

}
