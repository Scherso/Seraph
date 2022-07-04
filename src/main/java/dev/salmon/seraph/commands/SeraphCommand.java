package dev.salmon.seraph.commands;

import com.google.common.collect.Sets;
import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.ChatUtils;
import dev.salmon.seraph.util.ServerUtils;
import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import gg.essential.api.commands.DisplayName;
import gg.essential.api.commands.SubCommand;
import gg.essential.universal.ChatColor;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import java.util.Set;

public class SeraphCommand extends Command {
    public SeraphCommand() {
        super(Seraph.ID);
    }

    @DefaultHandler
    public void handle() {
        EssentialAPI.getGuiUtil().openScreen(Seraph.getInstance().getConfig().gui());
    }

    @SubCommand(value = "setkey", aliases = {"setapikey"})
    public void handleSetKey(@DisplayName("key") String key) {
        if (ServerUtils.isApiKeyValid(key)) {
            Seraph.getInstance().getConfig().setApiKey(key);
            ChatUtils.success(key + "has been added to Seraph's config file.");
        } else {
            ChatUtils.error(ChatColor.RED + "It seems your API key is invalid. Please run " + ChatColor.BOLD + "/api new" + ChatColor.RESET + ChatColor.RED + " once again.");
        }
    }

    @SubCommand(value = "getkey", aliases = {"getapikey"})
    public void handleGetKey() {
        if (Seraph.getInstance().getConfig().getApiKey().isEmpty()) {
            ChatUtils.error("Your API key isn't set!");
        } else {
            IChatComponent text = new ChatComponentText(ChatColor.YELLOW + "Hover over to and click to add your API key to your chat box.");
            text.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Seraph.getInstance().getConfig().getApiKey()));
            ChatUtils.success(text);
        }
    }

    @SubCommand(value = "info")
    public void handleInfo() {
        String builder = ChatColor.STRIKETHROUGH + "------------------" + "\n" +
                ChatColor.GRAY + "Credits:" + "\n" +
                ChatColor.AQUA + "[MVP" + ChatColor.DARK_RED + "+" + ChatColor.AQUA + "] Scherso" + "\n" +
                ChatColor.AQUA + "[MVP" + ChatColor.BLACK + "+" + ChatColor.AQUA + "] Xasr " + ChatColor.GRAY + "(Contributor)" + "\n" +
                ChatColor.AQUA + "[MVP" + ChatColor.GREEN + "+" + ChatColor.AQUA + "] KnightsWhoSayNi_ " + ChatColor.GRAY + "(Answering questions)" + "\n" +
                ChatColor.GREEN + "[VIP] exejar " + ChatColor.GRAY + "(Hypixel API library)" + "\n" +
                ChatColor.GREEN + "[VIP] You_ded " + ChatColor.GRAY + "(Being Cool)" + "\n" +
                ChatColor.AQUA + "[MVP" + ChatColor.DARK_GREEN + "+" + ChatColor.AQUA + "] Deftu " + ChatColor.GRAY + "(General clean-up)" + "\n" +
                ChatColor.STRIKETHROUGH + "------------------";
        ChatUtils.show(builder, false);
    }

    public Set<Alias> getCommandAliases() {
        return Sets.newHashSet(
                new Alias("statchecker"),
                new Alias("duelsniffer"),
                new Alias("duelsniff")
        );
    }
}
