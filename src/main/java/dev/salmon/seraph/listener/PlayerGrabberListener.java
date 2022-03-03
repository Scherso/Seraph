package dev.salmon.seraph.listener;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.Handler;
import me.kbrewster.exceptions.APIException;
import me.kbrewster.mojangapi.MojangAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerGrabberListener {
    List<String> playerList = new ArrayList<>();

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (Seraph.Instance.getLocrawUtil().isInGame()) {
            for (ScorePlayerTeam team : Minecraft.getMinecraft().theWorld.getScoreboard().getTeams()) {
                for (String playerName : team.getMembershipCollection()) {
                    /* All possible player's have a team prefix with the obfuscated color code */
                    if (!this.playerList.contains(playerName) && team.getColorPrefix().equals("§7§k")) {
                        /* Add to the player list, so we don't try and resolve the player infinitely */
                        this.playerList.add(playerName);
                        Handler.asExecutor(()-> {
                            try {
                                /* If you can't resolve a UUID from the player name, they aren't a real player */
                                MojangAPI.getUUID(playerName);
                                /* If it's a real player, just print their name for testing */
                                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Added " + playerName + " to player list"));
                            } catch (IOException | APIException ignored) {
                            }
                        });
                    }
                }
            }
        }
    }
}
