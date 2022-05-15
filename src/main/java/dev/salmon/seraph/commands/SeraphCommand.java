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
            ChatUtils.success("Your API key has been found, and added to Seraph's config.");
        } else {
            ChatUtils.error(ChatColor.RED + "It seems your API key is empty or invalid. Please run " + ChatColor.BOLD + "/api new" + ChatColor.RESET + ChatColor.RED + " once again.");
        }
    }

    @SubCommand(value = "getkey", aliases = {"getapikey"})
    public void handleGetKey() {
        if (Seraph.getInstance().getConfig().getApiKey().isEmpty()) {
            ChatUtils.error("Your API key isn't set!");
        } else {
            IChatComponent text = new ChatComponentText("Click to add your API key to your chat box.");
            text.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Seraph.getInstance().getConfig().getApiKey()));
            ChatUtils.success(text);
        }
    }

    @SubCommand(value = "info")
    public void handleInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(ChatColor.STRIKETHROUGH).append("------------------").append("\n");
        builder.append(ChatColor.GRAY).append("Credits:").append("\n");
        builder.append(ChatColor.AQUA).append("[MVP").append(ChatColor.DARK_RED).append("+").append(ChatColor.AQUA).append("] Scherso").append("\n");
        builder.append(ChatColor.AQUA).append("[MVP").append(ChatColor.GREEN).append("+").append(ChatColor.AQUA).append("] KnightsWhoSayNi_ ").append(ChatColor.GRAY).append("(Answering questions)").append("\n");
        builder.append(ChatColor.GREEN).append("[VIP] exejar ").append(ChatColor.GRAY).append("(Hypixel API library)").append("\n");
        builder.append(ChatColor.AQUA).append("[MVP").append(ChatColor.DARK_GREEN).append("+").append(ChatColor.AQUA).append("] Deftu ").append(ChatColor.GRAY).append("(General clean-up)").append("\n");
        builder.append(ChatColor.STRIKETHROUGH).append("------------------").append("\n");
        ChatUtils.show(builder.toString(), false);
    }

    public Set<Alias> getCommandAliases() {
        return Sets.newHashSet(
                new Alias("statchecker"),
                new Alias("duelsniffer"),
                new Alias("duelsniff")
        );
    }
}
