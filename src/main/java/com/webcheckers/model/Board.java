package com.webcheckers.model;

import java.util.Objects;

/**
 * Representation of a board object
 *
 * @author Klaus Curde
 */
public class Board {
    private Integer id;
    public Player player1;
    public Player player2;

    /**
     * Create a new board with a unique identifier
     * @param id the id of the board
     */
    public Board(int id, Player player1, Player player2) {
        this.id = Objects.requireNonNull(id, "an ID must be generated");
    }

    /**
     * Getter for the id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Checks if the boards are the same.
     * @param other the other board to look at
     * @return if same
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else return other instanceof Board;
    }
}
