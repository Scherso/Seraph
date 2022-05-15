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

public class ServerUtils {
    /**
     * @return Whether the player is on Hypixel or not.
     */
    public static boolean isOnHypixel() {
        if (Minecraft.getMinecraft().theWorld != null && !Minecraft.getMinecraft().isSingleplayer())
            return Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
        return false;
    }

    /**
     * @param key The Hypixel API key that should be checked.
     * @return Whether the API key is valid or not.
     */
    public static boolean isApiKeyValid(String key) {
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
