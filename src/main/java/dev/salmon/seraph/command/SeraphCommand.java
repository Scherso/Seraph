package dev.salmon.seraph.command;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.chat.ChatColor;
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
                        sender.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.RED + "Enter an API key"));
                    else
                        Seraph.Instance.getConfig().setApiKey(args[1]);
                        sender.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.GREEN + "Your API key has been saved."));
                    break;

                case "getapikey":
                case "getkey":
                    if (Seraph.Instance.getConfig().getApiKey().isEmpty()) {
                        sender.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.RED + "You must set an API key!"));
                    } else {
                        IChatComponent text = new ChatComponentText(Seraph.SeraphPrefix + ChatColor.GREEN + Seraph.Instance.getConfig().getApiKey());
                        HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(ChatColor.YELLOW + "Click to add your API key to your chat box."));
                        ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Seraph.Instance.getConfig().getApiKey());

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
                    sender.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.RED + "Incorrect usage, /seraph, /seraph setapikey <key>, /seraph info"));
                    break;
            }
        } else {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        MinecraftForge.EVENT_BUS.unregister(this);
        Minecraft.getMinecraft().displayGuiScreen(Seraph.Instance.getConfig().gui());
    }

}
