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
        // Combat
        add("Aim Assist", "Combat", false);
        add("Auto Anchor", "Combat", false);
        add("Auto Crystal", "Combat", false);
        add("Auto Mace", "Combat", false);
        add("AutoCart", "Combat", false);
        add("AutoTool", "Combat", false);
        add("Backtrack", "Combat", false);
        add("Hitboxes", "Combat", false);
        add("Mace Swap", "Combat", false);
        add("ShieldBreaker", "Combat", false);
        add("Spear Lunge", "Combat", false);
        add("Spear Reach", "Combat", false);
        add("Totem Hit", "Combat", false);
        add("Trigger Bot", "Combat", false);

        // Movement
        add("Auto Walk", "Movement", false);
        add("Combo Helper", "Movement", false);
        add("Fast Stairs", "Movement", false);
        add("Freecam", "Movement", false);
        add("GUI Walk", "Movement", false);
        add("Jump Reset", "Movement", false);
        add("Keep Sprint", "Movement", false);
        add("Move Fix", "Movement", false);
        add("No Jump Delay", "Movement", false);
        add("No Lunge Cooldown", "Movement", false);
        add("No Slow", "Movement", false);
        add("No Web", "Movement", false);
        add("NoFall", "Movement", false);
        add("Null Move", "Movement", false);
        add("Speed", "Movement", false);
        add("Sprint", "Movement", false);
        add("Step", "Movement", false);
        add("Timer", "Movement", false);
        add("Velocity", "Movement", false);

        // Render
        add("Aim Visualizer", "Render", false);
        add("Crosshair", "Render", false);
        add("FullBright", "Render", false);
        add("Glass Panel", "Render", false);
        add("Hit Markers", "Render", false);
        add("HUD", "Render", false);
        add("Jump Circles", "Render", false);
        add("Nametags", "Render", false);
        add("NoHurtCam", "Render", false);
        add("NoRender", "Render", false);
        add("Notifications", "Render", false);
        add("Player ESP", "Render", false);
        add("Storage ESP", "Render", false);
        add("Swing Speed", "Render", false);
        add("Target HUD", "Render", false);
        add("Trails", "Render", false);
        add("Trajectories", "Render", false);
        add("Waypoints", "Render", false);
        add("Zoom", "Render", false);

        // Miscellaneous
        add("Auto Reconnect", "Misc", false);
        add("InvPreview", "Misc", false);
        add("Targets", "Misc", false);
        add("Teams", "Misc", false);

        // Client / interface
        add("Liquid Glass", "Client", true);
        add("UI Sounds", "Client", false);
        add("Toggle Sounds", "Client", false);
    }
}
