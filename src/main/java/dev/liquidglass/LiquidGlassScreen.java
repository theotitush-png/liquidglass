package dev.liquidglass;

import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public final class LiquidGlassScreen extends Screen {
    private static final int PINK = 0xFFF078B8;
    private static final int PANEL = 0xB82A2637;
    private static final int PANEL_EDGE = 0x887F718B;
    private static final String[] TABS = {"Modules", "HUD", "Friends", "Theme", "Config", "Donut", "Waypoints"};
    private static final String[] CATEGORIES = {"Combat", "Movement", "Render", "Misc", "Client"};
    private String activeTab = "Modules";
    private TextFieldWidget search;
    private final List<HitBox> moduleHits = new ArrayList<>();

    public LiquidGlassScreen() {
        super(Text.literal("LiquidGlass"));
    }

    @Override
    protected void init() {
        search = new TextFieldWidget(textRenderer, width / 2 - 145, 57, 290, 22, Text.literal("Search modules..."));
        search.setPlaceholder(Text.literal("Search modules..."));
        addDrawableChild(search);
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        renderBackground(ctx, mouseX, mouseY, delta);
        ctx.fill(0, 0, width, height, 0x5520192B);

        int topW = Math.min(650, width - 32);
        int left = (width - topW) / 2;
        rounded(ctx, left, 8, left + topW, 48, 0xCC343140, PANEL_EDGE);
        int tabW = topW / TABS.length;
        for (int i = 0; i < TABS.length; i++) {
            int x = left + i * tabW;
            if (TABS[i].equals(activeTab)) rounded(ctx, x + 3, 13, x + tabW - 3, 43, PINK, PINK);
            centered(ctx, TABS[i], x + tabW / 2, 24, TABS[i].equals(activeTab) ? 0xFF241A29 : 0xFFE8E1EA);
        }
        rounded(ctx, width / 2 - 150, 56, width / 2 + 150, 82, 0xCC343140, PANEL_EDGE);
        search.setX(width / 2 - 140);
        search.setY(58);
        search.setWidth(280);
        search.render(ctx, mouseX, mouseY, delta);

        if (!activeTab.equals("Modules")) {
            centered(ctx, activeTab + " panel", width / 2, height / 2, 0xFFFFFFFF);
            centered(ctx, "This section is ready for its settings screen.", width / 2, height / 2 + 20, 0xFFDDD3E1);
            super.render(ctx, mouseX, mouseY, delta);
            return;
        }

        int gap = 12;
        int panelW = Math.min(210, (width - 36 - gap * 4) / 5);
        int total = panelW * 5 + gap * 4;
        int startX = (width - total) / 2;
        int startY = 105;
        moduleHits.clear();

        for (int c = 0; c < CATEGORIES.length; c++) {
            int x = startX + c * (panelW + gap);
            rounded(ctx, x, startY, x + panelW, height - 24, PANEL, PANEL_EDGE);
            ctx.drawText(textRenderer, CATEGORIES[c], x + 12, startY + 11, 0xFFFFFFFF, false);
            int y = startY + 38;
            List<Module> list = LiquidGlassClient.MODULES.inCategory(CATEGORIES[c]);
            for (Module module : list) {
                if (!search.getText().isBlank()
                        && !module.name().toLowerCase().contains(search.getText().toLowerCase())) continue;
                int rowH = 27;
                int color = module.enabled() ? PINK : 0x224D4658;
                if (module.enabled()) rounded(ctx, x + 5, y, x + panelW - 5, y + 24, color, color);
                ctx.drawText(textRenderer, module.name(), x + 13, y + 7,
                        module.enabled() ? 0xFFFFFFFF : 0xFFE5DFE9, false);
                String mark = module.enabled() ? "ON" : "+";
                ctx.drawText(textRenderer, mark, x + panelW - 28, y + 7, 0xFFFFFFFF, false);
                moduleHits.add(new HitBox(x + 5, y, x + panelW - 5, y + 24, module));
                y += rowH;
            }
        }
        centered(ctx, "RIGHT SHIFT  •  toggle GUI", width / 2, height - 14, 0xFFDDD3E1);
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        double mouseX = click.x();
        double mouseY = click.y();
        if (click.button() == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            int topW = Math.min(650, width - 32);
            int left = (width - topW) / 2;
            int tabW = topW / TABS.length;
            if (mouseY >= 8 && mouseY <= 48 && mouseX >= left && mouseX <= left + topW) {
                int index = Math.min(TABS.length - 1, (int) ((mouseX - left) / tabW));
                activeTab = TABS[index];
                return true;
            }
            for (HitBox hit : moduleHits) {
                if (hit.contains(mouseX, mouseY)) {
                    hit.module.toggle();
                    return true;
                }
            }
        }
        return super.mouseClicked(click, doubled);
    }

    private void rounded(DrawContext ctx, int x1, int y1, int x2, int y2, int fill, int edge) {
        ctx.fill(x1 + 4, y1, x2 - 4, y2, fill);
        ctx.fill(x1, y1 + 4, x2, y2 - 4, fill);
        ctx.fill(x1 + 2, y1 + 1, x2 - 2, y1 + 3, edge);
        ctx.fill(x1 + 2, y2 - 3, x2 - 2, y2 - 1, edge);
        ctx.fill(x1 + 1, y1 + 2, x1 + 3, y2 - 2, edge);
        ctx.fill(x2 - 3, y1 + 2, x2 - 1, y2 - 2, edge);
    }

    private void centered(DrawContext ctx, String value, int x, int y, int color) {
        ctx.drawText(textRenderer, value, x - textRenderer.getWidth(value) / 2, y, color, false);
    }

    @Override
    public boolean shouldPause() { return false; }

    private record HitBox(int x1, int y1, int x2, int y2, Module module) {
        boolean contains(double x, double y) {
            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }
    }
}
