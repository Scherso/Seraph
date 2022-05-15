package dev.salmon.seraph.command;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.ChatColor;
import dev.salmon.seraph.util.ChatUtils;
import dev.salmon.seraph.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Arrays;
import java.util.List;

public class SeraphCommand extends CommandBase {
    public String getCommandName() {
        return Seraph.ID;
    }

    public List<String> getCommandAliases() {
        return Arrays.asList("statchecker", "duelsniffer", "duelsniff");
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName() + " <subcommand>";
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "setapikey":
                case "setkey":
                    if (args.length < 2)
                        sender.addChatMessage(new ChatComponentText(ChatUtils.CHAT_PREFIX + ChatColor.RED + "Enter an API key"));
                    else {
                        Seraph.getInstance().getConfig().setApiKey(args[1]);
                        sender.addChatMessage(new ChatComponentText(ChatUtils.CHAT_PREFIX + ChatColor.GREEN + "Your API key has been saved."));
                    }
                    break;
                case "getapikey":
                case "getkey":
                    if (Seraph.getInstance().getConfig().getApiKey().isEmpty()) {
                        ChatUtils.showChatMessage(ChatColor.RED + "You must set an API key!");
                    } else {
                        IChatComponent text = new ChatComponentText(ChatUtils.CHAT_PREFIX + ChatColor.GREEN + Seraph.getInstance().getConfig().getApiKey());
                        HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(ChatColor.YELLOW + "Click to add your API key to your chat box."));
                        ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Seraph.getInstance().getConfig().getApiKey());
                        text.getChatStyle().setChatHoverEvent(hoverEvent).setChatClickEvent(clickEvent);
                        sender.addChatMessage(text);
                    }
                    break;
                case "info":
                    IChatComponent mainComponent = new ChatComponentText(ChatColor.WHITE + "Credits (hover!)");
                    IChatComponent hoverComponent = new ChatComponentText(ChatColor.WHITE + "Credits:\n" + ChatColor.AQUA + "[MVP" + ChatColor.DARK_RED + "+" + ChatColor.AQUA + "] Scherso" + ChatColor.GRAY + " Original creator.\n" + ChatColor.AQUA + "[MVP" + ChatColor.GREEN + "+" + ChatColor.AQUA + "] KnightsWhoSayNi_" + ChatColor.GRAY + " Answering " + ChatColor.AQUA + "[MVP" + ChatColor.DARK_RED + "+" + ChatColor.AQUA + "] Scherso's" + ChatColor.GRAY + " dumb questions.\n" + ChatColor.GREEN + "[VIP] exejar" + ChatColor.GRAY + " Hypixel API lib (ChampStats), original idea and naming.");
                    HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverComponent);
                    mainComponent.getChatStyle().setChatHoverEvent(hover);
                    sender.addChatMessage(mainComponent);
                    break;
                default:
                    ChatUtils.showChatMessage(ChatColor.RED + "Incorrect usage, /seraph, /seraph setapikey <key>, /seraph info");
                    break;
            }
        } else {
            GuiUtils.showScreen(Seraph.getInstance().getConfig().gui());
        }
    }
}
