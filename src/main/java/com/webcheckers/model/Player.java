package com.webcheckers.model;

import java.util.Objects;

public class Player {

    public String name;

    public Player(String name) {
        this.name = Objects.requireNonNull(name, "name is required");
    }

    public String getName() {
        return name;
    }
}
