<p align="center">
  <img src="src/main/resources/FastMinecarts.png" alt="FastMinecarts Icon" width="256"/>
</p>

---

# FastMinecarts

Break the speed limit in minecarts. This plugin bypasses the vanilla/Spigot hard cap for minecarts, allowing you to configure their maximum speed while smoothly clamping velocity to prevent erratic curve behavior or rail skipping.

## Features
* Bypasses vanilla Minecraft speed limits for minecarts.
* Dynamically updates minecart speeds every tick.
* Persists the configured maximum speed across server restarts via `config.yml`.
* Automatically hides the generated `/fastminecarts:fastminecarts` command from tab completion for a cleaner look.

## Commands
* `/fastminecarts help` - Shows the help message.
* `/fastminecarts speed [value]` - Views or sets the maximum minecart speed.

## Permissions
* `fastminecarts.speed` - Allows viewing and setting the minecart speed (default: op).

## Configuration
The default max speed is set to `1.2` blocks per tick in the `config.yml`. You can modify this in-game using the speed command, which will automatically save to the configuration file.

## Requirements
* **API Version:** 1.21
* **Java:** Target version 21