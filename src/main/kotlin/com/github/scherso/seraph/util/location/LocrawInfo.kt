package com.github.scherso.seraph.util.location

import com.google.gson.annotations.SerializedName

/**
 * @author Scherso ([...](https://github.com/scherso/))
 */
class LocrawInfo(
    @SerializedName("server")
    val serverId: String? = null,
    @SerializedName("mode")
    val gameMode: String? = null,
    @SerializedName("map")
    val mapName: String? = null,

    /**
     * @return The raw gametype from the serialized JSON as a [String] object.
     *         Not as a [GameType] object like you might expect.
     */
    @SerializedName("gametype")
    val rawGameType: String? = null
) {
    val gameType = GameType.fromLocraw(rawGameType!!)
    companion object {
        @JvmStatic
        val LIMBO = LocrawInfo("limbo", "", "", "LIMBO")
    }
}

/**
 * @author Scherso ([...](https://github.com/scherso/))
 */
enum class GameType(val gameType: String) {

    ARCADE_GAMES   ("ARCADE"),
    BEDWARS        ("BEDWARS"),
    BLITZ_SG       ("SURVIVAL_GAMES"),
    BUILD_BATTLE   ("BUILD_BATTLE"),
    CLASSIC_GAMES  ("LEGACY"),
    COPS_AND_CRIMS ("MCGO"),
    DUELS          ("DUELS"),
    HOUSING        ("HOUSING"),
    LIMBO          ("LIMBO"),
    MAIN           ("MAIN"),
    MEGA_WALLS     ("WALLS3"),
    MURDER_MYSTERY ("MURDER_MYSTERY"),
    PIT            ("PIT"),
    PROTOTYPE      ("PROTOTYPE"),
    SKYBLOCK       ("SKYBLOCK"),
    SKYWARS        ("SKYWARS"),
    SMASH_HEROES   ("SUPER_SMASH"),
    SPEED_UHC      ("SPEED_UHC"),
    TNT_GAMES      ("TNTGAMES"),
    UHC_CHAMPIONS  ("UHC"),
    UNKNOWN        (""),
    WARLORDS       ("BATTLEGROUND");

    companion object {

        /**
         * Grabs the [GameType] from Locraw. (Location Raw)
         *
         * @param gameType the [GameType] to check.
         * @return The respective value.
         */
        @JvmStatic
        fun fromLocraw(gameType: String): GameType {
            for (value in values()) {
                if (value.gameType == gameType)
                    return value
            }
            return UNKNOWN
        }
    }
}
