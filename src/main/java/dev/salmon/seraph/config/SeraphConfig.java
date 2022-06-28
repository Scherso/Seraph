package dev.salmon.seraph.config;

import dev.salmon.seraph.Seraph;
import gg.essential.universal.ChatColor;
import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

import java.io.File;

public class SeraphConfig extends Vigilant {

    @Property(
            type = PropertyType.TEXT,
            name = "API Key",
            description = "Hypixel API key used to make requests to the Hypixel API.",
            category = "General",
            placeholder = "/api new, or /seraph apikey <apikey>",
            protectedText = true
    )
    private String apiKey = "";

    @Property(
            type = PropertyType.NUMBER,
            name = "Dodge WLR",
            description = "Dodges WLR above given value. ",
            category = "Autododge"
    )
    private int dodgeWlr;

    @Property(
            type = PropertyType.NUMBER,
            name = "Dodge KDR",
            description = "Dodges KDR above given value. ",
            category = "Autododge"
    )
    private int dodgeKdr;

    @Property(
            type = PropertyType.NUMBER,
            name = "Dodge wins",
            description = "Dodges wins above given value. ",
            category = "Autododge"
    )
    private int dodgeWins;

    @Property(
            type = PropertyType.NUMBER,
            name = "Dodge kills",
            description = "Dodges kills above given value. ",
            category = "Autododge"
    )
    private int dodgeKills;

    public SeraphConfig(File configFile) {
        super(configFile, ChatColor.GOLD + Seraph.NAME);
        initialize();
    }

    private void saveConfig() {
        markDirty();
        writeData();
    }

    /**
     * @return The API key that will be used.
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the API key used for Hypixel API requests.
     *
     * @param apiKey The new value to set the config option to.
     */
    public void setApiKey(String apiKey) {
        setApiKey(apiKey, true);
    }

    /**
     * Sets the API key used for Hypixel API requests.
     *
     * @param apiKey The new value to set the config option to.
     * @param save   Whether this should be saved to the config file or not.
     */
    public void setApiKey(String apiKey, boolean save) {
        this.apiKey = apiKey;
        if (save) saveConfig();
    }

    public void setDodgeKdr(int dodgeKdr) {
        this.dodgeKdr = dodgeKdr;
    }

    public void setDodgeKills(int dodgeKills) {
        this.dodgeKills = dodgeKills;
    }

    public void setDodgeWins(int dodgeWins) {
        this.dodgeWins = dodgeWins;
    }

    public void setDodgeWlr(int dodgeWlr) {
        this.dodgeWlr = dodgeWlr;
    }

    public int getDodgeKdr() {
        return dodgeKdr;
    }

    public int getDodgeKills() {
        return dodgeKills;
    }

    public int getDodgeWins() {
        return dodgeWins;
    }

    public int getDodgeWlr() {
        return dodgeWlr;
    }
}