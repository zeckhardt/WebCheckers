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

public class PostBackupMoveRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());
    private GameCenter gameCenter;
    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle {@code POST /checkTurn} HTTP requests.
     *
     * @param gameCenter
     *   the application game center
     */
    public PostBackupMoveRoute(GameCenter gameCenter, Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        //
        LOG.config("PostBackupMoveRoute is initialized.");
    }

    public Object handle(Request request, Response response) {
        LOG.finer("PostBackupMoveRoute invoked.");

        Player player = request.session().attribute("player");
        String uuidString = request.queryParams("gameID");
        Game game = gameCenter.getGameByUUID(UUID.fromString(uuidString));
        boolean turn = false;
        if (game.getCurrentTurn() == Player.Color.RED) {
            turn = player.equals(game.getRedPlayer());
        } else {
            turn = player.equals(game.getWhitePlayer());
        }

        Message message;
        if (turn) {
            game.getBoard().backupMove();
            message = Message.info("Backed up move.");
        } else {
            message = Message.error("It is not your turn.");
        }

        return gson.toJson(message);
    }
}
