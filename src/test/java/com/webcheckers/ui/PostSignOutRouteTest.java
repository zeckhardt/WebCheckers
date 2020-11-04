package com.webcheckers.ui;

import com.webcheckers.app.GameCenter;
import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
public class PostSignOutRouteTest {

    // CuT
    private PostSignOutRoute CuT;

    // mock objects
    private Request request;
    private Response response;
    private Session session;
    private PlayerLobby playerLobby;
    private Player player;
    private GameCenter gameCenter;

    public PostSignOutRouteTest() {
    }

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        playerLobby = mock(PlayerLobby.class);
        player = mock(Player.class);
        gameCenter = mock(GameCenter.class);

        CuT = new PostSignOutRoute(gameCenter,playerLobby);
    }

    @Test
    public void test_handle() {
        when(request.session()).thenReturn(session);
        when(session.attribute("player")).thenReturn(player);
        when(playerLobby.removePlayer(player)).thenReturn(true);
        CuT.handle(request, response);
        //TODO Test that player is removed from session (mockito is weird)
    }
}
