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

    /**
     * Place pieces on all the required spaces on the board
     */
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

    /**
     * Removes the piece from its old space and then moves it to its new space. Also checks if that resulted in a win.
     */
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

    /**
     * Checks whether a move is valid
     * @param move the move being checked
     * @param currentTurn the color whose turn it is
     * @return
     */
    public boolean validateMove(Move move, Player.Color currentTurn) {
        if(jumpAvailable(move.getStartRow(), move.getStartCell(), currentTurn) && !validateJumpMove(move,currentTurn)) {
            return false;
        }
        return validateSquare(move.getEndRow(), move.getEndCell()) &&
                (validateSimpleMove(move, currentTurn) || validateJumpMove(move, currentTurn));
    }

    /**
     * Checks whether the space that the piece will be put on is a valid square to be placed on
     * @param endRow the row of the space
     * @param endCell the cell
     * @return false if the square is invalid, true otherwise
     */
    public boolean validateSquare(int endRow, int endCell) {
        return (endRow + endCell) % 2 != 0 && rows.get(endRow).getSpaces().get(endCell).getPiece() == null;
    }

    /**
     * Logic for whether a regular move is valid
     * @param move the move being checked
     * @param currentTurn the player whose turn it is
     * @return whether the move is valid
     */
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

    /**
     * Checks whether a jump move is valid or not.
     * @param move the move to check
     * @param currentTurn the player whose turn it is
     * @return true if the jump move is allowed, false otherwise
     */
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

    /**
     * Checks whether a jump move is available to be made or not
     * @param startRow the starting row to check
     * @param startCell the starting cell to check
     * @param currentTurn the color of the player whose turn it is
     * @return True if there is a jump move, false otherwise
     */
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

    /**
     * Checks whether a piece can be upgraded to a king
     * @param piece the piece being checked
     * @param move the move that would show whether the piece is moving towards its opposite end
     */
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

    /**
     * Checks whether the game is won by checking if all of one color piece is gone
     * @return
     */
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


    /**
     * Adds a move to the move queue
     * @param move the move to add
     */
    public void addPendingMove(Move move) {
        pendingMoves.add(move);
    }

    /**
     * Gets a list of all of the pending moves
     * @return an ArrayList of moves
     */
    public ArrayList<Move> getPendingMoves(){
        return pendingMoves;
    }

    /**
     * Undoes the last move made
     */
    public void backupMove() {
        pendingMoves.remove(pendingMoves.size() - 1);
    }

    /**
     * Gets all the rows in the board
     * @return
     */
    public ArrayList<Row> getRows() {
        return this.rows;
    }

    /**
     * Deprecated
     * @return
     */
    public Iterator<Row> iterator() {
        return rows.iterator();
    }

    /**
     * Used to check whether two board instances are identical
     * @param other the other board to look at
     * @return whether they are the same or not
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
