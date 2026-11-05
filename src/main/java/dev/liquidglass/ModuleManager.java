package dev.liquidglass;

import java.util.ArrayList;
import java.util.List;

public final class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void add(String name, String category, boolean enabled) {
        modules.add(new Module(name, category, enabled));
    }

    public List<Module> all() { return List.copyOf(modules); }

    public List<Module> inCategory(String category) {
        return modules.stream().filter(m -> m.category().equals(category)).toList();
    }

    public void registerDefaults() {
        // Safe client-side visual, interface and convenience options.
        add("Coordinates", "HUD", true);
        add("FPS Counter", "HUD", true);
        add("Clock", "HUD", false);
        add("Armor Status", "HUD", false);
        add("Crosshair Style", "Render", false);
        add("Hit Color", "Render", false);
        add("Fullbright", "Render", false);
        add("Zoom", "Render", false);
        add("Keystrokes", "HUD", false);
        add("Potion Timers", "HUD", false);
        add("Liquid Glass", "Client", true);
        add("UI Sounds", "Client", false);
        add("Compact Chat", "Client", false);
        add("Screenshot Toast", "Client", true);
        add("Weather Particles", "Render", false);
        add("Menu Panorama", "Client", true);
    }
}
