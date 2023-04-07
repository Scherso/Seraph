package com.github.scherso.seraph.listener

import club.maxstats.weave.loader.api.event.SubscribeEvent
import com.github.scherso.seraph.event.LocrawEvent

class LocrawListener {

    @SubscribeEvent
    fun onJoinGame(event: LocrawEvent.JoinGame) {
        System.out.printf("%s-5s%s-5s%s", "GAME", event.locraw.gameType, "MODE", event.locraw.gameMode)
    }

    @SubscribeEvent
    fun onJoinLobby(event: LocrawEvent.JoinLobby) {
//      Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText("You have joined the ${event.locraw.gameType} lobby."))
        System.out.printf("%s-5s%s-5s%s", "LOBBY", event.locraw.gameType, "MODE", event.locraw.gameMode)
    }
}
