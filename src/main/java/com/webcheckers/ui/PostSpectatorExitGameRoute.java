package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.app.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

public class PostSpectatorExitGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSpectatorExitGameRoute.class.getName());
    private GameCenter gameCenter;
    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle {@code POST /checkTurn} HTTP requests.
     *
     * @param gameCenter
     *   the application game center
     */
    public PostSpectatorExitGameRoute(GameCenter gameCenter, Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        //
        LOG.config("PostSpectatorExitGameRoute is initialized.");
    }

    public Object handle(Request request, Response response) {
        LOG.finer("PostSpectatorExitGameRoute invoked.");

        Player player = request.session().attribute("player");
        String uuidString = request.queryParams("gameID");
        Game game = gameCenter.getGameByUUID(UUID.fromString(uuidString));

        game.removeSpectator(player);
        response.redirect("/");
        return 200;
    }
}
