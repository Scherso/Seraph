package dev.salmon.seraph.listener;

import dev.salmon.seraph.util.Multithreading;
import dev.salmon.seraph.util.PlayerUtils;
import dev.salmon.seraph.util.locraw.LocrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerGrabberListener {
    List<String> playerList = new ArrayList<>();

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (LocrawUtils.getInstance().isInDuelsGame()) {
            for (ScorePlayerTeam team : Minecraft.getMinecraft().theWorld.getScoreboard().getTeams()) {
                for (String playerName : team.getMembershipCollection()) {
                    /* All possible player's have a team prefix with the obfuscated color code */
                    if (!playerList.contains(playerName) && team.getColorPrefix().equals("§7§k") && !playerName.equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.getDisplayNameString())) {
                        /* Add to the player list, so we don't try and resolve the player infinitely */
                        this.playerList.add(playerName);
                        Multithreading.runAsync(() -> {
                            if (!PlayerUtils.isValidPlayer(playerName)) return; // This is not a real player.
                            String uuid = PlayerUtils.getUuid(playerName).toString();
                            // If it's a real player, just print their name for testing.
                            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("name: " + playerName + "\nuuid: " + uuid));
                        });
                    }
                }
            }
        }
    }
}