package dev.salmon.seraph.playerapi.api.games.bedwars;

import dev.salmon.seraph.playerapi.api.games.HGameBase;
import dev.salmon.seraph.playerapi.api.stats.StatInt;
import gg.essential.universal.ChatColor;

public abstract class BedwarsUtil extends HGameBase {
    /* index of starWave */
    long starWave = 0;
    public BedwarsUtil(String playerName, String playerUUID) {
        super(playerName, playerUUID);
    }

    public double getFKDR(Bedwars bw) {
        return this.formatDouble(((StatInt)bw.finalKills).getValue(), ((StatInt)bw.finalDeaths).getValue());
    }

    //this is where I format all the stats and stat colors. You can modify this to your liking
    // Bedwars class is how I handle all the stats that are grabbed, you can also modify which stats are grabbed and add them to the stat list

    public ChatColor getFKDRColor(double fkdr) {
        if (fkdr < 1.5) {
            return ChatColor.GRAY;
        } else if (fkdr < 3.5) {
            return ChatColor.WHITE;
        } else if (fkdr < 5) {
            return ChatColor.GOLD;
        } else if (fkdr < 10) {
            return ChatColor.DARK_GREEN;
        } else if (fkdr < 20) {
            return ChatColor.RED;
        } else if (fkdr < 50) {
            return ChatColor.DARK_RED;
        } else if (fkdr < 100) {
            return ChatColor.LIGHT_PURPLE;
        } else {
            return ChatColor.DARK_PURPLE;
        }
    }

    public double getWLR(Bedwars bw) {
        return this.formatDouble(((StatInt)bw.wins).getValue(), ((StatInt)bw.losses).getValue());
    }

    public ChatColor getWLRColor(double wlr) {
        if (wlr < 1) {
            return ChatColor.GRAY;
        } else if (wlr < 2) {
            return ChatColor.WHITE;
        } else if (wlr < 3) {
            return ChatColor.GOLD;
        } else if (wlr < 5) {
            return ChatColor.DARK_GREEN;
        } else if (wlr < 10) {
            return ChatColor.RED;
        } else if (wlr < 15) {
            return ChatColor.DARK_RED;
        } else if (wlr < 50) {
            return ChatColor.LIGHT_PURPLE;
        } else {
            return ChatColor.DARK_PURPLE;
        }
    }

    public double getBBLR(Bedwars bw) {
        return this.formatDouble(((StatInt)bw.bedsBroken).getValue(), ((StatInt)bw.bedsLost).getValue());
    }

    public ChatColor getBBLRColor(double bblr) {
        if (bblr < 1.5) {
            return ChatColor.GRAY;
        } else if (bblr < 2.5) {
            return ChatColor.WHITE;
        } else if (bblr < 5) {
            return ChatColor.GOLD;
        } else if (bblr < 7.5) {
            return ChatColor.DARK_GREEN;
        } else if (bblr < 10) {
            return ChatColor.RED;
        } else if (bblr < 15) {
            return ChatColor.DARK_RED;
        } else if (bblr < 50) {
            return ChatColor.LIGHT_PURPLE;
        } else {
            return ChatColor.DARK_PURPLE;
        }
    }

    public ChatColor getWSColor(double ws) {
        if (ws < 5) {
            return ChatColor.GRAY;
        } else if (ws < 10) {
            return ChatColor.WHITE;
        } else if (ws < 20) {
            return ChatColor.GOLD;
        } else if (ws < 35) {
            return ChatColor.DARK_GREEN;
        } else if (ws < 50) {
            return ChatColor.RED;
        } else if (ws < 75) {
            return ChatColor.DARK_RED;
        } else if (ws < 100) {
            return ChatColor.LIGHT_PURPLE;
        } else {
            return ChatColor.DARK_PURPLE;
        }
    }

