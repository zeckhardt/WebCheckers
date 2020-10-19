package com.webcheckers.app;

import com.webcheckers.model.Game;

import java.util.ArrayList;
import java.util.UUID;

public class GameCenter {

    private ArrayList<Game> games;

    public GameCenter() {
        games = new ArrayList<>();
    }

    public void addGame(Game game){
        games.add(game);
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public Game getGameByUUID(UUID uuid) {
        for (Game g : games) {
            if (g.getUUID().equals(uuid)){
                return g;
            }
        }

        return null;
    }
}
