package com.github.scherso.seraph.util

import net.minecraft.client.Minecraft

/**
 * @return Whether the player is on Hypixel or not.
 */
val isOnHypixel: Boolean
    get() {
        return Minecraft.getMinecraft().currentServerData
            .serverIP.contains("hypixel.net")
    }
