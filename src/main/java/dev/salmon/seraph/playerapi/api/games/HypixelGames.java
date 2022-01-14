package dev.salmon.seraph.playerapi.api.games;

public enum HypixelGames {
    BEDWARS("Bedwars", "BEDWARS"),
    UHC("UHC", "UHC"),
    SKYBLOCK("Skyblock", "SKYBLOCK"),
    SKYWARS("SkyWars", "SKYWARS"),
    ARENABRAWL("Arena", "ARENA"),
    VAMPIREZ("VampireZ", "VAMPIREZ"),
    BLITZ("HungerGames", "BLITZ"),
    DUELS("Duels", "DUELS"),
    PIT("Pit", "PIT");

    private String apiName, gameName;

    /**
     * @param apiName Name of the Game in Hypixel's API
     * @param gameName Name of the Game which best suits the Player
     */
    HypixelGames(String apiName, String gameName) {
        this.apiName = apiName;
        this.gameName = gameName;
    }

    public String getApiName() { return this.apiName; }

    public String getGameName() { return this.gameName; }
}
