package com.webcheckers.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * A class to represent a row in a checkerboard
 *
 * @author Klaus Curde
 */
public class Row {
    private Integer index;
    private ArrayList<Space> spaces = new ArrayList<>();
    private int id;

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

    /**
     * Gets a list of all the spaces
     * @return a list of all the spaces in the row
     */
    public ArrayList<Space> getSpaces() {
        return spaces;
    }

    /**
     * Makes the spaces into an iterator for use by a template engine
     * @return an iterator of spaces
     */
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }

    /**
     * Checks whether a row is identical to another row
     * @param o the other row to check
     * @return if identical
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return id == row.id;
    }

    /**
     * Creates a hashcode for the row
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
