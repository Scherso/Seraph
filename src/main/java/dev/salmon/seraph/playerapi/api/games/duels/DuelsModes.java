package dev.salmon.seraph.playerapi.api.games.duels;

/* custom enum for duels api retrieval because the api is embarrassing, and probably made by a 10 year old. */
public enum DuelsModes {
    ALL_MODES("Overall", "", "current_winstreak", "best_overall_winstreak", "wins", "losses", "kills", "deaths"),
    OVERALL("Overall", "", "current_winstreak", "best_overall_winstreak", "wins", "losses", "kills", "deaths"),
    UHC("UHC", "uhc", "current_uhc_winstreak", "best_uhc_winstreak", "uhc_duel_wins", "uhc_duel_losses", "uhc_duel_kills", "uhc_duel_deaths"),
    NO_DEBUFF("No-Debuff", "no_debuff", "current_no_debuff_winstreak", "best_no_debuff_winstreak", "potion_duel_wins", "potion_duel_losses", "potion_duel_kills", "potion_duel_deaths"),
    POTION("No-Debuff", "no_debuff", "current_no_debuff_winstreak", "best_no_debuff_winstreak", "potion_duel_wins", "potion_duel_losses", "potion_duel_kills", "potion_duel_deaths"),
    SUMO("Sumo", "sumo", "current_sumo_winstreak", "best_sumo_winstreak", "sumo_duel_wins", "sumo_duel_losses", "sumo_duel_kills", "sumo_duel_deaths"),
    CLASSIC("Classic", "classic", "current_classic_winstreak", "best_classic_winstreak", "classic_duel_wins", "classic_duel_losses", "classic_duel_kills", "classic_duel_deaths"),
    COMBO("Combo", "combo", "current_combo_winstreak", "best_combo_winstreak", "combo_duel_wins", "combo_duel_losses", "combo_duel_kills", "combo_duel_deaths"),
    BOW("Bow", "bow", "current_bow_winstreak", "best_bow_winstreak", "bow_duel_wins", "bow_duel_losses", "bow_duel_kills", "bow_duel_deaths"),
    SKYWARS("Skywars", "skywars", "current_skywars_winstreak", "best_skywars_winstreak", "sw_duel_wins", "sw_duel_losses", "sw_duel_kills", "sw_duel_deaths"),
    SW("Skywars", "skywars", "current_skywars_winstreak", "best_skywars_winstreak", "sw_duel_wins", "sw_duel_losses", "sw_duel_kills", "sw_duel_deaths"),
    BLITZ("Blitz", "blitz", "current_blitz_winstreak", "best_blitz_winstreak", "blitz_duel_wins", "blitz_duel_losses", "blitz_duel_kills", "blitz_duel_deaths"),
    MEGAWALLS("Mega Walls", "mega_walls", "current_mega_walls_winstreak", "best_mega_walls_winstreak", "mw_duel_wins", "mw_duel_losses",  "mw_duel_kills", "mw_duel_deaths"),
    MEGA_WALLS("Mega Walls", "mega_walls", "current_mega_walls_winstreak", "best_mega_walls_winstreak", "mw_duel_wins", "mw_duel_losses",  "mw_duel_kills", "mw_duel_deaths"),
    MW("Mega Walls", "mega_walls", "current_mega_walls_winstreak", "best_mega_walls_winstreak", "mw_duel_wins", "mw_duel_losses",  "mw_duel_kills", "mw_duel_deaths"),
    BOWSPLEEF("Bow Spleef", "tnt_games", "current_tnt_games_winstreak", "best_tnt_games_winstreak", "bowspleef_duel_wins", "bowspleef_duel_losses", "bowspleef_duel_kills", "bowspleef_duel_deaths"),
    TNT_GAMES("Bow Spleef", "tnt_games", "current_tnt_games_winstreak", "best_tnt_games_winstreak", "bowspleef_duel_wins", "bowspleef_duel_losses", "bowspleef_duel_kills", "bowspleef_duel_deaths"),
    BRIDGE("Bridge", "bridge", "current_bridge_winstreak", "best_bridge_winstreak", "bridge_duel_wins", "bridge_duel_losses", "bridge_duel_kills", "bridge_duel_deaths"),
    BOXING("Boxing", "boxing", "current_winstreak", "best_overall_winstreak", "boxing_duel_wins", "boxing_duel_losses", "boxing_duel_kills", "boxing_duel_deaths"),
    OP("OP", "op", "current_op_winstreak", "best_op_winstreak", "op_duel_wins", "op_duel_losses", "op_duel_kills", "op_duel_deaths"),
    PARKOUR("Parkour", "parkour", "current_winstreak", "best_overall_winstreak", "wins", "losses", "kills", "deaths");


    String name, titleName, wsJson, bestWsJson, winsJson, lossesJson, killsJson, deathsJson;
    DuelsModes(String name, String titleName, String wsJson, String bestWsJson, String winsJson, String lossesJson, String killsJson, String deathsJson) {
        this.name = name;
        this.titleName = titleName;
        this.wsJson = wsJson;
        this.bestWsJson = bestWsJson;
        this.winsJson = winsJson;
        this.lossesJson = lossesJson;
        this.killsJson = killsJson;
        this.deathsJson = deathsJson;
    }

    public String getName() { return this.name; }

    public String getTitleName() { return this.titleName; }

    public String getWSJson() { return this.wsJson; }

    public String getBestWSJson() { return this.bestWsJson; }

    public String getWinsJson() { return this.winsJson; }

    public String getLossesJson() { return this.lossesJson; }

    public String getKillsJson() { return this.killsJson; }

    public String getDeathsJson() { return this.deathsJson; }
}
