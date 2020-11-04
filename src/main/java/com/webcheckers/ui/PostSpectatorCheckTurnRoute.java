package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.app.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Objects;
import java.util.logging.Logger;

public class PostSpectatorCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSpectatorCheckTurnRoute.class.getName());
    private GameCenter gameCenter;
    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle {@code POST /checkTurn} HTTP requests.
     *
     * @param gameCenter
     *   the application game center
     */
    public PostSpectatorCheckTurnRoute(GameCenter gameCenter, Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        //
        LOG.config("PostSpectatorCheckTurnRoute is initialized.");
    }

    public Object handle(Request request, Response response) {
        LOG.finer("PostSpectatorCheckTurnRoute invoked.");

        Player player = request.session().attribute("player");
        Game game = null;
        for (Game g : gameCenter.getGames()) {
            if (g.getSpectators().contains(player)) {
                game = g;
            }
        }

        boolean reload = !game.getCurrentTurn().equals(request.session().attribute("lastTurn"));

        Message message = Message.info(((reload)? "true" : "false"));
        return gson.toJson(message);
    }
}
