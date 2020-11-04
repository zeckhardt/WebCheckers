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

    @Test
    public void testToKing() {
        CuT.toKing();
        assertEquals(Piece.Type.KING,CuT.getType());
    }

    @Test
    public void testEquals(){
        Piece CuT1 = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        Piece CuT2 = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        CuT.toKing();
        assertEquals(true, CuT1.equals(CuT2));
        assertEquals(true, CuT2.equals(CuT1));
        assertFalse(CuT1.equals(CuT));
        assertFalse(CuT1.equals(null));
    }
}
