package dev.salmon.seraph.playerapi.api.stats;

import com.google.gson.JsonObject;

public abstract class Stat {
    protected String statName, jsonName;
    protected JsonObject gameObject;

    public Stat(String statName, String jsonName, JsonObject gameObject) {
        this.statName = statName;
        this.jsonName = jsonName;
        this.gameObject = gameObject;
    }

    public Stat(String statName) {
        this.statName = statName;
    }

    //    Need to add GameMode support
    public abstract void setStat();

    public String getJsonName() { return this.jsonName; }

    public String getStatName() { return this.statName; }

    public abstract StatType getType();
}
