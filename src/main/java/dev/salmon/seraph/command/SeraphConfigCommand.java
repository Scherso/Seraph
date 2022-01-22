package dev.salmon.seraph.command;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.References;
import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import gg.essential.api.commands.DisplayName;
import gg.essential.api.commands.SubCommand;
import gg.essential.universal.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import java.util.Optional;

public class SeraphConfigCommand extends Command {

    // setting the name of the command, and whether it is tab completable.
    public SeraphConfigCommand() {
        super(Seraph.ID, true);
    }

    // once the command has been executed, the vigilance gui screen opens. See SeraphConfig to see the vigilance gui screen.
    @DefaultHandler
    public void handle() {
        EssentialAPI.getGuiUtil().openScreen(Seraph.getInstance().getConfig().gui());
    }

    // creating a sub command to set your api key without /api new.
    @SubCommand(value = "apikey", aliases = {"setapi", "setapikey", "setkey"}, description = "Set your api key with a command, if you already have one.")
    public void apikey(@DisplayName("API-Key") String apikey) {
        // a boolean to check if the user set nothing as their api key.
        boolean emptyApiKey = apikey.isEmpty();

        // if the player set's an api key, the config set that key, and sends a message so the user knows their api key has been set.
            Seraph.getInstance().getConfig().setApiKey(apikey);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(References.SERAPHPREFIX + ChatColor.GRAY + "The API key " + apikey + " has been added to Seraph's config."));

            // if their api key is empty, there is no key set, and a message to remind the user how to set an api key.
            if (emptyApiKey) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(References.SERAPHPREFIX + ChatColor.RED + "Your API key cannot be null. /seraph apikey <apikey>"));
        }
    }

    // info sub command, /seraph info
    @SubCommand(value = "info", description = "See more about the mod Seraph.")
    public void info(@DisplayName("info") Optional<String> info) {
        // the chat components
        IChatComponent mainComponent  = new ChatComponentText( ChatColor.WHITE + "Credits (hover!)");
        IChatComponent hoverComponent = new ChatComponentText( ChatColor.WHITE + "Credits:\n" + ChatColor.AQUA + "[MVP" + ChatColor.DARK_RED + "+" + ChatColor.AQUA + "] Scherso" + ChatColor.GRAY + " Original creator.\n" + ChatColor.AQUA + "[MVP" + ChatColor.GREEN + "+" + ChatColor.AQUA + "] KnightsWhoSayNi_" + ChatColor.GRAY + " Answering " + ChatColor.AQUA + "[MVP" + ChatColor.DARK_RED + "+" + ChatColor.AQUA + "] Scherso's" + ChatColor.GRAY + " dumb questions.\n" + ChatColor.GREEN + "[VIP] exejar" + ChatColor.GRAY + " Hypixel API lib (ChampStats), original idea and naming." );

        // creating the hover event and applying it to the style
        HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverComponent);
        mainComponent.getChatStyle().setChatHoverEvent(hover);
        // sending the chat message with hover text.
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------" + ChatColor.RESET ));
        Minecraft.getMinecraft().thePlayer.addChatMessage(mainComponent);
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColor.WHITE + "How many people you have stat checked: ")); // add this later LOL
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------" + ChatColor.RESET));
    }
}
