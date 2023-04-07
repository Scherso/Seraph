package com.github.scherso.seraph

import club.maxstats.weave.loader.api.ModInitializer
import club.maxstats.weave.loader.api.event.EventBus
import com.github.scherso.seraph.listener.ApiKeyListener
import com.github.scherso.seraph.listener.LocrawListener
import com.github.scherso.seraph.util.location.LocrawUtils
import java.util.ArrayList

internal const val ID      = "@ID@"
internal const val NAME    = "@NAME@"
internal const val VERSION = "@VER@"

class Seraph : ModInitializer {

    /**
     * Registering events listeners.
     */
    override fun init() {
        EventBus.subscribe(LocrawUtils())
        EventBus.subscribe(ApiKeyListener())
        EventBus.subscribe(LocrawListener())
    }
}
