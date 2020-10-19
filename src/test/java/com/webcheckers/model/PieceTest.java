package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for the {@link Piece} component
 *
 * @author Zach Eckhardt
 */
@Tag("Model-Tier")
public class PieceTest {

    private Piece CuT;

    @BeforeEach
    public void setup(){
        CuT = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
    }

    @Test
    public void ctor(){
        final Piece CuT = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
    }

    @Test
    public void testGetType(){assertEquals(Piece.Type.SINGLE, CuT.getType());}

    @Test
    public void testGetColor(){assertEquals(Piece.Color.RED, CuT.getColor());}
}
