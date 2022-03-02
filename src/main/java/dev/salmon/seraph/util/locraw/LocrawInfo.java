package dev.salmon.seraph.util.locraw;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class LocrawInfo {

    @SerializedName("server")
    private String serverId;

    @SerializedName("mode")
    private String gameMode = "lobby";

    @SerializedName("map")
    private String mapName;

    @SerializedName("gametype")
    private String rawGameType;
    private GameType gameType;

    public enum GameType {
        UNKNOWN(""),
        LIMBO("LIMBO"),
        BEDWARS("BEDWARS"),
        SKYWARS("SKYWARS"),
        PROTOTYPE("PROTOTYPE"),
        SKYBLOCK("SKYBLOCK"),
        MAIN("MAIN"),
        MURDER_MYSTERY("MURDER_MYSTERY"),
        HOUSING("HOUSING"),
        ARCADE_GAMES("ARCADE"),
        BUILD_BATTLE("BUILD_BATTLE"),
        DUELS("DUELS"),
        PIT("PIT"),
        UHC_CHAMPIONS("UHC"),
        SPEED_UHC("SPEED_UHC"),
        TNT_GAMES("TNTGAMES"),
        CLASSIC_GAMES("LEGACY"),
        COPS_AND_CRIMS("MCGO"),
        BLITZ_SG("SURVIVAL_GAMES"),
        MEGA_WALLS("WALLS3"),
        SMASH_HEROES("SUPER_SMASH"),
        WARLORDS("BATTLEGROUND");

        private final String serverName;
        private static final GameType[] typeArray = values();

        GameType(String serverName) {
            this.serverName = serverName;
        }

        public static GameType getFromLocraw(String gameType) {
            for (GameType value : typeArray) {
                if (value.serverName.equals(gameType)) {
                    return value;
                }
            }

            return UNKNOWN;
        }

        public String getServerName() {
            return serverName;
        }

    }

    /**
     * @return The serverID of the server you are currently on, ex: mini121
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * @return The GameType of the server as a String.
     */
    public String getRawGameType() {
        return rawGameType;
    }

    /**
     * @return The GameMode of the server, ex: solo_insane
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * @param gameType The GameType to set it to.
     */
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    /**
     * @return The GameType of the server as an Enum.
     */
    public GameType getGameType() {
        return gameType;
    }

    /**
     * @return The map of the server, ex: Shire.
     */
    public String getMapName() {
        return mapName;
    }

}
