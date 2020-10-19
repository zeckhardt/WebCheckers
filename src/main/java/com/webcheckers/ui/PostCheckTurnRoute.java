package com.webcheckers.ui;

import com.webcheckers.app.GameCenter;
import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

public class PostCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());
    private GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) to handle {@code POST /checkTurn} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostCheckTurnRoute(GameCenter gameCenter) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        //
        LOG.config("PostCheckTurnRoute is initialized.");
    }

    public Object handle(Request request, Response response) {
        LOG.finer("PostCheckTurnRoute invoked.");

        Player player = request.session().attribute("player");
        String uuidString = request.queryParams("gameID");
        Game game = gameCenter.getGameByUUID(UUID.fromString(uuidString));
        boolean turn = false;
        if (game.getCurrentTurn() == Player.Color.RED) {
            turn = player.equals(game.getRedPlayer());
        }
        else {
            turn = player.equals(game.getWhitePlayer());
        }

        response.body("{ \"message\": " + ((turn)? "\"true\"" : "\"false\""));
        return 200;
    }
}
