package com.github.scherso.seraph.event

import club.maxstats.weave.loader.api.event.Event
import com.github.scherso.seraph.util.location.LocrawInfo

/**
 * @author Scherso ([...](https://github.com/Scherso))
 * @see Event
 */
open class LocrawEvent(val locraw: LocrawInfo) : Event() {

    /**
     * Posts from [com.github.scherso.seraph.util.location.LocrawUtils.onChatReceivedEvent]
     * on the event that a player joins a game.
     *
     * @param locraw Location Raw, the location of the player.
     * @see com.github.scherso.seraph.util.location.LocrawUtils
     */
    class JoinGame(locraw: LocrawInfo) : LocrawEvent(locraw)

    /**
     * Posts from [com.github.scherso.seraph.util.location.LocrawUtils.onChatReceivedEvent]
     * on the event that a player joins a lobby.
     *
     * @param locraw Location Raw, the location of the player.
     * @see com.github.scherso.seraph.util.location.LocrawUtils
     */
    class JoinLobby(locraw: LocrawInfo) : LocrawEvent(locraw)
}
