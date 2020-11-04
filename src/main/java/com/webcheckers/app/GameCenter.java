package com.webcheckers.app;

import com.webcheckers.model.Game;

import java.util.ArrayList;
import java.util.UUID;

/**
 * An object to manage games
 */
public class GameCenter {

    private ArrayList<Game> games;

    /**
     * Creates a new gamecenter object
     */
    public GameCenter() {
        games = new ArrayList<>();
    }

    /**
     * Adds a game to the list of games
     * @param game the game to add
     */
    public void addGame(Game game){
        games.add(game);
    }

    /**
     * Removes a game from the list of games
     * @param game the game to remove
     */
    public void removeGame(Game game){
        games.remove(game);
    }

    /**
     * Get the list of all of the games
     * @return the list of games
     */
    public ArrayList<Game> getGames() {
        return games;
    }

    /**
     * Get a specific game by its uuid
     * @param uuid the identifier to get the game by
     * @return the game object
     */
    public Game getGameByUUID(UUID uuid) {
        for (Game g : games) {
            if (g.getUUID().equals(uuid)){
                return g;
            }
        }

        return null;
    }
}
