package com.carpour.logger.Discord;

import com.carpour.logger.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.security.auth.login.LoginException;


public class Discord {

    private final Main main = Main.getInstance();

    private static JDA jda;

    private static TextChannel staffChannel;
    private static TextChannel playerChatChannel;
    private static TextChannel playerCommandsChannel;
    private static TextChannel consoleChannel;
    private static TextChannel playerSignTextChannel;
    private static TextChannel playerJoinChannel;
    private static TextChannel playerLeaveChannel;
    private static TextChannel playerKickChannel;
    private static TextChannel playerDeathChannel;
    private static TextChannel playerTeleportChannel;
    private static TextChannel playerLevelChannel;
    private static TextChannel blockPlaceChannel;
    private static TextChannel blockBreakChannel;
    private static TextChannel portalCreationChannel;
    private static TextChannel bucketPlaceChannel;
    private static TextChannel anvilChannel;
    private static TextChannel TPSChannel;
    private static TextChannel RAMChannel;
    private static TextChannel serverStartChannel;
    private static TextChannel serverStopChannel;
    private static TextChannel itemDropChannel;
    private static TextChannel enchantingChannel;
    private static TextChannel bookEditingChannel;
    private static TextChannel afkChannel;
    private static TextChannel itemPickupChannel;
    private static TextChannel furnaceChannel;

