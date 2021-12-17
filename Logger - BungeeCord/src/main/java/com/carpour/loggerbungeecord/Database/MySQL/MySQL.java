package com.carpour.loggerbungeecord.Database.MySQL;

import com.carpour.loggerbungeecord.Main;
import com.carpour.loggerbungeecord.Utils.ConfigManager;
import net.md_5.bungee.api.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    ConfigManager cm = Main.getConfig();

    private final String host = cm.getString("MySQL.Host");
    private final int port = cm.getInt("MySQL.Port");
    private final String username = cm.getString("MySQL.Username");
    private final String password = cm.getString("MySQL.Password");
    private final String database = cm.getString("MySQL.Database");
    private Connection connection;

    public boolean isConnected(){ return connection != null; }

    public void connect() {

        if (!isConnected()) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?AllowPublicKeyRetrieval=true?useSSL=false&jdbcCompliantTruncation=false", username, password);
                Main.getInstance().getLogger().info(ChatColor.GREEN + "MySQL Connection has been established!");

            } catch (SQLException | ClassNotFoundException e) {

                Main.getInstance().getLogger().warning(ChatColor.RED + "Could not connect to the Database!");

            }
        }
    }

    public void disconnect() {

        if (isConnected()) {

            try {

                connection.close();
                Main.getInstance().getLogger().info("MySQL Connection has been closed!");

            } catch (SQLException e) {

                Main.getInstance().getLogger().severe("MySQL Database couldn't be closed safely, if the issue persists contact the Authors!");

            }
        }
    }

    public Connection getConnection(){ return connection; }

}
