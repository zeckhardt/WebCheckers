package com.webcheckers.model;

import java.util.UUID;

public class Game {

    private UUID uuid;
    private Board board;
    private Player redPlayer;
    private Player whitePlayer;
    private Player.Color currentTurn;

    public Game(UUID uuid, Board board, Player redPlayer, Player whitePlayer) {
        this.uuid = uuid;
        this.board = board;
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.currentTurn = Player.Color.RED;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Board getBoard() {
        return board;
    }

    public Player.Color getCurrentTurn() {
        return currentTurn;
    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }
}
