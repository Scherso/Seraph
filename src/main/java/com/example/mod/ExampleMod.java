package com.example.mod;

import club.maxstats.weave.loader.api.ModInitializer;
import club.maxstats.weave.loader.api.command.CommandBus;
import club.maxstats.weave.loader.api.event.*;
import com.example.mod.command.TestCommand;
import com.example.mod.listener.RenderGameOverlayListener;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.input.Keyboard;

public class ExampleMod implements ModInitializer {
    @Override
    public void init() {
        System.out.println("Initializing ExampleMod!");

        CommandBus.register(new TestCommand());

        EventBus.subscribe(KeyboardEvent.class, e -> {
            if (Minecraft.getMinecraft().currentScreen == null && e.getKeyState()) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(
                        new ChatComponentText("Key Pressed: " + Keyboard.getKeyName(e.getKeyCode()))
                );
            }
        });
        EventBus.subscribe(RenderHandEvent.class, e -> e.setCancelled(true));

        EventBus.subscribe(new RenderGameOverlayListener());
    }
}