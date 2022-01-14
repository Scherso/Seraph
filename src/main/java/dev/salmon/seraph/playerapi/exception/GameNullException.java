package dev.salmon.seraph.playerapi.exception;
import dev.salmon.seraph.playerapi.api.games.HypixelGames;

public class GameNullException extends Exception {

    public GameNullException(HypixelGames game) {
        System.out.println(game.getGameName() + " data returned as null");
    }
}
