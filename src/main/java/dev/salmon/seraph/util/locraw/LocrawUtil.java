package dev.salmon.seraph.util.locraw;

import com.google.gson.Gson;
import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.listener.event.LocrawEvent;
import dev.salmon.seraph.util.Utils;
import dev.salmon.seraph.util.chat.ChatReceiveHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jetbrains.annotations.NotNull;

public class LocrawUtil implements ChatReceiveHelper {

    private final Gson gson = new Gson();

    private int tick;

    private LocrawInfo locraw;
    private boolean sentCommand = false;
    private boolean listening;
    private boolean ingame;
    private boolean inDuelsGame;
    private boolean isResending;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START || Minecraft.getMinecraft().thePlayer == null || !Utils.isHypixel()) {
            return;
        }

        this.tick++;
        if (this.tick == 20 || this.tick % 500 == 0) {
            this.listening = true;
            Seraph.Instance.getCommandQueue().queue("/locraw");
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        this.locraw = null;
        this.tick = 0;
        this.isResending = false;
    }

    @SuppressWarnings("UnusedReturnValue")
    public String onMessageSend(@NotNull String message) {
        if (message.startsWith("/locraw") && !this.listening) {
            if (this.tick == 22 || this.tick % 500 != 0) {
                this.sentCommand = true;
            }

        }
        return message;
    }

    @SubscribeEvent
    public void onMessageReceived(@NotNull ClientChatReceivedEvent event) {
        try {
            final String msg = event.message.getUnformattedTextForChat();
            if (msg.startsWith("{")) {
                // Parse the json, and make sure that it's not null.
                this.locraw = gson.fromJson(msg, LocrawInfo.class);
                if (locraw != null) {
                    this.locraw.setGameType(LocrawInfo.GameType.getFromLocraw(this.locraw.getRawGameType()));

                    if (!this.sentCommand) {
                        event.setCanceled(true);
                    }

                    /*
                    checking if a player is in duels queue
                     */
                    if (!this.locraw.getGameMode().equals("lobby") && this.locraw.getGameType() == LocrawInfo.GameType.DUELS) {
                        this.inDuelsGame = true;
                        MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinGame(this.locraw));
                    }

                    /*
                    checking if a player is in the queue of any hypixel mini-game
                     */
                    if (this.locraw.getGameMode().equals("lobby")) {
                        this.ingame = false;
                        MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinLobby(this.locraw));
                    } else {
                        this.ingame = true;
                        MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinGame(this.locraw));
                    }

                    if (!isResending) {
                        MinecraftForge.EVENT_BUS.post(new LocrawEvent(this.locraw));
                        isResending = true;
                    }

                    this.sentCommand = false;
                    this.listening = false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean isEnabled() {
        return this.listening;
    }

    public boolean isInGame() {
        return this.ingame;
    }

    public boolean isInDuelsGame() {
        return this.inDuelsGame;
    }

    public LocrawInfo getLocrawInfo() {
        return this.locraw;
    }
}
