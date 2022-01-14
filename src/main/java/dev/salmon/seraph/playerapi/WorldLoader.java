package dev.salmon.seraph.playerapi;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorldLoader extends StatWorld {
    Minecraft mc = Minecraft.getMinecraft();

    /* uuid version 4 means its a real player or watchdog bot, version 1 means it's a nicked player */
    public boolean loadOrRender(EntityPlayer player) {
        return player != null && (player.getUniqueID().version() == 4 || player.getUniqueID().version() == 1);
    }

    /* populates and checks the stat world player cache every tick */
    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (this.mc.theWorld != null && this.mc.thePlayer != null) {
            for (EntityPlayer entityPlayer : this.mc.theWorld.playerEntities) {
                if (!this.existedMoreThan5Seconds.contains(entityPlayer.getUniqueID())) {
                    if (!this.timeCheck.containsKey(entityPlayer.getUniqueID()))
                        this.timeCheck.put(entityPlayer.getUniqueID(), 0);

                    int old = this.timeCheck.get(entityPlayer.getUniqueID());
                    if (old > 100) {
                        if (!this.existedMoreThan5Seconds.contains(entityPlayer.getUniqueID()))
                            this.existedMoreThan5Seconds.add(entityPlayer.getUniqueID());
                    } else {
                        this.timeCheck.put(entityPlayer.getUniqueID(), old + 1);
                    }
                }
                if (loadOrRender(entityPlayer)) {
                    final UUID uuid = entityPlayer.getUniqueID();
                    if (!this.getWorldPlayers().containsKey(entityPlayer.getUniqueID()) && !this.statAssembly.contains(uuid)) {
                        this.statAssembly.add(uuid);
                        this.fetchStats(entityPlayer);
                        this.checkCacheSize();
                    }
                }
            }
        }
    }

    public void checkCacheSize() {
        int max = Math.max(150, 500);
        if (getWorldPlayers().size() > max) {
            List<UUID> safePlayers = new ArrayList<>();
            for (EntityPlayer player : mc.theWorld.playerEntities) {
                if (this.existedMoreThan5Seconds.contains(player.getUniqueID())) {
                    safePlayers.add(player.getUniqueID());
                }
            }

            this.existedMoreThan5Seconds.clear();
            this.existedMoreThan5Seconds.addAll(safePlayers);

            for (UUID playerUUID : this.getWorldPlayers().keySet()) {
                if (!safePlayers.contains(playerUUID)) {
                    this.removePlayer(playerUUID);
                }
            }
        }
    }

    public void clearCache  () {
        this.clearPlayers();
        this.existedMoreThan5Seconds.clear();
        this.statAssembly.clear();
    }
}