    // we need to update this o_O
    public ChatColor getStarColor(int star) {
        if (star < 100) {
            return ChatColor.GRAY;
        } else if (star < 200) {
            return ChatColor.WHITE;
        } else if (star < 300) {
            return ChatColor.GOLD;
        } else if (star < 400) {
            return ChatColor.AQUA;
        } else if (star < 500) {
            return ChatColor.DARK_GREEN;
        } else if (star < 600) {
            return ChatColor.DARK_AQUA;
        } else if (star < 700) {
            return ChatColor.DARK_RED;
        } else if (star < 800) {
            return ChatColor.LIGHT_PURPLE;
        } else if (star < 900) {
            return ChatColor.BLUE;
        } else {
            return ChatColor.DARK_PURPLE;
        }
    }

    /* should be the actual method used when displaying stars */
    public String getStarWithColor(int star) {
        /* how many colors there are in the array */
        int colorAmount;
        String starString = Integer.toString(star);
        if (star < 1000) {
            return getStarColor(star) + starString + "\u272B";

        /* prestige stars */
        } else {
            /* if it doesn't meet any requirements, just use normal rainbow colors and star unicode */
            ChatColor[] colors = new ChatColor[]{ChatColor.RED, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.GREEN, ChatColor.AQUA, ChatColor.LIGHT_PURPLE, ChatColor.DARK_PURPLE};
            String starUnicode = "\u272B";
            /* rainbow prestige has 7 colors */
            colorAmount = 7;

            /* 1,000-1,999 stars */
            if (star >= 1100) {
                if (star < 2000) {
                    starUnicode = "\u272A";
                    colorAmount = 5;
                    if (star < 1200) {
                        colors = new ChatColor[]{ChatColor.WHITE, ChatColor.WHITE, ChatColor.WHITE, ChatColor.WHITE, ChatColor.GRAY};
                    } else if (star < 1300) {
                        colors = new ChatColor[]{ChatColor.YELLOW, ChatColor.YELLOW, ChatColor.YELLOW, ChatColor.YELLOW, ChatColor.GOLD};
                    } else if (star < 1400) {
                        colors = new ChatColor[]{ChatColor.AQUA, ChatColor.AQUA, ChatColor.AQUA, ChatColor.AQUA, ChatColor.DARK_AQUA};
                    } else if (star < 1500) {
                        colors = new ChatColor[]{ChatColor.GREEN, ChatColor.GREEN, ChatColor.GREEN, ChatColor.GREEN, ChatColor.DARK_GREEN};
                    } else if (star < 1600) {
                        colors = new ChatColor[]{ChatColor.DARK_AQUA, ChatColor.DARK_AQUA, ChatColor.DARK_AQUA, ChatColor.DARK_AQUA, ChatColor.BLUE};
                    } else if (star < 1700) {
                        colors = new ChatColor[]{ChatColor.RED, ChatColor.RED, ChatColor.RED, ChatColor.RED, ChatColor.DARK_RED};
                    } else if (star < 1800) {
                        colors = new ChatColor[]{ChatColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.DARK_PURPLE};
                    } else if (star < 1900) {
                        colors = new ChatColor[]{ChatColor.BLUE, ChatColor.BLUE, ChatColor.BLUE, ChatColor.BLUE, ChatColor.DARK_BLUE};
                    } else {
                        colors = new ChatColor[]{ChatColor.DARK_PURPLE, ChatColor.DARK_PURPLE, ChatColor.DARK_PURPLE, ChatColor.DARK_PURPLE, ChatColor.DARK_GRAY};
                    }
                /* 2,000+ stars */
                } else {
                    starUnicode = "\u269D";
                    colorAmount = 6;
                    if (star < 2100) {
                        colorAmount = 7;
                        colors = new ChatColor[]{ChatColor.DARK_GRAY, ChatColor.GRAY, ChatColor.WHITE, ChatColor.WHITE, ChatColor.GRAY, ChatColor.GRAY, ChatColor.DARK_GRAY};
                    } else if (star < 2200) {
                        colors = new ChatColor[]{ChatColor.WHITE, ChatColor.WHITE, ChatColor.YELLOW, ChatColor.YELLOW, ChatColor.GOLD, ChatColor.GOLD};
                    } else if (star < 2300) {
                        colorAmount = 7;
                        colors = new ChatColor[]{ChatColor.GOLD, ChatColor.GOLD, ChatColor.WHITE, ChatColor.WHITE, ChatColor.AQUA, ChatColor.DARK_AQUA, ChatColor.DARK_AQUA};
                    } else if (star < 2400) {
                        colorAmount = 7;
                        colors = new ChatColor[]{ChatColor.DARK_PURPLE, ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.YELLOW};
                    } else if (star < 2500) {
                        colorAmount = 7;
                        colors = new ChatColor[]{ChatColor.AQUA, ChatColor.AQUA, ChatColor.WHITE, ChatColor.WHITE, ChatColor.GRAY, ChatColor.GRAY, ChatColor.DARK_GRAY};
                    } else if (star < 2600) {
                        colors = new ChatColor[]{ChatColor.WHITE, ChatColor.WHITE, ChatColor.GREEN, ChatColor.GREEN, ChatColor.DARK_GREEN, ChatColor.DARK_GREEN};
                    } else if (star < 2700) {
                        colorAmount = 7;
                        colors = new ChatColor[]{ChatColor.DARK_RED, ChatColor.DARK_RED, ChatColor.RED, ChatColor.RED, ChatColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.DARK_PURPLE};
                    } else if (star < 2800) {
                        colors = new ChatColor[]{ChatColor.YELLOW, ChatColor.YELLOW, ChatColor.WHITE, ChatColor.WHITE, ChatColor.DARK_GRAY, ChatColor.DARK_GRAY};
                    } else if (star < 2900) {
                        colorAmount = 7;
                        colors = new ChatColor[]{ChatColor.GREEN, ChatColor.GREEN, ChatColor.DARK_GREEN, ChatColor.DARK_GREEN, ChatColor.GOLD, ChatColor.GOLD, ChatColor.YELLOW};
                    } else if (star < 3000) {
                        colorAmount = 7;
                        colors = new ChatColor[]{ChatColor.AQUA, ChatColor.AQUA, ChatColor.DARK_AQUA, ChatColor.DARK_AQUA, ChatColor.BLUE, ChatColor.BLUE, ChatColor.DARK_BLUE};
                    } else {
                        colorAmount = 7;
                        colors = new ChatColor[]{ChatColor.YELLOW, ChatColor.YELLOW, ChatColor.GOLD, ChatColor.GOLD, ChatColor.RED, ChatColor.RED, ChatColor.DARK_RED};
                    }
                }
            }

            this.starWave = (long)((System.currentTimeMillis() % 850L / 850.0F) * colorAmount);

            return colors[(int)(this.starWave + 4) % colorAmount].toString() + starString.charAt(0) + colors[(int)(this.starWave + 3) % colorAmount].toString() + starString.charAt(1) + colors[(int)(this.starWave + 2) % colorAmount].toString() + starString.charAt(2) + colors[(int)(this.starWave + 1) % colorAmount].toString() + starString.charAt(3) + colors[(int)(this.starWave) % colorAmount].toString() + starUnicode;
        }
    }

    public ChatColor getFinalsColor(int finals) {
        if (finals < 300) {
            return ChatColor.GRAY;
        } else if (finals < 500) {
            return ChatColor.WHITE;
        } else if (finals < 1000) {
            return ChatColor.GOLD;
        } else if (finals < 2000) {
            return ChatColor.DARK_GREEN;
        } else if (finals < 3000) {
            return ChatColor.RED;
        } else if (finals < 5000) {
            return ChatColor.DARK_RED;
        } else if (finals < 10000) {
            return ChatColor.LIGHT_PURPLE;
        } else {
            return ChatColor.DARK_PURPLE;
        }
    }

    public ChatColor getWinsColor(int wins) {
        if (wins < 100) {
            return ChatColor.GRAY;
        } else if (wins < 300) {
            return ChatColor.WHITE;
        } else if (wins < 500) {
            return ChatColor.GOLD;
        } else if (wins < 1000) {
            return ChatColor.DARK_GREEN;
        } else if (wins < 2000) {
            return ChatColor.RED;
        } else if (wins < 2500) {
            return ChatColor.DARK_RED;
        } else if (wins < 3000) {
            return ChatColor.LIGHT_PURPLE;
        } else {
            return ChatColor.DARK_PURPLE;
        }
    }
}