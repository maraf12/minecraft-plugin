package com.carpour.logger.Events;

import com.carpour.logger.Database.MySQL.MySQLData;
import com.carpour.logger.Database.SQLite.SQLiteData;
import com.carpour.logger.Discord.Discord;
import com.carpour.logger.Discord.DiscordFile;
import com.carpour.logger.Main;
import com.carpour.logger.Utils.FileHandler;
import com.carpour.logger.Utils.Messages;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class OnBucketFill implements Listener {

    private final Main main = Main.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBucket(PlayerBucketFillEvent event){

        Player player = event.getPlayer();
        String playerName = player.getName();
        World world = event.getPlayer().getWorld();
        String worldName = world.getName();
        String bucket = Objects.requireNonNull(event.getItemStack()).getType().name().replaceAll("_", " ");
        int x = event.getBlockClicked().getX();
        int y = event.getBlockClicked().getY();
        int z = event.getBlockClicked().getZ();
        String serverName = main.getConfig().getString("Server-Name");
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        if (player.hasPermission("logger.exempt")) return;

        if (!event.isCancelled() && (main.getConfig().getBoolean("Log-Player.Bucket-Fill"))) {

            //Log To Files Handling
            if (main.getConfig().getBoolean("Log-to-Files")) {

                if (main.getConfig().getBoolean("Staff.Enabled") && player.hasPermission("logger.staff.log")) {

                    if (DiscordFile.get().getBoolean("Discord.Bucket-Fill.Enable")) {

                        Discord.staffChat(player, Objects.requireNonNull(Messages.get().getString("Discord.Bucket-Fill-Staff")).replaceAll("%world%", worldName).replaceAll("%x%", String.valueOf(x)).replaceAll("%y%", String.valueOf(y)).replaceAll("%z%", String.valueOf(z)).replaceAll("%bucket%", bucket), false);

                    }

                    try {

                        BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getstaffFile(), true));
                        out.write(Objects.requireNonNull(Messages.get().getString("Files.Bucket-Fill-Staff")).replaceAll("%time%", dateFormat.format(date)).replaceAll("%world%", worldName).replaceAll("%player%", playerName).replaceAll("%x%", String.valueOf(x)).replaceAll("%y%", String.valueOf(y)).replaceAll("%z%", String.valueOf(z)).replaceAll("%bucket%", bucket) + "\n");
                        out.close();

                    } catch (IOException e) {

                        main.getServer().getLogger().warning("An error occurred while logging into the appropriate file.");
                        e.printStackTrace();

                    }

                    if (main.getConfig().getBoolean("MySQL.Enable") && main.mySQL.isConnected()) {

                        MySQLData.bucketFill(serverName, worldName, playerName, bucket, x, y, z, true);

                    }

                    if (main.getConfig().getBoolean("SQLite.Enable") && main.getSqLite().isConnected()) {

                        SQLiteData.insertBucketFill(serverName, player, bucket, true);

                    }

                    return;

                }

                try {

                    BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getBucketFillFile(), true));
                    out.write(Objects.requireNonNull(Messages.get().getString("Files.Bucket-Fill")).replaceAll("%time%", dateFormat.format(date)).replaceAll("%world%", worldName).replaceAll("%player%", playerName).replaceAll("%x%", String.valueOf(x)).replaceAll("%y%", String.valueOf(y)).replaceAll("%z%", String.valueOf(z)).replaceAll("%bucket%", bucket) + "\n");
                    out.close();

                } catch (IOException e) {

                    main.getServer().getLogger().warning("An error occurred while logging into the appropriate file.");
                    e.printStackTrace();

                }
            }

            //Discord
            if (DiscordFile.get().getBoolean("Discord.Bucket-Fill.Enable")) {

                if (main.getConfig().getBoolean("Staff.Enabled") && player.hasPermission("logger.staff.log")) {

                    Discord.staffChat(player, Objects.requireNonNull(Messages.get().getString("Discord.Bucket-Fill-Staff")).replaceAll("%world%", worldName).replaceAll("%x%", String.valueOf(x)).replaceAll("%y%", String.valueOf(y)).replaceAll("%z%", String.valueOf(z)).replaceAll("%bucket%", bucket), false);

                } else {

                    Discord.bucketFill(player, Objects.requireNonNull(Messages.get().getString("Discord.Bucket-Fill")).replaceAll("%world%", worldName).replaceAll("%x%", String.valueOf(x)).replaceAll("%y%", String.valueOf(y)).replaceAll("%z%", String.valueOf(z)).replaceAll("%bucket%", bucket), false);

                }
            }

            //MySQL
            if (main.getConfig().getBoolean("MySQL.Enable") && main.mySQL.isConnected()) {

                try {

                    MySQLData.bucketFill(serverName, worldName, playerName, bucket, x, y, z, player.hasPermission("logger.staff.log"));

                } catch (Exception e) {

                    e.printStackTrace();

                }
            }

            //SQLite
            if (main.getConfig().getBoolean("SQLite.Enable") && main.getSqLite().isConnected()) {

                try {

                    SQLiteData.insertBucketFill(serverName, player, bucket, player.hasPermission("logger.staff.log"));

                } catch (Exception e) {

                    e.printStackTrace();

                }
            }
        }
    }
}
