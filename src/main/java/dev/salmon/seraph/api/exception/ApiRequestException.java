package dev.salmon.seraph.api.exception;

import dev.salmon.seraph.util.ChatUtils;

public class ApiRequestException extends Exception {
    public ApiRequestException() {
        ChatUtils.error("There was an API request exception. If this persists, contact a developer.");
    }
}
