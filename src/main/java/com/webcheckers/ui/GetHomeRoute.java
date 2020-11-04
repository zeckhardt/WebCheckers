package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.webcheckers.app.GameCenter;
import com.webcheckers.app.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.model.Game;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  protected static final String WELCOME_ATTR = "message";
  protected static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

  private final TemplateEngine templateEngine;
  private PlayerLobby playerLobby;
  private GameCenter gameCenter;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameCenter gameCenter) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
    this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");
    //
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");
    ArrayList<Player> players = playerLobby.getPlayers();
    Player player = request.session().attribute("player");

    if (player != null) {
      vm.put("currentUser", player);
      vm.put("username", player.getName());
      vm.put("players", players);

      if (player.isInGame()) {
        for (Game g : gameCenter.getGames()) {
          if (player.equals(g.getRedPlayer()) || player.equals(g.getWhitePlayer())) {
            response.redirect("/game?gameID=" + g.getUUID().toString());
            return 200;
          }
        }
      }
    }
    else {
      vm.put("numPlayers", players.size());
    }

    // display a user message in the Home page
    Message message = request.session().attribute("message");
    request.session().removeAttribute("message");
    if (message == null) {
      vm.put(WELCOME_ATTR, WELCOME_MSG);
    }
    else {
      vm.put(WELCOME_ATTR, message);
    }


    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}
