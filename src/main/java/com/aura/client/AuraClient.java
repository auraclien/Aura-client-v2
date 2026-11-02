package com.aura.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class AuraClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        // Добавляем кнопку прямо в меню паузы (Esc)
        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof GameMenuScreen) {
                Screens.getButtons(screen).add(ButtonWidget.builder(Text.literal("Aura Client"), button -> {
                    client.setScreen(new AuraMainScreen());
                }).dimensions(10, 10, 100, 20).build());
            }
        });
    }
}
