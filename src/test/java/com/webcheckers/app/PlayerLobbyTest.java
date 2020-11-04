package com.webcheckers.app;
import java.util.ArrayList;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test for the {@link PlayerLobby} component.
 *
 * @author Zach Eckhardt
 */
@Tag("Application-Tier")
public class PlayerLobbyTest {
    private PlayerLobby CuT;

    @BeforeEach
    public void setup(){ CuT = new PlayerLobby();}

    @Test
    public void ctor(){final PlayerLobby CuT = new PlayerLobby();
    }

    @Test
    public void testIsValidUsername() {
        final PlayerLobby CuT = new PlayerLobby();
        assertFalse(CuT.isValidUsername("@#$&*#"),"Expected True, got False");
    }

    @Test
    public void testAddPlayer() {
        Player player = new Player("name");
        ArrayList<Player> players = CuT.getPlayers();
        CuT.addPlayer(player);
        assertEquals(1,players.size(), "Invalid Player");
    }

    @Test
    public void testRemovePlayer() {
        Player player = new Player("name");
        CuT.addPlayer(player);
        assertTrue(CuT.removePlayer(player), "Unsuccessful remove");
    }

    @Test
    public void testGetPlayer() {
        ArrayList<Player> testAR = new ArrayList<>();
        Player test = new Player("player1");
        Player test2 = new Player("player2");
        testAR.add(test);
        testAR.add(test2);
        CuT.addPlayer(test);
        CuT.addPlayer(test2);
        assertEquals(testAR, CuT.getPlayers(), "Player input invalid");
    }

    @Test
    public void testGetPlayerByName() {
        ArrayList<Player> players = CuT.getPlayers();
        Player test = new Player("p1");
        CuT.addPlayer(test);
        assertNotNull(CuT.getPlayerByName("p1"),"Expected no null, got null return");

    }
}
