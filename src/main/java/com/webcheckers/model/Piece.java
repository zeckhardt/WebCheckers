package com.webcheckers.model;

/**
 * A class to represent a piece
 *
 * @author Klaus Curde
 */
public class Piece {
    // Enums for the possible states of a Piece
    private enum Type {SINGLE, KING}
    private enum Color {RED, WHITE}

    private Type type;
    private Color color;

    /**
     * Gets the type of the piece
     * @return the type value
     */
    public Type getType(){
        return this.type;
    }

    /**
     * Gets the color of the piece
     * @return
     */
    public Color getColor() {
        return this.color;
    }
}
