package com.webcheckers.model;

public class Move {

    private int startRow, startCell, endRow, endCell;

    public Move(int startRow, int startCell, int endRow, int endCell) {
        this.startRow = startRow;
        this.startCell = startCell;
        this.endRow = endRow;
        this.endCell = endCell;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCell() {
        return startCell;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCell() {
        return endCell;
    }
}
