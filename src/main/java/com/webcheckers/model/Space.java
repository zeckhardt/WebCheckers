package com.webcheckers.model;

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
    public boolean equals(Object other) {
        if (other == null) return false;
        else if (!(other instanceof Space)) return false;
        Space that = (Space) other;
        return this.cellIdx == that.getCellIdx() && this.piece.equals(that.getPiece());
    }
}
