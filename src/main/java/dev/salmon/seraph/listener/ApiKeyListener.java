package dev.salmon.seraph.listener;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.ChatUtils;
import dev.salmon.seraph.util.ServerUtils;
import gg.essential.api.EssentialAPI;
import gg.essential.universal.ChatColor;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ApiKeyListener {
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        if (message.startsWith("Your new API key is ") && EssentialAPI.getMinecraftUtil().isHypixel()) {
            String apiKey = message.replace("Your new API key is ", "");
            if (ServerUtils.isApiKeyValid(apiKey)) {
                Seraph.getInstance().getConfig().setApiKey(apiKey);
                ChatUtils.success("Your API key has been found, and added to Seraph's config.");
            } else {
                ChatUtils.error("It seems your API key is empty or invalid. Please run " + ChatColor.BOLD + "/api new" + ChatColor.RESET + ChatColor.RED + " once again.");
            }
        }
    }
}
