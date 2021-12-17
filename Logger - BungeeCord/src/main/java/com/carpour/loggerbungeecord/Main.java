package com.carpour.loggerbungeecord;

import com.carpour.loggerbungeecord.Database.SQLite.SQLite;
import com.carpour.loggerbungeecord.Database.SQLite.SQLiteData;
import com.carpour.loggerbungeecord.Commands.Reload;
import com.carpour.loggerbungeecord.Database.MySQL.MySQL;
import com.carpour.loggerbungeecord.Database.MySQL.MySQLData;
import com.carpour.loggerbungeecord.Discord.Discord;
import com.carpour.loggerbungeecord.Discord.DiscordFile;
import com.carpour.loggerbungeecord.Events.*;
import com.carpour.loggerbungeecord.ServerSide.OnReload;
import com.carpour.loggerbungeecord.ServerSide.Start;
import com.carpour.loggerbungeecord.ServerSide.Stop;
import com.carpour.loggerbungeecord.Utils.*;
import net.md_5.bungee.api.plugin.Plugin;

public final class Main extends Plugin {

    private static Main instance;

    private static ConfigManager cm;

    public MySQL mySQL;
    public MySQLData mySQLData;

    private SQLite sqLite;

    public SQLite getSqLite() { return sqLite; }

    public Discord discord;

    @Override
    public void onEnable() {

        instance = this;

        cm = new ConfigManager();
        cm.init();
        
        new Messages().init();
        
        new DiscordFile().init();
        
        discord = new Discord();
        discord.run();
        
        FileHandler fileHandler = new FileHandler(getDataFolder());
        fileHandler.deleteFiles();

        getProxy().getPluginManager().registerListener(this, new OnChat());
        getProxy().getPluginManager().registerListener(this, new OnLogin());
        getProxy().getPluginManager().registerListener(this, new OnLeave());
        getProxy().getPluginManager().registerListener(this, new OnReload());
        getProxy().getPluginManager().registerListener(this, new OnCommand());

        getProxy().getPluginManager().registerCommand(this, new Reload());

        if (getConfig().getBoolean("MySQL.Enable")) {

            mySQL = new MySQL();
            mySQL.connect();
            mySQLData = new MySQLData(this);
            if (mySQL.isConnected()) mySQLData.createTable();
            mySQLData.emptyTable();

        }

        if (getConfig().getBoolean("SQLite.Enable")) {

            sqLite = new SQLite();
            sqLite.connect();
            SQLiteData sqLiteData = new SQLiteData(this);
            if (sqLite.isConnected()) sqLiteData.createTable();
            sqLiteData.emptyTable();

        }

        new ASCIIArt().Art();

        //bstats

        new Metrics(this, 12036);

        //Update Checker
        new UpdateChecker().checkUpdates();

        getLogger().info("has been Enabled!");

        new Start().run();
    }

    public static Main getInstance() {
        return instance;
    }

    public static ConfigManager getConfig() { return cm; }

    @Override
    public void onDisable() {

        new Stop().run();

        if (getConfig().getBoolean("MySQL.Enable") && mySQL.isConnected()) mySQL.disconnect();

        if (getConfig().getBoolean("SQLite.Enable") && sqLite.isConnected()) sqLite.disconnect();

        discord.disconnect();

        getLogger().info("has been Disabled!");
    }
}
