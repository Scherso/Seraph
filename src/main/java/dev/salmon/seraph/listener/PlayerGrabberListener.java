package dev.salmon.seraph.listener;

import dev.salmon.seraph.api.HypixelAPI;
import dev.salmon.seraph.util.DuelsUtils;
import dev.salmon.seraph.util.FormatUtils;
import dev.salmon.seraph.util.Multithreading;
import dev.salmon.seraph.util.PlayerUtils;
import dev.salmon.seraph.util.locraw.LocrawUtils;
import gg.essential.universal.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dev.salmon.seraph.api.HypixelAPI.getRank;

public class PlayerGrabberListener {
    List<String> playerList = new ArrayList<>();
    
    /* To allow repeated grabbing of the same player */
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        playerList.clear();
    }

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

                            // Get player stats
                            String wins = HypixelAPI.getDuelsWins(uuid);
                            String losses = HypixelAPI.getDuelsLosses(uuid);
                            //String kills = HypixelAPI.getDuelsKills(uuid);
                            //String deaths = HypixelAPI.getDuelsDeaths(uuid);

                            // double wlr = FormatUtils.formatDouble(Integer.parseInt(wins), Integer.parseInt(losses));
                            double wlr = Double.parseDouble(wins) / Double.parseDouble(losses);
                            // double kdr = Double.parseDouble(kills) / Double.parseDouble(deaths);

                            try {
                                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText((HypixelAPI.getRank(uuid)) + " " + playerName)); // Rank Getter
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Name: " + playerName + "\nUUID: " + uuid + "\nWins: " + wins + " Losses: " + losses + " W/L: " + FormatUtils.splitNumber(wlr)));
                        });
                    }
                }
            }
        }
    }
}
