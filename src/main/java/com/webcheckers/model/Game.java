package com.webcheckers.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * A class to represent a game object.
 */
public class Game {

    private UUID uuid;
    private Board board;
    private Player redPlayer;
    private Player whitePlayer;
    private Player.Color currentTurn;
    private ArrayList<Player> spectators;

    /**
     * Constructor
     * @param uuid unique identifier for the game
     * @param board the board object
     * @param redPlayer the player playing as red
     * @param whitePlayer the other player playing as white
     */
    public Game(UUID uuid, Board board, Player redPlayer, Player whitePlayer) {
        this.uuid = uuid;
        this.board = board;
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.currentTurn = Player.Color.RED;
        this.spectators = new ArrayList<>();
    }

    /**
     * Gets the unique identifier for the game
     * @return the unique id
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Gets the board being used in the game
     * @return the board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets whose turn it currently is
     * @return an enum of the Player's turn
     */
    public Player.Color getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Changes the current turn from RED to WHITE, or from WHITE to RED
     */
    public void changeTurn() {
        currentTurn = (currentTurn == Player.Color.RED)? Player.Color.WHITE : Player.Color.RED;
    }

    /**
     * Gets the player that is playing as red
     * @return red player object
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * Gets the player that is playing as white
     * @return white player object
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public void addSpectator(Player player) {
        spectators.add(player);
    }

    public boolean removeSpectator(Player player) {
        return spectators.remove(player);
    }

    public ArrayList<Player> getSpectators() {
        return spectators;
    }
}
