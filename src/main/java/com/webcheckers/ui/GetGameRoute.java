package com.webcheckers.ui;

import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class GetGameRoute implements Route {
    private final TemplateEngine templateEngine;

    GetGameRoute(final TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
    }

    public String handle(Request request, Response response) {

        final Map<String, Object> vm = new HashMap<>();
        return null;
    }
}

