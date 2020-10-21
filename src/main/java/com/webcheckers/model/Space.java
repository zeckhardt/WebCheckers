package com.webcheckers.model;

import java.util.Objects;

/**
 * A class to represent a space on the checkerboard.
 *
 * @author Klaus Curde
 */
public class Space {
    private Integer cellIdx;
    public Piece piece;

    public Space(int cellIdx) {
        this.cellIdx = cellIdx;
    }

    /**
     * Gets the cell index
     * @return the integer of the cell's index
     */
    public Integer getCellIdx() {
        return this.cellIdx;
    }

    /**
     * Shows whether the space is a valid location for a piece to be placed.
     * i.e. there is no piece already on it
     *
     * @return A boolean saying whether there is already a piece
     */
    public Boolean isValid() {
        return this.piece == null;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void placePiece(Piece piece) {
        if (this.piece == null) {
            this.piece = piece;
        }
    }

    public Piece removePiece() {
        Piece p = piece;
        piece = null;
        return p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return Objects.equals(cellIdx, space.cellIdx) &&
                Objects.equals(piece, space.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellIdx, piece);
    }
}
