package com.webcheckers.model;

import com.webcheckers.app.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.TemplateEngine;

import java.util.ArrayList;
import java.util.Iterator;

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
    public void testSubmitMove() {
        Move move = new Move(3,0,4,1);
        CuT.addPendingMove(move);
        CuT.submitMove();
        assertEquals(0,CuT.getPendingMoves().size());
    }

    @Test
    public void testValidateSquare(){ assertTrue(CuT.validateSquare(4,1)); }

    @Test
    public void testValidateMove() {
        Move move = new Move(5,0,4,1);
        assertTrue(CuT.validateMove(move, Player.Color.RED));
    }

    @Test
    public void testValidateSimpleMove(){
        Move move = new Move(5,0,4,1);
        assertTrue(CuT.validateSimpleMove(move,Player.Color.RED));
    }

    @Test
    public void testValidateJumpMove() {
        Move move = new Move(2,3,0,5);
        assertTrue(CuT.validateJumpMove(move, Player.Color.RED));
    }

    @Test
    public void testJumpAvailable() {
        assertFalse(CuT.jumpAvailable(5,5, Player.Color.RED));
        assertFalse(CuT.jumpAvailable(1,5, Player.Color.RED));
    }

    @Test
    public void testCheckKing() {
        Piece piece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        Piece piece2 = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);
        Move move = new Move(1,0,0,1);
        Move move2 = new Move(6,1,7,0);
        CuT.checkKing(piece,move);
        CuT.checkKing(piece2,move2);
        assertEquals(Piece.Type.KING,piece.getType());
        assertEquals(Piece.Type.KING, piece2.getType());
    }

    @Test
    public void testAddPendingMove(){
        Move move = new Move(5,0,4,1);
        CuT.addPendingMove(move);
        ArrayList<Move> pendingMoves = CuT.getPendingMoves();
        assertEquals(1,pendingMoves.size());
    }

    @Test
    public void testGetPendingMoves() {
        Move move = new Move(5,0,4,1);
        ArrayList<Move> test = new ArrayList<Move>();
        test.add(move);
        CuT.addPendingMove(move);
        assertEquals(test,CuT.getPendingMoves());
    }

    @Test
    public void testBackupMove() {
        Move move = new Move(5,0,4,1);
        CuT.addPendingMove(move);
        ArrayList<Move> pendingMoves = CuT.getPendingMoves();
        assertEquals(1,pendingMoves.size());
        CuT.backupMove();
        assertEquals(0,pendingMoves.size());
    }

    @Test
    public void test_equals() {
        final Board CuT0 = new Board();
        final Board CuT1 = new Board();
        CuT0.hashCode();
        CuT1.hashCode();
        CuT.getRows().remove(2);
        assertEquals(true,CuT0.equals(CuT1));
        assertEquals(true,CuT1.equals(CuT0));
        assertFalse(CuT0.equals(CuT));
        assertFalse(CuT0.equals(null));
    }

}
