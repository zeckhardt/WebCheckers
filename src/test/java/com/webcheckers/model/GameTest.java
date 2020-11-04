package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


/**
 * Unit tests for {@link Game} component.
 *
 * @author Zach Eckhardt
 */
@Tag("Model-tier")
public class GameTest {

    private Game CuT;

    private Board board;
    private Player p1;
    private Player p2;
    private UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");

    @BeforeEach
    public void setup(){
        board = mock(Board.class);
        p1 = mock(Player.class);
        p2 = mock(Player.class);

        CuT = new Game(uuid,board,p1,p2);
    }

    @Test
    public void ctorArg(){final Game CuT = new Game(uuid,board,p1,p2);}

    @Test
    public void testGetUUID(){ assertEquals(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), CuT.getUUID());}

    @Test
    public void testGetBoard() { assertEquals(board, CuT.getBoard());}

    @Test
    public void testGetRedPlayer(){assertEquals(p1,CuT.getRedPlayer());}

    @Test
    public void testGetWhitePlayer(){assertEquals(p2, CuT.getWhitePlayer());}

    @Test
    public void testGetCurrentTurn(){assertEquals(Player.Color.RED, CuT.getCurrentTurn());}

    @Test
    public void testChangeTurn() {
        CuT.changeTurn();
        assertEquals(Player.Color.WHITE, CuT.getCurrentTurn());
        CuT.changeTurn();
        assertEquals(Player.Color.RED, CuT.getCurrentTurn());
    }
}
