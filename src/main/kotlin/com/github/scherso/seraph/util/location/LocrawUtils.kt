package com.github.scherso.seraph.util.location

import com.github.scherso.seraph.util.Multithreading
import net.minecraft.client.Minecraft
import java.util.concurrent.TimeUnit

class LocrawUtils {

    private var permitted: Boolean? = null
    private var sent:      Boolean? = null

    private fun enqueue(interval: Long) {
        this.permitted = true
        Multithreading().schedule({
            if (this.permitted!!)
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/locraw")
        }, interval, TimeUnit.MILLISECONDS)
    }

}