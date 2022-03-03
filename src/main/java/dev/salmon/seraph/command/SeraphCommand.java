package dev.salmon.seraph.command;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.Handler;
import dev.salmon.seraph.util.chat.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeraphCommand extends CommandBase {

    public String getCommandName() {
        return Seraph.ID;
    }

    public List<String> getCommandAliases() {
        return Arrays.asList("statchecker", "duelsniffer", "duelsniff");
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/seraph <subcommand>";
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public void processCommand(ICommandSender sender, String[] args) {

        if (args.length > 0) {
            switch (args[0].toLowerCase()) {

                case "setapikey":
                case "setkey":
                        if (args.length < 2) {
                            sender.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.RED + "Enter an API key"));
                        } else {
                            Seraph.Instance.getConfig().setApiKey(args[1]);
                            sender.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.GREEN + "Your API key has been saved."));
                        }
                        break;

                case "info":
                    IChatComponent mainComponent = new ChatComponentText(ChatColor.WHITE + "Credits (hover!)");
                    IChatComponent hoverComponent = new ChatComponentText(ChatColor.WHITE + "Credits:\n" + ChatColor.AQUA + "[MVP" + ChatColor.DARK_RED + "+" + ChatColor.AQUA + "] Scherso" + ChatColor.GRAY + " Original creator.\n" + ChatColor.AQUA + "[MVP" + ChatColor.GREEN + "+" + ChatColor.AQUA + "] KnightsWhoSayNi_" + ChatColor.GRAY + " Answering " + ChatColor.AQUA + "[MVP" + ChatColor.DARK_RED + "+" + ChatColor.AQUA + "] Scherso's" + ChatColor.GRAY + " dumb questions.\n" + ChatColor.GREEN + "[VIP] exejar" + ChatColor.GRAY + " Hypixel API lib (ChampStats), original idea and naming.");

                    HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverComponent);
                    mainComponent.getChatStyle().setChatHoverEvent(hover);

                    sender.addChatMessage(mainComponent);
                    break;
            }
        } else {
            MinecraftForge.EVENT_BUS.register(this);
            Handler.schedule(() -> Minecraft.getMinecraft().displayGuiScreen(Seraph.Instance.getConfig().gui()), 100, TimeUnit.MILLISECONDS);
        }
    }

}
