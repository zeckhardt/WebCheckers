package com.webcheckers.ui;

import com.webcheckers.app.PlayerLobby;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostSignInRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private static final Message ERROR_MSG = Message.info("That username is already taken! Please try another.");

    private final TemplateEngine templateEngine;
    private PlayerLobby playerLobby;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /signin} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostSignInRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        //
        LOG.config("PostSignInRoute is initialized.");
    }

    /**
     * Handle Player sign-in request
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   //TODO: IDK
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignInRoute is invoked.");

        String username = request.queryParams("username");
        boolean validUsername = playerLobby.usernameAvailable(username);

        //
        if (validUsername) {
            //TODO: save new player in session
            response.redirect("/");
        }
        else {
            Map<String, Object> vm = new HashMap<>();
            vm.put("title", "Sign In");
            vm.put("message", ERROR_MSG);

            // render the View
            templateEngine.render(new ModelAndView(vm, "signin.ftl"));
        }

        return null;
    }
}
