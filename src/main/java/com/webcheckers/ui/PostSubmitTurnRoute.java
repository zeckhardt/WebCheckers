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
import java.util.UUID;
import java.util.logging.Logger;

public class PostSubmitTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostSubmitTurnRoute.class.getName());
    private GameCenter gameCenter;
    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle {@code POST /checkTurn} HTTP requests.
     *
     * @param gameCenter
     *   the application game center
     */
    public PostSubmitTurnRoute(GameCenter gameCenter, Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        //
        LOG.config("PostSubmitTurnRoute is initialized.");
    }

    public Object handle(Request request, Response response) {
        LOG.finer("PostSubmitTurnRoute invoked.");

        Player player = request.session().attribute("player");
        String uuidString = request.queryParams("gameID");
        Game game = gameCenter.getGameByUUID(UUID.fromString(uuidString));
        boolean turn;
        if (game.getCurrentTurn() == Player.Color.RED) {
            turn = player.equals(game.getRedPlayer());
        } else {
            turn = player.equals(game.getWhitePlayer());
        }

        Message message;
        if (turn) {
            game.getBoard().submitMove();
            if (game.checkGameWon()) {
                System.exit(1); // TODO: Not this
            }
            game.changeTurn();
            message = Message.info("Move submitted.");
        } else {
            message = Message.error("It is not your turn.");
        }

        return gson.toJson(message);
    }
}
