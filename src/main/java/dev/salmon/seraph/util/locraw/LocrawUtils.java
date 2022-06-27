package dev.salmon.seraph.util.locraw;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.salmon.seraph.listener.event.LocrawEvent;
import dev.salmon.seraph.util.JsonUtils;
import dev.salmon.seraph.util.Multithreading;
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
    private LocrawInfo lastLocraw;

    public static LocrawUtils getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LocrawUtils();
        return INSTANCE;
    }

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
            // Checking for rate limitation.
            if (!JsonUtils.isValidJson(msg)) {
                if (msg.contains("You are sending too many commands! Please try again in a few seconds.")) // if you're being rate limited, the /locraw command will be resent in 5 seconds.
                    queueUpdate(4900);
                return;
            }

            JsonElement raw = JsonUtils.getJsonParser().parse(msg);
            if (!raw.isJsonObject()) return;
            JsonObject json = raw.getAsJsonObject();
            LocrawInfo parsed = JsonUtils.getGson().fromJson(json, LocrawInfo.class);

            if (5 > limboLoop && parsed.getGameType() == LocrawInfo.GameType.LIMBO) {
                sentCommand = false;
                limboLoop++;
                queueUpdate(1000);
            } else locraw = parsed; // if the player isn't in limbo, the parsed info is used.

            if (locraw != null) {
                locraw.setGameType(LocrawInfo.GameType.getFromLocraw(this.locraw.getRawGameType()));
                
                // boolean inBedwarsGame = (!parsed.getGameMode().equals("lobby") && parsed.getGameType() == LocrawInfo.GameType.BEDWARS); // Bedwars Game Check
                // boolean inSkywarsGame = (!parsed.getGameMode().equals("lobby") && parsed.getGameType() == LocrawInfo.GameType.SKYWARS); // Skywars Game Check
                this.inDuelsGame = (!parsed.getGameMode().equals("lobby") && parsed.getGameType() == LocrawInfo.GameType.DUELS); // Duels Game Check

                if (parsed.getGameMode().equals("lobby")) {
                    inGame = false; // If your gamemode returns "lobby", boolean inGame is false.
                    MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinLobby(locraw));
                } else {
                    lastLocraw = parsed;
                    inGame = true; // If your gamemode does not return "lobby", boolean inGame is true.
                    MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinGame(locraw));
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

    public LocrawInfo getLocrawInfo() {
        return this.locraw;
    }

    public LocrawInfo getLastLocrawInfo() {
        return this.lastLocraw;
    }
}
