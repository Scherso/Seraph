package dev.salmon.seraph.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.regex.Pattern;

public class Utils {

    static JsonParser jsonParser = new JsonParser();

    /**
     * Checking if the player is on Hypixel.
     * @return boolean
     */
    public static boolean isHypixel() {
        if (Minecraft.getMinecraft().theWorld != null && !Minecraft.getMinecraft().isSingleplayer()) {
            return Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
        }
        return false;
    }

    /**
     * @param uuid Adds dashes to uuids, used here to make them "valid"
     * @return UUID with a stripped pattern.
     */
    public static String addDashes(String uuid) {
        Pattern STRIPPED_UUID_PATTERN = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");
        return STRIPPED_UUID_PATTERN.matcher(uuid).replaceAll("$1-$2-$3-$4-$5");
    }

    /**
     * @param username Gets minecraft UUID from Username
     * @return uuid
     */
    public static UUID getUUID(String username) {
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
     * @param username Checks if a player is real or not
     * @return isValidPlayer
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
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isValidPlayer;
    }

    /**
     * @param input Checks for a valid Json by checking if it can be parsed.
     * @return boolean
     */
    public static boolean isValidJson(String input) {
        try {
            jsonParser.parse(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param key checks for a valid API key by calling api.hypixel.net/key
     * @return isValid
     */
    public static boolean isKeyValid(String key) {
        boolean isValid = false;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(String.format("https://api.hypixel.net/key?key=%s", key));
            try (InputStream is = client.execute(request).getEntity().getContent()) {
                JsonParser parser = new JsonParser();
                JsonObject object = parser.parse(new InputStreamReader(is, StandardCharsets.UTF_8)).getAsJsonObject();
                isValid = object.get("success").getAsBoolean();
            } catch (NullPointerException ex) {
                System.out.println("Could Not Retrieve UUID");
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            System.out.println("Could not get UUID");
            ex.printStackTrace();
        }

        return isValid;
    }

}
