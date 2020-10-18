package com.webcheckers.ui;

import com.webcheckers.app.Game;
import com.webcheckers.app.GameCenter;
import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private final TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetGameRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        //
        LOG.config("GetGameRoute is initialized.");
    }

    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute invoked.");

        final Map<String, Object> vm = new HashMap<>();
        ArrayList<Player> players = playerLobby.getPlayers();
        Player player1 = request.session().attribute("player");
        String otherPlayerName = request.queryParams("otherPlayer");
        Player player2 = playerLobby.getPlayerByName(otherPlayerName);
        UUID uuid = UUID.randomUUID();
        Game game = new Game(uuid, new Board());
        gameCenter.addGame(game);

        vm.put("title", "Game");
        vm.put("gameID", uuid);
        // TODO: Add error handling
        int random = (int) Math.random() * 2;
        if (random % 2 == 0) {
            vm.put("redPlayer", player2);
            vm.put("whitePlayer", player1);
        }
        else {
            vm.put("redPlayer", player1);
            vm.put("whitePlayer", player2);
        }

        vm.put("viewMode", "PLAY");
        vm.put("currentUser", player1);

        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}

