package com.github.scherso.seraph.util.location

import club.maxstats.weave.loader.api.event.ChatReceivedEvent
import club.maxstats.weave.loader.api.event.RenderWorldEvent
import club.maxstats.weave.loader.api.event.SubscribeEvent
import club.maxstats.weave.loader.api.event.TickEvent
import com.github.scherso.seraph.util.Multithreading
import com.github.scherso.seraph.util.isOnHypixel
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import net.minecraft.client.Minecraft
import java.util.concurrent.TimeUnit

/**
 * @author Scherso, (Sam)
 */
class LocrawUtils {

    private var tick:       Int         = 0
    private var loop:       Int         = 0

    private var permitted:  Boolean     = false
    private var sent:       Boolean     = false
    private var inGame:     Boolean?    = null
    private var inDuel:     Boolean?    = null

    private var locraw:     LocrawInfo? = null
    private var lastLocraw: LocrawInfo? = null

    /**
     * Queues a locraw request.
     *
     * @param interval The interval in milliseconds.
     */
    private fun enqueue(interval: Long) {
        this.permitted = true
        Multithreading().schedule({
            if (this.permitted)
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/locraw")
        }, interval, TimeUnit.MILLISECONDS)
    }

    /**
     * Effectively runs '/locraw' in chat every 5 seconds.
     */
    @SubscribeEvent
    fun onTickEvent(event: TickEvent) {
        tick++
        if (tick % 20 == 0) {
            tick = 0
            if (isOnHypixel && !sent) {
                enqueue(5000)
                sent = true
            }
        }
    }

    /**
     * Reset on every world render, as loading a new world
     * indirectly insinuates that the player has updated their location.
     *
     * @param event [RenderWorldEvent]
     */
    @SubscribeEvent
    fun onWorldLoadEvent(event: RenderWorldEvent) {
        locraw    = null
        permitted = false
        sent      = false
        loop      = 0
    }

    @SubscribeEvent
    fun onChatReceivedEvent(event: ChatReceivedEvent) {
        if (!sent) return

        val message = event.message.unformattedText

        if (!message.startsWith("{")) {
            if (message.contains("You are sending too many commands! Please try again in a few seconds."))
                enqueue(5000)

            return
        }

        val raw:    JsonElement = JsonParser().parse(message) ?: return
        val json:   JsonObject  = raw.asJsonObject
        val parsed: LocrawInfo  = GsonBuilder().create().fromJson(json, LocrawInfo::class.java)

        // Basic limbo check
        if (loop > 5 && parsed.gameType == LocrawInfo.GameType.LIMBO) {
            sent = false
            loop++
            enqueue(1000)
        } else locraw = parsed // If the player is not in limbo, set their current location info the parsed var.

        if (locraw != null) {
            // My attempt at setting 'gameType' to the correct enum value.
            locraw!!.gameType to LocrawInfo.GameType.getFromLocraw(locraw!!.rawGameType!!)
            @Suppress("KotlinConstantConditions")
            if (parsed.gameMode != "lobby") {
                inGame     = true
                inDuel     = parsed.gameType == LocrawInfo.GameType.DUELS
                lastLocraw = parsed
            } else inGame = false
        }

        event.cancelled = true
    }
}
