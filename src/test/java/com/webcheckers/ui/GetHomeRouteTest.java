package com.webcheckers.ui;
import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Player;
import spark.ModelAndView;
import org.junit.jupiter.api.Tag;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.Mockito.*;

/**
 * The unit test for the {@link GetHomeRoute} component.
 *
 * @author Zach Eckhardt
 */
@Tag("UI-tier")
public class GetHomeRouteTest {

    //CuT
    private GetHomeRoute CuT;

    //mock objects
    private Request request;
    private TemplateEngine engine;
    private Response response;
    private PlayerLobby lobby;
    private Session session;
    private Player player;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        engine = mock(TemplateEngine.class);
        lobby = mock(PlayerLobby.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        CuT = new GetHomeRoute(engine,lobby);
    }

    @Test
    public void test_handle() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        player = new Player("player1");
        when(session.attribute("player")).thenReturn(player);
        CuT.handle(request, response);


        testHelper.assertViewModelAttribute(GetHomeRoute.WELCOME_ATTR, GetHomeRoute.WELCOME_MSG);
    }

}
