package dev.salmon.seraph.commands;

import com.google.common.collect.Sets;
import dev.salmon.seraph.util.locraw.LocrawUtils;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import net.minecraft.client.Minecraft;

import java.util.Set;

public class RequeueCommand extends Command {
    public RequeueCommand() {
        super("rq");
    }

    @DefaultHandler
    public void handle() {
        if (LocrawUtils.getInstance().isInGame())
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/play " + LocrawUtils.getInstance().getLocrawInfo().getGameMode().toLowerCase());
        else
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/play " + LocrawUtils.getInstance().getLastLocrawInfo().getGameMode().toLowerCase());
    }

    public Set<Alias> getCommandAliases() {
        return Sets.newHashSet(new Alias("requeue"));
    }
}
