package dev.salmon.seraph;

import dev.salmon.seraph.command.SeraphCommand;
import dev.salmon.seraph.config.SeraphConfig;
import dev.salmon.seraph.listener.ApiKeyListener;
import dev.salmon.seraph.listener.LocrawListener;
import dev.salmon.seraph.listener.PlayerGrabberListener;
import dev.salmon.seraph.util.CommandQueue;
import dev.salmon.seraph.util.chat.ChatColor;
import dev.salmon.seraph.util.locraw.LocrawInfo;
import dev.salmon.seraph.util.locraw.LocrawUtil;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Seraph.ID, name = Seraph.NAME, version = Seraph.VER)
public class Seraph {

    public static final String NAME = "@NAME@", VER = "@VER@", ID = "@ID@";
    public static String SeraphPrefix = ChatColor.GOLD + "Seraph " + ChatColor.DARK_GRAY + "» ";

    @Mod.Instance(ID)
    public static Seraph Instance;

    private final CommandQueue commandQueue = new CommandQueue();
    private final LocrawInfo locrawInfo = new LocrawInfo();
    private final LocrawUtil locrawUtil = new LocrawUtil();
    private SeraphConfig config;

    public static void registerListeners(Object... objects) {
        for (Object o : objects) {
            MinecraftForge.EVENT_BUS.register(o);
        }
    }

    @Mod.EventHandler
    protected void onInitialization(FMLInitializationEvent event) {
        this.config = new SeraphConfig();
        this.config.preload();

        registerListeners(
                new ApiKeyListener(),
                this.locrawUtil,
                this.commandQueue,
                new LocrawListener(),
                new PlayerGrabberListener()
        );

        ClientCommandHandler.instance.registerCommand(new SeraphCommand());
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
