package com.webcheckers.app;

import com.webcheckers.model.Board;

import java.util.UUID;

public class Game {

    private UUID uuid;
    private Board board;

    public Game(UUID uuid, Board board) {
        this.uuid = uuid;
        this.board = board;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Board getBoard() {
        return board;
    }
}
