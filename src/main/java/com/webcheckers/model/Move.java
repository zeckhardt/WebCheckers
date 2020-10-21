package com.webcheckers.model;

/**
 * A class to handle the location data of a piece for movement
 */
public class Move {

    private int startRow, startCell, endRow, endCell;

    /**
     * Creates a movement object who's state is looked at for movement
     * @param startRow
     * @param startCell
     * @param endRow
     * @param endCell
     */
    public Move(int startRow, int startCell, int endRow, int endCell) {
        this.startRow = startRow;
        this.startCell = startCell;
        this.endRow = endRow;
        this.endCell = endCell;
    }

    /**
     * Get the starting row
     * @return the index of the starting row
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * Gets the starting cell
     * @return the index of the starting cell
     */
    public int getStartCell() {
        return startCell;
    }

    /**
     * Gets the end row
     * @return the index of the end row
     */
    public int getEndRow() {
        return endRow;
    }

    /**
     * Gets the end cell
     * @return the index of the end cell
     */
    public int getEndCell() {
        return endCell;
    }
}
