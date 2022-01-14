package dev.salmon.seraph.playerapi.api.stats;

import com.google.gson.JsonObject;

public class StatString extends Stat {
    private String value;

    /**
     * @param statName Name of the Stat
     * @param jsonName Json Name of the Stat in Hypixel's API
     * @param gameObject JsonObject of the desired Game Stat
     */
    public StatString(String statName, String jsonName, JsonObject gameObject) {
        super(statName, jsonName, gameObject);
    }

    public StatString(String statName, String stat) {
        super(statName);
        this.value = stat;
    }

    public StatString(String statName) {
        super(statName);
    }

    @Override
    public void setStat() {
        try {
            this.value = gameObject.get(jsonName).getAsString();
        } catch (Exception ex) {
            this.value = "";
        }
    }

    public void setValue(String value) { this.value = value; }

    public String getValue() { return this.value; }

    @Override
    public StatType getType() {
        return StatType.STRING;
    }
}
