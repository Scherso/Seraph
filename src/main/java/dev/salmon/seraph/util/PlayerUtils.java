package dev.salmon.seraph.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.regex.Pattern;

public class PlayerUtils {
    private static final Pattern STRIPPED_UUID_PATTERN = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");

    /**
     * @param uuid The UUID to modify.
     * @return The UUID provided with dashes added, as though it was newly created.
     */
    public static String addDashes(String uuid) {
        return STRIPPED_UUID_PATTERN.matcher(uuid).replaceAll("$1-$2-$3-$4-$5");
    }

    /**
     * @param username Gets minecraft UUID from Username
     * @return The player's UUID, or null if it can't be fetched.
     */
    public static UUID getUuid(String username) {
        UUID uuid = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(String.format("https://api.mojang.com/users/profiles/minecraft/%s", username));
            try (InputStream is = client.execute(request).getEntity().getContent()) {
                JsonParser parser = new JsonParser();
                JsonObject object = parser.parse(new InputStreamReader(is, StandardCharsets.UTF_8)).getAsJsonObject();
                uuid = UUID.fromString(addDashes(object.get("id").getAsString()));
            } catch (NullPointerException ex) {
                System.out.println("Could Not Retrieve UUID");
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            System.out.println("Could not get UUID");
            ex.printStackTrace();
        }

        return uuid;
    }

    /**
     * @param username The name of the player to check.
     * @return Whether the player is real or not.
     */
    public static boolean isValidPlayer(String username) {
        boolean isValidPlayer = false;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(String.format("https://api.mojang.com/users/profiles/minecraft/%s", username));
            try (InputStream is = client.execute(request).getEntity().getContent()) {
                JsonParser parser = new JsonParser();
                JsonObject object = parser.parse(new InputStreamReader(is, StandardCharsets.UTF_8)).getAsJsonObject();
                isValidPlayer = object.has("name");
            } catch (NullPointerException ex) {
                System.out.println("Null or invalid player provided by the server.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isValidPlayer;
    }
}
