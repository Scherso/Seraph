package dev.salmon.seraph;

import dev.salmon.seraph.commands.RequeueCommand;
import dev.salmon.seraph.commands.SeraphCommand;
import dev.salmon.seraph.config.SeraphConfig;
import dev.salmon.seraph.listener.ApiKeyListener;
import dev.salmon.seraph.listener.LocrawListener;
import dev.salmon.seraph.listener.PlayerGrabberListener;
import dev.salmon.seraph.util.locraw.LocrawUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = Seraph.ID, name = Seraph.NAME, version = Seraph.VER)
public class Seraph {
    public static final String NAME = "@MOD_NAME@", VER = "@MOD_VERSION@", ID = "@MOD_ID@";

    @Mod.Instance(ID)
    private static Seraph INSTANCE;

    private SeraphConfig config;

    public static void registerListeners(Object... objects) {
        for (Object o : objects) {
            MinecraftForge.EVENT_BUS.register(o);
        }
    }

    public static Seraph getInstance() {
        return INSTANCE;
    }

    @Mod.EventHandler
    private void preInitialize(FMLPreInitializationEvent event) {
        File configFile = new File(new File(event.getModConfigurationDirectory(), NAME), ID + ".toml");
        if (!configFile.getParentFile().exists() && !configFile.getParentFile().mkdirs())
            throw new IllegalStateException("Could not create config directory!");
        config = new SeraphConfig(configFile);
    }

    @Mod.EventHandler
    protected void initialize(FMLInitializationEvent event) {
        registerListeners(
                new ApiKeyListener(),
                LocrawUtils.getInstance(),
                new LocrawListener(),
                new PlayerGrabberListener()
        );

        new SeraphCommand().register();
        new RequeueCommand().register();
    }

    public SeraphConfig getConfig() {
        return config;
    }
}
