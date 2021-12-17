package com.carpour.logger.Events;

import com.carpour.logger.Discord.Discord;
import com.carpour.logger.Discord.DiscordFile;
import com.carpour.logger.Main;
import com.carpour.logger.Database.MySQL.MySQLData;
import com.carpour.logger.Utils.FileHandler;
import com.carpour.logger.Database.SQLite.SQLiteData;
import com.carpour.logger.Utils.Messages;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OnEnchant implements Listener {

    private final Main main = Main.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEnchanting(EnchantItemEvent event) {

        Player player = event.getEnchanter();
        String playerName = player.getName();
        World world = player.getWorld();
        String worldName = world.getName();
        String item = event.getItem().getType().toString();
        int cost = event.getExpLevelCost();
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        List<String> enchs = new ArrayList<>();
        String serverName = main.getConfig().getString("Server-Name");
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        if (!event.isCancelled() && main.getConfig().getBoolean("Log-Player.Enchanting")) {

            for (Enchantment ench : event.getEnchantsToAdd().keySet()) {

                enchs.add(ench.getName());

            }

            //Log To Files Handling
            if (main.getConfig().getBoolean("Log-to-Files")) {

                if (main.getConfig().getBoolean("Staff.Enabled") && player.hasPermission("logger.staff.log")) {

                    if (DiscordFile.get().getBoolean("Discord.Enchanting.Enable")) {

                        Discord.staffChat(player, Objects.requireNonNull(Messages.get().getString("Discord.Enchanting-Staff")).replaceAll("%world%", worldName).replaceAll("%item%", item).replaceAll("%level%", String.valueOf(cost)).replaceAll("%x%", String.valueOf(x)).replaceAll("%y%", String.valueOf(y)).replaceAll("%z%", String.valueOf(z)).replaceAll("%item%", item).replaceAll("%enchantment%", String.valueOf(enchs)), false);

                    }

                    try {

                        BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getstaffFile(), true));
                        out.write(Objects.requireNonNull(Messages.get().getString("Files.Enchanting-Staff")).replaceAll("%time%", dateFormat.format(date)).replaceAll("%world%", worldName).replaceAll("%player%", playerName).replaceAll("%item%", item).replaceAll("%level%", String.valueOf(cost)).replaceAll("%x%", String.valueOf(x)).replaceAll("%y%", String.valueOf(y)).replaceAll("%z%", String.valueOf(z)).replaceAll("%enchantment%", String.valueOf(enchs)) + "\n");
                        out.close();

                    } catch (IOException e) {

                        main.getServer().getLogger().warning("An error occurred while logging into the appropriate file.");
                        e.printStackTrace();

                    }

                    if (main.getConfig().getBoolean("MySQL.Enable") && main.mySQL.isConnected()) {

                        MySQLData.enchant(serverName, worldName, playerName, x, y, z, enchs, item, cost, true);

                    }

                    if (main.getConfig().getBoolean("SQLite.Enable") && main.getSqLite().isConnected()) {

                        SQLiteData.insertEnchant(serverName, player, enchs, item, cost, true);

                    }

                    return;

                }

                try {


                    BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getenchantFile(), true));
                    out.write(Objects.requireNonNull(Messages.get().getString("Files.Enchanting")).replaceAll("%time%", dateFormat.format(date)).replaceAll("%world%", worldName).replaceAll("%player%", playerName).replaceAll("%x%", String.valueOf(x)).replaceAll("%y%", String.valueOf(y)).replaceAll("%z%", String.valueOf(z)).replaceAll("%item%", item).replaceAll("%enchantment%", String.valueOf(enchs)).replaceAll("%level%", String.valueOf(cost)) + "\n");
                    out.close();

                } catch (IOException e) {

                    main.getServer().getLogger().warning("An error occurred while logging into the appropriate file.");
                    e.printStackTrace();

                }
            }

            //Discord
            if (DiscordFile.get().getBoolean("Discord.Enchanting.Enable")) {

                if (main.getConfig().getBoolean("Staff.Enabled") && player.hasPermission("logger.staff.log")) {

                    Discord.staffChat(player, Objects.requireNonNull(Messages.get().getString("Discord.Enchanting-Staff")).replaceAll("%world%", worldName).replaceAll("%item%", item).replaceAll("%level%", String.valueOf(cost)).replaceAll("%x%", String.valueOf(x)).replaceAll("%y%", String.valueOf(y)).replaceAll("%z%", String.valueOf(z)).replaceAll("%item%", item).replaceAll("%enchantment%", String.valueOf(enchs)), false);

                } else {

                    Discord.enchanting(player, Objects.requireNonNull(Messages.get().getString("Discord.Enchanting")).replaceAll("%world%", worldName).replaceAll("%item%", item).replaceAll("%level%", String.valueOf(cost)).replaceAll("%x%", String.valueOf(x)).replaceAll("%y%", String.valueOf(y)).replaceAll("%z%", String.valueOf(z)).replaceAll("%item%", item).replaceAll("%enchantment%", String.valueOf(enchs)), false);

                }
            }

            //MySQL
            if (main.getConfig().getBoolean("MySQL.Enable") && main.mySQL.isConnected()) {

                try {

                    MySQLData.enchant(serverName, worldName, playerName, x, y, z, enchs, item, cost, player.hasPermission("logger.staff.log"));

                } catch (Exception e) {

                    e.printStackTrace();

                }
            }

            //SQLite
            if (main.getConfig().getBoolean("SQLite.Enable") && main.getSqLite().isConnected()) {

                try {

                    SQLiteData.insertEnchant(serverName, player, enchs, item, cost, player.hasPermission("logger.staff.log"));

                } catch (Exception exception) {

                    exception.printStackTrace();

                }
            }
        }
    }
}
