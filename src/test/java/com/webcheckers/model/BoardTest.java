package com.webcheckers.model;

import com.webcheckers.app.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.TemplateEngine;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * The unit test for the {@link Board}
 *
 * @author Klaus Curde
 */
@Tag("Model-Tier")
public class BoardTest {
    // CuT
    private Board CuT;

    // mock objects
    private Integer id;

    @BeforeEach
    public void setup() {
        CuT = new Board();
    }

    @Test
    public void ctor() {
        final Board CuT = new Board();
    }

    @Test
    public void test_makeRows() {
        final Board CuT = new Board();
        assertEquals(CuT.getRows().size(), 8);
    }

    @Test
    public void test_getRows() {
        final Board CuT = new Board();
        assertEquals(CuT.getRows().size(), 8);
    }

    @Test
    public void testValidateSquare(){ assertTrue(CuT.validateSquare(2,3)); }

    @Test
    public void testValidateMove() {
        Move move = new Move(6,1,5,2);
        assertTrue(CuT.validateMove(move, Player.Color.RED));
    }

    @Test
    public void testValidateSimpleMove(){
        Move move = new Move(6,1,5,2);
        assertTrue(CuT.validateSimpleMove(move,Player.Color.RED));
    }

    @Test
    public void testAddPendingMove(){
        Move move = new Move(6,1,5,2);
        CuT.addPendingMove(move);
        ArrayList<Move> pendingMoves = CuT.getPendingMoves();
        assertEquals(1,pendingMoves.size());
    }

    @Test
    public void test_equals() {
        final Board CuT0 = new Board();
        final Board CuT1 = new Board();
        CuT0.hashCode();
        CuT1.hashCode();
        assertEquals(true,CuT0.equals(CuT1));
        assertEquals(true,CuT1.equals(CuT0));
    }

}
