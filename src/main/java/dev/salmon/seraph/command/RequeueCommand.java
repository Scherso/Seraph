package dev.salmon.seraph.command;

import dev.salmon.seraph.util.ChatColor;
import dev.salmon.seraph.util.ChatUtils;
import dev.salmon.seraph.util.locraw.LocrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;
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
        return "/" + getCommandName();
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return new ArrayList<>();
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (LocrawUtils.getInstance().isInGame())
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/play " + LocrawUtils.getInstance().getLocrawInfo().getGameMode().toLowerCase());
        else ChatUtils.showChatMessage(ChatColor.RED + "You must be in a game to use this command.");
    }
}
