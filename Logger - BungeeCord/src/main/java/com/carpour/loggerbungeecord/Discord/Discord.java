package com.carpour.loggerbungeecord.Discord;

import com.carpour.loggerbungeecord.Main;
import com.carpour.loggerbungeecord.Utils.ConfigManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import javax.security.auth.login.LoginException;


public class Discord {

    private static JDA jda;

    private final ConfigManager cm = Main.getConfig();

    private static TextChannel staffChannel;
    private static TextChannel playerChatChannel;
    private static TextChannel playerCommandChannel;
    private static TextChannel playerLoginChannel;
    private static TextChannel playerLeaveChannel;
    private static TextChannel serverReloadChannel;
    private static TextChannel serverStartChannel;
    private static TextChannel serverStopChannel;

    public void run() {

        if (DiscordFile.getBoolean("Discord.Enable")) {

            String botToken = DiscordFile.getString("Discord.Bot-Token");

            try {

                jda = JDABuilder.createDefault(botToken).build().awaitReady();

            } catch (InterruptedException | LoginException e) {

                Main.getInstance().getLogger().warning("An error has occurred whilst connecting to the Bot." +
                        " Is the Bot Key Valid?");
                return;

            }

            String staffChannelID = DiscordFile.getString("Discord.Staff.Channel-ID");

            String playerChatChannelID = DiscordFile.getString("Discord.Player-Chat.Channel-ID");

            String playerCommandChannelID = DiscordFile.getString("Discord.Player-Command.Channel-ID");

            String playerLoginChannelID = DiscordFile.getString("Discord.Player-Login.Channel-ID");

            String playerLeaveChannelID = DiscordFile.getString("Discord.Player-Leave.Channel-ID");

            String serverReloadChannelID = DiscordFile.getString("Discord.Server-Side.Restart.Channel-ID");

            String serverStartChannelID = DiscordFile.getString("Discord.Server-Side.Start.Channel-ID");

            String serverStopChannelID = DiscordFile.getString("Discord.Server-Side-Stop.Channel-ID");


            if (!(staffChannelID.isEmpty()) && cm.getBoolean("Staff.Enabled") && !staffChannelID.equals("LINK_HERE")) {

                staffChannel = jda.getTextChannelById(staffChannelID);

            }

            if (!(playerChatChannelID.isEmpty()) && cm.getBoolean("Log-Player.Chat") && !playerChatChannelID.equals("LINK_HERE")) {

                playerChatChannel = jda.getTextChannelById(playerChatChannelID);

            }

            if (!(playerCommandChannelID.isEmpty()) && cm.getBoolean("Log-Player.Command") && !playerCommandChannelID.equals("LINK_HERE")) {

                playerCommandChannel = jda.getTextChannelById(playerCommandChannelID);

            }

            if (!(playerLoginChannelID.isEmpty()) && cm.getBoolean("Log-Player.Login") && !playerLoginChannelID.equals("LINK_HERE")) {

                playerLoginChannel = jda.getTextChannelById(playerLoginChannelID);

            }

            if (!(playerLeaveChannelID.isEmpty()) && cm.getBoolean("Log-Player.Leave") && !playerLeaveChannelID.equals("LINK_HERE")) {

                playerLeaveChannel = jda.getTextChannelById(playerLeaveChannelID);

            }

            if (!(serverReloadChannelID.isEmpty()) && cm.getBoolean("Log-Server.Reload") && !serverReloadChannelID.equals("LINK_HERE")) {

                serverReloadChannel = jda.getTextChannelById(serverReloadChannelID);

            }

            if (!(serverStartChannelID.isEmpty()) && cm.getBoolean("Log-Server.Start") && !serverStartChannelID.equals("LINK_HERE")) {

                serverStartChannel = jda.getTextChannelById(serverStartChannelID);

            }

            if (!(serverStopChannelID.isEmpty()) && cm.getBoolean("Log-Server.Stop") && !serverStopChannelID.equals("LINK_HERE")) {

                serverStopChannel = jda.getTextChannelById(serverStopChannelID);

            }
        }
    }

    public static void staffChat(ProxiedPlayer player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, staffChannel);

    }

    public static void playerChat(ProxiedPlayer player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerChatChannel);
    }

    public static void playerCommand(ProxiedPlayer player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerCommandChannel);
    }

    public static void playerLogin(ProxiedPlayer player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerLoginChannel);
    }

    public static void playerLeave(ProxiedPlayer player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerLeaveChannel);
    }

    public static void serverReload(String player, String content, boolean contentinAuthorLine) {

        if (serverReloadChannel == null) return;

        EmbedBuilder builder = new EmbedBuilder().setAuthor("Console Reload");

        if (!contentinAuthorLine) builder.setDescription(content);

        serverReloadChannel.sendMessage(builder.build()).queue();
    }

    public static void serverStart(String content, boolean contentinAuthorLine) {

        if (serverStartChannel == null) return;

        EmbedBuilder builder = new EmbedBuilder().setAuthor("Server Start");

        if (!contentinAuthorLine) builder.setDescription(content);

        serverStartChannel.sendMessage(builder.build()).queue();
    }

    public static void serverStop(String content, boolean contentinAuthorLine) {

        if (serverStopChannel == null) return;

        EmbedBuilder builder = new EmbedBuilder().setAuthor("Server Stop");

        if (!contentinAuthorLine) builder.setDescription(content);

        serverStopChannel.sendMessage(builder.build()).queue();
    }

    private static void discordUtil(ProxiedPlayer player, String content, boolean contentinAuthorLine, TextChannel channel) {
        if (channel == null) return;

        EmbedBuilder builder = new EmbedBuilder().setAuthor(contentinAuthorLine ? content : player.getName(),
                null, "https://crafatar.com/avatars/" + player.getUniqueId() + "?overlay=1");

        if (!contentinAuthorLine) builder.setDescription(content);

        channel.sendMessage(builder.build()).queue();
    }

    public void disconnect() {

        if (jda != null) {
            try {

                jda.shutdown();
                jda = null;
                Main.getInstance().getLogger().info("Discord Bot Bridge has been closed!");

            } catch (Exception e) {

                Main.getInstance().getLogger().warning("The Connection between the Server and the Discord Bot didn't Shutdown down Safely." +
                        "If this Issue Persists, Contact the Authors!");

                e.printStackTrace();

            }
        }
    }
}
