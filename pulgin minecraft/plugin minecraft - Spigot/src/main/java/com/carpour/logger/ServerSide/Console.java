package com.carpour.logger.ServerSide;

import com.carpour.logger.Discord.Discord;
import com.carpour.logger.Main;
import com.carpour.logger.Utils.FileHandler;
import com.carpour.logger.Database.MySQL.MySQLData;
import com.carpour.logger.Database.SQLite.SQLiteData;
import com.carpour.logger.Utils.Messages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Console implements Listener {

    private final Main main = Main.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConsoleCommand(ServerCommandEvent event) {

        String msg = event.getCommand();
        String serverName = main.getConfig().getString("Server-Name");
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        if (main.getConfig().getBoolean("Log.Console-Commands")) {

            //Log To Files Handling
            if (main.getConfig().getBoolean("Log-to-Files")) {

                try {

                    BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getConsoleLogFile(), true));
                    out.write(Objects.requireNonNull(Messages.get().getString("Files.Console-Commands")).replaceAll("%time%", dateFormat.format(date)).replaceAll("%message%", msg) + "\n");
                    out.close();

                } catch (IOException e) {

                    main.getServer().getLogger().warning("An error occurred while logging into the appropriate file.");
                    e.printStackTrace();

                }
            }

            //Discord
            Discord.console(Objects.requireNonNull(Messages.get().getString("Discord.Console-Commands")).replaceAll("%message%", msg), false);

            //MySQL
            if (main.getConfig().getBoolean("MySQL.Enable") && main.mySQL.isConnected()) {

                try {

                    MySQLData.consoleCommands(serverName, msg);

                } catch (Exception e) {

                    e.printStackTrace();

                }
            }

            //SQLite
            if (main.getConfig().getBoolean("SQLite.Enable") && main.getSqLite().isConnected()) {

                try {

                    SQLiteData.insertConsoleCommands(serverName, msg);

                } catch (Exception e) {

                    e.printStackTrace();

                }
            }
        }
    }
}
