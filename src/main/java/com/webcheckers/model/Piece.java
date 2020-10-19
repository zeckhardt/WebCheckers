package com.webcheckers.model;

/**
 * A class to represent a piece
 *
 * @author Klaus Curde
 */
public class Piece {
    // Enums for the possible states of a Piece
    public enum Type {SINGLE, KING}
    public enum Color {RED, WHITE}

    private Type type;
    private Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

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

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        else if (!(other instanceof Piece)) return false;
        Piece that = (Piece) other;
        return this.type == that.getType() && this.color == that.getColor();
    }
}
