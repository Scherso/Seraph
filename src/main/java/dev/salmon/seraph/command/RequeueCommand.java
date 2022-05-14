package dev.salmon.seraph.command;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.chat.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RequeueCommand extends CommandBase {

    public String getCommandName() {
        return "rq";
    }

    public List<String> getCommandAliases() {
        return Collections.singletonList("requeue");
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName();
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return Arrays.asList("rq", "requeue");
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (Seraph.Instance.getLocrawUtil().isInGame())
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/play " + Seraph.Instance.getLocrawUtil().getLocrawInfo().getGameMode().toLowerCase());
        else
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Seraph.SeraphPrefix + ChatColor.RED + "You must be in a game to use this command."));
    }

}
