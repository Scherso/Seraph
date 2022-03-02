package dev.salmon.seraph.util.locraw;

import com.google.gson.Gson;
import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.listener.event.LocrawEvent;
import dev.salmon.seraph.util.chat.ChatReceieveHelper;
import gg.essential.api.EssentialAPI;
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

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START || Minecraft.getMinecraft().thePlayer == null || !EssentialAPI.getMinecraftUtil().isHypixel() || this.tick >= 22) return;

        this.tick++;
        if (this.tick == 20) {
            this.listening = true;
            Seraph.getInstance().getCommandQueue().queue("/locraw");
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

                    if (this.locraw.getGameMode().equals("lobby"))
                        MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinLobby(this.locraw));
                    else
                        MinecraftForge.EVENT_BUS.post(new LocrawEvent.JoinGame(this.locraw));

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

    public LocrawInfo getLocrawInfo() {
        return this.locraw;
    }
}
