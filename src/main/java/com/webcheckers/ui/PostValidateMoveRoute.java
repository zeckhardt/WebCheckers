package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webcheckers.app.GameCenter;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Routes for validation of moves
 */
public class PostValidateMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostValidateMoveRoute.class.getName());
    private GameCenter gameCenter;
    private Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle {@code POST /validateMove} HTTP requests.
     *
     * @param gameCenter
     *   the application game center
     */
    public PostValidateMoveRoute(GameCenter gameCenter, Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        //
        LOG.config("PostValidateMoveRoute is initialized.");
    }

    /**
     * Handles the required request
     * @param request
     * @param response
     * @return
     */
    public Object handle(Request request, Response response) {
        LOG.finer("PostValidateMoveRoute invoked.");

        Player player = request.session().attribute("player");
        String uuidString = request.queryParams("gameID");
        Game game = gameCenter.getGameByUUID(UUID.fromString(uuidString));
        String actionData = request.queryParams("actionData");

        Type type = new TypeToken<Map<String, Map<String, Integer>>>(){}.getType();
        Map<String, Map<String, Integer>> map = gson.fromJson(actionData, type);

        int startRow = map.get("start").get("row");
        int startCell = map.get("start").get("cell");
        int endRow = map.get("end").get("row");
        int endCell = map.get("end").get("cell");
        Move move = new Move(startRow, startCell, endRow, endCell);
        Board board = game.getBoard();
        boolean valid = board.validateMove(move, game.getCurrentTurn());

        Message message;
        if (valid) {
            message = Message.info("Valid move.");
            board.addPendingMove(move);
        } else {
            message = Message.error("Invalid move.");
        }

        return gson.toJson(message);
    }
}
