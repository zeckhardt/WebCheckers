package com.webcheckers.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Tag("UI-tier")
public class GetSignInRouteTest {

    // CuT
    private GetSignInRoute CuT;

    // mock objects
    private Request request;
    private Response response;
    private TemplateEngine engine;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        CuT = new GetSignInRoute(engine);
    }

    @Test
    public void test_handle() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(GetSignInRoute.TITLE_ATTR, GetSignInRoute.TITLE);
        testHelper.assertViewModelAttribute(GetSignInRoute.MSG_ATTR, GetSignInRoute.SIGNIN_MSG);
    }
}