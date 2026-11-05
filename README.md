# LiquidGlass — Fabric 1.21.11

A fresh Fabric client-mod starter with a pink glass-style ClickGUI.

## Features in this starter
- Right Shift opens/closes the GUI.
- Pink highlighted tabs and translucent panel styling.
- Searchable module list with clickable on/off toggles.
- HUD, Render, Client, Misc and Combat category columns.
- Safe client-side visual/interface modules only.

## Build
Requires JDK 21 and Gradle.

```bash
gradle build
```

The built mod jar will be in `build/libs/`.

## Notes
This is a starter implementation, not a finished feature-complete client. Several listed modules are currently UI toggles only; their actual game effects need separate implementation and testing. Automated attacks, player-targeting cheats, and combat automation are intentionally not included.
