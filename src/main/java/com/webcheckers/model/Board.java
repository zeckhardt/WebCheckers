package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

/**
 * Representation of a board object
 *
 * @author Klaus Curde
 */
public class Board {

    private ArrayList<Row> rows = new ArrayList<>();
    private Stack<Move> pendingMoves;

    /**
     * Create a new board
     */
    public Board() {
        makeRows();
        initPieces();

        pendingMoves = new Stack<>();
    }

    /**
     * Populate the rows iterator with Row objects
     */
    private void makeRows() {
        for (int i = 0; i < 8; i++) {
            this.rows.add(new Row(i));
        }
    }

    private void initPieces() {
        for (int row = 0; row < 3; row++) {
            int col = 0;
            Iterator<Space> spaces = rows.get(row).iterator();
            while (spaces.hasNext()) {
                if ((col + row) % 2 != 0) {
                    spaces.next().placePiece(new Piece(Piece.Type.SINGLE, Piece.Color.WHITE));
                }
                else {
                    spaces.next(); // discard the space from the iterator
                }
                col++;
            }
        }

        for (int row = 5; row < 8; row++) {
            int col = 0;
            Iterator<Space> spaces = rows.get(row).iterator();
            while (spaces.hasNext()) {
                if ((col + row) % 2 != 0) {
                    spaces.next().placePiece(new Piece(Piece.Type.SINGLE, Piece.Color.RED));
                }
                else {
                    spaces.next(); // discard the space from the iterator
                }
                col++;
            }
        }
    }

    public boolean validateMove(Move move, Player.Color currentTurn) {
        return validateSquare(move.getEndRow(), move.getEndCell()) && validateSimpleMove(move, currentTurn);
    }

    public boolean validateSquare(int endRow, int endCell) {
        return (endRow + endCell) % 2 != 0;
    }

    public boolean validateSimpleMove(Move move, Player.Color currentTurn) {
        if (pendingMoves.empty()) {
            if (currentTurn == Player.Color.RED) {
                return move.getEndRow() - move.getStartRow() == -1 &&
                        Math.abs(move.getEndCell() - move.getStartCell()) == 1;
            } else {
                return move.getEndRow() - move.getStartRow() == 1 &&
                        Math.abs(move.getEndCell() - move.getStartCell()) == 1;
            }
        } else {
            return false;
        }
    }

    public void addPendingMove(Move move) {
        pendingMoves.push(move);
    }

    public void backupMove() {
        pendingMoves.pop();
    }

    public ArrayList<Row> getRows() {
        return this.rows;
    }

    public Iterator<Row> iterator() {
        return rows.iterator();
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

        for (int i = 0; i < this.rows.size(); i++) {
            if (!this.rows.equals(that.getRows())) {
                return false;
            }
        }

        return true;
    }
}