    public void run() {

        if (DiscordFile.get().getBoolean("Discord.Enable")) {

            String botToken = DiscordFile.get().getString("Discord.Bot-Token");

            try {

                jda = JDABuilder.createDefault(botToken).build().awaitReady();

            } catch (InterruptedException | LoginException e) {

                Bukkit.getServer().getConsoleSender().sendMessage("[Logger] " + ChatColor.RED + "An error has occurred whilst connecting to the Bot." +
                        " Is the Bot Key Valid?");
                return;

            }

            String staffChannelID = DiscordFile.get().getString("Discord.Staff.Channel-ID");

            String playerChatChannelID = DiscordFile.get().getString("Discord.Player-Chat.Channel-ID");

            String playerCommandsChannelID = DiscordFile.get().getString("Discord.Player-Commands.Channel-ID");

            String consoleChannelID = DiscordFile.get().getString("Discord.Console-Commands.Channel-ID");

            String playerSignTextChannelID = DiscordFile.get().getString("Discord.Player-Sign-Text.Channel-ID");

            String playerJoinChannelID = DiscordFile.get().getString("Discord.Player-Join.Channel-ID");

            String playerLeaveChannelID = DiscordFile.get().getString("Discord.Player-Leave.Channel-ID");

            String playerKickChannelID = DiscordFile.get().getString("Discord.Player-Kick.Channel-ID");

            String playerDeathChannelID = DiscordFile.get().getString("Discord.Player-Death.Channel-ID");

            String playerTeleportChannelID = DiscordFile.get().getString("Discord.Player-Teleport.Channel-ID");

            String playerLevelChannelID = DiscordFile.get().getString("Discord.Player-Level.Channel-ID");

            String blockPlaceChannelID = DiscordFile.get().getString("Discord.Block-Place.Channel-ID");

            String blockBreakChannelID = DiscordFile.get().getString("Discord.Block-Break.Channel-ID");

            String portalCreationChannelID = DiscordFile.get().getString("Discord.Portal-Creation.Channel-ID");

            String bucketPlaceChannelID = DiscordFile.get().getString("Discord.Bucket-Place.Channel-ID");

            String anvilChannelID = DiscordFile.get().getString("Discord.Anvil.Channel-ID");

            String TPSChannelID = DiscordFile.get().getString("Discord.TPS.Channel-ID");

            String RAMChannelID = DiscordFile.get().getString("Discord.RAM.Channel-ID");

            String serverStartChannelID = DiscordFile.get().getString("Discord.Server-Start.Channel-ID");

            String serverStopChannelID = DiscordFile.get().getString("Discord.Server-Stop.Channel-ID");

            String itemDropChannelID = DiscordFile.get().getString("Discord.Item-Drop.Channel-ID");

            String enchantingChannelID = DiscordFile.get().getString("Discord.Enchanting.Channel-ID");

            String bookEditingChannelID = DiscordFile.get().getString("Discord.Book-Editing.Channel-ID");

            String afkChannelID = DiscordFile.get().getString("Discord.AFK.Channel-ID");

            String itemPickupChannelID = DiscordFile.get().getString("Discord.Item-Pickup.Channel-ID");

            String furnaceChannelID = DiscordFile.get().getString("Discord.Furnace.Channel-ID");


            if (staffChannelID != null && main.getConfig().getBoolean("Staff.Enabled") && !staffChannelID.equals("LINK_HERE")) {

                staffChannel = jda.getTextChannelById(staffChannelID);

            }

            if (playerChatChannelID != null && main.getConfig().getBoolean("Log.Player-Chat") && !playerChatChannelID.equals("LINK_HERE")) {

                playerChatChannel = jda.getTextChannelById(playerChatChannelID);

            }

            if (playerCommandsChannelID != null && main.getConfig().getBoolean("Log.Player-Commands") && !playerCommandsChannelID.equals("LINK_HERE")) {

                playerCommandsChannel = jda.getTextChannelById(playerCommandsChannelID);

            }

            if (consoleChannelID != null && main.getConfig().getBoolean("Log.Console-Commands") && !consoleChannelID.equals("LINK_HERE")) {

                consoleChannel = jda.getTextChannelById(consoleChannelID);

            }

            if (playerSignTextChannelID != null && main.getConfig().getBoolean("Log.Player-Sign-Text") && !playerSignTextChannelID.equals("LINK_HERE")) {

                playerSignTextChannel = jda.getTextChannelById(playerSignTextChannelID);

            }

            if (playerJoinChannelID != null && main.getConfig().getBoolean("Log.Player-Join") && !playerJoinChannelID.equals("LINK_HERE")) {

                playerJoinChannel = jda.getTextChannelById(playerJoinChannelID);

            }

            if (playerLeaveChannelID != null && main.getConfig().getBoolean("Log.Player-Leave") && !playerLeaveChannelID.equals("LINK_HERE")) {

                playerLeaveChannel = jda.getTextChannelById(playerLeaveChannelID);

            }

            if (playerKickChannelID != null && main.getConfig().getBoolean("Log.Player-Kick") && !playerKickChannelID.equals("LINK_HERE")) {

                playerKickChannel = jda.getTextChannelById(playerKickChannelID);

            }

            if (playerDeathChannelID != null && main.getConfig().getBoolean("Log.Player-Death") && !playerDeathChannelID.equals("LINK_HERE")) {

                playerDeathChannel = jda.getTextChannelById(playerDeathChannelID);

            }

            if (playerTeleportChannelID != null && main.getConfig().getBoolean("Log.Player-Teleport") && !playerTeleportChannelID.equals("LINK_HERE")) {

                playerTeleportChannel = jda.getTextChannelById(playerTeleportChannelID);

            }

            if (playerLevelChannelID != null && main.getConfig().getBoolean("Log.Player-Level") && !playerLevelChannelID.equals("LINK_HERE")) {

                playerLevelChannel = jda.getTextChannelById(playerLevelChannelID);

            }

            if (blockPlaceChannelID != null && main.getConfig().getBoolean("Log.Block-Place") && !blockPlaceChannelID.equals("LINK_HERE")) {

                blockPlaceChannel = jda.getTextChannelById(blockPlaceChannelID);

            }

            if (blockBreakChannelID != null && main.getConfig().getBoolean("Log.Block-Break") && !blockBreakChannelID.equals("LINK_HERE")) {

                blockBreakChannel = jda.getTextChannelById(blockBreakChannelID);

            }

            if (portalCreationChannelID != null && main.getConfig().getBoolean("Log.Portal-Creation") && !portalCreationChannelID.equals("LINK_HERE")) {

                portalCreationChannel = jda.getTextChannelById(portalCreationChannelID);

            }

            if (bucketPlaceChannelID != null && main.getConfig().getBoolean("Log.Bucket-Place") && !bucketPlaceChannelID.equals("LINK_HERE")) {

                bucketPlaceChannel = jda.getTextChannelById(bucketPlaceChannelID);

            }

            if (anvilChannelID != null && main.getConfig().getBoolean("Log.Anvil") && !anvilChannelID.equals("LINK_HERE")) {

                anvilChannel = jda.getTextChannelById(anvilChannelID);

            }

            if (TPSChannelID != null && main.getConfig().getBoolean("Log.TPS") && !TPSChannelID.equals("LINK_HERE")) {

                TPSChannel = jda.getTextChannelById(TPSChannelID);

            }

            if (RAMChannelID != null && main.getConfig().getBoolean("Log.RAM") && !RAMChannelID.equals("LINK_HERE")) {

                RAMChannel = jda.getTextChannelById(RAMChannelID);

            }

            if (serverStartChannelID != null && main.getConfig().getBoolean("Log.Server-Start") && !serverStartChannelID.equals("LINK_HERE")) {

                serverStartChannel = jda.getTextChannelById(serverStartChannelID);

            }

            if (serverStopChannelID != null && main.getConfig().getBoolean("Log.Server-Stop") && !serverStopChannelID.equals("LINK_HERE")) {

                serverStopChannel = jda.getTextChannelById(serverStopChannelID);

            }

            if (itemDropChannelID != null && main.getConfig().getBoolean("Log.Item-Drop") && !itemDropChannelID.equals("LINK_HERE")) {

                itemDropChannel = jda.getTextChannelById(itemDropChannelID);

            }

            if (enchantingChannelID != null && main.getConfig().getBoolean("Log.Enchanting") && !enchantingChannelID.equals("LINK_HERE")) {

                enchantingChannel = jda.getTextChannelById(enchantingChannelID);

            }

            if (bookEditingChannelID != null && main.getConfig().getBoolean("Log.Book-Editing") && !bookEditingChannelID.equals("LINK_HERE")) {

                bookEditingChannel = jda.getTextChannelById(bookEditingChannelID);

            }

            if (afkChannelID != null && main.getConfig().getBoolean("Log.AFK") && !afkChannelID.equals("LINK_HERE")) {

                afkChannel = jda.getTextChannelById(afkChannelID);

            }

            if (itemPickupChannelID != null && main.getConfig().getBoolean("Log.Item-Pickup") && !itemPickupChannelID.equals("LINK_HERE")) {

                itemPickupChannel = jda.getTextChannelById(itemPickupChannelID);

            }

            if (furnaceChannelID != null && main.getConfig().getBoolean("Log.Furnace") && !furnaceChannelID.equals("LINK_HERE")) {

                furnaceChannel = jda.getTextChannelById(furnaceChannelID);

            }
        }
    }

