package com.webcheckers.ui;

import com.webcheckers.model.Game;
import com.webcheckers.app.GameCenter;
import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

/**
 * Gets the game route
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private final TemplateEngine templateEngine;
    private GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetGameRoute(final TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        //
        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * Handles the incoming request
     * @param request
     * @param response
     * @return
     */
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute invoked.");

        final Map<String, Object> vm = new HashMap<>();
        String uuidString = request.queryParams("gameID");
        UUID uuid = UUID.fromString(uuidString);
        Game game = gameCenter.getGameByUUID(uuid);

        //TODO: Add the other view modes for nonplayers

        Player redPlayer = game.getRedPlayer();
        Player whitePlayer = game.getWhitePlayer();
        vm.put("title", redPlayer.getName() + " vs. " + whitePlayer.getName());
        vm.put("gameID", uuid.toString());
        vm.put("board", game.getBoard());
        vm.put("redPlayer", redPlayer);
        vm.put("whitePlayer", whitePlayer);
        vm.put("viewMode", "PLAY");
        vm.put("currentUser", request.session().attribute("player"));
        vm.put("activeColor", game.getCurrentTurn().toString());

        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}
