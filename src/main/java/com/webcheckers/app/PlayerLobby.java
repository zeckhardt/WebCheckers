package com.webcheckers.app;

import com.webcheckers.model.Player;

import java.util.ArrayList;

public class PlayerLobby {

    private ArrayList<Player> players;

    public PlayerLobby() {
        players = new ArrayList<>();
    }

    public boolean isValidUsername(String username) {
        for (Player p : players) {
            if (p.getUsername().equals(username)) {
                return false;
            }
        }

        return true;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}