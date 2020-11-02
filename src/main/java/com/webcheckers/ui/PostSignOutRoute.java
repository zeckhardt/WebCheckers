package com.webcheckers.ui;

import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * The UI Controller to POST player sign out.
 *
 * @author Aidan Lynch
 */
public class PostSignOutRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostSignOutRoute.class.getName());

    private PlayerLobby playerLobby;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /signout} HTTP requests.
     *
     * @param playerLobby
     *   the object logging player activities
     */
    public PostSignOutRoute(PlayerLobby playerLobby) {
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        //
        LOG.config("PostSignOutRoute is initialized.");
    }

    /**
     * Handle Player sign-in request
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   200 on success
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignOutRoute is invoked.");

        Player player = request.session().attribute("player");
        if (playerLobby.removePlayer(player)) {
            request.session().invalidate();
        }

        response.redirect("/");
        return 200;
    }
}
