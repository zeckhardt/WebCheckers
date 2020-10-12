package com.webcheckers.app;
import java.util.ArrayList;
import com.webcheckers.model.Player;
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

    @Test
    public void test_isValidUsername() {
        final PlayerLobby CuT = new PlayerLobby();
        assertFalse(CuT.isValidUsername("@#$&*#"),"Expected True, got False");
    }

    @Test
    public void test_addPlayer() {
        final PlayerLobby CuT = new PlayerLobby();
        Player player = new Player("name");
        CuT.addPlayer(player);
        assertEquals(player,player, "Invalid Player");
    }

    @Test
    public void test_getPlayer() {
        final PlayerLobby CuT = new PlayerLobby();
        ArrayList<Player> testAR = new ArrayList<>();
        Player test = new Player("player1");
        Player test2 = new Player("player2");
        testAR.add(test);
        testAR.add(test2);
        CuT.addPlayer(test);
        CuT.addPlayer(test2);
        assertEquals(testAR, CuT.getPlayers(), "Player input invalid");
    }
}
