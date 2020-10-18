package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Representation of a board object
 *
 * @author Klaus Curde
 */
public class Board {

    private ArrayList<Row> rows = new ArrayList<>();

    /**
     * Create a new board
     */
    public Board() {
        makeRows();
    }

    /**
     * Populate the rows iterator with Row objects
     */
    private void makeRows() {
        for (int i = 0; i < 8; i++) {
            this.rows.add(new Row(i));
        }
    }

    public ArrayList<Row> getRows() {
        return this.rows;
    }

    /**
     * Checks if the boards are the same.
     * @param other the other board to look at
     * @return if same
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        else if (!(other instanceof Board)) return false;
        Board that = (Board) other;
        return this.rows.equals(that.getRows());
    }
}
