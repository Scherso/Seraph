package dev.salmon.seraph.listener;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.ChatUtils;
import dev.salmon.seraph.util.ServerUtils;
import dev.salmon.seraph.util.ChatColor;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ApiKeyListener {
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        if (message.startsWith("Your new API key is ") && ServerUtils.isOnHypixel()) {
            String apiKey = message.replace("Your new API key is ", "");
            if (ServerUtils.isApiKeyValid(apiKey)) {
                Seraph.getInstance().getConfig().setApiKey(apiKey);
                ChatUtils.showChatMessage(ChatColor.GRAY + "Your API key has been found, and added to Seraph's config.");
            } else {
                ChatUtils.showChatMessage(ChatColor.RED + "It seems your API key is empty or invalid. Please run " + ChatColor.BOLD + "/api new" + ChatColor.RESET + ChatColor.RED + " once again.");
            }
        }
    }
}
