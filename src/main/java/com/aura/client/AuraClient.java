package com.aura.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class AuraClient implements ClientModInitializer {
    public static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        // Новый способ регистрации клавиши для 1.21.4
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.auraclient.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.auraclient.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new AuraMainScreen());
                }
            }
        });
    }
}
