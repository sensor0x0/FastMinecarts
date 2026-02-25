<p align="center">
  <img src="src/main/resources/FastMinecarts.png" alt="FastMinecarts Icon" width="256"/>
</p>

---

# FastMinecarts

Break the speed limit in minecarts.

FastMinecarts bypasses the vanilla/Spigot hard cap for minecarts, allowing you to configure their maximum speed while applying intelligent safeguards to reduce rail desync, clipping, and erratic behavior at higher velocities.

---

## Features

* Removes the vanilla/Spigot minecart speed cap.
* Fully configurable maximum speed.
* Dynamically updates minecart velocity every tick.
* Automatically persists configuration changes to `config.yml`.
* Temporarily clamps speed on slopes to reduce physics glitches.
* Hides the generated `/fastminecarts:fastminecarts` command from tab completion for a cleaner command experience.

---

## Commands

* `/fastminecarts help` – Shows the help message.
* `/fastminecarts speed [value]` – Views or sets the maximum minecart speed.

---

## Permissions

* `fastminecarts.speed` – Allows viewing and setting the configured minecart speed (default: op).

---

## Configuration

By default, FastMinecarts uses vanilla behavior.  
You can increase the maximum minecart speed in:

* `config.yml`
* In-game using `/fastminecarts speed [value]`

Changes made in-game are saved automatically.

---

## Known Limitations

Minecraft’s rail physics were not designed for extreme speeds. While this plugin removes the artificial speed cap, the underlying movement system still has constraints.

### Slopes

At high speeds, travelling up or down slopes can cause:

* Rail desynchronisation
* Clipping into nearby blocks
* Ejection from rails

To mitigate this, FastMinecarts temporarily restores the vanilla speed cap while a minecart is on a slope, then reapplies the configured speed once it returns to flat rails.

### Corners

Sharp 90° turns are especially sensitive at high velocity. Excessive speed may result in:

* Visual stuttering
* Direction snapping
* Derailment or collision glitches

For best stability, avoid tight right-angle turns at high speed and use buffered or gradual direction changes instead.

---

## Requirements

* **API Version:** 1.21
* **Java:** Target version 21
