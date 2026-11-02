package com.aura.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class AuraMainScreen extends Screen {
    private final int menuWidth = 480;
    private final int menuHeight = 300;
    private int startX;
    private int startY;
    private int selectedTab = 0;

    public AuraMainScreen() {
        super(Text.literal("Aura Client"));
    }

    @Override
    protected void init() {
        super.init();
        this.startX = (this.width - menuWidth) / 2;
        this.startY = (this.height - menuHeight) / 2;

        int tabX = startX + 10;
        int tabY = startY + 50;

        this.addDrawableChild(new AuraButton(tabX, tabY, 100, 20, Text.literal("Combat"), button -> this.selectedTab = 0));
        this.addDrawableChild(new AuraButton(tabX, tabY + 30, 100, 20, Text.literal("Visuals"), button -> this.selectedTab = 1));
        this.addDrawableChild(new AuraButton(tabX, tabY + 60, 100, 20, Text.literal("Interface"), button -> this.selectedTab = 2));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.applyBlur();
        context.fill(startX, startY, startX + menuWidth, startY + menuHeight, 0xEE050505);
        context.drawVerticalLine(startX + 120, startY + 40, startY + menuHeight - 10, 0xFF333333);
        context.drawVerticalLine(startX + 300, startY + 40, startY + menuHeight - 10, 0xFF333333);

        context.drawTextWithShadow(this.textRenderer, "Категории", startX + 10, startY + 15, 0xAAAAAA);
        context.drawTextWithShadow(this.textRenderer, "Модули", startX + 130, startY + 15, 0xAAAAAA);
        context.drawTextWithShadow(this.textRenderer, "Настройки", startX + 310, startY + 15, 0xAAAAAA);

        if (selectedTab == 0) {
            // Вкладка Combat и наша KillAura
            context.fill(startX + 130, startY + 45, startX + 290, startY + 65, 0xFF1A1A1A);
            context.drawTextWithShadow(this.textRenderer, "KillAura", startX + 140, startY + 51, 0xFFFFFF);
            context.fill(startX + 270, startY + 50, startX + 285, startY + 60, 0xFFAA0000); // Красный квадратик (вкл)
        } else if (selectedTab == 1) {
            context.drawTextWithShadow(this.textRenderer, "FullBright", startX + 130, startY + 50, 0xFFFFFF);
        } else if (selectedTab == 2) {
            context.drawTextWithShadow(this.textRenderer, "Theme: Dark", startX + 130, startY + 50, 0xFFFFFF);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
