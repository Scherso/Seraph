package dev.salmon.seraph.util.chat;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public interface ChatReceieveHelper extends ChatHelper {
    void onMessageReceived(@NotNull ClientChatReceivedEvent event);
}
