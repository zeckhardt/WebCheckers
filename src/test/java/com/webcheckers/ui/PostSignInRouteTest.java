package com.webcheckers.ui;

import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    private Response response;
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private Session session;
    private Player player;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        templateEngine = mock(TemplateEngine.class);
        playerLobby = mock(PlayerLobby.class);
        response = mock(Response.class);
        session = mock(Session.class);
        player = new Player("test");
        CuT = new PostSignInRoute(templateEngine, playerLobby);
    }

    @Test
    public void testHandle(){
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request,response);
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
    }

    @Test
    public void testHandleValidUser(){
        when(request.queryParams("username")).thenReturn("test");
        when(playerLobby.isValidUsername("test")).thenReturn(true);
        when(request.session(true)).thenReturn(session);
        when(session.attribute("player")).thenReturn(player);
        CuT.handle(request,response);

    }
}
