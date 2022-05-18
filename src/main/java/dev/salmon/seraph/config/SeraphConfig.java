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
    ) private String apiKey = "";

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
     * @param save Whether this should be saved to the config file or not.
     */
    public void setApiKey(String apiKey, boolean save) {
        this.apiKey = apiKey;
        if (save) saveConfig();
    }

    /**
     * Sets the API key used for Hypixel API requests.
     *
     * @param apiKey The new value to set the config option to.
     */
    public void setApiKey(String apiKey) {
        setApiKey(apiKey, true);
    }

}
