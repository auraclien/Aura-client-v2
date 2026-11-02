package com.aura.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;

public class AuraClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        // Регистрируем команду /menu
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("menu").executes(context -> {
                // Открываем наше меню
                net.minecraft.client.MinecraftClient.getInstance().setScreen(new AuraMainScreen());
                return 1;
            }));
        });
    }
}
