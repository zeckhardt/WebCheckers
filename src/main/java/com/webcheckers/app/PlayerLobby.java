package com.webcheckers.app;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * Logs player activities such as signing in.
 *
 * @author Aidan Lynch
 */
public class PlayerLobby {

    // Attributes
    private Message errorMsg;
    private ArrayList<Player> players;

    /**
     * Create the player lobby.
     */
    public PlayerLobby() {
        errorMsg = Message.error("");
        players = new ArrayList<>();
    }

    /**
     * Checks whether the username provided is valid given a pattern and a
     * list of current players.
     *
     * @param username
     *   the username given during sign in
     * @return
     *   true if the username is valid, false otherwise
     */
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

    /**
     * Adds player to the lobby.
     *
     * @param player
     *   the player to be added
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
    }

    /**
     * Gets the list of players currently in the lobby.
     *
     * @return
     *   list of players signed in
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets a player from the lobby by their name
     * @param name the name of the player
     * @return the player object
     */
    public Player getPlayerByName(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)){
                return p;
            }
        }

        return null;
    }

    /**
     * Returns the value of the error message. Used for specifying the issue on
     * the sign in page.
     *
     * @return
     *   the error message
     */
    public Message getErrorMsg() {
        return errorMsg;
    }
}