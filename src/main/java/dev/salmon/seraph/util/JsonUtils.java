package dev.salmon.seraph.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public class JsonUtils {
    private static final JsonParser JSON_PARSER = new JsonParser();
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /**
     * @param input The string to be checked for JSON validity.
     * @return Whether the provided input is a valid JSON string or not.
     */
    public static boolean isValidJson(String input) {
        try {
            JSON_PARSER.parse(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static JsonParser getJsonParser() {
        return JSON_PARSER;
    }

    public static Gson getGson() {
        return GSON;
    }
}
