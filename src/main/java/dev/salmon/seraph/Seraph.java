package dev.salmon.seraph;

import dev.salmon.seraph.command.RequeueCommand;
import dev.salmon.seraph.command.SeraphCommand;
import dev.salmon.seraph.config.SeraphConfig;
import dev.salmon.seraph.listener.ApiKeyListener;
import dev.salmon.seraph.listener.LocrawListener;
import dev.salmon.seraph.listener.PlayerGrabberListener;
import dev.salmon.seraph.util.chat.ChatColor;
import dev.salmon.seraph.util.locraw.LocrawUtil;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.Arrays;

@Mod(modid = Seraph.ID, name = Seraph.NAME, version = Seraph.VER)
public class Seraph {

    public static final String NAME = "@NAME@", VER = "@VERSION@", ID = "@ID@";
    public static String SeraphPrefix = ChatColor.GOLD + "Seraph " + ChatColor.DARK_GRAY + "» ";

    @Mod.Instance(ID)
    public static Seraph Instance;

    private final LocrawUtil locrawUtil = new LocrawUtil();
    private SeraphConfig config;

    public static void registerListeners(Object... objects) {
        for (Object o : objects) {
            MinecraftForge.EVENT_BUS.register(o);
        }
    }

    public static void registerCommands(ICommand... command) {
        Arrays.stream(command).forEachOrdered(ClientCommandHandler.instance::registerCommand);
    }

    @Mod.EventHandler
    protected void onInitialization(FMLInitializationEvent event) {
        this.config = new SeraphConfig();
        this.config.preload();

        registerListeners(
                new ApiKeyListener(),
                this.locrawUtil,
                new LocrawListener(),
                new PlayerGrabberListener()
        );

        registerCommands(
                new SeraphCommand(),
                new RequeueCommand()
        );

    }

    public SeraphConfig getConfig() {
        return this.config;
    }

    public LocrawUtil getLocrawUtil() {
        return this.locrawUtil;
    }

}
