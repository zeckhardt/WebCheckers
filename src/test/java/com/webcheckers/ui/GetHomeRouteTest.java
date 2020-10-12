package com.webcheckers.ui;
import com.webcheckers.app.PlayerLobby;
import org.junit.jupiter.api.Tag;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import spark.Request;
import spark.TemplateEngine;

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
    private PlayerLobby lobby;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        engine = mock(TemplateEngine.class);
        lobby = mock(PlayerLobby.class);
        CuT = new GetHomeRoute(engine,lobby);
    }
}
