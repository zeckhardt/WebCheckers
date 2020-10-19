package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webcheckers.app.GameCenter;
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
        boolean valid = game.getBoard().validateMove(move);

        Message message = (valid)? Message.info("Valid move.") : Message.error("Invalid move.");
        response.body(gson.toJson(message));
        return 200;
    }
}
