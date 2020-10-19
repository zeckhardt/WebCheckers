package com.webcheckers.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for {@link Space} component.
 *
 * @author Zach Eckhardt
 */
@Tag("Model-Tier")
public class SpaceTest {

    private Space CuT;
    private Piece piece;

    @BeforeEach
    public void setup(){
        CuT = new Space(5);
        piece = mock(Piece.class);
    }

    @Test
    public void ctorNoArg() {
        assertThrows(NumberFormatException.class, () -> {
            final Space CuT = new Space(Integer.parseInt(null));
        });
    }

    @Test
    public void ctorArg(){final Space CuT = new Space(5); }

    @Test
    public void testIsValid(){
        CuT.placePiece(piece);
        assertFalse(CuT.isValid());
    }

    @Test
    public void testGetCellIDx(){
        assertEquals(5,(int)CuT.getCellIdx());
    }

    @Test
    public void testGetPiece(){
        CuT.placePiece(piece);
        assertEquals(piece,CuT.getPiece());
    }
}
