package dev.salmon.seraph.api.exception;

import dev.salmon.seraph.api.HypixelGames;

public class GameNullException extends Exception {

    public GameNullException(HypixelGames game) {
        System.out.println(game.getGameName() + " data returned as null");
    }
}
