package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostSignInRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

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
        boolean validUsername = playerLobby.isValidUsername(username);
        //
        if (validUsername) {
            Player player = new Player(username);
            Session session = request.session(true);
            session.attribute("player", player);
            playerLobby.addPlayer(player);
            response.redirect("/");
            return 200;
        }
        else {
            Map<String, Object> vm = new HashMap<>();
            vm.put("title", "Sign In");
            vm.put("message", playerLobby.getErrorMsg());

            // render the View
            return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
        }
    }
}
