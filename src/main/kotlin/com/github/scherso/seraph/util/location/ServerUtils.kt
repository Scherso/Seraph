package com.github.scherso.seraph.util.location

import net.minecraft.client.Minecraft

class ServerUtils {
    private fun getServerId(): String {
        return Minecraft.getMinecraft().currentServerData.serverIP
    }

    /**
     * @return Whether the player is on Hypixel or not.
     */
    val isOnHypixel: Boolean
        get() {
            return getServerId().contains("hypixel.net")
        }
}