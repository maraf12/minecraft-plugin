package com.carpour.loggerbungeecord.Utils;

import com.carpour.loggerbungeecord.Main;
import net.md_5.bungee.api.ChatColor;

public class ASCIIArt {

    private final Main main = Main.getInstance();

    public void Art(){

        main.getLogger().info(ChatColor.DARK_PURPLE + "|\n" +
                ChatColor.DARK_PURPLE + "|" + ChatColor.AQUA + "     __                               \n" +
                ChatColor.DARK_PURPLE + "|" + ChatColor.AQUA + "    / /   ____  ____ _____ ____  _____\n" +
                ChatColor.DARK_PURPLE + "|" + ChatColor.AQUA + "   / /   / __ \\/ __ `/ __ `/ _ \\/ ___/\n" +
                ChatColor.DARK_PURPLE + "|" + ChatColor.AQUA + "  / /___/ /_/ / /_/ / /_/ /  __/ /    \n" +
                ChatColor.DARK_PURPLE + "|" + ChatColor.AQUA + " /_____/\\____/\\__, /\\__, /\\___/_/     \n" +
                ChatColor.DARK_PURPLE + "|" + ChatColor.AQUA + "             /____//____/     " + ChatColor.RED + main.getDescription().getVersion() + ChatColor.BLUE + " [ Proxy Version ]        \n" +
                ChatColor.DARK_PURPLE + "|\n" +
                //ChatColor.DARK_PURPLE + "|" + ChatColor.GOLD + " This is a DEV Build, please report any issues!\n" + ChatColor.DARK_PURPLE + "|\n" +
                ChatColor.DARK_PURPLE + "|" + ChatColor.WHITE + " Discord " + ChatColor.BLUE + "https://discord.gg/MfR5mcpVfX\n" +
                ChatColor.DARK_PURPLE + "|");

    }
}
