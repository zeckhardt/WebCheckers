package com.webcheckers.app;
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

    @BeforeEach
    public void setup(){
        CuT = new GameCenter();
        game = mock(Game.class);
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
        assertNull(CuT.getGameByUUID(game.getUUID()), "Expected null, got value");
    }

}
