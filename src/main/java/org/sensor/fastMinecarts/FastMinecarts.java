// src/main/java/org/sensor/fastMinecarts/FastMinecarts.java
package org.sensor.fastMinecarts;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public final class FastMinecarts extends JavaPlugin implements TabCompleter, Listener {

    private double maxSpeed = 1.2;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        maxSpeed = getConfig().getDouble("max-speed", 1.2);

        if (getCommand("fastminecarts") != null) {
            getCommand("fastminecarts").setExecutor(this);
            getCommand("fastminecarts").setTabCompleter(this);
        }

        getServer().getPluginManager().registerEvents(this, this);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    for (Minecart cart : world.getEntitiesByClass(Minecart.class)) {
                        if (cart.getMaxSpeed() != maxSpeed) {
                            cart.setMaxSpeed(maxSpeed);
                        }

                        Vector v = cart.getVelocity();
                        if (v.length() > maxSpeed) {
                            cart.setVelocity(v.normalize().multiply(maxSpeed));
                        }
                    }
                }
            }
        }.runTaskTimer(this, 1L, 1L);
    }

    @EventHandler
    public void onCommandSend(PlayerCommandSendEvent event) {
        event.getCommands().remove("fastminecarts:fastminecarts");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("fastminecarts")) {
            if (!sender.hasPermission("fastminecarts.speed")) {
                sender.sendMessage(Component.text("You do not have permission to use this command.", NamedTextColor.RED));
                return true;
            }

            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                sendHelpMessage(sender);
                return true;
            }

            if (args[0].equalsIgnoreCase("speed")) {
                if (args.length == 1) {
                    sender.sendMessage(Component.text("Current speed: " + maxSpeed + " blocks/tick.", NamedTextColor.YELLOW));
                    sender.sendMessage(Component.text("Usage: /fastminecarts speed <value>", NamedTextColor.RED));
                    return true;
                }

                if (args.length == 2) {
                    try {
                        double newSpeed = Double.parseDouble(args[1]);
                        if (newSpeed <= 0) {
                            sender.sendMessage(Component.text("Speed must be positive.", NamedTextColor.RED));
                            return true;
                        }
                        maxSpeed = newSpeed;

                        getConfig().set("max-speed", maxSpeed);
                        saveConfig();

                        sender.sendMessage(Component.text("Maximum minecart speed set to " + maxSpeed + " blocks/tick.", NamedTextColor.GREEN));
                    } catch (NumberFormatException e) {
                        sender.sendMessage(Component.text("Invalid number: " + args[1], NamedTextColor.RED));
                    }
                }
                return true;
            }

            sendHelpMessage(sender);
            return true;
        }
        return true;
    }

    private void sendHelpMessage(CommandSender sender) {
        sender.sendMessage(Component.text("--- FastMinecarts Help ---", NamedTextColor.GOLD));
        sender.sendMessage(Component.text("/fastminecarts help ", NamedTextColor.YELLOW)
                .append(Component.text("- Shows this help message.", NamedTextColor.WHITE)));
        sender.sendMessage(Component.text("/fastminecarts speed [value] ", NamedTextColor.YELLOW)
                .append(Component.text("- Views or sets the maximum minecart speed.", NamedTextColor.WHITE)));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (!sender.hasPermission("fastminecarts.speed")) {
            return completions;
        }

        if (args.length == 1) {
            String partialCommand = args[0].toLowerCase();
            if ("help".startsWith(partialCommand)) completions.add("help");
            if ("speed".startsWith(partialCommand)) completions.add("speed");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("speed")) {
            completions.add("<value>");
        }

        return completions;
    }
}