package dev.salmon.seraph.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.api.exception.*;
import dev.salmon.seraph.util.locraw.LocrawUtils;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class HypixelAPI {

    public JsonObject achievementObj;
    public JsonObject playerObject;
    public static String gamemode = LocrawUtils.getInstance().getLocrawInfo().getGameMode().toLowerCase().replace("duels_", "");

    /**
     * @param uuid Target Player's Hypixel API Whole Object
     * @return String of the specified player's duels wins
     */
    public static String getDuelsWins(String uuid) {
        String requestURL = String.format("https://api.hypixel.net/player?key=%s&uuid=%s", Seraph.getInstance().getConfig().getApiKey(), uuid.replace("-", ""));
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(requestURL);

            System.out.println("Stat checking " + uuid);

            try (InputStream is = client.execute(request).getEntity().getContent()) {
                JsonParser jsonParser = new JsonParser();
                JsonObject object = jsonParser.parse(new InputStreamReader(is, StandardCharsets.UTF_8)).getAsJsonObject();
                JsonObject player = object.get("player").getAsJsonObject();
                JsonObject stats = player.get("stats").getAsJsonObject();
                JsonObject duels = stats.get("Duels").getAsJsonObject();
                return duels.get(gamemode + "_wins").getAsString();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param uuid Target Player's Hypixel API Whole Object
     * @return String of the specified player's duels losses
     */
    public static String getDuelsLosses(String uuid) {
        String requestURL = String.format("https://api.hypixel.net/player?key=%s&uuid=%s", Seraph.getInstance().getConfig().getApiKey(), uuid.replace("-", ""));
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(requestURL);

            System.out.println("Stat checking " + uuid);

            try (InputStream is = client.execute(request).getEntity().getContent()) {
                JsonParser jsonParser = new JsonParser();
                JsonObject object = jsonParser.parse(new InputStreamReader(is, StandardCharsets.UTF_8)).getAsJsonObject();
                JsonObject player = object.get("player").getAsJsonObject();
                JsonObject stats = player.get("stats").getAsJsonObject();
                JsonObject duels = stats.get("Duels").getAsJsonObject();
                return duels.get(gamemode + "_losses").getAsString();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param uuid Target Player's Hypixel API Whole Object
     * @return String of the specified player's duels kills
     */
    public static String getDuelsKills(String uuid) {
        String requestURL = String.format("https://api.hypixel.net/player?key=%s&uuid=%s", Seraph.getInstance().getConfig().getApiKey(), uuid.replace("-", ""));
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(requestURL);

            System.out.println("Stat checking " + uuid);

            try (InputStream is = client.execute(request).getEntity().getContent()) {
                JsonParser jsonParser = new JsonParser();
                JsonObject object = jsonParser.parse(new InputStreamReader(is, StandardCharsets.UTF_8)).getAsJsonObject();
                JsonObject player = object.get("player").getAsJsonObject();
                JsonObject stats = player.get("stats").getAsJsonObject();
                JsonObject duels = stats.get("Duels").getAsJsonObject();
                return duels.get(gamemode + "_kills").getAsString();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param uuid Target Player's Hypixel API Whole Object
     * @return String of the specified player's duels deaths
     */
    public static String getDuelsDeaths(String uuid) {
        String requestURL = String.format("https://api.hypixel.net/player?key=%s&uuid=%s", Seraph.getInstance().getConfig().getApiKey(), uuid.replace("-", ""));
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(requestURL);

            System.out.println("Stat checking " + uuid);

            try (InputStream is = client.execute(request).getEntity().getContent()) {
                JsonParser jsonParser = new JsonParser();
                JsonObject object = jsonParser.parse(new InputStreamReader(is, StandardCharsets.UTF_8)).getAsJsonObject();
                JsonObject player = object.get("player").getAsJsonObject();
                JsonObject stats = player.get("stats").getAsJsonObject();
                JsonObject duels = stats.get("Duels").getAsJsonObject();
                return duels.get(gamemode + "_deaths").getAsString();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param wholeObject Target Player's Hypixel API Whole Object
     * @param game        Game Stats to retrieve
     * @return JsonObject of the specified gameType's Stats
     */
    public JsonObject getGameData(JsonObject wholeObject, HypixelGames game) throws GameNullException {
        JsonObject player = wholeObject.get("player").getAsJsonObject();
        JsonObject stats = player.get("stats").getAsJsonObject();

        if (stats.get(game.getApiName()) != null) {
            return stats.get(game.getApiName()).getAsJsonObject();
        } else {
            throw new GameNullException(game);
        }
    }

    /**
     * @param uuid Target player's UUID
     * @return JsonObject of the player's whole api result
     * @throws InvalidKeyException If Hypixel API Key is Invalid
     * @throws PlayerNullException If Target Player UUID is returned Null from the Hypixel API
     * @throws ApiRequestException If any other exception is thrown during the request
     */
    public JsonObject getWholeObject(String uuid) throws InvalidKeyException, PlayerNullException, ApiRequestException, BadJsonException {
        JsonObject obj = new JsonObject();
        if (Seraph.getInstance().getConfig().getApiKey() == null) {
            throw new InvalidKeyException();
        } else {
            // String.format is basically constructing a String. %s is a placeholder which is defined by the strings placed after the initial String.
            // so String.format("%s", "hello"); is the same as "hello"
            // String.format("https://api.hypixel.net/player?key=%s&uuid=%s", key, uuid); is basically placing the key in the first placeholder, and uuid in the second
            String requestURL = String.format("https://api.hypixel.net/player?key=%s&uuid=%s", Seraph.getInstance().getConfig().getApiKey(), uuid.replace("-", ""));
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpGet request = new HttpGet(requestURL);
                JsonParser parser = new JsonParser();

                System.out.println("Stat checking " + uuid);
                try {
                    StringWriter writer = new StringWriter();
                    IOUtils.copy(new InputStreamReader(client.execute(request).getEntity().getContent(), StandardCharsets.UTF_8), writer);

                    obj = parser.parse(writer.toString()).getAsJsonObject();
                } catch (JsonSyntaxException ex) {
                    throw new BadJsonException();
                }

                if (obj.get("player") == null) {
                    if (obj.get("cause").getAsString().equalsIgnoreCase("Invalid API key"))
                        throw new InvalidKeyException();
                    throw new PlayerNullException();
                } else if (obj.get("player").toString().equalsIgnoreCase("null"))
                    throw new PlayerNullException();
                else if (obj.get("success").getAsString().equals("false"))
                    throw new ApiRequestException();
            } catch (IOException ex) {
                System.out.println("Error with setGameData");
                ex.printStackTrace();
            }
        }

        JsonObject player = obj.get("player").getAsJsonObject();
        if (player.get("achievements") != null)
            this.achievementObj = player.get("achievements").getAsJsonObject();

        /* TODO Kind of Redundant */
        this.playerObject = player;
        return obj;
    }

        public static ChatComponentText getRank(String uuid) throws IOException {
            String requestURL = String.format("https://api.hypixel.net/player?key=%s&uuid=%s", Seraph.getInstance().getConfig().getApiKey(), uuid.replace("-", ""));
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpGet request = new HttpGet(requestURL);

                System.out.println("Stat checking " + uuid);

                try (InputStream is = client.execute(request).getEntity().getContent()) {
                    JsonParser jsonParser = new JsonParser();
                    JsonObject object = jsonParser.parse(new InputStreamReader(is, StandardCharsets.UTF_8)).getAsJsonObject();
                    JsonObject player = object.get("player").getAsJsonObject();

                    String rank = null;
                    String newPackageRank = player.get("newPackageRank").getAsString();
                    String monthlyPackageRank = player.get("monthlyPackageRank").getAsString();
                    String monthlyRankColor = player.get("monthlyRankColor").getAsString();
                    String rankPlusColor = player.get("rankPlusColor").getAsString();

                    if (!monthlyPackageRank.equals("NONE")) {
                        rank = (EnumChatFormatting.valueOf(monthlyRankColor) + "[MVP" + EnumChatFormatting.valueOf(rankPlusColor) + "++" + EnumChatFormatting.valueOf(monthlyRankColor) + "]");
                    }

                    else if (newPackageRank.equals("MVP_PLUS")) {
                        rank = (EnumChatFormatting.AQUA + "[MVP" + EnumChatFormatting.valueOf(rankPlusColor) + "+" + EnumChatFormatting.AQUA + "]");
                    }

                    else if (newPackageRank.equals("MVP")) {
                        rank = (EnumChatFormatting.AQUA + "[MVP]");
                    }

                    else if (newPackageRank.equals("VIP_PLUS")) {
                        rank = EnumChatFormatting.GREEN + "[VIP+]";
                    }

                    else if (newPackageRank.equals("VIP")) {
                        rank = EnumChatFormatting.GREEN + "[VIP]";
                    }

                    else if (newPackageRank.equals("REGULAR")) {
                        rank = "";
                    }

                    ChatComponentText chatComponentText = new ChatComponentText(rank);

                    return chatComponentText;
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            return null;
        }
}