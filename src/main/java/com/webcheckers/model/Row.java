package com.webcheckers.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A class to represent a row in a checkerboard
 *
 * @author Klaus Curde
 */
public class Row {
    private Integer index;
    private ArrayList<Space> spaces = new ArrayList<>();

    /**
     * Create a row object
     * @param index the index the row can be accessed by
     */
    public Row(int index) {
        this.index = index;
        makeSpaces();
    }

    /**
     * Populate the spaces iterator with Space objects
     */
    private void makeSpaces(){
        for (int i = 0; i < 8; i++) {
            this.spaces.add(new Space(i));
        }
    }

    /**
     * Gets the index of the row
     * @return The row index
     */
    public Integer getIndex() {
        return index;
    }
}
