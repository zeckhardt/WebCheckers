package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.app.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Route for checking whether a move is valid
 */
public class PostCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());
    private GameCenter gameCenter;
    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle {@code POST /checkTurn} HTTP requests.
     *
     * @param gameCenter
     *   the application game center
     */
    public PostCheckTurnRoute(GameCenter gameCenter, Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        //
        LOG.config("PostCheckTurnRoute is initialized.");
    }

    /**
     * Handles the request for checking a turn
     * @param request
     * @param response
     * @return
     */
    public Object handle(Request request, Response response) {
        LOG.finer("PostCheckTurnRoute invoked.");

        Player player = request.session().attribute("player");
        String uuidString = request.queryParams("gameID");
        Game game = gameCenter.getGameByUUID(UUID.fromString(uuidString));
        boolean turn = player.getColor() == game.getCurrentTurn();

        Message message = Message.info(((turn)? "true" : "false"));
        return gson.toJson(message);
    }
}
