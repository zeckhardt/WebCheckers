package com.webcheckers.model;

import java.util.*;

/**
 * Representation of a board object
 *
 * @author Klaus Curde
 */
public class Board {

    private ArrayList<Row> rows = new ArrayList<>();
    private ArrayList<Move> pendingMoves;


    /**
     * Create a new board
     */
    public Board() {
        makeRows();
        initPieces();

        pendingMoves = new ArrayList<>();
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

    public void submitMove() {
        for (Move m : pendingMoves) {
            int startRow = m.getStartRow();
            int startCell = m.getStartCell();
            int endRow = m.getEndRow();
            int endCell = m.getEndCell();

            Space startSpace = rows.get(startRow).getSpaces().get(startCell);
            Piece p = startSpace.removePiece();
            Space endSpace = rows.get(endRow).getSpaces().get(endCell);
            endSpace.placePiece(p);

            if (Math.abs(startRow - endRow) == 2) {
                int midRow = (startRow + endRow) / 2;
                int midCell = (startCell + endCell) / 2;
                Space midSpace = rows.get(midRow).getSpaces().get(midCell);
                midSpace.removePiece();
            }
            checkKing(p,m);
        }
            pendingMoves.clear();
    }

    public boolean validateMove(Move move, Player.Color currentTurn) {
        if(jumpAvailable(move.getStartRow(), move.getStartCell(), currentTurn) && !validateJumpMove(move,currentTurn)) {
            return false;
        }
        return validateSquare(move.getEndRow(), move.getEndCell()) && (validateSimpleMove(move, currentTurn) || validateJumpMove(move, currentTurn));
    }

    public boolean validateSquare(int endRow, int endCell) {
        return (endRow + endCell) % 2 != 0 && rows.get(endRow).getSpaces().get(endCell).getPiece() == null;
    }

    public boolean validateSimpleMove(Move move, Player.Color currentTurn) {
        int startRow = move.getStartRow();
        int startCell = move.getStartCell();
        Space space = rows.get(startRow).getSpaces().get(startCell);

        if (pendingMoves.isEmpty()) {
            if (currentTurn == Player.Color.RED) {
                if(space.getPiece().getType() == Piece.Type.SINGLE) {
                    return move.getEndRow() - move.getStartRow() == -1 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 1;
                } else { return true; }
            } else {
                if(space.getPiece().getType() == Piece.Type.SINGLE) {
                    return move.getEndRow() - move.getStartRow() == 1 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 1;
                } else { return true; }
            }
        } else {
            return false;
        }
    }

    public boolean validateJumpMove(Move move, Player.Color currentTurn) {
        if (pendingMoves.isEmpty()) {
            int midRow = (move.getStartRow() + move.getEndRow()) / 2;
            int midCell = (move.getStartCell() + move.getEndCell()) / 2;
            Piece piece = rows.get(midRow).getSpaces().get(midCell).getPiece();
            if (currentTurn == Player.Color.RED) {
                return move.getEndRow() - move.getStartRow() == -2 &&
                        Math.abs(move.getEndCell() - move.getStartCell()) == 2 &&
                        piece != null &&
                        piece.getColor() == Piece.Color.WHITE;
            } else {
                return move.getEndRow() - move.getStartRow() == 2 &&
                        Math.abs(move.getEndCell() - move.getStartCell()) == 2 &&
                        piece != null &&
                        piece.getColor() == Piece.Color.RED;
            }
        } else {
            return false;
        }
    }

    public boolean jumpAvailable(int startRow,int startCell, Player.Color currentTurn) {
        Space space = rows.get(startRow).getSpaces().get(startCell);

        if(space.getPiece() == null) {
            return false;
        }

        int frontRow;
        int backRow;
        if (space.getPiece().getColor() == Piece.Color.RED) {
            frontRow = startRow - 2;
            backRow = startRow + 2;
        } else {
            frontRow = startRow + 2;
            backRow = startRow - 2;
        }
        int rightCell = startCell + 2;
        int leftCell = startCell - 2;
        boolean frontRight;
        boolean frontLeft;
        boolean backRight;
        boolean backLeft;

        if(rightCell <= 7 && frontRow <= 7) { //makes sure right cell and front row don't go out of bounds
            frontRight = validateSquare(frontRow, rightCell) &&
                    validateJumpMove(new Move(startRow, startCell, frontRow, rightCell), currentTurn);
        } else {frontRight = false; }
        if (leftCell >= 0 && frontRow <= 7){ //makes sure left cell and front row don't go out of bounds
            frontLeft = validateSquare(frontRow, leftCell) &&
                    validateJumpMove(new Move(startRow,startCell,frontRow,leftCell),currentTurn);
        } else { frontLeft = false; }
        if(rightCell <= 7 && backRow >= 0) {//makes sure right cell and back row don't go out of bounds
            backRight = validateSquare(backRow, rightCell) &&
                    validateJumpMove(new Move(startRow, startCell, backRow, rightCell), currentTurn);
        } else { backRight = false; }
        if(leftCell >= 0 && backRow >= 0) {//makes sure left cell and back row don't go out of bounds
            backLeft = validateSquare(backRow, leftCell) &&
                    validateJumpMove(new Move(startRow, startCell, backRow, leftCell), currentTurn);
        } else { backLeft = false; }

        return frontRight || frontLeft || backRight || backLeft;//returns true if one or more jump move is available
    }

    public void checkKing(Piece piece, Move move){
        if(piece != null && move != null){
            if(piece.getColor() == Piece.Color.RED && move.getEndRow() == 0) {
                piece.toKing();
            }
            if(piece.getColor() == Piece.Color.WHITE && move.getEndRow() == 7) {
                piece.toKing();
            }
        }
    }

    public void addPendingMove(Move move) {
        pendingMoves.add(move);
    }

    public ArrayList<Move> getPendingMoves(){
        return pendingMoves;
    }

    public void backupMove() {
        pendingMoves.remove(pendingMoves.size() - 1);
    }

    public ArrayList<Row> getRows() {
        return this.rows;
    }

    public Iterator<Row> iterator() {
        return rows.iterator();
    }

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
