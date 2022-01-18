
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
import net.minecraft.util.ChatComponentText;

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
    public void apikey(@DisplayName("apikey") String apikey) {
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
}
