package dev.salmon.seraph;

import dev.salmon.seraph.command.SeraphCommand;
import dev.salmon.seraph.config.SeraphConfig;
import dev.salmon.seraph.listener.QueueListener;
import dev.salmon.seraph.listener.ApiKeyListener;
import dev.salmon.seraph.util.CommandQueue;
import dev.salmon.seraph.util.locraw.LocrawInfo;
import dev.salmon.seraph.util.locraw.LocrawUtil;
import gg.essential.universal.ChatColor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;

@Mod(modid = Seraph.ID, name = Seraph.NAME, version = Seraph.VER)
public class Seraph {

    public static final String NAME = "@NAME@", VER = "@VER@", ID = "@ID@";
    public static String SeraphPrefix = ChatColor.GOLD + "Seraph " + ChatColor.DARK_GRAY + "» ";

    @SuppressWarnings("unused")
    @Mod.Instance private static Seraph instance;
    private SeraphConfig config;
    private final CommandQueue commandQueue = new CommandQueue();
    private final LocrawInfo locrawInfo = new LocrawInfo();
    private final LocrawUtil locrawUtil = new LocrawUtil();

    @Mod.EventHandler
    protected void onInitialization(FMLInitializationEvent event) {
        new SeraphCommand().register();
        this.config = new SeraphConfig();
        this.config.preload();

        registerListeners();
    }

    private void registerListeners() {
        final EventBus eventBus = MinecraftForge.EVENT_BUS;

        eventBus.register(new ApiKeyListener());
        eventBus.register(new QueueListener());
        eventBus.register(locrawUtil);
        eventBus.register(commandQueue);
    }

    public static Seraph getInstance() {
        return instance;
    }

    public SeraphConfig getConfig() {
        return this.config;
    }

    public CommandQueue getCommandQueue() {
        return this.commandQueue;
    }

    public LocrawInfo getLocrawInfo() {
        return this.locrawInfo;
    }

    public LocrawUtil getLocrawUtil() {
        return this.locrawUtil;
    }

}
