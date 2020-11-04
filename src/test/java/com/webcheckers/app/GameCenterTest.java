package com.webcheckers.app;
import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import com.webcheckers.model.Game;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

/**
 * Unit test for the {@link GameCenter} component.
 *
 * @author Zach Eckhardt
 */
@Tag("Application-Tier")
public class GameCenterTest {
    private Game game;
    private GameCenter CuT;
    private Board board;
    private Player p1;
    private Player p2;

    @BeforeEach
    public void setup(){
        CuT = new GameCenter();
        game = mock(Game.class);
        board = mock(Board.class);
        p1 = mock(Player.class);
        p2 = mock(Player.class);
    }

    @Test
    public void ctor(){final GameCenter CuT = new GameCenter();}

    @Test
    public void testAddGame(){
        ArrayList<Game> games  = CuT.getGames();
        CuT.addGame(game);
        assertEquals(1,games.size(),"Invalid Array");
    }

    @Test
    public void testGetGames(){
        ArrayList<Game> games  = CuT.getGames();
        ArrayList<Game> test = new ArrayList<Game>();
        assertEquals(test, games,"Array does not exist");
    }

    @Test
    public void testGetGameUUID() {
        UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
        Game testGame = new Game(uuid,board,p1,p2);
        CuT.addGame(testGame);
        assertNull(CuT.getGameByUUID(game.getUUID()), "Expected null, got value");
        assertEquals(testGame,CuT.getGameByUUID(UUID.fromString("123e4567-e89b-12d3-a456-556642440000")));
    }

}
