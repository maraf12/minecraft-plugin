package com.carpour.logger.Database.MySQL;

import com.carpour.logger.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.world.PortalCreateEvent;

import java.net.InetSocketAddress;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQLData {

    private static Main plugin;

    public MySQLData(Main plugin){
        MySQLData.plugin = plugin;
    }

    public void createTable(){

        PreparedStatement playerChat, playerCommands, consoleCommands, playerSignText, playerJoin, playerLeave, playerDeath, playerTeleport, blockPlace, blockBreak, TPS, RAM, playerKick, portalCreation, playerLevel, bucketPlace, anvil, serverStart, serverStop, itemDrop, enchant, bookEditing, afk, itempPickup, furnace;

        try {

            playerChat = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Chat "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),Message VARCHAR(200),Is_Staff TINYINT,PRIMARY KEY (Date))");

            playerCommands = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Commands "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),Command VARCHAR(256),Is_Staff TINYINT,PRIMARY KEY (Date))");

            consoleCommands = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Console_Commands "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),Command VARCHAR(256),PRIMARY KEY (Date))");

            playerSignText = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Sign_Text "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),X INT,Y INT,Z INT,Playername VARCHAR(100),Line VARCHAR(60),Is_Staff TINYINT,PRIMARY KEY (Date))");

            playerDeath = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Death "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),X INT,Y INT,Z INT,Cause VARCHAR(40),By_Who VARCHAR(30),Item_Used VARCHAR(30),Is_Staff TINYINT,PRIMARY KEY (Date))");

            playerTeleport = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Teleport "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),From_X INT,From_Y INT,From_Z INT,To_X INT,To_Y INT,To_Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))");

            playerJoin = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Join "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),X INT,Y INT,Z INT,IP INT UNSIGNED,Is_Staff TINYINT,PRIMARY KEY (Date))");

            playerLeave = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Leave "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))");

            blockPlace = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Block_Place "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),Block VARCHAR(40),X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))");

            blockBreak = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Block_Break "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),Block VARCHAR(40),X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))");

            TPS = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS TPS "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),TPS INT,PRIMARY KEY (Date))");

            RAM = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS RAM "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),Total_Memory INT,Used_Memory INT,Free_Memory INT,PRIMARY KEY (Date))");

            playerKick = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Kick "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),X INT,Y INT,Z INT,Reason VARCHAR(50),Is_Staff TINYINT,PRIMARY KEY (Date))");

            portalCreation = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Portal_Creation "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Caused_By VARCHAR(50),PRIMARY KEY (Date))");

            playerLevel = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player_Level "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),Playername VARCHAR(100),Is_Staff TINYINT,PRIMARY KEY (Date))");

            bucketPlace = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Bucket_Place "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),Bucket VARCHAR(40),X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))");

            anvil = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Anvil "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),Playername VARCHAR(100),New_name VARCHAR(100),Is_Staff TINYINT,PRIMARY KEY (Date))");

            serverStart = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Server_Start "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),PRIMARY KEY (Date))");

            serverStop = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Server_Stop "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),PRIMARY KEY (Date))");

            itemDrop = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Item_Drop "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),Item VARCHAR(50),Amount INT,X INT,Y INT,Z INT,Enchantment VARCHAR(50),Changed_Name VARCHAR(50),Is_Staff TINYINT,PRIMARY KEY (Date))");

            enchant = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Enchanting "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),X INT,Y INT,Z INT,Enchantment VARCHAR(50),Item VARCHAR(50),Cost INT(5),Is_Staff TINYINT,PRIMARY KEY (Date))");

            bookEditing = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Book_Editing "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),Page_Count INT,Page_Content VARCHAR(250),Signed_by VARCHAR(25),Is_Staff TINYINT,PRIMARY KEY (Date))");

            if (plugin.getAPI() != null) {

                afk = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS AFK "
                        + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))");


                afk.executeUpdate();
            }

            itempPickup = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Item_Pickup "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),Item VARCHAR(250),Amount INT,X INT,Y INT,Z INT,Changed_Name VARCHAR(250),Is_Staff TINYINT,PRIMARY KEY (Date))");


            furnace = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Furnace "
                    + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100),Playername VARCHAR(100),Item VARCHAR(250),Amount INT,X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))");

            playerChat.executeUpdate();
            playerCommands.executeUpdate();
            consoleCommands.executeUpdate();
            playerSignText.executeUpdate();
            playerDeath.executeUpdate();
            playerTeleport.executeUpdate();
            playerJoin.executeUpdate();
            playerLeave.executeUpdate();
            blockPlace.executeUpdate();
            blockBreak.executeUpdate();
            TPS.executeUpdate();
            RAM.executeUpdate();
            playerKick.executeUpdate();
            portalCreation.executeUpdate();
            playerLevel.executeUpdate();
            bucketPlace.executeUpdate();
            anvil.executeUpdate();
            serverStart.executeUpdate();
            serverStop.executeUpdate();
            itemDrop.executeUpdate();
            bookEditing.executeUpdate();
            enchant.executeUpdate();
            itempPickup.executeUpdate();
            furnace.executeUpdate();

        }catch (SQLException e){

            e.printStackTrace();

        }
    
    }

    public static void playerChat(String serverName, String worldName, String playerName, String msg, boolean staff){
        try {
            String database = "Player_Chat";
            PreparedStatement playerChat = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Message,Is_Staff) VALUES(?,?,?,?,?)");
            playerChat.setString(1, serverName);
            playerChat.setString(2, worldName);
            playerChat.setString(3, playerName);
            playerChat.setString(4, msg);
            playerChat.setBoolean(5, staff);
            playerChat.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void playerCommands(String serverName, String worldName, String playerName, String msg, boolean staff){
        try {
            String database = "Player_Commands";
            PreparedStatement playerCommands = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Command,Is_Staff) VALUES(?,?,?,?,?)");
            playerCommands.setString(1, serverName);
            playerCommands.setString(2, worldName);
            playerCommands.setString(3, playerName);
            playerCommands.setString(4, msg);
            playerCommands.setBoolean(5, staff);
            playerCommands.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void consoleCommands(String serverName, String msg){
        try {
            String database = "Console_Commands";
            PreparedStatement consoleCommands = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,Command) VALUES(?,?)");
            consoleCommands.setString(1, serverName);
            consoleCommands.setString(2, msg);
            consoleCommands.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void playerSignText(String serverName, String worldName, double x, double y, double z, String playerName, String Lines, boolean staff){
        try {
            String database = "Player_Sign_Text";
            PreparedStatement playerSignText = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,X,Y,Z,Playername,Line,Is_Staff) VALUES(?,?,?,?,?,?,?,?)");
            playerSignText.setString(1, serverName);
            playerSignText.setString(2, worldName);
            playerSignText.setDouble(3, x);
            playerSignText.setDouble(4, y);
            playerSignText.setDouble(5, z);
            playerSignText.setString(6, playerName);
            playerSignText.setString(7, Lines);
            playerSignText.setBoolean(8, staff);
            playerSignText.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void playerDeath(String serverName, String worldName, String playerName, double x, double y, double z, String cause, String who, String itemUsed, boolean staff){
        try {
            String database = "Player_Death";
            PreparedStatement playerDeath= plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,X,Y,Z,Cause,By_Who,Item_Used,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?,?)");
            playerDeath.setString(1, serverName);
            playerDeath.setString(2, worldName);
            playerDeath.setString(3, playerName);
            playerDeath.setDouble(4, x);
            playerDeath.setDouble(5, y);
            playerDeath.setDouble(6, z);
            playerDeath.setString(7, cause);
            playerDeath.setString(8, who);
            playerDeath.setString(9, itemUsed);
            playerDeath.setBoolean(10, staff);
            playerDeath.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void playerTeleport(String serverName, String worldName, String playerName, double ox, double oy, double oz, double tx, double ty, double tz, boolean staff){
        try {
            String database = "Player_Teleport";
            PreparedStatement playerTeleport = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,From_X,From_Y,From_Z,To_X,To_Y,To_Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?,?)");
            playerTeleport.setString(1, serverName);
            playerTeleport.setString(2, worldName);
            playerTeleport.setString(3, playerName);
            playerTeleport.setDouble(4, ox);
            playerTeleport.setDouble(5, oy);
            playerTeleport.setDouble(6, oz);
            playerTeleport.setDouble(7, tx);
            playerTeleport.setDouble(8, ty);
            playerTeleport.setDouble(9, tz);
            playerTeleport.setBoolean(10, staff);
            playerTeleport.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void playerJoin(String serverName, String worldName, String playerName, double x, double y, double z, InetSocketAddress IP, boolean staff) {
        try {
            String database = "Player_Join";
            PreparedStatement playerJoin = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,X,Y,Z,IP,Is_Staff) VALUES(?,?,?,?,?,?,INET_ATON(?),?)");
            playerJoin.setString(1, serverName);
            playerJoin.setString(2, worldName);
            playerJoin.setString(3, playerName);
            playerJoin.setDouble(4, x);
            playerJoin.setDouble(5, y);
            playerJoin.setDouble(6, z);
            if (plugin.getConfig().getBoolean("Player-Join.Player-IP")) {
                playerJoin.setString(7, IP.getHostString());
            }else{

                playerJoin.setString(7, null);
            }
            playerJoin.setBoolean(8, staff);
            playerJoin.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void playerLeave(String serverName, String worldName, String playerName, double x, double y, double z, boolean staff){
        try {
            String database = "Player_Leave";
            PreparedStatement playerLeave = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?)");
            playerLeave.setString(1, serverName);
            playerLeave.setString(2, worldName);
            playerLeave.setString(3, playerName);
            playerLeave.setDouble(4, x);
            playerLeave.setDouble(5, y);
            playerLeave.setDouble(6, z);
            playerLeave.setBoolean(7, staff);
            playerLeave.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void blockPlace(String serverName, String worldName, String playerName, String block, double x, double y, double z, boolean staff){
        try {
            String database = "Block_Place";
            PreparedStatement blockPlace = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Block,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?)");
            blockPlace.setString(1, serverName);
            blockPlace.setString(2, worldName);
            blockPlace.setString(3, playerName);
            blockPlace.setString(4, block);
            blockPlace.setDouble(5, x);
            blockPlace.setDouble(6, y);
            blockPlace.setDouble(7, z);
            blockPlace.setBoolean(8, staff);
            blockPlace.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void blockBreak(String serverName, String worldName, String playerName, String blockname, double x, double y, double z,boolean staff){
        try {
            String database = "Block_Break";
            PreparedStatement blockBreak = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Block,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?)");
            blockBreak.setString(1, serverName);
            blockBreak.setString(2, worldName);
            blockBreak.setString(3, playerName);
            blockBreak.setString(4, blockname);
            blockBreak.setDouble(5, x);
            blockBreak.setDouble(6, y);
            blockBreak.setDouble(7, z);
            blockBreak.setBoolean(8, staff);
            blockBreak.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void TPS(String serverName, double tps){
        try {
            String database = "TPS";
            PreparedStatement TPS = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,TPS) VALUES(?,?)");
            TPS.setString(1, serverName);
            TPS.setDouble(2, tps);
            TPS.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void RAM(String serverName, long TM, long UM, long FM){
        try {
            String database = "RAM";
            PreparedStatement RAM = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,Total_Memory,Used_Memory,Free_Memory) VALUES(?,?,?,?)");
            RAM.setString(1, serverName);
            RAM.setDouble(2, TM);
            RAM.setDouble(3, UM);
            RAM.setDouble(4, FM);
            RAM.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void playerKick(String serverName, String worldName, String playerName, double x, double y, double z, String reason, boolean staff){
        try {
            String database = "Player_Kick";
            PreparedStatement playerKick = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,X,Y,Z,Reason,Is_Staff) VALUES(?,?,?,?,?,?,?,?)");
            playerKick.setString(1, serverName);
            playerKick.setString(2, worldName);
            playerKick.setString(3, playerName);
            playerKick.setDouble(4, x);
            playerKick.setDouble(5, y);
            playerKick.setDouble(6, z);
            playerKick.setString(7, reason);
            playerKick.setBoolean(8, staff);
            playerKick.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void portalCreate(String serverName, String worldName, PortalCreateEvent.CreateReason By){
        try {
            String database = "Portal_Creation";
            PreparedStatement portalCreation = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Caused_By) VALUES(?,?,?)");
            portalCreation.setString(1, serverName);
            portalCreation.setString(2, worldName);
            portalCreation.setString(3, By.toString());
            portalCreation.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void levelChange(String serverName, String playerName, boolean staff){
        try {
            String database = "Player_Level";
            PreparedStatement playerLevel = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,Playername,Is_Staff) VALUES(?,?,?)");
            playerLevel.setString(1, serverName);
            playerLevel.setString(2, playerName);
            playerLevel.setBoolean(3, staff);
            playerLevel.executeUpdate();

        } catch (SQLException e){
            
            e.printStackTrace();
            
        }
    }

    public static void bucketPlace(String serverName, String worldName, String playerName, String bucket, double x, double y, double z, boolean staff){
        try {
            String database = "Bucket_Place";
            PreparedStatement bucketPlace = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Bucket,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?)");
            bucketPlace.setString(1, serverName);
            bucketPlace.setString(2, worldName);
            bucketPlace.setString(3, playerName);
            bucketPlace.setString(4, bucket);
            bucketPlace.setDouble(5, x);
            bucketPlace.setDouble(6, y);
            bucketPlace.setDouble(7, z);
            bucketPlace.setBoolean(8, staff);
            bucketPlace.executeUpdate();

        } catch (SQLException e){
            
            e.printStackTrace();
            
        }
    }

    public static void anvil(String serverName, String playerName, String newname, boolean staff){
        try {
            String database = "Anvil";
            PreparedStatement anvil = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,Playername,New_name,Is_Staff) VALUES(?,?,?,?)");
            anvil.setString(1, serverName);
            anvil.setString(2, playerName);
            anvil.setString(3, newname);
            anvil.setBoolean(4, staff);
            anvil.executeUpdate();

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public static void serverStart(String serverName){
        try {
            String database = "Server_Start";
            PreparedStatement serverStart = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name) VALUES(?)");
            serverStart.setString(1, serverName);
            serverStart.executeUpdate();

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public static void serverStop(String serverName){
        try {
            String database = "Server_Stop";
            PreparedStatement serverStop = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name) VALUES(?)");
            serverStop.setString(1, serverName);
            serverStop.executeUpdate();

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public static void itemDrop(String serverName, String world, String playerName, String item, int amount, int x, int y, int z, List<String> enchantment, String changedName, boolean staff){
        try {
            String database = "Item_Drop";
            PreparedStatement itemDrop = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Item,Amount,X,Y,Z,Enchantment,Changed_Name,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            itemDrop.setString(1, serverName);
            itemDrop.setString(2, world);
            itemDrop.setString(3, playerName);
            itemDrop.setString(4, item);
            itemDrop.setInt(5, amount);
            itemDrop.setInt(6, x);
            itemDrop.setInt(7, y);
            itemDrop.setInt(8, z);
            itemDrop.setString(9, String.valueOf(enchantment));
            itemDrop.setString(10, changedName);
            itemDrop.setBoolean(11, staff);
            itemDrop.executeUpdate();

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public static void enchant(String serverName, String world, String playerName, double x, double y, double z, List<String> enchantment, String item, int cost ,boolean staff){
        try {
            String database = "Enchanting";
            PreparedStatement enchanting = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,X,Y,Z,Enchantment,Item,Cost,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?,?)");
            enchanting.setString(1, serverName);
            enchanting.setString(2, world);
            enchanting.setString(3, playerName);
            enchanting.setDouble(4, x);
            enchanting.setDouble(5, y);
            enchanting.setDouble(6, z);
            enchanting.setString(7, String.valueOf(enchantment));
            enchanting.setString(8, item);
            enchanting.setInt(9, cost);
            enchanting.setBoolean(10, staff);
            enchanting.executeUpdate();

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public static void bookEditing(String serverName, String world, String playerName, int pages, List<String> content, String signed_by, boolean staff){
        try {
            String database = "Book_Editing";
            PreparedStatement enchanting = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Page_Count,Page_Content,Signed_by,Is_Staff) VALUES(?,?,?,?,?,?,?)");
            enchanting.setString(1, serverName);
            enchanting.setString(2, world);
            enchanting.setString(3, playerName);
            enchanting.setInt(4, pages);
            enchanting.setString(5, String.valueOf(content));
            enchanting.setString(6, signed_by);
            enchanting.setBoolean(7, staff);
            enchanting.executeUpdate();

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public static void afk(String serverName, String world, String playerName, double x, double y, double z,boolean staff){

        if (plugin.getAPI() != null) {

            try {
                String database = "AFK";
                PreparedStatement afk = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?)");
                afk.setString(1, serverName);
                afk.setString(2, world);
                afk.setString(3, playerName);
                afk.setDouble(4, x);
                afk.setDouble(5, y);
                afk.setDouble(6, z);
                afk.setBoolean(7, staff);
                afk.executeUpdate();

            } catch (SQLException e) {

                e.printStackTrace();

            }
        }
    }

    public static void itemPickup(String serverName, String world, String playerName, Material item, int amount, double x, double y, double z, String changedName, boolean staff){
        try {
            String database = "Item_Pickup";
            PreparedStatement itemPickup = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Item,Amount,X,Y,Z,Changed_Name,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?,?)");
            itemPickup.setString(1, serverName);
            itemPickup.setString(2, world);
            itemPickup.setString(3, playerName);
            itemPickup.setString(4, String.valueOf(item));
            itemPickup.setInt(5, amount);
            itemPickup.setDouble(6, x);
            itemPickup.setDouble(7, y);
            itemPickup.setDouble(8, z);
            itemPickup.setString(9, changedName);
            itemPickup.setBoolean(10, staff);
            itemPickup.executeUpdate();

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public static void furnace(String serverName, String world, String playerName, Material item, int amount, double x, double y, double z, boolean staff){
        try {
            String database = "Furnace";
            PreparedStatement furnace = plugin.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Item,Amount,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?)");
            furnace.setString(1, serverName);
            furnace.setString(2, world);
            furnace.setString(3, playerName);
            furnace.setString(4, String.valueOf(item));
            furnace.setInt(5, amount);
            furnace.setDouble(6, x);
            furnace.setDouble(7, y);
            furnace.setDouble(8, z);
            furnace.setBoolean(9, staff);
            furnace.executeUpdate();

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public void emptyTable(){

        int when = plugin.getConfig().getInt("MySQL.Data-Deletion");

        if (when <= 0) return;

        try{

            PreparedStatement player_Chat = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Player_Chat WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Commands = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Player_Commands WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement console_Commands = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Console_Commands WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Sign_Text = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Player_Sign_Text WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Join = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Player_Join WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Leave = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Player_Leave WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Kick = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Player_Kick WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Death = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Player_Death WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Teleport = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Player_Teleport WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Level = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Player_Level WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement block_Place = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Block_Place WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement block_Break = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Block_Break WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement portal_Creation = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Portal_Creation WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement bucket_Place = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Bucket_Place WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement anvil = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Anvil WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement TPS = plugin.mySQL.getConnection().prepareStatement("DELETE FROM TPS WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement RAM = plugin.mySQL.getConnection().prepareStatement("DELETE FROM RAM WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement server_Start = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Server_Start WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement server_Stop = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Server_Stop WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement item_Drop = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Item_Drop WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement enchanting = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Enchanting WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement book_Editing = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Book_Editing WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            if (plugin.getAPI() != null) {

                PreparedStatement afk = plugin.mySQL.getConnection().prepareStatement("DELETE FROM AFK WHERE DATE < NOW() - INTERVAL " + when + " DAY");

                afk.executeUpdate();
            }

            PreparedStatement item_pickup = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Item_Pickup WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement furnace = plugin.mySQL.getConnection().prepareStatement("DELETE FROM Furnace WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            player_Chat.executeUpdate();
            player_Commands.executeUpdate();
            console_Commands.executeUpdate();
            player_Sign_Text.executeUpdate();
            player_Join.executeUpdate();
            player_Leave.executeUpdate();
            player_Kick.executeUpdate();
            player_Death.executeUpdate();
            player_Teleport.executeUpdate();
            player_Level.executeUpdate();
            block_Place.executeUpdate();
            block_Break.executeUpdate();
            portal_Creation.executeUpdate();
            bucket_Place.executeUpdate();
            anvil.executeUpdate();
            TPS.executeUpdate();
            RAM.executeUpdate();
            server_Start.executeUpdate();
            server_Stop.executeUpdate();
            item_Drop.executeUpdate();
            enchanting.executeUpdate();
            book_Editing.executeUpdate();
            item_pickup.executeUpdate();
            furnace.executeUpdate();

        }catch (SQLException e){

            plugin.getLogger().warning(ChatColor.RED + "An error has occurred while cleaning the tables, if the error persists, contact the Authors!");
            e.printStackTrace();

        }
    }
}