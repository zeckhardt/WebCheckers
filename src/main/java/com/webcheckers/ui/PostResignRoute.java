package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.app.GameCenter;
import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

import static spark.Spark.get;


/**
 * Handles when a player wants to resign
 */
public class PostResignRoute implements Route{
    private static final Logger LOG = Logger.getLogger(PostBackupMoveRoute.class.getName());
    private GameCenter gameCenter;
    private Gson gson;
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;

    public PostResignRoute(GameCenter gameCenter, Gson gson, TemplateEngine templateEngine, PlayerLobby playerLobby) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        //
        LOG.config("PostResignRoute is initialized.");
    }

    /**
     * Handles the requests for resigning
     * @param request
     * @param response
     * @return
     */
    public Object handle(Request request, Response response) {
        LOG.finer("PostResignRoute invoked.");
        String uuidString = request.queryParams("gameID");
        Game game = gameCenter.getGameByUUID(UUID.fromString(uuidString));
        Player whitePlayer = game.getWhitePlayer();
        Player redPlayer = game.getRedPlayer();
        redPlayer.leaveGame();
        whitePlayer.leaveGame();
        gameCenter.removeGame(game);
        playerLobby.addPlayer(whitePlayer);
        playerLobby.addPlayer(redPlayer);

        Message message = Message.info("You chickened out, and resigned from the game.");
        return message;
    }
}
