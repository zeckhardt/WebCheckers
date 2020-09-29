package com.webcheckers.app;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class PlayerLobby {

    private Message errorMsg;
    private ArrayList<Player> players;

    public PlayerLobby() {
        errorMsg = Message.error("");
        players = new ArrayList<>();
    }

    public boolean isValidUsername(String username) {
        boolean matchesRegex = Pattern.matches("^[A-Za-z0-9 ]+$", username);

        if (matchesRegex) {
            for (Player p : players) {
                if (p.getName().equals(username)) {
                    errorMsg = Message.error("That username is already taken, please try another.");
                    return false;
                }
            }
        } else {
            errorMsg = Message.error("Only alphanumeric characters and spaces are allowed in the username.");
            return false;
        }

        return true;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Message getErrorMsg() {
        return errorMsg;
    }
}