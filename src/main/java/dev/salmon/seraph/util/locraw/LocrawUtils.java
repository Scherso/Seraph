package dev.salmon.seraph.util.locraw;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.salmon.seraph.listener.event.LocrawEvent;
import dev.salmon.seraph.util.JsonUtils;
import dev.salmon.seraph.util.Multithreading;
import dev.salmon.seraph.util.ServerUtils;
import gg.essential.api.EssentialAPI;
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

public class LocrawUtils {
    private static LocrawUtils INSTANCE;

    private int tick = 0;
    private int limboLoop = 0;

    private boolean sentCommand = false;
    private boolean sendPermitted = false;

    private boolean inDuelsGame;
    private boolean inGame;

    private LocrawInfo locraw;
    private LocrawInfo lastGameLocraw;

    public void queueUpdate(long interval) {
        sendPermitted = true;
        Multithreading.schedule(() -> {
            if (sendPermitted) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/locraw");
            }
        }, interval, TimeUnit.MILLISECONDS);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        tick++;

        if (tick % 20 == 0) {
            tick = 0;
            if (EssentialAPI.getMinecraftUtil().isHypixel() && !sentCommand) {
                queueUpdate(500);
                sentCommand = true;
            }
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        locraw = null;
        sendPermitted = false;
        sentCommand = false;
        limboLoop = 0;
    }

    @SubscribeEvent(priority = EventPriority.HIGH, receiveCanceled = true)
    public void onMessageReceived(@NotNull ClientChatReceivedEvent event) {
        if (!sentCommand) return;
        try {
            final String msg = EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getUnformattedText());
            if (!JsonUtils.isValidJson(msg)) {
                if (msg.contains("You are sending too many commands! Please try again in a few seconds.")) // if you're being rate limited, the /locraw command will be resent in 5 seconds.
                    queueUpdate(5000);
                return;
            }

            JsonElement raw = JsonUtils.getJsonParser().parse(msg);
            if (!raw.isJsonObject()) return;
            JsonObject json = raw.getAsJsonObject();
            LocrawInfo parsed = JsonUtils.getGson().fromJson(json, LocrawInfo.class);

            // Basic limbo checks. Hypixel occasionally sends false limbo data, thus we need to make sure we're in limbo.
            if (5 > limboLoop && parsed.getGameType() == LocrawInfo.GameType.LIMBO) {
                sentCommand = false;
                limboLoop++;
                queueUpdate(1000);
            } // if the player isn't in limbo, the parsed info is used.
            else (parsed != null) {
                if(locraw != null && parsed.getGameMode().equals("lobby") && !locraw.getGameMode().equals("lobby"))
                    lastGameLocraw = locraw
                locraw = parsed
                locraw.setGameType(LocrawInfo.GameType.getFromLocraw(this.locraw.getRawGameType()));

                // In game checks for duels queue.
                if (!parsed.getGameMode().equals("lobby") && parsed.getGameType() == LocrawInfo.GameType.DUELS) {
                    inDuelsGame = true;
                    MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinGame(this.locraw));
                }

                // general in game check for any gamemode.
                if (parsed.getGameMode().equals("lobby")) {
                    inGame = false;
                    MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinLobby(locraw));
                } else {
                    inGame = true;
                    MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinGame(locraw));
                }
            }
            event.setCanceled(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean isInGame() {
        return inGame;
    }

    public boolean isInDuelsGame() {
        return inDuelsGame;
    }

    public LocrawInfo getLocrawInfo() {
        return locraw;
    }

    public LocrawInfo getLastGameLocrawInfo() {
        return lastGameLocraw;
    }

    public static LocrawUtils getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LocrawUtils();
        return INSTANCE;
    }
}
