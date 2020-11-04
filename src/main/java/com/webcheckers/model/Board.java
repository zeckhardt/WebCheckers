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
    public boolean boardWon = false;


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
            int winStatus = checkWon();
            switch(winStatus) {
                case 0:
                    System.out.println("No one won yet.");
                    break;
                case 1:
                    System.out.println("White won.");
                    boardWon = true;
                    break;
                case 2:
                    System.out.println("Red won.");
                    boardWon = true;
                    break;
                default:
                    System.out.println("Wow this really shouldn't have happened.");
                    break;
            }
        }
            pendingMoves.clear();
    }

    public boolean validateMove(Move move, Player.Color currentTurn) {
        if(jumpAvailable(move.getStartRow(), move.getStartCell(), currentTurn) && !validateJumpMove(move,currentTurn)) {
            return false;
        }
        return validateSquare(move.getEndRow(), move.getEndCell()) &&
                (validateSimpleMove(move, currentTurn) || validateJumpMove(move, currentTurn));
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
                if(space.getPiece().getType() == Piece.Type.SINGLE) {//allows for only forward movement
                    return move.getEndRow() - move.getStartRow() == -1 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 1;
                } else {//allows for movement both back and forward if king
                    return (move.getEndRow() - move.getStartRow() == -1 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 1) ||
                            (move.getEndRow() - move.getStartRow() == 1 &&
                                    Math.abs(move.getEndCell() - move.getStartCell()) == 1);
                }
            } else {
                if(space.getPiece().getType() == Piece.Type.SINGLE) {//allows for only forward movement
                    return move.getEndRow() - move.getStartRow() == 1 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 1;
                } else { //allows for movement both back and forward if king
                    return (move.getEndRow() - move.getStartRow() == 1 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 1) ||
                            (move.getEndRow() - move.getStartRow() == -1 &&
                                    Math.abs(move.getEndCell() - move.getStartCell()) == 1);
                }
            }
        } else {
            return false;
        }
    }

    public boolean validateJumpMove(Move move, Player.Color currentTurn) {
        Space space = rows.get(move.getStartRow()).getSpaces().get(move.getStartCell());
            int midRow = (move.getStartRow() + move.getEndRow()) / 2;
            int midCell = (move.getStartCell() + move.getEndCell()) / 2;
            Piece piece = rows.get(midRow).getSpaces().get(midCell).getPiece();
            if (currentTurn == Player.Color.RED) {
                //only allows forward capture if single
                //allows backward capture if king
                if(space.getPiece() == null) {
                    space = rows.get(pendingMoves.get(0).getStartRow()).getSpaces().get(pendingMoves.get(0).getStartCell());
                }
                if (space.getPiece().getType() == Piece.Type.SINGLE) {//only allows forward capture if single
                    return move.getEndRow() - move.getStartRow() == -2 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 2 &&
                            piece != null &&
                            piece.getColor() == Piece.Color.WHITE;
                } else {//allows backward capture if king
                    return (move.getEndRow() - move.getStartRow() == -2 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 2 &&
                            piece != null &&
                            piece.getColor() == Piece.Color.WHITE) || (move.getEndRow() - move.getStartRow() == 2 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 2 &&
                            piece != null &&
                            piece.getColor() == Piece.Color.WHITE);
                }
            } else {
                if(space.getPiece() == null) {
                    space = rows.get(pendingMoves.get(0).getStartRow()).getSpaces().get(pendingMoves.get(0).getStartCell());
                }
                if(space.getPiece().getType() == Piece.Type.SINGLE) {//only allows forward capture if single
                    return move.getEndRow() - move.getStartRow() == 2 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 2 &&
                            piece != null &&
                            piece.getColor() == Piece.Color.RED;
                } else {//allows backward capture if king
                    return (move.getEndRow() - move.getStartRow() == 2 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 2 &&
                            piece != null &&
                            piece.getColor() == Piece.Color.RED) || (move.getEndRow() - move.getStartRow() == -2 &&
                            Math.abs(move.getEndCell() - move.getStartCell()) == 2 &&
                            piece != null &&
                            piece.getColor() == Piece.Color.RED);
                }
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
            if(startRow < 6 && startRow >= 2) {//makes sure that it doesn't check for moves out of bounds
                frontRow = startRow - 2;
                backRow = startRow + 2;
            } else if(startRow < 2){
                frontRow = startRow;
                backRow = startRow +2;
            } else {
                frontRow = startRow - 2;
                backRow = startRow;
            }
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

    public int checkWon() {
        int redCount= 0;
        int whiteCount= 0;
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.size(); j++) {
                if (rows.get(i).getSpaces().get(j).piece != null) {
                    if (rows.get(i).getSpaces().get(j).piece.getColor().equals(Piece.Color.RED)) {
                        redCount++;
                    } else if (rows.get(i).getSpaces().get(j).piece.getColor().equals(Piece.Color.WHITE))
                        whiteCount++;
                }
            }
        }
        if (redCount == 0) {
            return 1; // white won
        } else if (whiteCount == 0) {
            return 2; // red white
        } else {
            return 0;
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
