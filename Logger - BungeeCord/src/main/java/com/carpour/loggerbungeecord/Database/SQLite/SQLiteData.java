package com.carpour.loggerbungeecord.Database.SQLite;

import com.carpour.loggerbungeecord.Main;

import java.net.InetSocketAddress;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SQLiteData {


    private static Main plugin;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss:SSSXXX");


    public SQLiteData(Main plugin) {
        SQLiteData.plugin = plugin;
    }

    public void createTable() {

        PreparedStatement playerChat, playerCommands, playerLogin, playerLeave, serverStart, serverStop, serverReload;

        try {

            //Player Side Part
            playerChat = plugin.getSqLite().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Chat_Proxy" +
                    "(Server_Name TEXT(30), Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP PRIMARY KEY, Player_Name TEXT(30)," +
                    "Message TEXT(256), Is_Staff INTEGER)");


            playerCommands = plugin.getSqLite().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Commands_Proxy" +
                    "(Server_Name TEXT(30), Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP PRIMARY KEY, Player_Name TEXT(30)," +
                    "Command TEXT(100), Is_Staff INTEGER)");

            playerLogin = plugin.getSqLite().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Login_Proxy" +
                    "(Server_Name TEXT(30), Date TIMESTAMP DEFAULT(STRFTIME('%Y-%m-%d %H:%M:%f', 'now'))  PRIMARY KEY," +
                    "Player_Name TEXT(30), IP TEXT, Is_Staff INTEGER)");

            playerLeave = plugin.getSqLite().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS  Player_Leave_Proxy " +
                    "(Server_Name TEXT(30), Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP PRIMARY KEY, Player_Name TEXT(30), Is_Staff INTEGER)");

            //Server Side Part
            serverReload = plugin.getSqLite().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Server_Reload_Proxy" +
                    "(Server_Name TEXT(30), Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,Command TEXT(256))");

            serverStart = plugin.getSqLite().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Server_Start_Proxy" +
                    "(Server_Name TEXT(30), Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP PRIMARY KEY)");

            serverStop = plugin.getSqLite().getConnection().prepareStatement("CREATE TABLE  IF NOT EXISTS Server_Stop_Proxy" +
                    "(Server_Name TEXT(30), Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP PRIMARY KEY )");

            playerChat.executeUpdate();
            playerCommands.executeUpdate();
            playerLogin.executeUpdate();
            playerLeave.executeUpdate();

            serverReload.executeUpdate();
            serverStart.executeUpdate();
            serverStop.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void insertPlayerChat(String serverName, String player, String message, boolean staff) {

        try {
            PreparedStatement playerChat = plugin.getSqLite().getConnection().prepareStatement("INSERT INTO Player_Chat_Proxy (Server_Name, Date, Player_Name, Message, Is_Staff) VALUES (?,?,?,?,?)");
            playerChat.setString(1, serverName);
            playerChat.setString(2, dateTimeFormatter.format(ZonedDateTime.now()));
            playerChat.setString(3, player);
            playerChat.setString(4, message);
            playerChat.setBoolean(5, staff);


            playerChat.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void insertPlayerCommands(String serverName, String player, String command, boolean staff) {
        try {
            PreparedStatement playerCommands = plugin.getSqLite().getConnection().prepareStatement("INSERT INTO Player_Commands_Proxy (Server_Name, Date, Player_Name, Command, Is_Staff) VALUES (?,?,?,?,?)");
            playerCommands.setString(1, serverName);
            playerCommands.setString(2, dateTimeFormatter.format(ZonedDateTime.now()));
            playerCommands.setString(3, player);
            playerCommands.setString(4, command);
            playerCommands.setBoolean(5, staff);

            playerCommands.executeUpdate();


        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void insertPlayerLogin(String serverName, String player, InetSocketAddress IP, boolean isStaff) {
        try {
            PreparedStatement playerLogin = plugin.getSqLite().getConnection().prepareStatement("INSERT INTO Player_Login_Proxy (Server_Name, Date, Player_Name, IP, Is_Staff) VALUES (?,?,?,?,?)");
            playerLogin.setString(1, serverName);
            playerLogin.setString(2, dateTimeFormatter.format(ZonedDateTime.now()));
            playerLogin.setString(3, player);
            if (Main.getConfig().getBoolean("Player-Login.Player-IP")) {

                playerLogin.setString(4, IP.getHostString());

            }else{

                playerLogin.setString(4, null);
            }
            playerLogin.setBoolean(5, isStaff);

            playerLogin.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void insertPlayerLeave(String serverName, String player, boolean isStaff) {
        try {
            PreparedStatement playerLeave = plugin.getSqLite().getConnection().prepareStatement("INSERT INTO Player_Leave_Proxy (Server_Name, Date, Player_Name, Is_Staff) VALUES (?,?,?,?)");
            playerLeave.setString(1, serverName);
            playerLeave.setString(2, dateTimeFormatter.format(ZonedDateTime.now()));
            playerLeave.setString(3, player);
            playerLeave.setBoolean(4, isStaff);

            playerLeave.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public static void insertServerReload(String serverName, String player, boolean isStaff) {
        try {
            PreparedStatement serverReload = plugin.getSqLite().getConnection().prepareStatement("INSERT INTO Server_Reload_Proxy (Server_Name, Date, Player_Name, Is_Staff) VALUES (?,?,?,?)");
            serverReload.setString(1, serverName);
            serverReload.setString(2, dateTimeFormatter.format(ZonedDateTime.now()));
            serverReload.setString(3, player);
            serverReload.setBoolean(4, isStaff);

            serverReload.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void insertServerStart(String serverName) {
        try {
            PreparedStatement serverStartStatement = plugin.getSqLite().getConnection().prepareStatement("INSERT INTO Server_Start_Proxy (Server_Name, Date) VALUES (?,?)");
            serverStartStatement.setString(1, serverName);
            serverStartStatement.setString(2, dateTimeFormatter.format(ZonedDateTime.now()));

            serverStartStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void insertServerStop(String serverName) {
        try {
            PreparedStatement serverStopStatement = plugin.getSqLite().getConnection().prepareStatement("INSERT INTO Server_Stop_Proxy (Server_Name, Date) VALUES (?,?)");
            serverStopStatement.setString(1, serverName);
            serverStopStatement.setString(2, dateTimeFormatter.format(ZonedDateTime.now()));

            serverStopStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public void emptyTable() {

        int when = Main.getConfig().getInt("SQLite.Data-Deletion");

        if (when <= 0) return;

        try{

            //Player Side Part
            PreparedStatement player_Chat = plugin.getSqLite().getConnection().prepareStatement("DELETE FROM Player_Chat_Proxy WHERE Date <= datetime('now','-" + when + " day')");

            PreparedStatement player_Commands = plugin.getSqLite().getConnection().prepareStatement("DELETE FROM Player_Commands_Proxy WHERE Date <= datetime('now','-" + when + " day')");

            PreparedStatement player_Login = plugin.getSqLite().getConnection().prepareStatement("DELETE FROM Player_Login_Proxy WHERE Date <= datetime('now','-" + when + " day')");

            PreparedStatement player_Leave = plugin.getSqLite().getConnection().prepareStatement("DELETE FROM Player_Leave_Proxy WHERE Date <= datetime('now','-" + when + " day')");

            PreparedStatement server_Reload = plugin.getSqLite().getConnection().prepareStatement("DELETE FROM Server_Reload_Proxy WHERE Date <= datetime('now','-" + when + " day')");

            PreparedStatement server_Start = plugin.getSqLite().getConnection().prepareStatement("DELETE FROM Server_Start_Proxy WHERE Date <= datetime('now','-" + when + " day')");

            PreparedStatement server_Stop = plugin.getSqLite().getConnection().prepareStatement("DELETE FROM Server_Stop_Proxy WHERE Date <= datetime('now','-" + when + " day')");

            player_Chat.executeUpdate();
            player_Commands.executeUpdate();
            player_Login.executeUpdate();
            player_Leave.executeUpdate();
            server_Reload.executeUpdate();
            server_Start.executeUpdate();
            server_Stop.executeUpdate();

        }catch (SQLException e){

            e.printStackTrace();

            plugin.getLogger().severe("An error has occurred while cleaning the tables, if the error persists, contact the Authors!");

        }
    }
}