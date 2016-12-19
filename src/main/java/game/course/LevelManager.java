package game.course;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.IOException;
import java.util.ArrayList;

import game.entities.Entity;
import game.entities.Player;
import game.entities.Players;
import game.factories.EntityFactory;
import game.factories.WorldFactory;
import game.misc.CacheTool;
import game.misc.Locator;
import game.states.StateManager;
import game.types.GameTime;
import game.ui.hud.PlayerUI;
import loader.data.json.CampaignData;
import loader.parser.ParserException;
import math.Rectangle;
import util.Node;

public class LevelManager {
  private final StateManager stateManager;

  private final Campaign campaign;
  private final Image background;
  private final Image statics;
  private final WorldFactory worldFactory;

  private World world;
  private boolean finished;
  private boolean credits;

  public LevelManager(StateManager stateManager, boolean twoPlayer,
      CampaignData data, int windowWidth, int arenaWidth, int arenaHeight)
      throws ParserException, IOException, SlickException {
    if (data.levels.isEmpty()) {
      throw new IllegalArgumentException("No levels in campaign '" + data.name + "'");
    }

    this.stateManager = stateManager;

    int left   = data.constraints[0];
    int top    = data.constraints[1];
    int bottom = data.constraints[2];
    int right  = data.constraints[3];

    Rectangle worldRect = new Rectangle(
        left, top,
        arenaWidth - left - right,
        arenaHeight - top - bottom
    );

    statics = new Image(arenaWidth, arenaHeight);

    campaign   = new Campaign(data);
    background = CacheTool.getImage(Locator.getCache(), data.background);


    EntityFactory entityFactory = new EntityFactory(worldRect,
        statics.getGraphics());

    ArrayList<Entity> players = new ArrayList<>();
    Player player1 = entityFactory.makePlayer("blue",
        Locator.getConfig().player1);
    Locator.getUI().addDynamic(player1.infoBar);
    Locator.getUI().addStatic(new PlayerUI(0, 0, windowWidth, player1.shopUI,
        player1.inventory));
    players.add(player1.entity);

    if (twoPlayer) {
      Player player2 = entityFactory.makePlayer("red",
          Locator.getConfig().player2);
      Locator.getUI().addDynamic(player2.infoBar);
      Locator.getUI().addStatic(new PlayerUI(0, 0, windowWidth, player1.shopUI,
          player1.inventory));
      players.add(player2.entity);
    }

    Players.reposition(players, worldRect);

    worldFactory = new WorldFactory(this, stateManager, entityFactory,
        worldRect, players);

    credits = false;
    finished = false;
  }

  public void nextLevel() {
    if (campaign.hasMoreLevels()) {
      try {
        campaign.nextLevel();
        world = worldFactory.makeLevel(campaign.getCurrentLevel());
      } catch (ParserException | IOException ex) {
        stateManager.handleException(ex);
      }
    } else {
      finished = true;
      credits = true;
    }
  }

  public void update(GameTime time) {
    world.update(time);
  }

  public void render(Graphics g) {
    g.drawImage(background, 0, 0);
    g.drawImage(statics, 0, 0);
    world.render(g);
  }

  public boolean isFinished() {
    return finished;
  }

  public boolean isCredits() {
    return credits;
  }

  public Node<String> debugTree() {
    Node<String> parent = new Node<>("LevelManager");
    parent.nodes.add(world.debugTree());

    return parent;
  }
}
