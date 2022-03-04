package dev.salmon.seraph.util.locraw;

import com.google.gson.Gson;
import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.listener.event.LocrawEvent;
import dev.salmon.seraph.util.Utils;
import dev.salmon.seraph.util.chat.ChatReceieveHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jetbrains.annotations.NotNull;

public class LocrawUtil implements ChatReceieveHelper {

    private final Gson gson = new Gson();

    private int tick;

    private LocrawInfo locraw;
    private boolean sentCommand = false;
    private boolean listening;
    private boolean ingame;
    private boolean inDuelsGame;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START || Minecraft.getMinecraft().thePlayer == null || !Utils.isHypixel()|| this.tick >= 22) return;

        this.tick++;
        if (this.tick == 20) {
            this.listening = true;
            Seraph.Instance.getCommandQueue().queue("/locraw");
        }
    }

    @SubscribeEvent
    public void onLoad(WorldEvent.Load event) {
        this.tick = 0;
    }

    public String onMessageSend(@NotNull String message) {
        if (message.startsWith("/locraw") && !this.listening && this.tick >= 22) {
            this.sentCommand = true;
        }
        return message;
    }

    @SubscribeEvent
    public void onMessageReceived(@NotNull ClientChatReceivedEvent event) {
        try {
            final String umsg = event.message.getUnformattedTextForChat();
            if (umsg.startsWith("{")) {
                this.locraw = gson.fromJson(umsg, LocrawInfo.class);

                if (this.locraw != null) {
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
                    }
                    else {
                        this.ingame = true;
                        MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinGame(this.locraw));
                    }

                    this.sentCommand = false;
                    this.listening = false;
                }
            }
        } catch (Exception ignored) {}
    }

    @Override
    public boolean isEnabled() {
        return this.listening;
    }

    public boolean isInGame() { return this.ingame; }

    public boolean isInDuelsGame() {
        return this.inDuelsGame;
    }

    public LocrawInfo getLocrawInfo() {
        return this.locraw;
    }
}
