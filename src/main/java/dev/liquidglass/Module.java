package dev.liquidglass;

public final class Module {
    private final String name;
    private final String category;
    private boolean enabled;

    public Module(String name, String category, boolean enabled) {
        this.name = name;
        this.category = category;
        this.enabled = enabled;
    }

    public String name() { return name; }
    public String category() { return category; }
    public boolean enabled() { return enabled; }
    public void toggle() { enabled = !enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
