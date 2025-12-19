package com.bettercrosshair;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CrosshairRenderer {

    private static float dynamicGap = 3f;

    public static void render(DrawContext ctx, float tickDelta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.options.hudHidden) return;

        int cx = mc.getWindow().getScaledWidth() / 2;
        int cy = mc.getWindow().getScaledHeight() / 2;

        boolean moving = mc.player.forwardSpeed != 0 || mc.player.sidewaysSpeed != 0;
        dynamicGap += moving ? 0.2f : -0.2f;
        dynamicGap = Math.max(3f, Math.min(dynamicGap, 6f));

        draw(ctx, cx, cy, (int) dynamicGap);
    }

    private static void draw(DrawContext ctx, int cx, int cy, int gap) {
        int length = 5;
        int thickness = 1;
        int color = 0xFFFFFFFF;
        int outline = 0xFF000000;

        // Outline
        fill(ctx, cx - 1, cy - gap - length - 1, cx + 2, cy - gap + 1, outline);
        fill(ctx, cx - 1, cy + gap - 1, cx + 2, cy + gap + length + 1, outline);
        fill(ctx, cx - gap - length - 1, cy - 1, cx - gap + 1, cy + 2, outline);
        fill(ctx, cx + gap - 1, cy - 1, cx + gap + length + 1, cy + 2, outline);

        // Lines
        fill(ctx, cx, cy - gap - length, cx + thickness, cy - gap, color);
        fill(ctx, cx, cy + gap, cx + thickness, cy + gap + length, color);
        fill(ctx, cx - gap - length, cy, cx - gap, cy + thickness, color);
        fill(ctx, cx + gap, cy, cx + gap + length, cy + thickness, color);
    }

    private static void fill(DrawContext ctx, int x1, int y1, int x2, int y2, int color) {
        ctx.fill(x1, y1, x2, y2, color);
    }
}
