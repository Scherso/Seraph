package com.github.scherso.seraph.util.location

import com.google.gson.annotations.SerializedName

class LocrawInfo {

    /**
     * @return The server ID from the serialized JSON.
     */
    @SerializedName("server")
    val serverId: String? = null

    /**
     * @return The mode name from the serialized JSON.
     */
    @SerializedName("mode")
    val gameMode = "lobby"

    /**
     * @return The map name from the serialized JSON.
     */
    @SerializedName("map")
    val mapName: String? = null

    /**
     * @return The gametype from the serialized JSON as [GameType] object.
     */
    @SerializedName("gametype")
    var gameType: GameType? = null

    /**
     * @return The raw gametype from the serialized JSON as a [String] object.
     */
    val rawGameType: String? = null

    enum class GameType(val serverName: String) {
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
            fun getFromLocraw(gameType: String): GameType {
                for (value in values())
                    if (value.serverName == gameType)
                        return value
                return UNKNOWN
            }
        }
    }

}