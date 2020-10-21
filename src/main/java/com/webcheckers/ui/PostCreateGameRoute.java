package com.webcheckers.ui;

import com.webcheckers.model.Game;
import com.webcheckers.app.GameCenter;
import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

public class PostCreateGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostCreateGameRoute.class.getName());
    private final TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostCreateGameRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        //
        LOG.config("PostCreateGameRoute is initialized.");
    }

    public Object handle(Request request, Response response) {
        LOG.finer("PostCreateGameRoute invoked.");

        final Map<String, Object> vm = new HashMap<>();
        Player player1 = request.session().attribute("player");
        String otherPlayerName = request.queryParams("otherPlayer");
        Player player2 = playerLobby.getPlayerByName(otherPlayerName);

        if (player1.isInGame() || player2.isInGame()) {
            response.redirect("/");
            return 200;
        }

        Game game;
        UUID uuid = UUID.randomUUID();

        int random = (int) (Math.random() * 2);
        if (random % 2 == 0) {
            player1.joinGame(Player.Color.RED);
            player2.joinGame(Player.Color.WHITE);
            game = new Game(uuid, new Board(), player1, player2);
        }
        else {
            player2.joinGame(Player.Color.RED);
            player1.joinGame(Player.Color.WHITE);
            game = new Game(uuid, new Board(), player2, player1);
        }

        gameCenter.addGame(game);

        response.redirect("/game?gameID=" + uuid.toString());
        return 200;
    }
}

