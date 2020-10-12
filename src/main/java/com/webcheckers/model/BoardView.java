package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Representation of a board object
 *
 * @author Klaus Curde
 */
public class BoardView {
    private Integer id;

    private ArrayList<Row> rows = new ArrayList<>();

    /**
     * Create a new board with a unique identifier
     * @param id
     */
    public BoardView(Integer id) {
        this.id = Objects.requireNonNull(id, "an ID must be generated");
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
        } else return other instanceof BoardView;
    }
}
