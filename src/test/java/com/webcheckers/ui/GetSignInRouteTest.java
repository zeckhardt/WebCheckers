package com.webcheckers.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import spark.Request;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("UI-tier")
public class GetSignInRouteTest {

    // CuT
    private GetSignInRoute CuT;

    // mock objects
    private Request request;
    private TemplateEngine engine;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        engine = mock(TemplateEngine.class);

        CuT = new GetSignInRoute(engine);
    }
}