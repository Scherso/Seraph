
package dev.salmon.seraph;

import dev.salmon.seraph.command.SeraphConfigCommand;
import dev.salmon.seraph.config.SeraphConfig;
import dev.salmon.seraph.listener.ApiKeyListener;
import gg.essential.vigilance.Vigilance;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Seraph.ID, name = Seraph.NAME, version = Seraph.VER)
public class Seraph {

    public static final String NAME = "@NAME@", VER = "@VER@", ID = "@ID@";
    // instance of the main class.
    @Mod.Instance private static Seraph instance;
    private SeraphConfig config;

    public static Seraph getInstance() {
        return instance;
    }

    @Mod.EventHandler
    protected void onInitialization(FMLInitializationEvent event) {
        // registering and loading configs and commands.
        new SeraphConfigCommand().register();
        Vigilance.initialize();
        this.config = new SeraphConfig();
        this.config.preload();
        registerListeners(new ApiKeyListener());

    }

    // registering listeners to be used.
    public static void registerListeners(Object... objects) {
        for (Object o : objects) {
            MinecraftForge.EVENT_BUS.register(o);
        }
    }

    // instance of the config.
    public SeraphConfig getConfig() {
        return config;
    }

}
