package dev.salmon.seraph.playerapi.api.games;

import dev.salmon.seraph.playerapi.api.HypixelAPI;
import dev.salmon.seraph.playerapi.api.ILeveling;
import dev.salmon.seraph.playerapi.api.stats.Stat;
import dev.salmon.seraph.util.handler.Handler;
import gg.essential.api.utils.Multithreading;
import gg.essential.universal.ChatColor;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class HGameBase extends HypixelAPI {
    private String playerName, playerUUID;
    public boolean isNicked;
    public boolean hasPlayed;

    public HGameBase(String playerName, String playerUUID) {
        this.playerName = playerName;
        this.playerUUID = playerUUID;
    }

    /**
     * @return String of all Formatted Stats combined
     */
    public abstract String getFormattedStats();

    /**
     * @return Game Enumeration of sub-classes Game
     */
    public abstract HypixelGames getGame();

    /**
     * @return List of every Stat from Game
     */
    public abstract List<Stat> getStatList();
    /**
     * @return List of every Formatted Stat from Game
     */
    public abstract List<Stat> getFormattedStatList();

    public abstract void setFormattedStatList();

    /**
     * Method to set the Game Data
     */
    public abstract boolean setData(HypixelGames game);

    public String getPlayerName() {
        return this.playerName;
    }

    public String getPlayerUUID() {
        return this.playerUUID;
    }

    public boolean getIsNicked() {
        return this.isNicked;
    }

    public boolean getHasPlayed() {
        return this.hasPlayed;
    }

    protected List<Stat> setStats(Stat... stats) {
        LinkedList<Stat> statList = new LinkedList<>();

        for (Stat stat : stats) {
            stat.setStat();
            statList.add(stat);
        }

        setFormattedStatList();
        return statList;
    }

    protected void setStatsAsync(List<Stat> statList, Stat... stats) {
        Multithreading.runAsync(() -> {
            for (Stat stat : stats) {
                stat.setStat();
                statList.add(stat);
            }
            setFormattedStatList();
        });
    }

    private int findIndexInArray(Stat[] arr, Stat s) {
        int index = Arrays.binarySearch(arr, s);
        return (index < 0) ? -1 : index;
    }

    public long getLastLogin() {
        try {
            return this.playerObject.get("lastLogin").getAsLong();
        } catch (Exception ex) {
            return 0;
        }
    }

    public long getLastLogout() {
        try {
            return this.playerObject.get("lastLogout").getAsLong();
        } catch (Exception ex) {
            return 0;
        }
    }

    protected long getFirstLogin() {
        try {
            return new BigInteger(this.playerObject.get("_id").getAsString().substring(0, 8), 16).intValue() * 1000L;
        } catch (Exception ex) {
            return 0;
        }
    }

    public long getNetworkXp() {
        try {
            return Double.valueOf(this.playerObject.get("networkExp").getAsString()).longValue();
        } catch (Exception ex) {
            return 0;
        }
    }

    public String getFormattedFirstLogin() {
        long unix_seconds = this.getFirstLogin();
        Date date = new Date(unix_seconds);
        SimpleDateFormat jdf = new SimpleDateFormat("MM-dd-yyyy");
        return jdf.format(date);
    }

    public String getFormattedNWL() {
        return Handler.plsSplit(ILeveling.getExactLevel(this.getNetworkXp())).replace(",", ".");
    }

    public String getFormattedSessionTime() {
        int seconds = (int)((System.currentTimeMillis() - this.getLastLogin()) / 1000);

        int secondsLeft = seconds % 3600 % 60;
        int minutes = (int) Math.floor(seconds % 3600 / 60);
        int hours = (int) Math.floor(seconds / 3600);

        String HH = ChatColor.GREEN.toString() + hours + "h ";
        String MM = minutes + "m ";
        String SS = secondsLeft + "s";
        if (HH.endsWith("0h ")) {
            HH = ChatColor.YELLOW.toString();
        } else {
            SS = "";
        }
        if (HH.equalsIgnoreCase(ChatColor.YELLOW.toString()) && MM.endsWith("0m ")) {
            HH = "";
            MM = ChatColor.RED.toString();
        }
        return HH + MM + SS;
    }

    protected double formatDouble(int int1, int int2) {
        if (int2 == 0) {
            return int1;
        }
        double d;
        double result = (double) int1 / (double) int2;
        DecimalFormat format = new DecimalFormat("##.##");
        String formattedString = format.format(result).replace(",", ".");
        try {
            d = Double.parseDouble(formattedString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            d = int1;
        }
        return d;
    }
}
