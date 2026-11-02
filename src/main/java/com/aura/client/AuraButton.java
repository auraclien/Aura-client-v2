package com.aura.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.client.font.TextRenderer;

public class AuraButton extends ButtonWidget {
    private float animationProgress = 0.0f;
    private boolean isHovered = false;

    public AuraButton(int x, int y, int width, int height, Text message, PressAction onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION_SUPPLIER);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        isHovered = mouseX >= this.getX() && mouseX <= this.getX() + this.width && 
                    mouseY >= this.getY() && mouseY <= this.getY() + this.height;

        if (isHovered && animationProgress < 1.0f) {
            animationProgress += 0.1f;
        } else if (!isHovered && animationProgress > 0.0f) {
            animationProgress -= 0.1f;
        }
        
        animationProgress = Math.max(0.0f, Math.min(1.0f, animationProgress));
        int yOffset = (int)(animationProgress * 2); 

        int bgColor = isHovered ? 0xFFAA0000 : 0xFF222222;
        int currentX = this.getX();
        int currentY = this.getY() + yOffset;
        
        context.fill(currentX, currentY, currentX + this.width, currentY + this.height, bgColor);

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        int textWidth = textRenderer.getWidth(this.getMessage());
        int textX = currentX + (this.width - textWidth) / 2;
        int textY = currentY + (this.height - 8) / 2;
        
        context.drawTextWithShadow(textRenderer, this.getMessage(), textX, textY, 0xFFFFFF);
    }
}
