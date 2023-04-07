package com.github.scherso.seraph.util

import net.minecraft.client.Minecraft
import java.util.*;

private val mc = Minecraft.getMinecraft()

/**
 * @return Whether the player is on Hypixel or not.
 */
val isOnHypixel: Boolean
    get() = if (mc.theWorld != null && !mc.isSingleplayer) mc.currentServerData.serverIP.lowercase(Locale.getDefault())
        .contains("hypixel") else false
