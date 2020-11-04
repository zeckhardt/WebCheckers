package com.webcheckers.model;

import java.util.Objects;

/**
 * Entity that encapsulates a unique username.
 *
 * @author Aidan Lynch
 */
public class Player {
    public enum Color {RED, WHITE}

    // Attributes
    private String name;
    private boolean inGame;
    private Color color;
    public boolean isWinner;

    /**
     * Create a new player with the given name.
     *
     * @param name
     *   the unique name of the player
     */
    public Player(String name) {
        this.name = Objects.requireNonNull(name, "name is required");
        this.inGame = false;
        this.isWinner = false;
    }

    public void joinGame(Color color) {
        inGame = true;
        this.color = color;
    }

    public void leaveGame() {
        inGame = false;
        this.color = null;
    }

    /**
     * Gets the player's name
     *
     * @return player's name
     */
    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Gets whether the player is in a game
     * @return if in a game
     */
    public boolean isInGame() {
        return inGame;
    }

    /**
     * Finds the hashcode of this object.
     *
     * @return
     *   the hashcode
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Determines if this object equals another.
     *
     * @param obj
     *   the object to compare with
     * @return
     *   whether obj = this
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else if (!(obj instanceof Player)) return false;
        Player that = (Player) obj;
        return this.name.equals(that.getName());
    }

    /**
     * Gives a string representation of a player
     * @return the string name of the player
     */
    @Override
    public String toString() {
        return name;
    }
}
