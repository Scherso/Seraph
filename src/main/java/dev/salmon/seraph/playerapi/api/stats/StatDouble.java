package dev.salmon.seraph.playerapi.api.stats;


import com.google.gson.JsonObject;

public class StatDouble extends Stat {
    private double value;

    /**
     * @param statName Name of the Stat
     * @param jsonName Json Name of the Stat in Hypixel's API
     * @param gameObject JsonObject of the desired Game Stat
     */
    public StatDouble(String statName, String jsonName, JsonObject gameObject) {
        super(statName, jsonName, gameObject);
    }

    public StatDouble(String statName) {
        super(statName);
    }

    @Override
    public void setStat() {
        try {
            this.value = Double.parseDouble(gameObject.get(jsonName).getAsString());
        } catch (Exception ex) {
            this.value = 0;
        }
    }

    public void setValue(double value) { this.value = value; }

    public double getValue() { return this.value; }

    @Override
    public StatType getType() {
        return StatType.DOUBLE;
    }
}
