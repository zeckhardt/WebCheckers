package com.webcheckers.ui;

import com.webcheckers.app.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import spark.Request;
import spark.TemplateEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * The unit test for {@link PostSignInRoute}
 *
 * @author Klaus Curde
 */
@Tag("UI-tier")
public class PostSignInRouteTest {
    // CuT
    private PostSignInRoute CuT;

    // mock objects
    private Request request;
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        templateEngine = mock(TemplateEngine.class);
        playerLobby = mock(PlayerLobby.class);
        CuT = new PostSignInRoute(templateEngine, playerLobby);
    }
}
