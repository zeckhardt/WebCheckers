package com.webcheckers.ui;

import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private final TemplateEngine templateEngine;
    private PlayerLobby playerLobby;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetGameRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        //
        LOG.config("GetGameRoute is initialized.");
    }

    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute invoked.");

        final Map<String, Object> vm = new HashMap<>();
        ArrayList<Player> players = playerLobby.getPlayers();
        Player player1 = request.session().attribute("player");
        Player player2 = null; // nooooooo

        // TODO: Add error handling and randomized role selection
        vm.put("viewMode", "PLAY");
        vm.put("currentUser", player1);
        vm.put("redPlayer", player2);
        vm.put("whitePlayer", player1);


        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}

