package com.carpour.loggerbungeecord.Events;

import com.carpour.loggerbungeecord.Database.MySQL.MySQLData;
import com.carpour.loggerbungeecord.Database.SQLite.SQLiteData;
import com.carpour.loggerbungeecord.Discord.Discord;
import com.carpour.loggerbungeecord.Main;
import com.carpour.loggerbungeecord.Utils.ConfigManager;
import com.carpour.loggerbungeecord.Utils.FileHandler;
import com.carpour.loggerbungeecord.Utils.Messages;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OnCommand implements Listener {

    private final Main main = Main.getInstance();
    private final ConfigManager cm = Main.getConfig();

    @EventHandler
    public void onCmd(ChatEvent event){

        if (event.isCommand()){

            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            String playerName = player.getName();
            String server =  player.getServer().getInfo().getName();
            String command = event.getMessage();
            List<String> commandParts = Arrays.asList(command.split("\\s+"));
            String serverName = cm.getString("Server-Name");
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

            for (String m : cm.getStringList("Player-Commands.BlackListed-Commands")){

                if (commandParts.get(0).equalsIgnoreCase(m)) return;

            }

            if (player.hasPermission("loggerproxy.exempt")) return;

            if (!event.isCancelled() && cm.getBoolean("Log-Player.Command")) {

                //Log To Files Handling
                if (cm.getBoolean("Log-to-Files")) {

                    if (cm.getBoolean("Staff.Enabled") && player.hasPermission("loggerproxy.staff.log")) {

                        if (!Messages.getString("Discord.Player-Command-Staff").isEmpty()) {

                            Discord.staffChat(player, Objects.requireNonNull(Messages.getString("Discord.Player-Command-Staff")).replaceAll("%server%", server).replaceAll("%command%", command), false);

                        } else {
                            return;
                        }

                        try {

                            BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getStaffLogFile(), true));
                            out.write(Messages.getString("Files.Player-Command-Staff").replaceAll("%time%", dateFormat.format(date)).replaceAll("%server%", server).replaceAll("%player%", player.getName()).replaceAll("%command%", command) + "\n");
                            out.close();

                        } catch (IOException e) {

                            Main.getInstance().getLogger().warning("An error occurred while logging into the appropriate file.");
                            e.printStackTrace();

                        }

                        if (cm.getBoolean("MySQL.Enable") && main.mySQL.isConnected()) {


                            MySQLData.playerCommands(serverName, playerName, command, true);

                        }

                        if (cm.getBoolean("SQLite.Enable") && main.getSqLite().isConnected()) {

                            SQLiteData.insertPlayerCommands(serverName, playerName, command, true);

                        }

                        return;

                    }

                    try {

                        BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getCommandLogFile(), true));
                        out.write(Messages.getString("Files.Player-Command").replaceAll("%time%", dateFormat.format(date)).replaceAll("%server%", server).replaceAll("%player%", player.getName()).replaceAll("%command%", command) + "\n");
                        out.close();

                    } catch (IOException e) {

                        Main.getInstance().getLogger().warning("An error occurred while logging into the appropriate file.");
                        e.printStackTrace();

                    }
                }

                //Discord Integration
                if (cm.getBoolean("Staff.Enabled") && player.hasPermission("loggerproxy.staff.log")) {

                    if (!Messages.getString("Discord.Player-Command-Staff").isEmpty()) {

                        Discord.staffChat(player, Objects.requireNonNull(Messages.getString("Discord.Player-Command-Staff")).replaceAll("%server%", server).replaceAll("%command%", command), false);

                    } else {
                        return;
                    }

                } else {

                    if (!Messages.getString("Discord.Player-Command").isEmpty()) {

                        Discord.playerCommand(player, Objects.requireNonNull(Messages.getString("Discord.Player-Command")).replaceAll("%server%", server).replaceAll("%command%", command), false);

                    } else {
                        return;
                    }
                }


                //MySQL Handling
                if (cm.getBoolean("MySQL.Enable") && main.mySQL.isConnected()) {

                    try {

                        MySQLData.playerCommands(serverName, playerName, command, player.hasPermission("loggerproxy.staff.log"));

                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                }

                //SQLite Handling
                if (cm.getBoolean("SQLite.Enable") && main.getSqLite().isConnected()) {

                    try {

                        SQLiteData.insertPlayerCommands(serverName, playerName, command, player.hasPermission("loggerproxy.staff.log"));

                    } catch (Exception exception) {

                        exception.printStackTrace();

                    }
                }
            }
        }
    }
}
