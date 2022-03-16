package dev.salmon.seraph.util.chat;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import org.jetbrains.annotations.NotNull;

public interface ChatReceiveHelper extends ChatHelper {
    void onMessageReceived(@NotNull ClientChatReceivedEvent event);
}
