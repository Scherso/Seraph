package com.example.mod.util.locraw;

import com.google.gson.annotations.SerializedName;

/**
 * LocrawInfo class.
 *
 * @author Scherso (Sam).
 */
public class LocrawInfo {

    /**
     * The server ID from serialized JSON.
     */
    @SerializedName("server")
    private String serverId;

    /**
     * The mode name from serialized JSON.
     */
    @SerializedName("mode")
    private String gameMode = "lobby";

    /**
     * The map name from serialized JSON.
     */
    @SerializedName("map")
    private String mapName;

    /**
     * The raw gametype from serialized JSON as a {@link String} object.
     */
    @SerializedName("gametype")
    private String rawGameType;

    /**
     * The gametype from serialized JSON as {@link GameType} object.
     */
    @SerializedName("gametype")
    private GameType gameType;

    /**
     * @return {@link #serverId}.
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * @return {@link #gameMode}
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * @return {@link #mapName}.
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * @return {@link #rawGameType}.
     */
    public String getRawGameType() {
        return rawGameType;
    }

    /**
     * @return {@link #gameType}.
     */
    public GameType getGameType() {
        return gameType;
    }

    /**
     * Sets the {@code gameType} to its argument.
     *
     * @param gameType the {@link #gameType} to set.
     */
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public enum GameType {
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

        private final String serverName;

        GameType(String serverName) {
            this.serverName = serverName;
        }

        /**
         * Grabs the {@link GameType} from Locraw. (location raw)
         *
         * @param gameType The {@link GameType} to check.
         * @return The respective value.
         */
        public static GameType getFromLocraw(String gameType) {
            for (GameType value : values())
                if (value.serverName.equals(gameType))
                    return value;

            return UNKNOWN;
        }

        /**
         * @return the gametype as {@link #serverName}.
         */
        public String getServerName() {
            return  serverName;
        }
    }


}
