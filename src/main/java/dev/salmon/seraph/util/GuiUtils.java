package dev.salmon.seraph.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class GuiUtils {
    private static GuiUtils INSTANCE;
    private static GuiScreen screen;

    public static void showScreen(GuiScreen screen) {
        if (INSTANCE == null)
            INSTANCE = new GuiUtils();
        GuiUtils.screen = screen;
        MinecraftForge.EVENT_BUS.register(INSTANCE);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (screen != null) {
            Minecraft.getMinecraft().displayGuiScreen(screen);
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
}
