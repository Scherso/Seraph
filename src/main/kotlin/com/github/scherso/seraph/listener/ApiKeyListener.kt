package com.github.scherso.seraph.listener

import club.maxstats.weave.loader.api.event.ChatReceivedEvent
import club.maxstats.weave.loader.api.event.SubscribeEvent
import com.github.scherso.seraph.util.isOnHypixel

class ApiKeyListener {

    @SubscribeEvent
    fun onChatReceived(event: ChatReceivedEvent) {
        if (event.message.unformattedText.startsWith("Your API key is ") && isOnHypixel) {
            val apiKey = event.message.unformattedText.replace("Your API key is ", "")
            // TODO: Save this API key to a configuration file.
        }
    }
}
