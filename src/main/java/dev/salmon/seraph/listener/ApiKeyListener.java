package dev.salmon.seraph.listener;

import dev.salmon.seraph.Seraph;
import gg.essential.api.utils.Multithreading;
import gg.essential.universal.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.concurrent.TimeUnit;

public class ApiKeyListener {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        // checking to see if the chat message the player received is Hypixel's api key message.
        if (message.startsWith("Your new API key is ")) {
            // if it is, the key is grabbed.
            String apiKey = message.replace("Your new API key is ", "");
            Seraph.getInstance().getConfig().setApiKey(apiKey);
            //checking to see if it has been set.
            if (Seraph.getInstance().getConfig().getApiKey().equals(apiKey)) {
                Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Seraph.Prefix.SeraphPrefix + ChatColor.GRAY + "Your API key has been found, and added to Seraph's config.")), 100, TimeUnit.MILLISECONDS);
            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Seraph.Prefix.SeraphPrefix + ChatColor.RED + "An error occurred, and your API key was not added to Seraph's config, try again.\nAlso, you can manually add it yourself in the config menu, /seraph, or run /seraph apikey <apikey>"));
            }
        }
    }

}
