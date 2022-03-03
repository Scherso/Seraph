package dev.salmon.seraph.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.api.exception.ApiRequestException;
import dev.salmon.seraph.api.exception.BadJsonException;
import dev.salmon.seraph.api.exception.InvalidKeyException;
import dev.salmon.seraph.api.exception.PlayerNullException;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class JsonRequest {

    private final String key = Seraph.Instance.getConfig().getApiKey();
    public JsonObject achievementObj;
    public JsonObject playerObject;

    /**
     * @param uuid Target player's UUID
     * @return JsonObject of the player's whole api result
     * @throws InvalidKeyException If Hypixel API Key is Invalid
     * @throws PlayerNullException If Target Player UUID is returned Null from the Hypixel API
     * @throws ApiRequestException If any other exception is thrown during the request
     */
    public JsonObject getWholeObject(String uuid) throws InvalidKeyException, PlayerNullException, ApiRequestException, BadJsonException {
        JsonObject obj = new JsonObject();
        if (key == null) {
            throw new InvalidKeyException();
        } else {
            // String.format is basically constructing a String. %s is a placeholder which is defined by the strings placed after the initial String.
            // so String.format("%s", "hello"); is the same as "hello"
            // String.format("https://api.hypixel.net/player?key=%s&uuid=%s", key, uuid); is basically placing the key in the first placeholder, and uuid in the second
            String requestURL = String.format("https://api.hypixel.net/player?key=%s&uuid=%s", key, uuid.replace("-", ""));
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
                    if (obj.get("cause").getAsString().equalsIgnoreCase("Invalid API key")) throw new InvalidKeyException();
                    throw new PlayerNullException();
                }
                else if (obj.get("player").toString().equalsIgnoreCase("null"))
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

    // this isn't really useful but i think i might need it
    public static String getUUID(String name) {
        String uuid = "";
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(String.format("https://api.mojang.com/users/profiles/minecraft/%s", uuid));
            try (InputStream is = client.execute(request).getEntity().getContent()) {
                JsonParser jsonParser = new JsonParser();
                JsonObject object = jsonParser.parse(new InputStreamReader(is, StandardCharsets.UTF_8)).getAsJsonObject();
                uuid = object.get("id").getAsString();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return uuid;
    }

}
