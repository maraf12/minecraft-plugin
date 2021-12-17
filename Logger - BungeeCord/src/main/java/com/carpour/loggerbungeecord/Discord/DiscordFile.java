package com.carpour.loggerbungeecord.Discord;

import com.carpour.loggerbungeecord.Main;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

public class DiscordFile {

    private static Configuration discord = null;

    public void init() {

        saveDefaultConfig();

        try {

            discord = ConfigurationProvider.getProvider(YamlConfiguration.class).load(getFile());

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static boolean getBoolean(String key) {
        return discord.getBoolean(key);
    }

    public static String getString(String key) {

        String str = discord.getString(key);

        str = ChatColor.translateAlternateColorCodes('&', str);

        return str;
    }

    public File getFile() {
        return new File(Main.getInstance().getDataFolder(), "discord - Bungee.yml");
    }

    private void saveDefaultConfig() {

        if (!Main.getInstance().getDataFolder().exists()) Main.getInstance().getDataFolder().mkdir();

        File file = getFile();

        if (!file.exists()) {

            try {

                file.createNewFile();

                try (InputStream is = Main.getInstance().getResourceAsStream("discord - Bungee.yml")) {

                    OutputStream os = new FileOutputStream(file);
                    ByteStreams.copy(is, os);
                    os.close();

                }

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
    }
}
