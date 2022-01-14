package dev.salmon.seraph.playerapi.api.games.bedwars;

import com.google.gson.JsonObject;
import dev.salmon.seraph.playerapi.api.games.HypixelGames;
import dev.salmon.seraph.playerapi.api.stats.Stat;
import dev.salmon.seraph.playerapi.api.stats.StatInt;
import dev.salmon.seraph.playerapi.api.stats.StatString;
import dev.salmon.seraph.playerapi.exception.GameNullException;
import gg.essential.universal.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Bedwars extends BedwarsUtil {
    private JsonObject bedwarsJson, wholeObject;
    private List<Stat> statList;
    private List<Stat> formattedStatList;
    public Stat gamesPlayed, finalKills, finalDeaths, wins, losses, kills, deaths, bedsBroken, bedsLost, winstreak, star;

    public Bedwars(String playerName, String playerUUID, JsonObject wholeObject) {
        super(playerName, playerUUID);
        this.wholeObject = wholeObject;
        this.achievementObj = wholeObject.get("player").getAsJsonObject().get("achievements").getAsJsonObject();
        this.playerObject = wholeObject.get("player").getAsJsonObject();
        this.statList = new ArrayList<>();
        this.formattedStatList = new ArrayList<>();

        if (setData(HypixelGames.BEDWARS)) {
            this.statList = setStats(
                    // "bedwars_level" is the Api name of the star.
                    // If you wish to add any other stats, you add them like this, statName is whatever name you want to call it, then jsonName is the name of the stat in the API
                    // and the 3rd parameter is the json object in which this api stat resides. So winstreak is inside of the bedwars json object along with all the other bedwars
                    // statistics. For some reason, bedwars level is inside of your achievements?? idk why
                    this.star = new StatInt("Level", "bedwars_level", this.achievementObj),
                    this.winstreak = new StatInt("Winstreak", "winstreak", this.bedwarsJson),
                    this.gamesPlayed = new StatInt("Games Played", "games_played_bedwars", this.bedwarsJson),
                    this.finalKills = new StatInt("Final Kills", "final_kills_bedwars", this.bedwarsJson),
                    this.finalDeaths = new StatInt("Final Deaths", "final_deaths_bedwars", this.bedwarsJson),
                    this.wins = new StatInt("Wins", "wins_bedwars", this.bedwarsJson),
                    this.losses = new StatInt("Losses", "losses_bedwars", this.bedwarsJson),
                    this.kills = new StatInt("Kills", "kills_bedwars", this.bedwarsJson),
                    this.deaths = new StatInt("Deaths", "deaths_bedwars", this.bedwarsJson),
                    this.bedsBroken = new StatInt("Beds Broken", "beds_broken_bedwars", this.bedwarsJson),
                    this.bedsLost = new StatInt("Beds Lost", "beds_lost_bedwars", this.bedwarsJson));
        } else {
            this.formattedStatList.add(new StatString("Star", ChatColor.RED + "NICKED"));
        }
    }

    @Override
    public boolean setData(HypixelGames game) {
        this.isNicked = false;
        this.hasPlayed = false;

        try {
            JsonObject obj = getGameData(wholeObject, game);
            if (!this.isNicked) {
                this.hasPlayed = true;
                this.bedwarsJson = obj;
                return true;
            }
            return false;
        } catch (GameNullException ex) {
            if (!this.isNicked) {
                System.out.println(String.format("Maybe %s has never played %s before", getPlayerName(), game.getGameName()));
            }

            System.out.println("Failed to Set Data");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public String getFormattedStats() {
        return String.format("%s%s", getFKDRColor(getFKDR(this)), getFKDR(this));
    }

    @Override
    public HypixelGames getGame() {
        return HypixelGames.BEDWARS;
    }

    @Override
    public List<Stat> getStatList() {
        return this.statList;
    }

    // this is what we're grabbing as the stat list.
    // so the first stat in the for loop that we'll encounter is Star
    // then we'll encounter WS
    // then FKDR
    // the for loop will grab these values accordingly and store it into the statValue string
    /* retrieves the formatted stat list */
    @Override
    public List<Stat> getFormattedStatList() {


        List<Stat> returnList = new ArrayList<>(this.formattedStatList);
        StatString star = new StatString("STAR");
        star.setValue(this.getStarWithColor(((StatInt)this.star).getValue()));
        returnList.add(0, star);
        return returnList;
    }

    /* sets the formatted stat list when the player is first grabbed */
    /* only set a single time */
    @Override
    public void setFormattedStatList() {
        StatString ws = new StatString("WS");
        ws.setValue(this.getWSColor(((StatInt)this.winstreak).getValue()).toString() + ((StatInt)this.winstreak).getValue());
        this.formattedStatList.add(ws);

        StatString fkdr = new StatString("FKDR");
        fkdr.setValue(this.getFKDRColor(this.getFKDR(this)).toString() + this.getFKDR(this));
        this.formattedStatList.add(fkdr);

        StatString finals = new StatString("FINALS");
        finals.setValue(/* this sets the color >>*/ this.getFinalsColor(((StatInt)this.finalKills).getValue()).toString() + /* this is what's actually displayed >>>*/ ((StatInt)this.finalKills).getValue());
        this.formattedStatList.add(finals);

        StatString wlr = new StatString("WLR");
        wlr.setValue(this.getWLRColor(this.getWLR(this)).toString() + this.getWLR(this));
        this.formattedStatList.add(wlr);

        StatString wins = new StatString("WINS");
        wins.setValue(/* this sets the color >>*/ this.getWinsColor(((StatInt)this.wins).getValue()).toString() + /* this is what's actually displayed >>>*/ ((StatInt)this.wins).getValue());
        this.formattedStatList.add(wins);

        StatString bblr = new StatString("BBLR");
        bblr.setValue(this.getBBLRColor(this.getBBLR(this)).toString() + this.getBBLR(this));
        this.formattedStatList.add(bblr);
    }
}