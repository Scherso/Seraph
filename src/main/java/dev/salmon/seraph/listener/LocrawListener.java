package dev.salmon.seraph.listener;

import dev.salmon.seraph.listener.event.LocrawEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LocrawListener {
    @SubscribeEvent
    public void onJoin(LocrawEvent.JoinGame event) {
        System.out.println("joined gametype: " + event.getLocraw().getGameType().toString() + " mode: " + event.getLocraw().getGameMode());
    }

    @SubscribeEvent
    public void onJoinLobby(LocrawEvent.JoinLobby event) {
        System.out.println("joined lobby " + event.getLocraw().getGameType().toString());
    }
}