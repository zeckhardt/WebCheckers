package com.webcheckers.model;

import java.util.Objects;

/**
 * Entity that encapsulates a unique username.
 *
 * @author Aidan Lynch
 */
public class Player {

    // Attributes
    public String name;

    /**
     * Create a new player with the given name.
     *
     * @param name
     *   the unique name of the player
     */
    public Player(String name) {
        this.name = Objects.requireNonNull(name, "name is required");
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
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
        return this.name.equals(that.name);
    }
}
