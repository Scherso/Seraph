package dev.salmon.seraph.listener;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.Utils;
import dev.salmon.seraph.util.chat.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ApiKeyListener {

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        if (message.startsWith("Your new API key is ") && Utils.isHypixel()) {
            Seraph.Instance.getConfig().setApiKey(message.replace("Your new API key is ", ""));
            String apikey = message.replace("Your new API key is ", "");
            if (!Utils.isKeyValid(apikey))
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.RED + "It seems your API key is empty or invalid. Please run " + ChatColor.BOLD + "/api new" + ChatColor.RESET + ChatColor.RED + " once again."));
            else
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.GRAY + "Your API key has been found, and added to Seraph's config."));
        }
    }

}