    public static void staffChat(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, staffChannel);

    }

    public static void playerChat(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerChatChannel);
    }

    public static void playerCommand(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerCommandsChannel);
    }

    public static void console(String content, boolean contentinAuthorLine) {

        if (consoleChannel == null) return;

        EmbedBuilder builder = new EmbedBuilder().setAuthor("Console");

        if (!contentinAuthorLine) builder.setDescription(content);

        consoleChannel.sendMessage(builder.build()).queue();
    }

    public static void playerSignText(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerSignTextChannel);
    }

    public static void playerJoin(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerJoinChannel);
    }

    public static void playerLeave(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerLeaveChannel);
    }

    public static void playerKick(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerKickChannel);
    }

    public static void playerDeath(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerDeathChannel);
    }

    public static void playerTeleport(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerTeleportChannel);
    }

    public static void playerLevel(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, playerLevelChannel);
    }

    public static void blockPlace(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, blockPlaceChannel);
    }

    public static void blockBreak(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, blockBreakChannel);
    }

    public static void portalCreation(String content, boolean contentinAuthorLine) {

        if (portalCreationChannel == null) return;

        EmbedBuilder builder = new EmbedBuilder().setAuthor("Portal Creation");

        if (!contentinAuthorLine) builder.setDescription(content);

        portalCreationChannel.sendMessage(builder.build()).queue();
    }

    public static void bucketPlace(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, bucketPlaceChannel);
    }

    public static void anvil(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, anvilChannel);
    }

    public static void TPS(String content, boolean contentinAuthorLine) {

        if (TPSChannel == null) return;

        EmbedBuilder builder = new EmbedBuilder().setAuthor("TPS");

        if (!contentinAuthorLine) builder.setDescription(content);

        TPSChannel.sendMessage(builder.build()).queue();
    }

    public static void RAM(String content, boolean contentinAuthorLine) {

        if (RAMChannel == null) return;

        EmbedBuilder builder = new EmbedBuilder().setAuthor("RAM");

        if (!contentinAuthorLine) builder.setDescription(content);

        RAMChannel.sendMessage(builder.build()).queue();
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

    public static void itemDrop(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, itemDropChannel);
    }

    public static void enchanting(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, enchantingChannel);
    }

    public static void bookEditing(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, bookEditingChannel);
    }

    public static void afk(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, afkChannel);
    }

    public static void itemPickup(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, itemPickupChannel);
    }

    public static void furnace(Player player, String content, boolean contentinAuthorLine) {

        discordUtil(player, content, contentinAuthorLine, furnaceChannel);
    }

    private static void discordUtil(Player player, String content, boolean contentinAuthorLine, TextChannel channel) {
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
                Bukkit.getServer().getConsoleSender().sendMessage("[Logger] " + ChatColor.GREEN + "Discord Bot Bridge has been closed!");

            } catch (Exception e) {

                Bukkit.getServer().getLogger().warning("The Connection between the Server and the Discord Bot didn't Shutdown down Safely." +
                        "If this Issue Persists, Contact the Authors!");

                e.printStackTrace();

            }
        }
    }
}
