package dev.salmon.seraph.config;

import dev.salmon.seraph.Seraph;
import gg.essential.universal.ChatColor;
import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;
import org.omg.CORBA.INITIALIZE;

import java.io.File;

public class SeraphConfig extends Vigilant {

    // setting the file and name of the file. Seraph.NAME will appear at the top of the config screen.
    public SeraphConfig() {
        super(new File("./config", Seraph.ID + ".toml"), ChatColor.GOLD + Seraph.NAME);
        initialize();
    }

    @Property(
            type = PropertyType.TEXT,
            name = "API Key",
            description = "Hypixel API key used to fetch stats.",
            category = "General",
            placeholder = "/api new, or /seraph apikey <apikey>",
            protectedText = true
    )
    private String apiKey = ""; // cannot initialize a variable with itself ( getApiKey() )

    // the api key that will be used.
    public String getApiKey() { return this.apiKey; }
    // set api key method, used in the api key sub command, and api key listener.
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

}
